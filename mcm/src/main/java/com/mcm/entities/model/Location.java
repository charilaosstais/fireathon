package com.mcm.entities.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the Location database table.
 * 
 */
@Entity
@Table(name="LOCATION")
@NamedQuery(name="Location.findAll", query="SELECT l FROM Location l")
public class Location implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private Double latitude;
	private Double longtitude;
	private Device device;

	public Location() {}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="LATITUDE")
	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	@Column(name="LONGTITUDE")
	public Double getLogntitude() {
		return longtitude;
	}

	public void setLogntitude(Double lognitude) {
		this.longtitude = lognitude;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="PHONE_ID")
	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}
	
	
}
