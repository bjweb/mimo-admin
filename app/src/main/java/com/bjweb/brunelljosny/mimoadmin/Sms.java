package com.bjweb.brunelljosny.mimoadmin;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Sms extends BroadcastReceiver {

    public static SQLiteHelper sqLiteHelper;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle data  = intent.getExtras();
        sqLiteHelper = new SQLiteHelper(context, "mimo.sqlite", null, 1);

        Object[] pdus = (Object[]) data.get("pdus");

        String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

        for(int i=0;i<pdus.length;i++){
            SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdus[i]);

            String sender = smsMessage.getDisplayOriginatingAddress();
            if(sender.contains("07486150")){
                String messageBody = smsMessage.getMessageBody();

                String[] decoupage = messageBody.split(" ");
                String montant = decoupage[6];
                String tel = decoupage[9];
                String trans_id = decoupage[2];

                sqLiteHelper.insertData(tel,montant+" Fcfa","hors ligne",date,trans_id);

            }
        }

    }

}
