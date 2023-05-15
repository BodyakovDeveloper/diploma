package com.koval.diploma.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

/*
@ControllerAdvice
@Slf4j
public class ErrorController {

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String exception(final Throwable throwable, final Model model) {
        log.error("Exception during execution of Spring Security application", throwable);
        String errorMessage = (throwable != null ? throwable.getMessage() : "Unknown error");
        model.addAttribute("errorMessage", errorMessage);

        List<String> errorSuggestions = List.of(
                "Try refreshing the page and attempting again.",
                "Contact the support team for further assistance.",
                "Check your internet connection and try again.",
                "Clear your browser cache and cookies, then try again."
        );
        model.addAttribute("errorSuggestions", errorSuggestions);

        return "error";
    }
}*/
