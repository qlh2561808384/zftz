package com.datanew.model;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="XTGL_YYGW_TOMENU")
public  class  XtglYygwTomenu {

    //主键
    private Long guid;

    //岗位ID
    private String gwid;

    //菜单ID
    private String menyid;

    public void setGuid(Long guid){

        this.guid=guid;
    }

    @Id
    @GeneratedValue(generator="sequence")
    @SequenceGenerator(name="sequence",sequenceName="SEQ_XTGL_YYGW_TOMENU")
    @Basic
    @Column(name="GUID")
    public Long getGuid(){

        return this.guid;
    }

    public void setGwid(String gwid){

        this.gwid=gwid;
    }

    @Basic
    @Column(name="GWID",length=20)
    public String getGwid(){

        return this.gwid;
    }

    public void setMenyid(String menyid){

        this.menyid=menyid;
    }

    @Basic
    @Column(name="MENYID",length=20)
    public String getMenyid(){

        return this.menyid;
    }

    public XtglYygwTomenu(Long guid,String gwid,String menyid){

        
        this.guid=guid;
        this.gwid=gwid;
        this.menyid=menyid;
    }

    public XtglYygwTomenu(){

            }

}
