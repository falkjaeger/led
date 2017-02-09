package com.fj.model;

/**
 * Created by falkj on 04.02.2017.
 */
public class Modified {
    private String led;
    private int value;

    public Modified(String led, String value) {
        this.led = led;
        this.value = Integer.parseInt(value);
    }

    public String getLed() {
        return led;
    }

    public void setLed(String led) {
        this.led = led;
    }

    public int getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = Integer.parseInt(value);
    }
}
