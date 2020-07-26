package com.datanew.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="ZFTZ_XM")
public  class  ZftzXm {

    private Long id;

    //项目名称
    private String xmmc;

    //项目阶段
    private Integer xmjd;

    //项目类型
    private Integer xmlx;

    //建设单位
    private Integer jsdw;

    //主管部门
    private Integer zgbm;

    //项目赋
    private String xmfm;

    //是否封闭,0否，1是
    private Integer sffb;

    //状态，0作废，1有效
    private Integer zt;





    public void setId(Long id){

        this.id=id;
    }
    private List<ZftzFile> zfFile;
    @Transient
    public List<ZftzFile> getZfFile() {
        return zfFile;
    }

    public void setZfFile(List<ZftzFile> zfFile) {
        this.zfFile = zfFile;
    }

    @Id
    @GeneratedValue(generator="sequence")
    @SequenceGenerator(name="sequence",sequenceName="SEQ_ZFTZ_XM")
    @Basic
    @Column(name="ID")
    public Long getId(){

        return this.id;
    }

    public void setXmmc(String xmmc){

        this.xmmc=xmmc;
    }

    @Basic
    @Column(name="XMMC",length=100)
    public String getXmmc(){

        return this.xmmc;
    }

    public void setXmjd(Integer xmjd){

        this.xmjd=xmjd;
    }

    @Basic
    @Column(name="XMJD")
    public Integer getXmjd(){

        return this.xmjd;
    }

    public void setXmlx(Integer xmlx){

        this.xmlx=xmlx;
    }

    @Basic
    @Column(name="XMLX")
    public Integer getXmlx(){

        return this.xmlx;
    }

    public void setJsdw(Integer jsdw){

        this.jsdw=jsdw;
    }

    @Basic
    @Column(name="JSDW")
    public Integer getJsdw(){

        return this.jsdw;
    }

    public void setZgbm(Integer zgbm){

        this.zgbm=zgbm;
    }

    @Basic
    @Column(name="ZGBM")
    public Integer getZgbm(){

        return this.zgbm;
    }

    public void setXmfm(String xmfm){

        this.xmfm=xmfm;
    }

    @Basic
    @Column(name="XMFM",length=20)
    public String getXmfm(){

        return this.xmfm;
    }

    public void setSffb(Integer sffb){

        this.sffb=sffb;
    }

    @Basic
    @Column(name="SFFB")
    public Integer getSffb(){

        return this.sffb;
    }

    public void setZt(Integer zt){

        this.zt=zt;
    }

    @Basic
    @Column(name="ZT")
    public Integer getZt(){

        return this.zt;
    }

    public ZftzXm(Long id,String xmmc,Integer xmjd,Integer xmlx,Integer jsdw,Integer zgbm,String xmfm,Integer sffb,Integer zt){

        
        this.id=id;
        this.xmmc=xmmc;
        this.xmjd=xmjd;
        this.xmlx=xmlx;
        this.jsdw=jsdw;
        this.zgbm=zgbm;
        this.xmfm=xmfm;
        this.sffb=sffb;
        this.zt=zt;
    }

    public ZftzXm(){

            }

}
