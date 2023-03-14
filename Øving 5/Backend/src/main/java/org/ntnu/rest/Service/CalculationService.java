package org.ntnu.rest.Service;

import org.ntnu.rest.Entity.Calculation;
import org.ntnu.rest.Entity.User;
import org.ntnu.rest.Model.Equation;
import org.ntnu.rest.Repository.CalculationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CalculationService {

    @Autowired
    CalculationRepository calculationRepository;

    public void save(Equation equation, User user) {
        Calculation calculation = new Calculation();
        calculation.setCalculation(equation.getEquation());

        calculation.setUser(user);
        this.calculationRepository.save(calculation);
    }

    public ArrayList<String> getCalculations(String username){
        System.out.println(username);
        List<Calculation> calculations = calculationRepository.findAll();
        System.out.println(calculations.stream().map(Calculation::getCalculation).toList());
        List<String> userCalculations = calculations.stream()
                .filter(c -> c.getUser().getUsername().equals(username))
                .map(Calculation::getCalculation).toList();
        return new ArrayList<>(userCalculations);
    }
}
