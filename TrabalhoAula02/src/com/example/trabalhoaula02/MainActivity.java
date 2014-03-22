package com.example.trabalhoaula02;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;



public class MainActivity extends Activity {

	private static final int REQUEST_FOTO = 100;
	private static final int REQUEST_TROCA_CORES = 101;
	
	private List<Item> fotos;
	
	private class Item {
		private File file;

		public Item(File file) {
			super();
			this.file = file;
		}

		public File getFile() {
			return file;
		}
		
		
	}
	
	private class ListaImagensAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			if (fotos != null) {
				return fotos.size();
			}
			return 0;
		}

		@Override
		public Object getItem(int arg0) {
			return fotos.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			return arg0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View view = inflater.inflate(R.layout.linha_lista_imagens, arg2, false);
			
			ImageView imageView = (ImageView) view.findViewById(R.id.imageView1);
			Bitmap thumb = null;
			try {
				Item item = fotos.get(arg0);
				Bitmap bitmap = android.provider.MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.fromFile(item.getFile())); 
				thumb = ThumbnailUtils.extractThumbnail(bitmap, 600, 450);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (thumb != null) {
				imageView.setImageBitmap(thumb);
			}
			
			
			return view;
		}
		
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ListView lista = (ListView) findViewById(R.id.listView1);
		lista.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				onFotoClick(arg0, arg1, arg2, arg3);
			}
		});
		
		this.loadImagens();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void onAdicionarFoto(MenuItem menu) {
		File file = new File(Environment.getExternalStorageDirectory(), String.valueOf(System.currentTimeMillis()) + ".jpg");
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
		intent.putExtra("return-data", false);
		this.startActivityForResult(intent, REQUEST_FOTO);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
				
		if (resultCode == RESULT_OK) {
			if (requestCode == REQUEST_FOTO || requestCode == REQUEST_TROCA_CORES) {
				loadImagens();
			}
		}
	}
	
	private void loadImagens() {
		if (null == fotos) {
			fotos = new ArrayList<MainActivity.Item>();
		} else {
			fotos.clear();
		}
		
		String path = Environment.getExternalStorageDirectory().toString();
		
		File f = new File(path);        
		File files[] = f.listFiles();
		
		if (files != null) {
			for (File file : files) {
				String name = file.getName();
				if (name.endsWith(".jpg")) {
					fotos.add(new Item(file));
				}
			}
		}
		
		//System.out.println(fotos.size());
		
		//ListView lista = (ListView) findViewById(R.id.listView1);
		//lista.invalidate();
		ListView lista = (ListView) findViewById(R.id.listView1);
		lista.setAdapter(new ListaImagensAdapter());
	}
	
	public void onFotoClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		Intent intent = new Intent(this, TrocaCores.class);
		
		intent.putExtra(TrocaCores.EXTRA_FILE_NAME, fotos.get(arg2).getFile().getName());
		
		startActivityForResult(intent, REQUEST_TROCA_CORES);
	}

}
