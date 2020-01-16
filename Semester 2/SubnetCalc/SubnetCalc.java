import java.util.Scanner;

/**
 * Subnet Calculator
 * 
 * Calculates the subnet space and related properties
 * of any given IP address with subnet mask.
 * 
 * WARNING!! Written for extra credit the week before finals.
 * Comments are few and far between, code readability is not good.
 * Proceed at your own risk!
 * 
 * @author Brady McIntosh
 * @version 1.0
 * @since 30 Apr 2018
 */
public class SubnetCalc {

	private static Scanner in = new Scanner(System.in);
	
	private Octet[] hostIP = new Octet[4];
	private Octet[] subnetMask = new Octet[4];
	private int subnetBits, borrowedBits, subnetNum, nativeBits;
	private int hostBits, hostNum, hostID;
	private int cellWidth, numWidth;
	private String[][][] addresses;
	private String subnetClass = new String();
	private Octet[] nativeMask = new Octet[4];
	private Octet[] broadcastIP = new Octet[4];
	private Octet[] wireIP = new Octet[4];
	
	static boolean valid;
	static boolean active = true;
	
	public SubnetCalc(String input) {
		
		valid = true;
		
		initialOct(hostIP);
		initialOct(subnetMask);
		initialOct(nativeMask);
		initialOct(broadcastIP);
		initialOct(wireIP);
		
		// hostIP & subnet bits
		try {
			// given 255.252.250.240/25
			int x1 = input.indexOf('.'); // 3
			String s1 = input.substring(0,x1); // "255"
			int x2 = input.indexOf('.', x1+1); // 7
			String s2 = input.substring(x1+1,x2); // "252"
			int x3 = input.indexOf('.', x2+1); // 11
			String s3 = input.substring(x2+1,x3); // "250"
			int x4 = input.indexOf('/', x2+1); // 15
			String s4 = input.substring(x3+1,x4); // "240"
			
			String slash = input.substring(x4+1); // "25"
		
			subnetBits = Integer.parseInt(slash); // 25
			
			hostIP[0].setDec(Integer.parseInt(s1));
			hostIP[1].setDec(Integer.parseInt(s2));
			hostIP[2].setDec(Integer.parseInt(s3));
			hostIP[3].setDec(Integer.parseInt(s4));
		}
		catch(Exception e) {
			System.out.println("Error: Invalid format");
			valid = false;
			return;
		}
		
		for(int i = 0; i < hostIP.length; i++) {
			if (hostIP[i].getInt() < 0 || hostIP[0].getInt() > 255) {
				System.out.println("Error: IP out of range: " + hostIP[i].getInt());
				valid = false;
				return;
			}	
		}
		
		if (hostIP[0].getInt() > 223) {
			System.out.println("Error: Undefined network: " + hostIP[0].getInt());
			valid = false;
			return;
		}
		
		// subnet mask
		subnetMask = setMask(subnetBits);
		
		if(!valid) {
			return;
		}
		
		// class and native mask
		if((hostIP[0].getInt() >= 1) && (hostIP[0].getInt() <= 126)) {
			subnetClass = "A";
			nativeBits = 8;
			nativeMask = setMask(8);
		}
		else if((hostIP[0].getInt() >= 128) && (hostIP[0].getInt() <= 191)) {
			subnetClass = "B";
			nativeBits = 16;
			nativeMask = setMask(16);
		}
		else if((hostIP[0].getInt() >= 192) && (hostIP[0].getInt() <= 223)) {
			subnetClass = "C";
			nativeBits = 24;
			nativeMask = setMask(24);
		}
		else {
			System.out.println("Error: Invalid IP class: " + hostIP[0].getInt());
			valid = false;
			return;
		}
		
		// borrowed bits
		borrowedBits = findBorrowed(nativeMask, subnetMask);
		if(borrowedBits < 0) {
			return;
		}
		
		// number of subnets
		subnetNum = (int)Math.pow(2, borrowedBits);
		
		// number of hosts per subnet
		hostBits = 32 - subnetBits;
		hostNum = (int)Math.pow(2, hostBits) - 2;
		
		
		if(nativeBits == subnetBits) {
			hostID = 1;
		}
		else {
			// host ID
			String tempID = new String();
			for(int i = 0; i < hostIP.length; i++) {
				
				for(int j = 0; j < hostIP[i].getBin().length(); j++) {
					
					if(((i*8)+1 + j) > nativeBits && ((i*8)+1 + j) <= subnetBits) {
						
						tempID = tempID + hostIP[i].getBin().charAt(j);
					}
				}
			}
			hostID = Integer.parseInt(tempID, 2) + 1;
		}
		
		String[] tempB = new String[4];
		tempB[0] = new String();
		tempB[1] = new String();
		tempB[2] = new String();
		tempB[3] = new String();
		
		for(int i = 0; i < hostIP.length; i++) {
			
			for(int j = 0; j < hostIP[i].getBin().length(); j++) {
			
				if(((i*8)+1 + j) > (subnetBits)) {
					
					tempB[i] = tempB[i] + "1";
				}
				else {
					
					tempB[i] = tempB[i] + hostIP[i].getBin().charAt(j);
				}
			}
		}
		
		for(int i = 0; i < broadcastIP.length; i++) {
			
			broadcastIP[i].setBin(tempB[i]);
		}
		
		tempB[0] = new String();
		tempB[1] = new String();
		tempB[2] = new String();
		tempB[3] = new String();
		
		for(int i = 0; i < hostIP.length; i++) {
			
			for(int j = 0; j < hostIP[i].getBin().length(); j++) {
			
				if(((i*8)+1 + j) > (subnetBits)) {
					
					tempB[i] = tempB[i] + "0";
				}
				else {
					
					tempB[i] = tempB[i] + hostIP[i].getBin().charAt(j);
				}
			}
		}
		
		for(int i = 0; i < wireIP.length; i++) {
			
			wireIP[i].setBin(tempB[i]);
		}
	}
	
