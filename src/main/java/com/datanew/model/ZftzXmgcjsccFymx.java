package com.datanew.model;


import java.math.BigDecimal;
import javax.persistence.*;

@Entity
@Table(name="ZFTZ_XMGCJSCC_FYMX")
public  class  ZftzXmgcjsccFymx {

    private Long id;

    private Long idZftzXmgcjscc;

    private Long gcfyid;

    private Integer tzefl;

    private BigDecimal htje;

    private BigDecimal xyje;

    public void setId(Long id){

        this.id=id;
    }

    @Id
    @GeneratedValue(generator="sequence")
    @SequenceGenerator(name="sequence",sequenceName="SEQ_ZFTZ_XMGCJSCC_FYMX")
    @Basic
    @Column(name="ID")
    public Long getId(){

        return this.id;
    }

    public void setIdZftzXmgcjscc(Long idZftzXmgcjscc){

        this.idZftzXmgcjscc=idZftzXmgcjscc;
    }

    @Basic
    @Column(name="ID_ZFTZ_XMGCJSCC")
    public Long getIdZftzXmgcjscc(){

        return this.idZftzXmgcjscc;
    }

    public void setGcfyid(Long gcfyid){

        this.gcfyid=gcfyid;
    }

    @Basic
    @Column(name="GCFYID")
    public Long getGcfyid(){

        return this.gcfyid;
    }

    public void setTzefl(Integer tzefl){

        this.tzefl=tzefl;
    }

    @Basic
    @Column(name="TZEFL")
    public Integer getTzefl(){

        return this.tzefl;
    }

    public void setHtje(BigDecimal htje){

        this.htje=htje;
    }

    @Basic
    @Column(name="HTJE")
    public BigDecimal getHtje(){

        return this.htje;
    }

    public void setXyje(BigDecimal xyje){

        this.xyje=xyje;
    }

    @Basic
    @Column(name="XYJE")
    public BigDecimal getXyje(){

        return this.xyje;
    }

    public ZftzXmgcjsccFymx(Long id,Long idZftzXmgcjscc,Long gcfyid,Integer tzefl,BigDecimal htje,BigDecimal xyje){

        
        this.id=id;
        this.idZftzXmgcjscc=idZftzXmgcjscc;
        this.gcfyid=gcfyid;
        this.tzefl=tzefl;
        this.htje=htje;
        this.xyje=xyje;
    }

    public ZftzXmgcjsccFymx(){

            }

}
