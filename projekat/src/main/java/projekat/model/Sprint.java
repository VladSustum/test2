package projekat.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Sprint {
	@Id 
	@GeneratedValue
	@Column 
	private Long id;
	@Column 
	private String ime;
	@Column
	private String bodovi;
	
	@OneToMany(mappedBy="stanje", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Zadatak> zadaci = new ArrayList<>();
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getBodovi() {
		return bodovi;
	}
	public void setBodovi(String bodovi) {
		this.bodovi = bodovi;
	}
	public List<Zadatak> getZadaci() {
		return zadaci;
	}
	public void setZadaci(List<Zadatak> zadaci) {
		this.zadaci = zadaci;
	}
	public void addZadatak(Zadatak zadatak) {
		if(zadatak.getSprint() != this) {
			zadatak.setSprint(this);
		}
		zadaci.add(zadatak);
	}

}
