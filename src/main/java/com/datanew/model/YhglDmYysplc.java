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
@Table(name="YHGL_DM_YYSPLC")
public  class  YhglDmYysplc {

    //当前版本
    private Long dqbb;

    //是否自动审核(0为否，1为是)
    private String sfzd;

    //GUID
    private Long guid;

    //下一环节编码
    private String xyhjbm;

    //当前环节编码
    private String dqhjbm;

    //所属应用id（外键，YHGL_DM_YY的guid）
    private Long ssyyid;

    //操作类型（1提交，2退回,3不同意），多选已逗号分开
    private String czlx;

    //审批人分组id (外键，YHGL_DM_SPRFZ的guid)
    private Long sprfzid;

    //当前环节名称
    private String dqhjmc;

    //新增时间
    private Date cjsj;
    //流程类型
    private String type;

    public void setDqbb(Long dqbb){

        this.dqbb=dqbb;
    }

    @Basic
    @Column(name="DQBB")
    public Long getDqbb(){

        return this.dqbb;
    }

    public void setSfzd(String sfzd){

        this.sfzd=sfzd;
    }

    @Basic
    @Column(name="SFZD",length=2)
    public String getSfzd(){

        return this.sfzd;
    }

    public void setGuid(Long guid){

        this.guid=guid;
    }

    @Id
    @GeneratedValue(generator="sequence")
    @SequenceGenerator(name="sequence",sequenceName="SEQ_YHGL_DM_YYSPLC")
    @Basic
    @Column(name="GUID")
    public Long getGuid(){

        return this.guid;
    }

    public void setXyhjbm(String xyhjbm){

        this.xyhjbm=xyhjbm;
    }

    @Basic
    @Column(name="XYHJBM",length=15)
    public String getXyhjbm(){

        return this.xyhjbm;
    }

    public void setDqhjbm(String dqhjbm){

        this.dqhjbm=dqhjbm;
    }

    @Basic
    @Column(name="DQHJBM",length=15)
    public String getDqhjbm(){

        return this.dqhjbm;
    }

    public void setSsyyid(Long ssyyid){

        this.ssyyid=ssyyid;
    }

    @Basic
    @Column(name="SSYYID")
    public Long getSsyyid(){

        return this.ssyyid;
    }

    public void setCzlx(String czlx){

        this.czlx=czlx;
    }

    @Basic
    @Column(name="CZLX",length=10)
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

    public void setDqhjmc(String dqhjmc){

        this.dqhjmc=dqhjmc;
    }

    @Basic
    @Column(name="DQHJMC",length=500)
    public String getDqhjmc(){

        return this.dqhjmc;
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
    
    
    @Basic
    @Column(name="TYPE",length=2)
    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public YhglDmYysplc(Long dqbb,String sfzd,Long guid,String xyhjbm,String dqhjbm,Long ssyyid,String czlx,Long sprfzid,String dqhjmc,Date cjsj,String type){

        
        this.dqbb=dqbb;
        this.sfzd=sfzd;
        this.guid=guid;
        this.xyhjbm=xyhjbm;
        this.dqhjbm=dqhjbm;
        this.ssyyid=ssyyid;
        this.czlx=czlx;
        this.sprfzid=sprfzid;
        this.dqhjmc=dqhjmc;
        this.cjsj=cjsj;
        this.type=type;
    }

    public YhglDmYysplc(){

            }

}
