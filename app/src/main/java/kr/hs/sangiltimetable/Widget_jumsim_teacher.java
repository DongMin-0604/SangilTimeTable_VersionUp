package kr.hs.sangiltimetable;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;

import java.util.Calendar;

/**
 * Implementation of App Widget functionality.
 */
public class Widget_jumsim_teacher extends AppWidgetProvider {

    private static final String CLICK3_ACTION ="kr.hs.sangiltimetable.CLICK3";

    public static int click=0;
    public static int click_teacher=0;

    public static int data_index=0;
    public static int data_index_teacher=0;

    static int weekclick=0;

    static TimeData dd = new TimeData();
    static String [] t = new String[10];

    static RemoteViews remoteViews;

    ComponentName watchWidget;

    static String mon, day;

    private static String weekend[] = { "월","화","수","목","금"};

    static Calendar cal = Calendar.getInstance();

    public static int pr_index=0, weekindex=0;

    public static String menu(String m, String d, int index){
        String sikdan="", temp;
        String md = m+"/"+d;
        String rt="././././.", t_m="",t_d="", ttemp ="";
        int k=0;


        String weeks="일";
        switch(cal.get(Calendar.DAY_OF_WEEK)){
            case 1 : weeks="일"; weekindex=0; break;
            case 2 : weeks="월"; weekindex=0; break;
            case 3 : weeks="화"; weekindex=1; break;
            case 4 : weeks="수"; weekindex=2; break;
            case 5 : weeks="목"; weekindex=3; break;
            case 6 : weeks="금"; weekindex=4; break;
            case 7 : weeks="토"; weekindex=0; break;
        }

        for(int i = 0 ; i < dd.jumsim.length; i++){
            temp = dd.jumsim[i];
            String a,b, ab;
            a = temp.split("/")[0].trim();
            b = temp.split("/")[1].trim();
            ab = a+"/"+b;
            if(ab.equals(md)){
                data_index =i;
                break;
            }else{
                data_index =-1;
            }
        }
        if(data_index ==-1){
            if(index==1){
                sikdan = "[상미고 알림]";
            }else{

                sikdan = dd.pr[pr_index][index-1];

                if(index ==7)pr_index++;
                if(pr_index ==  dd.pr.length)pr_index=0;
            }
        }else {

            if (index >= 8) {
                //=====================================================
                for (int i = 0; i < dd.jumsim_teacher.length; i++) {

                    ttemp = dd.jumsim_teacher[i];
                    t_m = ttemp.split("/")[0];
                    t_d = ttemp.split("/")[1];

                    if (t_m.equals(m) && t_d.equals(d)) data_index_teacher = i;
                }
                click_teacher = click;
                if ((data_index_teacher + click_teacher) >= dd.jumsim_teacher.length)
                    click_teacher = 0;
                ttemp = dd.jumsim_teacher[data_index_teacher + click_teacher];

                if (index == 8) sikdan = ttemp.split("/")[2];
                if (index == 9) sikdan = ttemp.split("/")[3];
                if (index == 10) sikdan = ttemp.split("/")[4];
                if (sikdan.equals(".")) sikdan = "";
            } else {
                weekclick = weekindex + click;
                if (weekclick > 4) weekclick = weekclick % 5;


                if ((data_index + click) >= dd.jumsim.length) click = 0;
                temp = dd.jumsim[data_index + click];
                for (int i = 0; i < 8; i++) {
                    sikdan = "";
                    t[i] = temp.split("/")[i];
                    if (t[i].equals(".")) t[i] = " ";
                }
                sikdan = t[index];
//   	           	sikdan = sikdan.trim();
                if (index == 1)
                    sikdan = temp.split("/")[0] + "." + temp.split("/")[1] + ".(" + weekend[weekclick] + ")상미고점심";
            }
        }

        return sikdan;
    }

    public static void si_pr_m_start(){
        String m;
        int start_mon=0;

        cal = Calendar.getInstance();
        m =  String.valueOf(cal.get(Calendar.MONTH)+1);
        for(int i =0;i<dd.pr.length;i++){
            if(dd.pr[i][0].equals(m)) {
                start_mon=i;
                break;
            }
        }
        if(pr_index <= start_mon)  pr_index = start_mon;
        // click=0;
    }

    //=================================================================================================


    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {

        //===============================================================================================

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, remoteViews);


         if((click+data_index) < dd.jumsim.length)click++;
         if(click>5)click =0;
        if((click+data_index) >= dd.jumsim.length)click =0;

          cal = Calendar.getInstance();

         mon = String.valueOf(cal.get(Calendar.MONTH)+1);
        day = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));


        remoteViews.setTextViewText(R.id.wg_teacher_tV1, menu(mon,day,1));
        remoteViews.setTextViewText(R.id.wg_teacher_tV2, menu(mon,day,2));
        remoteViews.setTextViewText(R.id.wg_teacher_tV3, menu(mon,day,3));
        remoteViews.setTextViewText(R.id.wg_teacher_tV4, menu(mon,day,4));
        remoteViews.setTextViewText(R.id.wg_teacher_tV5, menu(mon,day,5));
        remoteViews.setTextViewText(R.id.wg_teacher_tV6, menu(mon,day,6));
        remoteViews.setTextViewText(R.id.wg_teacher_tV7, menu(mon,day,7));

        remoteViews.setTextViewText(R.id.wg_teacher_tV8, menu(mon,day,8));
        remoteViews.setTextViewText(R.id.wg_teacher_tV9, menu(mon,day,9));
        remoteViews.setTextViewText(R.id.wg_teacher_tV10, menu(mon,day,10));



        //===============================================================================================

