package com.sparta.hanghaeboardproject2.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class JoinExceptionHandler {
    @ExceptionHandler(value = { HanghaeBoardJoinException.class })
    public String handleApiRequestException(Exception ex, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("msg", ex.getMessage());
        return "redirect:/member/signup";
    }
}
