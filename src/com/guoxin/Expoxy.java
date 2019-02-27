package com.guoxin;

import java.sql.Date;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Expoxy  {
	public String sierelNum ;
	public String type;
	public Date strorageDate;
	public Staff storager;
	public int status;
	public Date unfreezeDate;
	public Staff unfreezer;
	public Date useDate;
	public Staff user;
	public Date callbackDate;
	public int callbacker;
	private static String regex = "([a-zA-Z]|[0-9]){5}-[0-9]{8}-[s|l|S|L]{1}[0-9]{7}";
//	private static Pattern p= Pattern.compile("[a-zA-Z]|[0-9]{5}-[0-9]{8}-[s|l|S|L]{1}[0-9]{7}");

	public  Expoxy(String sierelNum,Date strorageDate,Staff storager,int status){
		this.sierelNum = sierelNum;
		this.strorageDate=strorageDate;
		this.storager =storager;
		this.status =status;
		char[] c= new char[1];
		sierelNum.getChars(15, 15, c, 0);
		if(c[0]=='L'||c[0]=='l') {
			type="大胶水";
		} else if(c[0]=='S'||c[0]=='s') {
			type="小胶水";
		}
	}
	
	public void storage(){
		
	}
	
	public void unfreeze(){
		
	}
	
	public void use(){
		
	}
	
	public void callBack(){
		
	}
	
	public void delete(){
		
	}
	
	public void update(){
		
	}
	
	public static ArrayList<Expoxy> searchFormDataBase(){
		
		
		return null;
		
	}
	
	public static boolean isExpoxy(String sierelNum) {
		return Pattern.matches(regex, sierelNum);
		
	}
}