	private int findBorrowed(Octet[] nat, Octet[] mas) {
		
		int bor = 0;
		
		for(int i = 0; i < nat.length; i++) {
			//System.out.print("i: " + i + ", j: ");
			for(int j = 0; j < nat[i].getBin().length(); j++) {
				//System.out.print(j);
				if(nat[i].getBin().charAt(j) == '0' 
					&& mas[i].getBin().charAt(j) == '1') {
					bor++;
					//System.out.print("!");
				}
				else if(nat[i].getBin().charAt(j) == '1' 
					&& mas[i].getBin().charAt(j) == '0') {
					System.out.println("Error: Subnet mask larger than native mask");
					valid = false;
					return -1;
				}
			}
			//System.out.println();
		}
		
		return bor;
	}
	
	private Octet[] setMask(int bits) {
		
		//System.out.println("setMask/");
		//System.out.println("bits = " + bits);
		
		Octet[] ret = new Octet[4];
		
		if(bits > 30 || bits < 8) {
			System.out.println("Subnet mask out of range: " + bits);
			valid = false;
			return ret;
		}
		
		String temp = new String();
		
		for(int i = 0; i < bits; i++) {
			temp = temp + "1";
		}
		while(temp.length() < 32) {
			temp = temp + "0";
		}
		
		//System.out.println("temp: " + temp);
		
		for(int i = 0; i < ret.length; i++) {
			ret[i] = new Octet();
			ret[i].setBin(temp.substring(i*8, (i*8) + 8));
		}

		//System.out.println("/setMask");
		
		return ret;
	}
	
	private void initialOct(Octet[] o) {
		
		for (int i = 0; i < o.length; i++) {
			o[i] = new Octet();
		}
	}
	
	private String returnOct(Octet[] o) {
		
		String ret = new String();
			
		for(int i = 0; i < o.length; i++) {
			ret = ret + o[i].getInt();
			if(i < o.length - 1) {
				ret = ret + ".";
			}
		}
		return ret;
	}
	
