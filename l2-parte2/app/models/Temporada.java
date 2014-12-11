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
public class Temporada {
	
	@Id
    @GeneratedValue
    private Long id;
	
	@Column
	private String nome;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn
	private List<Episodio> episodios;
	
	public Temporada(String nome) {
		this.setNome(nome);
		episodios = new ArrayList<Episodio>();
	}
	
	public Temporada() {
		
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Episodio> getEpisodios() {
		return episodios;
	}
	
	public void addEpisodio(Episodio e) {
		episodios.add(e);
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
	
	public String status() {
		String status = "nao assistida";
		
		int epsAssistidos = 0;
		
		for (int i = 0; i < episodios.size(); i++) {
			if (episodios.get(i).assistido().equals("sim")) {
				epsAssistidos += 1;
			}			
		}
		
		if (epsAssistidos == episodios.size()) {
			status = "assistida";
		}else if (epsAssistidos > 0 && epsAssistidos < episodios.size()) {
			status = "assistindo";
		}
		
		return status;
	}

}
