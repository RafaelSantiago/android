package com.example.exercicio001;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button bt = (Button) findViewById(R.id.buttonSalvar);
        
        bt.setText("Salvar");
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void teste(View v) {
    	//((Button)v).setText("Texto alterado");
    	
    	EditText nome = ((EditText)findViewById(R.id.editNome));
    	
    	Toast.makeText(this, getResources().getString(R.string.labelNome) + " " + nome.getText(), Toast.LENGTH_SHORT).show();
    }
    
}
