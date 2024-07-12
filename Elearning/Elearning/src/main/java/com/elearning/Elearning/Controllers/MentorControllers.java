package com.elearning.Elearning.Controllers;

import com.elearning.Elearning.Classes.ResponseMessage;
import com.elearning.Elearning.Entities.Course;
import com.elearning.Elearning.Entities.Mentor;
import com.elearning.Elearning.Services.MentorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class MentorControllers {

    @Autowired
    private MentorService mentorService;

    @PostMapping("/user/{id}/mentor/new")
    public ResponseEntity<ResponseMessage> createMentor(@PathVariable Long id,
                                                        @RequestBody Mentor mentor){
        try{
            mentorService.createMentor(id,mentor);
            return  new ResponseEntity<>(new ResponseMessage("Created"), HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(new ResponseMessage(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/mentor/{id}")
    public ResponseEntity<?> getOneMentorById(@PathVariable Long id){
        try{
            Mentor mentor = mentorService.getOneMentorById(id);
            return new ResponseEntity<>(mentor,HttpStatus.FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(new ResponseMessage(e.getMessage()),HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/mentor/{id}/addCourse")
    public  ResponseEntity<ResponseMessage> addCoursebyMentor(@PathVariable Long id,
                                                              @RequestBody Course course, @RequestPart MultipartFile file){
        try{
            System.out.println(course.toString());
            course.setThumbnail(file.getBytes());
            mentorService.addCourse(id,course);
            return new ResponseEntity<>(new ResponseMessage("Created"),HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(new ResponseMessage(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
