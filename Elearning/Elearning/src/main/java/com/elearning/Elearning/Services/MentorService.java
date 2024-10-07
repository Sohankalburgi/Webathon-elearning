package com.elearning.Elearning.Services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.elearning.Elearning.Classes.FileUtils;
import com.elearning.Elearning.Entities.Course;
import com.elearning.Elearning.Entities.Mentor;
import com.elearning.Elearning.Entities.User;
import com.elearning.Elearning.Entities.Video;
import com.elearning.Elearning.Repositories.CourseRepository;
import com.elearning.Elearning.Repositories.MentorRepository;
import com.elearning.Elearning.Repositories.UserRepository;
import com.elearning.Elearning.Repositories.VideoRepository;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@Service
public class MentorService {

    Dotenv dotenv = Dotenv.load();

    @Autowired
    private MentorRepository mentorRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private VideoRepository videoRepository;

    public void createMentor(String id, Mentor mentor) throws Exception{
        User user = userRepository.findByEmail(id);
        if(user!=null){
            mentor.setUser(user);
            mentorRepository.save(mentor);
        }else {
            throw new Exception("User Not Found OR Error");
        }
    }

    public Mentor getOneMentorById(Long id) throws  Exception{
        Mentor mentor = mentorRepository.findByuserId(id);


        if(mentor!=null){

            return mentor;
        }
        else{
            throw new Exception("Mentor Not Found");
        }
    }

    public Mentor addCourse(Long id, Course course) throws Exception {
        Optional<Mentor> mentor = mentorRepository.findById(id);
        if(mentor.isPresent()){
            course.setMentor(mentor.get());
            courseRepository.save(course);
            Mentor mentor1 = mentorRepository.getById(id);

            return mentor1 ;
        }
        else{
            throw new Exception("Mentor Not found or Error");
        }
    }

    public void createCourse(Long id, String coursename, String coursedescription, String courseduration, MultipartFile thumbnail, MultipartFile video) throws IOException {
        Mentor mentor = mentorRepository.findByuserId(id);

        Cloudinary cloudinary = new Cloudinary(dotenv.get("CLOUDINARY_URL"));
        Video addvideo = new Video();
        Course course = new Course();

        course.setCoursename(coursename);
        course.setCoursedescription(coursedescription);
        course.setCourseduration(courseduration);
        Map responseMapThumbnail = cloudinary.uploader().upload(thumbnail.getBytes(),ObjectUtils.emptyMap());
        course.setThumbnail(responseMapThumbnail.get("url").toString());
        course.setMentor(mentor);

        Course updatedCourse = courseRepository.saveAndFlush(course);

        addvideo.setFilename(video.getOriginalFilename());


        Map responseMap = cloudinary.uploader().upload(video.getBytes(), ObjectUtils.asMap("resource_type", "video"));
        String videoUploadURL = responseMap.get("url").toString();
        addvideo.setFileURL(videoUploadURL);
        addvideo.setCourse(updatedCourse);

        videoRepository.save(addvideo);

    }

//    public boolean setImage(Long id, Long courseId) {
//        Optional<Course> course = courseRepository.findById(id);
//        if(mentor.isPresent()){
//            course.get().setThumbnail();
//            courseRepository.save(course);
//        }
//        else{
//            throw new Exception("Mentor Not found or Error");
//        }
//    }
}
