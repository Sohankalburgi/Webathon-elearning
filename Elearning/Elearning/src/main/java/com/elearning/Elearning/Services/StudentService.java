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

    public void createStudent(Long id, Student student) throws Exception {

            Optional<User> user = userRepository.findById(id);
            if (user.isPresent()) {
                student.setUser(user.get());
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
