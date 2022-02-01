package com.tournament.marchmadnesspredictor.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "Games")
public class Game {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer gameNumber;

    @Column
    private Integer teams;

    @Column
    private Integer teamRank;

    @ManyToMany(fetch = FetchType.LAZY,
    cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "user_profile",
            joinColumns = {@JoinColumn(name = "game_id")},
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @JsonIgnore
    private Integer userSelection;


    public Game(Long id, Integer gameNumber, Integer teams, Integer teamRank, Integer userSelection) {
        this.id = id;
        this.gameNumber = gameNumber;
        this.teams = teams;
        this.teamRank = teamRank;
        this.userSelection = userSelection;
    }

    public Game() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getGameNumber() {
        return gameNumber;
    }

    public void setGameNumber(Integer gameNumber) {
        this.gameNumber = gameNumber;
    }

    public Integer getTeams() {
        return teams;
    }

    public void setTeams(Integer teams) {
        this.teams = teams;
    }

    public Integer getUserSelection() {
        return userSelection;
    }

    public void setUserSelection(Integer userSelection) {
        this.userSelection = userSelection;
    }

    public Integer getTeamRank() {
        return teamRank;
    }

    public void setTeamRank(Integer teamRank) {
        this.teamRank = teamRank;
    }
}
