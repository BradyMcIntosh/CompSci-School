import java.util.Random;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/************************************************************************************************************
Purpose:  		This class holds a list of grades and methods for manipulating them
Author:  		Linda Crane and Brady McIntosh
Course: 		F2018 - CST8130
Lab Section: 	310
Data members:  	blockChain : LinkedList - a list of grade blocks
				courseName : String - the name of this list
               	
Methods: 	default constructor(String) - sets name of course and initializes list
			toString() : String - returns an iterated, line-separated representation of this class' list data
			getName() : String - returns this class' "courseName"
			verifyChain() : boolean - verifies that all blocks are good, returns true if so, false if not
			addBlock(Scanner) - adds a good or bad block, according to user choice
			fix() - removes all bad blocks from the list and updates previous hashes on good blocks as appropriate                                        
         

*************************************************************************************************************/

public class BlockChain {
	private LinkedList<Block> blockChain;
	private String courseName = "NotEntered";
	
	public BlockChain ( String courseName) {
		this.courseName = new String (courseName);
		blockChain = new LinkedList<Block>();
	}
	
	public String toString() {
		String ret = String.format("Course: %s\n", courseName);
		for(int i = 0; i < blockChain.size(); i++) {
			ret += String.format("\t\t%s\n", blockChain.get(i).toString());
		}
		return ret;
	}
	
	public String getName() {
		return courseName;
	}
	
	public boolean verifyChain() {
		
		for(int i = 1; i < blockChain.size(); i++) {
			
			if(!blockChain.get(i).isEqual(blockChain.get(i-1))) {
				return false;
			}
		}
		
		return true;
	}
	
	public void addBlock(Scanner keyboard) {
		Random random = new Random();
		float prevHash = 0f;
		
		System.out.println("Add a good or bad block? (\'g\' for good, anything else for bad) ");
		if(keyboard.next().equals("g")) {
			try {
				prevHash = blockChain.getLast().getCurrentHash();
			}
			catch(NoSuchElementException nse) {
			}
		}
		else {
			prevHash = random.nextFloat();
		}
		
		Block block = new Block();
		block.addInfoToBlock(keyboard, prevHash);
		blockChain.add(block);
	}
	
	public void fix() {
		
		if(!blockChain.isEmpty()) {
			if(blockChain.size() > 1) {
				
				Block prevBlock = blockChain.get(0);
				Block currBlock;
				Block lastValid = prevBlock;
				int i = 1;
				
				while(i < blockChain.size()) {
					currBlock = blockChain.get(i);
					System.out.printf("%d. curr: %.2f... ", i, currBlock.getCurrentHash(), prevBlock.getCurrentHash());
					if(!currBlock.isEqual(prevBlock)) {
						blockChain.remove(i);
						System.out.println("NOT valid!");
					}
					else {
						currBlock.updatePreviousHash(lastValid.getCurrentHash());
						lastValid = currBlock;
						System.out.println("valid!");
						i++;
					}
					prevBlock = currBlock;
				}
			}
			else {
				System.out.println("Only one block - the first block is always valid.");
			}
		}
		else {
			System.out.println("No blocks to fix!");
		}
	}
}
