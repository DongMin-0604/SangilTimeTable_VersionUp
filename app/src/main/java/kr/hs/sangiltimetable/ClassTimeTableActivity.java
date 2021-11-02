package kr.hs.sangiltimetable;

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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ClassTimeTableActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TimeData td = new TimeData();

    int select_year, select_class;

    int teacherNum, startint=0;

    String basic;

    TextView tv_title, tvmon1,tvmon2,tvmon3,tvmon4,tvmon5,tvmon6,tvmon0 ;
    TextView tvtue1,tvtue2,tvtue3,tvtue4,tvtue5,tvtue6,tvtue0;
    TextView tvwed1,tvwed2,tvwed3,tvwed4,tvwed5,tvwed6,tvwed0;
    TextView tvthu1,tvthu2,tvthu3,tvthu4,tvthu5,tvthu6,tvthu0;
    TextView tvfri1,tvfri2,tvfri3,tvfri4,tvfri5,tvfri6,tvfri0;
    TextView tvteacher;

    // ListView list_teacher;
    Spinner sp_class, sp_year;

    ArrayList<String> arrlist_year;
    ArrayList<String> arrlist_class;

    final int cell_teacher = 0;
    final int cell_class = 1;
    final int cell_weekstart = 4;
    final int cell_weekend=36;
    ArrayAdapter<String> adapter_year;
    ArrayAdapter<String> adapter_class;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_time_table);

        startSETUP();
        startint=0;



    }



    // 월 X =0 금 = 4,   시간  Y 1교시 0, 시간  Y 7교시 6
    private void inputTV(int x, int y, String text){
	/*		if(text != ""){
				String t1, t2;
				t1 = text.split("/")[0].trim();
				t2 = text.split("/")[1].trim();
			    text = t1 + "\n" + t2;
			} */
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


        LinearLayout bgl = (LinearLayout)findViewById(R.id.BG_class);
        //	   bgl.setBackgroundResource(ran.bg_random());


        tvteacher = (TextView)findViewById(R.id.class_teacher);

        //	   tv_title = (TextView)findViewById(R.id.class_title);
        tvmon0 = (TextView)findViewById(R.id.class_mon1);
        tvmon1 = (TextView)findViewById(R.id.class_mon2);
        tvmon2 = (TextView)findViewById(R.id.class_mon3);
        tvmon3 = (TextView)findViewById(R.id.class_mon4);
        tvmon4 = (TextView)findViewById(R.id.class_mon5);
        tvmon5 = (TextView)findViewById(R.id.class_mon6);
        tvmon6 = (TextView)findViewById(R.id.class_mon7);

        tvtue0  = (TextView)findViewById(R.id.class_tue1);
        tvtue1  = (TextView)findViewById(R.id.class_tue2);
        tvtue2  = (TextView)findViewById(R.id.class_tue3);
        tvtue3  = (TextView)findViewById(R.id.class_tue4);
        tvtue4  = (TextView)findViewById(R.id.class_tue5);
        tvtue5  = (TextView)findViewById(R.id.class_tue6);
        tvtue6  = (TextView)findViewById(R.id.class_tue7);

        tvwed0  = (TextView)findViewById(R.id.class_wed1);
        tvwed1  = (TextView)findViewById(R.id.class_wed2);
        tvwed2  = (TextView)findViewById(R.id.class_wed3);
        tvwed3  = (TextView)findViewById(R.id.class_wed4);
        tvwed4  = (TextView)findViewById(R.id.class_wed5);
        tvwed5  = (TextView)findViewById(R.id.class_wed6);
        tvwed6  = (TextView)findViewById(R.id.class_wed7);

        tvthu0  = (TextView)findViewById(R.id.class_thu1);
        tvthu1  = (TextView)findViewById(R.id.class_thu2);
        tvthu2  = (TextView)findViewById(R.id.class_thu3);
        tvthu3  = (TextView)findViewById(R.id.class_thu4);
        tvthu4  = (TextView)findViewById(R.id.class_thu5);
        tvthu5  = (TextView)findViewById(R.id.class_thu6);
        tvthu6  = (TextView)findViewById(R.id.class_thu7);

        tvfri0  = (TextView)findViewById(R.id.class_fri1);
        tvfri1  = (TextView)findViewById(R.id.class_fri2);
        tvfri2  = (TextView)findViewById(R.id.class_fri3);
        tvfri3  = (TextView)findViewById(R.id.class_fri4);
        tvfri4  = (TextView)findViewById(R.id.class_fri5);
        tvfri5  = (TextView)findViewById(R.id.class_fri6);
        tvfri6  = (TextView)findViewById(R.id.class_fri7);

        sp_class = (Spinner)findViewById(R.id.sp_class);
        sp_year = (Spinner)findViewById(R.id.sp_year);

        sp_class.setPrompt("학년을 선택하세요");
        sp_year.setPrompt("학년을 선택하세요");

        arrlist_year = new ArrayList<String>();
        arrlist_class = new ArrayList<String>();

        arrlist_year.add("1학년");
        arrlist_year.add("2학년");
        arrlist_year.add("3학년");

        for(int i=0;i<11;i++){
            arrlist_class.add((i+1) +"반");
        }
        adapter_year = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arrlist_year);
        adapter_class = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arrlist_class);

        sp_year.setAdapter(adapter_year);
        sp_class.setAdapter(adapter_class);

        sp_year.setOnItemSelectedListener(this);
        sp_class.setOnItemSelectedListener(this);

        select_class = 0;
        select_year = 0;

        teacherNum = td.tBD.length;

        SharedPreferences pref = getSharedPreferences("saveid", Activity.MODE_PRIVATE);
        basic = pref.getString("saveid", "1/1/1/0");

        sp_year.setSelection(Integer.parseInt(basic.split("/")[1])-1);
        sp_class.setSelection(Integer.parseInt(basic.split("/")[2])-1);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()) {
            case R.id.sp_year:

                select_year = i+1;


                break;
            case R.id.sp_class:
                select_class = i+1;

                break;
        }

        seach_class(select_year, select_class);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void seach_class( int s_year, int s_class) {
        String data, dataT, dataM;
        clear();
        int iy, ic;
        //	tv_title.setText("("+ s_year + "학년"+ s_class + "반)");
        for(int x=cell_weekstart;x<= cell_weekend;x++)
            for(int y = 1 ; y < teacherNum ; y++){
                if(td.tBD[y][x].trim() != ""){
                    data = td.tBD[y][x].trim();
                    if(data.length()>0) {
                        try {
                            if(data == "/"){
                               dataT="";
                               dataM="";

                            }else {
                                dataT = data.split("/")[0].trim();
                                dataM = data.split("/")[1].trim();

                                iy = Integer.parseInt(dataT.substring(0, 1));
                                ic = Integer.parseInt(dataT.substring(1));

                                if (s_year == iy && s_class == ic) {
                                    inputTV(index_y(x), index_x(x), dataM + "\n" + td.tBD[y][cell_teacher].toString());
                                }
                            }
                        } catch (Exception e) {
                            Toast.makeText(this, "data:" + data , Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        for(int i=1;i<teacherNum;i++){
            if(td.tBD[i][cell_class] != ""){
                data = td.tBD[i][cell_class];
                dataT = data.split("-")[0];
                dataM = data.split("-")[1];
                iy = Integer.parseInt(dataT);
                ic = Integer.parseInt(dataM);
                if(iy == s_year && ic == s_class){
                    inputTV(4, 5, "HR\n"+td.tBD[i][cell_teacher].trim());
                    inputTV(4, 6, "CA\n"+td.tBD[i][cell_teacher].trim());
                    tvteacher.setText(td.tBD[i][cell_teacher].trim()+"담임선생님");
                }

            }
        }

    }
    private void clear(){
        for(int x=0;x<5;x++){
            for(int y=0;y<7;y++){
                inputTV(x, y, "");
            }
        }
    }
    private int index_x(int x){
        int xx=0;
        xx = (x+3)%7;
        return xx;
    }
    private int index_y(int x){
        int yy=0;
        if(x<11)
            yy = 0;
        else if(x<18)
            yy = 1;
        else if(x<25)
            yy = 2;
        else if(x<32)
            yy = 3;
        else
            yy = 4;
        return yy;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if( keyCode == KeyEvent.KEYCODE_BACK){
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
