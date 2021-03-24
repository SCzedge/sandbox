package com.websocket.test;

public class TestVo {
	private String msg;

//	public TestVo(String msg) {
//		super();
//		this.msg = msg;
//	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "TestVo [msg=" + msg + "]";
	}
	
}
