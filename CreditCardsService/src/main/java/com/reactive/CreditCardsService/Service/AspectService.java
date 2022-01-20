package com.reactive.CreditCardsService.Service;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import com.reactive.CreditCardsService.Entity.Customer;

import reactor.core.publisher.Flux;

//before/after/around/exception/ advice
//point cut - where or when do we want to call a before/after method = insertion point

@Aspect
@Component
@EnableAspectJAutoProxy
public class AspectService 
{
	
	Logger log = LoggerFactory.getLogger(AspectService.class);

	@Autowired
	private DaoService serv;//A Bean for the below BEFORE advice
	
		//@Before("execution(* com.reactive.CreditCardsService.Service.*.*(..))")-->Before Advice is applied on all methods of all classes in a package.
		//@Before("execution(* com.amitph.spring.aop.service.DaoService.getAllCustomers(..))")
		@Before("execution(* com.reactive.CreditCardsService.Service.DaoService.*(..))")//PointCut for a Before advice on an entire class 
		private void preLog() 
	  	{
			log.debug("The Data Service provider had been reached");
		}
		
		private void security() 
		{
			// TODO Auto-generated method stub
			log.debug("security service started");
		}
		
		private void transaction() 
		{
			// TODO Auto-generated method stub
			log.debug("transaction service started");
		}
		
	
	
}
