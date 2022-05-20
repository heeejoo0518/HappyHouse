package com.ssafy.happyhouse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SidoGugunCodeDto {
	private String dongCode;
	private String sidoName;
	private String gugunName;
	private String dongName;
}
