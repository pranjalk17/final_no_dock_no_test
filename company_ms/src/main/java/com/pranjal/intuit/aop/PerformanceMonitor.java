package com.pranjal.intuit.aop;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PerformanceMonitor {

	public static final Logger LOGGER=LoggerFactory.getLogger(PerformanceMonitor.class);
	
	
	
	@Around("execution (* com.pranjal.intuit.companyms.CompanyServiceImpl.getCompanyById(..))")
	public Object monitorTime(ProceedingJoinPoint jp) throws Throwable {
		
		long start=System.currentTimeMillis();
		
		Object obj= jp.proceed();
		long end=System.currentTimeMillis();
		
		LOGGER.info("Time taken by: "+jp.getSignature().getName()+" "+(end-start)+" ms");
		return obj;
	}
}
