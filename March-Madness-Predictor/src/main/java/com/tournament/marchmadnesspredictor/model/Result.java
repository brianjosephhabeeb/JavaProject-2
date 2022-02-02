package com.tournament.marchmadnesspredictor.model;


import javax.persistence.*;

@Entity
@Table(name = "Result")
public class Result {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resultId;

    @ManyToOne
    @JoinColumn(name = "user_profile_id")
    private UserProfile userProfile;

    @OneToOne
    @JoinColumn(name = "game_id")
    private Game game;

    public Result(Long resultId, UserProfile userProfile, Game game) {
        this.resultId = resultId;
        this.userProfile = userProfile;
        this.game = game;
    }

    public Result() {
    }

    public Long getResultId() {
        return resultId;
    }

    public void setResultId(Long resultId) {
        this.resultId = resultId;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public String toString() {
        return "Result{" +
                "resultId=" + resultId +
                ", userProfile=" + userProfile +
                ", game=" + game +
                '}';
    }
}
