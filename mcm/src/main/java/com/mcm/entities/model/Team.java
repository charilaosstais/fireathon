package com.mcm.entities.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the Team database table.
 * 
 */
@Entity
@Table(name="TEAM")
@NamedQuery(name="Team.findAll", query="SELECT t FROM Team t")
public class Team implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String description;
	private String color;
	private Set<TeamActor> teamActors = new HashSet<TeamActor>();
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name="color")
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	@OneToMany(mappedBy="team")
	public Set<TeamActor> getTeamActors() {
		return teamActors;
	}
	public void setTeamActors(Set<TeamActor> teamActors) {
		this.teamActors = teamActors;
	}
	
	

}
