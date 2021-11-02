package kr.hs.sangiltimetable;

import androidx.appcompat.app.AppCompatActivity;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class OtherActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    Spinner sp;
//    ArrayList<String> arrlist;
    String[] arrlist = {
            "식단표",
            "상미고 교가",
            "상미고 교실 배치도",
            "선생님 교무실 배치도",
            "2021년 학사 일정"
    };
    ArrayAdapter<String> adapter;
    private WebView web_ipsi;

    Button bt_play;

    Calendar cal = Calendar.getInstance();
    int month=0;

    MediaPlayer gyogamp3;
    int mp3 =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        web_ipsi = (WebView)findViewById(R.id.webView_ipsi);




        web_ipsi.setWebContentsDebuggingEnabled(false);

        gyogamp3 = MediaPlayer.create(this, R.raw.gyoga);

        sp = (Spinner)findViewById(R.id.sangil_spinner);


        sp.setPrompt("상일미디어고등학교입니다...");

//        arrlist = new ArrayList<String>();
//        arrlist.add("식단표");
//        arrlist.add("상미고 교가");
//        arrlist.add("상미고 교실배치도");
//        arrlist.add("선생님 교무실배치도");
//        arrlist.add("2019년 학사일정");
//        arrlist.add("상일시간표 두더지게임");

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arrlist);

        sp.setAdapter(adapter);

        //7. 아이템 선택을 위한 아이템 클릭이벤트 수신기 켬

        bt_play = (Button)findViewById(R.id.playBT_ipsi);


        sp.setOnItemSelectedListener(this);


        bt_play.setOnClickListener(this);

        bt_play.setVisibility(View.GONE);


        cal = Calendar.getInstance();
        month = cal.get(Calendar.MONTH);
    }

    @Override
    public void onBackPressed() {
        if(mp3>0) {
            gyogamp3.stop();
            mp3=0;
        }
        finish();

        super.onBackPressed();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        String urljumsim="";

        switch(i){

            case 0:
                bt_play.setVisibility(View.GONE);
                if(mp3>0) {
                    gyogamp3.pause();
                    gyogamp3.seekTo(0);
                    mp3=0;
                }
                web_ipsi.getSettings().setSupportZoom(true);
                web_ipsi.getSettings().setBuiltInZoomControls(true);
                web_ipsi.getSettings().setJavaScriptEnabled(false);
                web_ipsi.getSettings().setUseWideViewPort(true);
                web_ipsi.getSettings().setLoadWithOverviewMode(true);
                web_ipsi.getSettings().setSupportZoom(true);
//                web_ipsi.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
//                web_ipsi.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);


                if( month == 9){     //<- 10월 기준
                    urljumsim = "file:///android_asset/sikdan.html";
                }else{
                    urljumsim = "file:///android_asset/sikdan_next.html";
                }

                // Extend)jumsimActivity.java  53줄도 수정

                web_ipsi.loadUrl(urljumsim);


                break;


            case 1:
                bt_play.setVisibility(View.VISIBLE);
                web_ipsi.getSettings().setSupportZoom(false);
                web_ipsi.getSettings().setBuiltInZoomControls(false);
                web_ipsi.getSettings().setJavaScriptEnabled(false);
//                web_ipsi.setVerticalScrollbarOverlay(true);
                web_ipsi.getSettings().setUseWideViewPort(false);
                web_ipsi.getSettings().setLoadWithOverviewMode(false);
                web_ipsi.getSettings().setSupportZoom(false);

                //		web_ipsi.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
                String url3 = "file:///android_asset/gyoga.htm";
                web_ipsi.loadUrl(url3);
                if(mp3==0){


                }
                break;

            case 2:
                bt_play.setVisibility(View.GONE);
                if(mp3>0) {
                    gyogamp3.pause();
                    gyogamp3.seekTo(0);
                    mp3=0;
                }
                web_ipsi.getSettings().setSupportZoom(true);
                web_ipsi.getSettings().setBuiltInZoomControls(true);
                web_ipsi.getSettings().setJavaScriptEnabled(false);
                web_ipsi.getSettings().setUseWideViewPort(false);
                web_ipsi.getSettings().setLoadWithOverviewMode(false);
                web_ipsi.getSettings().setSupportZoom(false);

//                web_ipsi.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
//                web_ipsi.setVerticalScrollbarOverlay(true);
                String url2 = "file:///android_asset/gyosil2021.htm";
                web_ipsi.loadUrl(url2);
                break;



            case 3:
                bt_play.setVisibility(View.GONE);
                if(mp3>0) {
                    gyogamp3.pause();
                    gyogamp3.seekTo(0);
                    mp3=0;
                }
                web_ipsi.getSettings().setSupportZoom(true);
                web_ipsi.getSettings().setBuiltInZoomControls(true);
                web_ipsi.getSettings().setJavaScriptEnabled(false);
                web_ipsi.getSettings().setUseWideViewPort(false);
                web_ipsi.getSettings().setLoadWithOverviewMode(false);
                web_ipsi.getSettings().setSupportZoom(false);
//                web_ipsi.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
                String url5 = "file:///android_asset/gyomoosil2021.htm";
                web_ipsi.loadUrl(url5);
                break;

            case 4:
                bt_play.setVisibility(View.GONE);
                if(mp3>0) {
                    gyogamp3.pause();
                    gyogamp3.seekTo(0);
                    mp3=0;
                }
                web_ipsi.getSettings().setSupportZoom(true);
                web_ipsi.getSettings().setBuiltInZoomControls(true);
                web_ipsi.getSettings().setJavaScriptEnabled(false);
                web_ipsi.getSettings().setUseWideViewPort(true);
                web_ipsi.getSettings().setLoadWithOverviewMode(true);
                web_ipsi.getSettings().setSupportZoom(true);
//                web_ipsi.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
//                web_ipsi.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
                String urlhaksa = "file:///android_asset/haksa.html";
                web_ipsi.loadUrl(urlhaksa);
                break;

//        case 4:
//        	bt_play.setVisibility(View.GONE);
//        	if(mp3>0) {
//        		gyogamp3.pause();
//				gyogamp3.seekTo(0);
//				mp3=0;
//			}  // if(mp3>0)
//        	web_ipsi.getSettings().setSupportZoom(true);
//			web_ipsi.getSettings().setBuiltInZoomControls(true);
//			web_ipsi.getSettings().setJavaScriptEnabled(false);
//			web_ipsi.setVerticalScrollbarOverlay(true);
//			web_ipsi.getSettings().setUseWideViewPort(false);
//			web_ipsi.getSettings().setLoadWithOverviewMode(false);
//			web_ipsi.getSettings().setSupportZoom(false);
//
//		//	web_ipsi.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
//			try{
//			     String url1 = "http://www.sangilmedia.hs.kr/popup/popup.do?cmd=view&from=listPopup&puNo=77143";
//   	             web_ipsi.loadUrl(url1);
//			}catch (Exception e) {
//				// TODO: handle exception
//				String url1 = "file:///android_asset/sikdanerro.htm";
//  	             web_ipsi.loadUrl(url1);
//
//			}
//			break;
//            case 5:
//                bt_play.setVisibility(View.GONE);
//                if(mp3>0) {
//                    gyogamp3.pause();
//                    gyogamp3.seekTo(0);
//                    mp3=0;
//                }
//                Intent intent_game = new Intent(this, gameActivity.class);
//                startActivity(intent_game);
//                break;

        }
        web_ipsi.setWebContentsDebuggingEnabled(false);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.playBT_ipsi){
            if(mp3>0) {
                gyogamp3.pause();
                mp3=0;
            }
            else
            {
                gyogamp3.start();
                mp3++;
            }

        }
    }
}
