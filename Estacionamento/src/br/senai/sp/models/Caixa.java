package br.senai.sp.models;

public class Caixa {

	private int idCaixa;
	private int idUsuarioAbertura;
	private String dtAbertura;
	private double valorAbertura;
	private String dtFechamento;
	private double valorFechamento;

	public int getIdUsuarioAbertura() {
		return idUsuarioAbertura;
	}

	public void setIdUsuarioAbertura(int idUsuarioAbertura) {
		this.idUsuarioAbertura = idUsuarioAbertura;
	}

	public int getIdCaixa() {
		return idCaixa;
	}

	public void setIdCaixa(int idCaixa) {
		this.idCaixa = idCaixa;
	}

	public String getDtAbertura() {
		return dtAbertura;
	}

	public void setDtAbertura(String dtAbertura) {
		this.dtAbertura = dtAbertura;
	}

	public double getValorAbertura() {
		return valorAbertura;
	}

	public void setValorAbertura(double d) {
		this.valorAbertura = d;
	}

	public String getDtFechamento() {
		return dtFechamento;
	}

	public void setDtFechamento(String dtFechamento) {
		this.dtFechamento = dtFechamento;
	}

	public double getValorFechamento() {
		return valorFechamento;
	}

	public void setValorFechamento(double valorFechamento) {
		this.valorFechamento = valorFechamento;
	}

}
