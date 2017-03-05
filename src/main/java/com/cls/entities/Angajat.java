package com.cls.entities;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Angajat {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@NotNull
	private String nume;
	@NotNull
	private String prenume;
	@Size(min=13,max=13, message="CNP-ul are 13 caractere")
	private String cnp;
	private String functia;
	private Date dataNasterii;
	private Date dataAngajarii;
	private Date dataTerminarii;
	@NotNull
	private double salariu;
	private short persInt;
	
	
	@OneToMany(mappedBy="angajat")
	private Set<Concediu> concedii;
	
	@Transient
	private double deducere = calculDI();
	
	public Angajat() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getFunctia() {
		return functia;
	}

	public void setFunctia(String functia) {
		this.functia = functia;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public String getPrenume() {
		return prenume;
	}

	public void setPrenume(String prenume) {
		this.prenume = prenume;
	}

	public String getCnp() {
		return cnp;
	}

	public void setCnp(String cnp) {
		this.cnp = cnp;
	}

	public Date getDataNasterii() {
		return dataNasterii;
	}

	public void setDataNasterii(Date dataNasterii) {
		this.dataNasterii = dataNasterii;
	}

	public Date getDataAngajarii() {
		return dataAngajarii;
	}

	public void setDataAngajarii(Date dataAngajarii) {
		this.dataAngajarii = dataAngajarii;
	}


	public double getSalariu() {
		return salariu;
	}

	public void setSalariu(double salariu) {
		this.salariu = salariu;
	}

	public short getPersInt() {
		return persInt;
	}

	public void setPersInt(short persInt) {
		this.persInt = persInt;
	}

	public Date getDataTerminarii() {
		return dataTerminarii;
	}

	public void setDataTerminarii(Date dataTerminarii) {
		this.dataTerminarii = dataTerminarii;
	}
	
	

	public double getDeducere() {
		return deducere;
	}


	// calculul deducerii persoanle
	 public double calculDI() {
	        double ded = 0;
	        if (salariu <= 1500) {
	            switch (persInt) {
	                default:
	                    ded = 300;
	                    break;
	                case 1:
	                    ded = 400;
	                    break;
	                case 2:
	                    ded = 500;
	                    break;
	                case 3:
	                    ded = 600;
	                    break;
	                case 4:
	                    ded = 800;
	                    break;
	            }
	        } else if (salariu >= 1501 && salariu <= 3000) {
	            switch (persInt) {
	                default:
	                    ded = 300 * (1 - (salariu - 1500) / 1500);
	                    break;
	                case 1:
	                    ded = 400 * (1 - (salariu - 1500) / 1500);
	                    break;
	                case 2:
	                    ded = 500 * (1 - (salariu - 1500) / 1500);
	                    break;
	                case 3:
	                    ded = 600 * (1 - (salariu - 1500) / 1500);
	                    break;
	                case 4:
	                    ded = 800 * (1 - (salariu - 1500) / 1500);
	                    break;
	            }
	        } else {
	            ded = 0;
	        }

	        return ded;
	    }
	


	
	
}
