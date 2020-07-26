package com.datanew.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="ZFTZ_XMTZWH")
public  class  ZftzXmtzwh {

    //id
    private Long id;

    //项目id，zftz_xm.id
    private Long idZftzXm;

    //项目编号
    private String xmbh;

    //项目赋码
    private String xmfm;

    //项目实施类型
    private Integer xmsslx;

    //立项年度
    private Integer lxnd;

    /*//建设地址/项目规划选址
    private String jszd;*/

    //计划开工日期
    private Integer jhkgrq;

    //计划竣工日期
    private Integer jhjgrq;

    //形象进度(%)
    private BigDecimal xxjd;

    //项目建议书文号(项目联系单)
    private String xmjyswh;

    //项目可研批复文号
    private String xmkypfwh;

    //概算批复文号
    private String gspfwh;

    //项目初步设计批复文号
    private String xmcbsjpfwh;

    //受托审核事务所(竣工决算)
    private String stshsws;

    //审定投资数
    private BigDecimal sdtze;

    //项目联系人
    private String xmlxr;

    //项目联系电话
    private String xmlxdh;

    //状态，0作废，1有效
    private Integer zt;

    //操作时间
    private Date czsj;

    //操作人
    private String czr;

    //建设地址/项目规划选址
    private String jsdz;

    private Integer sjkgrq;

    @Basic
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",locale="zh",timezone="GMT+8")
    @Column(name="SJKGRQ")
    public Integer getSjkgrq() {
        return sjkgrq;
    }

    public void setSjkgrq(Integer sjkgrq) {
        this.sjkgrq = sjkgrq;
    }
    @Basic
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",locale="zh",timezone="GMT+8")
    @Column(name="SJJGRQ")
    public Integer getSjjgrq() {
        return sjjgrq;
    }

    public void setSjjgrq(Integer sjjgrq) {
        this.sjjgrq = sjjgrq;
    }

    private Integer sjjgrq;

    public void setId(Long id){

        this.id=id;
    }

    @Id
    @GeneratedValue(generator="sequence")
    @SequenceGenerator(name="sequence",sequenceName="SEQ_ZFTZ_XMTZWH")
    @Basic
    @Column(name="ID")
    public Long getId(){

        return this.id;
    }

    public void setIdZftzXm(Long idZftzXm){

        this.idZftzXm=idZftzXm;
    }

    @Basic
    @Column(name="ID_ZFTZ_XM")
    public Long getIdZftzXm(){

        return this.idZftzXm;
    }

    public void setXmbh(String xmbh){

        this.xmbh=xmbh;
    }

    @Basic
    @Column(name="XMBH",length=20)
    public String getXmbh(){

        return this.xmbh;
    }

    public void setXmfm(String xmfm){

        this.xmfm=xmfm;
    }

    @Basic
    @Column(name="XMFM",length=20)
    public String getXmfm(){

        return this.xmfm;
    }

    public void setXmsslx(Integer xmsslx){

        this.xmsslx=xmsslx;
    }

    @Basic
    @Column(name="XMSSLX")
    public Integer getXmsslx(){

        return this.xmsslx;
    }

    public void setLxnd(Integer lxnd){

        this.lxnd=lxnd;
    }

    @Basic
    @Column(name="LXND")
    public Integer getLxnd(){

        return this.lxnd;
    }

  /*  public void setJszd(String jszd){

        this.jszd=jszd;
    }

    @Basic
    @Column(name="JSZD",length=200)
    public String getJszd(){

        return this.jszd;
    }*/

    public void setJhkgrq(Integer jhkgrq){

        this.jhkgrq=jhkgrq;
    }

    @Basic
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",locale="zh",timezone="GMT+8")
    @Column(name="JHKGRQ")
    public Integer getJhkgrq(){

        return this.jhkgrq;
    }

    public void setJhjgrq(Integer jhjgrq){

        this.jhjgrq=jhjgrq;
    }

