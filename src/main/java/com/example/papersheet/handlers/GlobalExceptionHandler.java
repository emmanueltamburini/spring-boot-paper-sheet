package com.example.papersheet.handlers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ModelAndView handleAllExceptions(Exception ex) {
        ModelAndView model = new ModelAndView("error");
        model.addObject("errMsg", "An unexpected error occurred: " + ex.getMessage());
        return model;
    }

    @ExceptionHandler(CustomException.class)
    public ModelAndView handleCustomException(CustomException ex) {
        ModelAndView model = new ModelAndView("error");
        model.addObject("errMsg", "A custom exception occurred: " + ex.getMessage());
        return model;
    }
}