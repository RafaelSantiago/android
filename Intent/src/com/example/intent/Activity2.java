package com.example.intent;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class Activity2 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity2);
		// Capturar variavel texto da intent recebida da invocação da activity
		String txt = getIntent().getStringExtra("texto");
		//Capturar Textview da tela 
		TextView txtview = (TextView)findViewById(R.id.textView1);
		//Alterar Texto da textview com o texto recebido da outra activity
		txtview.setText(txt);
		
	}
	public void clickbt2(View v){
		// Criar uma nova intent para invocar uma nova activity
		Intent in = new Intent(getApplicationContext(), Activity1.class);
    	startActivity(in);
	}
	public void voltar(View v){
//		Declara um intent para colocar valores de retorno da activity
		Intent intent = new Intent();
//		Coloca um valor com o nome de retorno para que a outra activity possa pegar quando retornar
		intent.putExtra("retorno", "valor retornado");
//		Seta o resultado com o código de retorno RESULT_OK
		setResult(RESULT_OK, intent);
		
		//Encerrar 
		finish();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity2, menu);
		return true;
	}

}
