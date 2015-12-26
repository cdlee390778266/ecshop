package com.cnacex.eshop.msg.body.report;

public class Daily {
	
	private int seqno;
	
	private String remark;
	
	private double incAmt;
	
	private double decrAmt;
	
	private double amt;
	
	private boolean bold;

	public int getSeqno() {
		return seqno;
	}

	public void setSeqno(int seqno) {
		this.seqno = seqno;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public double getIncAmt() {
		return incAmt;
	}

	public void setIncAmt(double incAmt) {
		this.incAmt = incAmt;
	}

	public double getDecrAmt() {
		return decrAmt;
	}

	public void setDecrAmt(double decrAmt) {
		this.decrAmt = decrAmt;
	}

	public double getAmt() {
		return amt;
	}

	public void setAmt(double amt) {
		this.amt = amt;
	}

	public boolean isBold() {
		return bold;
	}

	public void setBold(boolean bold) {
		this.bold = bold;
	}
	
	

}
