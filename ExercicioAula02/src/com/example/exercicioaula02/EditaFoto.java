package com.example.exercicioaula02;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.example.exercicioaula02.R;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;
import android.os.Build;

public class EditaFoto extends ActionBarActivity {

	public static final String EXTRA_FILE_NAME = "extra-file-name";
	
	private static final float PROGRESS_PADRAO = 50.0f;
	
	private String fileName;
	private Bitmap bitmap;
	private Bitmap original;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edita_foto);

		fileName = getIntent().getStringExtra(EXTRA_FILE_NAME);
		File file = new File(Environment.getExternalStorageDirectory(), fileName);
		try {
			original = android.provider.MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.fromFile(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (original == null) {
			Toast.makeText(this, "Não foi possivel abrir a imagem", Toast.LENGTH_LONG).show();
		} else {
			setThumb(original);
		}
		
	}

	public void onAplicar(View v) {
		
		if (original != null) {
			bitmap = original.copy(Bitmap.Config.ARGB_8888, true);
			
			int width = bitmap.getWidth();
			int height = bitmap.getHeight();
						
			SeekBar seekRed = (SeekBar) findViewById(R.id.seekBarRed);
			int redProgress = seekRed.getProgress();
			float multiRed = redProgress / PROGRESS_PADRAO;
			
			SeekBar seekGreen = (SeekBar) findViewById(R.id.seekBarGreen);
			int greenProgress = seekGreen.getProgress();
			float multiGreen = greenProgress / PROGRESS_PADRAO;
			
			SeekBar seekBlue = (SeekBar) findViewById(R.id.seekBarBlue);
			int blueProgress = seekBlue.getProgress();
			float multiBlue = blueProgress / PROGRESS_PADRAO;
						
			for (int x = 0; x < width; ++x) {
				for (int y = 0; y < height; ++y) {
					int pixel = bitmap.getPixel(x, y);
					
					float pixelRed = Color.red(pixel) * multiRed;
					float pixelGreen = Color.green(pixel) * multiGreen;
					float pixelBlue = Color.blue(pixel) * multiBlue;
					
					bitmap.setPixel(x, y, Color.rgb((int)pixelRed, (int)pixelGreen, (int)pixelBlue));
				}
			}
			
			
			setThumb(bitmap);
		}
	}
	
	public void onSalvar(View v) {
		if (bitmap != null) {
			File file = new File(Environment.getExternalStorageDirectory(), fileName);
			boolean salvou = false;
			try {
				FileOutputStream out = new FileOutputStream(file);
				bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
				out.flush();
				out.close();
				salvou = true;
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (!salvou) {
				Toast.makeText(this, "Não foi possivel salvar a imagem", Toast.LENGTH_LONG).show();
			} else {
				setResult(RESULT_OK);
				finish();
			}
		} else {
			setResult(RESULT_CANCELED);
			finish();
		}
	}
	
	private void setThumb(Bitmap bitmap)
	{
		if (bitmap != null) {
			Bitmap thumb = ThumbnailUtils.extractThumbnail(bitmap, 400, 300);
		
			ImageView image = (ImageView) findViewById(R.id.imageView1);
			image.setImageBitmap(thumb);
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edita_foto, menu);
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
			View rootView = inflater.inflate(R.layout.fragment_edita_foto,
					container, false);
			return rootView;
		}
	}

}
