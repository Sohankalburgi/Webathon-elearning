package com.elearning.Elearning.Classes;

import com.elearning.Elearning.Entities.Mentor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CourseHelper {
    private Long courseId;

    private String coursename;


    private String coursedescription;


    private String courseduration;


    private Mentor mentor;

    private byte[] thumbnail;
}
