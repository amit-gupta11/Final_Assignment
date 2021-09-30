package com.lng.consultancy.scoresaver.repository;

import com.lng.consultancy.scoresaver.model.TeamData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamDataRepository extends JpaRepository<TeamData, Integer> {

}
