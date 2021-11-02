package kr.hs.sangiltimetable;

import android.app.Activity;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.Toast;

import androidx.constraintlayout.solver.widgets.WidgetContainer;

import java.util.Calendar;
import java.util.Random;

/**
 * Implementation of App Widget functionality.
 */
public class Widget_Timetable_Horizontal extends AppWidgetProvider {

    //===================================================================================
    private static final String CLICK1_ACTION = "kr.hs.sangiltimetable.CLICK1";

    static RemoteViews remoteViews;
    ComponentName watchWidget;

    static String now, mm, ss;

    static int togle = 0;
    static int week_togle = 0;
    static int week_basic = 0;

    static String[] save_class = new String[35];
    static String[] save_class_d = new String[35];
    static String[] save_teacher = new String[35];
    static String[] save_teacher_d = new String[35];

    static String class_hak = "3학년", class_ban = "1반";
    static int weekn;
    static String basic, sv_teacher = "", sv_class = "", temp = "", tp = "", name = "";
    static String no_data = "좋!은!하!루!~!행!쇼!좋!은!하!루!~!행!쇼!좋!은!하!루!~!행!쇼!좋!은!하!루!~!행!쇼!좋!은!하!루!~!행!쇼";
    static int widget_tc = 0;
    static Calendar cal= Calendar.getInstance();


    //=============================================================
    static String[][] str = {
            {"사랑", "받고", "싶다면", "사랑", "하라", "~", "밴자민"},
            {"사랑", "하는", "것은", "천국을", "살짝", "엿보는", "것이다"},
            {"인생은", "겸손에", "대한", "오랜", "수업", "이다", "제임스"},
            {"실천이", "말보다", "낫다", ".", ".", ".", "벤자민"},
            {"친구는", "제2의", "자신", "이다", "  아", "리스토", "텔레스"},
            {"뭉치면", "서고", "갈라지", "면", "넘어", "진다.", "이솝"},
            {"일분", "전만큼", "먼", "시간은", "없다", ".", "짐비숍"},
            {"여가", "시간을", "가지려", "면", "시간을", "잘써라", "벤자민"},
            {"삶이", "있는한", "희망은", "있다", ".", ".", "키케로"},
            {"시작이", "반", "이다.", ".", "  아", "리스토", "텔레스"},
            {"어디를", "가든지", "마음을", "다해", "가라", "...", "공자"},
            {"내가", "있는", "곳이", "낙원", "이라", "..", "볼테르"},
            {"정직이", "최고의", "이미지", "이다", "..", "..", "톰윌슨"},
            {"하늘은", "용기", "있는", "자를", "돕는다", "..", "테렌스"},
            {"예절이", "사람을", "만든다", "...", "..", "윈컴의", "윌리엄"},
            {"진실을", "사랑", "하고", "실수를", "용서", "하라", "볼테르"},
            {"인내가", "최상의", "미덕", "이다", "..", ".", "카토"},
            {"큰", "희망이", "큰", "사람을", "만든다", "토마스", "풀러"},
            {"바쁜", "벌은", "슬퍼할", "시간이", "없다", "윌리엄", "블레잌"},
            {"웃음", "없는", "하루는", "낭비한", "하루다", " 찰리", "채플린"},
            {"우둔함", "은영원", "하나", "무지는", "고칠수", "있다", "돈우드"},
            {"위대함", "의", "대가는", "책임감", "이다", "윈스턴", "처칠"},
            {"허물이", "있다면", "버리기", "를", "두려워", "말라", "공자"},
            {"언어는", "오해의", "근원", "이다", "..", "생텍쥐", "페리"},
            {"용서", "만큼", "완벽한", "복수는", "없다", ".조쉬", "빌링스"},
            {"사실이", "드문", "곳에", "전문가", "는많다", "도널드", "R개논"},
            {"슬픔의", "유일한", "치료제", "는행동", "이다", "조지헨", "루이스"},
            {"성공하", "기까지", "는항상", "실패를", "거친다", "미키", "루니"},
            {"가장큰", "위험은", "위험", "없는", "삶이다", "스티븐", "코비"},
            {"실수는", "발견의", "시작", "이다", "..", "제임스", "조이스"},
            {"인생은", "용서", "속의", "모험", "이다", "노먼", "커즌즈"},
            {"행복은", "우리자", "신에게", "달려있", "다.아", "리스토", "텔레스"}
    };



