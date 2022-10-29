package com.demo_project.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTOCredential {


    @NotBlank(message = "username boş olmamalidir!")
    @Size(min = 5,max = 15,message = "username ən azı 5 ən çoxu 15 simvoldan ibarət olmalıdır!")
    private String username;


    @NotBlank(message = "password boş olmamalidir!")
    @Size(min = 5,max = 15,message = "şifrə ən azı 5 ən çoxu 100 simvoldan ibarət olmalıdır!")
    private String password;


}
