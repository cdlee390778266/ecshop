package com.cnacex.eshop.msg.body.member;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

public class InvQueryRsp {
	@XStreamAlias("mid")
	private String mID;
	
	@XStreamAlias("memname")
	private String memName;
	
	@XStreamImplicit
	private List<Operator> operators;
	
	@XStreamAlias("operator")
	public static class Operator {
		
		@XStreamAlias("operid")
		private String	operID;
		
		@XStreamAlias("opertype")
		private char operType;
		
		@XStreamAlias("opername")
		private String operName;
		
		@XStreamAlias("opersex")
		private char operSex;
		
		
		@XStreamAlias("status")
		private String status;

		public String getOperID() {
			return operID;
		}

		public void setOperID(String operID) {
			this.operID = operID;
		}

		public String getOperName() {
			return operName;
		}

		public void setOperName(String operName) {
			this.operName = operName;
		}

		public char getOperType() {
			return operType;
		}

		public void setOperType(char operType) {
			this.operType = operType;
		}

		public char getOperSex() {
			return operSex;
		}

		public void setOperSex(char operSex) {
			this.operSex = operSex;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}
		
		
	}

	public String getmID() {
		return mID;
	}

	public void setmID(String mID) {
		this.mID = mID;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public List<Operator> getOperators() {
		return operators;
	}

	public void setOperators(List<Operator> operators) {
		this.operators = operators;
	}
}