    static public int xx(int i) {
        int result = 0;
        switch (i) {
            case 0:
                result = 0;
                break;
            case 1:
                result = 7;
                break;
            case 2:
                result = 14;
                break;
            case 3:
                result = 21;
                break;
            case 4:
                result = 28;
                break;
        }
        return result;
    }

    static void WidgetView(Context context) {

        remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget__timetable__horizontal);
        cal = Calendar.getInstance();
//===========================================================================
        weekn = cal.get(Calendar.DAY_OF_WEEK) - 2;
        if (weekn == 1) weekn = 2;
        week_basic = weekn;
        String weeks = "일";
        switch (cal.get(Calendar.DAY_OF_WEEK)) {
            case 1:
                weeks = "일";
                break;
            case 2:
                weeks = "월";
                break;
            case 3:
                weeks = "화";
                break;
            case 4:
                weeks = "수";
                break;
            case 5:
                weeks = "목";
                break;
            case 6:
                weeks = "금";
                break;
            case 7:
                weeks = "토";
                break;
        }

//===========================================================================
//        if(cal.get(Calendar.MINUTE)<10)
//            mm = "0"+ String.valueOf(cal.get(Calendar.MINUTE));
//        else
//            mm = String.valueOf(cal.get(Calendar.MINUTE));
        mm = String.format("%02d", cal.get(Calendar.MINUTE));

        if (cal.get(Calendar.HOUR_OF_DAY) >= 12) {
            if (cal.get(Calendar.HOUR_OF_DAY) == 12)
                now = "A) " + String.format("%2d", cal.get(Calendar.HOUR_OF_DAY)) + ":" + mm;
            else
                now = "P) " + String.format("%2d", cal.get(Calendar.HOUR_OF_DAY) - 12) + ":" + mm;
        } else
            now = "(오전) " + String.format("%2d", cal.get(Calendar.HOUR_OF_DAY)) + ":" + mm;


        remoteViews.setTextViewText(R.id.widget_timer_tv2, "(" + weeks + ")시간표");

//==============================================================================================
        SharedPreferences pref = context.getSharedPreferences("saveid", Activity.MODE_PRIVATE);

        basic = pref.getString("saveid", "1/1/1/0");
        class_hak = basic.split("/")[1];
        class_ban = basic.split("/")[2];
        widget_tc = Integer.valueOf(basic.split("/")[3]);

        SharedPreferences teacher = context.getSharedPreferences("teacher", Activity.MODE_PRIVATE);
        sv_teacher = teacher.getString("teacher", "처!음!~!실!행!할!.!경!우!~!반!드!시!~!셋!팅!화!면!에!서!~!데!이!타!저!장!이!~!필!요!합!니!다!~!~");
        for (int i = 0; i < 35; i++) save_teacher[i] = sv_teacher.split("!")[i];
        for (int i = 0; i < 35; i++) if (save_teacher[i].equals(".")) save_teacher[i] = " ";

        SharedPreferences teacher_d = context.getSharedPreferences("teacher_d", Activity.MODE_PRIVATE);
        sv_teacher = teacher_d.getString("teacher_d", "처!음!~!실!행!할!.!경!우!~!반!드!시!~!셋!팅!화!면!에!서!~!데!이!타!저!장!이!~!필!요!합!니!다!~!~");
        for (int i = 0; i < 35; i++) save_teacher_d[i] = sv_teacher.split("!")[i];
        for (int i = 0; i < 35; i++) if (save_teacher_d[i].equals(".")) save_teacher_d[i] = " ";


