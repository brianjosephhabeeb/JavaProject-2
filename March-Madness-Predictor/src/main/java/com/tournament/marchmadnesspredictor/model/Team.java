package com.tournament.marchmadnesspredictor.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "teams")   // create a new table called teams
public class Team {

    @Id // @Id means primary key
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY) // equivalent to SERIAL in SQL
    private Long id;

    @Column
    private String name;

    @Column
    private Integer rank;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;


    @OneToMany(mappedBy = "team", orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<TeamWeightedMetrics> teamWeightedMetricsList;

    public List<TeamWeightedMetrics> getTeamWeightedMetricsList() {
        return teamWeightedMetricsList;
    }

    public void setTeamWeightedMetricsList(List<TeamWeightedMetrics> teamWeightedMetricsList) {
        this.teamWeightedMetricsList = teamWeightedMetricsList;
    }

    public Team() {
    }

    public Team(Long id, String name, int rank) {
        this.id = id;
        this.name = name;
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rank='" + rank + '\'' +
                '}';
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

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

}
