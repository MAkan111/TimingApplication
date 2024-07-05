package com.example.timingapplication.Controller;

import com.example.timingapplication.Model.ExceptionDTO;
import com.example.timingapplication.Model.ModelNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ExceptionController {
    @ExceptionHandler(ModelNotExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ExceptionDTO handleException(ModelNotExistException e){
        return new ExceptionDTO(e.getMessage());
    }
}
