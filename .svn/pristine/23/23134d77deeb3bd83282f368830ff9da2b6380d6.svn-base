package com.datanew.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import sun.misc.BASE64Encoder;

import javax.persistence.*;
import java.io.InputStream;
import java.sql.Blob;
import java.util.Date;

@Entity
@Table(name = "zftz_file")
public class ZftzFile {

    //
    private Integer guid;

    //文件业务种类（1，项目受理单附件   2，项目资金来源附件 3，项目概算附件）
    private String filebstype;


    //文件业务id
    private String filebsid;

    //文件名称
    private String filename;

    //文件类型
    private String filetype;

    //文件大小
    private String filesize;

    //文件内容
    private Blob filecontent;

    //添加时间
    private Date addtime;

    //
    private String adduser;

    //添加人名称
    private String addusername;

    //附件大类
    private String filedl;

    //附件小类
    private String filexl;

    public void setGuid(Integer guid) {

        this.guid = guid;
    }

    @Id
    @GeneratedValue(generator = "sequence")
    @SequenceGenerator(name = "sequence", sequenceName = "SEQ_ZFTZ_FILE")
    @Basic
    @Column(name = "GUID")
    public Integer getGuid() {

        return this.guid;
    }

    public void setFilebstype(String filebstype) {

        this.filebstype = filebstype;
    }

    @Basic
    @Column(name = "FILEBSTYPE", length = 255)
    public String getFilebstype() {

        return this.filebstype;
    }

    @Basic
    @Column(name = "FILEBSID", length = 255)
    public String getFilebsid() {
        return filebsid;
    }

    public void setFilebsid(String filebsid) {
        this.filebsid = filebsid;
    }


    public void setFilename(String filename) {

        this.filename = filename;
    }

    @Basic
    @Column(name = "FILENAME", length = 255)
    public String getFilename() {

        return this.filename;
    }

    public void setFiletype(String filetype) {

        this.filetype = filetype;
    }

    @Basic
    @Column(name = "FILETYPE", length = 255)
    public String getFiletype() {

        return this.filetype;
    }

    public void setFilesize(String filesize) {

        this.filesize = filesize;
    }

    @Basic
    @Column(name = "FILESIZE", length = 255)
    public String getFilesize() {

        return this.filesize;
    }

    public void setFilecontent(Blob filecontent) {

        this.filecontent = filecontent;
    }

    @Basic
    @Lob //对应Blob字段类型
    @Column(name = "FILECONTENT")
    public Blob getFilecontent() {

        return this.filecontent;
    }

    public void setAddtime(Date addtime) {

        this.addtime = addtime;
    }

    @Basic
    @Column(name = "ADDTIME")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    public Date getAddtime() {

        return this.addtime;
    }

    public void setAdduser(String adduser) {

        this.adduser = adduser;
    }

    @Basic
    @Column(name = "ADDUSER", length = 255)
    public String getAdduser() {

        return this.adduser;
    }

    public void setAddusername(String addusername) {

        this.addusername = addusername;
    }

    @Basic
    @Column(name = "ADDUSERNAME", length = 255)
    public String getAddusername() {

        return this.addusername;
    }

    @Basic
    @Column(name = "FILEDL", length = 10)
    public String getFiledl() {
        return filedl;
    }

    public void setFiledl(String filedl) {
        this.filedl = filedl;
    }

    @Basic
    @Column(name = "FILEXL", length = 10)
    public String getFilexl() {
        return filexl;
    }

    public void setFilexl(String filexl) {
        this.filexl = filexl;
    }

    public ZftzFile(Integer guid, String filebstype, String filebsid, String filename, String filetype, String filesize, Blob filecontent, Date addtime, String adduser, String addusername, String filedl, String filexl) {
        this.guid = guid;
        this.filebstype = filebstype;
        this.filebsid = filebsid;
        this.filename = filename;
        this.filetype = filetype;
        this.filesize = filesize;
        this.filecontent = filecontent;
        this.addtime = addtime;
        this.adduser = adduser;
        this.addusername = addusername;
        this.filedl = filedl;
        this.filexl = filexl;
    }

    public ZftzFile() {

    }

    @Transient
    public String getImageBase64() {
        Blob image = this.getFilecontent();
        byte[] data = null;
        try {
            InputStream in = image.getBinaryStream();
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }

}
