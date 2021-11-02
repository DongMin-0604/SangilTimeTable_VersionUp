package kr.hs.sangiltimetable;

import androidx.annotation.NonNull;
import  androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import android.Manifest;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener {

    TextView BT_teacher, BT_class, BT_setup, BT_ipsi, BT_silsup;

    TextView TV_main_text;

    int re=0;

    Random ran;

    // 애니메이션 인트로 선언
    Animation Ani1, Ani2, Ani3;
    ImageView intro;
    LinearLayout LL_intro;

    // 점심 알림 선언
    TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7;
    LinearLayout LL_jumsimLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ani_exe(this);   // 인트로 애니 시작

        BT_ipsi = (TextView)findViewById(R.id.ipsi_BT);
        BT_teacher = (TextView)findViewById(R.id.teacher_time);
        BT_class = (TextView)findViewById(R.id.class_time);
        BT_setup = (TextView)findViewById(R.id.basic_BT);
        BT_silsup = (TextView)findViewById(R.id.silsupsil_BT);

        TV_main_text = (TextView)findViewById(R.id.tv_maintxt);


        BT_class.setOnTouchListener(this);
        BT_ipsi.setOnTouchListener(this);
        BT_setup.setOnTouchListener(this);
        BT_silsup.setOnTouchListener(this);
        BT_teacher.setOnTouchListener(this);


        TV_main_text.setOnTouchListener(this);
        ran = new Random();


        jumsim();  // 점심 식단 알림


      //  jumsimAlarmStart();
    }

    static int requestCode = 3365;
    private void jumsimAlarmStart() {

        //====================================================

        SharedPreferences prefAlarm = getSharedPreferences("alarm", Activity.MODE_PRIVATE);
        String basic = prefAlarm.getString("alarm", "1"); // 점심알림
        int select_alarm = Integer.parseInt(basic);


        if (select_alarm == 1) {
            Alarmregister(getApplicationContext());

        } else {
          //  Alarmunregister(getApplicationContext());
        }


    }

    // ====================================================
    // 알람을 등록 및 코드

    // ====================================================





    //====================================================
    public static void Alarmregister(Context context)
    {
//	    Log.e(TAG, "registerAlarm");
        Intent intent = new Intent(context, NotificationAlarmService.class);
        // intent.setAction(NotificationReceiver.INNER_PUSH);
        PendingIntent sender = PendingIntent.getBroadcast(context, requestCode, intent, 0);
        try {
            // 내일 아침 8시 30분에 처음 시작해서, 24시간 마다 실행되게

              Date tomorrow = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2020-09-22 08:25:00");
//            Date tomorrow = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2016-04-25 11:21:00");
//            Date tomorrow = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2020-10-01 16:07:00");

            AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            am.setInexactRepeating(AlarmManager.RTC, tomorrow.getTime(), 24 * 60 * 60 * 1000, sender);

            Toast.makeText(context, "매일 오전에 점심메뉴를 알려드립니다.", Toast.LENGTH_LONG).show();
        } catch (ParseException e) {
            // e.printStackTrace();
        }

    }

    public static void Alarmunregister(Context context) {
        // Log.e(TAG, "unregisterAlarm");

        Intent intent = new Intent();
        PendingIntent sender = PendingIntent.getBroadcast(context, requestCode, intent, 0);

        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        manager.cancel(sender);
    }

    //=================================================================================
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        switch(view.getId()){
            case R.id.class_time:
                Intent intent1 = new Intent(this, ClassTimeTableActivity.class);
                startActivity(intent1);

                break;
            case  R.id.teacher_time:
                Intent intent2 = new Intent(this, TeacherTimeTableActivity.class);
                startActivity(intent2);
                break;

            case R.id.basic_BT:
			     Intent intent3 = new Intent(this,  Setting2Activity.class);
//                Intent intent3 = new Intent(this,  setupPreferenceActivity.class);
                startActivity(intent3);

                break;
            case R.id.ipsi_BT:
                Intent intent4 = new Intent(this,  OtherActivity.class);
                startActivity(intent4);

                break;
            case R.id.silsupsil_BT:
                Intent intent5 = new Intent(this,  SilsupsilActivity.class);
                startActivity(intent5);

                break;

            case R.id.tv_maintxt:
//                TV_main_text.setTextColor(Color.rgb(ran.nextInt(255),ran.nextInt(255),ran.nextInt(255)));
                break;
        }
        TV_main_text.setTextColor(Color.rgb(ran.nextInt(255),ran.nextInt(255),ran.nextInt(255)));
        return false;
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub

        if( keyCode == KeyEvent.KEYCODE_BACK){

            Toast.makeText(this,"종료합니다." , Toast.LENGTH_SHORT).show();
            finish();

        }

        return super.onKeyDown(keyCode, event);
    }

    // ,애니메이션 ---------------------------------------------------------------------
    // 애니메이션 매소드
    void ani_exe(Context context){

        intro = (ImageView)findViewById(R.id.intro_image_main);

        LL_intro = (LinearLayout)findViewById(R.id.Main_intro_LinearLayout);

//        LL_intro.setVisibility(LinearLayout.VISIBLE);

        Ani1 = AnimationUtils.loadAnimation(context, R.anim.ani2);
        Ani2 = AnimationUtils.loadAnimation(context, R.anim.ani1);
        Ani3 = AnimationUtils.loadAnimation(context, R.anim.ani1);

        ani1Listener ani1 = new ani1Listener();
        ani2Listener ani2 = new ani2Listener();
        ani3Listener ani3 = new ani3Listener();

        Ani1.setAnimationListener(ani1);
        Ani2.setAnimationListener(ani2);
        Ani3.setAnimationListener(ani3);

//		intro.setBackgroundResource(R.drawable.splash_intro);
//        intro.setBackgroundResource(R.drawable.logo);

        intro.startAnimation(Ani1);
//         LL_intro.startAnimation(Ani1);
    }



    /// 배경있음 - 그림 나타남(애니1) - 그림사라짐(애니2) - 배경 사라짐(애니3)
    //애니메이션 클래스 선언------------------------------------------------------------------------------

    private class ani1Listener implements Animation.AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            // TODO Auto-generated method stub
            intro.startAnimation(Ani2);
