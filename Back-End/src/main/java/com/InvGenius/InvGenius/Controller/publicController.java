package com.InvGenius.InvGenius.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/api/v1/public")
public class publicController {
    

    @GetMapping("/")
    public String home() {

        return "Este es un end point (publico)";
    }
    
}
