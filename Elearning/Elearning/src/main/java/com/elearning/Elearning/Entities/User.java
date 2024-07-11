package com.elearning.Elearning.Entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.*;

import org.hibernate.annotations.NaturalId;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NaturalId
    @Email(message = "Invalid Email")
    private String email;

    @NotBlank(message="Password is Blank")
    private String password;

    @NotBlank(message="Role is Not Set")
    private String role;
}
