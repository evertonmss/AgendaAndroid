package com.example.agendaandroid;

import java.util.ArrayList;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TimePicker;

public class MainActivity extends Activity {
	Button bInserirCompromissos,bRemoverCompromisso,bCadastra;
	EditText edtDesc;
	DatePicker dPData;
	TimePicker tPHora;
	Spinner	spCompromisso,spOcorre;
	ArrayAdapter adp,ado;
	ArrayList<Compromisso> compromisso = new ArrayList<Compromisso>();
	private static final String[] tipocomp = new String[]{"Aniversário","Reunião",
	"Festa", "Tarefa", "Evento","Outro"};
	private static final String[] ocorre = new String[]{"Uma vez",
		"semanalmente", "mensalmente" ,"anualmente"};
	int pos;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		CarregarTelaPrincipal();
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
	public void CarregarTelaPrincipal() {
		setContentView(R.layout.activity_main);
		
		bInserirCompromissos = (Button) findViewById(R.id.bInserirCompromissos);
		bRemoverCompromisso = (Button) findViewById(R.id.bRemoverCompromisso);
		bInserirCompromissos.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				CarregarTelaInserir();
			}
		});
	}
	public void CarregarTelaInserir(){
		setContentView(R.layout.inserir_compromisso);
		
		dPData = (DatePicker) findViewById(R.id.dPData);
		tPHora = (TimePicker) findViewById(R.id.tPHora);
		edtDesc = (EditText) findViewById(R.id.edTDesc);
		
		spCompromisso = (Spinner) findViewById(R.id.spCompromisso);
		adp = new ArrayAdapter<String>(this, 
		  android.R.layout.simple_spinner_item, tipocomp);
		adp.setDropDownViewResource(android.R.layout.simple_spinner_item);
		spCompromisso.setAdapter(adp);
		
		bCadastra = (Button) findViewById(R.id.bCadastra);
		
		spOcorre = (Spinner) findViewById(R.id.spOcorre);
		ado = new ArrayAdapter<String>(this, 
				  android.R.layout.simple_spinner_item, ocorre);
		ado.setDropDownViewResource(android.R.layout.simple_spinner_item);
		spOcorre.setAdapter(ado);
		
		bCadastra.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				String data = ""+String.valueOf(dPData.getDayOfMonth())+"/"+
				String.valueOf(dPData.getMonth()+1)+"/"+String.valueOf(dPData.getYear());
				String hora = String.valueOf(tPHora.getCurrentHour())+
				":"+String.valueOf(tPHora.getCurrentMinute());
				compromisso.add(new Compromisso(data,
				hora, edtDesc.getText().toString(),
				spCompromisso.getSelectedItem().toString(),
				spOcorre.getSelectedItem().toString())
				);
				 AlertDialog.Builder dialogo = new AlertDialog.Builder(
							MainActivity.this);
					dialogo.setMessage("Data selecionada :"+data
							);
					dialogo.setNeutralButton("Ok", null);
					dialogo.setTitle("Data");
					dialogo.show();
					ExibirMensagem("Compromisso cadastrado com sucesso");
				CarregarTelaPrincipal();
			}
		});
	}
	/*
	 * AlertDialog.Builder dialogo = new AlertDialog.Builder(
						MainActivity.this);
				dialogo.setMessage("Data selecionada :"
						+String.valueOf(dPData.getDayOfMonth())+"/"
						+String.valueOf(dPData.getMonth()+1)+"/"
						+String.valueOf(dPData.getYear())
						);
				dialogo.setNeutralButton("Ok", null);
				dialogo.setTitle("Data");
				dialogo.show();
				ExibirMensagem("Compromisso cadastrado com sucesso");
	 */
	public void ExibirMensagem(String mensagem) {
		AlertDialog.Builder dialogo = new AlertDialog.Builder(
				MainActivity.this);
		dialogo.setTitle("Aviso");
		dialogo.setMessage(mensagem);
		dialogo.setNeutralButton("OK", null);
		dialogo.show();
	}
}
