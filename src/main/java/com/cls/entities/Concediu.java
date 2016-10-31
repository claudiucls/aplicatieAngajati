package com.cls.entities;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Concediu {
		@Id
		@GeneratedValue
		private int id;
	    private String descriere;
	    
	    private String tip;
	    private Date dataDeLa;
	    private Date dataPanaLa;
	    
	    @ManyToOne(cascade=CascadeType.ALL)
	    private Angajat angajat;
	    
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getDescriere() {
			return descriere;
		}
		public void setDescriere(String descriere) {
			this.descriere = descriere;
		}
		public String getTip() {
			return tip;
		}
		public void setTip(String tip) {
			this.tip = tip;
		}
		public Date getDataDeLa() {
			return dataDeLa;
		}
		public void setDataDeLa(Date dataDeLa) {
			this.dataDeLa = dataDeLa;
		}
		public Date getDataPanaLa() {
			return dataPanaLa;
		}
		public void setDataPanaLa(Date dataPanaLa) {
			this.dataPanaLa = dataPanaLa;
		}
		public Angajat getAngajat() {
			return angajat;
		}
		public void setAngajat(Angajat angajat) {
			this.angajat = angajat;
		}
		
	    
	    
}
