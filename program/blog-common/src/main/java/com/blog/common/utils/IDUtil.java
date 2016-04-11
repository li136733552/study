package com.blog.common.utils;

import java.util.Random;

public class IDUtil {

	public static String getImageName(){
		long millis = System.currentTimeMillis();
		
		Random rand = new Random();
		int randInt = rand.nextInt(999);
		return millis + String.format("%03d", randInt);
	}
}
