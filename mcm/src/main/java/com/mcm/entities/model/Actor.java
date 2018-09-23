package com.mcm.entities.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;
import java.util.Set;


/**
 * The persistent class for the USER database table.
 * 
 */
@Entity
@Table(name="ACTOR")
@NamedQuery(name="Actor.findAll", query="SELECT a FROM Actor a")
public class Actor implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String email;
	private String name;
	@JsonIgnore
	private Set<Device> devices = new HashSet<Device>();
	private Set<TeamActor> teamActors = new HashSet<TeamActor>();
	
	public Actor() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="email")
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name="name")
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	@JsonIgnore
	@OneToMany(mappedBy="actor", fetch = FetchType.EAGER)
	public Set<Device> getDevices() {
		return devices;
	}


	public void setDevices(Set<Device> devices) {
		this.devices = devices;
	}

	@JsonIgnore
	@OneToMany(mappedBy="actor")
	public Set<TeamActor> getTeamActors() {
		return teamActors;
	}


	public void setTeamActors(Set<TeamActor> teamActors) {
		this.teamActors = teamActors;
	}
	
	

}