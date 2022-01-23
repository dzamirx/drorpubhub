package URLExtractorSrevice.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import com.URLExtractor.Entity.Page;

@Service
public class Htmldownload {

	
	//Method to extract HTML content for a list of URL's provided in a text file returning a list of Page objects 
	public static ArrayList<Page> extractContent(ArrayList<Page> lst) throws IOException
	{
		for (Page page : lst)
		{
			String tempUrl = page.getPageUrl();
			String content = null;
			
			URLConnection connection = null;
			try {								//try to establish a connection to the URL
			  connection =  new URL(tempUrl).openConnection();
			  Scanner scanner = new Scanner(connection.getInputStream());
			  scanner.useDelimiter("\\Z");
			  content = scanner.next();
			  scanner.close();
			}catch ( Exception ex ) {
			    ex.printStackTrace();
			}
			
			page.setHtmlContent(content);			//save the HTML content in a single String inside Page object
			
			System.out.println(tempUrl);		//print to console for debugging
			System.out.println(content);
			
		}
		return lst;								//return a list of Pages 
	}

	
}
