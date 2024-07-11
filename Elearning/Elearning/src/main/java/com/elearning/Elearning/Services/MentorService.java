package com.elearning.Elearning.Services;

import com.elearning.Elearning.Entities.Course;
import com.elearning.Elearning.Entities.Mentor;
import com.elearning.Elearning.Entities.User;
import com.elearning.Elearning.Repositories.CourseRepository;
import com.elearning.Elearning.Repositories.MentorRepository;
import com.elearning.Elearning.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MentorService {
    @Autowired
    private MentorRepository mentorRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    public void createMentor(Long id, Mentor mentor) throws Exception{
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            mentor.setUser(user.get());
            mentorRepository.save(mentor);
        }else {
            throw new Exception("User Not Found OR Error");
        }
    }

    public Mentor getOneMentorById(Long id) throws  Exception{
        Optional<Mentor> mentor = mentorRepository.findById(id);
        if(mentor.isPresent()){
            return mentor.get();
        }
        else{
            throw new Exception("Mentor Not Found");
        }
    }

    public void addCourse(Long id, Course course) throws Exception {
        Optional<Mentor> mentor = mentorRepository.findById(id);
        if(mentor.isPresent()){
            course.setMentor(mentor.get());
            courseRepository.save(course);
        }
        else{
            throw new Exception("Mentor Not found or Error");
        }
    }
}
