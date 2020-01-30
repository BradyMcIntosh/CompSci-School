import java.util.Random;
import java.util.Scanner;

public class BlockChain {
	private Block head = new Block (); // start the chain with the Genesis block
	private Block tail = head;
	private String courseName = "NotEntered";
	
	public BlockChain ( String courseName) {
		this.courseName = new String (courseName);
	}
	
	public void printBlockChain() {
		Block current = head;
		System.out.println("Chain for " + courseName + ": ");
		
		while (null != current) {
			System.out.println(current);
			current = current.next();
		}
	}
	
	public boolean verifyChain() {
			
		Block current = head;
		
		while (null != current.next()) {
			if(!(current.next().isEqual(current))) {
				return false;
			}
			current = current.next();
		}
		
		return true;
	}
	
	public void addBlock(Scanner keyboard) {
		Block newOne = new Block();
		if (newOne.addInfoToBlock(keyboard, tail.getCurrentHash())) {
			tail.updateNext(newOne);
			tail = newOne;
		}
		
	}
	
	public void addBadBlock(Scanner keyboard) {
		Random random = new Random();
		Block newOne = new Block();
		if (newOne.addInfoToBlock(keyboard, random.nextFloat())){
			// add to chain at tail
			tail.updateNext(newOne);
			tail = newOne;			
		}
		
	}
	
	
}
