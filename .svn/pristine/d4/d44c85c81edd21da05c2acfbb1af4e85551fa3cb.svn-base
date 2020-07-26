package com.datanew.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;
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
@Table(name="ZFTZ_SPLC")
public  class  ZftzSplc {

    //GUID
    private Long guid;

    //事项编码（zftz_xxb的bm）
    private String  sxbm;

    //当前环节编码
    private String dqhjbm;

    //下一环节编码
    private String xyhjbm;

    //当前环节名称
    private String dqhjmc;

    //操作类型（1提交，2退回,3不同意），多选已逗号分开
    private String czlx;

    //审批人分组id (外键，YHGL_DM_SPRFZ的guid)
    private Long sprfzid;

    //操作时间
    private Date cjsj;

    public void setGuid(Long guid){

        this.guid=guid;
    }

    @Id
    @GeneratedValue(generator="sequence")
    @SequenceGenerator(name="sequence",sequenceName="SEQ_ZFTZ_SPLC")
    @Basic
    @Column(name="GUID")
    public Long getGuid(){

        return this.guid;
    }

    
    @Basic
    @Column(name="SXBM")
    public String getSxbm() {
		return sxbm;
	}

	public void setSxbm(String sxbm) {
		this.sxbm = sxbm;
	}

	public void setDqhjbm(String dqhjbm){

        this.dqhjbm=dqhjbm;
    }

    @Basic
    @Column(name="DQHJBM",length=15)
    public String getDqhjbm(){

        return this.dqhjbm;
    }

    public void setXyhjbm(String xyhjbm){

        this.xyhjbm=xyhjbm;
    }

    @Basic
    @Column(name="XYHJBM",length=15)
    public String getXyhjbm(){

        return this.xyhjbm;
    }

    public void setDqhjmc(String dqhjmc){

        this.dqhjmc=dqhjmc;
    }

    @Basic
    @Column(name="DQHJMC",length=500)
    public String getDqhjmc(){

        return this.dqhjmc;
    }

    public void setCzlx(String czlx){

        this.czlx=czlx;
    }

    @Basic
    @Column(name="CZLX",length=20)
    public String getCzlx(){

        return this.czlx;
    }

    public void setSprfzid(Long sprfzid){

        this.sprfzid=sprfzid;
    }

    @Basic
    @Column(name="SPRFZID")
    public Long getSprfzid(){

        return this.sprfzid;
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

    public ZftzSplc(Long guid,String sxbm,String dqhjbm,String xyhjbm,String dqhjmc,String czlx,Long sprfzid,Date cjsj){

        
        this.guid=guid;
        this.sxbm=sxbm;
        this.dqhjbm=dqhjbm;
        this.xyhjbm=xyhjbm;
        this.dqhjmc=dqhjmc;
        this.czlx=czlx;
        this.sprfzid=sprfzid;
        this.cjsj=cjsj;
    }

    public ZftzSplc(){

            }

}
