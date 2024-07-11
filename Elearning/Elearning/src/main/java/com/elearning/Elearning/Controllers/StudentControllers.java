package com.elearning.Elearning.Controllers;

import com.elearning.Elearning.Classes.ResponseMessage;
import com.elearning.Elearning.Entities.Student;
import com.elearning.Elearning.Entities.User;
import com.elearning.Elearning.Services.StudentService;
import com.elearning.Elearning.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentControllers {

    @Autowired
    private StudentService studentService;
    @Autowired
    private UserService userService;

    @PostMapping("/user/{id}/student/new")
    public ResponseEntity<ResponseMessage> createStudent(@PathVariable Long id,
                                                         @RequestBody Student student){
        try{
            studentService.createStudent(id,student);
            return new ResponseEntity<>(new ResponseMessage("Created"), HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(new ResponseMessage(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/student/{id}")
    public  ResponseEntity<?> getOneStudentById(@PathVariable Long id){
        try{
            Student student = studentService.getOneStudentById(id);
            return new ResponseEntity<>(student,HttpStatus.FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(new ResponseMessage(e.getMessage()),HttpStatus.NOT_FOUND);
        }
    }


}
