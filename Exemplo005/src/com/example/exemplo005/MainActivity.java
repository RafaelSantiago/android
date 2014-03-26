package com.example.exemplo005;

import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;
import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ContentResolver cr = getContentResolver();
        
        Cursor c = cr.query(Contacts.CONTENT_URI, new String[] {Contacts._ID, Contacts.DISPLAY_NAME}, null, null, null);
        
        StringBuilder sb = new StringBuilder();
        while (c.moveToNext()) {
        	String id = c.getString(0);
        	String nome = c.getString(1);
        	
        	Cursor cTelefones = cr.query(Phone.CONTENT_URI, new String[] {Phone.NUMBER, Phone.TYPE}, Phone.CONTACT_ID + "= ?", new String[] {id}, null);
        	
        	sb.append("id:");
        	sb.append(id);
        	sb.append(" ");
        	sb.append("nome:");
        	sb.append(nome);
        	sb.append(" ");
        	
        	while (cTelefones.moveToNext()) {
        		String numero = cTelefones.getString(0);
        		String tipo = cTelefones.getString(1);
        		
        		sb.append("tipo:");
            	sb.append(tipo);
            	sb.append(" ");
            	sb.append("numero:");
            	sb.append(numero);
            	
        	}
        	
        	cTelefones.close();
        	
        	sb.append("\n");
        	
        }
        
        c.close();
        
        Cursor dados = cr.query(Uri.parse("content://com.example.exemplo005"), null, null, null, null);
        
        while (dados.moveToNext()) {
        	String dado = dados.getString(0);
        	
        	Toast.makeText(this, dado, Toast.LENGTH_LONG).show();
        	
        }
        
        ((TextView)findViewById(R.id.textView1)).setText(sb);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
}
