package com.reservation.screeningclinic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class QuestionnaireController {
    @GetMapping("/questionnaire")
    public String getQuestionnaire(){
        return "문진표2";
    }

    @GetMapping("/questionnaire2")
    public String getQuestionnaire2(){
        return "문진표3";
    }
}
