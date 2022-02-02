package com.tournament.marchmadnesspredictor.model;

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id")
    private UserProfile userProfile;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "result_id")
    private Result result;

    @Column
    private String HomeTeam;

    @Column
    private String AwayTeam;

    public Game(Long id, String teamName, UserProfile userProfile, Result result, String homeTeam, String awayTeam) {
        this.id = id;
        this.teamName = teamName;
        this.userProfile = userProfile;
        this.result = result;
        HomeTeam = homeTeam;
        AwayTeam = awayTeam;
    }

    public Game() {
    }



    public String getHomeTeam() {
        return HomeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        HomeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return AwayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        AwayTeam = awayTeam;
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

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", teamName='" + teamName + '\'' +
                ", userProfile=" + userProfile +
                ", result=" + result +
                '}';
    }

    public void setResult(Result result) {
        this.result = result;
    }


    public String getName() {
     return teamName;
    }
    public void setName(String name) {
    }
}