        SharedPreferences classban = context.getSharedPreferences("class", Activity.MODE_PRIVATE);
        sv_class = classban.getString("class", "처!음!~!실!행!할!.!경!우!~!반!드!시!~!셋!팅!화!면!에!서!~!데!이!타!저!장!이!~!필!요!합!니!다!~!~");
        for (int i = 0; i < 35; i++) save_class[i] = sv_class.split("!")[i];
        SharedPreferences classban_d = context.getSharedPreferences("class_d", Activity.MODE_PRIVATE);
        sv_class = classban_d.getString("class_d", "처!음!~!실!행!할!.!경!우!~!반!드!시!~!셋!팅!화!면!에!서!~!데!이!타!저!장!이!~!필!요!합!니!다!~!~");
        for (int i = 0; i < 35; i++) save_class_d[i] = sv_class.split("!")[i];

        if (save_teacher[0] == "좋" || save_class[0] == "좋")
            Toast.makeText(context, "시간표프로그램에서 설정하여야 정상작동합니다.", Toast.LENGTH_LONG).show();

        SharedPreferences teacher_name = context.getSharedPreferences("name", Activity.MODE_PRIVATE);
        name = teacher_name.getString("name", "선생님").split("선")[0];


//=============================================================================================
        if (weekn == 1) weekn = 2;
        if (weeks == "토") {

            int il = 0;

            Random ran = new Random();
            il = ran.nextInt(str.length);
            String strn = " ";
//  		    토요일, 일요일 타이틀 지우기
            remoteViews.setTextViewText(R.id.widget_tv1_n, strn);
            remoteViews.setTextViewText(R.id.widget_tv2_n, strn);
            remoteViews.setTextViewText(R.id.widget_tv3_n, strn);
            remoteViews.setTextViewText(R.id.widget_tv4_n, strn);
            remoteViews.setTextViewText(R.id.widget_tv5_n, strn);
            remoteViews.setTextViewText(R.id.widget_tv6_n, strn);
            remoteViews.setTextViewText(R.id.widget_tv7_n, strn);

            remoteViews.setTextViewText(R.id.widget_title_tv1, strn);
            remoteViews.setTextViewText(R.id.widget_title_tv2, strn);
            remoteViews.setTextViewText(R.id.widget_title_tv3, strn);
            remoteViews.setTextViewText(R.id.widget_title_tv4, strn);
            remoteViews.setTextViewText(R.id.widget_title_tv5, strn);
            remoteViews.setTextViewText(R.id.widget_title_tv6, strn);
            remoteViews.setTextViewText(R.id.widget_title_tv7, strn);

            if (weeks == "토") {
//			토요일 출력
                remoteViews.setTextViewText(R.id.widget_tv1, str[il][0]);
                remoteViews.setTextViewText(R.id.widget_tv2, str[il][1]);
                remoteViews.setTextViewText(R.id.widget_tv3, str[il][2]);
                remoteViews.setTextViewText(R.id.widget_tv4, str[il][3]);
                remoteViews.setTextViewText(R.id.widget_tv5, str[il][4]);
                remoteViews.setTextViewText(R.id.widget_tv6, str[il][5]);
                remoteViews.setTextViewText(R.id.widget_tv7, str[il][6]);

                remoteViews.setTextViewText(R.id.widget_timer_tv1, "토요일");
            } else {
////			일요일 출력
//			        remoteViews.setTextViewText(R.id.widget_tv1_v, str[il][0]);
//					remoteViews.setTextViewText(R.id.widget_tv2_v, str[il][1]);
//					remoteViews.setTextViewText(R.id.widget_tv3_v, str[il][2]);
//					remoteViews.setTextViewText(R.id.widget_tv4_v, str[il][3]);
//					remoteViews.setTextViewText(R.id.widget_tv5_v, str[il][4]);
//					remoteViews.setTextViewText(R.id.widget_tv6_v, str[il][5]);
//					remoteViews.setTextViewText(R.id.widget_tv7_v, str[il][6]);
//
//
//			 			remoteViews.setTextViewText(R.id.widget_timer_tv1, "일요일");
            }
        } else {
//			시간 타이틀 출력
            remoteViews.setTextViewText(R.id.widget_title_tv1, "1교시");
            remoteViews.setTextViewText(R.id.widget_title_tv2, "2교시");
            remoteViews.setTextViewText(R.id.widget_title_tv3, "3교시");
            remoteViews.setTextViewText(R.id.widget_title_tv4, "4교시");
            remoteViews.setTextViewText(R.id.widget_title_tv5, "5교시");
            remoteViews.setTextViewText(R.id.widget_title_tv6, "6교시");
            remoteViews.setTextViewText(R.id.widget_title_tv7, "7교시");

            if (widget_tc == 0) {
//			              학생 시간표
                remoteViews.setTextViewText(R.id.widget_tv1, save_class[xx(week_togle) + 0]);
                remoteViews.setTextViewText(R.id.widget_tv2, save_class[xx(week_togle) + 1]);
                remoteViews.setTextViewText(R.id.widget_tv3, save_class[xx(week_togle) + 2]);
                remoteViews.setTextViewText(R.id.widget_tv4, save_class[xx(week_togle) + 3]);
                remoteViews.setTextViewText(R.id.widget_tv5, save_class[xx(week_togle) + 4]);
                remoteViews.setTextViewText(R.id.widget_tv6, save_class[xx(week_togle) + 5]);
                remoteViews.setTextViewText(R.id.widget_tv7, save_class[xx(week_togle) + 6]);

                remoteViews.setTextViewText(R.id.widget_tv1_n, save_class_d[xx(week_togle) + 0]);
                remoteViews.setTextViewText(R.id.widget_tv2_n, save_class_d[xx(week_togle) + 1]);
                remoteViews.setTextViewText(R.id.widget_tv3_n, save_class_d[xx(week_togle) + 2]);
                remoteViews.setTextViewText(R.id.widget_tv4_n, save_class_d[xx(week_togle) + 3]);
                remoteViews.setTextViewText(R.id.widget_tv5_n, save_class_d[xx(week_togle) + 4]);
                remoteViews.setTextViewText(R.id.widget_tv6_n, save_class_d[xx(week_togle) + 5]);
                remoteViews.setTextViewText(R.id.widget_tv7_n, save_class_d[xx(week_togle) + 6]);

                switch (week_togle) {
                    case 6:   weeks = "일";
                        break;
                    case 0:
                        weeks = "월";
                        break;
                    case 1:
                        weeks = "화";
                        break;
                    case 2:
                        weeks = "수";
                        break;
                    case 3:
                        weeks = "목";
                        break;
                    case 4:
                        weeks = "금";
                        break;
                    case 5:
                        weeks = "토";
                        break;
                }
                remoteViews.setTextViewText(R.id.widget_timer_tv2, "(" + weeks + ")시간표");

                remoteViews.setTextViewText(R.id.widget_timer_tv1, class_hak + "학년" + class_ban + "반");
            } else if (widget_tc == 1) {
//			    선생님시간표

//			     		remoteViews.setTextViewText(R.id.widget_tv1_v, save_teacher[xx(weekn)+0]);
//			 			remoteViews.setTextViewText(R.id.widget_tv2_v, save_teacher[xx(weekn)+1]);
//			 			remoteViews.setTextViewText(R.id.widget_tv3_v, save_teacher[xx(weekn)+2]);
//			 			remoteViews.setTextViewText(R.id.widget_tv4_v, save_teacher[xx(weekn)+3]);
//			 			remoteViews.setTextViewText(R.id.widget_tv5_v, save_teacher[xx(weekn)+4]);
//			 			remoteViews.setTextViewText(R.id.widget_tv6_v, save_teacher[xx(weekn)+5]);
//			 			remoteViews.setTextViewText(R.id.widget_tv7_v, save_teacher[xx(weekn)+6]);
//
//			 			remoteViews.setTextViewText(R.id.widget_tv1_n_v, save_teacher_d[xx(weekn)+0]);
//			 			remoteViews.setTextViewText(R.id.widget_tv2_n_v, save_teacher_d[xx(weekn)+1]);
//			 			remoteViews.setTextViewText(R.id.widget_tv3_n_v, save_teacher_d[xx(weekn)+2]);
//			 			remoteViews.setTextViewText(R.id.widget_tv4_n_v, save_teacher_d[xx(weekn)+3]);
//			 			remoteViews.setTextViewText(R.id.widget_tv5_n_v, save_teacher_d[xx(weekn)+4]);
//			 			remoteViews.setTextViewText(R.id.widget_tv6_n_v, save_teacher_d[xx(weekn)+5]);
//			 			remoteViews.setTextViewText(R.id.widget_tv7_n_v, save_teacher_d[xx(weekn)+6]);

                switch (week_togle) {
                    case 6:
                        weeks = "일";
                        break;
                    case 0:
                        weeks = "월";
                        break;
                    case 1:
                        weeks = "화";
                        break;
                    case 2:
                        weeks = "수";
                        break;
                    case 3:
                        weeks = "목";
                        break;
                    case 4:
                        weeks = "금";
                        break;
                    case 5:
                        weeks = "토";
                        break;
                }

                remoteViews.setTextViewText(R.id.widget_timer_tv2, "(" + weeks + ")시간표");

                remoteViews.setTextViewText(R.id.widget_tv1, save_teacher[xx(week_togle) + 0]);
                remoteViews.setTextViewText(R.id.widget_tv2, save_teacher[xx(week_togle) + 1]);
                remoteViews.setTextViewText(R.id.widget_tv3, save_teacher[xx(week_togle) + 2]);
                remoteViews.setTextViewText(R.id.widget_tv4, save_teacher[xx(week_togle) + 3]);
                remoteViews.setTextViewText(R.id.widget_tv5, save_teacher[xx(week_togle) + 4]);
                remoteViews.setTextViewText(R.id.widget_tv6, save_teacher[xx(week_togle) + 5]);
                remoteViews.setTextViewText(R.id.widget_tv7, save_teacher[xx(week_togle) + 6]);

                remoteViews.setTextViewText(R.id.widget_tv1_n, save_teacher_d[xx(week_togle) + 0]);
                remoteViews.setTextViewText(R.id.widget_tv2_n, save_teacher_d[xx(week_togle) + 1]);
                remoteViews.setTextViewText(R.id.widget_tv3_n, save_teacher_d[xx(week_togle) + 2]);
                remoteViews.setTextViewText(R.id.widget_tv4_n, save_teacher_d[xx(week_togle) + 3]);
                remoteViews.setTextViewText(R.id.widget_tv5_n, save_teacher_d[xx(week_togle) + 4]);
                remoteViews.setTextViewText(R.id.widget_tv6_n, save_teacher_d[xx(week_togle) + 5]);
                remoteViews.setTextViewText(R.id.widget_tv7_n, save_teacher_d[xx(week_togle) + 6]);

                remoteViews.setTextViewText(R.id.widget_timer_tv1_v, name + "님");

            } else {
                if (togle == 0) {
                    remoteViews.setTextViewText(R.id.widget_tv1, save_teacher[xx(weekn) + 0]);
                    remoteViews.setTextViewText(R.id.widget_tv2, save_teacher[xx(weekn) + 1]);
                    remoteViews.setTextViewText(R.id.widget_tv3, save_teacher[xx(weekn) + 2]);
                    remoteViews.setTextViewText(R.id.widget_tv4, save_teacher[xx(weekn) + 3]);
                    remoteViews.setTextViewText(R.id.widget_tv5, save_teacher[xx(weekn) + 4]);
                    remoteViews.setTextViewText(R.id.widget_tv6, save_teacher[xx(weekn) + 5]);
                    remoteViews.setTextViewText(R.id.widget_tv7, save_teacher[xx(weekn) + 6]);

                    remoteViews.setTextViewText(R.id.widget_tv1_n, save_teacher_d[xx(weekn) + 0]);
                    remoteViews.setTextViewText(R.id.widget_tv2_n, save_teacher_d[xx(weekn) + 1]);
                    remoteViews.setTextViewText(R.id.widget_tv3_n, save_teacher_d[xx(weekn) + 2]);
                    remoteViews.setTextViewText(R.id.widget_tv4_n, save_teacher_d[xx(weekn) + 3]);
                    remoteViews.setTextViewText(R.id.widget_tv5_n, save_teacher_d[xx(weekn) + 4]);
                    remoteViews.setTextViewText(R.id.widget_tv6_n, save_teacher_d[xx(weekn) + 5]);
                    remoteViews.setTextViewText(R.id.widget_tv7_n, save_teacher_d[xx(weekn) + 6]);

                    remoteViews.setTextViewText(R.id.widget_timer_tv1, name + "님");
                    togle = 1;
                } else {
                    remoteViews.setTextViewText(R.id.widget_tv1, save_class[xx(weekn) + 0]);
                    remoteViews.setTextViewText(R.id.widget_tv2, save_class[xx(weekn) + 1]);
                    remoteViews.setTextViewText(R.id.widget_tv3, save_class[xx(weekn) + 2]);
                    remoteViews.setTextViewText(R.id.widget_tv4, save_class[xx(weekn) + 3]);
                    remoteViews.setTextViewText(R.id.widget_tv5, save_class[xx(weekn) + 4]);
                    remoteViews.setTextViewText(R.id.widget_tv6, save_class[xx(weekn) + 5]);
                    remoteViews.setTextViewText(R.id.widget_tv7, save_class[xx(weekn) + 6]);

                    remoteViews.setTextViewText(R.id.widget_tv1_n, save_class_d[xx(weekn) + 0]);
                    remoteViews.setTextViewText(R.id.widget_tv2_n, save_class_d[xx(weekn) + 1]);
                    remoteViews.setTextViewText(R.id.widget_tv3_n, save_class_d[xx(weekn) + 2]);
                    remoteViews.setTextViewText(R.id.widget_tv4_n, save_class_d[xx(weekn) + 3]);
                    remoteViews.setTextViewText(R.id.widget_tv5_n, save_class_d[xx(weekn) + 4]);
                    remoteViews.setTextViewText(R.id.widget_tv6_n, save_class_d[xx(weekn) + 5]);
                    remoteViews.setTextViewText(R.id.widget_tv7_n, save_class_d[xx(weekn) + 6]);

                    remoteViews.setTextViewText(R.id.widget_timer_tv1, class_hak + "학년" + class_ban + "반");
                    togle = 0;
                }

            }
        }

