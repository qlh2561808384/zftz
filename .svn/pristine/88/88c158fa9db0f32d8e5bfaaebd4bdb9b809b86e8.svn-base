package com.datanew.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "ZFTZ_SHJL")
public class ZftzShjl {

    //GUID
    private Long guid;

    //项目ID
    private Long xmid;

    private String dqhjbm;

    //前一条审核记录GUID
    private Long qshjlid;

    //处理人id（外键，YHGL_YW_YHYY的guid）
    private Long clyhid;

    //处理意见
    private String clyj;

    //处理时间
    private Date clsj = new Date();

    //操作类型(1提交，2退回，3不同意)
    private String czlx;

    private String syhjbm;

    private String sxlx;

    private Date ddrq;


    public void setGuid(Long guid) {

        this.guid = guid;
    }

    @Id
    @GeneratedValue(generator="sequence")
    @SequenceGenerator(name="sequence",sequenceName="SEQ_ZFTZ_SHJL")
    @Basic
    @Column(name = "GUID")
    public Long getGuid() {

        return this.guid;
    }

    public void setXmid(Long xmid) {

        this.xmid = xmid;
    }

    @Basic
    @Column(name = "XMID")
    public Long getXmid() {

        return this.xmid;
    }

    public void setDqhjbm(String dqhjbm) {

        this.dqhjbm = dqhjbm;
    }

    @Basic
    @Column(name = "DQHJBM", length = 10)
    public String getDqhjbm() {

        return this.dqhjbm;
    }

    public void setQshjlid(Long qshjlid) {

        this.qshjlid = qshjlid;
    }

    @Basic
    @Column(name = "QSHJLID")
    public Long getQshjlid() {

        return this.qshjlid;
    }

    public void setClyhid(Long clyhid) {

        this.clyhid = clyhid;
    }

    @Basic
    @Column(name = "CLYHID")
    public Long getClyhid() {

        return this.clyhid;
    }

    public void setClyj(String clyj) {

        this.clyj = clyj;
    }

    @Basic
    @Column(name = "CLYJ", length = 500)
    public String getClyj() {

        return this.clyj;
    }

    public void setClsj(Date clsj) {

        this.clsj = clsj;
    }

    @Basic
    @Column(name = "CLSJ")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    public Date getClsj() {

        return this.clsj;
    }

    public void setCzlx(String czlx) {

        this.czlx = czlx;
    }

    @Basic
    @Column(name = "CZLX", length = 2)
    public String getCzlx() {

        return this.czlx;
    }

    public void setSyhjbm(String syhjbm) {

        this.syhjbm = syhjbm;
    }

    @Basic
    @Column(name = "SYHJBM", length = 10)
    public String getSyhjbm() {

        return this.syhjbm;
    }

    public void setSxlx(String sxlx) {

        this.sxlx = sxlx;
    }

    @Basic
    @Column(name = "SXLX", length = 20)
    public String getSxlx() {

        return this.sxlx;
    }

    public void setDdrq(Date ddrq) {

        this.ddrq = ddrq;
    }

    @Basic
    @Column(name = "DDRQ")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    public Date getDdrq() {

        return this.ddrq;
    }

    public ZftzShjl(Long guid, Long xmid, String dqhjbm, Long qshjlid, Long clyhid, String clyj, Date clsj, String czlx, String syhjbm, String sxlx, Date ddrq) {
        this.guid = guid;
        this.xmid = xmid;
        this.dqhjbm = dqhjbm;
        this.qshjlid = qshjlid;
        this.clyhid = clyhid;
        this.clyj = clyj;
        this.clsj = clsj;
        this.czlx = czlx;
        this.syhjbm = syhjbm;
        this.sxlx = sxlx;
        this.ddrq = ddrq;
    }

    public ZftzShjl() {

    }

}
