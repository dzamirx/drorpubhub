package com.warehouseservice.Service;

import java.io.CharArrayReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.stream.IntStream;

import javax.print.DocFlavor.INPUT_STREAM;

import org.springframework.security.access.event.PublicInvocationEvent;

public class javatest {

		/*** STRING - REVERSE ***/
	public static String reverse(String str) 
	{
		StringBuffer bf= new StringBuffer(str);
		System.out.println(bf.reverse());
		
		char[] c = str.toCharArray();
		for (int i = 0, j=c.length-1; i <= c.length/2; i++,j--) 
		{
			char temp = c[i];
			c[i]=c[j];
			c[j]=temp;
		}

		String str2 = String.valueOf(c);
		return str2;
	}
	
		/***   STRINGS  - DUPLICATES? 	***/	
	public static void findDuplicates(String str)
	{
		HashMap<Character,Integer>  s = new HashMap<>();
		char[] c = str.toCharArray();
		for (int i = 0; i < c.length; i++) 
		{
			if (s.containsKey(c[i])) 
			{
				s.put(c[i],s.get(c[i])+1 );
			}	else
				{
					s.put(c[i], 1);
				}
		}
		
		for (int i = 1; i < s.size(); i++) {
			if (s.get(c[i])>1) 
			{
				System.out.println(c[i]+":"+s.get(c[i]));
			}
		}	
	}
	
			/***   ARRAYS  - EQUALS? 	***/
	public static boolean ifArraysEqual(char [] str1,char [] str2 )
	{
		if (str1.length != str2.length ) return false;
		Arrays.sort(str1);
		Arrays.sort(str2);
		
		if (str1.equals(str2))
		{
			return true;
		}
		return false;
	}
	
		/***   ARMSTRONG 	***/
	public static boolean isArmstrongNum(int input) {
		int temp = input;
		int local = 0;
		String set = String.valueOf(input);
		int l = set.length();
		for (int i = 0; i < l; i++) 
		{
			int mod = temp%10;
			int h= mod;
			//double h=Math.pow(h, l);
			for (int j = 1; j <l; j++) 
			{
				h = h*mod;
			}
			local = local+h;
			temp=temp/10;
		}
		if (local==input) {
			return true;
		}
		return false;
	}
	
		/***   ARRAYS - DUPLICATES (SET)	***/
	public static void findDuplicates(int [] input) {
		
		HashSet<Integer> check = new HashSet<>();
		for (int i = 0; i < input.length; i++) {
			if (check.contains(input[i]))
			{
				System.out.println(input[i]+" is a duplicated char");
			}
			else check.add(input[i]);
		}

	}
	
	/***   ARRAYS - streams	***/
	public static void streams(int [] input, Customer cos) 
	{
		int minimal = IntStream.of(input).min().getAsInt();//find the minimum number in an int [] array// .max() //	.sum() //	.count()	// .average() //
	
		IntStream.of(input).min().ifPresent(System.out::println);//if there is a minimum print it out

		IntStream.of(input).distinct().sorted().limit(3).forEach(System.out::println);//find the 3 distinct integers of minimal	//.skip(3) // .filter(num-> num % 2==0)  // .map(num-> num*2)
																						//.anyMatch(num-> num % 2==0) -->returns true if ANY of the numbers is even
																						//.allMatch(num-> num % 2==0) -->returns true if ALL of the numbers is even
		
		ArrayList<Customer> lst = new ArrayList<Customer>();// take a list of objects sort them by id and then will give you the names of the 3 lowest earning employees
		lst.stream().sorted(Comparator.comparingInt(Customer::getId).reversed()).limit(3).map(Customer::getName).forEach(System.out::println);
		//can filter the results:    .filter(Customer -> isAactive(Customer)) to implement an if(Customer is active)
		//can collect all the objects to a new list/set/map: 	.collect(Collectors.toList)		.collect(Collectors.toSet)	.collect(Collectors.toMap)
		
		//can add multithreads inside the command: .parallel() when there are streams of more than 10000 elements
		
	}
	
		/***   DIGIT SUM	***/
	public static int digitSum(int input) {
		
		int temp = input;
		int rem = 0;
		
		for (int i = 0; temp!=0; i++) 
		{
			rem = rem+temp%10;
			temp=temp/10;
		}
		return rem;
	}
	
		/***   ARRAY -  SECOND HIGHEST ***/
	public static int secondLargest(int [] input) 
	{
		if (input.length==0)return 0;

		int highest=input[0];
		int scndhiest=input[0];
		
		for (int i = 1; i < input.length; i++) 
		{
			if (input[i]<highest)
			{
				if (input[i]>scndhiest)
				{				
					scndhiest=input[i];
				}
			}
			if (input[i]>highest)
			{
				scndhiest=highest;
				highest=input[i];
			}
		}
		return scndhiest;
	}
	
