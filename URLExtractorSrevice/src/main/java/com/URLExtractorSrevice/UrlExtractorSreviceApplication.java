package com.URLExtractorSrevice;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.URLExtractor.Entity.Category;
import com.URLExtractor.Entity.Page;

import URLExtractorSrevice.Service.FitCategory;
import URLExtractorSrevice.Service.Htmldownload;

@SpringBootApplication
public class UrlExtractorSreviceApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(UrlExtractorSreviceApplication.class, args);
		
		ArrayList<Page> pageslst = new ArrayList<>();				//input pages list (should of been a file...)
		ArrayList<Category> categories = new ArrayList<>();			//input categories list (should of been a file...)
		
		pageslst = Htmldownload.extractContent(pageslst);			//regenerating the list now with their HTML contents
		
		pageslst = FitCategory.assignCategory(pageslst,categories); //regenerating the list now with assigned categories 
		
		/***Bonus - scanning the new pages list and if the page has populated category field it will be inserted in a minimized list***/
		pageslst = FitCategory.pagesWithCategoryOnly(pageslst); 	//regenerating the list now showing only pages that found their category  
		
	}

}
