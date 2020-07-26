package com.datanew.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="XTGL_GG_FJ")
public  class  XtglGgFj {

    //GUID
    private Long guid;

    //创建用户id （外键，YHGL_YW_YHYY的guid）
    private Long cjyhid;

    //文件HASH值
    private String hashval;

    //附件来源(1预警监控--预警数据附件说明,2用户管理)
    private String ly;

    //附件类型
    private String lx;

    //对应来源业务id
    private String ywid;

    //创建时间
    private Date cjsj;

    //附件大小(kb)
    private Long dx;

    //附件名称
    private String mc;

    //附件真实存储名
    private String wjm;

    public void setGuid(Long guid){

        this.guid=guid;
    }

    @Id
    @GeneratedValue(generator="sequence")
    @SequenceGenerator(name="sequence",sequenceName="SEQ_XTGL_GG_FJ")
    @Basic
    @Column(name="GUID")
    public Long getGuid(){

        return this.guid;
    }

    public void setCjyhid(Long cjyhid){

        this.cjyhid=cjyhid;
    }

    @Basic
    @Column(name="CJYHID")
    public Long getCjyhid(){

        return this.cjyhid;
    }

    public void setHashval(String hashval){

        this.hashval=hashval;
    }

    @Basic
    @Column(name="HASHVAL",length=50)
    public String getHashval(){

        return this.hashval;
    }

    public void setLy(String ly){

        this.ly=ly;
    }

    @Basic
    @Column(name="LY",length=2)
    public String getLy(){

        return this.ly;
    }

    public void setLx(String lx){

        this.lx=lx;
    }

    @Basic
    @Column(name="LX",length=50)
    public String getLx(){

        return this.lx;
    }

    public void setYwid(String ywid){

        this.ywid=ywid;
    }

    @Basic
    @Column(name="YWID")
    public String getYwid(){

        return this.ywid;
    }

    public void setCjsj(Date cjsj){

        this.cjsj=cjsj;
    }

    @Basic
    @Column(name="CJSJ")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",locale="zh",timezone="GMT+8")
    public Date getCjsj(){

        return this.cjsj;
    }

    public void setDx(Long dx){

        this.dx=dx;
    }

    @Basic
    @Column(name="DX")
    public Long getDx(){

        return this.dx;
    }

    public void setMc(String mc){

        this.mc=mc;
    }

    @Basic
    @Column(name="MC",length=50)
    public String getMc(){

        return this.mc;
    }

    public void setWjm(String wjm){

        this.wjm=wjm;
    }

    @Basic
    @Column(name="WJM",length=100)
    public String getWjm(){

        return this.wjm;
    }

    public XtglGgFj(Long guid,Long cjyhid,String hashval,String ly,String lx,String ywid,Date cjsj,Long dx,String mc,String wjm){

        
        this.guid=guid;
        this.cjyhid=cjyhid;
        this.hashval=hashval;
        this.ly=ly;
        this.lx=lx;
        this.ywid=ywid;
        this.cjsj=cjsj;
        this.dx=dx;
        this.mc=mc;
        this.wjm=wjm;
    }

    public XtglGgFj(){

            }

}
