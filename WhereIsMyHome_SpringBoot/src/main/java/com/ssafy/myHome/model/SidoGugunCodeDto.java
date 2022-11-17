package com.ssafy.myHome.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "SidoGugunCodeDto : 시도/구군 정보", description = "시도/구군 코드, 이름")
@Data
public class SidoGugunCodeDto {
	@ApiModelProperty(value = "시도/구군 코드")
	private String code;
	
	@ApiModelProperty(value = "시도/구군 이름")
	private String name;
}
