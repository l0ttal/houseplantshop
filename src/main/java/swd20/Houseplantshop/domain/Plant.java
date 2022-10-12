package swd20.Houseplantshop.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Plant {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String scientificName;
	private String commonName;
	
	@ManyToOne
	@JsonIgnoreProperties ("plants")
	@JoinColumn(name = "genusid")
	private Genus genus;
	
	@ManyToOne
	@JsonIgnoreProperties ("plants")
	@JoinColumn(name = "familyid")
	private Family family;
	
	public Plant() {		
	}

	public Plant(String scientificName, String commonName, Genus genus, Family family) {
		this.scientificName = scientificName;
		this.commonName = commonName;
		this.genus = genus;
		this.family = family;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setScientificName(String scientificName) {
		this.scientificName = scientificName;
	}

	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}

	public void setGenus(Genus genus) {
		this.genus = genus;
	}

	public void setFamily(Family family) {
		this.family = family;
	}

	public Long getId() {
		return id;
	}

	public String getScientificName() {
		return scientificName;
	}

	public String getCommonName() {
		return commonName;
	}

	public Genus getGenus() {
		return genus;
	}

	public Family getFamily() {
		return family;
	}

	@Override
	public String toString() {
		if (this.genus != null && this.family != null) {
			return "Plant [id=" + id + ", scientificName=" + scientificName + ", commonName=" + commonName + ", genus="
					+ this.getGenus() + ", family=" + this.getFamily() + "]";
		} else if (this.genus != null && this.family == null) {
			return "Plant [id=" + id + ", scientificName=" + scientificName + ", commonName=" + commonName + ", genus="
					+ this.getGenus() + "]";
		} else if (this.genus == null && this.family != null) {
			return "Plant [id=" + id + ", scientificName=" + scientificName + ", commonName=" + commonName + ", family="
		+ this.getFamily() + "]";
		} else {
			return "Plant [id=" + id + ", scientificName=" + scientificName + ", commonName=" + commonName + "]";
		}
	}
}
