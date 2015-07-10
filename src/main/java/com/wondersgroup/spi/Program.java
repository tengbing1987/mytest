package com.wondersgroup.spi;

public class Program {
    public static void main(String[] args) throws InstantiationException,
    IllegalAccessException, ClassNotFoundException {
    	Class.forName("com.wondersgroup.spi.SpiA");

    	Api api = new Api("a");
    	api.Send("段光伟");
    	
    	System.out.println(Api.class.getClassLoader());
    	System.out.println(String.class.getResource("").getPath());
}
}
