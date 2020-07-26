package com.datanew.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="ZFTZ_HTBAMX")
public class Htbamx {
	private long id;
	private long htbaid;
	private long sgtbaid;
	private long gcfyid;
	private String tzefl;
	private double bchtje;
	private String bz;
	public Htbamx() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Id
    @GeneratedValue(generator="sequence")
    @SequenceGenerator(name="sequence",sequenceName="SEQ_ZFTZ_HTBAMX")
    @Basic
    @Column(name="id")
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	@Basic
    @Column(name = "ID_ZFTZ_HTBA")
	public long getHtbaid() {
		return htbaid;
	}
	public void setHtbaid(long htbaid) {
		this.htbaid = htbaid;
	}
	@Basic
    @Column(name = "ID_ZFTZ_SGTBA")
	public long getSgtbaid() {
		return sgtbaid;
	}
	public void setSgtbaid(long sgtbaid) {
		this.sgtbaid = sgtbaid;
	}
	@Basic
    @Column(name = "GCFYID")
	public long getGcfyid() {
		return gcfyid;
	}
	public void setGcfyid(long gcfyid) {
		this.gcfyid = gcfyid;
	}
	@Basic
    @Column(name = "TZEFL")
	public String getTzefl() {
		return tzefl;
	}
	public void setTzefl(String tzefl) {
		this.tzefl = tzefl;
	}
	@Basic
    @Column(name = "BCHTJE")
	public double getBchtje() {
		return bchtje;
	}
	public void setBchtje(double bchtje) {
		this.bchtje = bchtje;
	}
	@Basic
    @Column(name = "BZ")
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public Htbamx(long id, long htbaid, long sgtbaid, long gcfyid,
			String tzefl, double bchtje, String bz) {
		super();
		this.id = id;
		this.htbaid = htbaid;
		this.sgtbaid = sgtbaid;
		this.gcfyid = gcfyid;
		this.tzefl = tzefl;
		this.bchtje = bchtje;
		this.bz = bz;
	}
	
	
}
