package com.bitc.xml.service;

import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitc.xml.dto.Covid19InfoStateDto;
import com.bitc.xml.dto.Covid19InfoStateItemDto;
import com.bitc.xml.mapper.Covid19InfoStateMapper;

@Service
public class Covid19ServiceImpl implements Covid19Service {
	@Autowired
	private Covid19InfoStateMapper covid19Mapper;
	
	@Override
	public List<Covid19InfoStateItemDto> getItemList() throws Exception {
		
		//Controller에서 만들었던 소스를 Service에 가져오고 거기서는 Service를 사용함.
		//JAXB 라이브러리를 사용하여 xml데이터를 파싱
		JAXBContext jc = JAXBContext.newInstance(Covid19InfoStateDto.class);
		
		//언마샬 사용 준비
		Unmarshaller um = jc.createUnmarshaller();
		
		//실제 xml 데이터 파일을 로드하여 jaxb 라이브러리로 언마샬을 실행 후 대입
		Covid19InfoStateDto fullData = (Covid19InfoStateDto)um.unmarshal(new File("c://java102//covid19InfoState.xml"));
		
		List<Covid19InfoStateItemDto> itemList = fullData.getBody().getItems().getItemList();
		
		//윗줄의 리스트만드는 방식을 세세하게 해보면 아래와 같다.
		/*
		PharmacyFullDataHeaderDto header = fullData.getHeader();
		PharmacyFullDataBodyDto body = fullData.getBody();
		
		System.out.println("header 정보 : " + header.getResultCode() + "\t" + header.getResultMsg());
		System.out.println("body 정보 : \n전체 데이터 수 : " + body.getTotalCount() + "\n현재 페이지 : " + body.getPageNo() + "\n한 페이지당 출력 수" + body.getNumOfRows());
		
		body의 아이템리스트를 가져와서 items에 저장, items의 getItemList를 사용해서 ItemDto에 리스트 저장
		PharmacyFullDataItemsDto items = body.getItems();
		List<PharmacyFullDataItemDto> itemList = items.getItemList();
		
		
		System.out.println(itemList.get(0).getDutyName());
		System.out.println(itemList.get(0).getDutyAddr());
		*/
		
		
		return itemList;
	}
	
	//공공데이터 포털에서 xml 데이터를 가져옴
	//xml데이터를 Covid19InfoStateItemDto 타입의 Java리스트로 변환
	//직접 범위 지정 후 가져오기(매일 업데이트된 데이터를 가져올 수 있음)
	@Override
	public List<Covid19InfoStateItemDto> getItemListRange(String strUrl) throws Exception{
		List<Covid19InfoStateItemDto> itemList = null; //반환값
		URL url = null; //실제 url 주소
		HttpURLConnection urlConn = null; //접속
		
		try {
			//공공데이터 포털로 네트워크 연결
			url = new URL(strUrl);
			urlConn = (HttpURLConnection)url.openConnection();
			urlConn.setRequestMethod("GET");
			
			//JAXB 라이브러리를 활용하여 xml을 자바 클래스 객체로 변환
			JAXBContext jc = JAXBContext.newInstance(Covid19InfoStateDto.class);
			Unmarshaller um = jc.createUnmarshaller();
			
			Covid19InfoStateDto infoData = (Covid19InfoStateDto)um.unmarshal(url);
			
//			Covid19InfoStateBodyDto infoBody = infoData.getBody();
//			Covid19InfoStateItemsDto infoItems = infoBody.getItems();
//			itemList = infoItems.getItemList();
			
			itemList = infoData.getBody().getItems().getItemList();
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally{
			urlConn.disconnect();
		}
		
		return itemList;
	}
	
	@Override
	public List<Covid19InfoStateItemDto> getDbItemList() throws Exception{
		return covid19Mapper.getDbItemList();
	}
	
	//기존 저장된 DB 데이터 가져오기
	@Override
	public List<Covid19InfoStateItemDto> getDbItemListRange(String startDt, String endDt) throws Exception{
		
		List<Covid19InfoStateItemDto> dbItemList = covid19Mapper.getDbItemListRange(dateConvert(startDt), dateConvert(endDt));
		
		return dbItemList;
	}
	
	private String dateConvert(String date) {
		//입력받은 날짜를 DB에서 알 수 있는 형태로 변환
		//20200314 -> 2020-03-14
		StringBuilder sb = new StringBuilder();
		for (int i=0; i< date.length(); i++) {
			sb.append(date.charAt(i));
			if(i==3) {
				sb.append('-');
			}
			else if(i == 5) {
				sb.append('-');
			}
		}
		
		return sb.toString();
	}
	
	@Override
	public void setDbItemList(List<Covid19InfoStateItemDto> newItemList) throws Exception{
		//for문 사용하여 mapper의 setDbItem 메서드를 여러번 반복실행하여 데이터 입력함
		//한꺼번이 아니라 여러번 하는거라서 성능은 떨어질 수 있음.
		for (Covid19InfoStateItemDto item : newItemList) {
			covid19Mapper.setDbItem(item);
		}
		//리스트 타입을 한번에 추가하기. 성능에 좋음
		//covid19Mapper.setDbItemList(newItemList);
	}
}
