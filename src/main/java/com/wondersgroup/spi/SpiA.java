package com.wondersgroup.spi;

public class SpiA implements Spi {
    static {
        Api.Register("a", SpiA.class);
    }

    public void send(String msg) {
        System.out.println("SpiA：" + msg);
    }

}
