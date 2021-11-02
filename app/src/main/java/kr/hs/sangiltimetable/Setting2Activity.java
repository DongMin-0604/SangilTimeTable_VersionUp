package kr.hs.sangiltimetable;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RemoteViews;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Setting2Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    int mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;

    TimeData td = new TimeData();

    SeekBar wg_seek;

    int select_year, select_class, select_teacher, select_widget, select_alarm;
    //
    int teacherNum;

    String[][] inputsave = new String[5][7];
    String[][] inputsave_d = new String[5][7];

    Button BT_save, BT_cancel, BT_notification;



    final int cell_teacher = 0;
    final int cell_class = 1;
    final int cell_weekstart = 4;
    final int cell_weekend = 36;


    String basic;

    int alarmState = 0;


    Spinner   spTeacher, spYear, spClass, spWidget;
    CheckBox cbAlarm;
    TextView  tvText1, tvText2;

    ArrayAdapter<String> adapter_teacher, adapter_year, adapter_class, adapter_widget;
    ArrayList<String> arrlist_teacher, arrlist_year, arrlist_class, arrlist_widget;


    public static final String NOTIFICATION_CHANNEL_ID = "3365";



//    ListPreference pTeacher, pYear, pClass, pWidget;

//    CheckBoxPreference pAlarm;

//    EditTextPreference  pText1, pText2;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting2);

       StartSetup();



    }
