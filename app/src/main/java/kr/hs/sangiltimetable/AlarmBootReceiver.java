package kr.hs.sangiltimetable;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AlarmBootReceiver extends BroadcastReceiver {

    int alarm_on_off=0;

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            Alarmregister(context);
        }
//
//        try {
////            throw new UnsupportedOperationException("Not yet implemented");
//        } catch (Exception e) {
//
//        }
    }

    static int requestCode = 3365;
    //====================================================
    public static void Alarmregister(Context context)
    {
//	    Log.e(TAG, "registerAlarm");
        Intent intent = new Intent(context, NotificationAlarmService.class);
        // intent.setAction(NotificationReceiver.INNER_PUSH);
        PendingIntent sender = PendingIntent.getBroadcast(context, requestCode, intent, 0);
        try {
            // 내일 아침 8시 30분에 처음 시작해서, 24시간 마다 실행되게
//            Date tomorrow = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2020-09-22 08:30:00");
            Date tomorrow = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2020-09-22 08:25:00");
            // Date tomorrow = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2016-04-25 11:21:00");

            AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            am.setInexactRepeating(AlarmManager.RTC, tomorrow.getTime(), 24 * 60 * 60 * 1000, sender);

            Toast.makeText(context, "매일 오전에 점심메뉴를 알려드립니다.\n"+
                                         "               상일미디어고등학교" , Toast.LENGTH_LONG).show();
        } catch (ParseException e) {
            // e.printStackTrace();
        }

    }
}
