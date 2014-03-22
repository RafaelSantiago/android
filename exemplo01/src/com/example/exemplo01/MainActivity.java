package com.example.exemplo01;

import java.io.File;

import com.example.exemplo01.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;


public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		ListView lista = (ListView) findViewById(R.id.listView1);
		
		//File directory = Environment.getExternalStorageDirectory();
		File directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
		String arquivos[] = directory.list();
		
		lista.setAdapter((ListAdapter) new ArrayAdapter<String>(
				getApplicationContext(),
				android.R.layout.simple_list_item_1,
				android.R.id.text1,
				arquivos
		));
		
		
	}
	
}