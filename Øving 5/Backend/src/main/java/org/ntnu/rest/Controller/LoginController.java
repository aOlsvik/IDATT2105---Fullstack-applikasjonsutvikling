package org.ntnu.rest.Controller;

import org.ntnu.rest.Entity.User;
import org.ntnu.rest.Model.UserLogin;
import org.ntnu.rest.Repository.UserRepository;
import org.ntnu.rest.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/createUser")
    @ResponseBody
    public boolean createUser(@RequestBody UserLogin login){
        return userService.createUser(login);
    }

    @PostMapping("/login")
    public boolean getLogin(@RequestBody UserLogin login){
        return userService.login(login);
    }

}
