package com.example.exemplo002;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {
	
	private static final int REQUEST_FOTO = 1;

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
    
    File arquivo= new File(Environment.getExternalStorageDirectory(), "foto.jpg");
    
    public void onFoto(View v) {
    	Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    	it.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(arquivo));
    	startActivityForResult(it, REQUEST_FOTO);
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	
    	super.onActivityResult(requestCode, resultCode, data);
    	
    	if (resultCode != RESULT_OK) {
    		return;
    	}
    	
    	if (REQUEST_FOTO == requestCode) {
    		try {
				Bitmap img = ThumbnailUtils.extractThumbnail(android.provider.MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.fromFile(arquivo)),800,600);
				
				Bitmap copia = img.copy(Config.ARGB_8888, true);
				
				for (int i = 0; i < copia.getWidth(); i++) {
					for (int j = 0; j < copia.getHeight(); ++j) {
						/*int cor = copia.getPixel(i, j);
						int vermelho = Color.red(cor);
						if (vermelho > 200) {
							copia.setPixel(i, j, Color.MAGENTA);
						}*/
						if (i < 10 || j < 10 || i >= copia.getWidth() - 10 || j >= copia.getHeight() - 10) {
							copia.setPixel(i, j, Color.BLACK);
						}
					}
				}
				
				ImageView imageView = (ImageView)findViewById(R.id.imageView1);
				imageView.setImageBitmap(copia);
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
    
}
