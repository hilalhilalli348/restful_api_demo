package com.demo_project.rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(length = 50, unique = true, nullable = false)
    @NotBlank(message = "username boş olmamalidir!")
    @Size(min = 5,max = 15,message = "username ən azı 5 ən çoxu 15 simvoldan ibarət olmalıdır!")
    private String username;



    @Column(length = 100,nullable = false)
    @NotBlank(message = "password boş olmamalidir!")
    @Size(min = 5,max = 100,message = "şifrə ən azı 5 ən çoxu 100 simvoldan ibarət olmalıdır!")
    private String password;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;


}
