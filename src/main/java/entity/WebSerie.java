package entity;

import java.io.Serializable;

public class WebSerie implements Media, Serializable {

	private String anneeTournage;
	private String debutTournage;
	private String finTournage;
	private String typeTournage;
	private String adresseTournage;
	private String nomTournage;
	private String nomProducteur;
	private Double coordx;
	private Double coordy;

	public WebSerie(String anneeTournage, String debutTournage, String finTournage, String typeTournage,
			String adresseTournage, String nomTournage, String nomProducteur, Double coordx, Double coordy) {
		super();
		this.anneeTournage = anneeTournage;
		this.debutTournage = debutTournage;
		this.finTournage = finTournage;
		this.typeTournage = typeTournage;
		this.adresseTournage = adresseTournage;
		this.nomTournage = nomTournage;
		this.nomProducteur = nomProducteur;
		this.coordx = coordx;
		this.coordy = coordy;
	}

	public String getAnneeTournage() {
		return anneeTournage;
	}

	public void setAnneeTournage(String anneeTournage) {
		this.anneeTournage = anneeTournage;
	}

	public String getDebutTournage() {
		return debutTournage;
	}

	public void setDebutTournage(String debutTournage) {
		this.debutTournage = debutTournage;
	}

	public String getFinTournage() {
		return finTournage;
	}

	public void setFinTournage(String finTournage) {
		this.finTournage = finTournage;
	}

	public String getTypeTournage() {
		return typeTournage;
	}

	public void setTypeTournage(String typeTournage) {
		this.typeTournage = typeTournage;
	}

	public String getAdresseTournage() {
		return adresseTournage;
	}

	public void setAdresseTournage(String adresseTournage) {
		this.adresseTournage = adresseTournage;
	}

	public String getNomTournage() {
		return nomTournage;
	}

	public void setNomTournage(String nomTournage) {
		this.nomTournage = nomTournage;
	}

	public String getNomProducteur() {
		return nomProducteur;
	}

	public void setNomProducteur(String nomProducteur) {
		this.nomProducteur = nomProducteur;
	}

	public Double getCoordx() {
		return coordx;
	}

	public void setCoordx(Double coordx) {
		this.coordx = coordx;
	}

	public Double getCoordy() {
		return coordy;
	}

	public void setCoordy(Double coordy) {
		this.coordy = coordy;
	}

}
