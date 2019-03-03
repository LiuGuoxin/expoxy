package com.guoxin;

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
	public Staff callbacker;
	public String place;
	private static String regex = "([a-zA-Z]|[0-9]){5}-[0-9]{8}-[s|l|S|L]{1}[0-9]{7}";
	private static String regexExpoxyL = "([a-zA-Z]|[0-9]){5}-[0-9]{8}-[l|L]{1}[0-9]{7}";
	private static String regexExpoxyS = "([a-zA-Z]|[0-9]){5}-[0-9]{8}-[s|S]{1}[0-9]{7}";
	public static int STORAGED = 1;
	public static int FREEZED = 2;
	public static int USED = 3;
	public static int CALLBACKED = 4;
	
//	private static Pattern p= Pattern.compile("[a-zA-Z]|[0-9]{5}-[0-9]{8}-[s|l|S|L]{1}[0-9]{7}");

	public long getUnfreezeDate(){
		return unfreezeDate.getTime();
	}
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
	
	public boolean isUnfrrezeComplete(){
		return (System.currentTimeMillis()-unfreezeDate.getTime())>(60*60*2*1000);
	}

	public boolean isUnfrrezeOverflow(){
		return (System.currentTimeMillis()-unfreezeDate.getTime())>(60*60*72*1000);
	}
	
	public void unfreeze(Staff unfreezer,Timestamp unfreezeDate){
		this.unfreezer = unfreezer;
		this.unfreezeDate = unfreezeDate;
	}
		
	public void use(Staff user,Timestamp useDate,String place){
		this.place = place;
		this.useDate = useDate;
		this.user = user;
	}
	
	public void callBack(Staff callbacker,Timestamp callbackDate){
		this.callbackDate= callbackDate;
		this.callbacker = callbacker;
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
