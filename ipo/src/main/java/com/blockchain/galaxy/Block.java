package com.blockchain.galaxy;

import java.util.Date;

public class Block {
	public String hash;
	public String prevHash;
	private String data;
	private long timeStamp;
	
	public Block( String data, String prevHash) {
		this.prevHash = prevHash;
		this.data = data;
		this.timeStamp = new Date().getTime();
		this.hash = calculateHash();
	}
	public String calculateHash() {
		String calculateHash = Utility.applySha256(
				prevHash+
				Long.toString(timeStamp)+
				data
				);
		return calculateHash;
	}
}
