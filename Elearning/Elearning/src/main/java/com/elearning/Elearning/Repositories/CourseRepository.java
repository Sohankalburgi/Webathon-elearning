package com.elearning.Elearning.Repositories;

import com.elearning.Elearning.Entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
    @Query("SELECT c FROM Course c WHERE c.mentor.id = :userId")
    Course findCoursesByUserId(Long userId);

    @Query("SELECT c FROM Course c WHERE LOWER(c.coursename) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Course> findByCourseNameContaining(@Param("keyword") String keyword);
}
