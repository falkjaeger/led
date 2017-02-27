package com.fj.model;

import com.pi4j.device.piface.PiFace;

/**
 * Created by falkj on 06.02.2017.
 */
public class LedThread extends Thread {
    private LED led;
    private PiFace piface;
    private com.pi4j.component.light.LED pifaceLed;
    private long offTimeMs = 100;
    private long onTimeMs = 0;
    private boolean run = true;

    public LedThread(LED led) {
        this.led = led;
        this.piface = PiFaceSingleton.getInstance().getPiFace();
        pifaceLed = piface.getLed(led.getLed());
    }


    public void run() {
        try {
            while (run) {
                if (onTimeMs > 0 && offTimeMs == 0) {
                    if (pifaceLed.isOn())
                        pifaceLed.off();
                    Thread.sleep(onTimeMs);
                } else if (onTimeMs == 0 && offTimeMs > 0) {
                    if (pifaceLed.isOff()) {
                        pifaceLed.on();
                    }
                } else if (onTimeMs > 0 && offTimeMs > 0) {
                    pifaceLed.off();
                    Thread.sleep(onTimeMs);
                    pifaceLed.on();
                    Thread.sleep(offTimeMs);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void end() {
        this.run = false;
    }

    public LED getLed() {
        return led;
    }

    public void setLed(LED led) {
        this.led = led;
    }

    public long getOffTimeMs() {
        return offTimeMs;
    }

    public void setOffTimeMs(long offTimeMs) {
        this.offTimeMs = offTimeMs;
        run=true;
    }

    public long getOnTimeMs() {
        return onTimeMs;
    }

    public void setOnTimeMs(long onTimeMs) {
        this.onTimeMs = onTimeMs;
        run=true;
    }
}
