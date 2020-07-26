package com.datanew.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="ZFTZ_XMGS")
public  class  ZftzXmgs {

    //id
    private Long id;

    //项目id，zftz_xm.id
    private Long idZftzXm;


    //项目规划选址
    private String xmghxz;

    //容积率
    private BigDecimal rjl;

    //用地面积(平方米)
    private BigDecimal ydmj;

    //建筑总面积（平方米）
    private BigDecimal jzzmj;

    //其中：地上建筑面积（平方米）
    private BigDecimal dsjzmj;

    //项目里程（公里）
    private BigDecimal xmlc;

    //道路宽度(米)
    private BigDecimal dlkd;

    //投资限额
    private BigDecimal tzxe;

    //建议控制造价标准
    private BigDecimal jykzzjbz;

    //项目单位造价
    private BigDecimal xmdwzj;

    //建设内容
    private String jsnr;

    //概算财政评审建议
//    private String gsczphjy;

    //流程环节
    private Integer lchj;

    //状态，0作废，1有效
    private Integer zt;

    //操作时间
    private Date czsj;

    //操作人
    private String czr;

    public void setId(Long id){

        this.id=id;
    }

    @Id
    @GeneratedValue(generator="sequence")
    @SequenceGenerator(name="sequence",sequenceName="SEQ_ZFTZ_XMGS")
    @Basic
    @Column(name="ID")
    public Long getId(){

        return this.id;
    }

    public void setIdZftzXm(Long idZftzXm){

        this.idZftzXm=idZftzXm;
    }

    @Basic
    @Column(name="ID_ZFTZ_XM")
    public Long getIdZftzXm(){

        return this.idZftzXm;
    }

    public void setXmghxz(String xmghxz){

        this.xmghxz=xmghxz;
    }

    @Basic
    @Column(name="XMGHXZ",length=200)
    public String getXmghxz(){

        return this.xmghxz;
    }

    public void setRjl(BigDecimal rjl){

        this.rjl=rjl;
    }

    @Basic
    @Column(name="RJL")
    public BigDecimal getRjl(){

        return this.rjl;
    }

    public void setYdmj(BigDecimal ydmj){

        this.ydmj=ydmj;
    }

    @Basic
    @Column(name="YDMJ")
    public BigDecimal getYdmj(){

        return this.ydmj;
    }

    public void setJzzmj(BigDecimal jzzmj){

        this.jzzmj=jzzmj;
    }

    @Basic
    @Column(name="JZZMJ")
    public BigDecimal getJzzmj(){

        return this.jzzmj;
    }

    public void setDsjzmj(BigDecimal dsjzmj){

        this.dsjzmj=dsjzmj;
    }

    @Basic
    @Column(name="DSJZMJ")
    public BigDecimal getDsjzmj(){

        return this.dsjzmj;
    }

    public void setXmlc(BigDecimal xmlc){

        this.xmlc=xmlc;
    }

    @Basic
    @Column(name="XMLC")
    public BigDecimal getXmlc(){

        return this.xmlc;
    }

    public void setDlkd(BigDecimal dlkd){

        this.dlkd=dlkd;
    }

    @Basic
    @Column(name="DLKD")
    public BigDecimal getDlkd(){

        return this.dlkd;
    }

    public void setTzxe(BigDecimal tzxe){

        this.tzxe=tzxe;
    }

    @Basic
    @Column(name="TZXE")
    public BigDecimal getTzxe(){

        return this.tzxe;
    }

    public void setJykzzjbz(BigDecimal jykzzjbz){

        this.jykzzjbz=jykzzjbz;
    }

    @Basic
    @Column(name="JYKZZJBZ")
    public BigDecimal getJykzzjbz(){

        return this.jykzzjbz;
    }

    public void setXmdwzj(BigDecimal xmdwzj){

        this.xmdwzj=xmdwzj;
    }

    @Basic
    @Column(name="XMDWZJ")
    public BigDecimal getXmdwzj(){

        return this.xmdwzj;
    }

    public void setJsnr(String jsnr){

        this.jsnr=jsnr;
    }

    @Basic
    @Column(name="JSNR",length=200)
    public String getJsnr(){

        return this.jsnr;
    }

    /*public void setGsczphjy(String gsczphjy){

        this.gsczphjy=gsczphjy;
    }*/

    /*@Basic
    @Column(name="GSCZPHJY",length=200)
    public String getGsczphjy(){

        return this.gsczphjy;
    }*/

    public void setLchj(Integer lchj){

        this.lchj=lchj;
    }

    @Basic
    @Column(name="LCHJ")
    public Integer getLchj(){

        return this.lchj;
    }

    public void setZt(Integer zt){

        this.zt=zt;
    }

    @Basic
    @Column(name="ZT")
    public Integer getZt(){

        return this.zt;
    }

    public void setCzsj(Date czsj){

        this.czsj=czsj;
    }

    @Basic
    @Column(name="CZSJ")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",locale="zh",timezone="GMT+8")
    public Date getCzsj(){

        return this.czsj;
    }

    public void setCzr(String czr){

        this.czr=czr;
    }

    @Basic
    @Column(name="CZR",length=20)
    public String getCzr(){

        return this.czr;
    }

    public ZftzXmgs(Long id,Long idZftzXm,String xmghxz,BigDecimal rjl,BigDecimal ydmj,BigDecimal jzzmj,BigDecimal dsjzmj,BigDecimal xmlc,BigDecimal dlkd,BigDecimal tzxe,BigDecimal jykzzjbz,BigDecimal xmdwzj,String jsnr,/*String gsczphjy,*/Integer lchj,Integer zt,Date czsj,String czr){

        
        this.id=id;
        this.idZftzXm=idZftzXm;
        this.xmghxz=xmghxz;
        this.rjl=rjl;
        this.ydmj=ydmj;
        this.jzzmj=jzzmj;
        this.dsjzmj=dsjzmj;
        this.xmlc=xmlc;
        this.dlkd=dlkd;
        this.tzxe=tzxe;
        this.jykzzjbz=jykzzjbz;
        this.xmdwzj=xmdwzj;
        this.jsnr=jsnr;
//        this.gsczphjy=gsczphjy;
        this.lchj=lchj;
        this.zt=zt;
        this.czsj=czsj;
        this.czr=czr;
    }

    public ZftzXmgs(){

            }

}
