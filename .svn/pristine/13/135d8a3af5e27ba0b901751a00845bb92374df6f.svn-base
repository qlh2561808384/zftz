package com.datanew.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name="ZFTZ_XMZJLYDJ")//不能加schema
public  class  ZftzXmzjlydj {

    //id
    private Long ide;

    //项目id，zftz_xm.id
    private Long xmid;

    //项目规划选址
    private String xmghxz;

    //项目总投资
    private BigDecimal xmztz;

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

    //计量单位(造价标准)
    private String jldw;

    //投资限额
    private BigDecimal tzxe;

    //建议控制造价标准
    private BigDecimal jykzzjbz;

    //项目单位造价
    private BigDecimal xmdwzj;

    //建设内容
    private String jsnr;

    //总投资_建筑安装投资
    private BigDecimal ztzJzaztz;

    //总投资_设备投资
    private BigDecimal ztzSbtz;

    //总投资_待摊投资
    private BigDecimal ztzDttz;

    //总投资_其他投资
    private BigDecimal ztzQttz;

    //项目资金来源_财政性资金
    private BigDecimal xmzjlyCzxzj;

    //项目资金来源_资源平衡
    private BigDecimal xmzjlyZyph;

    //项目资金来源_其他
    private BigDecimal xmzjlyQt;

    //状态，0作废，1有效
    private Integer zt;

    //操作时间
    private Date czsj=new Date();

    //操作人
    private String czr;

    private List<ZftzFile> zfFile;

    public void setIde(Long ide) {
        this.ide = ide;
    }
    @Id
    @GeneratedValue(generator="sequence")
    @SequenceGenerator(name="sequence",sequenceName="SEQ_ZFTZ_XMZJLYDJ")
    @Basic
    @Column(name="ID")
    public Long getIde() {
        return ide;
    }

    @Basic
    @Column(name="ID_ZFTZ_XM")
    public Long getXmid() {
        return xmid;
    }

    public void setXmid(Long xmid) {
        this.xmid = xmid;
    }




    public void setXmghxz(String xmghxz){

        this.xmghxz=xmghxz;
    }

    @Basic
    @Column(name="XMGHXZ",length=200)
    public String getXmghxz(){

        return this.xmghxz;
    }

    public void setXmztz(BigDecimal xmztz){

        this.xmztz=xmztz;
    }

    @Basic
    @Column(name="XMZTZ")
    public BigDecimal getXmztz(){

        return this.xmztz;
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

    public void setJldw(String jldw){

        this.jldw=jldw;
    }

    @Basic
    @Column(name="JLDW",length=20)
    public String getJldw(){

        return this.jldw;
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

    public void setZtzJzaztz(BigDecimal ztzJzaztz){

        this.ztzJzaztz=ztzJzaztz;
    }

    @Basic
    @Column(name="ZTZ_JZAZTZ")
    public BigDecimal getZtzJzaztz(){

        return this.ztzJzaztz;
    }

    public void setZtzSbtz(BigDecimal ztzSbtz){

        this.ztzSbtz=ztzSbtz;
    }

    @Basic
    @Column(name="ZTZ_SBTZ")
    public BigDecimal getZtzSbtz(){

        return this.ztzSbtz;
    }

    public void setZtzDttz(BigDecimal ztzDttz){

        this.ztzDttz=ztzDttz;
    }

    @Basic
    @Column(name="ZTZ_DTTZ")
    public BigDecimal getZtzDttz(){

        return this.ztzDttz;
    }

    public void setZtzQttz(BigDecimal ztzQttz){

        this.ztzQttz=ztzQttz;
    }

    @Basic
    @Column(name="ZTZ_QTTZ")
    public BigDecimal getZtzQttz(){

        return this.ztzQttz;
    }

    public void setXmzjlyCzxzj(BigDecimal xmzjlyCzxzj){

        this.xmzjlyCzxzj=xmzjlyCzxzj;
    }

    @Basic
    @Column(name="XMZJLY_CZXZJ")
    public BigDecimal getXmzjlyCzxzj(){

        return this.xmzjlyCzxzj;
    }

    public void setXmzjlyZyph(BigDecimal xmzjlyZyph){

        this.xmzjlyZyph=xmzjlyZyph;
    }

    @Basic
    @Column(name="XMZJLY_ZYPH")
    public BigDecimal getXmzjlyZyph(){

        return this.xmzjlyZyph;
    }

    public void setXmzjlyQt(BigDecimal xmzjlyQt){

        this.xmzjlyQt=xmzjlyQt;
    }

    @Basic
    @Column(name="XMZJLY_QT")
    public BigDecimal getXmzjlyQt(){

        return this.xmzjlyQt;
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

    public ZftzXmzjlydj(Long ide,Long xmid,String xmghxz,BigDecimal xmztz,BigDecimal rjl,BigDecimal ydmj,BigDecimal jzzmj,BigDecimal dsjzmj,BigDecimal xmlc,BigDecimal dlkd,String jldw,BigDecimal tzxe,BigDecimal jykzzjbz,BigDecimal xmdwzj,String jsnr,BigDecimal ztzJzaztz,BigDecimal ztzSbtz,BigDecimal ztzDttz,BigDecimal ztzQttz,BigDecimal xmzjlyCzxzj,BigDecimal xmzjlyZyph,BigDecimal xmzjlyQt,Integer zt,Date czsj,String czr){

        
        this.ide=ide;
        this.xmid=xmid;
        this.xmghxz=xmghxz;
        this.xmztz=xmztz;
        this.rjl=rjl;
        this.ydmj=ydmj;
        this.jzzmj=jzzmj;
        this.dsjzmj=dsjzmj;
        this.xmlc=xmlc;
        this.dlkd=dlkd;
        this.jldw=jldw;
        this.tzxe=tzxe;
        this.jykzzjbz=jykzzjbz;
        this.xmdwzj=xmdwzj;
        this.jsnr=jsnr;
        this.ztzJzaztz=ztzJzaztz;
        this.ztzSbtz=ztzSbtz;
        this.ztzDttz=ztzDttz;
        this.ztzQttz=ztzQttz;
        this.xmzjlyCzxzj=xmzjlyCzxzj;
        this.xmzjlyZyph=xmzjlyZyph;
        this.xmzjlyQt=xmzjlyQt;
        this.zt=zt;
        this.czsj=czsj;
        this.czr=czr;
    }

    public ZftzXmzjlydj(){

            }
    @Transient
    public List<ZftzFile> getZfFile() {
        return zfFile;
    }
    public void setZfFile(List<ZftzFile> zfFile) {
        this.zfFile = zfFile;
    }
}
