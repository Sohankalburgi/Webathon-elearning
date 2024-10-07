package com.elearning.Elearning.Services;

import com.elearning.Elearning.Classes.LoginRequest;
import com.elearning.Elearning.Classes.ResponseMessage;
import com.elearning.Elearning.Entities.User;
import com.elearning.Elearning.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public Long getId(String email) {
        User id = userRepository.findByEmail(email);
        if(id!=null) {
            return id.getId();
        }
        else{
            return -1l;
        }

    }

    public String getRole(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        
        if(user.isPresent()){
            return user.get().getRole();
        }
        else{
            return null;
        }
    }
}
