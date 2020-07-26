package com.datanew.model;


import javax.persistence.*;

@Entity
@Table(name="ZFTZ_ZJJG")
public  class  ZftzZjjg {

    private Long id;

    private String jgbm;

    private String jgmc;

    private Integer jgfl;

    private String jgzz;

    public void setId(Long id){

        this.id=id;
    }

    @Id
    @GeneratedValue(generator="sequence")
    @SequenceGenerator(name="sequence",sequenceName="SEQ_ZFTZ_ZJJG")
    @Basic
    @Column(name="ID")
    public Long getId(){

        return this.id;
    }

    public void setJgbm(String jgbm){

        this.jgbm=jgbm;
    }

    @Basic
    @Column(name="JGBM",length=20)
    public String getJgbm(){

        return this.jgbm;
    }

    public void setJgmc(String jgmc){

        this.jgmc=jgmc;
    }

    @Basic
    @Column(name="JGMC",length=60)
    public String getJgmc(){

        return this.jgmc;
    }

    public void setJgfl(Integer jgfl){

        this.jgfl=jgfl;
    }

    @Basic
    @Column(name="JGFL")
    public Integer getJgfl(){

        return this.jgfl;
    }

    public void setJgzz(String jgzz){

        this.jgzz=jgzz;
    }

    @Basic
    @Column(name="JGZZ",length=200)
    public String getJgzz(){

        return this.jgzz;
    }

    public ZftzZjjg(Long id,String jgbm,String jgmc,Integer jgfl,String jgzz){

        
        this.id=id;
        this.jgbm=jgbm;
        this.jgmc=jgmc;
        this.jgfl=jgfl;
        this.jgzz=jgzz;
    }

    public ZftzZjjg(){

            }

}
