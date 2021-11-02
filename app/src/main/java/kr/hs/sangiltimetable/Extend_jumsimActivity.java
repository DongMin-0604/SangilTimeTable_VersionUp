package kr.hs.sangiltimetable;


import android.os.Bundle;



import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import java.util.Calendar;


public class Extend_jumsimActivity extends AppCompatActivity implements View.OnClickListener {

    WebView web;
    Button bt_end;

    Calendar cal;
    int month=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_extend_jumsim);

        setContentView(R.layout.content_extend_jumsim);

        cal = Calendar.getInstance();
        month = (int) cal.get(Calendar.MONTH);
//
        web = (WebView)findViewById(R.id.Lunch_WebView);


        web.getSettings().setSupportZoom(true);
        web.getSettings().setBuiltInZoomControls(true);
        web.getSettings().setJavaScriptEnabled(false);
        web.getSettings().setUseWideViewPort(true);
        web.getSettings().setLoadWithOverviewMode(true);
        web.getSettings().setSupportZoom(true);
//        web.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
//        web.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);


//        web.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
//        web.setVerticalScrollbarOverlay(true);

        String urljumsim="";

        if( month == 9){    // <- 10 월 기준
           urljumsim = "file:///android_asset/sikdan.html";
        }else{
            urljumsim = "file:///android_asset/sikdan_next.html";
        }

        // OtherActivity.java에 122줄도 수정


        web.loadUrl(urljumsim);

        bt_end = (Button)findViewById(R.id.BT_luchViewEnd);
        bt_end.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        finish();
    }
}
