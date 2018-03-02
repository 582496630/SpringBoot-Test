package com.springboot.zy;

import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Test {

	public static void main(String[] args) {
		 Logger logger = LoggerFactory.getLogger(Test.class);
		 logger.info("slf4j");
		 System.out.println(Test.class.getResource("/"));
		 
		 String str = "1,3,d,f,s,sww,e,s,r";
		 StringTokenizer tokenizer = new StringTokenizer(str, ",");
		 
		 while (tokenizer.hasMoreTokens()) {
			 System.out.print(tokenizer.nextToken());
			 if (tokenizer.hasMoreElements()) {
				System.out.print("/");
			}
		 }
		 
	}

}
