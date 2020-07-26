package com.datanew.model;


import javax.persistence.*;

@Entity
@Table(name="fbyx_ptgs")
//
public  class Ptgs {

    private Long id;

    private String bm;

    private String mc;

    private String zt;

    private String bz;

    private String parentcode;



    @Id
    @GeneratedValue(generator="sequence")
    @SequenceGenerator(name="sequence",sequenceName="SEQ_FBYX_PTGS")
    @Basic
    @Column(name="id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name="bm")
    public String getBm() {
        return bm;
    }

    public void setBm(String bm) {
        this.bm = bm;
    }

    @Basic
    @Column(name="mc")
    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc;
    }

    @Basic
    @Column(name="zt")
    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    @Basic
    @Column(name="bz")
    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    @Transient
    public String getParentcode() {
        return parentcode;
    }

    public void setParentcode(String parentcode) {
        this.parentcode = parentcode;
    }

    public Ptgs() {

    }

    public Ptgs(Long id, String bm, String mc, String zt, String bz, String parentcode) {
        this.id = id;
        this.bm = bm;
        this.mc = mc;
        this.zt = zt;
        this.bz = bz;
        this.parentcode = parentcode;
    }
}
