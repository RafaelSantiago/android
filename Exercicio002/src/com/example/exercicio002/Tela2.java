package com.example.exercicio002;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class Tela2 extends Activity {

	public static final String PARAM_1 = "param1";
	public static final String PARAM_2 = "param2";
	
	public static final String RESULTADO_OPERACAO = "resultado operacao";
	
	private int param1;
	private int param2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela2);
		 
		try {
			param1 = Integer.parseInt(getIntent().getStringExtra(PARAM_1));
		} catch (NumberFormatException ex) {
			param1 = 0;
		}
		
		try {
			param2 = Integer.parseInt(getIntent().getStringExtra(PARAM_2));
		} catch (NumberFormatException ex) {
			param2 = 0;
		}
		
		
		((TextView)findViewById(R.id.textViewParam1)).setText("Param 1: "+param1);
		((TextView)findViewById(R.id.textViewParam2)).setText("Param 2: "+param2);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_tela2, menu);
		return true;
	}
	
	public void onSomar(View v) {
		Intent it = new Intent();
		it.putExtra(RESULTADO_OPERACAO, param1 + param2);
		setResult(RESULT_OK, it);
		finish();
	}
	
	public void onSubtrair(View v) {
		Intent it = new Intent();
		it.putExtra(RESULTADO_OPERACAO, param1 - param2);
		setResult(RESULT_OK, it);
		finish();
	}
	
	public void onMulti(View v) {
		Intent it = new Intent();
		it.putExtra(RESULTADO_OPERACAO, param1 * param2);
		setResult(RESULT_OK, it);
		finish();
	}
	
	public void onDividir(View v) {
		Intent it = new Intent();
		it.putExtra(RESULTADO_OPERACAO, (0 == param2) ? 0 : param1 / param2);
		setResult(RESULT_OK, it);
		finish();
	}

}
