package app.settings;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class MyAspect {
private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Before("execution(* app.*.*(..))")
	public void logger(JoinPoint joinPoint) {
		logger.info("Method: " + joinPoint.getSignature());
		logger.info("Params: " + Arrays.toString(joinPoint.getArgs()));
	}
//	@Around("execution(* cz.vsb.vea2019.vea2019cv3.*.*(..))")
	public Object test(ProceedingJoinPoint joinPoint) throws Throwable {
		logger.info("Method: " + joinPoint.getSignature());
		logger.info("Params: " + Arrays.toString(joinPoint.getArgs()));
			return joinPoint.proceed();
	}
}
