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
    private String teamName;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserProfile userProfile;

    @OneToOne
    @JoinColumn(name = "result_id")
    private Result result;

    public Game(Long id, String teamName, UserProfile userProfile, Result result) {
        this.id = id;
        this.teamName = teamName;
        this.userProfile = userProfile;
        this.result = result;
    }

    public Game() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
