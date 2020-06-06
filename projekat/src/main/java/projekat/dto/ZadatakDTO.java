package projekat.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class ZadatakDTO {
	
	private Long id;
	@NotBlank
	@Size(max= 40)
	private String ime;
	
	@Min(0)
	@Max(20)
	private Integer bodovi;
	
	private String zaduzeni;
	
	private Long sprintId;
	
	private String sprintIme;
	
	private Long stanjeId;
	
	private String stanjeIme;
	
	public boolean isFinalnoStanje() {
		return finalnoStanje;
	}

	public void setFinalnoStanje(boolean finalnoStanje) {
		this.finalnoStanje = finalnoStanje;
	}

	private boolean finalnoStanje;

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

	public Integer getBodovi() {
		return bodovi;
	}

	public void setBodovi(Integer bodovi) {
		this.bodovi = bodovi;
	}

	public Long getSprintId() {
		return sprintId;
	}

	public void setSprintId(Long sprintId) {
		this.sprintId = sprintId;
	}

	public String getSprintIme() {
		return sprintIme;
	}

	public void setSprintIme(String sprintIme) {
		this.sprintIme = sprintIme;
	}

	public Long getStanjeId() {
		return stanjeId;
	}

	public void setStanjeId(Long stanjeId) {
		this.stanjeId = stanjeId;
	}

	public String getStanjeIme() {
		return stanjeIme;
	}

	public void setStanjeIme(String stanjeIme) {
		this.stanjeIme = stanjeIme;
	}

	public String getZaduzeni() {
		return zaduzeni;
	}

	public void setZaduzeni(String zaduzeni) {
		this.zaduzeni = zaduzeni;
	}
	

}
