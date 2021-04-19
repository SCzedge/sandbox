package com.blockchain.publish;

import java.util.ArrayList;

import com.blockchain.galaxy.Block;
import com.google.gson.GsonBuilder;

public class Publishing {

	public void publishing() {
		Block genesisBlock = new Block("1stmsg","0");
		System.out.println("b1 hash : "+genesisBlock.hash);
		
		Block secondBlock = new Block("2ndmsg",genesisBlock.hash);
		System.out.println("b2 hash : "+secondBlock.hash);
		
		Block thirdBlock = new Block("3rdmsg",secondBlock.hash);
		System.out.println("b3 hash : "+ thirdBlock.hash);
		
		
		ArrayList<Block> blockChain = new ArrayList<Block>();
		blockChain.add(new Block("1st","0"));
		blockChain.add(new Block("2nd",blockChain.get(blockChain.size()-1).hash));
		blockChain.add(new Block("3rd",blockChain.get(blockChain.size()-1).hash));
		
//		String blockChainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockChain);
//		System.out.println("blockChainJson : "+blockChainJson);
		
		
		isValid(blockChain);
		
	}
	public static Boolean isValid(ArrayList<Block> blockChain) {
		Block currentBlock;
		Block prevBlock;
//		String hashTarget = new String(new char[difficulty]).replace('\0','0');
		
		for(int i=1; i<blockChain.size();i++) {
			currentBlock = blockChain.get(i);
			prevBlock = blockChain.get(i-1);
			
			if(!currentBlock.hash.equals(currentBlock.calculateHash())) {
				System.out.println("current not equal");
				return false;
			}
			if(!prevBlock.hash.equals(currentBlock.prevHash)) {
				System.out.println("prev not equal");
				return false;
			}
		}
		System.out.println("true");
		return true;
	}
	
}
