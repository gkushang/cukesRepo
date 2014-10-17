package com.cukesrepo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ErrorPageController
{

    @ExceptionHandler(Exception.class)
    public void handleError()
    {

        System.out.println("\nerror page ");

    }
}
