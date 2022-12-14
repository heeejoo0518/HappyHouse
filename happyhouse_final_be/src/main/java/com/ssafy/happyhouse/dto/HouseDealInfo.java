package com.ssafy.happyhouse.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "HouseDealInfo (거래정보)", description = "아파트 거래 정보를 가진 Domain Class")
public class HouseDealInfo {
	@ApiModelProperty(value = "거래번호")
	private long no;
	@ApiModelProperty(value = "거래금액")
	private String dealAmount;
	@ApiModelProperty(value = "거래년도")
	private int dealYear;
	@ApiModelProperty(value = "거래월")
	private int dealMonth;
	@ApiModelProperty(value = "거래일")
	private int dealDay;
	@ApiModelProperty(value = "전용면적")
	private String area;
	@ApiModelProperty(value = "층")
	private String floor;
	@ApiModelProperty(value = "해제여부")
	private String cancelDealType;
	@ApiModelProperty(value = "아파트코드")
	private long aptCode;
}
