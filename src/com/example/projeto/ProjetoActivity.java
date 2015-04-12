package com.example.projeto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ProjetoActivity extends Activity {

	private EditText usuario, senha;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		usuario = (EditText) findViewById(R.id.usuario);
		senha = (EditText) findViewById(R.id.senha);
	}

	public void entrarOnClick(View v) {
		String nomeUsuario = usuario.getText().toString();
		String senhaUsuario = senha.getText().toString();

		if (nomeUsuario.equals("clesio") && senhaUsuario.equals("123")) {
			// outra activity
			startActivity(new Intent(this, MainActivity.class));
		} else {
			String mensagemErro = getString(R.string.erro_autenticacao);
			Toast toast = Toast
					.makeText(this, mensagemErro, Toast.LENGTH_SHORT);
			toast.show();
		}

	}

}
