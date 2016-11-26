package com.nordman.big.smsparking2;

import android.content.Context;
import android.content.Intent;

import com.nordman.big.smsparkinglib.BaseSmsManager;

/**
 * Created by s_vershinin on 15.01.2016.
 * Класс для формирования целевого СМС-сообщения
 */
class SmsManager extends BaseSmsManager {
    SmsManager(Context context) {
        super(context);
    }

    @Override
    public void updateSms() {
        sms = "p66*";

        if (currentZone==null) {
            sms += "*";
        } else {
            sms += currentZone.getZoneNumber().toString() + "*";
        }
        sms += regNum + "*" + hours;
    }

    @Override
    public void showParkingScreen() {
        Intent intent = new Intent(context, ParkingActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void showMainScreen() {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }
}

