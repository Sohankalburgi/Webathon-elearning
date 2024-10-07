package com.elearning.Elearning.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String filename;


    @Column(columnDefinition = "TEXT")
    private String fileURL;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    @JsonIgnoreProperties
    private Course course;
}
