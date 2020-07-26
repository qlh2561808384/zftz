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
@Table(name="YHGL_DM_DW")
public  class  YhglDmDw {

    //联系人
    private String lxr;

    //GUID
    private Long guid;

    //创建人
    private Long cjyhid;

    //联系电话
    private String lxdh;

    //类型（1预算单位，2财政）
    private String lx;

    //排序号
    private Integer pxh;

    //主管部门ID   （本表的外键，guid）
    private Long zgbmid;

    //创建时间
    private Date cjsj;

    //单位名称
    private String mc;

    //地址
    private String dz;

    //状态(0为停用，1为可用，2为假删)
    private String zt;

    //单位说明
    private String ms;

    //单位编码
    private String bm;

    public void setLxr(String lxr){

        this.lxr=lxr;
    }

    @Basic
    @Column(name="LXR",length=20)
    public String getLxr(){

        return this.lxr;
    }

    public void setGuid(Long guid){

        this.guid=guid;
    }

    @Id
    @GeneratedValue(generator="sequence")
    @SequenceGenerator(name="sequence",sequenceName="SEQ_YHGL_DM_DW")
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

    public void setLxdh(String lxdh){

        this.lxdh=lxdh;
    }

    @Basic
    @Column(name="LXDH",length=20)
    public String getLxdh(){

        return this.lxdh;
    }

    public void setLx(String lx){

        this.lx=lx;
    }

    @Basic
    @Column(name="LX",length=2)
    public String getLx(){

        return this.lx;
    }

    public void setPxh(Integer pxh){

        this.pxh=pxh;
    }

    @Basic
    @Column(name="PXH")
    public Integer getPxh(){

        return this.pxh;
    }

    public void setZgbmid(Long zgbmid){

        this.zgbmid=zgbmid;
    }

    @Basic
    @Column(name="ZGBMID")
    public Long getZgbmid(){

        return this.zgbmid;
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

    public void setMc(String mc){

        this.mc=mc;
    }

    @Basic
    @Column(name="MC",length=100)
    public String getMc(){

        return this.mc;
    }

    public void setDz(String dz){

        this.dz=dz;
    }

    @Basic
    @Column(name="DZ",length=200)
    public String getDz(){

        return this.dz;
    }

    public void setZt(String zt){

        this.zt=zt;
    }

    @Basic
    @Column(name="ZT",length=2)
    public String getZt(){

        return this.zt;
    }

    public void setMs(String ms){

        this.ms=ms;
    }

    @Basic
    @Column(name="MS",length=500)
    public String getMs(){

        return this.ms;
    }

    public void setBm(String bm){

        this.bm=bm;
    }

    @Basic
    @Column(name="BM",length=15)
    public String getBm(){

        return this.bm;
    }

    public YhglDmDw(String lxr,Long guid,Long cjyhid,String lxdh,String lx,Integer pxh,Long zgbmid,Date cjsj,String mc,String dz,String zt,String ms,String bm){

        
        this.lxr=lxr;
        this.guid=guid;
        this.cjyhid=cjyhid;
        this.lxdh=lxdh;
        this.lx=lx;
        this.pxh=pxh;
        this.zgbmid=zgbmid;
        this.cjsj=cjsj;
        this.mc=mc;
        this.dz=dz;
        this.zt=zt;
        this.ms=ms;
        this.bm=bm;
    }

    public YhglDmDw(){

            }

}
