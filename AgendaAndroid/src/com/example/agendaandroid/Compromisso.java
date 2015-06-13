package com.example.agendaandroid;

public class Compromisso implements Comparable<Compromisso> {
	private String data,hora,desc,tipo,ocorre;
	public Compromisso(String data, String hora,String desc, String tipo, String ocorre){
		this.data = data;
		this.hora = hora;
		this.desc = desc;
		this.tipo = tipo;
		this.ocorre = ocorre;
	}
	public String getData() {
		return data;
	}
	public String getHora() {
		return hora;
	}
	public String getDesc() {
		return desc;
	}
	public String getTipo() {
		return tipo;
	}
	public String getOcorre() {
		return ocorre;
	}
	@Override
	public int compareTo(Compromisso compromisso) {
		return this.data.compareToIgnoreCase(compromisso.getData());
	}
}
