package com.example.exemplo006;

import android.os.Bundle;
import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("Title 1");
        builder.setContentText("Text 1");
        builder.setSmallIcon(R.drawable.ic_launcher);
        
        Intent it = new Intent(this, ResultadoNotificacao.class);
        PendingIntent pint = PendingIntent.getActivity(this, 10, it, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pint);
        
        NotificationManager nmg = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        nmg.notify(1, builder.build());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
}
