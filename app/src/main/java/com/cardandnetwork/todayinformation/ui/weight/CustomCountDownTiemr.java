package com.cardandnetwork.todayinformation.ui.weight;

import android.os.CountDownTimer;
import android.os.Handler;

public class CustomCountDownTiemr implements Runnable {

    private int time;
    private int countDownTime;
    private final ICountDownHandler countDownHandler;
    private final Handler handler;
    private boolean isRun;

    //实时回调 当前秒数
    //支持动态传入总秒数
    //每过一秒 总秒数减一
    //总时间为0时 回掉完成的状态

    //观察者设计模式
    public CustomCountDownTiemr(int time, ICountDownHandler countDownHandler) {
        handler = new Handler();
        this.time = time;
        this.countDownTime = time;
        this.countDownHandler = countDownHandler;
    }

    //实时更新秒数
    @Override
    public void run() {
        if (isRun) {
            if (countDownHandler != null) {
                countDownHandler.onTick(countDownTime);
            }
            if (countDownTime == 0) {
                cancel();
                if (countDownHandler != null) {
                    countDownHandler.onFinish();
                }
            } else {
                countDownTime = time--;
                handler.postDelayed(this, 1000);
            }
        }
    }

    //开始循环
    public void start() {
        isRun = true;
        handler.post(this);
    }

    //跳出循环 终止
    public void cancel() {
        isRun = false;
        handler.removeCallbacks(this);
    }

    //观察者回调接口 (IOC 数据回调)
    public interface ICountDownHandler {
        //倒计时回调
        void onTick(int time);

        //完成时回调
        void onFinish();
    }
}
