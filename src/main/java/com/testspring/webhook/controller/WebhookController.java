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

    @RequestMapping(value="/echo", produces = MediaType.ALL_VALUE)
    public Map<String, Object> hook(@RequestBody Map<String, Object> requestBody){
        HashMap<String, Object> responseBody = new HashMap<>();
        for (var key : requestBody.keySet()){
            responseBody.put(key, service.getMethod(requestBody.get(key).toString()));
        }
        return responseBody;
    }
}
