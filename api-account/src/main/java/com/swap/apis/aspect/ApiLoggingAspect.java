package com.swap.apis.aspect;

import static com.swap.apis.exception.ApisExceptionType.INTERNAL_SERVER_ERROR;
import static com.swap.apis.util.ServletUtils.getClientIp;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.util.Strings;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.swap.apis.exception.ApisException;
import com.swap.apis.util.ApiLog;
import com.swap.apis.util.ProcessTimer;
import com.swap.apis.util.RequestContext;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
@Aspect
@RequiredArgsConstructor
public class ApiLoggingAspect {

	ObjectMapper objectMapper = new ObjectMapper();

	@Around("execution(* com.swap.apis.controller..*(..))")
	public Object logApiMetrics(ProceedingJoinPoint joinPoint) {
		
		log.debug("> logApiMetrics(joinPoint=ProceedingJoinPoint(-)");
		
		ProcessTimer processTimer = new ProcessTimer();
		HttpServletRequest httpServletRequest = null;
		ApisException apisException = null;
		String clientIp = null;
		String keyName = null;
		String args = Arrays.stream(joinPoint.getArgs()).map(this::convertString).collect(Collectors.joining(","));
		String result = null;
		Object resultObj = null;
		String apiUrl = null;
		
		try {
			httpServletRequest = getHttpServletRequest();
			apiUrl = (String) httpServletRequest.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
			clientIp = getClientIp(httpServletRequest);
			RequestContext.setCaller(clientIp);
			resultObj = joinPoint.proceed();
			result = convertString(resultObj);
		} catch (ApisException e) {
			apisException = e;
		} catch (Throwable t) {
			apisException = new ApisException(INTERNAL_SERVER_ERROR, t.toString());
		}
		
		processTimer.end();
		ApiLog apiLog = ApiLog.builder().apiUrl(apiUrl).args(args).result(result)
				.clientIp(clientIp)
				.exception(Optional.ofNullable(apisException)
						.map(exception -> exception.getMessage() + " | " + exception.getClass().toString())
						.orElse(null))
				.accessTime(processTimer.getAccessTime()).processingTime(processTimer.getProcessTimeNano()).build();


		if (apisException != null) {
			throw apisException;
		}

		RequestContext.clear();
		return resultObj;
	}

	private HttpServletRequest getHttpServletRequest() {
		return Optional.of(((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest())
				.orElseThrow(() -> new ApisException(INTERNAL_SERVER_ERROR, "failed to get ServletRequest"));
	}

	private String convertString(Object object) {
		try {
			return objectMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			return Strings.EMPTY;
		}
	}

}