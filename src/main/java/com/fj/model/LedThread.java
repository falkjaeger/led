package com.fj.model;

import com.fj.LedHandler;
import com.pi4j.device.piface.PiFace;
import com.pi4j.device.piface.impl.PiFaceDevice;
import com.pi4j.wiringpi.Spi;

import java.io.IOException;

/**
 * Created by falkj on 06.02.2017.
 */
public class LedThread extends Thread {
    private LedHandler ledHandler;
    private LED led;
    private long offTimeMs;
    private long onTimeMs;
    private boolean run;

    public LedThread(LED led) {
        this.led = led;
        final PiFace piface;
        try {
            piface = new PiFaceDevice(PiFace.DEFAULT_ADDRESS, Spi.CHANNEL_0);
            ledHandler = new LedHandler(piface);
        } catch (IOException e) {
            throw new RuntimeException("Geht nicth");
        }

    }


    public void run() {
        try {
            while (run) {
                if (onTimeMs > 0) {
                    Thread.sleep(onTimeMs);
                    Thread.sleep(offTimeMs);
                    ledHandler.turnOn(this.led);
                    ledHandler.turnOn(this.led);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void end(){
        this.run=false;
    }
}
