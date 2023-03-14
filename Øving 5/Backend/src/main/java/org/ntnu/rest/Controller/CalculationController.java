package org.ntnu.rest.Controller;

import org.ntnu.rest.Model.Equation;
import org.ntnu.rest.Service.CalculationService;
import org.ntnu.rest.Service.SolverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin(origins = "*")
public class CalculationController {

    @Autowired
    private SolverService solverService;
    @Autowired
    private CalculationService calculationService;


    @PostMapping("/plus")
    @ResponseBody
    public Equation plus(@RequestBody Equation equation){
        equation.setOperator('+');
        this.solverService.solve(equation);
        return equation;
    }

    @PostMapping("/minus")
    @ResponseBody
    public Equation minus(@RequestBody Equation equation){
        equation.setOperator('-');
        this.solverService.solve(equation);
        return equation;
    }

    @PostMapping("/multiplication")
    @ResponseBody
    public Equation multiplication(@RequestBody Equation equation){
        equation.setOperator('*');
        this.solverService.solve(equation);
        return equation;
    }

    @PostMapping("/division")
    @ResponseBody
    public Equation division(@RequestBody Equation equation){
        equation.setOperator('/');
        this.solverService.solve(equation);
        return equation;
    }

    @GetMapping("/calculations/{username}")
    public ArrayList<String> getCalculations(@PathVariable String username){
        return calculationService.getCalculations(username);
    }

}
