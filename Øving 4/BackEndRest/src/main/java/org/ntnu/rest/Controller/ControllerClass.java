package org.ntnu.rest.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:5000")
public class ControllerClass {

    @GetMapping("/")
    public String testConnection(){
        return "Success";
    }

    @GetMapping("/plus")
    public String plus(){
        return "Success";
    }

    @GetMapping("/minus")
    public String minus(){
        return "Success";
    }

    @GetMapping("/multiplication")
    public String multiplication(){
        return "Success";
    }

    @GetMapping("/division")
    public String division(){
        return "Success";
    }

}
