package com.ssafy.happyhouse.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Ticket (예매정보)", description = "번호, 가수, 콘서트명, 장소, 날짜, 포스터이미지주소, 예매링크를 가진 Domain Class")
public class Ticket {
	@ApiModelProperty(value="번호")
	private int ticketno;
	@ApiModelProperty(value="가수")
	private String artist;
	@ApiModelProperty(value="콘서트명")
	private String title;
	@ApiModelProperty(value="장소")
	private String location;
	@ApiModelProperty(value="날짜")
	private String ticketdate;
	@ApiModelProperty(value="포스터이미지주소")
	private String img;
	@ApiModelProperty(value="예매링크")
	private String link;
}
