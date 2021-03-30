package com.bitc.xml.service;

import java.util.List;

import com.bitc.xml.dto.Covid19InfoStateItemDto;

public interface Covid19Service {
	List<Covid19InfoStateItemDto> getItemList() throws Exception;
	List<Covid19InfoStateItemDto> getItemListRange(String strUrl) throws Exception;
}
