package com.zy.many.test.comparable;

import java.util.ArrayList;  
import java.util.Collections;
import java.util.List;  
  
class Student implements Comparable<Object>{  
    private String name;  
    private int ranking;  
  
    public Student(String name, int ranking){  
        this.name = name;  
        this.ranking = ranking;  
    }   
  
    public String toString(){  
        return this.name + ":" + this.ranking;  
    }

	@Override
	public int compareTo(Object o) {
		Student s = (Student)(o);  
        return this.ranking - s.ranking;  
	}  
}  
  
public class DataComparable{  
    public static void main(String[] args){
        List<Student> arr = new ArrayList<Student>();  
        arr.add(new Student("Jack",10));  
        arr.add(new Student("Bill",23));  
        arr.add(new Student("Rudy",7));  
        arr.add(new Student("heder",9));  
  
        System.out.println(arr);  
        Collections.sort(arr);
        System.out.println(arr);  
        
    }   
}  













