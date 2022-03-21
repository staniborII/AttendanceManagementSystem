package com.genesis.utility;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class UserActivityLog {
	
	public String logActivity(String entry) {
		
		String s = "Hello";
		String fileName = "C:\\Users\\stani\\eclipse-workspace\\LogUserActivity\\log.txt";
		
		try {
			FileWriter fw = new FileWriter(fileName);
			
			for(int i=0; i<entry.length(); i++) {
				fw.write(entry.charAt(i));
			}
			System.out.println("Writting successful");
			fw.close();
			
			//-1 is end of file
			
			int ch = 0;
			
			FileReader fr = new FileReader(fileName);
			
			/*
			 * we read once before reading with a while loop
			 * because if we immediately start reading with a while loop,
			 * we would also print the character when ch = -1
			 */
			
			ch = fr.read();
			
			while(ch!=-1) {
				if(ch==-1) {
					break;
				}
				System.out.print((char)ch);
				ch = fr.read(); //reads next character
			}
			fr.close();
		}catch (IOException ioe) {
			// TODO: handle exception
			System.out.println("IOException: "+ioe);
			ioe.getMessage();
			ioe.printStackTrace();
		}catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
			e.printStackTrace();
		}
		
		return null;
		
	}
}
