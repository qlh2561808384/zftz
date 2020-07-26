package com.datanew.model;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="ZFTZ_GWTOGK")
public  class  ZftzGwtogk {

    private Long guid;

    //岗位ID
    private Long gwid;

    //归口ID
    private Long gkid;

    public void setGuid(Long guid){

        this.guid=guid;
    }

    
    @Id
    @GeneratedValue(generator="sequence")
    @SequenceGenerator(name="sequence",sequenceName="SEQ_ZFTZ_GWTOGK")
    @Basic
    @Column(name="GUID")
    public Long getGuid(){

        return this.guid;
    }

    public void setGwid(Long gwid){

        this.gwid=gwid;
    }

    @Basic
    @Column(name="GWID")
    public Long getGwid(){

        return this.gwid;
    }

    public void setGkid(Long gkid){

        this.gkid=gkid;
    }

    @Basic
    @Column(name="GKID")
    public Long getGkid(){

        return this.gkid;
    }

    public ZftzGwtogk(Long guid,Long gwid,Long gkid){

        
        this.guid=guid;
        this.gwid=gwid;
        this.gkid=gkid;
    }

    public ZftzGwtogk(){

            }

}
