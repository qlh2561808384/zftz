package com.datanew.service.impl;

import com.datanew.dao.BaseDao;
import com.datanew.dto.unalterable.$Result;
import com.datanew.model.ZftzFile;
import com.datanew.model.YhglYwYh;
import com.datanew.model.YhglYwYhyy;
import com.datanew.service.FileService;
import com.datanew.util.Base64Utils;
import com.datanew.util.ConfigureParser;
import com.datanew.util.FTPUtils;
import com.datanew.util.StaticData;
import com.datanew.util.StringUtil;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLEncoder;
import java.sql.Blob;
import java.util.*;


@Service("fileService")
public class FileServiceImpl implements FileService {
    @Autowired
    BaseDao baseDao;


	public Object uploader(HttpServletRequest req) {
		Map map = new HashMap();
		YhglYwYh operator = (YhglYwYh) req.getSession().getAttribute(StaticData.LOGINUSERINFO);
		YhglYwYhyy yhglywyhyy = (YhglYwYhyy) req.getSession().getAttribute(StaticData.LOGINUSER);

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
		MultipartFile file = multipartRequest.getFile("file");
		String filebstype =req.getParameter("filebstype");
        FTPUtils ftp=null;
		try {
			InputStream input = file.getInputStream();
			ZftzFile bsfile = new ZftzFile();
			 bsfile.setAddtime(new Date());
            bsfile.setAdduser(""+yhglywyhyy.getGuid());
            bsfile.setAddusername(operator.getXm());
            bsfile.setFilebstype(filebstype);
            bsfile.setFilename(file.getOriginalFilename());
            BigDecimal b = new BigDecimal(file.getSize());
//            BigDecimal d = b.divide(new BigDecimal(1024),2,RoundingMode.UP);
            bsfile.setFilesize(""+b);
            bsfile.setFiletype(file.getContentType());
            //存数据库
            //Blob blob = Hibernate.getLobCreator(baseDao.getSession()).createBlob(input,input.available());
            //bsfile.setFilecontent(blob);

			baseDao.save(bsfile);

            //存ftp
            String[] fstr = file.getOriginalFilename().split("\\.");
            String fend = fstr[1];
            ftp = new FTPUtils();
            File targetFile=new File(File.separator+ConfigureParser.getPropert("ftp.upload.path")+File.separator+bsfile.getGuid()+"."+fend);
            //否则直接将文件内容拷贝至新文件
            //saveUploadFile(multipartFile.getInputStream(), targetFile,fmap);
            Map fmap = new HashMap();
            ftp.uploadFileInputStream(targetFile,input,fmap);

			map.put("success",true);
			map.put("fileId",bsfile.getGuid());

			return map;
		} catch (IOException e) {
			e.printStackTrace();
			map.put("success",false);
			map.put("content",e.toString());
			return map;
		}
		finally {
		    ftp.logintout();
        }

	}

	public Object deleteById(String guid, HttpSession session) {
		ZftzFile file = (ZftzFile) baseDao.load(ZftzFile.class, Integer.parseInt(guid));
		baseDao.delete(file);
		return $Result.success();
	}

	public void downloadByid(String guid, HttpServletResponse response) {
		ZftzFile file = (ZftzFile) baseDao.load(ZftzFile.class, Integer.parseInt(guid));
		//获得Blob对象
		//Blob image = file.getFilecontent();
		//获得照片输入流
        FTPUtils ftp=null;
		try {
            ftp = new FTPUtils();
            String[] fstr = file.getFilename().split("\\.");
            String fend = fstr[1];
            System.out.println(ConfigureParser.getPropert("ftp.upload.path"));
            //String ppp =ConfigureParser.getPropert("ftp.upload.path").toString().replaceAll("/", File.separator);
			InputStream input = ftp.getFileStream(ConfigureParser.getPropert("ftp.upload.path")+File.separator,file.getGuid()+"."+fend);

			String fileName = file.getFilename();
			fileName = URLEncoder.encode(fileName, "UTF-8");
			response.reset();
			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
			response.addHeader("Content-Length", "" +file.getFilesize() );
			response.setContentType("application/octet-stream;charset=UTF-8");
			OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());

			int ch;
			while ((ch = input.read()) != -1) {
				outputStream.write(ch);
			}
			outputStream.flush();
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			ftp.logintout();
		}


	}

	public List getFilesByids(String ids) {
		String hql =" from ZftzFile b where b.guid in ("+ids+")";
		List<ZftzFile> list = baseDao.selectByHql(hql);
		List result = new ArrayList();
		for (int i = 0; i <list.size() ; i++) {
			Map map = new HashMap();
			map.put("id",list.get(i).getGuid());
			map.put("type",list.get(i).getFiletype());
			map.put("name",list.get(i).getFilename());
			map.put("size",list.get(i).getFilesize());
			map.put("status","complete");
			map.put("uploadDate",list.get(i).getAddtime());
			map.put("fileId",list.get(i).getGuid());
			map.put("base64Url","data:"+list.get(i).getFiletype()+";base64,"+list.get(i).getImageBase64());
			result.add(map);
		}
		return result;
	}

	public Object uploaderJpg(HttpServletRequest req) {

		Map map = new HashMap();
		YhglYwYh operator = (YhglYwYh) req.getSession().getAttribute(StaticData.LOGINUSERINFO);
		YhglYwYhyy yhglywyhyy = (YhglYwYhyy) req.getSession().getAttribute(StaticData.LOGINUSER);

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
		MultipartFile file = multipartRequest.getFile("file");
		String filebstype =req.getParameter("filebstype");
        FTPUtils ftp=null;
		try {
			InputStream input = file.getInputStream();
			ZftzFile bsfile = new ZftzFile();
			 bsfile.setAddtime(new Date());
            bsfile.setAdduser(""+yhglywyhyy.getGuid());
            bsfile.setAddusername(operator.getXm());
            bsfile.setFilebstype(filebstype);
            bsfile.setFilename(file.getOriginalFilename());
            bsfile.setFilesize(""+file.getSize());
            bsfile.setFiletype(file.getContentType());
            bsfile.setFilexl("99");
            //存数据库
            //Blob blob = Hibernate.getLobCreator(baseDao.getSession()).createBlob(input,input.available());
            //bsfile.setFilecontent(blob);

			baseDao.save(bsfile);

            //存ftp
            String[] fstr = file.getOriginalFilename().split("\\.");
            String fend = fstr[1];
            ftp = new FTPUtils();
            File targetFile=new File(File.separator+ConfigureParser.getPropert("ftp.upload.path")+File.separator+bsfile.getGuid()+"."+fend);
            //否则直接将文件内容拷贝至新文件
            //saveUploadFile(multipartFile.getInputStream(), targetFile,fmap);
            Map fmap = new HashMap();
            ftp.uploadFileInputStream(targetFile,input,fmap);

			map.put("success",true);
			map.put("fileId",bsfile.getGuid());

			return map;
		} catch (IOException e) {
			e.printStackTrace();
			map.put("success",false);
			map.put("content",e.toString());
			return map;
		}
		finally {
		    ftp.logintout();
        }

	

	}
	
	
	

}
