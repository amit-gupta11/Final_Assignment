package com.lng.consultancy.scoresaver.service;

import com.lng.consultancy.scoresaver.model.PlayerData;
import com.lng.consultancy.scoresaver.model.TeamData;
import com.lng.consultancy.scoresaver.repository.PlayerDataRepository;
import com.lng.consultancy.scoresaver.repository.TeamDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TableTennisServiceImpl implements TableTennisService {

    @Autowired
    private TeamDataRepository teamDataRepository;
    @Autowired
    private PlayerDataRepository playerDataRepository;

    @Override
    public void saveTableTennisTeam(TeamData teamData) {
        this.teamDataRepository.save(teamData);
    }

    @Override
    public void saveTableTennisPlayer(PlayerData playerData) {
            this.playerDataRepository.save(playerData);
    }

    @Override
    public TeamData getTeamById(int id) {
        Optional<TeamData> optional = teamDataRepository.findById(id);
        TeamData teamData = null;
        if (optional.isPresent()){
            teamData = optional.get();
        }
        else{
            throw new RuntimeException("Team not found by id ::" +id);
        }
        return teamData;
    }

    @Override
    public Page<TeamData> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.teamDataRepository.findAll(pageable);
    }

    @Override
    public List<TeamData> getAllTeam() {

        return teamDataRepository.findAll();
    }

}
