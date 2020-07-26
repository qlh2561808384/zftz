package com.datanew.model;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="YHGL_YW_YHYYDWFB",schema="SXFX")
public  class  YhglYwYhyydwfb {

	private Long guid;
    //对应预算单位id
    private Long dwid;

    //YHGL_YW_YHYYguid
    private Long yhyyid;

    public void setDwid(Long dwid){

        this.dwid=dwid;
    }

    @Basic
    @Column(name="DWID")
    public Long getDwid(){

        return this.dwid;
    }

    public void setYhyyid(Long yhyyid){

        this.yhyyid=yhyyid;
    }

    @Basic
    @Column(name="YHYYID")
    public Long getYhyyid(){

        return this.yhyyid;
    }

    
    @Id
    @GeneratedValue(generator="sequence")
    @SequenceGenerator(name="sequence",sequenceName="SEQ_YHGL_YW_YHYYDWFB")
    @Basic 
    @Column(name="GUID")
    public Long getGuid() {
		return guid;
	}

	public void setGuid(Long guid) {
		this.guid = guid;
	}

	public YhglYwYhyydwfb(Long guid,Long dwid,Long yhyyid){

    	this.guid=guid;
        this.dwid=dwid;
        this.yhyyid=yhyyid;
    }

    public YhglYwYhyydwfb(){

            }

}
