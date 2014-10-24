package mynotification.com.mynotification2;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

import java.lang.annotation.Annotation;


public class MyActivity extends Activity {

    private Button button1;
    private Button button2;
    private Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        Intent intent = getIntent();
        if (intent!=null && intent.getStringExtra("test")!=null){
            button1.setText(intent.getStringExtra("test"));
        }
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNormalNotification2();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNormalNotification3();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCustomViewNotification();
            }
        });
    }
    public class MyRemoteView extends RemoteViews{

        public MyRemoteView(String packageName, int layoutId) {
            super(packageName, layoutId);
        }
    }
    private void setCustomViewNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(MyActivity.this);
        builder.setSmallIcon(android.R.drawable.ic_notification_clear_all)
                .setContentTitle("测试title1")
                .setContentText("测试内容")
                .setNumber(1)
                .setAutoCancel(true);

        Intent intent = new Intent();
        intent.setAction("com.example.notification2");
        intent.putExtra("test", "testbutton");
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        PendingIntent pendingIntent = PendingIntent.getActivities(MyActivity.this, 1, new Intent[]{intent}, PendingIntent.FLAG_UPDATE_CURRENT);

        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(this);
//        taskStackBuilder.addParentStack(new ComponentName("mynotification.com.mynotification2", "MyActivity2"));
        taskStackBuilder.addNextIntent(intent);
        PendingIntent pendingIntent = taskStackBuilder.getPendingIntent(1, PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setContentIntent(pendingIntent);

        Notification notification = builder.build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);
    }

    private void setBigNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(MyActivity.this);
        builder.setSmallIcon(android.R.drawable.ic_notification_clear_all)
                .setContentTitle("测试title1")
                .setContentText("测试内容")
                .setAutoCancel(true);
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        inboxStyle.setBigContentTitle("bigTitle");
        for (int i=0; i<5; i++) {
            inboxStyle.addLine("position_"+i);
        }
        builder.setStyle(inboxStyle);

        Intent intent = new Intent();
        intent.setAction("com.example.notification2");
        intent.putExtra("test", "testbutton");
//                PendingIntent pendingIntent = PendingIntent.getActivities(MyActivity.this, 1, new Intent[]{intent}, PendingIntent.FLAG_UPDATE_CURRENT);

        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(MyActivity.this);
        taskStackBuilder.addNextIntent(intent);
//        taskStackBuilder.addParentStack(MyActivity.class);
        PendingIntent pendingIntent = taskStackBuilder.getPendingIntent(1, PendingIntent.FLAG_UPDATE_CURRENT, null);

        builder.setContentIntent(pendingIntent);

        Notification notification = builder.build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);
    }

    private void setNormalNotification2() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(MyActivity.this);
        builder.setSmallIcon(android.R.drawable.ic_notification_clear_all)
                .setContentTitle("测试title1")
                .setContentText("测试内容")
                .setNumber(1)
                .setAutoCancel(true);

        Intent intent = new Intent();
        intent.setAction("com.example.notification2");
        intent.putExtra("test", "testbutton");
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        PendingIntent pendingIntent = PendingIntent.getActivities(MyActivity.this, 1, new Intent[]{intent}, PendingIntent.FLAG_UPDATE_CURRENT);

        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(this);
        taskStackBuilder.addParentStack(new ComponentName("mynotification.com.mynotification2", "MyActivity2"));
        taskStackBuilder.addNextIntent(intent);
        PendingIntent pendingIntent = taskStackBuilder.getPendingIntent(1, PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setContentIntent(pendingIntent);

        Notification notification = builder.build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);

    }

    private void setNormalNotification3() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(MyActivity.this);
        builder.setSmallIcon(android.R.drawable.ic_notification_clear_all)
                .setContentTitle("测试title1")
                .setContentText("测试内容")
                .setNumber(1)
                .setAutoCancel(true);
        builder.setProgress(100, 0, false);

        Intent intent = new Intent();
        intent.setAction("com.example.notification3");
        intent.putExtra("test", "testbutton");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(MyActivity.this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);

//        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(this);
//        taskStackBuilder.addParentStack(MyActivity2.class);
//        taskStackBuilder.addNextIntent(intent);
//        PendingIntent pendingIntent = taskStackBuilder.getPendingIntent(1, PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setContentIntent(pendingIntent);

        Notification notification = builder.build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
