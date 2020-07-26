package com.datanew.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="ZFTZ_XMGCJSCC")
public  class  ZftzXmgcjscc {

    //id
    private Long id;

    //合同id，ZFTZ_HTBA.id
    private Long idZftzHtba;

    //合同名称
    private String htmc;

    //工程内容
    private String gcnr;

    //项目名称
    private String xmmc;

    //项目总投资
    private BigDecimal xmztz;

    //建设单位
    private String jsdw;

    //主管部门
    private String zgbm;

    //项目资金来源_财政性资金
    private BigDecimal zjlyCzxzj;

    //项目资金来源_资源平衡
    private BigDecimal zjlyZyph;

    //项目资金来源_其他
    private BigDecimal zjlyQt;

    //开工时间
    private Date kgsj;

    //竣工时间
    private Date jgsj;

    //审核中介机构
    private String shzjjg;

    //结算送审时间
    private Date jssssj;

    //结算审定时间
    private Date jssdsj;

    //工程变更情况_增加额
    private BigDecimal gcbgZje;

    //工程变更情况_减少额
    private BigDecimal gcbgJse;

    //送审数
    private BigDecimal sss;

    //核增（减）数
    private BigDecimal hzjs;

    //已支付工程款
    private BigDecimal yzfgck;

    //尚需支付工程款
    private BigDecimal sxzfgck;

    //主管部门备案文件号
    private String zgbmbawh;

    //财政审批意见
    private String czspyj;

    //状态，0作废，1未备案，2已备案
    private Integer zt;

    //操作时间
    private Date czsj;

    //操作人
    private String czr;

    public void setId(Long id){

        this.id=id;
    }

    @Id
    @GeneratedValue(generator="sequence")
    @SequenceGenerator(name="sequence",sequenceName="SEQ_ZFTZ_XMGCJSCC")
    @Basic
    @Column(name="ID")
    public Long getId(){

        return this.id;
    }

    public void setIdZftzHtba(Long idZftzHtba){

        this.idZftzHtba=idZftzHtba;
    }

    @Basic
    @Column(name="ID_ZFTZ_HTBA")
    public Long getIdZftzHtba(){

        return this.idZftzHtba;
    }

    public void setHtmc(String htmc){

        this.htmc=htmc;
    }

    @Basic
    @Column(name="HTMC",length=60)
    public String getHtmc(){

        return this.htmc;
    }

    public void setGcnr(String gcnr){

        this.gcnr=gcnr;
    }

    @Basic
    @Column(name="GCNR",length=200)
    public String getGcnr(){

        return this.gcnr;
    }

    public void setXmmc(String xmmc){

        this.xmmc=xmmc;
    }

    @Basic
    @Column(name="XMMC",length=100)
    public String getXmmc(){

        return this.xmmc;
    }

    public void setXmztz(BigDecimal xmztz){

        this.xmztz=xmztz;
    }

    @Basic
    @Column(name="XMZTZ")
    public BigDecimal getXmztz(){

        return this.xmztz;
    }

    public void setJsdw(String jsdw){

        this.jsdw=jsdw;
    }

    @Basic
    @Column(name="JSDW",length=100)
    public String getJsdw(){

        return this.jsdw;
    }

    public void setZgbm(String zgbm){

        this.zgbm=zgbm;
    }

    @Basic
    @Column(name="ZGBM",length=100)
    public String getZgbm(){

        return this.zgbm;
    }

    public void setZjlyCzxzj(BigDecimal zjlyCzxzj){

        this.zjlyCzxzj=zjlyCzxzj;
    }

    @Basic
    @Column(name="ZJLY_CZXZJ")
    public BigDecimal getZjlyCzxzj(){

        return this.zjlyCzxzj;
    }

    public void setZjlyZyph(BigDecimal zjlyZyph){

        this.zjlyZyph=zjlyZyph;
    }

    @Basic
    @Column(name="ZJLY_ZYPH")
    public BigDecimal getZjlyZyph(){

        return this.zjlyZyph;
    }

    public void setZjlyQt(BigDecimal zjlyQt){

        this.zjlyQt=zjlyQt;
    }

    @Basic
    @Column(name="ZJLY_QT")
    public BigDecimal getZjlyQt(){

        return this.zjlyQt;
    }

    public void setKgsj(Date kgsj){

        this.kgsj=kgsj;
    }

    @Basic
    @Column(name="KGSJ")
    public Date getKgsj(){

        return this.kgsj;
    }

    public void setJgsj(Date jgsj){

        this.jgsj=jgsj;
    }

    @Basic
    @Column(name="JGSJ")
    public Date getJgsj(){

        return this.jgsj;
    }

    public void setShzjjg(String shzjjg){

        this.shzjjg=shzjjg;
    }

