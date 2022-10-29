package com.demo_project.rest.exception;


import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public ResponseEntity<?> handleError(HttpServletResponse response) {
        int status = response.getStatus();

        if (status == HttpStatus.UNAUTHORIZED.value()) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value())
                    .body(
                            new ErrorDetails(HttpStatus.UNAUTHORIZED.value(),
                                    HttpStatus.UNAUTHORIZED.getReasonPhrase()
                            )
                    );

        }

        if (status == HttpStatus.FORBIDDEN.value()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN.value())
                    .body(
                            new ErrorDetails(
                                    HttpStatus.FORBIDDEN.value(),
                                    HttpStatus.FORBIDDEN.getReasonPhrase()
                            )
                    );
        }

        if (status == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .body(
                            new ErrorDetails(
                                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                    HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()
                            )
                    );
        }


        return ResponseEntity.status(status)
                .body(
                        new ErrorDetails(
                                HttpStatus.resolve(status).value(),
                                HttpStatus.resolve(status).getReasonPhrase()
                        )
                );

    }
}
