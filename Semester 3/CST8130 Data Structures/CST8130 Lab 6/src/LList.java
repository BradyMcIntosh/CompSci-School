
public class LList {
	private LLNode head;
	
	public LList() {
		head = null;
	}
	public void addAtHead (char newData) {
		LLNode newNode = new LLNode (newData);
		newNode.updateNode(head);
		head = newNode;
	}
	
	public void display() {
		LLNode temp = head;
		while (temp != null) {
			System.out.println (temp);
			temp = temp.getNext();
		}
	}
	
	public LLNode deleteAtHead ( ) {
		LLNode removedOne = head;
		head = head.getNext();
		return removedOne;
	}
	
	public boolean isEmpty() {
		if(null == head) {
			return true;
		}
		else {
			return false;
		}
	}
	

}