    @Basic
    @Column(name="SHZJJG",length=60)
    public String getShzjjg(){

        return this.shzjjg;
    }

    public void setJssssj(Date jssssj){

        this.jssssj=jssssj;
    }

    @Basic
    @Column(name="JSSSSJ")
    public Date getJssssj(){

        return this.jssssj;
    }

    public void setJssdsj(Date jssdsj){

        this.jssdsj=jssdsj;
    }

    @Basic
    @Column(name="JSSDSJ")
    public Date getJssdsj(){

        return this.jssdsj;
    }

    public void setGcbgZje(BigDecimal gcbgZje){

        this.gcbgZje=gcbgZje;
    }

    @Basic
    @Column(name="GCBG_ZJE")
    public BigDecimal getGcbgZje(){

        return this.gcbgZje;
    }

    public void setGcbgJse(BigDecimal gcbgJse){

        this.gcbgJse=gcbgJse;
    }

    @Basic
    @Column(name="GCBG_JSE")
    public BigDecimal getGcbgJse(){

        return this.gcbgJse;
    }

    public void setSss(BigDecimal sss){

        this.sss=sss;
    }

    @Basic
    @Column(name="SSS")
    public BigDecimal getSss(){

        return this.sss;
    }

    public void setHzjs(BigDecimal hzjs){

        this.hzjs=hzjs;
    }

    @Basic
    @Column(name="HZJS")
    public BigDecimal getHzjs(){

        return this.hzjs;
    }

    public void setYzfgck(BigDecimal yzfgck){

        this.yzfgck=yzfgck;
    }

    @Basic
    @Column(name="YZFGCK")
    public BigDecimal getYzfgck(){

        return this.yzfgck;
    }

    public void setSxzfgck(BigDecimal sxzfgck){

        this.sxzfgck=sxzfgck;
    }

    @Basic
    @Column(name="SXZFGCK")
    public BigDecimal getSxzfgck(){

        return this.sxzfgck;
    }

    public void setZgbmbawh(String zgbmbawh){

        this.zgbmbawh=zgbmbawh;
    }

    @Basic
    @Column(name="ZGBMBAWH",length=60)
    public String getZgbmbawh(){

        return this.zgbmbawh;
    }

    public void setCzspyj(String czspyj){

        this.czspyj=czspyj;
    }

    @Basic
    @Column(name="CZSPYJ",length=200)
    public String getCzspyj(){

        return this.czspyj;
    }

    public void setZt(Integer zt){

        this.zt=zt;
    }

    @Basic
    @Column(name="ZT")
    public Integer getZt(){

        return this.zt;
    }

    public void setCzsj(Date czsj){

        this.czsj=czsj;
    }

    @Basic
    @Column(name="CZSJ")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",locale="zh",timezone="GMT+8")
    public Date getCzsj(){

        return this.czsj;
    }

    public void setCzr(String czr){

        this.czr=czr;
    }

    @Basic
    @Column(name="CZR",length=20)
    public String getCzr(){

        return this.czr;
    }

    public ZftzXmgcjscc(Long id, Long idZftzHtba, String htmc, String gcnr, String xmmc, BigDecimal xmztz, String jsdw, String zgbm, BigDecimal zjlyCzxzj, BigDecimal zjlyZyph, BigDecimal zjlyQt, Date kgsj, Date jgsj, String shzjjg, Date jssssj, Date jssdsj, BigDecimal gcbgZje, BigDecimal gcbgJse, BigDecimal sss, BigDecimal hzjs, BigDecimal yzfgck, BigDecimal sxzfgck, String zgbmbawh, String czspyj, Integer zt, Date czsj, String czr) {
        this.id = id;
        this.idZftzHtba = idZftzHtba;
        this.htmc = htmc;
        this.gcnr = gcnr;
        this.xmmc = xmmc;
        this.xmztz = xmztz;
        this.jsdw = jsdw;
        this.zgbm = zgbm;
        this.zjlyCzxzj = zjlyCzxzj;
        this.zjlyZyph = zjlyZyph;
        this.zjlyQt = zjlyQt;
        this.kgsj = kgsj;
        this.jgsj = jgsj;
        this.shzjjg = shzjjg;
        this.jssssj = jssssj;
        this.jssdsj = jssdsj;
        this.gcbgZje = gcbgZje;
        this.gcbgJse = gcbgJse;
        this.sss = sss;
        this.hzjs = hzjs;
        this.yzfgck = yzfgck;
        this.sxzfgck = sxzfgck;
        this.zgbmbawh = zgbmbawh;
        this.czspyj = czspyj;
        this.zt = zt;
        this.czsj = czsj;
        this.czr = czr;
    }

    public ZftzXmgcjscc(){

            }

}
