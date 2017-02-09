package com.fj;

import com.fj.model.LED;
import com.pi4j.device.piface.PiFace;

import java.util.Arrays;

/**
 * Created by falkj on 23.12.2016.
 */
public class LedHandler {
    private PiFace piFace;
    private boolean modify = false;

    public LedHandler(PiFace piFace) {
        modify = false;
        this.piFace = piFace;
    }

    public void toggleLed(LED led) {
        modify = false;
        piFace.getLed(led.getLed()).toggle();
    }

    public void turnOffLed(LED led) {
        modify = false;
        piFace.getLed(led.getLed()).on();
    }

    public void turnOffLed(LED... leds) {
        modify = false;
        Arrays.stream(leds).forEach(led -> piFace.getLed(led.getLed()).on());
    }

    public void turnOn(LED led) {
        modify = false;
        piFace.getLed(led.getLed()).off();
    }

    public void modifyLed(int timeout, LED led) {
        modify = true;
        while (modify){
            piFace.getLed(led.getLed()).blink(timeout/10,1000);
        }
    }

    public void modifyLeds(int red, int green, int blue) {
        modify = true;
        try {
            while (modify){
                piFace.getLed(LED.RED.getLed()).blink(red, 1000);
                Thread.sleep(red/2);
                System.out.println("red");
                piFace.getLed(LED.GREEN.getLed()).blink(green, 1000);
                Thread.sleep(green/2);
                System.out.println("green");
                piFace.getLed(LED.BLUE.getLed()).blink(blue, 1000);
                Thread.sleep(blue/2);
                System.out.println("blue");
            }
        }catch (Exception e){
            throw new RuntimeException("Bumm kaputt");
        }
    }
}
