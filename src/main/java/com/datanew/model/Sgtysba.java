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
@Table(name="ZFTZ_SGTBA")
public class Sgtysba {
	private long id;
	private long id_zftz_xm;
	private String jsdwlxr;
	private String lxdh;
	private String bcbagcmc;
	private double bcsgtys;
	private String bcbajsnr;
	private String zgbmyj;
	private String sczjbayj;
	private int lchj;
	private int zt;
	private Date czsj;
	private String czr;
	private String bcsgtysshdw;
	private List<Sgtysbamx> sgtysbamx;
	private List<ZftzFile> zftzFiles;
	
	public Sgtysba() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Id
    @GeneratedValue(generator="sequence")
    @SequenceGenerator(name="sequence",sequenceName="SEQ_ZFTZ_SGTBA")
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
    @Column(name="JSDWLXR")
	public String getJsdwlxr() {
		return jsdwlxr;
	}

	public void setJsdwlxr(String jsdwlxr) {
		this.jsdwlxr = jsdwlxr;
	}

	@Basic
    @Column(name="LXDH")
	public String getLxdh() {
		return lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	@Basic
    @Column(name="BCBAGCMC")
	public String getBcbagcmc() {
		return bcbagcmc;
	}

	public void setBcbagcmc(String bcbagcmc) {
		this.bcbagcmc = bcbagcmc;
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
    @Column(name="BCBAJSNR")
	public String getBcbajsnr() {
		return bcbajsnr;
	}

	public void setBcbajsnr(String bcbajsnr) {
		this.bcbajsnr = bcbajsnr;
	}

	@Basic
    @Column(name="ZGBMYJ")
	public String getZgbmyj() {
		return zgbmyj;
	}

	public void setZgbmyj(String zgbmyj) {
		this.zgbmyj = zgbmyj;
	}

	@Basic
    @Column(name="SCZJBAYJ")
	public String getSczjbayj() {
		return sczjbayj;
	}

	public void setSczjbayj(String sczjbayj) {
		this.sczjbayj = sczjbayj;
	}

	@Basic
    @Column(name="LCHJ")
	public int getLchj() {
		return lchj;
	}

	public void setLchj(int lchj) {
		this.lchj = lchj;
	}

	@Basic
    @Column(name="ZT")
	public int getZt() {
		return zt;
	}

	public void setZt(int zt) {
		this.zt = zt;
	}

	@Basic
	@DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",locale="zh",timezone="GMT+8")
    @Column(name="CZSJ")
	public Date getCzsj() {
		return czsj;
	}

	public void setCzsj(Date czsj) {
		this.czsj = czsj;
	}

	@Basic
    @Column(name="CZR")
	public String getCzr() {
		return czr;
	}

	public void setCzr(String czr) {
		this.czr = czr;
	}
	
	@Basic
    @Column(name="BCSGTYSSHDW")
	public String getBcsgtysshdw() {
		return bcsgtysshdw;
	}

	public void setBcsgtysshdw(String bcsgtysshdw) {
		this.bcsgtysshdw = bcsgtysshdw;
	}
	
	@Transient
    public List<Sgtysbamx> getSgtysbamx() {
        return sgtysbamx;
    }

    public void setSgtysbamx(List<Sgtysbamx> sgtysbamx) {
        this.sgtysbamx = sgtysbamx;
    }

	public Sgtysba(long id, long id_zftz_xm, String jsdwlxr, String lxdh,
			String bcbagcmc, double bcsgtys, String bcbajsnr, String zgbmyj,
			String sczjbayj, int lchj, int zt, Date czsj, String czr) {
		super();
		this.id = id;
		this.id_zftz_xm = id_zftz_xm;
		this.jsdwlxr = jsdwlxr;
		this.lxdh = lxdh;
		this.bcbagcmc = bcbagcmc;
		this.bcsgtys = bcsgtys;
		this.bcbajsnr = bcbajsnr;
		this.zgbmyj = zgbmyj;
		this.sczjbayj = sczjbayj;
		this.lchj = lchj;
		this.zt = zt;
		this.czsj = czsj;
		this.czr = czr;
	}
	@Transient
	public List<ZftzFile> getZftzFiles() {
		return zftzFiles;
	}
	public void setZftzFiles(List<ZftzFile> zftzFiles) {
		this.zftzFiles = zftzFiles;
	}
	
	
}
