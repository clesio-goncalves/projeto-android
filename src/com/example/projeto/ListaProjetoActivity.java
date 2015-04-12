package com.example.projeto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SimpleAdapter;

public class ListaProjetoActivity extends ListActivity {

	private DatabaseHelper helper;
	private List<Map<String, Object>> projetos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		helper = new DatabaseHelper(this);

		String[] de = { "nome", "descricao", "valor", "validade_anos" };
		int[] para = { R.id.nome, R.id.descricao, R.id.valor,
				R.id.validade_anos };

		SimpleAdapter adapter = new SimpleAdapter(this, listarProjetos(),
				R.layout.listar, de, para);

		setListAdapter(adapter);
	}

	private List<Map<String, Object>> listarProjetos() {

		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db
				.rawQuery(
						"select _id, nome, descricao, valor, validade_anos from tbProjeto",
						null);
		cursor.moveToFirst();

		projetos = new ArrayList<Map<String, Object>>();

		for (int i = 0; i < cursor.getCount(); i++) {

			Map<String, Object> item = new HashMap<String, Object>();

			String id = cursor.getString(0);
			String nome = cursor.getString(1);
			String descricao = cursor.getString(2);
			double valor = cursor.getDouble(3);
			int validade_anos = cursor.getInt(4);

			item.put("id", id);
			item.put("nome", nome);
			item.put("descricao", descricao);
			item.put("valor", valor);
			item.put("validade_anos", validade_anos);

			projetos.add(item);
			cursor.moveToNext();
		}

		cursor.close();

		return projetos;
	}

	// Menu
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.lista_projeto_menu, menu);
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		startActivity(new Intent(this, NovoProjetoActivity.class));
		return true;
	}

	@Override
	protected void onDestroy() {
		helper.close();
		super.onDestroy();
	}
}
