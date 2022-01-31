package com.tournament.marchmadnesspredictor.controller;

import com.tournament.marchmadnesspredictor.model.Team;
import com.tournament.marchmadnesspredictor.model.TeamWeightedMetrics;
import com.tournament.marchmadnesspredictor.TeamService;
import com.tournament.marchmadnesspredictor.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")

public class TeamController {

    private TeamService teamService;
    @Autowired
    public void setTeamService(TeamService teamService){this.teamService = teamService;}

    @GetMapping("/teams/")
    public List<Team> getAllTeams(){
        System.out.println("calling all teams");
        return teamService.getAllTeams();
    }

    @PostMapping("/teams/")
    public Team creatTeam(@RequestBody Team teamObject){
        return teamService.crdatTeam(teamObject);
    }

    @GetMapping("/teams/{teamId}/")
    public Optional<Team> getTeam(@PathVariable(value = "teamId") Long teamId){
        return teamService.getTeam(teamId);
    }

    @PutMapping("/team/{teamId}")
    public Team updateTeam(@PathVariable(value = "teamId") Long teamId, @RequestBody Team teamObject) {
        return teamService.updateTeam(teamId, teamObject);
    }

    @DeleteMapping("/teeam/{teamId}")
    public Optional<Team> deleteteam(@PathVariable(value = "teamId") Long teamId) {
        System.out.println("calling delete team");
        return teamService.deleteTeam(teamId);
    }

    @PostMapping("/team/{teamId}/twm/")
    public TeamWeightedMetrics createCategoryRecipe(@PathVariable(value = "teamId")Long teamId, @RequestBody TeamWeightedMetrics teamWeightedMetricsObject){
        System.out.println("calling createTeamWeightedMetrics");
        return teamService.createTeamService(teamId, teamWeightedMetricsObject);
    }

    @GetMapping("/team/{teamId}/twm/")
    public List<TeamWeightedMetrics> getTeamWeightedMetrics(@PathVariable (value = "teamId") Long teamId){
        return teamService.getTeamWeightedMetrics(teamId);
    }

    @GetMapping("/team/{teamId}/twm/{twmId}/")
    public TeamWeightedMetrics getTeamWeightedMetrics(@PathVariable(value = "teamId") Long teamId, @PathVariable(value = "twmId") Long twmId){
        return teamService.getTeamWeightedMetrics(teamId, twmId);
    }

    @PutMapping("/team/{teamId}/twm/{twmId}")
    public TeamWeightedMetrics updateTeamWeightedMetrics(@PathVariable(value = "teamId") Long teamId,
                                       @PathVariable(value = "twmId") Long twmId,
                                       @RequestBody TeamWeightedMetrics teamWeightedMetricsObject) {
        System.out.println("calling getTeamWeightedMetrics ==>");
        return teamService.updateTeamWeightedMetrics(teamId, twmId, teamWeightedMetricsObject);
    }

    @DeleteMapping("/team/{teamId}/twm/{twmId}")
    public ResponseEntity<HashMap> deleteTeamWeightedMetrics(
            @PathVariable(value = "teamId") Long teamId, @PathVariable(value = "twmId") Long twmId) {
        System.out.println("calling getTeamWeightedMetrics ==>");
        teamService.deleteTeamWeightedMetrics(teamId, twmId);
        HashMap responseMessage = new HashMap();
        responseMessage.put("status", "team weighted metrics with id: " + twmId + " was successfully deleted.");
        return new ResponseEntity<HashMap>(responseMessage, HttpStatus.OK);
    }

}
