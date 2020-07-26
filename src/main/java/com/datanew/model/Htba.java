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
@Table(name="ZFTZ_HTBA")
public class Htba {
	private long id;
	private long id_zftz_xm;
	private String htmc;
	private String htbm;
	private int htlx;
	private String zbdwmc;
	private double dybdj;
	private double htje;
	private long ydkgrq;
	private long ydjgrq;
	private long qdrq;
	private int jsfs;
	private String gcmc;
	private String htnr;
	private String zbwjzytk;
	private int lchj;
	private int zt;
	private Date czsj;
	private String czr;
	private int htxybj;
	private List<Htbamx> htbamx;
	private List<Htyjzfmx> htyjzfmx;
	private List<ZftzFile> zftzfiles;
	public Htba() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Id
    @GeneratedValue(generator="sequence")
    @SequenceGenerator(name="sequence",sequenceName="SEQ_ZFTZ_HTBA")
    @Basic
    @Column(name="id")
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	@Basic
    @Column(name="ID_ZFTZ_XM")
	public long getId_zftz_xm() {
		return id_zftz_xm;
	}
	public void setId_zftz_xm(long id_zftz_xm) {
		this.id_zftz_xm = id_zftz_xm;
	}
	@Basic
    @Column(name="HTMC")
	public String getHtmc() {
		return htmc;
	}
	public void setHtmc(String htmc) {
		this.htmc = htmc;
	}
	@Basic
    @Column(name="HTBM")
	public String getHtbm() {
		return htbm;
	}
	public void setHtbm(String htbm) {
		this.htbm = htbm;
	}
	@Basic
    @Column(name="HTLX")
	public int getHtlx() {
		return htlx;
	}
	public void setHtlx(int htlx) {
		this.htlx = htlx;
	}
	@Basic
    @Column(name="ZBDWMC")
	public String getZbdwmc() {
		return zbdwmc;
	}
	public void setZbdwmc(String zbdwmc) {
		this.zbdwmc = zbdwmc;
	}
	@Basic
    @Column(name="DYBDJ")
	public double getDybdj() {
		return dybdj;
	}
	public void setDybdj(double dybdj) {
		this.dybdj = dybdj;
	}
	@Basic
    @Column(name="HTJE")
	public double getHtje() {
		return htje;
	}
	public void setHtje(double htje) {
		this.htje = htje;
	}
	@Basic
    @Column(name = "YDKGRQ")
	public long getYdkgrq() {
		return ydkgrq;
	}
	public void setYdkgrq(long ydkgrq) {
		this.ydkgrq = ydkgrq;
	}
	@Basic
    /*@DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",locale="zh",timezone="GMT+8")*/
    @Column(name = "YDJGRQ")
	public long getYdjgrq() {
		return ydjgrq;
	}
	public void setYdjgrq(long ydjgrq) {
		this.ydjgrq = ydjgrq;
	}
	@Basic
    /*@DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",locale="zh",timezone="GMT+8")*/
    @Column(name = "QDRQ")
	public long getQdrq() {
		return qdrq;
	}
	public void setQdrq(long qdrq) {
		this.qdrq = qdrq;
	}
	
	@Basic
    @Column(name = "JSFS")
	public int getJsfs() {
		return jsfs;
	}
	public void setJsfs(int jsfs) {
		this.jsfs = jsfs;
	}
	@Basic
    @Column(name = "GCMC")
	public String getGcmc() {
		return gcmc;
	}
	public void setGcmc(String gcmc) {
		this.gcmc = gcmc;
	}
	@Basic
    @Column(name = "HTNR")
	public String getHtnr() {
		return htnr;
	}
	public void setHtnr(String htnr) {
		this.htnr = htnr;
	}
	@Basic
    @Column(name = "ZBWJZYTK")
	public String getZbwjzytk() {
		return zbwjzytk;
	}
	public void setZbwjzytk(String zbwjzytk) {
		this.zbwjzytk = zbwjzytk;
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
    @Column(name = "HTXYBJ")
	public int getHtxybj() {
		return htxybj;
	}

	public void setHtxybj(int htxybj) {
		this.htxybj = htxybj;
	}

	@Transient
	public List<Htbamx> getHtbamx() {
		return htbamx;
	}
	public void setHtbamx(List<Htbamx> htbamx) {
		this.htbamx = htbamx;
	}

	public Htba(long id, long id_zftz_xm, String htmc, String htbm,
			int htlx, String zbdwmc, double dybdj, double htje, long ydkgrq,
			long ydjgrq, long qdrq, int jsfs, String gcmc, String htnr,
			String zbwjzytk, String bxfwtk, int lchj, int zt, Date czsj, String czr,int htxybj) {
		super();
		this.id = id;
		this.id_zftz_xm = id_zftz_xm;
		this.htmc = htmc;
		this.htbm = htbm;
		this.htlx = htlx;
		this.zbdwmc = zbdwmc;
		this.dybdj = dybdj;
		this.htje = htje;
		this.ydkgrq = ydkgrq;
		this.ydjgrq = ydjgrq;
		this.qdrq = qdrq;
		this.jsfs = jsfs;
		this.gcmc = gcmc;
		this.htnr = htnr;
		this.zbwjzytk = zbwjzytk;
		this.lchj = lchj;
		this.zt = zt;
		this.czsj = czsj;
		this.czr = czr;
		this.htxybj = htxybj;
	}
	@Transient
	public List<Htyjzfmx> getHtyjzfmx() {
		return htyjzfmx;
	}

	public void setHtyjzfmx(List<Htyjzfmx> htyjzfmx) {
		this.htyjzfmx = htyjzfmx;
	}

	@Transient
	public List<ZftzFile> getZftzfiles() {
		return zftzfiles;
	}

	public void setZftzfiles(List<ZftzFile> zftzfiles) {
		this.zftzfiles = zftzfiles;
	}
	
	
	
	
}
