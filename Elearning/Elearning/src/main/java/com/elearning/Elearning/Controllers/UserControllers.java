package com.elearning.Elearning.Controllers;

import com.elearning.Elearning.Classes.LoginRequest;
import com.elearning.Elearning.Classes.ResponseMessage;
import com.elearning.Elearning.Entities.User;
import com.elearning.Elearning.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/user")
public class UserControllers {

    @Autowired
    private UserService userService;

    @PostMapping("/registerUser")
    public ResponseEntity<ResponseMessage> registerUser(@RequestBody User user){
        try{
            System.out.println(user.toString());
            userService.createUser(user);
            return new ResponseEntity<>(new ResponseMessage("Created"),HttpStatus.CREATED);
        }
        catch (Exception error){

            return new ResponseEntity<>(new ResponseMessage(error.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/login")
    public User loginUser(@RequestBody LoginRequest loginRequest){
        return userService.LoginAuth(loginRequest);
    }

    @GetMapping("/checkUserExists/{email}")
    public boolean checkUserExists(@PathVariable String email){
        return userService.checkAlreadyExist(email);
    }


}