//    Spinner   spTeacher, spYear, spClass, spWidget;
//    CheckBox cbAlarm;
//    TextView  tvText1, tvText2;
//
//    ArrayAdapter<String> adapter_teacher, adapter_year, adapter_class, adapter_widget;
//    ArrayList<String> arrlist_teacher, arrlist_year, arrlist_class, arrlist_widget;
//    Button BT_save, BT_cancel, BT_notification;

    private void StartSetup() {

        arrlist_teacher = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.teacher_name)));
        arrlist_year = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.yearhak)));
        arrlist_class = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.classban)));
        arrlist_widget = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.list_widget)));

        adapter_teacher = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,arrlist_teacher);
        adapter_year = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,arrlist_year);
        adapter_class = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,arrlist_class);
        adapter_widget = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,arrlist_widget);

        spTeacher = (Spinner)findViewById(R.id.SP_pref_teachername);
        spYear = (Spinner)findViewById(R.id.SP_pref_yearhak);
        spClass = (Spinner)findViewById(R.id.SP_pref_classban);
        spWidget = (Spinner)findViewById(R.id.SP_pref_widget);

        spTeacher.setAdapter(adapter_teacher);
        spYear.setAdapter(adapter_year);
        spClass.setAdapter(adapter_class);
        spWidget.setAdapter(adapter_widget);

        spTeacher.setOnItemSelectedListener(this);
        spClass.setOnItemSelectedListener(this);
        spYear.setOnItemSelectedListener(this);
        spWidget.setOnItemSelectedListener(this);


        spWidget.setEnabled(false);

        cbAlarm = (CheckBox)findViewById(R.id.CHECKBOX_pref_alarm);

        tvText1 = (TextView)findViewById(R.id.Setting_pref_text1);
        tvText2 = (TextView)findViewById(R.id.Setting_pref_text2);


        BT_save = (Button)findViewById(R.id.BTsenting_priference_save);
        BT_cancel = (Button)findViewById(R.id.BTsenting_priference_cancel);
        BT_notification = (Button)findViewById(R.id.BTsenting_priference_notification);

        cbAlarm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(cbAlarm.isChecked()){
                    select_alarm = 1;
                }
                else {
                    select_alarm = 0;
                }

                set_edit_title();

                //실행불가 알림=========================================================================
                Toast.makeText(getApplicationContext(), "현재 준비중 기능입니다..",Toast.LENGTH_SHORT).show();
                //=========================================================================
            }
        });

        BT_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub
                // 선생님/학년/반/점심위젯선택
                SharedPreferences pref1 = getSharedPreferences("saveid", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor1 = pref1.edit();
                editor1.putString("saveid", String.valueOf(select_teacher) + "/" + String.valueOf(select_year) + "/"
                        + String.valueOf(select_class) + "/" + String.valueOf(select_widget));
                editor1.commit();
                // =========================================================================

                seach_class(select_year, select_class);

                select_techer(select_teacher);

                // =========================================================================
                SharedPreferences prefAlarmsave = getSharedPreferences("alarm", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editorAlarmsave = prefAlarmsave.edit();
                editorAlarmsave.putString("alarm", String.valueOf(select_alarm));
                editorAlarmsave.commit();





                if (select_alarm == 1) {
                    Alarmregister(getApplicationContext());

                } else {
                    Alarmunregister(getApplicationContext());
                }





                // SharedPreferences prefAlarm = getSharedPreferences("alarm",
                // Activity.MODE_PRIVATE);
                // basic = prefAlarm.getString("alarm", "1"); //
                // 선생님/학년/반/점심위젯선택/상단점심알림
                //
                // select_alarm = Integer.parseInt(basic);
                // toggle_Alarm.setChecked(select_alarm==1?true:false);

                // ===========================================================================
                final Context context = Setting2Activity.this;
                AppWidgetManager widgetMgr = AppWidgetManager.getInstance(context);

///-----------------------------------------------------------------------------
//                ttwidget.ttonUpdate(context, widgetMgr, mAppWidgetId);
                Widget_Timetable_Horizontal.updateAppWidget(context, widgetMgr, mAppWidgetId);
                Widget_Timetable_Vertical.updateAppWidget(context, widgetMgr, mAppWidgetId);
///-----------------------------------------------------------------------------



                set_edit_title();
                Toast.makeText(context, "저장하였습니다.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        BT_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //============================================================================알림강제 시작
                Toast.makeText(getApplicationContext(), "[상일미디어고등학교]급식을 알려드립니다."
                        , Toast.LENGTH_LONG).show();

                if(!td.todaytitle().equals("공") && td.todaylunch(0).length()>0){


                    //2020년도 노티피케이션======================================================



                    RemoteViews remoteViews = new RemoteViews(getApplicationContext().getPackageName(), R.layout.notificationarlm);
                    remoteViews.setTextViewText(R.id.notification_txt, td.todaytitle());
                    remoteViews.setTextViewText(R.id.notification_txt2, td.todaylunch(0)+"\n"+
                            td.todaylunch(1)+"");

                    NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                    Intent notificatonIntent = new Intent(getApplicationContext(), Extend_jumsimActivity.class);
                    notificatonIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, notificatonIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                    NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), NOTIFICATION_CHANNEL_ID)
                            .setSmallIcon(R.drawable.logo)
                            .setContent(remoteViews)   // 백그라운드 배경
                            .setContentTitle(td.todaytitle())
                            .setContentText(td.todaylunch(0)+" (펼쳐보세요~)")
//                           .setPriority(Notification.PRIORITY_HIGH)
                            .setContentIntent(pendingIntent);
//                            .setAutoCancel(true);
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                        CharSequence channelName = "상미고점심 채널";
                        String description = "오레오버전 이상을 위한 것임";
                        int importance = NotificationManager.IMPORTANCE_HIGH;

                        NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName, importance);
                        channel.setDescription(description);

                        assert  notificationManager != null;
                        notificationManager.createNotificationChannel(channel);
                    }else {
                        builder.setSmallIcon(R.mipmap.logo);
                    }

                    assert notificationManager != null;
                    notificationManager.notify(3365, builder.build());


                    //2017년도 노티피케이션 시작======================================================
