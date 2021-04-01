package com.bitc.xml.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="item")
public class Covid19InfoStateItemDto {
	private String accDefRate;
	private String accExamCnt;
	private String accExamCompCnt;
    private String careCnt;
    private String clearCnt;
    private String createDt;
    private String deathCnt;
    private String decideCnt;
    private String examCnt;
    private String resultNegCnt;
    private String seq;
    private String stateDt;
    private String stateTime;
    private String updateDt;
    
    @XmlElement
	public String getAccDefRate() {
		return accDefRate;
	}
	public void setAccDefRate(String accDefRate) {
		this.accDefRate = accDefRate;
	}
	public String getAccExamCnt() {
		return accExamCnt;
	}
	public void setAccExamCnt(String accExamCnt) {
		this.accExamCnt = accExamCnt;
	}
	public String getAccExamCompCnt() {
		return accExamCompCnt;
	}
	public void setAccExamCompCnt(String accExamCompCnt) {
		this.accExamCompCnt = accExamCompCnt;
	}
	public String getCareCnt() {
		return careCnt;
	}
	public void setCareCnt(String careCnt) {
		this.careCnt = careCnt;
	}
	public String getClearCnt() {
		return clearCnt;
	}
	public void setClearCnt(String clearCnt) {
		this.clearCnt = clearCnt;
	}
	public String getCreateDt() {
		return createDt;
	}
	public void setCreateDt(String createDt) {
		this.createDt = createDt;
	}
	public String getDeathCnt() {
		return deathCnt;
	}
	public void setDeathCnt(String deathCnt) {
		this.deathCnt = deathCnt;
	}
	public String getDecideCnt() {
		return decideCnt;
	}
	public void setDecideCnt(String decideCnt) {
		this.decideCnt = decideCnt;
	}
	public String getExamCnt() {
		return examCnt;
	}
	public void setExamCnt(String examCnt) {
		this.examCnt = examCnt;
	}
	public String getResultNegCnt() {
		return resultNegCnt;
	}
	public void setResultNegCnt(String resutlNegCnt) {
		this.resultNegCnt = resutlNegCnt;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getStateDt() {
		return stateDt;
	}
	public void setStateDt(String stateDt) {
		this.stateDt = stateDt;
	}
	public String getStateTime() {
		return stateTime;
	}
	public void setStateTime(String stateTime) {
		this.stateTime = stateTime;
	}
	public String getUpdateDt() {
		return updateDt;
	}
	public void setUpdateDt(String updateDt) {
		this.updateDt = updateDt;
	}
}
