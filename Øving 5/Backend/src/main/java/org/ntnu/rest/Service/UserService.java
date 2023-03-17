package org.ntnu.rest.Service;

import org.ntnu.rest.Entity.Calculation;
import org.ntnu.rest.Entity.User;
import org.ntnu.rest.Model.UserLogin;
import org.ntnu.rest.Repository.UserRepository;
import org.ntnu.rest.Security.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean createUser(UserLogin login){
        User user = new User();
        user.setUsername(login.getUsername());
        user.setPassword(PasswordUtils.encode(login.getPassword()));
        if(userRepository.findById(user.getUsername()).isPresent()){
            return false;
        }
        userRepository.save(user);
        return true;

    }

    public boolean login(UserLogin login){
        Optional<User> user = userRepository.findById(login.getUsername());
        return user.map(value -> PasswordUtils.matches(login.getPassword(), value.getPassword())).orElse(false);
    }

}
