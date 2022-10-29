package com.demo_project.rest.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class ErrorDetails {

    private String timestamp;
    private String message;
    private int status;
    public ErrorDetails(int status,String message){
        this.message = message;
        this.status = status;
        this.timestamp = new SimpleDateFormat("dd-M-yyyy hh:mm:ss")
                             .format(new Date());

    }


}