/*
                    Intent intent2 = new Intent(getApplicationContext(), Extend_jumsimActivity.class);
                    intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    PendingIntent pendingintent = PendingIntent.getActivity(getApplicationContext(), 0, intent2, 0);

                    RemoteViews remoteViews = new RemoteViews(getApplicationContext().getPackageName(), R.layout.notificationarlm);
                    remoteViews.setTextViewText(R.id.notification_txt, td.todaytitle());
                    remoteViews.setTextViewText(R.id.notification_txt2, td.todaylunch(0)+"\n"+
                            td.todaylunch(1)+"");

                    Notification.InboxStyle style = new Notification.InboxStyle(
                            new Notification.Builder(getApplicationContext()).setSmallIcon(R.drawable.logo)
                                    .setContent(remoteViews)   // 백그라운드 배경
                                    .setContentTitle(td.todaytitle())
                                    .setContentText(td.todaylunch(0)+" (펼쳐보세요~)")
                                    .setPriority(Notification.PRIORITY_HIGH)
                                    .setContentIntent(pendingintent)
                    );
                    style.addLine(td.todaylunch(0));
                    style.addLine(td.todaylunch(1)+"  ※클릭하면 한달 급식표가 나옵니다.");



                    NotificationManager nm = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
                    nm.notify(3365,style.build());
*/
                    //2017년도 노티피케이션 끝======================================================


                } //if 공
                else{
                    Toast.makeText(getApplicationContext(), "\n     [상일미디어고등학교]\n\n     토요일 이거나 공휴일로 급식 내용이 없습니다.     \n"
                            , Toast.LENGTH_LONG).show();
                }


//				RemoteViews remoteViews = new RemoteViews(getApplicationContext().getPackageName(), R.layout.notification_back);
//				Notification.InboxStyle style = new Notification.InboxStyle(
//						  new Notification.Builder(getApplicationContext()).setSmallIcon(R.drawable.logo)
//						                                    .setContent(remoteViews)   // 백그라운드 배경
//						                                   .setContentTitle(td.todaytitle())
//						                                   .setContentText(td.todaylunch(0)+" (펼쳐보세요~)")
//						                                   .setPriority(Notification.PRIORITY_HIGH)
//						                                    );
//				style.addLine(td.todaylunch(0));
//				style.addLine(td.todaylunch(1));
//
//
//				NotificationManager nm = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
//				nm.notify(3365,style.build());



                //실행불가 알림=========================================================================
//                Toast.makeText(getApplicationContext(), "현재 준비중 기능입니다..",Toast.LENGTH_SHORT).show();
                //=========================================================================

            }
        });
        BT_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        //=========================================================================

        teacherNum = td.tBD.length;


        SharedPreferences pref = getSharedPreferences("saveid", Activity.MODE_PRIVATE);
        basic = pref.getString("saveid", "1/1/1/0"); // 선생님/학년/반/점심위젯선택

        select_teacher = Integer.parseInt(basic.split("/")[0]);
        select_year = Integer.parseInt(basic.split("/")[1]);
        select_class = Integer.parseInt(basic.split("/")[2]);
        select_widget = Integer.parseInt(basic.split("/")[3]);

