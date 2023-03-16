package org.ntnu.rest.Controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import org.ntnu.rest.Model.UserLogin;
import org.ntnu.rest.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;
import java.time.Instant;

@RestController
@CrossOrigin(origins = "*")
public class LoginController {

    @Autowired
    private UserService userService;

    private String keyStr="darwinnunezthegreatestofalltime";
    private static final Duration JWT_TOKEN_VALIDITY = Duration.ofMinutes(5);


    @PostMapping("/createUser")
    @ResponseBody
    public boolean createUser(@RequestBody UserLogin login){
        return userService.createUser(login);
    }

    @PostMapping("/login")
    public boolean getLogin(@RequestBody UserLogin login){
        return userService.login(login);
    }

    @PostMapping(value = "/token")
    @ResponseStatus(value = HttpStatus.CREATED)
    public String generateToken(final @RequestBody UserLogin loginRequest) throws Exception {
        // if username and password are valid, issue an access token
        // note that subsequent requests need this token
        if (userService.login(loginRequest)) {
            return generateToken(loginRequest.getUsername());
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Access denied, wrong credentials....");
    }

    private String generateToken(final String userId) {
        final Instant now = Instant.now();
        final Algorithm hmac512 = Algorithm.HMAC512(keyStr);;
        final JWTVerifier verifier = JWT.require(hmac512).build();
        return JWT.create()
                .withSubject(userId)
                .withIssuedAt(now)
                .withExpiresAt(now.plusMillis(JWT_TOKEN_VALIDITY.toMillis()))
                .sign(hmac512);
    }

}
