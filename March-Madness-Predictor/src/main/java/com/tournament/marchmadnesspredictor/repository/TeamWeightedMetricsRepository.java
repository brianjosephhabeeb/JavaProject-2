package com.tournament.marchmadnesspredictor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamWeightedMetricsRepository extends JpaRepository<TeamWeightedMetrics, Long> {

    List<TeamWeightedMetrics> findByTeamId(Long twmId);

    TeamWeightedMetrics findByNameAndUserId(String name, Long id);
}
