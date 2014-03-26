package com.example.exemplo005;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;


public class MeuContentProvider extends ContentProvider {

	@Override
	public int delete(Uri arg0, String arg1, String[] arg2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getType(Uri arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri arg0, ContentValues arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Cursor query(Uri arg0, String[] arg1, String arg2, String[] arg3,
			String arg4) {
		
		String[] dados = new String[1];
		dados[0] = "nome";
		
		MatrixCursor cursor = new MatrixCursor(dados, 1);
		cursor.addRow(new Object[] {"Teste 1"});
		cursor.addRow(new Object[] {"Teste 2"});
		cursor.addRow(new Object[] {"Teste 3"});
		cursor.addRow(new Object[] {"Teste 4"});
		cursor.addRow(new Object[] {"Teste 5"});
		
		return cursor;
	}

	@Override
	public int update(Uri arg0, ContentValues arg1, String arg2, String[] arg3) {
		// TODO Auto-generated method stub
		return 0;
	}

}
