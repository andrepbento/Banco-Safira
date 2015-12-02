package model;

import java.util.Date;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Cliente extends Pessoa {
	
	private final DoubleProperty taxaFidelidade;
	
	public Cliente(){
		this(null,null,null,null,0.0);
	}
	
	public Cliente(String nome,String cpf,String email,Date dataNascimento,Double taxaFidelidade){
		super(nome,cpf,email,dataNascimento);
		this.taxaFidelidade = new SimpleDoubleProperty(taxaFidelidade);
	}
	

	public Double getTaxaFidelidade() {
		return taxaFidelidade.get();
	}

	public void setTaxaFidelidade(Double taxaFidelidade) {
		this.taxaFidelidade.set(taxaFidelidade);;
	}
	
	public DoubleProperty taxaFidelidadeProperty(){
		return taxaFidelidade;
	}
	
	public String toString(){
		String text = "Nome: "+this.getNome() +" CPF: "+this.getCpf();
		return text;
	}
	
	
}