//  ==============================화면에 표시 시작

        spTeacher.setSelection(select_teacher-1);
        spYear.setSelection(select_year-1);
        spClass.setSelection(select_class-1);
        spWidget.setSelection(select_widget);




        // ====================================================================================
        SharedPreferences prefAlarm = getSharedPreferences("alarm", Activity.MODE_PRIVATE);
        basic = prefAlarm.getString("alarm", "1"); // 점심알림

        select_alarm = Integer.parseInt(basic);


        if(select_alarm == 1)
            cbAlarm.setChecked(true);
        else
            cbAlarm.setChecked(false);


        //==== 하단에 알려주기
        set_edit_title();

        //==============================================================================

        // =================================================
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            mAppWidgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        }
        // ==================================================


        //==============================================================================
        Toast.makeText(this, "알림을 설정하시면\n점심메뉴를 오전에 알림을 받을 수 있습니다.", Toast.LENGTH_SHORT).show();


    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()){
            case R.id.SP_pref_teachername:   select_teacher = i+1;    break;
            case R.id.SP_pref_yearhak:   select_year = i+1;    break;
            case R.id.SP_pref_classban:   select_class = i+1;    break;
            case R.id.SP_pref_widget:   select_widget = i;
            break;
        }

        set_edit_title();

    }

    private void set_edit_title(){
        String title = "";
        title = "설정: "+td.tBD[select_teacher][0] +"선생님, "+(select_year)+"학년"+(select_class)+"반";

        tvText1.setText(title);

        title = "설정: "+td.widget_title_arr[select_widget];
        if(select_alarm==1)
            title += ", 점심메뉴 알려줌";
        else
            title += ", 점심메뉴 안알려줌";
        tvText2.setText(title);
    }


    private void seach_class(int s_year, int s_class) {

        for (int i = 0; i < 5; i++)
            for (int k = 0; k < 7; k++) {
                inputsave[i][k] = ".";
                inputsave_d[i][k] = ".";
            }
        String data, dataT, dataM;
        int iy, ic;
        // tv_title.setText("("+ s_year + "학년"+ s_class + "반)");
        for (int x = cell_weekstart; x <= cell_weekend; x++)
            for (int y = 1; y < teacherNum; y++) {
                if (td.tBD[y][x].trim() != "") {
                    data = td.tBD[y][x].trim();
                    dataT = data.split("/")[0].trim();
                    dataM = data.split("/")[1].trim();
                    iy = Integer.parseInt(dataT.substring(0, 1));
                    ic = Integer.parseInt(dataT.substring(1));
                    if (s_year == iy && s_class == ic) {
                        inputsave[index_y(x)][index_x(x)] = dataM;
                        inputsave_d[index_y(x)][index_x(x)] = td.tBD[y][cell_teacher].toString();
                    }
                }
            }
        for (int i = 1; i < teacherNum; i++) {
            if (td.tBD[i][cell_class] != "") {
                data = td.tBD[i][cell_class];
                dataT = data.split("-")[0];
                dataM = data.split("-")[1];
                iy = Integer.parseInt(dataT);
                ic = Integer.parseInt(dataM);
                if (iy == s_year && ic == s_class) {
                    inputsave[4][5] = "HR";
                    inputsave_d[4][5] = td.tBD[i][cell_teacher].trim();
                    inputsave[4][6] = "CA";
                    inputsave_d[4][6] = td.tBD[i][cell_teacher].trim();
                }

            }
        }

        // 월------------------
        SharedPreferences pref = getSharedPreferences("class", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("class",
                inputsave[0][0] + "!" + inputsave[0][1] + "!" + inputsave[0][2] + "!" + inputsave[0][3] + "!"
                        + inputsave[0][4] + "!" + inputsave[0][5] + "!" + inputsave[0][6] + "!" + inputsave[1][0] + "!"
                        + inputsave[1][1] + "!" + inputsave[1][2] + "!" + inputsave[1][3] + "!" + inputsave[1][4] + "!"
                        + inputsave[1][5] + "!" + inputsave[1][6] + "!" + inputsave[2][0] + "!" + inputsave[2][1] + "!"
                        + inputsave[2][2] + "!" + inputsave[2][3] + "!" + inputsave[2][4] + "!" + inputsave[2][5] + "!"
                        + inputsave[2][6] + "!" + inputsave[3][0] + "!" + inputsave[3][1] + "!" + inputsave[3][2] + "!"
                        + inputsave[3][3] + "!" + inputsave[3][4] + "!" + inputsave[3][5] + "!" + inputsave[3][6] + "!"
                        + inputsave[4][0] + "!" + inputsave[4][1] + "!" + inputsave[4][2] + "!" + inputsave[4][3] + "!"
                        + inputsave[4][4] + "!" + inputsave[4][5] + "!" + inputsave[4][6]);
        editor.commit();
        SharedPreferences pref_d = getSharedPreferences("class_d", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor_d = pref_d.edit();
        editor_d.putString("class_d", inputsave_d[0][0] + "!" + inputsave_d[0][1] + "!" + inputsave_d[0][2] + "!"
                + inputsave_d[0][3] + "!" + inputsave_d[0][4] + "!" + inputsave_d[0][5] + "!" + inputsave_d[0][6] + "!"
                + inputsave_d[1][0] + "!" + inputsave_d[1][1] + "!" + inputsave_d[1][2] + "!" + inputsave_d[1][3] + "!"
                + inputsave_d[1][4] + "!" + inputsave_d[1][5] + "!" + inputsave_d[1][6] + "!" + inputsave_d[2][0] + "!"
                + inputsave_d[2][1] + "!" + inputsave_d[2][2] + "!" + inputsave_d[2][3] + "!" + inputsave_d[2][4] + "!"
                + inputsave_d[2][5] + "!" + inputsave_d[2][6] + "!" + inputsave_d[3][0] + "!" + inputsave_d[3][1] + "!"
                + inputsave_d[3][2] + "!" + inputsave_d[3][3] + "!" + inputsave_d[3][4] + "!" + inputsave_d[3][5] + "!"
                + inputsave_d[3][6] + "!" + inputsave_d[4][0] + "!" + inputsave_d[4][1] + "!" + inputsave_d[4][2] + "!"
                + inputsave_d[4][3] + "!" + inputsave_d[4][4] + "!" + inputsave_d[4][5] + "!" + inputsave_d[4][6]);
        editor_d.commit();

        SharedPreferences prefname = getSharedPreferences("name", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editorname = prefname.edit();
        editorname.putString("name", td.tBD[select_teacher][cell_teacher] + "선생님");
        editorname.commit();
    }

    private int index_x(int x) {
        int xx = 0;
        xx = (x - 4) % 7;
        return xx;
    }

    private int index_y(int x) {
        int yy = 0;
        if (x < 11)
            yy = 0;
        else if (x < 18)
            yy = 1;
        else if (x < 25)
            yy = 2;
        else if (x < 32)
            yy = 3;
        else
            yy = 4;
        return yy;
    }

    // ===========================================================

    private void select_techer(int t) {
        String t1, t2, text, data;
        for (int i = 0; i < 5; i++)
            for (int k = 0; k < 7; k++) {
                inputsave[i][k] = ".";
                inputsave_d[i][k] = ".";
            }
        String temp;
        int index = cell_weekstart;
        for (int i = 0; i < 5; i++) {
            for (int k = 0; k < 7; k++) {
                if (i == 4 && k > 4) {
                    inputsave[i][k] = ".";
                    inputsave_d[i][k] = ".";
                } else {
                    temp = td.tBD[t][index++];
                    if (temp.length() > 1) {
                        inputsave[i][k] = temp.split("/")[0];
                        inputsave_d[i][k] = temp.split("/")[1];
                    }
                }
            }
        }

        // tv_title.setText(td.tBD[t][cell_teacher].toString() + "선생님");

        // =======================================================

        if (td.tBD[t][cell_class] != "") {
            t1 = td.tBD[t][cell_class].split("-")[0].trim();
            t2 = td.tBD[t][cell_class].split("-")[1].trim();
            if (t2.length() == 1)
                t2 = "0" + t2;
            text = t1 + t2;
            inputsave[4][5] = text;
            inputsave_d[4][5] = "HR";
            inputsave[4][6] = text;
            inputsave_d[4][6] = "CA";

        }
        for (int i = 0; i < 5; i++)
            for (int k = 0; k < 7; k++) {
                if (inputsave[i][k] == "") {
                    inputsave[i][k] = ".";
                    inputsave_d[i][k] = ".";

                }
            }
        // 월------------------
        SharedPreferences pref = getSharedPreferences("teacher", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("teacher",
                inputsave[0][0] + "!" + inputsave[0][1] + "!" + inputsave[0][2] + "!" + inputsave[0][3] + "!"
                        + inputsave[0][4] + "!" + inputsave[0][5] + "!" + inputsave[0][6] + "!" + inputsave[1][0] + "!"
                        + inputsave[1][1] + "!" + inputsave[1][2] + "!" + inputsave[1][3] + "!" + inputsave[1][4] + "!"
                        + inputsave[1][5] + "!" + inputsave[1][6] + "!" + inputsave[2][0] + "!" + inputsave[2][1] + "!"
                        + inputsave[2][2] + "!" + inputsave[2][3] + "!" + inputsave[2][4] + "!" + inputsave[2][5] + "!"
                        + inputsave[2][6] + "!" + inputsave[3][0] + "!" + inputsave[3][1] + "!" + inputsave[3][2] + "!"
                        + inputsave[3][3] + "!" + inputsave[3][4] + "!" + inputsave[3][5] + "!" + inputsave[3][6] + "!"
                        + inputsave[4][0] + "!" + inputsave[4][1] + "!" + inputsave[4][2] + "!" + inputsave[4][3] + "!"
                        + inputsave[4][4] + "!" + inputsave[4][5] + "!" + inputsave[4][6]);
        editor.commit();

        SharedPreferences pref_d = getSharedPreferences("teacher_d", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor_d = pref_d.edit();
        editor_d.putString("teacher_d", inputsave_d[0][0] + "!" + inputsave_d[0][1] + "!" + inputsave_d[0][2] + "!"
                + inputsave_d[0][3] + "!" + inputsave_d[0][4] + "!" + inputsave_d[0][5] + "!" + inputsave_d[0][6] + "!"
                + inputsave_d[1][0] + "!" + inputsave_d[1][1] + "!" + inputsave_d[1][2] + "!" + inputsave_d[1][3] + "!"
                + inputsave_d[1][4] + "!" + inputsave_d[1][5] + "!" + inputsave_d[1][6] + "!" + inputsave_d[2][0] + "!"
                + inputsave_d[2][1] + "!" + inputsave_d[2][2] + "!" + inputsave_d[2][3] + "!" + inputsave_d[2][4] + "!"
                + inputsave_d[2][5] + "!" + inputsave_d[2][6] + "!" + inputsave_d[3][0] + "!" + inputsave_d[3][1] + "!"
                + inputsave_d[3][2] + "!" + inputsave_d[3][3] + "!" + inputsave_d[3][4] + "!" + inputsave_d[3][5] + "!"
                + inputsave_d[3][6] + "!" + inputsave_d[4][0] + "!" + inputsave_d[4][1] + "!" + inputsave_d[4][2] + "!"
                + inputsave_d[4][3] + "!" + inputsave_d[4][4] + "!" + inputsave_d[4][5] + "!" + inputsave_d[4][6]);
        editor_d.commit();
        // =======================================================

    }
    private int num_teacher(String name) {
        int result=1;
        for(int i = 1; i<=teacherNum;i++){
            if(td.tBD[i][0].equals(name)){
                result = i;
                return result;
            }
        }
        return result;
    }

    private int num_widget(String name) {
        int result=0;
        for(int i = 0; i<td.widget_title_arr.length;i++){
            if(td.widget_title_arr[i].equals(name)){
                result = i;
                return result;
            }
        }
        return result;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            Toast.makeText(this, "변경하지 않았습니다.", Toast.LENGTH_SHORT).show();
            finish();

        }
        return super.onKeyDown(keyCode, event);
    }


    // ====================================================
    // 알람을 등록 및 코드
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

            Toast.makeText(context, "매일 오전에 점심메뉴를 알려드립니다.", Toast.LENGTH_LONG).show();
        } catch (ParseException e) {
            // e.printStackTrace();
        }

    }

    // ====================================================
    // 알람을 등록 해제
    // ====================================================
    public static void Alarmunregister(Context context) {
        // Log.e(TAG, "unregisterAlarm");

        Intent intent = new Intent();
        PendingIntent sender = PendingIntent.getBroadcast(context, requestCode, intent, 0);

        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        manager.cancel(sender);
    }

}