//        remoteViews.setOnClickPendingIntent(R.id.wg_layout_jumsim_teacher, getPendingSelfIntent(context, CLICK3_ACTION));



    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created

        remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_jumsim_teacher);


        //===============================================================================================



        if((click+data_index) < dd.jumsim.length)click++;
        if(click>5)click =0;
        if((click+data_index) >= dd.jumsim.length)click =0;

        cal = Calendar.getInstance();

        mon = String.valueOf(cal.get(Calendar.MONTH)+1);
        day = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));


        remoteViews.setTextViewText(R.id.wg_teacher_tV1, menu(mon,day,1));
        remoteViews.setTextViewText(R.id.wg_teacher_tV2, menu(mon,day,2));
        remoteViews.setTextViewText(R.id.wg_teacher_tV3, menu(mon,day,3));
        remoteViews.setTextViewText(R.id.wg_teacher_tV4, menu(mon,day,4));
        remoteViews.setTextViewText(R.id.wg_teacher_tV5, menu(mon,day,5));
        remoteViews.setTextViewText(R.id.wg_teacher_tV6, menu(mon,day,6));
        remoteViews.setTextViewText(R.id.wg_teacher_tV7, menu(mon,day,7));

        remoteViews.setTextViewText(R.id.wg_teacher_tV8, menu(mon,day,8));
        remoteViews.setTextViewText(R.id.wg_teacher_tV9, menu(mon,day,9));
        remoteViews.setTextViewText(R.id.wg_teacher_tV10, menu(mon,day,10));



        //===============================================================================================

        remoteViews.setOnClickPendingIntent(R.id.wg_layout_jumsim_teacher, getPendingSelfIntent(context, CLICK3_ACTION));

    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    protected static PendingIntent getPendingSelfIntent(Context context, String action) {
        Intent intent = new Intent(context,  Widget_jumsim_teacher.class);
        intent.setAction(action);
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        String action = intent.getAction();
        if (AppWidgetManager.ACTION_APPWIDGET_UPDATE.equals(action)) {

            Bundle extras = intent.getExtras();

            si_pr_m_start();

            if (extras != null) {

                int[] appWidgetIds = extras.getIntArray(AppWidgetManager.EXTRA_APPWIDGET_IDS);

                if (appWidgetIds != null && appWidgetIds.length > 0) {
                    this.onUpdate(context, AppWidgetManager.getInstance(context), appWidgetIds);
                }
            }
        } else if (AppWidgetManager.ACTION_APPWIDGET_DELETED.equals(action)) {

            Bundle extras = intent.getExtras();
            if (extras != null && extras.containsKey(AppWidgetManager.EXTRA_APPWIDGET_ID)) {

                si_pr_m_start();

                final int appWidgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID);
                this.onDeleted(context, new int[] { appWidgetId });
            }
        }
        else if (AppWidgetManager.ACTION_APPWIDGET_ENABLED.equals(action)) {

            si_pr_m_start();

            this.onEnabled(context);
        } else if (AppWidgetManager.ACTION_APPWIDGET_DISABLED.equals(action)) {
            this.onDisabled(context);
        }else if(CLICK3_ACTION.equals(intent.getAction()))
        {
            // 위젯 업데이트
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            watchWidget = new ComponentName(context, Widget_jumsim_teacher.class);

            if((click+data_index) < dd.jumsim.length)click++;
            if(click>5)click =0;
            if((click+data_index) >= dd.jumsim.length)click =0;

            remoteViews.setTextViewText(R.id.wg_teacher_tV1, menu(mon,day,1));
            remoteViews.setTextViewText(R.id.wg_teacher_tV2, menu(mon,day,2));
            remoteViews.setTextViewText(R.id.wg_teacher_tV3, menu(mon,day,3));
            remoteViews.setTextViewText(R.id.wg_teacher_tV4, menu(mon,day,4));
            remoteViews.setTextViewText(R.id.wg_teacher_tV5, menu(mon,day,5));
            remoteViews.setTextViewText(R.id.wg_teacher_tV6, menu(mon,day,6));
            remoteViews.setTextViewText(R.id.wg_teacher_tV7, menu(mon,day,7));

            remoteViews.setTextViewText(R.id.wg_teacher_tV8, menu(mon,day,8));
            remoteViews.setTextViewText(R.id.wg_teacher_tV9, menu(mon,day,9));
            remoteViews.setTextViewText(R.id.wg_teacher_tV10, menu(mon,day,10));

            appWidgetManager.updateAppWidget(watchWidget, remoteViews);

        }
    }
}

