package com.datanew.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name="ZFTZ_XMGSYJZX")
public  class  ZftzXmgsyjzx {

    //id
    private Long id;

    //项目id，zftz_xm.id
    private Long idZftzXm;

    //建设内容
    private String jsnr;

    //反馈意见
    private String fkyj;

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
    @SequenceGenerator(name="sequence",sequenceName="SEQ_ZFTZ_XMGSYJZX")
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

    public void setJsnr(String jsnr){

        this.jsnr=jsnr;
    }

    @Basic
    @Column(name="JSNR",length=200)
    public String getJsnr(){

        return this.jsnr;
    }

    public void setFkyj(String fkyj){

        this.fkyj=fkyj;
    }

    @Basic
    @Column(name="FKYJ",length=200)
    public String getFkyj(){

        return this.fkyj;
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

    public ZftzXmgsyjzx(Long id,Long idZftzXm,String jsnr,String fkyj,Integer lchj,Integer zt,Date czsj,String czr){

        
        this.id=id;
        this.idZftzXm=idZftzXm;
        this.jsnr=jsnr;
        this.fkyj=fkyj;
        this.lchj=lchj;
        this.zt=zt;
        this.czsj=czsj;
        this.czr=czr;
    }

    public ZftzXmgsyjzx(){

            }

}
