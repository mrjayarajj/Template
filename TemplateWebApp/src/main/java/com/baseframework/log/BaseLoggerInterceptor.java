package com.baseframework.log;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseLoggerInterceptor implements MethodInterceptor {

	private static Logger LOG = LoggerFactory.getLogger(BaseLoggerInterceptor.class);

	public BaseLoggerInterceptor() {
		if (LOG.isDebugEnabled()) {
			LOG.debug("AOP Configured....");
		}
	}

	public Object invoke(MethodInvocation method) throws Throwable {
		if (LOG.isDebugEnabled()) {
			LOG.debug("--------------->" + method);
		}
		double start = System.currentTimeMillis();
		Object val = method.proceed();
		double end = System.currentTimeMillis();
		double duration = end - start;
		if (LOG.isDebugEnabled()) {
			LOG.debug("<---------------" + method + " took (" + duration + ") ms");
		}
		return val;
	}

}