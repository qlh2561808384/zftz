package com.datanew.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;
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
@Table(name="ZFTZ_ZBK")
public  class  ZftzZbk {

    private Long id;

    //项目类型
    private Integer xmlx;

    //一级指标
    private String yjzb;

    //二级指标
    private String ejzb;

    //三级指标
    private String sjzb;

    //单位投资额
    private BigDecimal dwtze;

    //计量单位
    private String jldw;

    //指标来源
    private String zbly;

    //备注
    private String bz;

    //作废日期
    private Date zfrq;

    //操作时间
    private Date czsj;

    //操作人
    private String czr;

    public void setId(Long id){

        this.id=id;
    }

    @Id
    @GeneratedValue(generator="sequence")
    @SequenceGenerator(name="sequence",sequenceName="SEQ_ZFTZ_ZBK")
    @Basic
    @Column(name="ID")
    public Long getId(){

        return this.id;
    }

    public void setXmlx(Integer xmlx){

        this.xmlx=xmlx;
    }

    @Basic
    @Column(name="XMLX")
    public Integer getXmlx(){

        return this.xmlx;
    }

    public void setYjzb(String yjzb){

        this.yjzb=yjzb;
    }

    @Basic
    @Column(name="YJZB",length=50)
    public String getYjzb(){

        return this.yjzb;
    }

    public void setEjzb(String ejzb){

        this.ejzb=ejzb;
    }

    @Basic
    @Column(name="EJZB",length=50)
    public String getEjzb(){

        return this.ejzb;
    }

    public void setSjzb(String sjzb){

        this.sjzb=sjzb;
    }

    @Basic
    @Column(name="SJZB",length=50)
    public String getSjzb(){

        return this.sjzb;
    }

    public void setDwtze(BigDecimal dwtze){

        this.dwtze=dwtze;
    }

    @Basic
    @Column(name="DWTZE")
    public BigDecimal getDwtze(){

        return this.dwtze;
    }

    public void setJldw(String jldw){

        this.jldw=jldw;
    }

    @Basic
    @Column(name="JLDW",length=20)
    public String getJldw(){

        return this.jldw;
    }

    public void setZbly(String zbly){

        this.zbly=zbly;
    }

    @Basic
    @Column(name="ZBLY",length=100)
    public String getZbly(){

        return this.zbly;
    }

    public void setBz(String bz){

        this.bz=bz;
    }

    @Basic
    @Column(name="BZ",length=200)
    public String getBz(){

        return this.bz;
    }

    public void setZfrq(Date zfrq){

        this.zfrq=zfrq;
    }

    @Basic
    @Column(name="ZFRQ")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",locale="zh",timezone="GMT+8")
    public Date getZfrq(){

        return this.zfrq;
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

    public ZftzZbk(Long id,Integer xmlx,String yjzb,String ejzb,String sjzb,BigDecimal dwtze,String jldw,String zbly,String bz,Date zfrq,Date czsj,String czr){

        
        this.id=id;
        this.xmlx=xmlx;
        this.yjzb=yjzb;
        this.ejzb=ejzb;
        this.sjzb=sjzb;
        this.dwtze=dwtze;
        this.jldw=jldw;
        this.zbly=zbly;
        this.bz=bz;
        this.zfrq=zfrq;
        this.czsj=czsj;
        this.czr=czr;
    }

    public ZftzZbk(){

            }

}
