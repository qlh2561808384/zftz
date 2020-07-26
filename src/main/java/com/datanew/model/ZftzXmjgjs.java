package com.datanew.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "ZFTZ_XMJGJS")
public class ZftzXmjgjs {

    //id
    private Long id;

    //项目id，zftz_xm.id
    private Long idZftzXm;

    //建设单位
    private Long jsdw;

    //主管部门
    private Long zgbm;

    //项目规划选址
    private String xmghxz;

    //项目概算金额
    private BigDecimal xmgsje;

    //审核中介机构
    private Integer shzjjg;

    //开工时间
    private Integer kgsj;

    //竣工时间
    private Integer jgsj;

    //送审时间
    private Integer sssj;

    //竣工决算送审价
    private BigDecimal jgjsssj;

    //审定时间
    private Integer sdsj;

    //竣工决算审定价
    private BigDecimal jgjssdj;

    //批复/备案时间
    private Integer pfbasj;

    //批复文号
    private String pfwh;

    //应缴结余资金
    private BigDecimal yjjyzj;

    //缴回结余资金
    private BigDecimal jhjyzj;

    //项目计划资金来源_财政资金
    private BigDecimal jhCzzj;

    //项目计划资金来源_资源平衡
    private BigDecimal jhZyph;

    //项目计划资金来源_其他资金
    private BigDecimal jhQtzj;

    //项目实际到位资金_财政资金
    private BigDecimal sjCzzj;

    //项目实际到位资金_资源平衡
    private BigDecimal sjZyph;

    //项目实际到位资金_其他资金
    private BigDecimal sjQtzj;

    //联系人
    private String lxr;

    //联系电话
    private String lxdh;

    //固定资产
    private BigDecimal zcGdzc;

    //无形资产
    private BigDecimal zcWxzc;

    //待核销基建支出
    private BigDecimal zcDhxjjzc;

    //转出投资
    private BigDecimal zcZctz;

    //财政审批意见
    private String czspyj;

    //流程环节
    private Integer lchj = -1;

    //状态，0作废，1有效
    private Integer zt = 1;

    //操作时间
    private Date czsj = new Date();

    //操作人
    private String czr;

    public void setId(Long id) {

        this.id = id;
    }

    @Id
    @GeneratedValue(generator = "sequence")
    @SequenceGenerator(name = "sequence", sequenceName = "SEQ_ZFTZ_XMJGJS")
    @Basic
    @Column(name = "ID")
    public Long getId() {

        return this.id;
    }

    public void setIdZftzXm(Long idZftzXm) {

        this.idZftzXm = idZftzXm;
    }

    @Basic
    @Column(name = "ID_ZFTZ_XM")
    public Long getIdZftzXm() {

        return this.idZftzXm;
    }

    public void setJsdw(Long jsdw) {

        this.jsdw = jsdw;
    }

    @Basic
    @Column(name = "JSDW", length = 60)
    public Long getJsdw() {

        return this.jsdw;
    }

    public void setZgbm(Long zgbm) {

        this.zgbm = zgbm;
    }

    @Basic
    @Column(name = "ZGBM", length = 60)
    public Long getZgbm() {

        return this.zgbm;
    }

    public void setXmghxz(String xmghxz) {

        this.xmghxz = xmghxz;
    }

    @Basic
    @Column(name = "XMGHXZ", length = 200)
    public String getXmghxz() {

        return this.xmghxz;
    }

    public void setXmgsje(BigDecimal xmgsje) {

        this.xmgsje = xmgsje;
    }

    @Basic
    @Column(name = "XMGSJE")
    public BigDecimal getXmgsje() {

        return this.xmgsje;
    }

    public void setShzjjg(Integer shzjjg) {

        this.shzjjg = shzjjg;
    }

    @Basic
    @Column(name = "SHZJJG")
    public Integer getShzjjg() {

        return this.shzjjg;
    }

    public void setKgsj(Integer kgsj) {

        this.kgsj = kgsj;
    }

    @Basic
    @Column(name = "KGSJ")
    public Integer getKgsj() {

        return this.kgsj;
    }

    public void setJgsj(Integer jgsj) {

        this.jgsj = jgsj;
    }

