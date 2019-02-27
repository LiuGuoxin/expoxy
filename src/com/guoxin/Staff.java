package com.guoxin;

import java.util.regex.Pattern;

public class Staff {
public String staffNum;
public String name;
private static String regex = "[0-9]{1}0[S|A|s|a]{1}[0-9]{5}";

public Staff(String staffNum,String name){
	this.staffNum = staffNum;
	this.name = name;
}

public static Staff searchFormDataBase(){
	
	
	return null;
	
}

public static boolean isStaff(String staffNum) {
	return Pattern.matches(regex, staffNum);
}
}
