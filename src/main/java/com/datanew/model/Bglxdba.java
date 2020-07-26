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
@Table(name="ZFTZ_XMBGDBA")
public class Bglxdba {
	private long id;
	private long xmid;
	private long htbaid;
	private double sjzjzjys;
	private String bgnr;
	private String tcdw;
	private String tcr;
	private long tcsj;
	private String jsdwjbr;
	private String jsdwspr;
	private long jsdwsptysj;
	private String zgdwspr;
	private long zgdwspsj;
	private long bgqrsj;
	private int lchj;
	private int zt;
	private Date czsj;
	private String czr;
	private String bgdh;
	private List<Bglxdbamx> bglxdbamx;
	private List<ZftzFile> zfFile;
	
	public Bglxdba() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Id
    @GeneratedValue(generator="sequence")
    @SequenceGenerator(name="sequence",sequenceName="SEQ_ZFTZ_XMBGDBA")
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
    @Column(name = "SJZJYSZJ")
	public double getSjzjzjys() {
		return sjzjzjys;
	}
	public void setSjzjzjys(double sjzjzjys) {
		this.sjzjzjys = sjzjzjys;
	}
	@Basic
    @Column(name = "BGNR")
	public String getBgnr() {
		return bgnr;
	}
	public void setBgnr(String bgnr) {
		this.bgnr = bgnr;
	}
	@Basic
    @Column(name = "TCDW")
	public String getTcdw() {
		return tcdw;
	}
	public void setTcdw(String tcdw) {
		this.tcdw = tcdw;
	}
	@Basic
    @Column(name = "TCR")
	public String getTcr() {
		return tcr;
	}
	public void setTcr(String tcr) {
		this.tcr = tcr;
	}
	@Basic
    @Column(name = "TCSJ")
	public long getTcsj() {
		return tcsj;
	}
	public void setTcsj(long tcsj) {
		this.tcsj = tcsj;
	}
	@Basic
    @Column(name = "JSDWJBR")
	public String getJsdwjbr() {
		return jsdwjbr;
	}
	public void setJsdwjbr(String jsdwjbr) {
		this.jsdwjbr = jsdwjbr;
	}
	@Basic
    @Column(name = "JSDWSPR")
	public String getJsdwspr() {
		return jsdwspr;
	}
	public void setJsdwspr(String jsdwspr) {
		this.jsdwspr = jsdwspr;
	}
	@Basic
    @Column(name = "SJDWSPTYSJ")
	public long getJsdwsptysj() {
		return jsdwsptysj;
	}
	public void setJsdwsptysj(long jsdwsptysj) {
		this.jsdwsptysj = jsdwsptysj;
	}
	@Basic
    @Column(name = "ZGDWSPR")
	public String getZgdwspr() {
		return zgdwspr;
	}
	public void setZgdwspr(String zgdwspr) {
		this.zgdwspr = zgdwspr;
	}
	@Basic
    @Column(name = "ZGDWSPSJ")
	public long getZgdwspsj() {
		return zgdwspsj;
	}
	public void setZgdwspsj(long zgdwspsj) {
		this.zgdwspsj = zgdwspsj;
	}
	@Basic
    @Column(name = "BGQRSJ")
	public long getBgqrsj() {
		return bgqrsj;
	}
	public void setBgqrsj(long bgqrsj) {
		this.bgqrsj = bgqrsj;
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
    @Column(name = "BGDH")
	public String getBgdh() {
		return bgdh;
	}
	public void setBgdh(String bgdh) {
		this.bgdh = bgdh;
	}
	public Bglxdba(long id, long xmid, long htbaid, double sjzjzjys,
			String bgnr, String tcdw, String tcr, long tcsj, String jsdwjbr,
			String jsdwspr, long jsdwsptysj, String zgdwspr, long zgdwspsj,
			long bgqrsj, int lchj, int zt, Date czsj, String czr,String bgdh) {
		super();
		this.id = id;
		this.xmid = xmid;
		this.htbaid = htbaid;
		this.sjzjzjys = sjzjzjys;
		this.bgnr = bgnr;
		this.tcdw = tcdw;
		this.tcr = tcr;
		this.tcsj = tcsj;
		this.jsdwjbr = jsdwjbr;
		this.jsdwspr = jsdwspr;
		this.jsdwsptysj = jsdwsptysj;
		this.zgdwspr = zgdwspr;
		this.zgdwspsj = zgdwspsj;
		this.bgqrsj = bgqrsj;
		this.lchj = lchj;
		this.zt = zt;
		this.czsj = czsj;
		this.czr = czr;
		this.bgdh = bgdh;
	}
	@Transient
	public List<Bglxdbamx> getBglxdbamx() {
		return bglxdbamx;
	}
	public void setBglxdbamx(List<Bglxdbamx> bglxdbamx) {
		this.bglxdbamx = bglxdbamx;
	}
	@Transient
	public List<ZftzFile> getZfFile() {
		return zfFile;
	}
	public void setZfFile(List<ZftzFile> zfFile) {
		this.zfFile = zfFile;
	}
	
	
}
