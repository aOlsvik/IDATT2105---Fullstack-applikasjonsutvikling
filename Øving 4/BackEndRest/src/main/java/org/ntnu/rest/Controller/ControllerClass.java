package org.ntnu.rest.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://10.22.7.100:8000/")
public class ControllerClass {

    @GetMapping("/")
    public String testConnection(){
        return "Success";
    }

}
