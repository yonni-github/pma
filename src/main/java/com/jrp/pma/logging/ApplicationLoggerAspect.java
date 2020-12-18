package com.jrp.pma.logging;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect //crosscut concern
@Component
public class ApplicationLoggerAspect {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Pointcut("within(com.jrp.pma.controllers..*)") //which methods to log
	public void definePackagePointcuts(){
		
	}
	
	@Around("definePackagePointcuts()") //advice annotation [when and what to log]
	public Object logBefore(ProceedingJoinPoint jp) {
		log.debug("\n--------------Before Method execution--------------------------");
		log.debug("\n{}, {} () with args = {}",
				jp.getSignature().getDeclaringTypeName(),
				jp.getSignature().getName(), Arrays.toString(jp.getArgs()));
		log.debug("\n----------------------------------------------------------------");
		
		Object o = null;
		
		try {
			o = jp.proceed();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		log.debug("\n--------------After Method execution--------------------------");
		log.debug("\n{}, {} () with args = {}",
				jp.getSignature().getDeclaringTypeName(),
				jp.getSignature().getName(), Arrays.toString(jp.getArgs()));
		log.debug("\n----------------------------------------------------------------");
		
		return o;
	}
}
