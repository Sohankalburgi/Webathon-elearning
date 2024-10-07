package com.elearning.Elearning.Controllers;

import com.elearning.Elearning.Classes.ResponseMessage;
import com.elearning.Elearning.Entities.CourseStatus;
import com.elearning.Elearning.Entities.VideoCompletion;
import com.elearning.Elearning.Services.CourseStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @PostMapping("/markVideo")
    public ResponseEntity<?> markVideo(@RequestParam Long studentId,@RequestParam Long courseId,@RequestParam Long videoId ){
        System.out.println("stud"+studentId+"courseId"+courseId+"vidoeId"+videoId);
        try{
            boolean mark = courseStatusService.markVideo(studentId,courseId,videoId);
            return new ResponseEntity<>(new ResponseMessage(mark? "Video marked Completed":"Video unmarked Completed"),HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(new ResponseMessage(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getmarkedstatus")
    public ResponseEntity<?> getMarkedStatus(@RequestParam Long studentId, @RequestParam Long courseId){
        System.out.println("FROM : getmarked STatus student Id "+studentId+" course ID "+courseId);
        try {
            return new ResponseEntity<>(courseStatusService.getmarkedstatus(studentId, courseId),HttpStatus.FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(new ResponseMessage(e.getMessage()),HttpStatus.NOT_FOUND);
        }
    }

}
