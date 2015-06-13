package com.example.agendaandroid;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

public class lista extends LinearLayout{
	TextView tVData, tVHora, tVTipo, tVDesc, tVOcorrencia;
	public lista(Context context, AttributeSet attrs,Compromisso compromisso){
		super(context, attrs);
		if (compromisso == null) {
			
			return;
		} else {
			tVData.setText(compromisso.getData());
			tVHora.setText(compromisso.getHora());
			tVTipo.setText(compromisso.getTipo());
			tVDesc.setText(compromisso.getDesc());
			tVOcorrencia.setText(compromisso.getOcorre());
		}
	}

}
