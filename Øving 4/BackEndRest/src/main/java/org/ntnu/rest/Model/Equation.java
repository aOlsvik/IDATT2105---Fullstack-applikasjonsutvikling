package org.ntnu.rest.Model;

import org.ntnu.rest.Service.SolverService;
import org.springframework.beans.factory.annotation.Autowired;

public class Equation {
    private String equation;


    public Equation(){}

    public void setEquation(String equation) {
        this.equation = equation;
    }

    public String getEquation() {
        return equation;
    }


}
