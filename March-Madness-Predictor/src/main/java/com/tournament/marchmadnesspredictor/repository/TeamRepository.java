package com.tournament.marchmadnesspredictor.repository;

import com.tournament.marchmadnesspredictor.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {
    Team findByName(String teamName);
    List<Team> findByUserId(Long userId);
    Team findByUserIdAndName(Long userId, String name);
    Team findByIdAndUserId(Long categoryId, Long userId);
}
