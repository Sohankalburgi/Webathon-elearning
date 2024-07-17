package com.elearning.Elearning.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Mentor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String dateofbirth;

    private String highestqualification;

    private String location;

    @OneToOne
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE},mappedBy = "mentor")
    private List<Course> courses = new ArrayList<>();


}
