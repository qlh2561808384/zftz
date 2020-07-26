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
@Table(name="ZFTZ_XMGSMX")
public  class  ZftzXmgsmx {

    //id
    private Long id;

    //对应概算id
    private Long idZftzXmgs;

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

    //投资额_建筑投资
    private BigDecimal tzeJztz;

    //投资额_安装投资
    private BigDecimal tzeAztz;

    //投资额_设备投资
    private BigDecimal tzeSbtz;

    //投资额_其他投资
    private BigDecimal tzeQttz;

    //其中：土地征迁费用
    private BigDecimal tzeQtTdzqfy;

    //其中：利息费用
    private BigDecimal tzeQtLx;

    public void setId(Long id){

        this.id=id;
    }

    @Id
    @GeneratedValue(generator="sequence")
    @SequenceGenerator(name="sequence",sequenceName="SEQ_ZFTZ_XMGSMX")
    @Basic
    @Column(name="ID")
    public Long getId(){

        return this.id;
    }

    public void setIdZftzXmgs(Long idZftzXmgs){

        this.idZftzXmgs=idZftzXmgs;
    }

    @Basic
    @Column(name="ID_ZFTZ_XMGS")
    public Long getIdZftzXmgs(){

        return this.idZftzXmgs;
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

    public void setTzeJztz(BigDecimal tzeJztz){

        this.tzeJztz=tzeJztz;
    }

    @Basic
    @Column(name="TZE_JZTZ")
    public BigDecimal getTzeJztz(){

        return this.tzeJztz;
    }

    public void setTzeAztz(BigDecimal tzeAztz){

        this.tzeAztz=tzeAztz;
    }

    @Basic
    @Column(name="TZE_AZTZ")
    public BigDecimal getTzeAztz(){

        return this.tzeAztz;
    }

    public void setTzeSbtz(BigDecimal tzeSbtz){

        this.tzeSbtz=tzeSbtz;
    }

    @Basic
    @Column(name="TZE_SBTZ")
    public BigDecimal getTzeSbtz(){

        return this.tzeSbtz;
    }

    public void setTzeQttz(BigDecimal tzeQttz){

        this.tzeQttz=tzeQttz;
    }

    @Basic
    @Column(name="TZE_QTTZ")
    public BigDecimal getTzeQttz(){

        return this.tzeQttz;
    }

    public void setTzeQtTdzqfy(BigDecimal tzeQtTdzqfy){

        this.tzeQtTdzqfy=tzeQtTdzqfy;
    }

    @Basic
    @Column(name="TZE_QT_TDZQFY")
    public BigDecimal getTzeQtTdzqfy(){

        return this.tzeQtTdzqfy;
    }

    public void setTzeQtLx(BigDecimal tzeQtLx){

        this.tzeQtLx=tzeQtLx;
    }

    @Basic
    @Column(name="TZE_QT_LX")
    public BigDecimal getTzeQtLx(){

        return this.tzeQtLx;
    }

    public ZftzXmgsmx(Long id,Long idZftzXmgs,String gcfymc,String jldw,BigDecimal gcl,Integer fylx,BigDecimal djhfl,BigDecimal tzeJztz,BigDecimal tzeAztz,BigDecimal tzeSbtz,BigDecimal tzeQttz,BigDecimal tzeQtTdzqfy,BigDecimal tzeQtLx){

        
        this.id=id;
        this.idZftzXmgs=idZftzXmgs;
        this.gcfymc=gcfymc;
        this.jldw=jldw;
        this.gcl=gcl;
        this.fylx=fylx;
        this.djhfl=djhfl;
        this.tzeJztz=tzeJztz;
        this.tzeAztz=tzeAztz;
        this.tzeSbtz=tzeSbtz;
        this.tzeQttz=tzeQttz;
        this.tzeQtTdzqfy=tzeQtTdzqfy;
        this.tzeQtLx=tzeQtLx;
    }

    public ZftzXmgsmx(){

            }

}
