package com.cls.entities;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.DecimalMin;





@Entity
public class Calcul {
	 @Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
	 private Long id;
	 @OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	 private Angajat angajat;
	 private String lunaCurenta;
	 private double salariuBrut;
	 private int zileLucrate;
	 @DecimalMin(value="0",message="Doar numere")
	 private int persoaneIntretinere;
	
	 public Calcul() {
		super();
		// TODO Auto-generated constructor stub
	}
	 
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getLunaCurenta() {
		return lunaCurenta;
	}

	public void setLunaCurenta(String lunaCurenta) {
		this.lunaCurenta = lunaCurenta;
	}

	public double getSalariuBrut() {
		return salariuBrut;
	}

	public void setSalariuBrut(double salariuBrut) {
		this.salariuBrut = salariuBrut;
	}

	public int getZileLucrate() {
		return zileLucrate;
	}

	public void setZileLucrate(int zileLucrate) {
		this.zileLucrate = zileLucrate;
	}

	public int getPersoaneIntretinere() {
		return persoaneIntretinere;
	}

	public void setPersoaneIntretinere(int persoaneIntretinere) {
		this.persoaneIntretinere = persoaneIntretinere;
	}



	public Angajat getAngajat() {
		return angajat;
	}



	public void setAngajat(Angajat angajat) {
		this.angajat = angajat;
	}
	 
	
	 
	 
}