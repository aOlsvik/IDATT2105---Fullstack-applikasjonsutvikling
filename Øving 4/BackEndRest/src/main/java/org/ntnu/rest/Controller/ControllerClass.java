package org.ntnu.rest.Controller;

import org.ntnu.rest.Model.Equation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5000")
public class ControllerClass {

    @GetMapping("/")
    public String testConnection(){
        System.out.println("Success");
        return "Success";
    }

    @PostMapping("/plus")
    public String plus(@RequestBody Equation equation){
        System.out.println(equation.getEquation());
        return "plus";
    }

    @PostMapping("/minus")
    public String minus(@RequestBody Equation equation){
        System.out.println(equation.getEquation());
        return "minus";
    }

    @PostMapping("/multiplication")
    public String multiplication(@RequestBody Equation equation){
        System.out.println(equation.getEquation());
        return "multiplication";
    }

    @PostMapping("/division")
    public String division(@RequestBody Equation equation){
        System.out.println(equation.getEquation());
        return "division";
    }

}
