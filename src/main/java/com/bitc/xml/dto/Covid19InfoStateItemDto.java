package com.bitc.xml.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="item")
public class Covid19InfoStateItemDto {
	private String accDefRate;
	private int accExamCnt;
	private int accExamCompCnt;
    private int careCnt;
    private int clearCnt;
    private String createDt;
    private int deathCnt;
    private int decideCnt;
    private int examCnt;
    private int resutlNegCnt;
    private int seq;
    private int stateDt;
    private String stateTime;
    private String updateDt;
    
    @XmlElement
	public String getAccDefRate() {
		return accDefRate;
	}
	public void setAccDefRate(String accDefRate) {
		this.accDefRate = accDefRate;
	}
	public int getAccExamCnt() {
		return accExamCnt;
	}
	public void setAccExamCnt(int accExamCnt) {
		this.accExamCnt = accExamCnt;
	}
	public int getAccExamCompCnt() {
		return accExamCompCnt;
	}
	public void setAccExamCompCnt(int accExamCompCnt) {
		this.accExamCompCnt = accExamCompCnt;
	}
	public int getCareCnt() {
		return careCnt;
	}
	public void setCareCnt(int careCnt) {
		this.careCnt = careCnt;
	}
	public int getClearCnt() {
		return clearCnt;
	}
	public void setClearCnt(int clearCnt) {
		this.clearCnt = clearCnt;
	}
	public String getCreateDt() {
		return createDt;
	}
	public void setCreateDt(String createDt) {
		this.createDt = createDt;
	}
	public int getDeathCnt() {
		return deathCnt;
	}
	public void setDeathCnt(int deathCnt) {
		this.deathCnt = deathCnt;
	}
	public int getDecideCnt() {
		return decideCnt;
	}
	public void setDecideCnt(int decideCnt) {
		this.decideCnt = decideCnt;
	}
	public int getExamCnt() {
		return examCnt;
	}
	public void setExamCnt(int examCnt) {
		this.examCnt = examCnt;
	}
	public int getResutlNegCnt() {
		return resutlNegCnt;
	}
	public void setResutlNegCnt(int resutlNegCnt) {
		this.resutlNegCnt = resutlNegCnt;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public int getStateDt() {
		return stateDt;
	}
	public void setStateDt(int stateDt) {
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
