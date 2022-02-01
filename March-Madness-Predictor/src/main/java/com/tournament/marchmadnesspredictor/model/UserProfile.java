package com.tournament.marchmadnesspredictor.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "profiles")
public class UserProfile {


    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String emailAddress;

    @JsonIgnore
    @OneToOne(mappedBy = "userProfile")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "result_id")
    private Result result;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_selection_id")
    private Result userSelection;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "game_id")
    private Result game;

    public UserProfile(Long id, String name, String emailAddress, User user, Result result, Result userSelection, Result game) {
        this.id = id;
        this.name = name;
        this.emailAddress = emailAddress;
        this.user = user;
        this.result = result;
        this.userSelection = userSelection;
        this.game = game;
    }

    public UserProfile() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Result getUserSelection() {
        return userSelection;
    }

    public void setUserSelection(Result userSelection) {
        this.userSelection = userSelection;
    }

    public Result getGame() {
        return game;
    }

    public void setGame(Result game) {
        this.game = game;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", user=" + user +
                ", result=" + result +
                ", userSelection=" + userSelection +
                ", game=" + game +
                '}';
    }
}

