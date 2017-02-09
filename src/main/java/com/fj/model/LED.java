package com.fj.model;

import com.pi4j.device.piface.PiFaceLed;

/**
 * Created by falkj on 23.12.2016.
 */
public enum LED {
    RED(PiFaceLed.LED6),
    GREEN(PiFaceLed.LED7),
    BLUE(PiFaceLed.LED5);

    private PiFaceLed led;

    LED(PiFaceLed led) {
        this.led = led;
    }
    public PiFaceLed getLed(){
        return this.led;
    }

}
