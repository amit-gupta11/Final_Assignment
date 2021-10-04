package com.lng.consultancy.scoresaver.repository;

import com.lng.consultancy.scoresaver.model.PlayerData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerDataRepository extends JpaRepository<PlayerData, Integer> {
    List<PlayerData> findPlayerDetailByTeamId(int teamId);
}
