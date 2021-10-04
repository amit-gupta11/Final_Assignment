package com.lng.consultancy.scoresaver.controller;

import com.lng.consultancy.scoresaver.model.PlayerData;
import com.lng.consultancy.scoresaver.model.TeamData;
import com.lng.consultancy.scoresaver.repository.PlayerDataRepository;
import com.lng.consultancy.scoresaver.service.TableTennisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class TableTennisTeamController {

    @Autowired
    private TableTennisService tableTennisService;

    @GetMapping("/")
    public String viewHomePage(Model model){
        return "homepage";
    }

    @GetMapping("/aboutUs")
    public String aboutUs(Model model){
        return "about_us";
    }



    @GetMapping("/selectTeamTT")
    public String selectTeamTT(Model model){
        model.addAttribute("listTeam", tableTennisService.getAllTeam());
        return "select_team_tt";
    }

    @Autowired
    PlayerDataRepository playerDataRepository;
    @GetMapping("/getTeamByTeamId")
    public @ResponseBody List<PlayerData> getPlayersByTeamId(@RequestParam(value="teamId") int teamId)
    {
        List<PlayerData> playerDataList = playerDataRepository.findPlayerDetailByTeamId(teamId);
        return playerDataList;
    }

    @GetMapping("/tableTennis")
    public String tableTennis(Model model){
        return "table_tennis";
    }

    @GetMapping("/addTeamForTableTennis")
    public String addFormTableTennisTeam(Model model){
        TeamData teamData = new TeamData();
        model.addAttribute("teamData",teamData);
        return "add_team_tt";
    }

    @GetMapping("/teamList")
    public String viewTeamList(Model model) {
        return findPaginated(1, model);
    }

    @GetMapping("/teamId/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") int id, Model model) {
        PlayerData playerData = new PlayerData();
        playerData.setTeamId(id);
        model.addAttribute("playerData", playerData);
        return "add_player_tt";
    }

    @PostMapping("/saveTableTennisTeam")
    public String saveTableTennisTeam(@Valid @ModelAttribute("teamData") TeamData teamData, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
           return  "add_team_tt";
        }
        else{
            tableTennisService.saveTableTennisTeam(teamData);
       return "redirect:/teamList";
        }
    }

    @PostMapping("/saveTableTennisPlayer")
    public String saveTableTennisPlayer(@Valid @ModelAttribute("playerData") PlayerData playerData , BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            return  "add_player_tt";
        }
        else{
            tableTennisService.saveTableTennisPlayer(playerData);
            return "redirect:/tableTennis";
        }

    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
        int pageSize = 5;
        Page< TeamData > page = tableTennisService.findPaginated(pageNo, pageSize);
        List< TeamData > teamDataList = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("teamDataList", teamDataList);
        return "team_list";
    }

}
