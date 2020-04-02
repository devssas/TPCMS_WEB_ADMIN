package com.tpcmswebadmin.webpages.card.controller;

import com.tpcmswebadmin.webpages.card.domain.CalenderNewModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class CalenderNewCardController {

    @GetMapping("/card/calenderNew")
    public String getCrimeReportCard(HttpServletRequest httpServletRequest, Model model) {
        model.addAttribute("calenderNew", new CalenderNewModel());

        return "card_calender_new_event";
    }
}
