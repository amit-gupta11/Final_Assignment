package com.lng.consultancy.scoresaver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TicTacToeController {

    @GetMapping("/ticTacToe")
    public String viewTicTacToePage(Model model){
        return  "tic_tac_toe";
    }

}
