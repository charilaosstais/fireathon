package com.mcm.entities.dao;

public class SubMap {
	
	private long minLat, maxLat, minLng, maxLng;

	public SubMap() {
	}
	
	public SubMap(long minLat, long maxLat, long minLong, long maxLong) {
		super();
		this.minLat = minLat;
		this.maxLat = maxLat;
		this.minLng = minLong;
		this.maxLng = maxLong;
	}

	public long getMinLat() {
		return minLat;
	}

	public void setMinLat(long minLat) {
		this.minLat = minLat;
	}

	public long getMaxLat() {
		return maxLat;
	}

	public void setMaxLat(long maxLat) {
		this.maxLat = maxLat;
	}

	public long getMinLng() {
		return minLng;
	}

	public void setMinLong(long minLng) {
		this.minLng = minLng;
	}

	public long getMaxLng() {
		return maxLng;
	}

	public void setMaxLong(long maxLng) {
		this.maxLng = maxLng;
	}

}