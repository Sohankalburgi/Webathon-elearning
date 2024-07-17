package com.elearning.Elearning.Repositories;

import com.elearning.Elearning.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    @Query("SELECT s FROM Student s WHERE s.user.id = :userId")
    Student findAllByUserId(@Param("userId") Long userId);
}
