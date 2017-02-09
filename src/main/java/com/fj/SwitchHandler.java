package com.fj;

import com.fj.model.LED;
import com.pi4j.component.switches.SwitchListener;
import com.pi4j.component.switches.SwitchState;
import com.pi4j.device.piface.PiFace;
import com.pi4j.device.piface.PiFaceLed;
import com.pi4j.device.piface.PiFaceSwitch;

import java.io.IOException;

/**
 * Created by falkj on 23.12.2016.
 */
public class SwitchHandler {
    private static PiFaceLed red = LED.RED.getLed();
    private static PiFaceLed green = LED.GREEN.getLed();
    private static PiFaceLed blue = LED.BLUE.getLed();
    private PiFace piface;

    public SwitchHandler(PiFace piFace) {
        this.piface = piFace;
    }

    public void initListener() throws IOException {
        LedHandler ledHandler = new LedHandler(piface);
        piface.getSwitch(PiFaceSwitch.S1).addListener((SwitchListener) event -> {
            if (event.getNewState() == SwitchState.ON) {
                ledHandler.toggleLed(LED.RED);
            }

        });

        piface.getSwitch(PiFaceSwitch.S2).addListener((SwitchListener) event -> {
            if (event.getNewState() == SwitchState.ON) {
                ledHandler.toggleLed(LED.GREEN);
            }
        });

        piface.getSwitch(PiFaceSwitch.S3).addListener((SwitchListener) event -> {
            if (event.getNewState() == SwitchState.ON) {
                ledHandler.toggleLed(LED.BLUE);
            }
        });

        piface.getSwitch(PiFaceSwitch.S4).addListener((SwitchListener) event -> {
            if (event.getNewState() == SwitchState.ON) {
                ledHandler.turnOffLed(LED.RED,LED.BLUE,LED.GREEN);
            }
        });
    }
}
