package com.elearning.Elearning.Repositories;

import com.elearning.Elearning.Entities.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MentorRepository extends JpaRepository<Mentor,Long> {
    @Query("SELECT m FROM Mentor m WHERE m.user.id = :userId")
    Mentor findByuserId(@Param("userId")Long userId);
}
