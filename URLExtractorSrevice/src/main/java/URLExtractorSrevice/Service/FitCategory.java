package URLExtractorSrevice.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.stereotype.Service;

import com.URLExtractor.Entity.Category;
import com.URLExtractor.Entity.Page;

@Service
public class FitCategory {
	
	
	//Brute-force method to match a category to each web page in the provided list (O(MxNxK))
	public static ArrayList<Page> assignCategory(ArrayList<Page> pgLst,ArrayList<Category> catLst) 
	{
		for (Page page : pgLst) 								//1/ take each page in the provided web pages list
		{
			int found=0;
			String curPage = page.getHtmlContent();					//1.1/ extract the content string of this page			
			for (Category category : catLst) 					//2/ take each primary category provided in the category list
			{
				String [] tempCats = category.getKeywords();		//2.1/ extract an array of keywords of this category
				for (String word : tempCats) 					//3/ for each keyword check if its present in the content string of the page
				{
					String tempWord = word;
					if (curPage.contains(tempWord)) 				//3.1/ if the keyword is present declare assign the primery category to this page
					{								
						page.setCategory(category.getCatName());
						found=1;
						break;											//found so break to check the next page skip all next keywords
					}
				}
				if (found==1) break;									//found so break to check the next page skip all next categories 

			}
		}
		return pgLst;													//finished updating the the web-pages list with categories so returning it

	}


	/***Bonus option - Method to minimize a list of web pages to have only web pages that found a category for themselves (O(N))***/
	public static ArrayList<Page> pagesWithCategoryOnly(ArrayList<Page> pgLst) 
	{
		ArrayList<Page> minimizedPageslst = new ArrayList<>();
		
		for (Page page : pgLst) 
		{
			if(page.getCategory()!=null)
			{
				minimizedPageslst.add(page);
			}
		}
		return minimizedPageslst;
	}
	
	
	/***Bonus option - to minimize runtime execution the above algorithm may be divided to be processed by 4 separate threads given a 4 core CPU - if not bound by CPU then O(MxK)***/
	volatile boolean found=false;//for multi-threads communication
	public ArrayList<Page> assignWithMultiThreads(ArrayList<Page> pgLst,ArrayList<Category> catLst) 
	{
		
		ExecutorService cpuBound = Executors.newFixedThreadPool(4);//creating new threadpool

		int qutro = catLst.size()/4;
		for (Page page : pgLst) 								//1/ take each page in the provided web pages list
		{
			String curPage = page.getHtmlContent();					//1.1/ extract the content string of this page	
			for (int i = 0,cnt =0; i < 4; i++) //Dividing the work of category assignment to 4 threads by 
			{
				//splitting the category list to 4, each thread will explore forth of the list
				ArrayList<Category> tempcatLst = (ArrayList<Category>) catLst.subList(cnt, qutro);
				cnt=qutro;
				
				//sending each list to a separate new thread
				Thread t1 = new Thread(() -> {
					while(!found)
					{
						for (Category category : catLst) 					//2/ take each primary category provided in the category list
						{
							String [] tempCats = category.getKeywords();		//2.1/ extract an array of keywords of this category
							for (String word : tempCats) 					//3/ for each keyword check if its present in the content string of the page
							{
								String tempWord = word;
								if (curPage.contains(tempWord)) 				//3.1/ if the keyword is present declare assign the primary category to this page
								{								
									page.setCategory(category.getCatName());
									found=true;
									break;											//found so break to check the next page skip all next keywords
								}
							}
							if (found) break;									//found so break to check the next page skip all next categories 

						}						
					}
				});
				
				cpuBound.submit(t1);	
			}

		}
		return pgLst;														//finished updating the the web-pages list with categories so returning it
	}
	
}
