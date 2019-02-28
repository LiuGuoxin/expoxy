package com.guoxin;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Expoxy  {
	public String sierelNum ;
	public String type;
	public Timestamp strorageDate;
	public Staff storager;
	public int status;
	public Timestamp unfreezeDate;
	public Staff unfreezer;
	public Timestamp useDate;
	public Staff user;
	public Timestamp callbackDate;
	public int callbacker;
	private static String regex = "([a-zA-Z]|[0-9]){5}-[0-9]{8}-[s|l|S|L]{1}[0-9]{7}";
	private static String regexExpoxyL = "([a-zA-Z]|[0-9]){5}-[0-9]{8}-[l|L]{1}[0-9]{7}";
	private static String regexExpoxyS = "([a-zA-Z]|[0-9]){5}-[0-9]{8}-[s|S]{1}[0-9]{7}";
	public static int STORAGED = 1;
	public static int FREEZED = 2;
	public static int USED = 3;
	public static int CALLBACKED = 4;
	
//	private static Pattern p= Pattern.compile("[a-zA-Z]|[0-9]{5}-[0-9]{8}-[s|l|S|L]{1}[0-9]{7}");

	public  Expoxy(String sierelNum,Timestamp strorageDate,Staff storager,int status){
		this.sierelNum = sierelNum;
		this.strorageDate=strorageDate;
		this.storager =storager;
		this.status =status;
		char[] c= new char[1];
		sierelNum.getChars(16, 16, c, 0);
		if(Pattern.matches(regexExpoxyL, sierelNum)) {
			type="大胶水";
		} else if(Pattern.matches(regexExpoxyS, sierelNum)){
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
