package com.example.exercicio002;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	private static final int REQUEST_EXECUTAR = 1;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void onExecutar(View v) {
    	Intent it  = new Intent(this, Tela2.class);
    	it.putExtra(Tela2.PARAM_1, ((EditText)findViewById(R.id.editText1)).getText().toString());
    	it.putExtra(Tela2.PARAM_2, ((EditText)findViewById(R.id.editText2)).getText().toString());
    	startActivityForResult(it, REQUEST_EXECUTAR);
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	
    	super.onActivityResult(requestCode, resultCode, data);
    	
    	if (resultCode != RESULT_OK) {
    		return;
    	}
    	
    	if (REQUEST_EXECUTAR == requestCode) {
    		((TextView)findViewById(R.id.textViewResultado)).setText("Resultado: " + data.getIntExtra(Tela2.RESULTADO_OPERACAO, 0));
    	}
    }
    
}
