package com.elearning.Elearning.Controllers;

import com.elearning.Elearning.Classes.CourseHelper;
import com.elearning.Elearning.Classes.FileUtils;
import com.elearning.Elearning.Classes.ResponseMessage;
import com.elearning.Elearning.Entities.Course;
import com.elearning.Elearning.Entities.Mentor;
import com.elearning.Elearning.Entities.Video;
import com.elearning.Elearning.Services.MentorService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@RestController
@CrossOrigin("http://localhost:4200")
public class MentorControllers {

    @Autowired
    private MentorService mentorService;

    @PostMapping("/user/{emailid}/mentor/new")
    public ResponseEntity<ResponseMessage> createMentor(@PathVariable String emailid,
                                                        @RequestBody Mentor mentor){
        try{
            mentorService.createMentor(emailid,mentor);
            return  new ResponseEntity<>(new ResponseMessage("Created"), HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(new ResponseMessage(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/mentor/{id}")
    public Mentor getOneMentorById(@PathVariable Long id){
        try{
            Mentor mentor = mentorService.getOneMentorById(id);

            return  mentor;
        }
        catch (Exception e){
            return null;
        }
    }

    @PostMapping("/mentor/{id}/addcourse")
    public ResponseEntity<?> addcourse(@PathVariable Long id,@RequestPart("coursename") String coursename,
                                       @RequestPart("coursedescription") String coursedescription,
                                       @RequestPart("courseduration") String courseduration,
                                       @RequestPart("thumbnail") MultipartFile thumbnail,
                                       @RequestPart("video") MultipartFile video, HttpServletRequest request) throws IOException, ServletException {
        System.out.println("Request parts: " + Collections.list(request.getParameterNames()));
        System.out.println("Thumbnail part: " + request.getPart("thumbnail"));
        mentorService.createCourse(id,coursename,coursedescription,courseduration,thumbnail,video);
        return new ResponseEntity<>(new ResponseMessage("Created"),HttpStatus.CREATED);
    }

}
