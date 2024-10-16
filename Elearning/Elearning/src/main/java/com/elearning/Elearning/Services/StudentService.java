package com.elearning.Elearning.Services;

import com.elearning.Elearning.Entities.Student;
import com.elearning.Elearning.Entities.User;
import com.elearning.Elearning.Repositories.StudentRepository;
import com.elearning.Elearning.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserRepository userRepository;

    public void createStudent(String id, Student student) throws Exception {

            User user = userRepository.findByEmail(id);
            if (user!=null) {
                student.setUser(user);
                studentRepository.save(student);
            }
            else{
                throw new Exception("User Not Found OR ERROR");
            }

    }

    public Student getOneStudentById(Long id)throws Exception {
        Optional<Student> student = studentRepository.findById(id);
        if(student.isPresent()){
            return student.get();
        }
        else{
            throw new Exception("Student Not Found");
        }
    }
}
