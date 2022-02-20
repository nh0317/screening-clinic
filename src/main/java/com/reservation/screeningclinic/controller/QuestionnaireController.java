package com.reservation.screeningclinic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionnaireController {
    @GetMapping("/questionnaire/{screenClinicIdx}")
    public String getQuestionnaire(@PathVariable("screenClinicIdx")Long screenClinicIdx, Model model){
        model.addAttribute("screenClinicIdx", screenClinicIdx);
        return "문진표2";
    }

    @GetMapping("/questionnaire2/{screenClinicIdx}")
    public String getQuestionnaire2(@PathVariable("screenClinicIdx")Long screenClinicIdx, Model model){
        model.addAttribute("screenClinicIdx", screenClinicIdx);
        return "문진표3";
    }
}
