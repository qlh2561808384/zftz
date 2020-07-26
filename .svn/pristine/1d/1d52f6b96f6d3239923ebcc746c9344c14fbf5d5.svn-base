package com.datanew.model;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author wuwei
 * @create 2019/5/17 11:12
 * @desc
 **/
@Entity
@Table(name="FBYX_NDSRJH_DZ")
public class FbyxNdsrjhDz {

    private Long id;
    private Long ndsrbid;
    private Long jhdzrq;
    private BigDecimal jhdzje;
    private Long sjdzrq;
    private BigDecimal sjdzje;
    private Long czrq;

    @Id
    @GeneratedValue(generator="sequence")
    @SequenceGenerator(name="sequence",sequenceName="SEQ_FBYX_NDSRJH_DZ")
    @Basic
    @Column(name="id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name="id_FBYX_NDSRB")
    public Long getNdsrbid() {
        return ndsrbid;
    }

    public void setNdsrbid(Long ndsrbid) {
        this.ndsrbid = ndsrbid;
    }

    @Basic
    @Column(name="jhdz_rq")
    public Long getJhdzrq() {
        return jhdzrq;
    }

    public void setJhdzrq(Long jhdzrq) {
        this.jhdzrq = jhdzrq;
    }

    @Basic
    @Column(name="jhdz_je")
    public BigDecimal getJhdzje() {
        return jhdzje;
    }

    public void setJhdzje(BigDecimal jhdzje) {
        this.jhdzje = jhdzje;
    }

    @Basic
    @Column(name="sjdz_rq")
    public Long getSjdzrq() {
        return sjdzrq;
    }

    public void setSjdzrq(Long sjdzrq) {
        this.sjdzrq = sjdzrq;
    }

    @Basic
    @Column(name="sjdz_je")
    public BigDecimal getSjdzje() {
        return sjdzje;
    }

    public void setSjdzje(BigDecimal sjdzje) {
        this.sjdzje = sjdzje;
    }

    @Basic
    @Column(name="czrq")
    public Long getCzrq() {
        return czrq;
    }

    public void setCzrq(Long czrq) {
        this.czrq = czrq;
    }
}
