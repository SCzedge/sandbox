package com.blockchain.publish;

import com.blockchain.galaxy.Block;

public class Publishing {

	public void publishing() {
		Block genesisBlock = new Block("1st msg","0");
		System.out.println("b1 hash : "+genesisBlock.hash);
		
		Block secondBlock = new Block("2nd msg",genesisBlock.hash);
		System.out.println("b2 hash : "+secondBlock.hash);
		
		Block thirdBlock = new Block("3rd msg",secondBlock.hash);
		System.out.println("b3 hash : "+ thirdBlock.hash);
	}
	
}
