package com.elearning.Elearning.Services;

import com.elearning.Elearning.Entities.Course;
import com.elearning.Elearning.Repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;


    public Course getCourseById(Long id) throws  Exception{
        Optional<Course> course = courseRepository.findById(id);
        if(course.isPresent()){
            return course.get();
        }
        else{
            throw new Exception("Course Not Found");
        }
    }
}
