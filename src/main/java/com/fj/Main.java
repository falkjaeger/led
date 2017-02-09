package com.fj;

import com.fj.model.LED;
import com.pi4j.device.piface.PiFace;
import com.pi4j.device.piface.impl.PiFaceDevice;
import com.pi4j.wiringpi.Spi;
import spark.Request;
import spark.Response;
import spark.Spark;

import java.io.IOException;

import static spark.Spark.post;

/**
 * Created by falkj on 13.09.2016.
 */

public class Main {

    public static void main(String args[]) throws InterruptedException, IOException {
        final PiFace piface = new PiFaceDevice(PiFace.DEFAULT_ADDRESS, Spi.CHANNEL_0);
        SwitchHandler switchHandler = new SwitchHandler(piface);
        switchHandler.initListener();
        LedHandler ledHandler = new LedHandler(piface);
        Spark.port(8080);
        Spark.staticFiles.location("/static");
        post("/toggleLed", (Request req, Response res) -> {
            LED led = null;
            String ledString = req.queryParams("led");
            try {
                led = LED.valueOf(ledString);
            } catch (Exception e) {
                System.err.println("Led: " + ledString + " ins:t a valid Led\n" + e);
            }
            ledHandler.toggleLed(led);
            return "";
        });
        post("/allOff", (Request req, Response res) -> {
            ledHandler.turnOffLed(LED.RED, LED.BLUE, LED.GREEN);
            return "";
        });
        post("/modify", (Request req, Response res) -> {
            LED led = LED.valueOf(req.queryParams("led"));
            int timeout = Integer.parseInt(req.queryParams("timeout"));
            print(timeout);
            print(led.name());
            ledHandler.modifyLed(timeout,led);
            return "";
        });
    }

    private static void print(Object modified) {
        System.out.println(modified);
    }


}
