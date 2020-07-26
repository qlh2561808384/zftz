package com.datanew.model;


import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ZFTZ_XMGCJSBA_FYMX")
public class ZftzXmgcjsbaFymx {

    //id
    private Long id;

    //工程结算id，ZFTZ_XMGCJSBA.id
    private Long idZftzXmgcjsba;

    //对应工程或者费用名称id
    private Long gcfyid;

    //投资分类
    private Integer tzefl;

    //合同金额
    private BigDecimal htje;

    //协议金额
    private BigDecimal xyje;

    public void setId(Long id) {

        this.id = id;
    }

    @Id
    @GeneratedValue(generator = "sequence")
    @SequenceGenerator(name = "sequence", sequenceName = "seq_zftz_xmgcjsbamx")
    @Basic
    @Column(name = "ID")
    public Long getId() {

        return this.id;
    }

    public void setIdZftzXmgcjsba(Long idZftzXmgcjsba) {

        this.idZftzXmgcjsba = idZftzXmgcjsba;
    }

    @Basic
    @Column(name = "ID_ZFTZ_XMGCJSBA")
    public Long getIdZftzXmgcjsba() {

        return this.idZftzXmgcjsba;
    }

    public void setGcfyid(Long gcfyid) {

        this.gcfyid = gcfyid;
    }

    @Basic
    @Column(name = "GCFYID")
    public Long getGcfyid() {

        return this.gcfyid;
    }

    public void setTzefl(Integer tzefl) {

        this.tzefl = tzefl;
    }

    @Basic
    @Column(name = "TZEFL")
    public Integer getTzefl() {

        return this.tzefl;
    }

    public void setHtje(BigDecimal htje) {

        this.htje = htje;
    }

    @Basic
    @Column(name = "HTJE")
    public BigDecimal getHtje() {

        return this.htje;
    }

    public void setXyje(BigDecimal xyje) {

        this.xyje = xyje;
    }

    @Basic
    @Column(name = "XYJE")
    public BigDecimal getXyje() {

        return this.xyje;
    }

    public ZftzXmgcjsbaFymx(Long id, Long idZftzXmgcjsba, Long gcfyid, Integer tzefl, BigDecimal htje, BigDecimal xyje) {


        this.id = id;
        this.idZftzXmgcjsba = idZftzXmgcjsba;
        this.gcfyid = gcfyid;
        this.tzefl = tzefl;
        this.htje = htje;
        this.xyje = xyje;
    }

    public ZftzXmgcjsbaFymx() {

    }

}
