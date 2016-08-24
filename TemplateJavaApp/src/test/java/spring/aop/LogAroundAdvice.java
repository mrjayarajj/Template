package spring.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class LogAroundAdvice implements MethodInterceptor {

	public Object invoke(MethodInvocation method) throws Throwable {
		System.out.println("---------------->");
		Object val = method.proceed();
		System.out.print("<-----------------");
		return val;
	}
 
}