	private void setTable() {
		
		final int[] width = new int[4];
		
		width[0] = Integer.toString(hostIP[0].getInt()).length();
		width[3] = 3;

		if(nativeBits >= 16) {
			// if it is larger than class A
			width[1] = Integer.toString(hostIP[1].getInt()).length();
			
			if(nativeBits >= 24) {
				// if it is class C
				width[2] = Integer.toString(hostIP[2].getInt()).length();
			}
			else {
				// if it is class B
				width[2] = 3;
			}
			
		}
		else {
			// if it is class A
			width[1] = 3;
			width[2] = 3;
		}
		
		cellWidth = width[0] + width[1] + width[2] + width[3] + 3;
		
		if(cellWidth < 9) {
			cellWidth = 9;
		}
		
		numWidth = String.valueOf(subnetNum).length();
		
		if(numWidth < 9) {
			numWidth = 9;
		}
		
		int[][] table = new int[subnetNum][4];
		
		String calc = new String();
		
		int s1 = 0, s2 = 0, s3 = 0;
		
		if(borrowedBits > 8) {
			
			calc = "00000000";
			s1 = 8;
			
			if(borrowedBits > 16) {
				
				calc = calc + "00000000";
				s2 = 8;
				
				for(int j = 0; j < borrowedBits - 16; j++) {
					
					calc = calc + "0";
					s3++;
				}
			}
			else {
				
				for(int j = 0; j < borrowedBits - 8; j++) {
					
					calc = calc + "0";
					s2++;
				}
			}
			
		}
		else { 
			
			for(int j = 0; j < borrowedBits; j++) {
				
				calc = calc + "0";
				s1++;
			}
		}
		
		addresses = new String[table.length][4][4];
		
		for(int i = 0; i < table.length; i++) {
			
			// finding the relevant addresses			
			addresses[i][0][0] = hostIP[0].getBin();
			
			if(subnetClass.equals("B") || subnetClass.equals("C")) {
				
				addresses[i][0][1] = hostIP[1].getBin();
			}
			else {
				if(s1 > 0) {
					addresses[i][0][1] = calc.substring(0, s1);
				}
				else {
					addresses[i][0][1] = "0";
				}
			}
			
			// finds network address
			if(subnetClass.equals("C")) {
				addresses[i][0][2] = hostIP[2].getBin();
			}
			else if(subnetClass.equals("B")){
				if(s1 > 0) {
					addresses[i][0][2] = calc.substring(0, s1);
				}
				else {
					addresses[i][0][2] = "0";
				}
			}
			else {
				if(s2 > 0) {
					if(s1 > 0) {
						addresses[i][0][2] = calc.substring(s1, s1 + s2);
					}
					else {
						addresses[i][0][2] = calc.substring(0, s1);
					}
					
				}
				else {
					addresses[i][0][2] = "0";
				}
				
			}
			
			if(subnetClass.equals("C")) {
				if(s1 > 0) {
					addresses[i][0][3] = calc.substring(0, s1);
				}
				else {
					addresses[i][0][3] = "0";
				}
			}
			else if (subnetClass.equals("B")) {
				if(s2 > 0) {
					if(s1 > 0) {
						addresses[i][0][3] = calc.substring(s1, s1 + s2);
					}
					else {
						addresses[i][0][3] = calc.substring(0, s1);
					}
					
				}
				else {
					addresses[i][0][3] = "0";
				}
			}
			else {
				if(s3 > 0) {
					if(s2 > 0) {
						if(s1 > 0) {
							addresses[i][0][3] = calc.substring(s1 + s2, s1 + s2 + s3);
						}
						else {
							addresses[i][0][3] = calc.substring(s1, s1 + s2);
						}
					}
					else {
						addresses[i][0][3] = calc.substring(0, s1);
					}
				}
				else {
					addresses[i][0][3] = "0";
				}
			}
			
//			System.out.println(printArray(addresses[i][0]));
			
			// finds first IP
			String tempFip = new String();
			while(tempFip.length() < hostBits-1) {
				tempFip = tempFip + "0";
			}
			tempFip = tempFip + "1";
			
			addresses[i][1] = setAddresses(addresses[i][1], addresses[i][0], tempFip);
			
			// finds last IP
			String tempLip = new String();
			while(tempLip.length() < hostBits-1) {
				tempLip = tempLip + "1";
			}
			tempLip = tempLip + "0";
			
			addresses[i][2] = setAddresses(addresses[i][2], addresses[i][0], tempLip);
			
			// finds broadcast address
			String tempBroad = new String();
			while(tempBroad.length() < hostBits) {
				tempBroad = tempBroad + "1";
			}
			
			addresses[i][3] = setAddresses(addresses[i][3], addresses[i][0], tempBroad);
			// ^^ addresses
			
			
			for(int f = 0; f < addresses[i].length; f++) {
				for(int j = 0; j < addresses[i][0].length; j++) {
					
					if(addresses[i][f][j] != null) {
						while(addresses[i][f][j].length() < 8) {
							addresses[i][f][j] = addresses[i][f][j] + "0";
						}
						addresses[i][f][j] = String.valueOf(Integer.valueOf(addresses[i][f][j], 2));
					}
				}
			}
			
			// going to the next subnet
			if(subnetNum > 1) {
				calc = binaryAdd(calc, 1);
			}
		}
	}
	
