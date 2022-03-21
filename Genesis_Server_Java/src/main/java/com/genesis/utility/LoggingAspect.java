package com.genesis.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	
	private Logger logger=LogManager.getLogger(this.getClass());
	
	
	// DO NOT CHANGE METHOD SIGNATURE
	// DO NOT DELETE/COMMENT METHOD
	@AfterThrowing(pointcut="execution(* com.infy.dao.*Impl.*(..))", throwing="exception")
	public void logDAOException(Exception exception) throws Exception {
		logger.error(exception.getMessage(),exception);
	}

	// DO NOT CHANGE METHOD SIGNATURE
	// DO NOT DELETE/COMMENT METHOD
	@AfterThrowing(pointcut="execution(* com.infy.service.*Impl.*(..))", throwing="exception")
	public void logServiceException(Exception exception) throws Exception {
		logger.error(exception.getMessage(),exception);
	}

}
