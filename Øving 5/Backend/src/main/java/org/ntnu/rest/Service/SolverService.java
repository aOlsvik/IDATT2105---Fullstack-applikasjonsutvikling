package org.ntnu.rest.Service;

import org.ntnu.rest.Entity.User;
import org.ntnu.rest.Model.Equation;
import org.ntnu.rest.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class SolverService {

    @Autowired
    private CalculationService calculationService;
    @Autowired
    private UserRepository userRepository;

    Logger logger = LoggerFactory.getLogger(SolverService.class);
    public void solve(Equation equation){
        logger.info("Received calculation: " + equation.getEquation());
        switch (equation.getOperator()) {
            case '+' -> equation.setAnswer(Double.parseDouble(equation.getNum1()) + Double.parseDouble(equation.getNum2()));
            case '-' -> equation.setAnswer(Double.parseDouble(equation.getNum1()) - Double.parseDouble(equation.getNum2()));
            case '*' -> equation.setAnswer(Double.parseDouble(equation.getNum1()) * Double.parseDouble(equation.getNum2()));
            case '/' -> {
                if (equation.getNum2().equals("0")) throw new ArithmeticException("Division by zero");
                equation.setAnswer(Double.parseDouble(equation.getNum1()) / Double.parseDouble(equation.getNum2()));
            }
        }
        logger.info("Calculated equation with result of: " + equation.getAnswer());
        save(equation);
    }

    private void save(Equation equation){
        User user = userRepository.findById(equation.getUsername()).get();
        calculationService.save(equation,user);
    }
}
