package com.bitc.xml.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bitc.xml.dto.Covid19InfoStateItemDto;
import com.bitc.xml.service.Covid19Service;

@Controller
public class Covid19Controller {
	@Autowired
	private Covid19Service covid19Service;
	
	@RequestMapping(value="/covid19/infoState1",method=RequestMethod.GET)
	public ModelAndView covid19InfoState1() throws Exception{
		ModelAndView mv = new ModelAndView("/covid19/infoState1");
		
		List<Covid19InfoStateItemDto> itemList = covid19Service.getItemList();
		
		mv.addObject("covid19Datas", itemList);
		
		return mv;
	}
	
	//범위 지정해서 직접 가져오기(매일 새로 업데이트된 내용도 가져올 수 있다)
	@ResponseBody
	@RequestMapping(value="/covid19/infoState2",method=RequestMethod.POST)
	public Object covid19InfoState2(@RequestParam Map<String, String> param) throws Exception{
		
		String endPoint ="http://openapi.data.go.kr/openapi/service/rest/Covid19/";
		String serviceFunc = "getCovid19InfStateJson?";
		String keyFunc = "serviceKey=";
		String pageNo = "&pageNo=1";
		String rows = "&numOfRows=10";
		//원하는 날짜로 바꿔봄.
		String startCreateDt = "&startCreateDt=";
		String endCreateDt = "&endCreateDt=";
		
		String serviceKey = "wCwyFcqFJF6f%2Fvmt4o9jLT5OlchBL2fy2I%2BfCll7WUzY0a7N2WMAsZryTeqJIjsL2p4q5eJ6IKPtAAm%2F19wtFg%3D%3D";
		
		String strUrl = endPoint + serviceFunc + keyFunc + serviceKey + pageNo + rows + startCreateDt + param.get("startCreateDt") + endCreateDt + param.get("endCreateDt");
		
		List<Covid19InfoStateItemDto> itemList = covid19Service.getItemListRange(strUrl);

		return itemList;
		//이건 누르면 테이블에 추가되도록 만들어본 부분. 내가 만듬.
		//List<Covid19InfoStateItemDto> itemList = covid19Service.getItemList();
		
		//return itemList;
	}
}