		/***  MAP -  COUNT ACCURANCES ***/
	public static void countAccurances(char [] input)
	{
		Map<Character, Integer> acc = new HashMap<>();
		
		for (int i = 0; i < input.length; i++) 
		{
			if (acc.containsKey(input[i]))
			{
				acc.put(input[i], acc.get(input[i])+1);
			}else
			{
				acc.put(input[i], 1);
			}
		}
		System.out.println(acc);
	}
	
		/***  MAP -  FIND PAIRS THAT ADDUP ***/
	public static void findPairs(int [] input, int given)
	{
	
		Map<Integer, Integer> join = new HashMap<>();
		
		for (int i = 0; i < input.length; i++) 
		{
			if (join.containsKey(input[i]))
			{
				System.out.println(input[i]+" : "+join.get(input[i]));
			}
			else
			{
				join.put(given-input[i], input[i]);
			}
		}
	}
	
	
		/***  ARRAYS -  FIND SUBARRAY THAT ADDUP ***/
	public static void subArraySum(int [] input, int given) 
	{
		//int [] helper = new int[input.length];
		int temp = given;
		
		for (int i = 0,j = 0; i < input.length; i++) 
		{
			temp = temp-input[i];
			
			while(temp<0)
			{
				temp=temp+input[j];
				j++;
			}
			if (temp>0) 
			{
				continue;
			}
			if (temp==0) 
			{
				for (int k = j; k < i+1; k++) 
				{
					System.out.println(input[k]);
				}
				break;
			}
			System.out.println("No subarray found for the value"+ given);

		}
	}
	
		/***  SETS -  REMOVE DUPLICATES ***/
	public static void removeDuplicates(ArrayList<Character> given) 
	{
		HashSet<Character> set = new HashSet<Character>();
		for (Character character : given) 
		{
			set.add(character);
		}
		System.out.println(set);	
	}
	
	
		/***   DIGIT  - IS BINARY	***/
	public static void isBinary(int given) 
	{
		int temp= given;
		String str = String.valueOf(given);
		int l = str.length();
		
		for (int i = 0; i < l; i++) 
		{
			int striped = temp%10;
			if (striped!=1 && striped!=0)
			{
				System.out.println("given number is not binary");
				break;
			}
			else 
			{
				if(i==l-1) 
				{
					System.out.println(given +" is binary");
					break;
				}
				temp= temp/10;
			}
		}
	}
	
		/***   DIGIT  - IS A NUMBER	***/
	public static void isInteger(String given) 
	{
		
		try {
			Integer i = Integer.parseInt(given);
			System.out.println(i+ " is a number");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			System.out.println("not a number");
		}

	}
	
		/***   STRING  - REVERSE INTENALLY	***/
	public static void reverseStringInternaly(String ins) 
	{
		String strf = null;
        String[] arrOfStr = ins.split(" ");
		for (int i = 0; i < arrOfStr.length; i++)
		{
			StringBuffer bf = new StringBuffer();
			bf.append(arrOfStr[i]);
			System.out.printf(bf.reverse()+" ");
		}
		
	}
	
		/***   ARRAYS  - FIND BOOLSEYE	***/
	public static void boolsEye(int [] given, int [] target) {
		HashSet<Integer> s = new LinkedHashSet<Integer>();
		int bools =0;
		int almost =0;
		
		for (int i = 0; i < target.length; i++)
		{
				s.add(target[i]);
		}
		for (int i = 0; i < target.length; i++) {
			if (target[i]==given[i]) {
				bools++;
			}else if (s.contains(given[i])) 
			{
				almost++;
			} 
		}
		System.out.println("number of boolseye: "+bools);
		System.out.println("number of almosts: "+almost);

	}
	
	public static void main(String[] args) {

		//String original = "develop the landscape";
		//System.out.println(""+isArmstrongNum(153));
		int [] news  = {1,5,1,6,3,2,6,5,1,4,2};
		int [] tars  = {1,4,5,6,3,9,6,7,1,7,2};
		boolsEye(news,tars);
		//String s = "we want everything";
		//reverseStringInternaly(s);
		//isInteger(s);
		//isBinary(n); 
//		ArrayList<Character> send = new ArrayList<>();
//		send.add('1');
//		send.add('5');
//		send.add('1');
//		send.add('6');
//		send.add('3');
//		send.add('2');
//		send.add('3');
//		send.add('1');
//		send.add('7');
//		send.add('6');
//		send.add('c');
//		send.add('t');
//		send.add('c');
//		removeDuplicates(send);
		//subArraySum(news,11);
		//findPairs(news,6);
		//char [] news  = {'1','5','1','6','3','2','6','5','1','4','2','c','c','t'};
		//countAccurances(news);
		//System.out.println(digitSum(2223));
		//findDuplicates(news);
		//removeWhite(original);
		//System.out.println(reverse(original));
	}

}
