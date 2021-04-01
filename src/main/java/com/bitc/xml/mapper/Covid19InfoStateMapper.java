package com.bitc.xml.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.bitc.xml.dto.Covid19InfoStateItemDto;

@Mapper
public interface Covid19InfoStateMapper {
	List<Covid19InfoStateItemDto> getDbItemList() throws Exception;

	List<Covid19InfoStateItemDto> getDbItemListRange(@Param("startDt") String startDt, @Param("endDt") String endDt) throws Exception;

	void setDbItem(Covid19InfoStateItemDto item) throws Exception;
	void setDbItemList(List<Covid19InfoStateItemDto> itemList) throws Exception;

}
