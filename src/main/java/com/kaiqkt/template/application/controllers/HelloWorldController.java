package com.kaiqkt.template.application.controllers;

import com.kaiqkt.template.generated.application.controllers.HelloApi;
import com.kaiqkt.template.generated.application.dto.HelloWorld;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController implements HelloApi {
    @Override
    public ResponseEntity<HelloWorld> helloWorld() {
        return ResponseEntity.ok(new HelloWorld("hello"));
    }
}
