package com.fj;

import com.fj.model.LED;
import com.fj.model.LedThread;
import com.pi4j.device.piface.PiFace;

import java.util.Arrays;

/**
 * Created by falkj on 23.12.2016.
 */
public class LedHandler {
    private PiFace piFace;
    private boolean modify = false;
    private LedThread red;
    private LedThread green;
    private LedThread blue;

    public LedHandler(PiFace piFace) {
        this.red = new LedThread(LED.RED);
        red.start();
        this.green = new LedThread(LED.GREEN);
        green.start();
        this.blue = new LedThread(LED.BLUE);
        blue.start();
        modify = false;
        this.piFace = piFace;
    }

    public void toggleLed(LED led) {
        System.out.println("LED: "+led.name());
        LedThread currentThread = getThreadByLed(led);
        System.out.println("ActualLed: "+currentThread.getLed().name());
        if (currentThread.getOnTimeMs() == 0) {
            System.out.println("should be on now");
            currentThread.setOnTimeMs(100);
            currentThread.setOffTimeMs(0);
        } else {
            System.out.println("should be off now");
            currentThread.setOnTimeMs(0);
            currentThread.setOffTimeMs(100);
        }

    }

    private LedThread getThreadByLed(LED led) {
        switch (led) {
            case RED:
                return this.red;

            case GREEN:
                return this.green;

            case BLUE:
                return this.blue;

        }
        throw new RuntimeException("Could not get Thread by Led: " + led);
    }

    public void turnOffLed(LED led) {
        LedThread currentThread = getThreadByLed(led);
        currentThread.setOffTimeMs(100);
        currentThread.setOnTimeMs(0);
    }

    public void turnOffLed(LED... leds) {
        Arrays.stream(leds).forEach(this::turnOffLed);
    }

    public void turnOn(LED led) {
        LedThread currentThread = getThreadByLed(led);
        currentThread.setOffTimeMs(100);
        currentThread.setOnTimeMs(0);
    }

    public void modifyLed(int timeout, LED led) {
        LedThread currentLedThread = getThreadByLed(led);
        currentLedThread.setOnTimeMs(calcOnTimeMs(timeout));
        currentLedThread.setOffTimeMs(calcOffTimeMs(timeout));
    }

    private long calcOffTimeMs(int timeout) {
        return 16-calcOnTimeMs(timeout);
    }

    private long calcOnTimeMs(int timeout) {
        return (long) (0.16*timeout);
    }


    public void stopIt() {
        red.end();
        blue.end();
        green.end();
    }
}