	private void printTable(int first, int last) {
		
		
//		System.out.println("first: " + first + ", last: " + last);
		
		System.out.print("┏");
		
		for(int i = 0; i < 5; i++) {
			if(i < 1) {
				for(int j = 0; j < numWidth; j++) {
					System.out.print("━");
				}
			}
			else {
				for(int j = 0; j < cellWidth; j++) {
					System.out.print("━");
				}
			}
			if(i < 4) {
				System.out.print("┳");
			}
		}
		
		System.out.println("┓");
		
		
		System.out.print("┃");
		System.out.print(justifyLeft("Subnet #", numWidth));
		System.out.print("┃");
		System.out.print(justifyLeft("Network", cellWidth));
		System.out.print("┃");
		System.out.print(justifyLeft("First IP", cellWidth));
		System.out.print("┃");
		System.out.print(justifyLeft("Last IP", cellWidth));
		System.out.print("┃");
		System.out.print(justifyLeft("Broadcast", cellWidth));
		System.out.println("┃");
		
		for(int i = first; i <= last; i++) {
			
//			System.out.println("i: " + i);
			
			if(i <= last) {
				
				System.out.print("┣");
				
				for(int j = 0; j < 5; j++) {
					if(j < 1) {
						for(int f = 0; f < numWidth; f++) {
							System.out.print("━");
						}
					}
					else {
						for(int f = 0; f < cellWidth; f++) {
							System.out.print("━");
						}
					}
					if(j < 4) {
						System.out.print("╋");
					}
				}
				System.out.println("┫");
			}
			
			System.out.print("┃");
			System.out.print(justifyLeft(String.valueOf(i), numWidth));
			System.out.print("┃");
			System.out.print(justifyLeft(printArray(addresses[i][0]), cellWidth));
			System.out.print("┃");
			System.out.print(justifyLeft(printArray(addresses[i][1]), cellWidth));
			System.out.print("┃");
			System.out.print(justifyLeft(printArray(addresses[i][2]), cellWidth));
			System.out.print("┃");
			System.out.print(justifyLeft(printArray(addresses[i][3]), cellWidth));
			System.out.println("┃");
			
			if(i == last) {
				
				System.out.print("┗");
				for(int f = 0; f < 5; f++) {
					if(f < 1) {
						for(int j = 0; j < numWidth; j++) {
							System.out.print("━");
						}
					}
					else {
						for(int j = 0; j < cellWidth; j++) {
							System.out.print("━");
						}
					}
					if(f < 4) {
						System.out.print("┻");
					}
				}
				System.out.println("┛");
			}
		}
		
	}
	
	private String[] setAddresses(String[] addresses, String[] refAddresses, String temp) {
		
		// if there are more than 8 relevant bits
		if(temp.length() >= 8) {
			
			addresses[3] = temp.substring(temp.length()-8, temp.length());
//			System.out.println("add3 = " + addresses[3]);
			
			// if there are more than 16 relevant bits
			if(temp.length() >= 16) {
				
				addresses[2] = temp.substring(temp.length()-16, temp.length()-8);
//				System.out.println("add2 = " + addresses[2]);
				
				// if there are more than 24 relevant bits
				if(temp.length() >= 24) {
					
					addresses[1] = temp.substring(temp.length()-24, temp.length()-16);
//					System.out.println("add1 = " + addresses[1]);
				}
				// if there are between 17 and 24 relevant bits
				else {
					addresses[1] = setIP(refAddresses[1], temp, 16);
				}
			}
			// if there are between 9 and 16 relevant bits
			else {
				addresses[2] = setIP(refAddresses[2], temp, 8);
			}
		}
		// if there are 8 or fewer relevant bits
		else {
			addresses[3] = setIP(refAddresses[3], temp, 0);
		}
		
		for(int j = 0; j < addresses.length; j++) {
			if(addresses[j] == null) {
				addresses[j] = refAddresses[j];
			}
		}
		
		return addresses;
	}
	
	private String setIP(String address, String tempip, int offset) {
		
//		System.out.println("address: " + address);
//		System.out.println("tempip: " + tempip);
//		System.out.println("offset: " + offset);
		
		tempip = tempip.substring(0, tempip.length()-offset);
//		System.out.println("cut tempip: " + tempip);
		
		while(tempip.length() < 8) {
			tempip = address.charAt((8-tempip.length())-1) + tempip;
		}
//		System.out.println("matched tempip: " + tempip);
		
		return tempip;
	}
	
	private String printArray(String[] s) {
		
		String ret = new String();
		
		for(int i = 0; i < s.length; i++) {
			ret = ret + s[i];
			if(i < s.length-1) {
				ret = ret + ".";
			}
		}
		
		return ret;
	}
	