//            LL_intro.startAnimation(Ani2);
            re++;
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
            // TODO Auto-generated method stub

        }

    }
    private class ani2Listener implements Animation.AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            // TODO Auto-generated method stub
//			if(re>0)	{
//
//				intro.startAnimation(Ani1);
//			}
//
//			if(re==2)finish();
            intro.setVisibility(ImageView.GONE);
//            LL_intro.setVisibility(LinearLayout.GONE);
            LL_intro.startAnimation(Ani3);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
            // TODO Auto-generated method stub

        }

    }
    private class ani3Listener implements Animation.AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            // TODO Auto-generated method stub
//			if(re>0)	{
//
//				intro.startAnimation(Ani1);
//			}
//
//			if(re==2)finish();
//            intro.setVisibility(ImageView.GONE);
            LL_intro.setVisibility(LinearLayout.GONE);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
            // TODO Auto-generated method stub

        }

    }
//===================================================================================
    // 점심 안내
    public int click=0;

    public int data_index=0;

    int weekclick=0;

    TimeData dd = new TimeData();
    String [] t = new String[10];

    static String mon, day;

    private String weekend[] = { "월","화","수","목","금"};

    Calendar cal = Calendar.getInstance();
    public int pr_index=0, weekindex=0;

    public String menu(String m, String d, int index){
        String sikdan="", temp;
        String md = m+"/"+d;
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
        }else{

            weekclick=weekindex+click;
            if(weekclick>4) weekclick = weekclick % 5;


            if((data_index+click) >= dd.jumsim.length) click=0;
            temp = dd.jumsim[data_index+click];
            for(int i=0;i<8;i++){
                sikdan = "";
                t[i] = temp.split("/")[i];
                if(t[i].equals(".")) t[i]=" ";
            }
            sikdan = t[index];
//   	           	sikdan = sikdan.trim();
            if(index==1)sikdan = temp.split("/")[0]+"."+temp.split("/")[1]+".("+ weekend[weekclick]+ ")상미고점심";
        }


        return sikdan;
    }

    public void si_pr_m_start(){
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

    void jumsim (){
        LL_jumsimLayout = (LinearLayout)findViewById(R.id.Main_jumsim_Layout);
        LL_jumsimLayout.setOnClickListener(this);

        tv1 = (TextView)findViewById(R.id.main_jumsim_tV1);
        tv2 = (TextView)findViewById(R.id.main_jumsim_tV2);
        tv3 = (TextView)findViewById(R.id.main_jumsim_tV3);
        tv4 = (TextView)findViewById(R.id.main_jumsim_tV4);
        tv5 = (TextView)findViewById(R.id.main_jumsim_tV5);
        tv6 = (TextView)findViewById(R.id.main_jumsim_tV6);
        tv7 = (TextView)findViewById(R.id.main_jumsim_tV7);


        si_pr_m_start();

        if((click+data_index) < dd.jumsim.length)click++;
        if(click>5)click =0;
        if((click+data_index) >= dd.jumsim.length)click =0;

        cal = Calendar.getInstance();

        mon = String.valueOf(cal.get(Calendar.MONTH)+1);
        day = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));


        tv1.setText(menu(mon,day,1));
        tv2.setText(menu(mon,day,2));
        tv3.setText(menu(mon,day,3));
        tv4.setText(menu(mon,day,4));
        tv5.setText(menu(mon,day,5));
        tv6.setText(menu(mon,day,6));
        tv7.setText(menu(mon,day,7));



    }

    @Override
    public void onClick(View v) {



        if(v.getId() == R.id.Main_jumsim_Layout) {




            si_pr_m_start();

            if ((click + data_index) < dd.jumsim.length) click++;
            if (click > 5) click = 0;
            if ((click + data_index) >= dd.jumsim.length) click = 0;

            tv1.setText(menu(mon, day, 1));
            tv2.setText(menu(mon, day, 2));
            tv3.setText(menu(mon, day, 3));
            tv4.setText(menu(mon, day, 4));
            tv5.setText(menu(mon, day, 5));
            tv6.setText(menu(mon, day, 6));
            tv7.setText(menu(mon, day, 7));

        }
    }


}
