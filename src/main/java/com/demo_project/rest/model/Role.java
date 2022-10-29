package com.demo_project.rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "roles")
public class Role
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @NotBlank(message = "role boş olmamalıdır!")
    @Size(min = 5,max = 15,message = "role ən azı 5 ən çoxu 15 simvoldan ibarət olmalıdır!")
    @Column(name = "role",length = 20,nullable = false)
    private String role;

    public Role(String role){
        this.role = role;
    }

//    @OneToMany
//    private List<User> users;

}
