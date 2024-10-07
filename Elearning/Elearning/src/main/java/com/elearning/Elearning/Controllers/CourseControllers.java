package com.elearning.Elearning.Controllers;

import com.elearning.Elearning.Classes.ResponseMessage;
import com.elearning.Elearning.Entities.Course;
import com.elearning.Elearning.Entities.CourseStatus;
import com.elearning.Elearning.Entities.Video;
import com.elearning.Elearning.Repositories.CourseRepository;
import com.elearning.Elearning.Services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
public class CourseControllers {

    @Autowired
    private CourseService courseService;

    @GetMapping("/course/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable Long id){
        try{
            Course course = courseService.getCourseById(id);
            return new ResponseEntity<>(course, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getImage/{id}")
    public ResponseEntity<ResponseMessage> getImage(@PathVariable Long id) throws Exception {
        // retrieve the image from the database or file system
        String imageBytes = courseService.getCourseById(id).getThumbnail();

        return new ResponseEntity<ResponseMessage>(new ResponseMessage(imageBytes),HttpStatus.OK);
    }


    @GetMapping("/allcourse/{userId}")
    public List<Course> getAllcourse(@PathVariable Long userId){
       return courseService.getAllCoursesById(userId);
    }

    @GetMapping("/getallcourse/{search}")
    public List<Course> getAllcoursebysearch(@PathVariable String search){
        return courseService.getAllcoursebySearch(search);
    }


    @PostMapping(value = "/course/{id}/uploadvideofile",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadVideoFile(@PathVariable Long id, @RequestPart MultipartFile file){
        try{
            courseService.uploadVideo(id,file);
            return new ResponseEntity<>(new ResponseMessage("File Uploaded"),HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(new ResponseMessage(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/course/{id}/downloadvideo")
    public ResponseEntity<?> downloadFile(@PathVariable Long id){
        try{
            List<Video> video = courseService.downloadAllFile(id);
            return new ResponseEntity(video,HttpStatus.FOUND);
        }catch (Exception e){
            return new ResponseEntity(new ResponseMessage(e.getMessage()),HttpStatus.NOT_FOUND);
        }
    }


}
