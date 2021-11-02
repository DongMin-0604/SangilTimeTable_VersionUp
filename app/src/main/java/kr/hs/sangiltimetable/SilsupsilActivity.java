package kr.hs.sangiltimetable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class SilsupsilActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView tv_title, tvmon1,tvmon2,tvmon3,tvmon4,tvmon5,tvmon6,tvmon0 ;
    TextView tvtue1,tvtue2,tvtue3,tvtue4,tvtue5,tvtue6,tvtue0;
    TextView tvwed1,tvwed2,tvwed3,tvwed4,tvwed5,tvwed6,tvwed0;
    TextView tvthu1,tvthu2,tvthu3,tvthu4,tvthu5,tvthu6,tvthu0;
    TextView tvfri1,tvfri2,tvfri3,tvfri4,tvfri5,tvfri6,tvfri0;

    TimeData td = new TimeData();

    ArrayAdapter<String> adapter_sss;
    ArrayList<String> arrlist_sss;

    final int cell_teacher = 0;
    final int cell_class = 1;
    final int cell_weekstart = 4;
    final int cell_weekend=36;

    Spinner sp_sss;



    int teacherNum=0;

    String [] s_name = {
            "(공통)미술실",
            "(공통)관현악실",
            "(공통)음악실",

            "(S)게임콘텐츠제작2실(본관2층)",
            "(S)게임콘텐츠제작3실(본관2층)",
            "(S)데이터베이스프로그래밍실(실습동3층)",
            "(S)응용프로그래밍실(실습동3층)",
            "(S)스마트문화앱콘텐츠제작실(실습동2층)",
            "(S)디지털융합콘텐츠제작실(실습동2층)",
            "(S)게임콘텐츠제작1실(실습동1층)",
            "(S)도제전용1실",
            "(S)도제전용2실",

            "(C)유비쿼터스실",
            "(C)정보통신실",
            "(C)프로그래밍실",
            "(C)네트워크실",
            "(C)전자전산응용실",

            "(D)조형실",
//            "(D)소묘실",
            "(D)시각디자인실",
            "(D)제품디자인실",
            "(D)컴퓨터그래픽실",
            "(D)영상디자인실",

            "(A)만화영상편집실",
            "(A)조형실",
            "(A)만화창작실",
            "(A)만화실기실",
            "(A)컴퓨터그래픽실",

            "(B)바리스타실무실",
            "(B)조리실습실",
            "(B)제과제빵1실",
            "(B)제과제빵2실"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_silsupsil);

        startSETUP();
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


        LinearLayout bgl = (LinearLayout)findViewById(R.id.BG_sil);
//			   bgl.setBackgroundResource(ran.bg_random());

        //	   tv_title = (TextView)findViewById(R.id.class_title);
        tvmon0 = (TextView)findViewById(R.id.sss_mon1);
        tvmon1 = (TextView)findViewById(R.id.sss_mon2);
        tvmon2 = (TextView)findViewById(R.id.sss_mon3);
        tvmon3 = (TextView)findViewById(R.id.sss_mon4);
        tvmon4 = (TextView)findViewById(R.id.sss_mon5);
        tvmon5 = (TextView)findViewById(R.id.sss_mon6);
        tvmon6 = (TextView)findViewById(R.id.sss_mon7);

        tvtue0  = (TextView)findViewById(R.id.sss_tue1);
        tvtue1  = (TextView)findViewById(R.id.sss_tue2);
        tvtue2  = (TextView)findViewById(R.id.sss_tue3);
        tvtue3  = (TextView)findViewById(R.id.sss_tue4);
        tvtue4  = (TextView)findViewById(R.id.sss_tue5);
        tvtue5  = (TextView)findViewById(R.id.sss_tue6);
        tvtue6  = (TextView)findViewById(R.id.sss_tue7);

        tvwed0  = (TextView)findViewById(R.id.sss_wed1);
        tvwed1  = (TextView)findViewById(R.id.sss_wed2);
        tvwed2  = (TextView)findViewById(R.id.sss_wed3);
        tvwed3  = (TextView)findViewById(R.id.sss_wed4);
        tvwed4  = (TextView)findViewById(R.id.sss_wed5);
        tvwed5  = (TextView)findViewById(R.id.sss_wed6);
        tvwed6  = (TextView)findViewById(R.id.sss_wed7);

        tvthu0  = (TextView)findViewById(R.id.sss_thu1);
        tvthu1  = (TextView)findViewById(R.id.sss_thu2);
        tvthu2  = (TextView)findViewById(R.id.sss_thu3);
        tvthu3  = (TextView)findViewById(R.id.sss_thu4);
        tvthu4  = (TextView)findViewById(R.id.sss_thu5);
        tvthu5  = (TextView)findViewById(R.id.sss_thu6);
        tvthu6  = (TextView)findViewById(R.id.sss_thu7);

        tvfri0  = (TextView)findViewById(R.id.sss_fri1);
        tvfri1  = (TextView)findViewById(R.id.sss_fri2);
        tvfri2  = (TextView)findViewById(R.id.sss_fri3);
        tvfri3  = (TextView)findViewById(R.id.sss_fri4);
        tvfri4  = (TextView)findViewById(R.id.sss_fri5);
        tvfri5  = (TextView)findViewById(R.id.sss_fri6);
        tvfri6  = (TextView)findViewById(R.id.sss_fri7);

        sp_sss = (Spinner)findViewById(R.id.sp_silsupsil);


        sp_sss.setPrompt("실습실을 선택하세요");

        arrlist_sss = new ArrayList<String>();

        for (int i = 0 ; i < s_name.length;i++){
            arrlist_sss.add(s_name[i]);
        }

				/*
				 *
(공통)멀티미디어실

(C)전자회로실
(C)전자전산응용실
(C)전자응용실
(C)전자계산기구조실

(D)영상디자인실
(D)소묘실
(D)조형실
(D)공예디자인실
(D)컴퓨터그래픽실
(D)시각디자인실

(A)만화창작실
(A)만화컴퓨터실
(A)컴퓨터그래픽실
(A)영상애니실
(A)취업실기실

(B)바리스타실무실
(B)웰빙제품실
(B)푸드공예실기기술실


				 *
				 *
				 */



        adapter_sss = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arrlist_sss);

        teacherNum = td.tBD.length;
        sp_sss.setAdapter(adapter_sss);
        sp_sss.setOnItemSelectedListener(this);

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        seach_sss(s_name[i]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void seach_sss(String select) {
        String s_data, data, dataT, dataM;
        clear();

        //	tv_title.setText("("+ s_year + "학년"+ s_class + "반)");
        for(int x=cell_weekstart;x<= cell_weekend;x++)
            for(int y = 1 ; y < teacherNum ; y++){
                if(td.sss[y][x].trim() != ""){
                    s_data = td.sss[y][x];
                    data = td.tBD[y][x].trim();
                    if(data.length()>0){
                        dataT = data.split("/")[0].trim();
                        dataM = data.split("/")[1].trim();
                        if(s_data.trim() == select.trim()){
                            inputTV(index_y(x), index_x(x),dataT + "\n"+ dataM + "\n"+ td.tBD[y][cell_teacher].toString());
                        }
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
        finish();
        return super.onKeyDown(keyCode, event);
    }
}
