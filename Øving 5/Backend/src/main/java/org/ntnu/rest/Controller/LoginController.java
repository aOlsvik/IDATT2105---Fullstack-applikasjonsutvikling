package org.ntnu.rest.Controller;

import org.ntnu.rest.Model.UserLogin;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
public class LoginController {

    @PostMapping("/login")
    public boolean login(@RequestBody UserLogin login){


        return true;
    }

}
