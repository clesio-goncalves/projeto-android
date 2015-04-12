package com.example.projeto;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NovoProjetoActivity extends Activity {

	private DatabaseHelper helper;
	private EditText nome, descricao, valor, validade_anos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.adicionar);

		// recupera as views
		nome = (EditText) findViewById(R.id.nome);
		descricao = (EditText) findViewById(R.id.descricao);
		valor = (EditText) findViewById(R.id.valor);
		validade_anos = (EditText) findViewById(R.id.validade_anos);

		// prepara o acesso ao bando de dados
		helper = new DatabaseHelper(this);
	}

	public void salvarProjeto(View view) {
		SQLiteDatabase db = helper.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put("nome", nome.getText().toString());
		values.put("descricao", descricao.getText().toString());
		values.put("valor", valor.getText().toString());
		values.put("validade_anos", validade_anos.getText().toString());

		long resultado = db.insert("tbProjeto", null, values);

		if (resultado != -1) {
			Toast.makeText(this, getString(R.string.registro_salvo),
					Toast.LENGTH_SHORT).show();
			startActivity(new Intent(this, ListaProjetoActivity.class));
		} else {
			Toast.makeText(this, getString(R.string.erro_salvar),
					Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	protected void onDestroy() {
		helper.close();
		super.onDestroy();
	}

}
