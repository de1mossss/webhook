package com.testspring.webhook.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Service
public record WebhookService(HttpServletRequest request) {
    public Object getMethod(String methodName){
        Method requestedMethod = null;
        Object methodResult = null;
        try {
            requestedMethod = request.getClass().getMethod(methodName);
            methodResult = requestedMethod.invoke(request);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return methodResult;
    }

    public Map<String, Object> getRequestData(){
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("method", request.getMethod());
        requestData.put("final_url", request.getRequestURL() + (request.getQueryString() == null ? "" : "?" + request.getQueryString()));
        Enumeration<String> requestHeaders = request.getHeaderNames();
        if(requestHeaders != null){
            Map<String, String> headersContainer = new HashMap<>();
            while (requestHeaders.hasMoreElements()){
                String headerElement = requestHeaders.nextElement();
                headersContainer.put(headerElement, request.getHeader(headerElement));
            }
            requestData.put("headers", headersContainer);
        }
        return requestData;
    }
}
