package com.fj.model;

import com.pi4j.device.piface.PiFace;
import com.pi4j.device.piface.impl.PiFaceDevice;
import com.pi4j.wiringpi.Spi;

import java.io.IOException;

/**
 * Created by falkj on 21.02.2017.
 */
public class PiFaceSingleton {
    private static PiFaceSingleton ourInstance = new PiFaceSingleton();
    private PiFace piFace;
    public static PiFaceSingleton getInstance() {
        return ourInstance;
    }

    public PiFace getPiFace() {
        return piFace;
    }

    private PiFaceSingleton() {
        try {
            piFace = new PiFaceDevice(PiFace.DEFAULT_ADDRESS, Spi.CHANNEL_0);
        } catch (IOException e) {
            throw new RuntimeException("Cant initialize piFace");
        }
    }
}
