package com.example.agendaandroid;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
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
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends Activity {
	Button bInserirCompromissos,bListaCompromisso,bRemoverCompromisso,
	bCadastra,btnVoltar,btnAvancar,bTelaPrincipal, bInforma,bDev,bTP;
	EditText edtDesc;
	DatePicker dPData;
	TimePicker tPHora;
	TextView tVData, tVHora, tVTipo, tVDesc, tVOcorrencia;
	Spinner	spCompromisso,spOcorre;
	ArrayAdapter adp,ado;
	String dia, mes,dataA;
	ArrayList<Compromisso> compromisso = new ArrayList<Compromisso>();
	ArrayList<Compromisso> compromissoI = new ArrayList<Compromisso>();
	private static final String[] tipocomp = new String[]{"Aniversário","Reunião",
		"Festa", "Tarefa", "Evento","Outro"};
	private static final String[] ocorre = new String[]{"Uma vez",
		"semanalmente", "mensalmente" ,"anualmente"};
	int pos, v=0;
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
		bListaCompromisso = (Button) findViewById(R.id.bListaCompromisso);	
		bInforma = (Button) findViewById(R.id.bInforma);
		bDev = (Button) findViewById(R.id.bDev);
		bInserirCompromissos.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				CarregarTelaInserir();
			}
		});
		bListaCompromisso.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				CarregarTelaListar();
			}
		});
		bInforma.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if(compromisso.isEmpty()){
					ExibirMensagem("Nenhum registro cadastrado");
				}else{
					String i = "Data:"+compromisso.get(0).getData()+"\nHora:"+
							compromisso.get(0).getHora()+"\nTipo:"+compromisso.get(0).getTipo()+"\nDesc.:"+
							compromisso.get(0).getDesc()+"\nOcorre:"+compromisso.get(0).getOcorre();
					ExibirMensagem(i);
				}
			}
		});
		bDev.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				setContentView(R.layout.deb);
				bTP = (Button) findViewById(R.id.bTP);
				bTP.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						CarregarTelaPrincipal();
					}
				});
			}
		});
	}
	public void CarregarTelaInserir(){
		setContentView(R.layout.inserir_compromisso);

		dPData = (DatePicker) findViewById(R.id.dPData);
		tPHora = (TimePicker) findViewById(R.id.tPHora);
		edtDesc = (EditText) findViewById(R.id.edTDesc);

		if(dPData.getDayOfMonth()<10 && dPData.getMonth()<10){
		 dataA = ""+String.valueOf(String.valueOf(dPData.getYear())+"/0"+
				dPData.getMonth()+"/0"+dPData.getDayOfMonth());
		}else if(dPData.getDayOfMonth()<10){
			 dataA = ""+String.valueOf(String.valueOf(dPData.getYear())+"/"+
					dPData.getMonth()+"/0"+dPData.getDayOfMonth());
		}else if(dPData.getMonth()<10){
			 dataA = ""+String.valueOf(String.valueOf(dPData.getYear())+"/0"+
					dPData.getMonth()+"/"+dPData.getDayOfMonth());
		}else {
			 dataA = ""+String.valueOf(String.valueOf(dPData.getYear())+"/"+
					dPData.getMonth()+"/"+dPData.getDayOfMonth());
		}
		spCompromisso = (Spinner) findViewById(R.id.spTipo);
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
				if(dPData.getDayOfMonth()<10){
					dia = "0"+String.valueOf(dPData.getDayOfMonth());
				}else{
					dia = ""+String.valueOf(dPData.getDayOfMonth());
				}
				if((dPData.getMonth()+1)<10){
					mes = "0"+String.valueOf((dPData.getMonth()+1));
				}else{
					mes = ""+String.valueOf((dPData.getMonth()+1));
				}
					String data = ""+String.valueOf(String.valueOf(dPData.getYear())+"/"+
							mes+"/"+dia);
					String hora = String.valueOf(tPHora.getCurrentHour())+
							":"+String.valueOf(tPHora.getCurrentMinute());
					
					v = data.compareTo(dataA);
					if(v>=0){
					compromisso.add(new Compromisso(data,
							hora, edtDesc.getText().toString(),
							spCompromisso.getSelectedItem().toString(),
							spOcorre.getSelectedItem().toString())
							);
					}else{
						compromissoI.add(new Compromisso(data,
								hora, edtDesc.getText().toString(),
								spCompromisso.getSelectedItem().toString(),
								spOcorre.getSelectedItem().toString())
								);
					}
					Collections.sort(compromisso);
					ExibirMensagem("Compromisso cadastrado com sucesso");
					CarregarTelaPrincipal();
			}
		});
	}
	public void CarregarTelaListar(){

		if (compromisso.isEmpty()) {
			ExibirMensagem("Nenhum registro cadastrado");
			CarregarTelaPrincipal();
			return;
		} else {
			setContentView(R.layout.listar_compromisso);
			btnVoltar = (Button) findViewById(R.id.bVoltar);
			btnAvancar = (Button) findViewById(R.id.bAvancar);
			bTelaPrincipal = (Button) findViewById(R.id.bTelaPrincipal);
			bRemoverCompromisso = (Button) findViewById(R.id.bRemoverCompromisso);

			tVData = (TextView) findViewById(R.id.tVData);
			tVHora = (TextView) findViewById(R.id.tVHora);
			tVTipo = (TextView) findViewById(R.id.tVTipo);
			tVDesc = (TextView) findViewById(R.id.tVDesc);
			tVOcorrencia = (TextView) findViewById(R.id.tVOcorrencia);

			pos = 0;

			lista(pos);

			btnVoltar.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if (pos == 0)
						return;
					pos--;
					lista(pos);
				}
			});

			btnAvancar.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if (pos == compromisso.size() - 1)
						return;
					pos++;
					lista(pos);
				}
			});

			bTelaPrincipal.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					CarregarTelaPrincipal();
				}
			});

			bRemoverCompromisso.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View arg0) {
					AlertDialog.Builder dialogo = new AlertDialog.Builder(
							MainActivity.this);
					dialogo.setTitle("Aviso");
					dialogo.setMessage("Você tem certesa que quer excluir esse compromisso?");
					dialogo.setNeutralButton("Não", null);
					dialogo.setPositiveButton("Sim", new OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							compromisso.remove(pos);
						}
					} );
					dialogo.show();

				}

			});
		}
	}
	public void lista(int pos){
		tVData.setText(compromisso.get(pos).getData());
		tVHora.setText(compromisso.get(pos).getHora());
		tVTipo.setText(compromisso.get(pos).getTipo());
		tVDesc.setText(compromisso.get(pos).getDesc());
		tVOcorrencia.setText(compromisso.get(pos).getOcorre());
	}
	public void ExibirMensagem(String mensagem) {
		AlertDialog.Builder dialogo = new AlertDialog.Builder(
				MainActivity.this);
		dialogo.setTitle("Aviso");
		dialogo.setMessage(mensagem);
		dialogo.setNeutralButton("OK", null);
		dialogo.show();
	}
}
