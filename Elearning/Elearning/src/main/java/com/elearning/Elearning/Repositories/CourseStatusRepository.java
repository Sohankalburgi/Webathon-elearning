package com.elearning.Elearning.Repositories;

import com.elearning.Elearning.Entities.CourseStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseStatusRepository extends JpaRepository<CourseStatus,Long> {

    @Query("SELECT cs FROM CourseStatus cs WHERE cs.student.id = :id")
    List<CourseStatus> findAllByStudentId(Long id);
}
