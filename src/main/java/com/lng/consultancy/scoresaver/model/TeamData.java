package com.lng.consultancy.scoresaver.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "teams")
public class TeamData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "team_name")
    @Size(min = 4,max = 24)
    private String teamName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
