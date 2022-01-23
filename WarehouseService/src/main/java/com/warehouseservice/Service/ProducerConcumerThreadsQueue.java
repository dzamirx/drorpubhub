package com.warehouseservice.Service;

import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.scheduling.config.Task;
import org.springframework.security.web.header.writers.frameoptions.StaticAllowFromStrategy;

public class ProducerConcumerThreadsQueue 
{

	private static BlockingQueue<Integer> queueim = new ArrayBlockingQueue<Integer>(10);
	
	private static void producer() throws InterruptedException 
	{
		Random rand = new Random();
		
		while (true)
		{
			queueim.put(rand.nextInt(100));
		}
	}
	
	private static void concumer() throws InterruptedException 
	{
		Random rand = new Random();
		
		while (true)
		{
			Thread.sleep(100);
			if (rand.nextInt(10)==0) 
			{
				Integer i = queueim.take();
				System.out.println("Taken value: "+ i +" from the queue, queue curent size is "+ queueim.size());
			}
		}
	}
	
	
	public static void main(String[] args) throws InterruptedException 
	{
		Thread t1 = new Thread(new Runnable() 
		{
			@Override
			public void run() 
			{
				try {
					producer();
				} catch (InterruptedException e) {e.printStackTrace();}				
			}
		});
		
		Thread t2 = new Thread(new Runnable() 
		{
			@Override
			public void run() 
			{
				try {
					concumer();
				} catch (InterruptedException e) {e.printStackTrace();}				
			}
		});
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
		
		
		
		/****standard FUTURE callable implementation ***/
		
		ExecutorService cpu_service = Executors.newFixedThreadPool(4);//as the number of CPU cores available in the machine for CPU-bounded threads
		ExecutorService io_service = Executors.newCachedThreadPool();//for outer memory fetching threads
		
		Future<Integer> future = cpu_service.submit(new Task());
		
		Integer res;
		try {
			res = future.get();//blocking
			System.out.println("Result is: "+res);
			
		} catch (InterruptedException e) {	
			e.printStackTrace();
		} catch (ExecutionException e) {	
			e.printStackTrace();
		}
		
		/**** Completable FUTURE callable implementation ***/
		
		for (int i = 0; i < args.length; i++) 
		{
			CompletableFuture<Integer>completableFuture = new CompletableFuture<>();
			
			completableFuture.supplyAsync(() ->getOrder())
			.thenApplyAsync(order ->enrich(order,cpu_service))
			.thenApplyAsync(order ->payment(order,io_service))
			.thenAccept(order ->sendMail(order));		
		}
		
	
	}
	
	

	static class Task implements Callable<Integer>
	{

		@Override
		public Integer call() throws Exception {
			Thread.sleep(1000);
			return 6;
		}
		
	}
	
	private static Object sendMail(Object order) {
		// TODO Auto-generated method stub
		return null;
	}

	private static Object payment(Object order, ExecutorService io_service) {
		// TODO Auto-generated method stub
		return null;
	}

	private static Object enrich(Object order, ExecutorService cpu_service) {
		// TODO Auto-generated method stub
		return null;
	}

	private static Object getOrder() {
		// TODO Auto-generated method stub
		return null;
	}
}
