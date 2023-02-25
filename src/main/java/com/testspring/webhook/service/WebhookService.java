package com.testspring.webhook.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
}
