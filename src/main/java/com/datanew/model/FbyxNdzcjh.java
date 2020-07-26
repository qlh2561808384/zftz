package com.datanew.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author wuwei
 * @create 2019/5/17 10:50
 * @desc
 **/
@Entity
@Table(name="FBYX_NDZCJH")
public class FbyxNdzcjh {

    private Long id;
    private Long nd;
    private Long ptgs;
    private Long xmid;
    private BigDecimal nc_yyggys;
    private BigDecimal nc_phzy;
    private BigDecimal nc_phzc;
    private BigDecimal nc_pppshzb;
    private BigDecimal nc_qxfd;
    private BigDecimal nc_qt;
    private BigDecimal tz_yyggys;
    private BigDecimal tz_phzy;
    private BigDecimal tz_phzc;
    private BigDecimal tz_pppshzb;
    private BigDecimal tz_qxfd;
    private BigDecimal tz_qt;
    private Long czrq;
    private Long zfrq;
    private String djr;
    private BigDecimal bnjhtz;

    @Id
    @GeneratedValue(generator="sequence")
    @SequenceGenerator(name="sequence",sequenceName="SEQ_FBYX_NDZCJH")
    @Basic
    @Column(name="id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name="nd")
    public Long getNd() {
        return nd;
    }

    public void setNd(Long nd) {
        this.nd = nd;
    }

    @Basic
    @Column(name="ptgs")
    public Long getPtgs() {
        return ptgs;
    }

    public void setPtgs(Long ptgs) {
        this.ptgs = ptgs;
    }

    @Basic
    @Column(name="xmid")
    public Long getXmid() {
        return xmid;
    }

    public void setXmid(Long xmid) {
        this.xmid = xmid;
    }

    @Basic
    @Column(name="nc_yyggys")
    public BigDecimal getNc_yyggys() {
        return nc_yyggys;
    }

    public void setNc_yyggys(BigDecimal nc_yyggys) {
        this.nc_yyggys = nc_yyggys;
    }

    @Basic
    @Column(name="nc_phzy")
    public BigDecimal getNc_phzy() {
        return nc_phzy;
    }

    public void setNc_phzy(BigDecimal nc_phzy) {
        this.nc_phzy = nc_phzy;
    }

    @Basic
    @Column(name="nc_phzc")
    public BigDecimal getNc_phzc() {
        return nc_phzc;
    }

    public void setNc_phzc(BigDecimal nc_phzc) {
        this.nc_phzc = nc_phzc;
    }

    @Basic
    @Column(name="nc_pppshzb")
    public BigDecimal getNc_pppshzb() {
        return nc_pppshzb;
    }

    public void setNc_pppshzb(BigDecimal nc_pppshzb) {
        this.nc_pppshzb = nc_pppshzb;
    }

    @Basic
    @Column(name="nc_qxfd")
    public BigDecimal getNc_qxfd() {
        return nc_qxfd;
    }

    public void setNc_qxfd(BigDecimal nc_qxfd) {
        this.nc_qxfd = nc_qxfd;
    }

    @Basic
    @Column(name="nc_qt")
    public BigDecimal getNc_qt() {
        return nc_qt;
    }

    public void setNc_qt(BigDecimal nc_qt) {
        this.nc_qt = nc_qt;
    }

    @Basic
    @Column(name="tz_yyggys")
    public BigDecimal getTz_yyggys() {
        return tz_yyggys;
    }

    public void setTz_yyggys(BigDecimal tz_yyggys) {
        this.tz_yyggys = tz_yyggys;
    }

    @Basic
    @Column(name="tz_phzy")
    public BigDecimal getTz_phzy() {
        return tz_phzy;
    }

    public void setTz_phzy(BigDecimal tz_phzy) {
        this.tz_phzy = tz_phzy;
    }

    @Basic
    @Column(name="tz_phzc")
    public BigDecimal getTz_phzc() {
        return tz_phzc;
    }

    public void setTz_phzc(BigDecimal tz_phzc) {
        this.tz_phzc = tz_phzc;
    }

    @Basic
    @Column(name="tz_pppshzb")
    public BigDecimal getTz_pppshzb() {
        return tz_pppshzb;
    }

    public void setTz_pppshzb(BigDecimal tz_pppshzb) {
        this.tz_pppshzb = tz_pppshzb;
    }

    @Basic
    @Column(name="tz_qxfd")
    public BigDecimal getTz_qxfd() {
        return tz_qxfd;
    }

    public void setTz_qxfd(BigDecimal tz_qxfd) {
        this.tz_qxfd = tz_qxfd;
    }

    @Basic
    @Column(name="tz_qt")
    public BigDecimal getTz_qt() {
        return tz_qt;
    }

    public void setTz_qt(BigDecimal tz_qt) {
        this.tz_qt = tz_qt;
    }

    @Basic
    @Column(name="czrq")
    public Long getCzrq() {
        return czrq;
    }

    public void setCzrq(Long czrq) {
        this.czrq = czrq;
    }

    @Basic
    @Column(name="zfrq")
    public Long getZfrq() {
        return zfrq;
    }

    public void setZfrq(Long zfrq) {
        this.zfrq = zfrq;
    }

    @Basic
    @Column(name="djr")
    public String getDjr() {
        return djr;
    }

    public void setDjr(String djr) {
        this.djr = djr;
    }

    @Basic
    @Column(name="bnjhtz")
    public BigDecimal getBnjhtz() {
        return bnjhtz;
    }

    public void setBnjhtz(BigDecimal bnjhtz) {
        this.bnjhtz = bnjhtz;
    }
}
