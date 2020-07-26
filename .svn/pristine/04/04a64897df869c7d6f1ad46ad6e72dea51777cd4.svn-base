package com.datanew.model;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="YHGL_GG_RYQZFB",schema="SXFX")
public  class  YhglGgRyqzfb {

    //主键
    private Long guid;

    //人员群组类型值
    private Long qzval;

    //YHGL_GG_RYQZ表主键
    private Long qzid;

    public void setGuid(Long guid){

        this.guid=guid;
    }

    @Id
    @GeneratedValue(generator="sequence")
    @SequenceGenerator(name="sequence",sequenceName="SEQ_YHGL_GG_RYQZFB")
    @Basic
    @Column(name="GUID")
    public Long getGuid(){

        return this.guid;
    }

    public void setQzval(Long qzval){

        this.qzval=qzval;
    }

    @Basic
    @Column(name="QZVAL")
    public Long getQzval(){

        return this.qzval;
    }

    public void setQzid(Long qzid){

        this.qzid=qzid;
    }

    @Basic
    @Column(name="QZID")
    public Long getQzid(){

        return this.qzid;
    }

    public YhglGgRyqzfb(Long guid,Long qzval,Long qzid){

        
        this.guid=guid;
        this.qzval=qzval;
        this.qzid=qzid;
    }

    public YhglGgRyqzfb(){

            }

}
