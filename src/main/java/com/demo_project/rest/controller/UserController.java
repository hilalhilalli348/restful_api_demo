package com.demo_project.rest.controller;

import com.demo_project.rest.dto.UserDTO;
import com.demo_project.rest.dto.UserDTOCredential;
import com.demo_project.rest.dto.UserDTOUpdate;
import com.demo_project.rest.exception.ResourceIsInvalidException;
import com.demo_project.rest.model.User;
import com.demo_project.rest.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
@Api(description = "User üzərində crud əməliyyatları üçün controller")
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }


    @GetMapping
    @ApiOperation("Mövcut bütün user-lərin alınması")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<User> userList = userService.getAllUsers();
        List<UserDTO> userDTOList = converToUserDTOList(userList);
        return ResponseEntity.ok(userDTOList);
    }


    @GetMapping(path = "{id}")
    @ApiOperation("ID-yə görə mövcut user-in alınması")
    public ResponseEntity<UserDTO> getUserById(@PathVariable int id) {
        Optional<User> user = userService.getUserById(id);
        UserDTO userDTO = converToUserDTO(user.get());



        return ResponseEntity.ok(userDTO);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Yeni user-in yaradılması")
    public void createUser(@Valid @RequestBody UserDTOCredential userDTOCredential, BindingResult bindingResult) {
        validateUser(bindingResult);
        userService.createUser(userDTOCredential);
    }


    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Mövcut user-in silinməsi")
    public void deleteUser(@PathVariable int id){
        userService.deleteUser(id);
    }


    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Mövcut user-in güncəllənməsi")
    public void updateUser(@Valid @RequestBody UserDTOUpdate userDTOUpdate, BindingResult bindingResult){

            validateUser(bindingResult);
            userService.updateUser(userDTOUpdate);
    }





    private  void validateUser(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errMessage = new StringBuilder();

            List<FieldError> fieldErrors = bindingResult.getFieldErrors();


            for (FieldError fieldError : fieldErrors) {

                errMessage.append(String.format("%s : %s" ,fieldError.getField(),fieldError.getDefaultMessage()));
            }


            throw new ResourceIsInvalidException(errMessage.toString());
        }
    }

    private UserDTO converToUserDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    private List<UserDTO> converToUserDTOList(List<User> userList) {
        return modelMapper.map(userList, new TypeToken<List<UserDTO>>() {
        }.getType());
    }

}
