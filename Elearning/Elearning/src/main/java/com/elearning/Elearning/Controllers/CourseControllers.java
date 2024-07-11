package com.elearning.Elearning.Controllers;

import com.elearning.Elearning.Entities.Course;
import com.elearning.Elearning.Entities.CourseStatus;
import com.elearning.Elearning.Repositories.CourseRepository;
import com.elearning.Elearning.Services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseControllers {

    @Autowired
    private CourseService courseService;

    @GetMapping("/course/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable Long id){
        try{
            Course course = courseService.getCourseById(id);
            return new ResponseEntity<>(course, HttpStatus.FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }


}
