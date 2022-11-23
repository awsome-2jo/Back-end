package com.ssafy.myHome.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.myHome.model.AptDealDto;
import com.ssafy.myHome.model.AptInfoDto;
import com.ssafy.myHome.model.AptSearchDto;
import com.ssafy.myHome.model.SidoGugunCodeDto;

@Mapper
public interface AptMapper {
	// 필요한 아파트 정보 가져오기
	public List<AptInfoDto> selectApart(AptSearchDto aptSearch) throws Exception; 
	public AptInfoDto selectApartDeal(AptSearchDto aptSearch) throws Exception; 
	public List<AptDealDto> selectApartDetail(String aptCode) throws Exception;
	public int countApart(AptSearchDto aptSearch) throws Exception;
	public List<SidoGugunCodeDto> selectSido() throws Exception;
	public List<SidoGugunCodeDto> selectGugun(String code) throws Exception;
	public List<SidoGugunCodeDto> selectDong(String code) throws Exception;
	public AptInfoDto selectApartByAptCode(String aptCode) throws Exception;
	public List<AptInfoDto> selectApartByRegCode(AptSearchDto aptSearch) throws Exception;
}
