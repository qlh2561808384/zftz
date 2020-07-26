package com.datanew.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="ZFTZ_XMGS_TZ")
public  class  ZftzXmgsTz {

    //id
    private Long id;

    //项目id，zftz_xm.id
    private Long idZftzXm;

    //概算id，ZFTZ_XMGS.id
    private Long idZftzXmgs;

    //概算调整财政评审建议
    private String gstzczphjy;

    //流程环节
    private Integer lchj;

    //状态，0作废，1有效
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
    @SequenceGenerator(name="sequence",sequenceName="SEQ_ZFTZ_XMGS_TZ")
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

    public void setIdZftzXmgs(Long idZftzXmgs){

        this.idZftzXmgs=idZftzXmgs;
    }

    @Basic
    @Column(name="ID_ZFTZ_XMGS")
    public Long getIdZftzXmgs(){

        return this.idZftzXmgs;
    }

    public void setGstzczphjy(String gstzczphjy){

        this.gstzczphjy=gstzczphjy;
    }

    @Basic
    @Column(name="GSTZCZPHJY",length=200)
    public String getGstzczphjy(){

        return this.gstzczphjy;
    }

    public void setLchj(Integer lchj){

        this.lchj=lchj;
    }

    @Basic
    @Column(name="LCHJ")
    public Integer getLchj(){

        return this.lchj;
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

    public ZftzXmgsTz(Long id,Long idZftzXm,Long idZftzXmgs,String gstzczphjy,Integer lchj,Integer zt,Date czsj,String czr){

        
        this.id=id;
        this.idZftzXm=idZftzXm;
        this.idZftzXmgs=idZftzXmgs;
        this.gstzczphjy=gstzczphjy;
        this.lchj=lchj;
        this.zt=zt;
        this.czsj=czsj;
        this.czr=czr;
    }

    public ZftzXmgsTz(){

            }

}
