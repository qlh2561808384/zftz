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
@Table(name="ZFTZ_XMBZCS")
public  class  ZftzXmbzcs {

    //id
    private Long id;

    //项目id
    private Long idZftzXm;

    //指标库id
    private Long idZftzZbk;

    //数量
    private Double sl;

    public void setId(Long id){

        this.id=id;
    }

    @Id
    @GeneratedValue(generator="sequence")
    @SequenceGenerator(name="sequence",sequenceName="SEQ_ZFTZ_XMBZCS")
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

    public void setIdZftzZbk(Long idZftzZbk){

        this.idZftzZbk=idZftzZbk;
    }

    @Basic
    @Column(name="ID_ZFTZ_ZBK")
    public Long getIdZftzZbk(){

        return this.idZftzZbk;
    }

    public void setSl(Double sl){

        this.sl=sl;
    }

    @Basic
    @Column(name="SL")
    public Double getSl(){

        return this.sl;
    }

    public ZftzXmbzcs(Long id,Long idZftzXm,Long idZftzZbk,Double sl){

        
        this.id=id;
        this.idZftzXm=idZftzXm;
        this.idZftzZbk=idZftzZbk;
        this.sl=sl;
    }

    public ZftzXmbzcs(){

            }

}
