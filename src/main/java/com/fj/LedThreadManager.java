package com.fj;

import com.fj.model.LED;
import com.fj.model.LedThread;

/**
 * Created by falkj on 06.02.2017.
 */
public class LedThreadManager {
    LedThread green;
    LedThread red;
    LedThread blue;

    public LedThreadManager() {
        this.green = new LedThread(LED.GREEN);
        this.red =  new LedThread(LED.RED);
        this.blue =  new LedThread(LED.BLUE);
    }
}
