package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Episodio {
	
	@Id
    @GeneratedValue
    private Long id;
	
	private String nome, numero;
	
	private boolean status;
	
	public Episodio(String numero, String nome) {
		this.setNumero(numero);
		this.setNome(nome);
		this.status = false;
	}
	
	public Episodio() {
		
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getNumero() {
		return numero;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
	
	public boolean getStatus() {
		return status;
	}
	
	public boolean setStatus() {
		return this.status = true;
	}
	
	public String assistido() {
		if (status) {
			return "sim";
		}		
		return "nao";		
	}

}
