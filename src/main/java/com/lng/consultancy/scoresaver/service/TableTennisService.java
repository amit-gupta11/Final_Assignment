package com.lng.consultancy.scoresaver.service;

import com.lng.consultancy.scoresaver.model.PlayerData;
import com.lng.consultancy.scoresaver.model.TeamData;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TableTennisService {
   void saveTableTennisTeam(TeamData teamData);
   void saveTableTennisPlayer(PlayerData playerData);
    TeamData getTeamById(int id);
   Page<TeamData> findPaginated(int pageNo, int pageSize);
}
