package com.ssafy.myHome.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.myHome.model.AptDealDto;
import com.ssafy.myHome.model.AptInfoDto;
import com.ssafy.myHome.model.AptSearchDto;
import com.ssafy.myHome.model.SidoGugunCodeDto;
import com.ssafy.myHome.model.mapper.AptMapper;

@Service
public class AptServiceImpl implements AptService {
	private AptMapper aptMapper;
	
	@Autowired
	public AptServiceImpl(AptMapper aptMapper) {
		this.aptMapper = aptMapper;
	}

	@Override
	public List<AptInfoDto> selectApartByDong(AptSearchDto aptSearch) throws Exception {
		return aptMapper.selectApartByDong(aptSearch);
	}

	@Override
	public List<AptInfoDto> selectApartByName(AptSearchDto aptSearch) throws Exception {
		return aptMapper.selectApartByName(aptSearch);
	}

	@Override
	public List<AptDealDto> selectApartDetail(String aptCode) throws Exception {
		return aptMapper.selectApartDetail(aptCode);
	}

	@Override
	public List<SidoGugunCodeDto> getSido() throws Exception {
		return aptMapper.selectSido();
	}

	@Override
	public List<SidoGugunCodeDto> getGugun(String code) throws Exception {
		return aptMapper.selectGugun(code);
	}

	@Override
	public List<SidoGugunCodeDto> getDong(String code) throws Exception {
		return aptMapper.selectDong(code);
	}

}
