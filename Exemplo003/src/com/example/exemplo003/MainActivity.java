package com.example.exemplo003;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	class Item {
		String nome;
		boolean selecionado;
		
		public Item(String nome, boolean selecionado) {
			super();
			this.nome = nome;
			this.selecionado = selecionado;
		}
		
		
	}
	
	class AdapterPersonalizado extends BaseAdapter {

		@Override
		public int getCount() {
			return itens.size();
		}

		@Override
		public Object getItem(int arg0) {
			return itens.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			return arg0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			
			View linha = arg1;
			if (linha == null) {
				linha =	inflater.inflate(R.layout.linha, arg2, false);
			}
			
			TextView text = (TextView)linha.findViewById(R.id.textView1);
			ImageView image = (ImageView)linha.findViewById(R.id.imageView1);
			
			Item atual = itens.get(arg0);
			
			text.setText(atual.nome);
			if (atual.selecionado) {
				image.setImageResource(android.R.drawable.btn_star_big_on);
			} else {
				image.setImageResource(android.R.drawable.btn_star_big_off);
			}
			
			return linha;
		}
		
	}
	
	ArrayList<Item> itens = new ArrayList<Item>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        itens.add(new Item("Linha 1", false));
        itens.add(new Item("Linha 2", true));
        itens.add(new Item("Linha 3", false));
        itens.add(new Item("Linha 4", false));
        itens.add(new Item("Linha 5", true));
        itens.add(new Item("Linha 6", false));
        itens.add(new Item("Linha 7", true));
        itens.add(new Item("Linha 8", false));
        itens.add(new Item("Linha 9", false));
        itens.add(new Item("Linha 10", true));
               
        
        setContentView(R.layout.activity_main);
        
        final ListView lista = (ListView) findViewById(R.id.listView1);
        lista.setAdapter(new AdapterPersonalizado());
        
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Item atual = itens.get(arg2);
				atual.selecionado = !atual.selecionado;
				AdapterPersonalizado adapter = (AdapterPersonalizado) lista.getAdapter();
				adapter.notifyDataSetChanged();
			}
        	
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void onConfig(MenuItem item) {
    	PopupMenu menu = new PopupMenu(this, findViewById(R.id.listView1));
    	menu.getMenuInflater().inflate(R.menu.submenu, menu.getMenu());
    	menu.show();
    }
    
    public void onConfig1(MenuItem item) {
    	Toast.makeText(getApplicationContext(), "Configuração 1", Toast.LENGTH_LONG).show();
    }
    
}
