package com.elearning.Elearning.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Name is Null")
    private String name;

    @NotNull(message = "Qualification is Null")
    private String highestqualification;

    @NotNull(message = "InstitueName is Null")
    private String institutename;

    @NotNull(message = "dob is Null")
    private String dateofbirth;

    @NotNull(message = "location is Null")
    private String location;

    @JsonIgnoreProperties
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "student")
    private List<CourseStatus> coursestatus;

    @OneToOne
    @JoinColumn(name = "userId")
    private User user;
}
