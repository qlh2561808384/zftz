package com.datanew.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="ZFTZ_XMKTDBA")
public class Xmktdba {
	private long id;
	private long xmid;
	private long htbaid;
	private String jsdw;
	private String sgdw;
	private String cyshjdnr;
	private long rq;
	private String cyshjdqk;
	private String jsdwyj;
	private String sgdwyj;
	private String jldwyj;
	private int lchj;
	private int zt;
	private Date czsj;
	private String czr;
	private String ktdh;
	private double xxjd;
	private List<ZftzFile> zftzfiles;
	
	public Xmktdba() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	@Id
    @GeneratedValue(generator="sequence")
    @SequenceGenerator(name="sequence",sequenceName="SEQ_ZFTZ_XMKTDBA")
    @Basic
    @Column(name="id")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Basic
    @Column(name = "ID_ZFTZ_XM")
	public long getXmid() {
		return xmid;
	}

	public void setXmid(long xmid) {
		this.xmid = xmid;
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
    @Column(name = "JSDW")
	public String getJsdw() {
		return jsdw;
	}

	public void setJsdw(String jsdw) {
		this.jsdw = jsdw;
	}

	@Basic
    @Column(name = "SGDW")
	public String getSgdw() {
		return sgdw;
	}

	public void setSgdw(String sgdw) {
		this.sgdw = sgdw;
	}

	@Basic
    @Column(name = "CYSHJDNR")
	public String getCyshjdnr() {
		return cyshjdnr;
	}

	public void setCyshjdnr(String cyshjdnr) {
		this.cyshjdnr = cyshjdnr;
	}

	@Basic
    @Column(name = "RQ")
	public long getRq() {
		return rq;
	}

	public void setRq(long rq) {
		this.rq = rq;
	}

	@Basic
    @Column(name = "CYSHJDQK")
	public String getCyshjdqk() {
		return cyshjdqk;
	}

	public void setCyshjdqk(String cyshjdqk) {
		this.cyshjdqk = cyshjdqk;
	}

	@Basic
    @Column(name = "JSDWYJ")
	public String getJsdwyj() {
		return jsdwyj;
	}

	public void setJsdwyj(String jsdwyj) {
		this.jsdwyj = jsdwyj;
	}

	@Basic
    @Column(name = "SGDWYJ")
	public String getSgdwyj() {
		return sgdwyj;
	}

	public void setSgdwyj(String sgdwyj) {
		this.sgdwyj = sgdwyj;
	}

	@Basic
    @Column(name = "JLDWYJ")
	public String getJldwyj() {
		return jldwyj;
	}

	public void setJldwyj(String jldwyj) {
		this.jldwyj = jldwyj;
	}

	@Basic
    @Column(name = "LCHJ")
	public int getLchj() {
		return lchj;
	}

	public void setLchj(int lchj) {
		this.lchj = lchj;
	}

	@Basic
    @Column(name = "ZT")
	public int getZt() {
		return zt;
	}

	public void setZt(int zt) {
		this.zt = zt;
	}

	@Basic
	@DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",locale="zh",timezone="GMT+8")
    @Column(name = "CZSJ")
	public Date getCzsj() {
		return czsj;
	}

	public void setCzsj(Date czsj) {
		this.czsj = czsj;
	}

	@Basic
    @Column(name = "CZR")
	public String getCzr() {
		return czr;
	}

	public void setCzr(String czr) {
		this.czr = czr;
	}
	
	@Basic
    @Column(name = "KTDH")
	public String getKtdh() {
		return ktdh;
	}

	public void setKtdh(String ktdh) {
		this.ktdh = ktdh;
	}
	
	@Basic
    @Column(name = "XXJD")
	public double getXxjd() {
		return xxjd;
	}

	public void setXxjd(double xxjd) {
		this.xxjd = xxjd;
	}

	public Xmktdba(long id, long xmid, long htbaid, String jsdw, String sgdw,
			String cyshjdnr, long rq, String cyshjdqk, String jsdwyj,
			String sgdwyj, String jldwyj, int lchj, int zt, Date czsj,
			String czr,String ktdh,double xxjd) {
		super();
		this.id = id;
		this.xmid = xmid;
		this.htbaid = htbaid;
		this.jsdw = jsdw;
		this.sgdw = sgdw;
		this.cyshjdnr = cyshjdnr;
		this.rq = rq;
		this.cyshjdqk = cyshjdqk;
		this.jsdwyj = jsdwyj;
		this.sgdwyj = sgdwyj;
		this.jldwyj = jldwyj;
		this.lchj = lchj;
		this.zt = zt;
		this.czsj = czsj;
		this.czr = czr;
		this.ktdh = ktdh;
		this.xxjd = xxjd;
	}


	@Transient
	public List<ZftzFile> getZftzfiles() {
		return zftzfiles;
	}


	public void setZftzfiles(List<ZftzFile> zftzfiles) {
		this.zftzfiles = zftzfiles;
	}
	
	
}
