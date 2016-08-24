package com.baseframework.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class DoBeforeAspect {
	
	public DoBeforeAspect(){
		System.out.println("--------------------------------------------------------------------------->");
	}
	

	//@Before("execution(* com.baseframework.biz.security.access..*(..))")
	@Before("execution(* com.baseframework..*(..))")
	public void doBefore(JoinPoint joinPoint) {

		System.out.println("***AspectJ*** DoBefore() is running!! intercepted : " + joinPoint.getSignature().getName());
	}

}