        //==================================================================
        // 완전 처음 실행 일경우 셋팅 해서 저장하기

//=============================================================================================
    }
    //======================================================================================

    protected static PendingIntent getPendingSelfIntent(Context context, String action) {
        Intent intent = new Intent(context, Widget_Timetable_Horizontal.class);
        intent.setAction(action);
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }


    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {



        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, remoteViews);


        WidgetView(context);
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

        WidgetView(context);

        remoteViews.setOnClickPendingIntent(R.id.widget_timetable_horizontal, getPendingSelfIntent(context, CLICK1_ACTION));

    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);


        String action = intent.getAction();
        if (AppWidgetManager.ACTION_APPWIDGET_UPDATE.equals(action)) {

            Bundle extras = intent.getExtras();

            WidgetView(context);

            if (extras != null) {

                int[] appWidgetIds = extras.getIntArray(AppWidgetManager.EXTRA_APPWIDGET_IDS);

                if (appWidgetIds != null && appWidgetIds.length > 0) {
                    this.onUpdate(context, AppWidgetManager.getInstance(context), appWidgetIds);
                }
            }
        } else if (AppWidgetManager.ACTION_APPWIDGET_DELETED.equals(action)) {

            Bundle extras = intent.getExtras();
            if (extras != null && extras.containsKey(AppWidgetManager.EXTRA_APPWIDGET_ID)) {

                WidgetView(context);

                final int appWidgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID);
                this.onDeleted(context, new int[]{appWidgetId});
            }
        } else if (AppWidgetManager.ACTION_APPWIDGET_ENABLED.equals(action)) {

            WidgetView(context);

            this.onEnabled(context);
        } else if (AppWidgetManager.ACTION_APPWIDGET_DISABLED.equals(action)) {
            this.onDisabled(context);
        } else if (CLICK1_ACTION.equals(intent.getAction())) {
            // 위젯 업데이트
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            watchWidget = new ComponentName(context, Widget_Timetable_Horizontal.class);

            week_togle++;
            if (week_togle == 5) week_togle = 0;

            WidgetView(context);



            appWidgetManager.updateAppWidget(watchWidget, remoteViews);
        }
    }
}

