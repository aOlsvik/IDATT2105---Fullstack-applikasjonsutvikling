package org.ntnu.rest.Model;

public class Equation {
    private String equation;

    public Equation(String equation){
        this.equation = equation;
    }

    public Equation(){};

    public void setEquation(String equation) {
        this.equation = equation;
    }

    public String getEquation() {
        return equation;
    }

    public String solve(){
        return "";
    }


}
