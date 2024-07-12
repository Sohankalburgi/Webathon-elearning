package com.elearning.Elearning.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long courseId;

    @NotNull(message = "courseName is Null")
    private String coursename;

    @Lob
    @Column(columnDefinition = "TEXT")
    @NotNull(message = "course Description is Null")
    private String coursedescription;

    @NotNull(message = "course Description is Null")
    private String courseduration;

    @JsonIgnore
    @JsonIgnoreProperties
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "mentor")
    private Mentor mentor;

    @Lob
    @Column(name = "thumbnail",columnDefinition = "LONGBLOB")
    private byte[] thumbnail;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "course")
    private List<Video> videos;
}
