package com.warehouseservice.Service;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class FileSort implements Comparator<FileSort>
{

	int id;
	String name;

	public FileSort(int id, String name) {
		this.id = id;
		this.name = name;
	}

	@Override
	public int compare(FileSort o1, FileSort o2) {
		
		return o1.id-o2.id;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return id;
	}


	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}


	public static void main(String[] args) throws IOException 
	{
		
		BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\dzamir\\Desktop\\input_file.txt"));
		ArrayList<FileSort> lst = new ArrayList<FileSort>();
		FilesComperator cmpr = new FilesComperator();
		String [] fin = null; 
		String currentline = br.readLine();
		while(currentline!=null)
		{
			String [] str = currentline.split(" ");
			if(str.length>1) 
			{
				FileSort fst = new FileSort(Integer.parseInt(str[0]), str[1]);
				lst.add(fst);
			}
			if (str.length<=1)
			{
				FileSort fst = new FileSort(0, str[0]);
				lst.add(fst);
			}
			currentline = br.readLine();
		}
		
		lst.sort(cmpr);
		
		BufferedWriter wrt = new BufferedWriter(new FileWriter("C:\\Users\\dzamir\\Desktop\\output_file.txt"));
		for (int j = 0; j < lst.size();j++) 
		{
			wrt.write(lst.get(j).id+" "+lst.get(j).name);
			wrt.newLine();
		}
		
		wrt.close();
		br.close();
			
	 }
		


}