    @Basic
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",locale="zh",timezone="GMT+8")
    @Column(name="JHJGRQ")
    public Integer getJhjgrq(){

        return this.jhjgrq;
    }

    public void setXxjd(BigDecimal xxjd){

        this.xxjd=xxjd;
    }

    @Basic
    @Column(name="XXJD")
    public BigDecimal getXxjd(){

        return this.xxjd;
    }

    public void setXmjyswh(String xmjyswh){

        this.xmjyswh=xmjyswh;
    }

    @Basic
    @Column(name="XMJYSWH",length=100)
    public String getXmjyswh(){

        return this.xmjyswh;
    }

    public void setXmkypfwh(String xmkypfwh){

        this.xmkypfwh=xmkypfwh;
    }

    @Basic
    @Column(name="XMKYPFWH",length=100)
    public String getXmkypfwh(){

        return this.xmkypfwh;
    }

    public void setGspfwh(String gspfwh){

        this.gspfwh=gspfwh;
    }

    @Basic
    @Column(name="GSPFWH",length=100)
    public String getGspfwh(){

        return this.gspfwh;
    }

    public void setXmcbsjpfwh(String xmcbsjpfwh){

        this.xmcbsjpfwh=xmcbsjpfwh;
    }

    @Basic
    @Column(name="XMCBSJPFWH",length=100)
    public String getXmcbsjpfwh(){

        return this.xmcbsjpfwh;
    }

    public void setStshsws(String stshsws){

        this.stshsws=stshsws;
    }

    @Basic
    @Column(name="STSHSWS",length=100)
    public String getStshsws(){

        return this.stshsws;
    }

    public void setSdtze(BigDecimal sdtze){

        this.sdtze=sdtze;
    }

    @Basic
    @Column(name="SDTZE")
    public BigDecimal getSdtze(){

        return this.sdtze;
    }

    public void setXmlxr(String xmlxr){

        this.xmlxr=xmlxr;
    }

    @Basic
    @Column(name="XMLXR",length=20)
    public String getXmlxr(){

        return this.xmlxr;
    }

    public void setXmlxdh(String xmlxdh){

        this.xmlxdh=xmlxdh;
    }

    @Basic
    @Column(name="XMLXDH",length=20)
    public String getXmlxdh(){

        return this.xmlxdh;
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

    public void setJsdz(String jsdz){

        this.jsdz=jsdz;
    }

    @Basic
    @Column(name="JSDZ",length=200)
    public String getJsdz(){

        return this.jsdz;
    }


    public ZftzXmtzwh(Long id,Long idZftzXm,String xmbh,String xmfm,Integer xmsslx,Integer lxnd/*,String jszd*/,Integer jhkgrq,Integer jhjgrq,BigDecimal xxjd,String xmjyswh,String xmkypfwh,String gspfwh,String xmcbsjpfwh,String stshsws,BigDecimal sdtze,String xmlxr,String xmlxdh,Integer zt,Date czsj,String czr,String jsdz,Integer sjkgrq,Integer sjjgrq){

        
        this.id=id;
        this.idZftzXm=idZftzXm;
        this.xmbh=xmbh;
        this.xmfm=xmfm;
        this.xmsslx=xmsslx;
        this.lxnd=lxnd;
//        this.jszd=jszd;
        this.jhkgrq=jhkgrq;
        this.jhjgrq=jhjgrq;
        this.xxjd=xxjd;
        this.xmjyswh=xmjyswh;
        this.xmkypfwh=xmkypfwh;
        this.gspfwh=gspfwh;
        this.xmcbsjpfwh=xmcbsjpfwh;
        this.stshsws=stshsws;
        this.sdtze=sdtze;
        this.xmlxr=xmlxr;
        this.xmlxdh=xmlxdh;
        this.zt=zt;
        this.czsj=czsj;
        this.czr=czr;
        this.jsdz=jsdz;
        this.sjkgrq = sjkgrq;
        this.sjjgrq = sjjgrq;
    }

    public ZftzXmtzwh(){

            }

}
