package com.testspring.webhook.controller;

import com.testspring.webhook.service.WebhookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
            if(requestBody.containsKey("execute")){
                Map<String, Object> executeBody = (Map<String, Object>) requestBody.get("execute");
                executeBody.forEach((key, value) -> executeBody.put(key, service.getMethod(value.toString())));
                requestBody.put("execute", executeBody);
            }
            responseBody.put("content", requestBody);
        }
        return responseBody;
    }
}
