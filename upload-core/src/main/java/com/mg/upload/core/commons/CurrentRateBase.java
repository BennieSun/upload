package com.mg.upload.core.commons;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by BennieSun on 2017/2/27.
 *
 * 汇率
 */
public class CurrentRateBase {

    private static int currentRateNT2USD = 32;

    private static AtomicBoolean atomicBoolean = new AtomicBoolean();


    /**
     * 默认台币兑换美金 32：1
     * @return
     */
    public static int getCurrentRateTWD2USD() {
        return currentRateNT2USD;
    }

    public static void setCurrentRateNT2USD(int currentRateNT2USD) {
        if (atomicBoolean.compareAndSet(false,true)) {
            CurrentRateBase.currentRateNT2USD = currentRateNT2USD;
        }
    }
}
