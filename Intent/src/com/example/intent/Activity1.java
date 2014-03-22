package com.example.intent;

import java.io.File;
import java.io.IOException;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Activity1 extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity1);
	}

	// Codigos de controle para retorno de chamada de intent
	public static final int CODIGO_REQ = 35;
	public static final int CODIGO_REQ_FOTO = 36;

	public void clickbt(View v) {
		// Captura EditText da tela
		EditText ed = (EditText) findViewById(R.id.inputtxt);
		// Pega texto do edittext da tela
		String txt = ed.getText().toString();
		// Cria uma intent para inicar a segunda activity
		Intent in = new Intent(getApplicationContext(), Activity2.class);
		// Seta variavel na intent
		in.putExtra("texto", txt);
		// Solicita o android para iniciar uma activity
		startActivityForResult(in, CODIGO_REQ);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// Verifica se o resultado de request é o solicitado e result OK
		if (requestCode == CODIGO_REQ && resultCode == RESULT_OK) {
			// Pega a variavel retorno devolvida pela segunda activity
			String retorno = data.getStringExtra("retorno");
			// Gera um Toast e da um show para mostra-lo para o usuário
			Toast.makeText(getApplicationContext(), retorno, Toast.LENGTH_LONG)
					.show();
			// Verifica se o resultado de request é o solicitado e result OK
		}
		if (requestCode == CODIGO_REQ_FOTO && resultCode == RESULT_OK) {
			// Cria uma variavel file para saber o caminho correto do arquivo
			// Envirement é utilizado para poder pegar o caminho do diretório no
			// SDCard
			File file = new File(
					Environment
							.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
					"foto.jpg");
			// pega o Imageview colocado no layout xml
			ImageView img = (ImageView) findViewById(R.id.imageView1);
			// Declara uma variavel do tipo bitmap
			Bitmap btm = null;
			try {
				// Decodifica uma variável pelo caminho do arquivo
				btm = BitmapFactory.decodeFile(file.getCanonicalPath());
			} catch (IOException e) {
				Toast.makeText(getApplicationContext(), e.getMessage(),
						Toast.LENGTH_LONG).show();
				e.printStackTrace();
			}
			// Coloca a imagem carregada do arquivo no componenete capturado da
			// tela.
			img.setImageBitmap(btm);
		}
	}

	public void capturaFoto(View v) {
		// Cria uma Intent para poder invocar a camera para capturar imagem
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		// Cria uma variavel file para saber o caminho correto do arquivo
		// Envirement é utilizado para poder pegar o caminho do diretório no
		// SDCard
		File file = new File(
				Environment
						.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
				"foto.jpg");
		// Coloca o caminho do arquivo como uma parametro para a activity de
		// captura
		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
		// Coloca um parametro para dizer que não deseja que a activity da
		// camera retorne a imagem (apenas grave em arquivo)
		intent.putExtra("return-data", false);
		// Invoca a activity baseada na intent esperando uma resposta com o
		// código CODIGO_REQ_FOTO
		startActivityForResult(intent, CODIGO_REQ_FOTO);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity1, menu);
		return true;
	}

}
