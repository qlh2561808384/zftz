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
@Table(name="YHGL_GG_RYQZ")
public  class  YhglGgRyqz {

    //GUID
    private Long guid;

    //创建用户id （外键，YHGL_YW_YHYY的guid）
    private Long cjyhid;

    //所属应用id（外键，YHGL_DM_YY的guid）
    private String ssyybm;

    //分组类型对应值，根据分组类型设置值 (1时单位id,2时角色id,3时外键，YHGL_DM_SPRFZ的guid，4用户id)
    private String fzlxval;

    //分组划分类型(1单位，2角色，3分组,4指定用户)
    private String fzlx;

    //创建时间
    private Date cjsj;

    //分组名称
    private String mc;

    //状态(0为停用，1为可用，2为假删)
    private String zt;

    //分组说明
    private String ms;
    
    private Long ssyyid;
    
    private Long pid;
    
    private String regicode;
    
    private String fzlxsql;

    public void setGuid(Long guid){

        this.guid=guid;
    }

    @Id
    @GeneratedValue(generator="sequence")
    @SequenceGenerator(name="sequence",sequenceName="SEQ_YHGL_GG_RYQZ")
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


    
    

    public void setFzlxval(String fzlxval){

        this.fzlxval=fzlxval;
    }

    @Basic
    @Column(name="SSYYBM")
    public String getSsyybm() {
		return ssyybm;
	}

	public void setSsyybm(String ssyybm) {
		this.ssyybm = ssyybm;
	}

	
    
    public String getFzlxval(){

        return this.fzlxval;
    }

    public void setFzlx(String fzlx){

        this.fzlx=fzlx;
    }

    @Basic
    @Column(name="FZLX",length=2)
    public String getFzlx(){

        return this.fzlx;
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
    @Column(name="MC",length=50)
    public String getMc(){

        return this.mc;
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

    public YhglGgRyqz(Long guid,Long cjyhid,String ssyybm,String fzlxval,String fzlx,Date cjsj,String mc,String zt,String ms,Long ssyyid,Long pid,String regicode,String fzlxsql){

        
        this.guid=guid;
        this.cjyhid=cjyhid;
        this.ssyybm=ssyybm;
        this.fzlxval=fzlxval;
        this.fzlx=fzlx;
        this.cjsj=cjsj;
        this.mc=mc;
        this.zt=zt;
        this.ms=ms;
        this.ssyyid=ssyyid;
        this.pid=pid;
        this.regicode=regicode;
        this.fzlxsql=fzlxsql;
       
    }

    public YhglGgRyqz(){

            }

    @Basic
    @Column(name="SSYYID")
	public Long getSsyyid() {
		return ssyyid;
	}

	public void setSsyyid(Long ssyyid) {
		this.ssyyid = ssyyid;
	}

	@Basic
	@Column(name="PID")
	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
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
    @Column(name="FZLXSQL",length=50)
	public String getFzlxsql() {
		return fzlxsql;
	}

	public void setFzlxsql(String fzlxsql) {
		this.fzlxsql = fzlxsql;
	}
	
	

    
}
