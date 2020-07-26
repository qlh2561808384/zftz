package com.datanew.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="fbyx_fj")
//
public  class XtFj {

    private Long id;

    private String mc;

    private String lx;

    private String wjm;

    private Long zyid;

    private Long yhid;

    private Date cjsj;

    private String hashval;

    private Long dx;

    @Id
    @GeneratedValue(generator="sequence")
    @SequenceGenerator(name="sequence",sequenceName="SEQ_FBYX_FJ")
    @Basic
    @Column(name="id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name="mc")
    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc;
    }

    @Basic
    @Column(name="lx")
    public String getLx() {
        return lx;
    }

    public void setLx(String lx) {
        this.lx = lx;
    }

    @Basic
    @Column(name="wjm")
    public String getWjm() {
        return wjm;
    }

    public void setWjm(String wjm) {
        this.wjm = wjm;
    }

    @Basic
    @Column(name="zyid")
    public Long getZyid() {
        return zyid;
    }

    public void setZyid(Long zyid) {
        this.zyid = zyid;
    }

    @Basic
    @Column(name="yhid")
    public Long getYhid() {
        return yhid;
    }

    public void setYhid(Long yhid) {
        this.yhid = yhid;
    }

    @Basic
    @Column(name="cjsj")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",locale="zh",timezone="GMT+8")
    public Date getCjsj() {
        return cjsj;
    }

    public void setCjsj(Date cjsj) {
        this.cjsj = cjsj;
    }

    @Basic
    @Column(name="hashval")
    public String getHashval() {
        return hashval;
    }

    public void setHashval(String hashval) {
        this.hashval = hashval;
    }

    @Basic
    @Column(name="dx")
    public Long getDx() {
        return dx;
    }

    public void setDx(Long dx) {
        this.dx = dx;
    }
}
