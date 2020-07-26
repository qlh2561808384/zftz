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
@Table(name="FBYX_NDSRB")
public class FbyxNdsrb {

    private Long id;
    private Long ptgs;
    private Long zmlx;
    private Long zymcid;
    private String zymc;
    private String ssxzqh;
    private Long jhcrrq;
    private BigDecimal ncjhje;
    private BigDecimal tzjhje;
    private Long sjcrrq;
    private BigDecimal sjcrje;
    private String bz;
    private Long czrq;
    private Long zfrq;
    private String djr;

    @Id
    @GeneratedValue(generator="sequence")
    @SequenceGenerator(name="sequence",sequenceName="SEQ_FBYX_NDSRB")
    @Basic
    @Column(name="id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    @Column(name="zmlx")
    public Long getZmlx() {
        return zmlx;
    }

    public void setZmlx(Long zmlx) {
        this.zmlx = zmlx;
    }

    @Basic
    @Column(name="zymcid")
    public Long getZymcid() {
        return zymcid;
    }

    public void setZymcid(Long zymcid) {
        this.zymcid = zymcid;
    }

    @Basic
    @Column(name="zymc")
    public String getZymc() {
        return zymc;
    }

    public void setZymc(String zymc) {
        this.zymc = zymc;
    }

    @Basic
    @Column(name="ssxzqh")
    public String getSsxzqh() {
        return ssxzqh;
    }

    public void setSsxzqh(String ssxzqh) {
        this.ssxzqh = ssxzqh;
    }

    @Basic
    @Column(name="jhcrrq")
    public Long getJhcrrq() {
        return jhcrrq;
    }

    public void setJhcrrq(Long jhcrrq) {
        this.jhcrrq = jhcrrq;
    }

    @Basic
    @Column(name="nc_jhje")
    public BigDecimal getNcjhje() {
        return ncjhje;
    }

    public void setNcjhje(BigDecimal ncjhje) {
        this.ncjhje = ncjhje;
    }

    @Basic
    @Column(name="tz_jhje")
    public BigDecimal getTzjhje() {
        return tzjhje;
    }

    public void setTzjhje(BigDecimal tzjhje) {
        this.tzjhje = tzjhje;
    }

    @Basic
    @Column(name="sjcr_rq")
    public Long getSjcrrq() {
        return sjcrrq;
    }

    public void setSjcrrq(Long sjcrrq) {
        this.sjcrrq = sjcrrq;
    }

    @Basic
    @Column(name="sjcr_je")
    public BigDecimal getSjcrje() {
        return sjcrje;
    }

    public void setSjcrje(BigDecimal sjcrje) {
        this.sjcrje = sjcrje;
    }

    @Basic
    @Column(name="bz")
    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
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
}
