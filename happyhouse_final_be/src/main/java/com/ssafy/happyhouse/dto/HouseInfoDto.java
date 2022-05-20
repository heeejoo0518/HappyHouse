package com.ssafy.happyhouse.dto;

import java.util.ArrayList;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "HouseInfoDto (아파트정보)", description = "아파트 정보, 거래목록, 최소거래액, 최대거래액 가진 Domain Class")
public class HouseInfoDto {
	@ApiModelProperty(value = "아파트코드")
	private int aptCode;
	@ApiModelProperty(value = "건축년도")
	private int buildYear;
	@ApiModelProperty(value = "도로명")
	private String roadName;
	@ApiModelProperty(value = "도로명건물본번호코드")
	private String roadNameBonbun;
	@ApiModelProperty(value = "도로명건물부번호코드")
	private String roadNameBubun;
	@ApiModelProperty(value = "도로명일련번호코드")
	private String roadNameSeq;
	@ApiModelProperty(value = "도로명지상지하코드")
	private String roadNameBasementCode;
	@ApiModelProperty(value = "도로명코드")
	private String roadNameCode;
	@ApiModelProperty(value = "법정동")
	private String dong;
	@ApiModelProperty(value = "법정동본번코드")
	private String bonbun;
	@ApiModelProperty(value = "법정동부번코드")
	private String bubun;
	@ApiModelProperty(value = "법정동시군구코드")
	private String sigunguCode;
	@ApiModelProperty(value = "법정동읍면동코드")
	private String eubmyundongCode;
	@ApiModelProperty(value = "법정동지번코드")
	private String dongCode;
	@ApiModelProperty(value = "지역코드")
	private String landCode;
	@ApiModelProperty(value = "아파트명")
	private String apartmentName;
	@ApiModelProperty(value = "지번")
	private String jibun;
	@ApiModelProperty(value = "위도")
	private String lat;
	@ApiModelProperty(value = "경도")
	private String lng;
	@ApiModelProperty(value = "이미지주소")
	private String img;
	@ApiModelProperty(value = "거래목록")
	private ArrayList<HouseDealInfo> deals;
	@ApiModelProperty(value = "최소거래금액")
	private int lowest;
	@ApiModelProperty(value = "최대거래금액")
	private int highest;
}
