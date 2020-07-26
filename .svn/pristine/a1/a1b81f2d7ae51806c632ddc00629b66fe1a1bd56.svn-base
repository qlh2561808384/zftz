package com.datanew.model;


import javax.persistence.*;

@Entity
@Table(name = "ZFTZ_ALKDY")
public class ZftzAlkdy {

    private Long id;

    private String xmlx;

    private String zdm;

    private String xsmc;

    private String zdlx;

    private String zdgs;

    private Integer zt;

    private Integer sfzj;

    public void setId(Long id) {

        this.id = id;
    }

    @Id
    @GeneratedValue(generator = "sequence")
    @SequenceGenerator(name = "sequence", sequenceName = "SEQ_ZFTZ_ALKDY")
    @Basic
    @Column(name = "ID")
    public Long getId() {

        return this.id;
    }

    public void setXmlx(String xmlx) {

        this.xmlx = xmlx;
    }

    @Basic
    @Column(name = "XMLX")
    public String getXmlx() {

        return this.xmlx;
    }

    public void setZdm(String zdm) {

        this.zdm = zdm;
    }

    @Basic
    @Column(name = "ZDM", length = 60)
    public String getZdm() {

        return this.zdm;
    }

    public void setXsmc(String xsmc) {

        this.xsmc = xsmc;
    }

    @Basic
    @Column(name = "XSMC", length = 60)
    public String getXsmc() {

        return this.xsmc;
    }

    public void setZdlx(String zdlx) {

        this.zdlx = zdlx;
    }

    @Basic
    @Column(name = "ZDLX", length = 60)
    public String getZdlx() {

        return this.zdlx;
    }

    public void setZdgs(String zdgs) {

        this.zdgs = zdgs;
    }

    @Basic
    @Column(name = "ZDGS")
    public String getZdgs() {

        return this.zdgs;
    }

    public void setZt(Integer zt) {

        this.zt = zt;
    }

    @Basic
    @Column(name = "ZT")
    public Integer getZt() {

        return this.zt;
    }

    public void setSfzj(Integer sfzj) {

        this.sfzj = sfzj;
    }

    @Basic
    @Column(name = "SFZJ")
    public Integer getSfzj() {

        return this.sfzj;
    }

    public ZftzAlkdy(Long id, String xmlx, String zdm, String xsmc, String zdlx, String zdgs, Integer zt, Integer sfzj) {
        this.id = id;
        this.xmlx = xmlx;
        this.zdm = zdm;
        this.xsmc = xsmc;
        this.zdlx = zdlx;
        this.zdgs = zdgs;
        this.zt = zt;
        this.sfzj = sfzj;
    }

    public ZftzAlkdy() {

    }

}