    @Basic
    @Column(name = "JGSJ")
    public Integer getJgsj() {

        return this.jgsj;
    }

    public void setSssj(Integer sssj) {

        this.sssj = sssj;
    }

    @Basic
    @Column(name = "SSSJ")
    public Integer getSssj() {

        return this.sssj;
    }

    public void setJgjsssj(BigDecimal jgjsssj) {

        this.jgjsssj = jgjsssj;
    }

    @Basic
    @Column(name = "JGJSSSJ")
    public BigDecimal getJgjsssj() {

        return this.jgjsssj;
    }

    public void setSdsj(Integer sdsj) {

        this.sdsj = sdsj;
    }

    @Basic
    @Column(name = "SDSJ")
    public Integer getSdsj() {

        return this.sdsj;
    }

    public void setJgjssdj(BigDecimal jgjssdj) {

        this.jgjssdj = jgjssdj;
    }

    @Basic
    @Column(name = "JGJSSDJ")
    public BigDecimal getJgjssdj() {

        return this.jgjssdj;
    }

    public void setPfbasj(Integer pfbasj) {

        this.pfbasj = pfbasj;
    }

    @Basic
    @Column(name = "PFBASJ")
    public Integer getPfbasj() {

        return this.pfbasj;
    }

    public void setPfwh(String pfwh) {

        this.pfwh = pfwh;
    }

    @Basic
    @Column(name = "PFWH", length = 60)
    public String getPfwh() {

        return this.pfwh;
    }

    public void setYjjyzj(BigDecimal yjjyzj) {

        this.yjjyzj = yjjyzj;
    }

    @Basic
    @Column(name = "YJJYZJ")
    public BigDecimal getYjjyzj() {

        return this.yjjyzj;
    }

    public void setJhjyzj(BigDecimal jhjyzj) {

        this.jhjyzj = jhjyzj;
    }

    @Basic
    @Column(name = "JHJYZJ")
    public BigDecimal getJhjyzj() {

        return this.jhjyzj;
    }

    public void setJhCzzj(BigDecimal jhCzzj) {

        this.jhCzzj = jhCzzj;
    }

    @Basic
    @Column(name = "JH_CZZJ")
    public BigDecimal getJhCzzj() {

        return this.jhCzzj;
    }

    public void setJhZyph(BigDecimal jhZyph) {

        this.jhZyph = jhZyph;
    }

    @Basic
    @Column(name = "JH_ZYPH")
    public BigDecimal getJhZyph() {

        return this.jhZyph;
    }

    public void setJhQtzj(BigDecimal jhQtzj) {

        this.jhQtzj = jhQtzj;
    }

    @Basic
    @Column(name = "JH_QTZJ")
    public BigDecimal getJhQtzj() {

        return this.jhQtzj;
    }

    public void setSjCzzj(BigDecimal sjCzzj) {

        this.sjCzzj = sjCzzj;
    }

    @Basic
    @Column(name = "SJ_CZZJ")
    public BigDecimal getSjCzzj() {

        return this.sjCzzj;
    }

    public void setSjZyph(BigDecimal sjZyph) {

        this.sjZyph = sjZyph;
    }

    @Basic
    @Column(name = "SJ_ZYPH")
    public BigDecimal getSjZyph() {

        return this.sjZyph;
    }

    public void setSjQtzj(BigDecimal sjQtzj) {

        this.sjQtzj = sjQtzj;
    }

    @Basic
    @Column(name = "SJ_QTZJ")
    public BigDecimal getSjQtzj() {

        return this.sjQtzj;
    }

    public void setLxr(String lxr) {

        this.lxr = lxr;
    }

    @Basic
    @Column(name = "LXR", length = 20)
    public String getLxr() {

        return this.lxr;
    }

    public void setLxdh(String lxdh) {

        this.lxdh = lxdh;
    }

    @Basic
    @Column(name = "LXDH", length = 20)
    public String getLxdh() {

        return this.lxdh;
    }

    public void setZcGdzc(BigDecimal zcGdzc) {

        this.zcGdzc = zcGdzc;
    }

