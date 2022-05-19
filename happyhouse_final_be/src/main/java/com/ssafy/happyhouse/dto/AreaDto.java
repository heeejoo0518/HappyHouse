package com.ssafy.happyhouse.dto;

public class AreaDto {
	private int no;
	private String userId;
	private String dongCode;
	private String sidoName;
	private String gugunName;
	private String dongName;
	private double lat;
	private double lng;
	
	public AreaDto() {}

	public AreaDto(int no, String userId, String dongCode, String sidoName, String gugunName, String dongName,
			double lat, double lng) {
		this.no = no;
		this.userId = userId;
		this.dongCode = dongCode;
		this.sidoName = sidoName;
		this.gugunName = gugunName;
		this.dongName = dongName;
		this.lat = lat;
		this.lng = lng;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDongCode() {
		return dongCode;
	}

	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}

	public String getSidoName() {
		return sidoName;
	}

	public void setSidoName(String sidoName) {
		this.sidoName = sidoName;
	}

	public String getGugunName() {
		return gugunName;
	}

	public void setGugunName(String gugunName) {
		this.gugunName = gugunName;
	}

	public String getDongName() {
		return dongName;
	}

	public void setDongName(String dongName) {
		this.dongName = dongName;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{no:");
		builder.append(no);
		builder.append(", userId:");
		builder.append(userId);
		builder.append(", dongCode:");
		builder.append(dongCode);
		builder.append(", sidoName:");
		builder.append(sidoName);
		builder.append(", gugunName:");
		builder.append(gugunName);
		builder.append(", dongName:");
		builder.append(dongName);
		builder.append("}");
		return builder.toString();
	}
	
}
