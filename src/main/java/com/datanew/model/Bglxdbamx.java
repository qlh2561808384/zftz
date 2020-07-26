package com.datanew.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="ZFTZ_XMBGDBAMX")
public class Bglxdbamx {
	private long id;
	private long xmbgdbaid;
	private long gcfyid;
	private String tzefl;
	private double bcbgje;
	private String bz;
	public Bglxdbamx() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Id
    @GeneratedValue(generator="sequence")
    @SequenceGenerator(name="sequence",sequenceName="SEQ_ZFTZ_XMBGDBAMX")
    @Basic
    @Column(name="id")
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	@Basic
    @Column(name = "ID_ZFTZ_XMBGDBA")
	public long getXmbgdbaid() {
		return xmbgdbaid;
	}
	public void setXmbgdbaid(long xmbgdbaid) {
		this.xmbgdbaid = xmbgdbaid;
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
    @Column(name = "BCBGJE")
	public double getBcbgje() {
		return bcbgje;
	}
	public void setBcbgje(double bcbgje) {
		this.bcbgje = bcbgje;
	}
	@Basic
    @Column(name = "BZ")
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public Bglxdbamx(long id, long xmbgdbaid, long gcfyid, String tzefl,
			double bcbgje, String bz) {
		super();
		this.id = id;
		this.xmbgdbaid = xmbgdbaid;
		this.gcfyid = gcfyid;
		this.tzefl = tzefl;
		this.bcbgje = bcbgje;
		this.bz = bz;
	}
	
}
