package com.lng.consultancy.scoresaver.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "players")
public class PlayerData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "team_id")
    private int teamId;

    @Size(min = 4,max = 24)
    @Column(name = "player_name")
    private String playerName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
