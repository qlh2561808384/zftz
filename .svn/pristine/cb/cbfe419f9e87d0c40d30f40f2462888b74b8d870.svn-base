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
@Table(name="XTGL_DM_YY")
public  class  XtglDmYy {

    //GUID
    private Long guid;

    //通知方式（0无需通知，1存储过程，2webservice，3http）
    private String tzfs;

    //通知方式对应的值
    private String tzfsz;

    //应用说明
    private String ms;

    //当前流程版本
    private Long dqbb;

    //创建用户id （外键，YHGL_YW_YHYY的guid）
    private Long cjyhid;

    //系统URL地址
    private String xturl;

    //管理处室（应用处室） (外键，YHGL_DM_DW的guid)
    private Long dwid;

    //组织架构对应应用编码（如果是基于门户的，则此值为门户的应用编码）
    private String zzjgyybm;

    //创建时间
    private Date cjsj;

    //应用名称
    private String mc;

    //注销短信通知模板ID(外键，XTGL_DM_DXMB的guid)
    private Long zxdxtzmb;

    //新增短信通知模板ID(外键，XTGL_DM_DXMB的guid)
    private Long xzdxtzmb;

    //应用编码
    private String bm;

    //状态(0为停用，1为可用，2为假删)
    private String zt;
    
    private String scgz;
    
    private String sjkurl;
    
    private String sjkzh;
    
    private String sjkmm;

    //变更短信通知模板ID(外键，XTGL_DM_DXMB的guid)
    private Long bgdxtzmb;
    
    private Long fzr;
    
    private String lx;

    public void setGuid(Long guid){

        this.guid=guid;
    }

    @Id
    @GeneratedValue(generator="sequence")
    @SequenceGenerator(name="sequence",sequenceName="SEQ_XTGL_DM_YY")
    @Basic
    @Column(name="GUID")
    public Long getGuid(){

        return this.guid;
    }

    public void setTzfs(String tzfs){

        this.tzfs=tzfs;
    }

    @Basic
    @Column(name="TZFS",length=2)
    public String getTzfs(){

        return this.tzfs;
    }

    public void setTzfsz(String tzfsz){

        this.tzfsz=tzfsz;
    }

    @Basic
    @Column(name="TZFSZ",length=100)
    public String getTzfsz(){

        return this.tzfsz;
    }

    public void setMs(String ms){

        this.ms=ms;
    }

    @Basic
    @Column(name="MS",length=500)
    public String getMs(){

        return this.ms;
    }

    public void setDqbb(Long dqbb){

        this.dqbb=dqbb;
    }

    @Basic
    @Column(name="DQBB")
    public Long getDqbb(){

        return this.dqbb;
    }

    public void setCjyhid(Long cjyhid){

        this.cjyhid=cjyhid;
    }

    @Basic
    @Column(name="CJYHID")
    public Long getCjyhid(){

        return this.cjyhid;
    }

    public void setXturl(String xturl){

        this.xturl=xturl;
    }

    @Basic
    @Column(name="XTURL",length=500)
    public String getXturl(){

        return this.xturl;
    }

    public void setDwid(Long dwid){

        this.dwid=dwid;
    }

    @Basic
    @Column(name="DWID")
    public Long getDwid(){

        return this.dwid;
    }

    public void setZzjgyybm(String zzjgyybm){

        this.zzjgyybm=zzjgyybm;
    }

    @Basic
    @Column(name="ZZJGYYBM",length=15)
    public String getZzjgyybm(){

        return this.zzjgyybm;
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

    public void setZxdxtzmb(Long zxdxtzmb){

        this.zxdxtzmb=zxdxtzmb;
    }

    @Basic
    @Column(name="ZXDXTZMB")
    public Long getZxdxtzmb(){

        return this.zxdxtzmb;
    }

    public void setXzdxtzmb(Long xzdxtzmb){

        this.xzdxtzmb=xzdxtzmb;
    }

    @Basic
    @Column(name="XZDXTZMB")
    public Long getXzdxtzmb(){

        return this.xzdxtzmb;
    }

    public void setBm(String bm){

        this.bm=bm;
    }

    @Basic
    @Column(name="BM",length=15)
    public String getBm(){

        return this.bm;
    }

    public void setZt(String zt){

        this.zt=zt;
    }

    @Basic
    @Column(name="ZT",length=2)
    public String getZt(){

        return this.zt;
    }

    public void setBgdxtzmb(Long bgdxtzmb){

        this.bgdxtzmb=bgdxtzmb;
    }

    @Basic
    @Column(name="BGDXTZMB")
    public Long getBgdxtzmb(){

        return this.bgdxtzmb;
    }
    
    
    @Basic
    @Column(name="SCGZ",length=2)
    public String getScgz() {
		return scgz;
	}

	public void setScgz(String scgz) {
		this.scgz = scgz;
	}

	@Basic
    @Column(name="SJKURL",length=500)
	public String getSjkurl() {
		return sjkurl;
	}

	public void setSjkurl(String sjkurl) {
		this.sjkurl = sjkurl;
	}

	@Basic
    @Column(name="SJKZH",length=100)
	public String getSjkzh() {
		return sjkzh;
	}

	public void setSjkzh(String sjkzh) {
		this.sjkzh = sjkzh;
	}

	@Basic
    @Column(name="SJKMM",length=100)
	public String getSjkmm() {
		return sjkmm;
	}

	public void setSjkmm(String sjkmm) {
		this.sjkmm = sjkmm;
	}

	public XtglDmYy(Long guid,String tzfs,String tzfsz,String ms,Long dqbb,Long cjyhid,String xturl,Long dwid,String zzjgyybm,Date cjsj,String mc,Long zxdxtzmb,Long xzdxtzmb,String bm,String zt,Long bgdxtzmb,String scgz,String sjkurl,String sjkzh,String sjkmm,Long fzr,String lx){

        
        this.guid=guid;
        this.tzfs=tzfs;
        this.tzfsz=tzfsz;
        this.ms=ms;
        this.dqbb=dqbb;
        this.cjyhid=cjyhid;
        this.xturl=xturl;
        this.dwid=dwid;
        this.zzjgyybm=zzjgyybm;
        this.cjsj=cjsj;
        this.mc=mc;
        this.zxdxtzmb=zxdxtzmb;
        this.xzdxtzmb=xzdxtzmb;
        this.bm=bm;
        this.zt=zt;
        this.bgdxtzmb=bgdxtzmb;
        this.scgz=scgz;
        this.sjkurl=sjkurl;
        this.sjkzh=sjkzh;
        this.sjkmm=sjkmm;
        this.fzr=fzr;
        this.lx=lx;
    }

    public XtglDmYy(){

            }

    @Basic
    @Column(name="FZR")
	public Long getFzr() {
		return fzr;
	}

	public void setFzr(Long fzr) {
		this.fzr = fzr;
	}

	 @Basic
	 @Column(name="LX")
	public String getLx() {
		return lx;
	}

	public void setLx(String lx) {
		this.lx = lx;
	}
    
    

}
