package com.elearning.Elearning.Services;

import com.elearning.Elearning.Entities.Course;
import com.elearning.Elearning.Entities.CourseStatus;
import com.elearning.Elearning.Entities.Student;
import com.elearning.Elearning.Repositories.CourseRepository;
import com.elearning.Elearning.Repositories.CourseStatusRepository;
import com.elearning.Elearning.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseStatusService {

    @Autowired
    private CourseStatusRepository courseStatusRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    public void registerCourseByStudent(Long id, Long courseId) throws Exception{
        Optional<Student> student = studentRepository.findById(id);
        Optional<Course> course = courseRepository.findById(courseId);

        if(student.isPresent() && course.isPresent()){
            CourseStatus courseStatus = new CourseStatus();
            courseStatus.setStudent(student.get());
            courseStatus.setCourse(course.get());
            courseStatus.setStatus(0);
            courseStatusRepository.save(courseStatus);
        }else {
            throw new Exception("Error or Either student or course Not found");
        }
    }

    public List<CourseStatus> getAllCoursesregistered(Long id) throws Exception{
        List<CourseStatus> courses = courseStatusRepository.findAllByStudentId(id);
        if(!courses.isEmpty()){
            return courses;
        }
        else{
            throw new Exception("Not Found");
        }
    }
}
