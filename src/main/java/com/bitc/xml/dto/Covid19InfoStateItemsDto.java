package com.bitc.xml.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="items")
public class Covid19InfoStateItemsDto {
	private List<Covid19InfoStateItemDto> itemList;
	
	@XmlElement(name="item")
	public List<Covid19InfoStateItemDto> getItemList() {
		return itemList;
	}

	public void setItemList(List<Covid19InfoStateItemDto> itemList) {
		this.itemList = itemList;
	}
	
}
