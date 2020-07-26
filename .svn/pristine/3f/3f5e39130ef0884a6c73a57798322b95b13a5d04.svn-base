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
@Table(name="YHGL_DM_YYGW")
public  class  YhglDmYygw {

    //GUID
    private Long guid;

    //创建用户id（外键，YHGL_YW_YHYY的guid）
    private Long cjyhid;

    //所属应用id（外键，YHGL_DM_YY的guid）
    private Long ssyyid;

    //岗位类型(1财政，2单位)
    private String gwlx;

    //创建时间
    private Date cjsj;

    //岗位名称
    private String mc;

    //上级岗位ID
    private Long sjgwid;

    //状态(0为停用，1为可用，2为假删)
    private String zt;

    //岗位说明
    private String ms;

    //岗位编码
    private String bm;
    
    private String ssyybm;

    public void setGuid(Long guid){

        this.guid=guid;
    }

    @Id
    @GeneratedValue(generator="sequence")
    @SequenceGenerator(name="sequence",sequenceName="SEQ_YHGL_DM_YYGW")
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

    public void setSsyyid(Long ssyyid){

        this.ssyyid=ssyyid;
    }

    @Basic
    @Column(name="SSYYID")
    public Long getSsyyid(){

        return this.ssyyid;
    }

    public void setGwlx(String gwlx){

        this.gwlx=gwlx;
    }

    @Basic
    @Column(name="GWLX",length=2)
    public String getGwlx(){

        return this.gwlx;
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

    public void setSjgwid(Long sjgwid){

        this.sjgwid=sjgwid;
    }

    @Basic
    @Column(name="SJGWID")
    public Long getSjgwid(){

        return this.sjgwid;
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

    
    
    @Basic
    @Column(name="SSYYBM",length=20)
    public String getSsyybm() {
		return ssyybm;
	}

	public void setSsyybm(String ssyybm) {
		this.ssyybm = ssyybm;
	}

	public YhglDmYygw(Long guid,Long cjyhid,Long ssyyid,String gwlx,Date cjsj,String mc,Long sjgwid,String zt,String ms,String bm,String ssyybm){

        
        this.guid=guid;
        this.cjyhid=cjyhid;
        this.ssyyid=ssyyid;
        this.gwlx=gwlx;
        this.cjsj=cjsj;
        this.mc=mc;
        this.sjgwid=sjgwid;
        this.zt=zt;
        this.ms=ms;
        this.bm=bm;
        this.ssyybm=ssyybm;
    }

    public YhglDmYygw(){

            }

}
