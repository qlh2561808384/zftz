package com.datanew.model;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="YHGL_YW_YH")
public  class  YhglYwYh {

    //用户所属单位
    private Long yhszdw;

    //GUID
    private Long guid;

    //手机号码
    private String ydhm;

    //人员性质（1行政，2事业，3企业，9其他）
    private String ryxz;

    //人员类型(1在职)
    private String rylx;

    //登录名
    private String dlm;

    //证件号码
    private String zjhm;

    //职称
    private String zc;

    //姓名
    private String xm;

    //联系地址
    private String lxdz;

    //职务
    private String zw;

    //用户类型（1单位用户，2财政用户）
    private String yhlx;

    //办公电话
    private String bgdh;

    //状态（1启用，2停用）
    private String zt;
    
    
    
  //ca
    private String ca;
    
    private String ssgkcs;
    
    private String regicode;

    
    private String email;
    
    public void setYhszdw(Long yhszdw){

        this.yhszdw=yhszdw;
    }

    @Basic
    @Column(name="YHSZDW")
    public Long getYhszdw(){

        return this.yhszdw;
    }

    public void setGuid(Long guid){

        this.guid=guid;
    }

    @Id
    @GeneratedValue(generator="sequence")
    @SequenceGenerator(name="sequence",sequenceName="SEQ_YHGL_YW_YH")
    @Basic
    @Column(name="GUID")
    public Long getGuid(){

        return this.guid;
    }

    public void setYdhm(String ydhm){

        this.ydhm=ydhm;
    }

    @Basic
    @Column(name="YDHM",length=12)
    public String getYdhm(){

        return this.ydhm;
    }

    public void setRyxz(String ryxz){

        this.ryxz=ryxz;
    }

    @Basic
    @Column(name="RYXZ",length=2)
    public String getRyxz(){

        return this.ryxz;
    }

    public void setRylx(String rylx){

        this.rylx=rylx;
    }

    @Basic
    @Column(name="RYLX",length=2)
    public String getRylx(){

        return this.rylx;
    }

    public void setDlm(String dlm){

        this.dlm=dlm;
    }

    @Basic
    @Column(name="DLM",length=50)
    public String getDlm(){

        return this.dlm;
    }

    public void setZjhm(String zjhm){

        this.zjhm=zjhm;
    }

    @Basic
    @Column(name="ZJHM",length=18)
    public String getZjhm(){

        return this.zjhm;
    }

    public void setZc(String zc){

        this.zc=zc;
    }

    @Basic
    @Column(name="ZC",length=50)
    public String getZc(){

        return this.zc;
    }

    public void setXm(String xm){

        this.xm=xm;
    }

    @Basic
    @Column(name="XM",length=20)
    public String getXm(){

        return this.xm;
    }

    public void setLxdz(String lxdz){

        this.lxdz=lxdz;
    }

    @Basic
    @Column(name="LXDZ",length=100)
    public String getLxdz(){

        return this.lxdz;
    }

    public void setZw(String zw){

        this.zw=zw;
    }

    @Basic
    @Column(name="ZW",length=50)
    public String getZw(){

        return this.zw;
    }

    public void setYhlx(String yhlx){

        this.yhlx=yhlx;
    }

    @Basic
    @Column(name="YHLX",length=2)
    public String getYhlx(){

        return this.yhlx;
    }

    public void setBgdh(String bgdh){

        this.bgdh=bgdh;
    }

    @Basic
    @Column(name="BGDH",length=12)
    public String getBgdh(){

        return this.bgdh;
    }

    public void setZt(String zt){

        this.zt=zt;
    }

    @Basic
    @Column(name="ZT",length=2)
    public String getZt(){

        return this.zt;
    }
    
    

   
	
	
	@Basic
    @Column(name="CA",length=2)
	public String getCa() {
		return ca;
	}

	public void setCa(String ca) {
		this.ca = ca;
	}

	
	@Basic
    @Column(name="SSGKCS",length=50)
	public String getSsgkcs() {
		return ssgkcs;
	}

	public void setSsgkcs(String ssgkcs) {
		this.ssgkcs = ssgkcs;
	}

	public YhglYwYh(Long yhszdw,Long guid,String ydhm,String ryxz,String rylx,String dlm,String zjhm,String zc,String xm,String lxdz,String zw,String yhlx,String bgdh,String zt,String ca,String ssgkcs,String regicode,String email){

        
        this.yhszdw=yhszdw;
        this.guid=guid;
        this.ydhm=ydhm;
        this.ryxz=ryxz;
        this.rylx=rylx;
        this.dlm=dlm;
        this.zjhm=zjhm;
        this.zc=zc;
        this.xm=xm;
        this.lxdz=lxdz;
        this.zw=zw;
        this.yhlx=yhlx;
        this.bgdh=bgdh;
        this.zt=zt;
       
        this.ca=ca;
        this.ssgkcs=ssgkcs;
        this.regicode=regicode;
        this.email=email;
    }

    public YhglYwYh(){

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
    @Column(name="EMAIL",length=50)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
