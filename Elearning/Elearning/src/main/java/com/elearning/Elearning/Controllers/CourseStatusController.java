package com.elearning.Elearning.Controllers;

import com.elearning.Elearning.Classes.ResponseMessage;
import com.elearning.Elearning.Entities.CourseStatus;
import com.elearning.Elearning.Services.CourseStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
public class CourseStatusController {

    @Autowired
    private CourseStatusService courseStatusService;

    @PostMapping("/student/{id}/courseregister/{courseId}")
    public ResponseEntity<ResponseMessage> registercourseforstudent(@PathVariable Long id, @PathVariable Long courseId){
        try{
            courseStatusService.registerCourseByStudent(id,courseId);
            return new ResponseEntity<>(new ResponseMessage("Created"), HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(new ResponseMessage(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/student/{id}/getAllregisteredCourses")
    public  ResponseEntity<?> getAllregisteredCourseBystudentId(@PathVariable Long id){
        try{
            List<CourseStatus> courses = courseStatusService.getAllCoursesregistered(id);
            return new ResponseEntity<>(courses,HttpStatus.FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(new ResponseMessage(e.getMessage()),HttpStatus.NOT_FOUND);
        }
    }



}