    @Basic
    @Column(name = "ZC_GDZC")
    public BigDecimal getZcGdzc() {

        return this.zcGdzc;
    }

    public void setZcWxzc(BigDecimal zcWxzc) {

        this.zcWxzc = zcWxzc;
    }

    @Basic
    @Column(name = "ZC_WXZC")
    public BigDecimal getZcWxzc() {

        return this.zcWxzc;
    }

    public void setZcDhxjjzc(BigDecimal zcDhxjjzc) {

        this.zcDhxjjzc = zcDhxjjzc;
    }

    @Basic
    @Column(name = "ZC_DHXJJZC")
    public BigDecimal getZcDhxjjzc() {

        return this.zcDhxjjzc;
    }

    public void setZcZctz(BigDecimal zcZctz) {

        this.zcZctz = zcZctz;
    }

    @Basic
    @Column(name = "ZC_ZCTZ")
    public BigDecimal getZcZctz() {

        return this.zcZctz;
    }

    public void setCzspyj(String czspyj) {

        this.czspyj = czspyj;
    }

    @Basic
    @Column(name = "CZSPYJ", length = 200)
    public String getCzspyj() {

        return this.czspyj;
    }

    public void setLchj(Integer lchj) {

        this.lchj = lchj;
    }

    @Basic
    @Column(name = "LCHJ")
    public Integer getLchj() {

        return this.lchj;
    }

    public void setZt(Integer zt) {

        this.zt = zt;
    }

    @Basic
    @Column(name = "ZT")
    public Integer getZt() {

        return this.zt;
    }

    public void setCzsj(Date czsj) {

        this.czsj = czsj;
    }

    @Basic
    @Column(name = "CZSJ")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    public Date getCzsj() {

        return this.czsj;
    }

    public void setCzr(String czr) {

        this.czr = czr;
    }

    @Basic
    @Column(name = "CZR", length = 20)
    public String getCzr() {

        return this.czr;
    }

    public ZftzXmjgjs(Long id, Long idZftzXm, Long jsdw, Long zgbm, String xmghxz, BigDecimal xmgsje, Integer shzjjg, Integer kgsj, Integer jgsj, Integer sssj, BigDecimal jgjsssj, Integer sdsj, BigDecimal jgjssdj, Integer pfbasj, String pfwh, BigDecimal yjjyzj, BigDecimal jhjyzj, BigDecimal jhCzzj, BigDecimal jhZyph, BigDecimal jhQtzj, BigDecimal sjCzzj, BigDecimal sjZyph, BigDecimal sjQtzj, String lxr, String lxdh, BigDecimal zcGdzc, BigDecimal zcWxzc, BigDecimal zcDhxjjzc, BigDecimal zcZctz, String czspyj, Integer lchj, Integer zt, Date czsj, String czr) {
        this.id = id;
        this.idZftzXm = idZftzXm;
        this.jsdw = jsdw;
        this.zgbm = zgbm;
        this.xmghxz = xmghxz;
        this.xmgsje = xmgsje;
        this.shzjjg = shzjjg;
        this.kgsj = kgsj;
        this.jgsj = jgsj;
        this.sssj = sssj;
        this.jgjsssj = jgjsssj;
        this.sdsj = sdsj;
        this.jgjssdj = jgjssdj;
        this.pfbasj = pfbasj;
        this.pfwh = pfwh;
        this.yjjyzj = yjjyzj;
        this.jhjyzj = jhjyzj;
        this.jhCzzj = jhCzzj;
        this.jhZyph = jhZyph;
        this.jhQtzj = jhQtzj;
        this.sjCzzj = sjCzzj;
        this.sjZyph = sjZyph;
        this.sjQtzj = sjQtzj;
        this.lxr = lxr;
        this.lxdh = lxdh;
        this.zcGdzc = zcGdzc;
        this.zcWxzc = zcWxzc;
        this.zcDhxjjzc = zcDhxjjzc;
        this.zcZctz = zcZctz;
        this.czspyj = czspyj;
        this.lchj = lchj;
        this.zt = zt;
        this.czsj = czsj;
        this.czr = czr;
    }

    public ZftzXmjgjs() {

    }

}
