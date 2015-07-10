package com.wondersgroup.generic;

public class GenericTest {
	public static void main(String[] args) {

		Box<Number> name = new Box<Number>(99);
        Box<Integer> age = new Box<Integer>(712);
        //getData(name);
        //getData(age);  
        getUpperNumberData(name); 
        getUpperNumberData(age); 
    }

	public static void getData(Box<?> data){
        System.out.println("data :" + data.getData());
    }
	
	public static void getUpperNumberData(Box<? extends Number> data){
        System.out.println("data :" + data.getData());
    }
}
