package kr.hs.sangiltimetable;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.RemoteViews;

import androidx.core.app.NotificationCompat;

public class NotificationAlarmService extends BroadcastReceiver {

    TimeData td = new TimeData();

    public static final String NOTIFICATION_CHANNEL_ID = "3365";


    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        if(!td.todaytitle().equals("공") && td.todaylunch(0).length()>0){

            //2020년버전  시작 =============================================================


            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.notificationarlm);
            remoteViews.setTextViewText(R.id.notification_txt, td.todaytitle());
            remoteViews.setTextViewText(R.id.notification_txt2, td.todaylunch(0)+"\n"+
                    td.todaylunch(1)+"");

            NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
            Intent notificatonIntent = new Intent(context, Extend_jumsimActivity.class);
            notificatonIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notificatonIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
                    .setSmallIcon(R.drawable.logo)
                    .setContent(remoteViews)   // 백그라운드 배경
                    .setContentTitle(td.todaytitle())
                    .setContentText(td.todaylunch(0)+" (펼쳐보세요~)")
                    .setPriority(Notification.PRIORITY_HIGH)
                    .setContentIntent(pendingIntent);
//                    .setAutoCancel(true);
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


            // 2020년버전 끝 ===============================================================


            //2017년버전 시작 ===============================================================
/*
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.notificationarlm);
            remoteViews.setTextViewText(R.id.notification_txt, td.todaytitle());
            remoteViews.setTextViewText(R.id.notification_txt2, td.todaylunch(0)+"\n"+
                    td.todaylunch(1)+"");

            Intent intent2 = new Intent(context, Extend_jumsimActivity.class);
            intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            PendingIntent pendingintent = PendingIntent.getActivity(context, 0, intent2, 0);


            Notification.InboxStyle style = new Notification.InboxStyle(
                    new Notification.Builder(context).setSmallIcon(R.drawable.logo)
                            .setContent(remoteViews)   // 백그라운드 배경
                            .setContentTitle(td.todaytitle())
                            .setContentText(td.todaylunch(0)+" (펼쳐보세요~)")
                            .setPriority(Notification.PRIORITY_HIGH)
                            .setContentIntent(pendingintent)
            );
            style.addLine(td.todaylunch(0));
            style.addLine(td.todaylunch(1)+"  ※클릭하면 한달 급식표가 나옵니다.");




            NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            nm.notify(3365,style.build());

*/
            //2017년버전 끝 ======================================================================================

     try {
//         throw new UnsupportedOperationException("Not yet implemented");

     }catch (Exception e){

     }
    }
}

//	public Bitmap Txt2Img (Context context) {
//		float w,h;
//		Bitmap btm = null;
//		Bitmap back = BitmapFactory.decodeResource(context.getResources(), R.drawable.back_widget);
//		w = back.getWidth();
//		h = back.getHeight();
//		Canvas canvas = new Canvas(back);
//		Paint p = new Paint();
//		p.setColor(Color.BLACK);
//		p.setTextSize(h/3);
//		canvas.drawText(td.todaytitle(), (float) (w * 0.05), (float)( 0 + h * 0.05), p);
//		p.setTextSize(h/4);
//		canvas.drawText(td.todaylunch(0), (float) (w * 0.08), (float)( h/3 + h * 0.1), p);
//		canvas.drawText(td.todaylunch(1), (float) (w * 0.05), (float)( h*2/3 + h * 0.1), p);
//
//		BitmapDrawable btmDrw = new BitmapDrawable();
//		btmDrw.setBounds(0,0,(int)w,(int)h);
//		btmDrw.draw(canvas);
//
//		btm = btmDrw.getBitmap();
//
//		return btm;
//	}

}


//PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, new Intent(context, null), PendingIntent.FLAG_UPDATE_CURRENT);
//PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, null, PendingIntent.FLAG_CANCEL_CURRENT);

//Notification.Builder builder = new Notification.Builder(context);
//// 작은 아이콘 이미지.
//builder.setSmallIcon(R.drawable.logo);
//// 알림이 출력될 때 상단에 나오는 문구.
//builder.setTicker("상미고점심메뉴");
//// 알림 출력 시간.
//builder.setWhen(System.currentTimeMillis());
//// 알림 제목.
//builder.setContentTitle(td.todaytitle());
//// 프로그래스 바.
////builder.setProgress(100, 50, false);
//
//// 알림 내용.
//builder.setContentText(td.todaylunch(0));
//
////builder.setStyle(new Notification.InboxStyle()
////		                         .addLine(td.todaylunch(1))
////                                 .setSummaryText("상미고점심")
////                                 ).build();
//
//
//
//// 알림시 사운드, 진동, 불빛을 설정 가능.
//builder.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_LIGHTS);
//
//// 알림 터치시 반응.
////builder.setContentIntent(pendingIntent);
//
//// 알림 터치시 반응 후 알림 삭제 여부.
//builder.setAutoCancel(true);
//
//// 우선순위.
//builder.setPriority(Notification.PRIORITY_HIGH);
//
//// 행동 최대3개 등록 가능.
////builder.addAction(R.mipmap.ic_launcher, "Show", pendingIntent);
////builder.addAction(R.mipmap.ic_launcher, "Hide", pendingIntent);
////builder.addAction(R.mipmap.ic_launcher, "Remove", pendingIntent);
//
//// 고유ID로 알림을 생성.
//NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//nm.notify(3365, builder.build());
