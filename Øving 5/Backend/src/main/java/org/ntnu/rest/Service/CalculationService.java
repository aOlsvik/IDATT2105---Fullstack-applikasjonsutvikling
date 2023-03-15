package org.ntnu.rest.Service;

import org.ntnu.rest.Entity.Calculation;
import org.ntnu.rest.Entity.User;
import org.ntnu.rest.Model.Equation;
import org.ntnu.rest.Repository.CalculationRepository;
import org.ntnu.rest.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CalculationService {

    @Autowired
    private CalculationRepository calculationRepository;
    @Autowired
    private UserRepository userRepository;

    public void save(Equation equation, User user) {
        Calculation calculation = new Calculation();
        calculation.setCalculation(equation.getEquation());

        calculation.setUser(user);
        this.calculationRepository.save(calculation);
    }

    public List<String> getCalculations(String username, int pageNumber){
        User user = userRepository.findById(username).get();
        Page<Calculation> calculations = calculationRepository.findByUserOrderByIdDesc(user, PageRequest.of(pageNumber, 10));
        List<String> list = calculations
                .getContent()
                .stream()
                .map(Calculation::getCalculation)
                .toList();
        return list;
    }
}
