package com.testspring.hello.restservice;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {
    private static final String template = "Hello, %s";
    private final AtomicLong counter = new AtomicLong();

    private final HttpServletRequest request;

    LinkedHashMap<String, String> map = new LinkedHashMap<>();

    public GreetingController(HttpServletRequest request) {
        this.request = request;
    }

//    @GetMapping("/greeting")
//    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name){
//        return new Greeting(counter.incrementAndGet(), String.format(template, name));
//    }

//    @RequestMapping(value="/greeting", produces = MediaType.ALL_VALUE)
//    public Greeting greeting(String name){
//        return new Greeting(counter.incrementAndGet(), String.format(template, name));
//    }

//    @RequestMapping(value="/greeting", method = RequestMethod.POST, produces = MediaType.ALL_VALUE)
//    public Greeting greeting(@RequestHeader("Content-Type")String contentType, @RequestBody Name name){
//        return new Greeting(counter.incrementAndGet(), request.getMethod());
//    }

    @RequestMapping(value="/echo", produces = MediaType.ALL_VALUE)
    public Map<String, Object> greeting(@RequestBody Map<String, Object> body) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        HashMap<String, Object> map = new HashMap<>();
        for (var key : body.keySet()){
            map.put(key, testGetMethod(body.get(key).toString()));
        }
        return map;
    }

    public Object testGetMethod(String methodName) throws SecurityException, NoSuchMethodException, IllegalArgumentException,
            IllegalAccessException, InvocationTargetException {
        Method m = request.getClass().getMethod(methodName);
        Object ret = m.invoke(request);
        return ret;
    }
}
