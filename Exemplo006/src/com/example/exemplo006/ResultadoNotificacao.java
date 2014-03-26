package com.example.exemplo006;

import android.os.Bundle;
import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.view.Menu;

public class ResultadoNotificacao extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_resultado_notificacao);
		
		NotificationManager nmg = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        nmg.cancel(1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_resultado_notificacao, menu);
		return true;
	}

}
