package com.datanew.model;

import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="ZFTZ_XMGS_TZMX")
public  class  ZftzXmgsTzmx {

    //id
    private Long id;

    //对应概算id，ZFTZ_XMGS_TZ.id
    private Long idZftzXmgsTz;

    //对应工程费用id，ZFTZ_XMGSMX.id
    private Long gcfyid;

    //工程或者费用名称
    private String gcfymc;

    //计量单位
    private String jldw;

    //工程量
    private BigDecimal gcl;

    //费用类型
    private Integer fylx;

    //单价或者费率
    private BigDecimal djhfl;

    //投资额分类
    private Integer tzefl;

    //调整金额
    private BigDecimal tzje;

    //调增说明
    private BigDecimal tzsm;

    public void setId(Long id){

        this.id=id;
    }

    @Id
    @GeneratedValue(generator="sequence")
    @SequenceGenerator(name="sequence",sequenceName="SEQ_ZFTZ_XMGS_TZMX")
    @Basic
    @Column(name="ID")
    public Long getId(){

        return this.id;
    }

    public void setIdZftzXmgsTz(Long idZftzXmgsTz){

        this.idZftzXmgsTz=idZftzXmgsTz;
    }

    @Basic
    @Column(name="ID_ZFTZ_XMGS_TZ")
    public Long getIdZftzXmgsTz(){

        return this.idZftzXmgsTz;
    }

    public void setGcfyid(Long gcfyid){

        this.gcfyid=gcfyid;
    }

    @Basic
    @Column(name="GCFYID")
    public Long getGcfyid(){

        return this.gcfyid;
    }

    public void setGcfymc(String gcfymc){

        this.gcfymc=gcfymc;
    }

    @Basic
    @Column(name="GCFYMC",length=100)
    public String getGcfymc(){

        return this.gcfymc;
    }

    public void setJldw(String jldw){

        this.jldw=jldw;
    }

    @Basic
    @Column(name="JLDW",length=20)
    public String getJldw(){

        return this.jldw;
    }

    public void setGcl(BigDecimal gcl){

        this.gcl=gcl;
    }

    @Basic
    @Column(name="GCL")
    public BigDecimal getGcl(){

        return this.gcl;
    }

    public void setFylx(Integer fylx){

        this.fylx=fylx;
    }

    @Basic
    @Column(name="FYLX")
    public Integer getFylx(){

        return this.fylx;
    }

    public void setDjhfl(BigDecimal djhfl){

        this.djhfl=djhfl;
    }

    @Basic
    @Column(name="DJHFL")
    public BigDecimal getDjhfl(){

        return this.djhfl;
    }

    public void setTzefl(Integer tzefl){

        this.tzefl=tzefl;
    }

    @Basic
    @Column(name="TZEFL")
    public Integer getTzefl(){

        return this.tzefl;
    }

    public void setTzje(BigDecimal tzje){

        this.tzje=tzje;
    }

    @Basic
    @Column(name="TZJE")
    public BigDecimal getTzje(){

        return this.tzje;
    }

    public void setTzsm(BigDecimal tzsm){

        this.tzsm=tzsm;
    }

    @Basic
    @Column(name="TZSM")
    public BigDecimal getTzsm(){

        return this.tzsm;
    }

    public ZftzXmgsTzmx(Long id,Long idZftzXmgsTz,Long gcfyid,String gcfymc,String jldw,BigDecimal gcl,Integer fylx,BigDecimal djhfl,Integer tzefl,BigDecimal tzje,BigDecimal tzsm){

        
        this.id=id;
        this.idZftzXmgsTz=idZftzXmgsTz;
        this.gcfyid=gcfyid;
        this.gcfymc=gcfymc;
        this.jldw=jldw;
        this.gcl=gcl;
        this.fylx=fylx;
        this.djhfl=djhfl;
        this.tzefl=tzefl;
        this.tzje=tzje;
        this.tzsm=tzsm;
    }

    public ZftzXmgsTzmx(){

            }

}
