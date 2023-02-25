package com.testspring.webhook.controller;

import com.testspring.webhook.service.WebhookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class WebhookController {
    @Autowired
    private WebhookService service;

    @RequestMapping(value="/echo/*")
    public Map<String, Object> hook(@RequestBody(required=false) Map<String, Object> requestBody){
        Map<String, Object> responseBody = new HashMap<>();
        responseBody = service.getRequestData();
        if(requestBody != null){
            responseBody.put("content", requestBody);
        }
        return responseBody;
    }
}
