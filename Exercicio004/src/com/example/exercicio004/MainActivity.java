package com.example.exercicio004;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.Menu;
import android.widget.EditText;

public class MainActivity extends Activity {

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
    
    @Override
    protected void onPause() {
    	super.onPause();
    	
    	SharedPreferences preferences = getSharedPreferences("Tela Cadastro", Context.MODE_PRIVATE);
    	SharedPreferences.Editor edit = preferences.edit();
    	
    	edit.putString("nome", ((EditText)findViewById(R.id.editTextNome)).getText().toString());
    	edit.putString("sobrenome", ((EditText)findViewById(R.id.editTextSobrenome)).getText().toString());
    	edit.putString("endereco", ((EditText)findViewById(R.id.editTextEndereco)).getText().toString());
    	
    	edit.commit();
    	
    	/*try {
			FileOutputStream fout = openFileOutput("Preferencias", Context.MODE_PRIVATE);
			
			String pref = ((EditText)findViewById(R.id.editTextNome)).getText().toString() + ";" +
					((EditText)findViewById(R.id.editTextSobrenome)).getText().toString() + ";" +
					((EditText)findViewById(R.id.editTextEndereco)).getText().toString();
			
			fout.write(pref.getBytes());
			
			fout.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
    	
    }
    
    @Override
    protected void onResume() {
    	super.onResume();
    	
    	SharedPreferences preferences = getSharedPreferences("Tela Cadastro", Context.MODE_PRIVATE);
    	
    	((EditText)findViewById(R.id.editTextNome)).setText(preferences.getString("nome", ""));
    	((EditText)findViewById(R.id.editTextSobrenome)).setText(preferences.getString("sobrenome", ""));
    	((EditText)findViewById(R.id.editTextEndereco)).setText(preferences.getString("endereco", ""));
    }
    
}
