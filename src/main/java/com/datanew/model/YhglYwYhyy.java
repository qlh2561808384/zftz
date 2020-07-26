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
@Table(name="YHGL_YW_YHYY")
public  class  YhglYwYhyy {

    //对应应用用户账号
    private String yhzh;

    //对应应用id(外键，YHGL_DM_YY的guid)
    private Long yyid;

    //对应用户id(外键，YHGL_YW_YH的guid)
    private Long yhid;

    //GUID
    private Long guid;

    //创建人
    private Long cjyhid;

    //对应操作单位id(多个已逗号隔开)
    private String dwids;

    //对应应用岗位id(多个已逗号隔开)
    private String gwid;

    //创建时间
    private Date cjsj;

    //状态（1启用，2停用）
    private String zt;
    
    //所属单位ID
    private String szdwid;
    
    //应用编码
    private String yybm;

    //所属用户密码
    private String yhmm;
    
    private String regicode;
    
   
    
    private Date clsj;
    
    private String sfcl;
    
    private String ssgkcs;
    
    public void setYhzh(String yhzh){

        this.yhzh=yhzh;
    }

    @Basic
    @Column(name="YHZH",length=50)
    public String getYhzh(){

        return this.yhzh;
    }

    public void setYyid(Long yyid){

        this.yyid=yyid;
    }

    @Basic
    @Column(name="YYID")
    public Long getYyid(){

        return this.yyid;
    }

    public void setYhid(Long yhid){

        this.yhid=yhid;
    }

    @Basic
    @Column(name="YHID")
    public Long getYhid(){

        return this.yhid;
    }

    public void setGuid(Long guid){

        this.guid=guid;
    }

    @Id
    @GeneratedValue(generator="sequence")
    @SequenceGenerator(name="sequence",sequenceName="SEQ_YHGL_YW_YHYY")
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

    public void setDwids(String dwids){

        this.dwids=dwids;
    }

    @Basic
    @Column(name="DWIDS",length=500)
    public String getDwids(){

        return this.dwids;
    }

    public void setGwid(String gwid){

        this.gwid=gwid;
    }

    @Basic
    @Column(name="GWID",length=500)
    public String getGwid(){

        return this.gwid;
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

    public void setZt(String zt){

        this.zt=zt;
    }

    @Basic
    @Column(name="ZT",length=2)
    public String getZt(){

        return this.zt;
    }
    
    
    
   

	public YhglYwYhyy(String yhzh,Long yyid,Long yhid,Long guid,Long cjyhid,String dwids,String gwid,Date cjsj,String zt,String szdwid,String yybm,String yhmm,String regicode,Date clsj,String sfcl,String ssgkcs){

        
        this.yhzh=yhzh;
        this.yyid=yyid;
        this.yhid=yhid;
        this.guid=guid;
        this.cjyhid=cjyhid;
        this.dwids=dwids;
        this.gwid=gwid;
        this.cjsj=cjsj;
        this.zt=zt;
        this.szdwid=szdwid;
        this.yybm=yybm;
        this.yhmm=yhmm;
        this.regicode=regicode;
        this.clsj=clsj;
        this.sfcl=sfcl;
        this.ssgkcs=ssgkcs;
    }

	@Basic
    @Column(name="SZDWID",length=500)
    public String getSzdwid() {
		return szdwid;
	}

	public void setSzdwid(String szdwid) {
		this.szdwid = szdwid;
	}
	
	
	public void setYybm(String yybm) {
		this.yybm = yybm;
	}

	@Basic
    @Column(name="YYBM",length=15)
	public String getYybm() {
		return yybm;
	}

	public void setSsyybm(String yybm) {
		this.yybm = yybm;
	}

	public YhglYwYhyy(){

            }

	@Basic
    @Column(name="YHMM",length=500)
	public String getYhmm() {
		return yhmm;
	}

	public void setYhmm(String yhmm) {
		this.yhmm = yhmm;
	}
	
	@Basic
    @Column(name="REGICODE",length=50)
	public String getRegicode() {
		return regicode;
	}

	public void setRegicode(String regicode) {
		this.regicode = regicode;
	}

	@Basic
    @Column(name="CLSJ")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",locale="zh",timezone="GMT+8")
	public Date getClsj() {
		return clsj;
	}

	@Basic
    @Column(name="SFCL")
	public void setClsj(Date clsj) {
		this.clsj = clsj;
	}

	public String getSfcl() {
		return sfcl;
	}

	public void setSfcl(String sfcl) {
		this.sfcl = sfcl;
	}

	@Basic
    @Column(name="SSGKCS")
	public String getSsgkcs() {
		return ssgkcs;
	}

	public void setSsgkcs(String ssgkcs) {
		this.ssgkcs = ssgkcs;
	}
	
	

}
