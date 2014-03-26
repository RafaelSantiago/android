package com.example.exercicioaula02;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.os.Build;
import android.provider.MediaStore;

public class MainActivity extends ActionBarActivity {
	
	private static final int REQUEST_FOTO = 100;
	private static final int REQUEST_EDITA_FOTO = 101;
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
		
		this.carregaFotos();
		
	}

	private void carregaFotos() {
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
		
		ListView lista = (ListView) findViewById(R.id.listView1);
		lista.setAdapter(new ListaImagensAdapter());
	}
	
	public void onAdicionarFoto(View view) {
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
			if (requestCode == REQUEST_FOTO || requestCode == REQUEST_EDITA_FOTO) {
				carregaFotos();
			}
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
			View view = inflater.inflate(R.layout.linha_foto, arg2, false);
			
			ImageView imageView = (ImageView) view.findViewById(R.id.imageView1);
			Bitmap thumb = null;
			try {
				Item item = fotos.get(arg0);
				Bitmap bitmap = android.provider.MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.fromFile(item.getFile())); 
				thumb = ThumbnailUtils.extractThumbnail(bitmap, 200, 150);
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
	
	public void onFotoClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		Intent intent = new Intent(this, EditaFoto.class);
		
		intent.putExtra(EditaFoto.EXTRA_FILE_NAME, fotos.get(arg2).getFile().getName());
		
		startActivityForResult(intent, REQUEST_EDITA_FOTO);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}
