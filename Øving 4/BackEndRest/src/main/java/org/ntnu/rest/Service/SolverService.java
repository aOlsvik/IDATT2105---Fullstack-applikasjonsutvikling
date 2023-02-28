package org.ntnu.rest.Service;

import org.ntnu.rest.Model.Equation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class SolverService {

    Logger logger = LoggerFactory.getLogger(SolverService.class);

    public String solve(Equation equation){
        logger.info("Received calculation: " + equation.getEquation());
        String[] elements = new String[3];
        StringBuilder s=new StringBuilder();
        for(char c : equation.getEquation().trim().toCharArray()){
            if(Character.isDigit(c)){
                s.append(c);
            } else if (c == '/' || c == '*' || c == '+' || c == '-'){
                elements[0] = s.toString();
                s = new StringBuilder();
                elements[1] = String.valueOf(c);
            }
            else{
                return "Invalid calculation. " +
                        "Please enter a calculation on the form:" +
                        "1+1";
            }
        }
        elements[2] = s.toString();
        int answer = 0;
        String operator="";
        switch (elements[1]) {
            case "+" -> {
                operator = "+";
                answer = Integer.parseInt(elements[0]) + Integer.parseInt(elements[2]);
            }
            case "-" -> {
                operator = "-";
                answer = Integer.parseInt(elements[0]) - Integer.parseInt(elements[2]);
            }
            case "*" -> {
                operator = "*";
                answer = Integer.parseInt(elements[0]) * Integer.parseInt(elements[2]);
            }
            case "/" -> {
                operator = "/";
                if (elements[2].equals("0")) return "Cannot divide by zero";
                answer = Integer.parseInt(elements[0]) / Integer.parseInt(elements[2]);
            }
        }
        logger.info("Calculated equation with result of: " + answer);
        return elements[0] + operator + elements[2] + "=" + answer;
    }
}
