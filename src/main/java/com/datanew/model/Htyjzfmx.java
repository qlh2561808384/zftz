package com.datanew.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="ZFTZ_HTYJZFMX")
public class Htyjzfmx {
	private long id;
	private long htbaid;
	private long yjzfsj;
	private double yjzfje;
	private String zfkxsm;
	private double yjxxjd;
	private String bz;
	public Htyjzfmx() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Id
    @GeneratedValue(generator="sequence")
    @SequenceGenerator(name="sequence",sequenceName="SEQ_ZFTZ_HTYJZFMX")
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
    @Column(name = "YJZFSJ")
	public long getYjzfsj() {
		return yjzfsj;
	}
	public void setYjzfsj(long yjzfsj) {
		this.yjzfsj = yjzfsj;
	}
	@Basic
    @Column(name = "YJZFJE")
	public double getYjzfje() {
		return yjzfje;
	}
	public void setYjzfje(double yjzfje) {
		this.yjzfje = yjzfje;
	}
	@Basic
    @Column(name = "YJXXJD")
	public double getYjxxjd() {
		return yjxxjd;
	}
	public void setYjxxjd(double yjxxjd) {
		this.yjxxjd = yjxxjd;
	}
	
	@Basic
    @Column(name = "BZ")
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	@Basic
    @Column(name = "ZFKXSM")
	public String getZfkxsm() {
		return zfkxsm;
	}
	public void setZfkxsm(String zfkxsm) {
		this.zfkxsm = zfkxsm;
	}
	
	
	public Htyjzfmx(long id, long htbaid, long yjzfsj, double yjzfje,
			String zfkxsm, double yjxxjd, String bz) {
		super();
		this.id = id;
		this.htbaid = htbaid;
		this.yjzfsj = yjzfsj;
		this.yjzfje = yjzfje;
		this.zfkxsm = zfkxsm;
		this.yjxxjd = yjxxjd;
		this.bz = bz;
		
	}
	
	
}
