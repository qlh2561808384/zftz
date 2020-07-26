package com.datanew.model;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="XTGL_GG_MENU")
public  class  XtglGgMenu {

    private String yyid;

    //是否可用(1为是,0为否)
    private Integer enable;

    //排序号
    private Long ordernum;

    //菜单名称
    private String menuname;

    private String yybm;

    //父id
    private Long parentid;

    //菜单id
    private Long menuid;

    //菜单图标
    private String menuicon;

    //是否为系统菜单1.系统菜单不允许删除2外挂菜单允许删除
    private String isdel;

    //菜单地址
    private String menuurl;

    //菜单类型 1-菜单  2-按钮
    private String menutype;

    public void setYyid(String yyid){

        this.yyid=yyid;
    }

    @Basic
    @Column(name="YYID",length=100)
    public String getYyid(){

        return this.yyid;
    }

    public void setEnable(Integer enable){

        this.enable=enable;
    }

    @Basic
    @Column(name="ENABLE")
    public Integer getEnable(){

        return this.enable;
    }

    public void setOrdernum(Long ordernum){

        this.ordernum=ordernum;
    }

    @Basic
    @Column(name="ORDERNUM")
    public Long getOrdernum(){

        return this.ordernum;
    }

    public void setMenuname(String menuname){

        this.menuname=menuname;
    }

    @Basic
    @Column(name="MENUNAME",length=100)
    public String getMenuname(){

        return this.menuname;
    }

    public void setYybm(String yybm){

        this.yybm=yybm;
    }

    @Basic
    @Column(name="YYBM",length=100)
    public String getYybm(){

        return this.yybm;
    }

    public void setParentid(Long parentid){

        this.parentid=parentid;
    }

    @Basic
    @Column(name="PARENTID")
    public Long getParentid(){

        return this.parentid;
    }

    public void setMenuid(Long menuid){

        this.menuid=menuid;
    }

    @Id
    @GeneratedValue(generator="sequence")
    @SequenceGenerator(name="sequence",sequenceName="SEQ_XTGL_GG_MENU")
    @Basic
    @Column(name="MENUID")
    public Long getMenuid(){

        return this.menuid;
    }

    public void setMenuicon(String menuicon){

        this.menuicon=menuicon;
    }

    @Basic
    @Column(name="MENUICON",length=100)
    public String getMenuicon(){

        return this.menuicon;
    }

    public void setIsdel(String isdel){

        this.isdel=isdel;
    }

    @Basic
    @Column(name="ISDEL",length=10)
    public String getIsdel(){

        return this.isdel;
    }

    public void setMenuurl(String menuurl){

        this.menuurl=menuurl;
    }

    @Basic
    @Column(name="MENUURL",length=100)
    public String getMenuurl(){

        return this.menuurl;
    }

    public void setMenutype(String menutype){

        this.menutype=menutype;
    }

    @Basic
    @Column(name="MENUTYPE",length=6)
    public String getMenutype(){

        return this.menutype;
    }

    public XtglGgMenu(String yyid,Integer enable,Long ordernum,String menuname,String yybm,Long parentid,Long menuid,String menuicon,String isdel,String menuurl,String menutype){

        
        this.yyid=yyid;
        this.enable=enable;
        this.ordernum=ordernum;
        this.menuname=menuname;
        this.yybm=yybm;
        this.parentid=parentid;
        this.menuid=menuid;
        this.menuicon=menuicon;
        this.isdel=isdel;
        this.menuurl=menuurl;
        this.menutype=menutype;
    }

    public XtglGgMenu(){

            }

}
