package kr.hs.sangiltimetable;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class TeacherTimeTableActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TimeData td = new TimeData();

    int teacherNum, select_teacher_num, startint=0;

    TextView tv_title, tvmon1,tvmon2,tvmon3,tvmon4,tvmon5,tvmon6,tvmon0 ;
    TextView tvtue1,tvtue2,tvtue3,tvtue4,tvtue5,tvtue6,tvtue0;
    TextView tvwed1,tvwed2,tvwed3,tvwed4,tvwed5,tvwed6,tvwed0;
    TextView tvthu1,tvthu2,tvthu3,tvthu4,tvthu5,tvthu6,tvthu0;
    TextView tvfri1,tvfri2,tvfri3,tvfri4,tvfri5,tvfri6,tvfri0;

    // ListView list_teacher;
    Spinner sp_teacher;

    ArrayList<String> arrlist;

    final int cell_teacher = 0;
    final int cell_class = 1;
    final int cell_weekstart = 4;
    final int cell_weekend=36;
    String basic;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_time_table);

        startSETUP();

        teacherNum= td.tBD.length;

        //        list_teacher = (ListView)findViewById(R.id.list_teacher);

        sp_teacher = (Spinner)findViewById(R.id.sp_teacher);

        arrlist = new ArrayList<String>();
        for(int i=1;i<teacherNum;i++){
            arrlist.add(td.tBD[i][cell_teacher]+"선생님");
        }
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arrlist);
        sp_teacher.setPrompt("선생님을 선택하세요");
        sp_teacher.setAdapter(adapter);
        sp_teacher.setOnItemSelectedListener(this);

        //	   list_teacher.setAdapter(adapter);
        //	   list_teacher.setOnItemClickListener(this);


        SharedPreferences pref = getSharedPreferences("saveid", Activity.MODE_PRIVATE);
        basic = pref.getString("saveid", "1/1/1/0");

        sp_teacher.setSelection(Integer.parseInt(basic.split("/")[0])-1);
    }

    private void select_techer(int t){
        clear();
        int index=cell_weekstart;
        for(int i=0;i<5;i++ ){
            for(int k=0;k<7;k++){
                if(i==4 && k >4)
                {

                }else
                {
                    inputTV(i, k, td.tBD[t][index++]);
                }
            }
        }

        //	tv_title.setText(td.tBD[t][cell_teacher].toString() + "선생님");
        if(td.tBD[t][cell_class]!=""){
            String t1,t2,text;
            t1 = td.tBD[t][cell_class].split("-")[0].trim();
            t2 = td.tBD[t][cell_class].split("-")[1].trim();
            if(t2.length()==1) t2 = "0"+ t2;
            text = t1 + t2;
            inputTV(4, 5, text+"/HR");
            inputTV(4, 6, text+"/CA");

        }

    }


    // 월 X =0 금 = 4,   시간  Y 1교시 0, 시간  Y 7교시 6
    private void inputTV(int x, int y, String text){
        if(text != ""){
            String t1, t2;
            t1 = text.split("/")[0].trim();
            t2 = text.split("/")[1].trim();
            text = t1 + "\n" + t2;
        }
        switch (x) {
            case 0:
                switch (y) {
                    case 0:
                        tvmon0.setText(text);
                        break;
                    case 1:
                        tvmon1.setText(text);
                        break;
                    case 2:
                        tvmon2.setText(text);
                        break;
                    case 3:
                        tvmon3.setText(text);
                        break;
                    case 4:
                        tvmon4.setText(text);
                        break;
                    case 5:
                        tvmon5.setText(text);
                        break;
                    case 6:
                        tvmon6.setText(text);
                        break;
                }
                break;
            case 1:
                switch (y) {
                    case 0:
                        tvtue0.setText(text);
                        break;
                    case 1:
                        tvtue1.setText(text);
                        break;
                    case 2:
                        tvtue2.setText(text);
                        break;
                    case 3:
                        tvtue3.setText(text);
                        break;
                    case 4:
                        tvtue4.setText(text);
                        break;
                    case 5:
                        tvtue5.setText(text);
                        break;
                    case 6:
                        tvtue6.setText(text);
                        break;
                }
                break;
            case 2:
                switch (y) {
                    case 0:
                        tvwed0.setText(text);
                        break;
                    case 1:
                        tvwed1.setText(text);
                        break;
                    case 2:
                        tvwed2.setText(text);
                        break;
                    case 3:
                        tvwed3.setText(text);
                        break;
                    case 4:
                        tvwed4.setText(text);
                        break;
                    case 5:
                        tvwed5.setText(text);
                        break;
                    case 6:
                        tvwed6.setText(text);
                        break;
                }
                break;
            case 3:
                switch (y) {
                    case 0:
                        tvthu0.setText(text);
                        break;
                    case 1:
                        tvthu1.setText(text);
                        break;
                    case 2:
                        tvthu2.setText(text);
                        break;
                    case 3:
                        tvthu3.setText(text);
                        break;
                    case 4:
                        tvthu4.setText(text);
                        break;
                    case 5:
                        tvthu5.setText(text);
                        break;
                    case 6:
                        tvthu6.setText(text);
                        break;
                }
                break;

            case 4:
                switch (y) {
                    case 0:
                        tvfri0.setText(text);
                        break;
                    case 1:
                        tvfri1.setText(text);
                        break;
                    case 2:
                        tvfri2.setText(text);
                        break;
                    case 3:
                        tvfri3.setText(text);
                        break;
                    case 4:
                        tvfri4.setText(text);
                        break;
                    case 5:
                        tvfri5.setText(text);
                        break;
                    case 6:
                        tvfri6.setText(text);
                        break;
                }
                break;
        }

    }

    private void startSETUP(){


        LinearLayout bgl = (LinearLayout)findViewById(R.id.BG_teacher);
//	   bgl.setBackgroundResource(ran.bg_random());

        //   tv_title = (TextView)findViewById(R.id.teacher_title);
        tvmon0 = (TextView)findViewById(R.id.mon1);
        tvmon1 = (TextView)findViewById(R.id.mon2);
        tvmon2 = (TextView)findViewById(R.id.mon3);
        tvmon3 = (TextView)findViewById(R.id.mon4);
        tvmon4 = (TextView)findViewById(R.id.mon5);
        tvmon5 = (TextView)findViewById(R.id.mon6);
        tvmon6 = (TextView)findViewById(R.id.mon7);

        tvtue0  = (TextView)findViewById(R.id.tue1);
        tvtue1  = (TextView)findViewById(R.id.tue2);
        tvtue2  = (TextView)findViewById(R.id.tue3);
        tvtue3  = (TextView)findViewById(R.id.tue4);
        tvtue4  = (TextView)findViewById(R.id.tue5);
        tvtue5  = (TextView)findViewById(R.id.tue6);
        tvtue6  = (TextView)findViewById(R.id.tue7);

        tvwed0  = (TextView)findViewById(R.id.wed1);
        tvwed1  = (TextView)findViewById(R.id.wed2);
        tvwed2  = (TextView)findViewById(R.id.wed3);
        tvwed3  = (TextView)findViewById(R.id.wed4);
        tvwed4  = (TextView)findViewById(R.id.wed5);
        tvwed5  = (TextView)findViewById(R.id.wed6);
        tvwed6  = (TextView)findViewById(R.id.wed7);

        tvthu0  = (TextView)findViewById(R.id.thu1);
        tvthu1  = (TextView)findViewById(R.id.thu2);
        tvthu2  = (TextView)findViewById(R.id.thu3);
        tvthu3  = (TextView)findViewById(R.id.thu4);
        tvthu4  = (TextView)findViewById(R.id.thu5);
        tvthu5  = (TextView)findViewById(R.id.thu6);
        tvthu6  = (TextView)findViewById(R.id.thu7);

        tvfri0  = (TextView)findViewById(R.id.fri1);
        tvfri1  = (TextView)findViewById(R.id.fri2);
        tvfri2  = (TextView)findViewById(R.id.fri3);
        tvfri3  = (TextView)findViewById(R.id.fri4);
        tvfri4  = (TextView)findViewById(R.id.fri5);
        tvfri5  = (TextView)findViewById(R.id.fri6);
        tvfri6  = (TextView)findViewById(R.id.fri7);



        startint=0;

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        if(adapterView.getId()==R.id.sp_teacher)
        {
            select_techer(i + 1);
            select_teacher_num = i + 1;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void clear(){
        for(int x=0;x<5;x++){
            for(int y=0;y<7;y++){
                inputTV(x, y, "");
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if( keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
