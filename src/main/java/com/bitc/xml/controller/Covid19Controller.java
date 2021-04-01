package com.bitc.xml.controller;

import java.util.ArrayList;
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
	
	//사용자 입력창에서 입력된 날짜를 매개변수 HashMap 키,값 방식으로 받음.
	@ResponseBody
	@RequestMapping(value="/covid19/infoState3", method=RequestMethod.POST)
	public Object getInfoState3(@RequestParam Map<String, String> param) throws Exception{
		/*
		ajax 통신을 통해 매개변수로 시작날짜와 종료날짜를 받아서 
		공공데이터 포털에서 데이터를 가져오기 위한 준비
		 */
		String endPoint ="http://openapi.data.go.kr/openapi/service/rest/Covid19/";
		String serviceFunc = "getCovid19InfStateJson?";
		String keyFunc = "serviceKey=";
		String pageNo = "&pageNo=1";
		String rows = "&numOfRows=10";
		String startCreateDt = "&startCreateDt=";
		String endCreateDt = "&endCreateDt=";
		
		String serviceKey = "wCwyFcqFJF6f%2Fvmt4o9jLT5OlchBL2fy2I%2BfCll7WUzY0a7N2WMAsZryTeqJIjsL2p4q5eJ6IKPtAAm%2F19wtFg%3D%3D";
		
		//공공데이터 포털에서 데이터를 가져오기 위한 url생성
		String strUrl = endPoint + serviceFunc + keyFunc + serviceKey + pageNo + rows + startCreateDt + param.get("startCreateDt") + endCreateDt + param.get("endCreateDt");
		
		//공공데이터 포털을 통해서 신규 xml 데이터 가져오기.(신규데이터)
		List<Covid19InfoStateItemDto> itemList = covid19Service.getItemListRange(strUrl);

		//DB에 저장되어있는 기존의 데이터를 가져오기, 매개변수 2개 날짜
		List<Covid19InfoStateItemDto> dbItemList = covid19Service.getDbItemListRange(param.get("startCreateDt"), param.get("endCreateDt"));
		
		//가져온 xml 데이터와 DB에 저장되어있는 기존의 데이터 비교
		//newItemList 선언함. 나중에 여기 신규데이터 저장하게된다.
		List<Covid19InfoStateItemDto> newItemList = new ArrayList<Covid19InfoStateItemDto>();
		
		//2중 for문을 사용하여 신규 데이터에서 기존 db의 데이터를 뺀 새 데이터 리스트를 생성함.
		for(Covid19InfoStateItemDto item : itemList) {//신규데이터리스트에서 값 하나씩
			boolean isEquals = false;
			for(Covid19InfoStateItemDto dbItem : dbItemList) {//기존db데이터리스트에서 값 하나씩
				//신규 데이터와 db 데이터중 같은 값이 있는지 비교
				if(item.getSeq().equals(dbItem.getSeq())) {
					isEquals = true;
				}
			}
			//item(신규데이터)를 저장한다.
			if(!isEquals) {//false일때 즉 기존에 없는 값일때
				newItemList.add(item);
			}
		}
		
		//신규데이터만 모아둔 newItemList를 DB에 저장
		covid19Service.setDbItemList(newItemList);
		
		//지정한 기간에 맞는 데이터를 sql문을 통해 DB로부터 가져옴.
		List<Covid19InfoStateItemDto> newDbItemList = covid19Service.getDbItemListRange(param.get("startCreateDt"),param.get("endCreateDt"));

		return newDbItemList;
	}
}
