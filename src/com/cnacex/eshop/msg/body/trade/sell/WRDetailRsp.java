package com.cnacex.eshop.msg.body.trade.sell;

import java.util.ArrayList;
import java.util.List;

import com.cnacex.eshop.msg.body.comm.Prop;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * 1、协议：仓单详单查询（60003004）  
 * 2、类描述：仓单详单查询详细消息体
 */
public class WRDetailRsp {
	//仓储会员编号
	@XStreamAlias("mid")
	private String mid;
	
	//仓储会员名称
	@XStreamAlias("mname")
	private String mName;
	
     //商品代码     
	@XStreamAlias("commcode")   
	private String commCode;
	
	
     //品类代码
	@XStreamAlias("classcode")    
	private String classCode;
    
	//市场代码     
	@XStreamAlias("markcode")    
	private String markCode;
    
	//货物数量      
	@XStreamAlias("qty")    
	private Long qty;
	
	//剩余数量    
	@XStreamAlias("rem")    
	private Long rem;
	
	//计量单位 
	@XStreamAlias("cargomu")    
	private Long cargoMU;
	
	//仓位编号
	@XStreamAlias("position")    
	private Long position;
    
	//商品描述     
	@XStreamAlias("detail")
	private String  detail;
     
	//内容图片1      
	@XStreamAlias("ctxpic1")    
	private String ctxPic1;
     
	//内容图片2     
	@XStreamAlias("ctxpic2")    
	private String ctxPic2;
	
	//内容图片3     
	@XStreamAlias("ctxpic3")    
	private String   ctxPic3;
	
	//扩展图片标志      
	@XStreamAlias("ctxpicex")    
	private String   ctxPiceX;
	
	//摘要信息1   
	@XStreamAlias("summary1")    
	private String   summary1;
	
	// 摘要信息2     
	@XStreamAlias("summary2")    
	private String   summary2;
	
	//摘要信息3     
	@XStreamAlias("summary3")    
	private String  summary3;
	
	//摘要信息4
	@XStreamAlias("summary4")    
	private String  summary4;
	
	//申请备注      
	 @XStreamAlias("remark")    
	 private String  remark;
	
	//属性
	@XStreamImplicit
	List<Prop> props = new ArrayList<Prop>();
	
 	//录入员      
 	@XStreamAlias("mgrmid")    
 	private String mgrMid;
 	
 	//入库时间      
 	@XStreamAlias("dol")    
 	private String dol;
 	
 	//过期时间      
 	@XStreamAlias("doe")    
 	private String doe;
 	
 	//仓单状态
 	@XStreamAlias("status")    
	private Long status;

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getCommCode() {
		return commCode;
	}

	public void setCommCode(String commCode) {
		this.commCode = commCode;
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public String getMarkCode() {
		return markCode;
	}

	public void setMarkCode(String markCode) {
		this.markCode = markCode;
	}

	public Long getQty() {
		return qty;
	}

	public void setQty(Long qty) {
		this.qty = qty;
	}

	public Long getRem() {
		return rem;
	}

	public void setRem(Long rem) {
		this.rem = rem;
	}

	public Long getCargoMU() {
		return cargoMU;
	}

	public void setCargoMU(Long cargoMU) {
		this.cargoMU = cargoMU;
	}

	public Long getPosition() {
		return position;
	}

	public void setPosition(Long position) {
		this.position = position;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getCtxPic1() {
		return ctxPic1;
	}

	public void setCtxPic1(String ctxPic1) {
		this.ctxPic1 = ctxPic1;
	}

	public String getCtxPic2() {
		return ctxPic2;
	}

	public void setCtxPic2(String ctxPic2) {
		this.ctxPic2 = ctxPic2;
	}

	public String getCtxPic3() {
		return ctxPic3;
	}

	public void setCtxPic3(String ctxPic3) {
		this.ctxPic3 = ctxPic3;
	}

	public String getCtxPiceX() {
		return ctxPiceX;
	}

	public void setCtxPiceX(String ctxPiceX) {
		this.ctxPiceX = ctxPiceX;
	}

	public String getSummary1() {
		return summary1;
	}

	public void setSummary1(String summary1) {
		this.summary1 = summary1;
	}

	public String getSummary2() {
		return summary2;
	}

	public void setSummary2(String summary2) {
		this.summary2 = summary2;
	}

	public String getSummary3() {
		return summary3;
	}

	public void setSummary3(String summary3) {
		this.summary3 = summary3;
	}

	public String getSummary4() {
		return summary4;
	}

	public void setSummary4(String summary4) {
		this.summary4 = summary4;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<Prop> getProps() {
		return props;
	}

	public void setProps(List<Prop> props) {
		this.props = props;
	}

	public String getMgrMid() {
		return mgrMid;
	}

	public void setMgrMid(String mgrMid) {
		this.mgrMid = mgrMid;
	}

	public String getDol() {
		return dol;
	}

	public void setDol(String dol) {
		this.dol = dol;
	}

	public String getDoe() {
		return doe;
	}

	public void setDoe(String doe) {
		this.doe = doe;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}
}
