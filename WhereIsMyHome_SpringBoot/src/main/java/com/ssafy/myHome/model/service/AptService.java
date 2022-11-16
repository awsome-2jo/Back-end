package com.ssafy.myHome.model.service;

import java.util.List;

import com.ssafy.myHome.model.AptDealDto;
import com.ssafy.myHome.model.AptInfoDto;
import com.ssafy.myHome.model.AptSearchDto;
import com.ssafy.myHome.model.SidoGugunCodeDto;

public interface AptService {
	List<AptInfoDto> selectApartByDong(AptSearchDto aptSearch) throws Exception;
	List<AptInfoDto> selectApartByName(AptSearchDto aptSearch) throws Exception;
	List<AptDealDto> selectApartDetail(AptSearchDto aptSearch) throws Exception;
	List<SidoGugunCodeDto> getSido() throws Exception;
	List<SidoGugunCodeDto> getGugun(String code) throws Exception;
	List<SidoGugunCodeDto> getDong(String code) throws Exception;
}
