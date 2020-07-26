package com.datanew.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="ZFTZ_SGTBAMX")
public class Sgtysbamx {
	private long id;
	private long sgtbaid;
	private long gcfyid;
	private int tzefl;
	private double bcsgtys;
	private String bz;
	public Sgtysbamx() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Id
    @GeneratedValue(generator="sequence")
    @SequenceGenerator(name="sequence",sequenceName="SEQ_ZFTZ_SGTBAMX")
    @Basic
    @Column(name="id")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	@Basic
    @Column(name="ID_ZFTZ_SGTBA")
	public long getSgtbaid() {
		return sgtbaid;
	}

	public void setSgtbaid(long sgtbaid) {
		this.sgtbaid = sgtbaid;
	}

	@Basic
    @Column(name="GCFYID")
	public long getGcfyid() {
		return gcfyid;
	}

	public void setGcfyid(long gcfyid) {
		this.gcfyid = gcfyid;
	}
	
	@Basic
    @Column(name="TZEFL")
	public int getTzefl() {
		return tzefl;
	}

	public void setTzefl(int tzefl) {
		this.tzefl = tzefl;
	}

	@Basic
    @Column(name="BCSGTYS")
	public double getBcsgtys() {
		return bcsgtys;
	}

	public void setBcsgtys(double bcsgtys) {
		this.bcsgtys = bcsgtys;
	}
	
	@Basic
    @Column(name="BZ")
	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}
	public Sgtysbamx(long id, long sgtbaid, long gcfyid, int tzefl,
			double bcsgtys,String bz) {
		super();
		this.id = id;
		this.sgtbaid = sgtbaid;
		this.gcfyid = gcfyid;
		this.tzefl = tzefl;
		this.bcsgtys = bcsgtys;
		this.bz = bz;
	}
	
	
}
