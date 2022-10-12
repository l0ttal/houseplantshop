package swd20.Houseplantshop.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Family {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long familyid;
	private String scientificName;
	private String commonName;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "family")
	@JsonIgnoreProperties("family")
	private List<Genus> genera;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "family")
	@JsonIgnoreProperties("family")
	private List<Plant> plants;
	
	public Family() {
	}

	public Family(String scientificName, String commonName) {
		super();
		this.scientificName = scientificName;
		this.commonName = commonName;
	}

	public void setFamilyid(Long familyid) {
		this.familyid = familyid;
	}

	public void setScientificName(String scientificName) {
		this.scientificName = scientificName;
	}

	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}

	public void setGenera(List<Genus> genera) {
		this.genera = genera;
	}

	public void setFamilies(List<Plant> plants) {
		this.plants = plants;
	}

	public Long getFamilyid() {
		return familyid;
	}

	public String getScientificName() {
		return scientificName;
	}

	public String getCommonName() {
		return commonName;
	}

	public List<Genus> getGenera() {
		return genera;
	}

	public List<Plant> getPlants() {
		return plants;
	}

	@Override
	public String toString() {
		return "Family [familyid=" + familyid + ", scientificName=" + scientificName + ", commonName=" + commonName
				+ "]";
	}
}
