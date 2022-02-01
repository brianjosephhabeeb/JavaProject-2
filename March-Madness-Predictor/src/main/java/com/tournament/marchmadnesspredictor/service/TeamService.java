package com.tournament.marchmadnesspredictor.service;

import com.tournament.marchmadnesspredictor.exceptions.InformationExistException;
import com.tournament.marchmadnesspredictor.exceptions.InformationNotFoundException;
import com.tournament.marchmadnesspredictor.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {
    private TeamRepository teamRepository;
    @Autowired
    public void setCategoryRepository(TeamRepository teamRepository){
        this.teamRepository = teamRepository;
    }

    private TeamWeightedMetricsRepository teamWeightedMetricsRepository;

    @Autowired
    public void setTeamWeightedMetricsRepository(TeamWeightedMetricsRepository recipeRepository){
        this.teamWeightedMetricsRepository = teamWeightedMetricsRepository;
    }

    public List<Team> getAllTeams(){
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(userDetails.getUser().getId());

        List<Team> team = teamRepository.findByUserId(userDetails.getUser().getId());
        if(team.isEmpty()){
            throw new InformationNotFoundException("no team found for user id " + userDetails.getUser().getId() + " not found");
        } else {
            return team;
        }
    }

    public Team createTeam(Team teamObject){

        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Team team = teamRepository.findByUserIdAndName(userDetails.getUser().getId(),teamObject.getName());

        if(team != null){
            throw new InformationExistException("team with name " + team.getName() + " already exists");
        } else{
            teamObject.setUser(userDetails.getUser());
            return teamRepository.save(teamObject);
        }
    }

    public Optional<Team> getTeam(Long teamId){
        Optional<Team> team = teamRepository.findById(teamId);
        if(team.isPresent()){
            return team;
        } else{
            throw new InformationNotFoundException("category with Id " + teamId + " not found");
        }
    }

    public Team updateTeam(Long teamId, Team teamObject) {
        System.out.println("service calling updateTeam ==>");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Team team = teamRepository.findByIdAndUserId(teamId, userDetails.getUser().getId());
        if (team == null) {
            throw new InformationNotFoundException("team with id " + teamId + " not found");
        } else {
            team.setName(teamObject.getName());
            team.setRank(teamObject.getRank());
            team.setUser(userDetails.getUser());
            return teamRepository.save(team);
        }
    }

    public Optional<Team> deleteTeam(Long teamId) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Team team = teamRepository.findByIdAndUserId(teamId, userDetails.getUser().getId());
        if (team == null) {
            throw new InformationNotFoundException("team with id " + teamId + " not found.");
        } else {
            teamRepository.deleteById(teamId);
            return Optional.ofNullable(team);
        }
    }

    public TeamWeightedMetrics createTeamWeightedMetrics(Long teamId, TeamWeightedMetrics teamWeightedMetricsObject) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Team team = teamRepository.findByIdAndUserId(teamId, userDetails.getUser().getId());
        if (team == null) {
            throw new InformationNotFoundException(
                    "team with id " + teamId + " does not belong to this user or team does not exist.");
        }
        TeamWeightedMetrics teamWeightedMetrics = teamWeightedMetricsRepository.findByNameAndUserId(teamWeightedMetricsObject.getName(), userDetails.getUser().getId());
        if (teamWeightedMetrics != null) {
            throw new InformationExistException("team weighted metrics with name " + teamWeightedMetrics.getName() + " already exists.");
        }
        teamWeightedMetricsObject.setUser(userDetails.getUser());
        teamWeightedMetricsObject.setTeam(team);
        return teamWeightedMetricsRepository.save(teamWeightedMetricsObject);
    }

    public List<TeamWeightedMetrics> getTeamWeightedMetrics(Long teamId) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Team team = teamRepository.findByIdAndUserId(teamId, userDetails.getUser().getId());
        if (team == null) {
            throw new InformationNotFoundException("team with id " + teamId + " " +
                    " does not belong to this user or team does not exist.");
        }
        return team.getTeamWeightedMetricsList();
    }

    public TeamWeightedMetrics getTeamWeightedMetrics(Long teamId, Long twmId) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Team team = teamRepository.findByIdAndUserId(teamId, userDetails.getUser().getId());
        if (team == null) {
            throw new InformationNotFoundException("team with id " + teamId +
                    " does not belong to this user or team does not exist.");
        }
        Optional<TeamWeightedMetrics> teamWeightedMetrics = teamWeightedMetricsRepository.findByTeamId(
                teamId).stream().filter(p -> p.getId().equals(twmId)).findFirst();
        if (!teamWeightedMetrics.isPresent()) {
            throw new InformationNotFoundException("team weighted metrics with id " + twmId +
                    " does not belong to this user or team weighted metrics does not exist.");
        }
        return teamWeightedMetrics.get();
    }

    public TeamWeightedMetrics updateTeamWeightedMetrics(Long teamId, Long twmId, TeamWeightedMetrics teamWeightedMetricsObject) {
        System.out.println("service calling updateTeamWeightedMetrics ==>");
        try {
            TeamWeightedMetrics teamWeightedMetrics = (teamWeightedMetricsRepository.findByTeamId(
                    teamId).stream().filter(p -> p.getId().equals(twmId)).findFirst()).get();
            teamWeightedMetrics.setName(teamWeightedMetricsObject.getName());
            teamWeightedMetrics.setAttributes(teamWeightedMetricsObject.getAttributes());
            teamWeightedMetrics.setPublic(teamWeightedMetricsObject.isPublic());
            return teamWeightedMetricsRepository.save(teamWeightedMetrics);
        } catch (InformationNotFoundException e) {
            throw new InformationNotFoundException("team weighted metrics or team not found");
        }
    }

    public void deleteTeamWeightedMetrics(Long teamId, Long twmId) {
        try {
            TeamWeightedMetrics teamWeightedMetrics = (teamWeightedMetricsRepository.findByTeamId(
                    teamId).stream().filter(p -> p.getId().equals(twmId)).findFirst()).get();
            teamWeightedMetricsRepository.deleteById(teamWeightedMetrics.getId());
        } catch (InformationNotFoundException e) {
            throw new InformationNotFoundException("team weighted metrics or team not found");
        }
    }

    public TeamWeightedMetrics createTeamService(Long teamId, TeamWeightedMetrics teamWeightedMetricsObject) {
        return teamWeightedMetricsObject;
    }
}