	private String binaryAdd(String s, int a) {
		
		int l = s.length();
		
		int x = Integer.parseInt(s, 2);
		
		x += a;
		
		s = Integer.toBinaryString(x);
		
		while(s.length() < l) {
			s = "0" + s;
		}
		
		return s;
	}
	
	private String justifyLeft(String s, int w) {
		
		while(s.length() < w) {
			s = s + " ";
		}
		
		return s;
	}
	
	private void printSummary() {
		
		String tempIP = returnOct(hostIP);
		String tempSub = returnOct(subnetMask);
		int diff1 = new String(tempIP + "IP Address:  ").length() 
				- new String(tempSub + "Subnet Mask: ").length();

		if(diff1 < 0) {
			
			for(int i = diff1; i < 0; i++) {
				
				tempIP = tempIP + " ";
			}
		}
		else {
			for(int i = 0; i < diff1; i++) {
				tempSub = tempSub  + " ";
			}
		}
		
		String tempSubNum = String.valueOf(subnetNum);
		int diff2 = new String(tempIP + "IP Address:  ").length() - new String(
				String.valueOf(subnetNum) + "Number of Subnets: ").length();
		
		if(diff2 < 0) {
			for(int i = diff2; i < 0; i++) {
				
				tempIP = tempIP + " ";
				tempSub = tempSub + " ";
			}
		}
		else {
			for(int i = 0; i < diff2; i++) {
				tempSubNum = tempSubNum  + " ";
			}
		}
		
		System.out.println("Summary of subnet information:");
		
		System.out.print("\nIP Address:  " + tempIP);
		System.out.println("\tIP Class: " + subnetClass);
		
		System.out.print("Subnet Mask: " + tempSub);
		System.out.println("\tSubnet Bits: " + subnetBits);
		
		System.out.print("Number of Subnets: " + tempSubNum);
		System.out.println("\tHosts per Subnet: " + hostNum);
		
		System.out.println("\nSubnet ID of Host: " + hostID + "\n");
	}
	
	private int askRange(String type) {
		
		boolean test;
		int range = 0;
		do {
			test = true;
			System.out.println("Please enter the " + type + " subnet number.");
			try {
				range = Integer.valueOf(in.nextLine());
			}
			catch(Exception e) {
				System.out.println("Invalid input: Must be an integer.");
				test = false;
				continue;
			}
			
			if(range < 0 || range > subnetNum - 1) {
				System.out.println("Invalid input: Out of range.");
				System.out.println("Please enter a number between 0 and " + (subnetNum - 1));
				test = false;
				continue;
			}
		
		} while(!test);
		
		return range;
	}
	
	public static void main(String[] args) {
		
		SubnetCalc calc;
		
		do {
		
			do {
			System.out.println("Please enter a host IP address in decimal with slash notation.");
			System.out.println("It should be in this format: xxx.xxx.xxx.xxx/yy");
			
			calc = new SubnetCalc(in.nextLine());
			} while (!valid);
			
			calc.setTable();
			calc.printSummary();
			
			String input;
			char cInput;
			boolean aTest;
			
			do {
				aTest = true;
				System.out.println("Please select an option: ");
				System.out.println("s: Print subnet summary ");
				System.out.println("w: Print whole subnet table ");
				System.out.println("r: Print a range of subnets ");
				System.out.println("n: Enter a new host IP");
				System.out.println("q: Quit ");
				
				input = in.nextLine();
				
				try {
					cInput = input.toLowerCase().charAt(0);
					if(((cInput != 'w' && cInput != 'r') && (cInput != 'n' && cInput != 'q')) && cInput != 's') {
						throw new Exception();
					}
				}
				catch (Exception e) {
					System.out.println("Invalid input: Must be \"s\", \"w\", \"r\", \"n\" or \"q\".");
					aTest = false;
				}
				
				switch(input.toLowerCase().charAt(0)) {
				
					case 's':
						calc.printSummary();
						break;
					case 'w':
						calc.printTable(0, calc.subnetNum-1);
						break;
					case 'r':
						boolean rTest;
						int first, last;
						do {
							rTest = true;
							first = calc.askRange("first");
							last = calc.askRange("last");
							
							if(first > last) {
								System.out.println("Invalid input: Negative range!");
								System.out.println("Please make sure the first number is smaller than the last number.");
								rTest = false;
							}
						} while(!rTest);
						
//						System.out.println(first + ", " + last);
						calc.printTable(first,last);
						break;
					case 'n': 
						aTest = false;
						continue;
					case 'q': 
						active = false;
						aTest = false;
						break;
				}
			} while(aTest);
			
		} while(active);
		
		System.out.println("Goodbye.");
		
	}
}
