package swd20.Houseplantshop.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Genus {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long genusid;
	private String scientificName;
	private String commonName;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "genus")
	@JsonIgnoreProperties("genus")
	private List<Plant> plants;
	
	@ManyToOne
	@JsonIgnoreProperties("genera")
	@JoinColumn(name = "familyid")
	private Family family;
	
	public Genus() {		
	}

	public Genus(String scientificName, String commonName, Family family) {
		this.scientificName = scientificName;
		this.commonName = commonName;
		this.family = family;
	}

	public void setGenusid(Long genusid) {
		this.genusid = genusid;
	}

	public void setScientificName(String scientificName) {
		this.scientificName = scientificName;
	}

	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}

	public void setPlants(List<Plant> plants) {
		this.plants = plants;
	}

	public void setFamily(Family family) {
		this.family = family;
	}

	public Long getGenusid() {
		return genusid;
	}

	public String getScientificName() {
		return scientificName;
	}

	public String getCommonName() {
		return commonName;
	}

	public List<Plant> getPlants() {
		return plants;
	}

	public Family getFamily() {
		return family;
	}

	@Override
	public String toString() {
		if (this.family != null) {
			return "Genus [genusid=" + genusid + ", scientificName=" + scientificName + ", commonName="
					+ commonName + ", family=" + this.getFamily() + "]";
		} else {
			return "Genus [genusis=" + genusid + ", scientificName=" + scientificName + ", commonName="
					+ commonName + "]";
		}
	}
}
