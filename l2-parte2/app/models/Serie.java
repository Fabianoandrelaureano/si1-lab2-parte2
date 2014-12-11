package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Serie {
	
	@Id
    @GeneratedValue
    private Long id;
	
	@Column
	private String nome;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn
	private List<Temporada> temporadas;
	
	private boolean status;
	
	public Serie(String nome) {
		this.setNome(nome);
		this.temporadas = new ArrayList<Temporada>();
		this.status = false;
	}
	
	public Serie() {
		
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Temporada> getTemporadas() {
		return this.temporadas;
	}	
	
	public void addTemporada(Temporada t) {
		this.temporadas.add(t);
	}
	
	public Temporada getUltimaTemporada() {
		return temporadas.get(temporadas.size()-1);
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
	
	public String isAcompanhada() {
		if (status) {
			return "sim";
		}		
		return "nao";		
	}

}
