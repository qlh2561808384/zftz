package com.datanew.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name="ZFTZ_XMZCDJ")
public  class  ZftzXmzcdj {

    //id
    private Long id;

    //项目id，zftz_xm.id
    private Long idZftzXm;

    //资产编号
    private String zcbh;

    //资产名称
    private String zcmc;

    //资产类别
    private String zclb;

    //资产价值
    private BigDecimal zcjz;

    //面积(平方米)
    private BigDecimal mj;

    //入账方式
    private Integer rzfs;

    //入账时间
    private Integer rzsj;

    //备注
    private String bz;

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
    @SequenceGenerator(name="sequence",sequenceName="SEQ_ZFTZ_XMZJLYDJ")
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

    public void setZcbh(String zcbh){

        this.zcbh=zcbh;
    }

    @Basic
    @Column(name="ZCBH",length=20)
    public String getZcbh(){

        return this.zcbh;
    }

    public void setZcmc(String zcmc){

        this.zcmc=zcmc;
    }

    @Basic
    @Column(name="ZCMC",length=60)
    public String getZcmc(){

        return this.zcmc;
    }

    public void setZclb(String zclb){

        this.zclb=zclb;
    }

    @Basic
    @Column(name="ZCLB",length=10)
    public String getZclb(){

        return this.zclb;
    }

    public void setZcjz(BigDecimal zcjz){

        this.zcjz=zcjz;
    }

    @Basic
    @Column(name="ZCJZ")
    public BigDecimal getZcjz(){

        return this.zcjz;
    }

    public void setMj(BigDecimal mj){

        this.mj=mj;
    }

    @Basic
    @Column(name="MJ")
    public BigDecimal getMj(){

        return this.mj;
    }

    public void setRzfs(Integer rzfs){

        this.rzfs=rzfs;
    }

    @Basic
    @Column(name="RZFS")
    public Integer getRzfs(){

        return this.rzfs;
    }

    public void setRzsj(Integer rzsj){

        this.rzsj=rzsj;
    }

    @Basic
    @Column(name="RZSJ")
    public Integer getRzsj(){

        return this.rzsj;
    }

    public void setBz(String bz){

        this.bz=bz;
    }

    @Basic
    @Column(name="BZ",length=200)
    public String getBz(){

        return this.bz;
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

    public ZftzXmzcdj(Long id,Long idZftzXm,String zcbh,String zcmc,String zclb,BigDecimal zcjz,BigDecimal mj,Integer rzfs,Integer rzsj,String bz,Integer zt,Date czsj,String czr){

        
        this.id=id;
        this.idZftzXm=idZftzXm;
        this.zcbh=zcbh;
        this.zcmc=zcmc;
        this.zclb=zclb;
        this.zcjz=zcjz;
        this.mj=mj;
        this.rzfs=rzfs;
        this.rzsj=rzsj;
        this.bz=bz;
        this.zt=zt;
        this.czsj=czsj;
        this.czr=czr;
    }

    public ZftzXmzcdj(){

            }

}
