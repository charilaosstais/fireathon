package com.mcm.entities.dao;

public class SubMap {
	
	private long minLat, maxLat, minLong, maxLong;

	public SubMap() {
	}
	
	public SubMap(long minLat, long maxLat, long minLong, long maxLong) {
		super();
		this.minLat = minLat;
		this.maxLat = maxLat;
		this.minLong = minLong;
		this.maxLong = maxLong;
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

	public long getMinLong() {
		return minLong;
	}

	public void setMinLong(long minLong) {
		this.minLong = minLong;
	}

	public long getMaxLong() {
		return maxLong;
	}

	public void setMaxLong(long maxLong) {
		this.maxLong = maxLong;
	}

}