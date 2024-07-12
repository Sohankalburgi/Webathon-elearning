package com.elearning.Elearning.Services;

import com.elearning.Elearning.Classes.LoginRequest;
import com.elearning.Elearning.Entities.User;
import com.elearning.Elearning.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public void createUser(User user) {
        userRepository.save(user);

    }

    public User LoginAuth(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail());
        if(user!=null){
            if(user.getPassword().equals(loginRequest.getPassword())){
                return user;
            }
        }
        return null;
    }

    public boolean checkAlreadyExist(String email) {
        User user = userRepository.findByEmail(email);
        if(user!=null){
            return true;
        }
        return false;
    }
}
