package com.bitc.xml.service;

import java.util.List;

import com.bitc.xml.dto.Covid19InfoStateItemDto;

public interface Covid19Service {
	List<Covid19InfoStateItemDto> getItemList() throws Exception;
	List<Covid19InfoStateItemDto> getItemListRange(String strUrl) throws Exception;
	
	//DB에서 가져오기
	List<Covid19InfoStateItemDto> getDbItemList() throws Exception;
	//범위지정해서 insert돼있는 DB로부터 가져오기
	List<Covid19InfoStateItemDto> getDbItemListRange(String startDt, String endDt) throws Exception;
	void setDbItemList(List<Covid19InfoStateItemDto> newItemList) throws Exception;
}
