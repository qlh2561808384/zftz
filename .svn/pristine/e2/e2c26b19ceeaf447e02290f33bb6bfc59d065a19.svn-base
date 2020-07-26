package com.datanew.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FTPUtils {
    private static int CON_TIMEOUT = 30000;
    private static String ENCODING = "GBK";

    public FTPClient ftpClient = null;

    public void logintout(){
        try {
            if(ftpClient!=null){
                ftpClient.logout();
            }
            ftpClient=null;
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public FTPClient initFtpClient() throws Exception{
        String outMess = ConfigureParser.getPropert("ftp.url")+":"+ConfigureParser.getPropert("ftp.port");
        if (ftpClient == null) {
                ftpClient = new FTPClient();
                ftpClient.setControlEncoding(ENCODING);
                ftpClient.connect(ConfigureParser.getPropert("ftp.url"), Integer.valueOf(ConfigureParser.getPropert("ftp.port")));
                ftpClient.login(ConfigureParser.getPropert("ftp.username"), ConfigureParser.getPropert("ftp.password"));
                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                ftpClient.enterLocalPassiveMode();
                ftpClient.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
                ftpClient.setDataTimeout(CON_TIMEOUT);
                int reply = ftpClient.getReplyCode(); //是否成功登录服务器

                if (FTPReply.isPositiveCompletion(reply)) {
                    System.out.println("FTP "+outMess+" 连接成功");
                } else {
                    System.out.println("FTP "+outMess+" 连接失败");
                    ftpClient.disconnect();
                    ftpClient = null;
                }
        }
        return ftpClient;
    }

    /**
     * 读取文件路径上传文件
     * @param remotePath ftp服务保存地址
     * @param fileName 上传到ftp的文件名
     *  @param localPath 待上传文件的地址（绝对地址）
     * @return
     */
    public boolean uploadFilePath( String remotePath, String fileName,String localPath){
        boolean flag = false;
        InputStream inputStream = null;
        try{
            inputStream = new FileInputStream(new File(localPath));
            if(initFtpClient()!=null){
                ftpClient.setFileType(ftpClient.BINARY_FILE_TYPE);
                CreateDirecroty(remotePath);
                ftpClient.makeDirectory(remotePath);
                ftpClient.changeWorkingDirectory(remotePath);
                ftpClient.storeFile(fileName, inputStream);
                inputStream.close();
                ftpClient.logout();
                flag = true;
                System.out.println("上传文件成功");
            }
        }catch (Exception e) {
            System.out.println("上传文件失败");
            e.printStackTrace();
        }finally{
            if(ftpClient!=null && ftpClient.isConnected()){
                try{
                    ftpClient.disconnect();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            if(null != inputStream){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }
    /**
     * 文件流上传文件
     * @param targetFile
     * @param inputStream 输入文件流
     * @param map 结果
     * @return
     */
    public boolean uploadFileInputStream( File targetFile,InputStream inputStream,Map map){
        boolean flag = false;
        try{
            if(initFtpClient()!=null){
            	if(!existFile(targetFile.getParent())){
            		CreateDirecroty(targetFile.getParent());
            	}
                ftpClient.makeDirectory(targetFile.getParent());
                ftpClient.changeWorkingDirectory(targetFile.getParent());
                ftpClient.storeFile(targetFile.getName(), inputStream);

                FTPFile[] ftpFiles = ftpClient.listFiles();
                for(FTPFile ftpfile : ftpFiles){
                    if(targetFile.getName().equals(ftpfile.getName())){
                        map.put("SIZE",ftpfile.getSize());
                        break;
                    }
                }

                inputStream.close();
                flag = true;
                map.put("success",true);
                map.put("MD5", DigestUtils.md5Hex(targetFile.getPath()));
                System.out.println("上传文件成功");
            }else{
                map.put("success",false);
                map.put("content","FTP服务器连接失败");
                System.out.println("FTP服务器连接失败");
            }

        }catch (Exception e) {
            map.put("success",false);
            map.put("content","上传失败");
            System.out.println("上传文件失败");
            e.printStackTrace();
        }finally{
            
            if(null != inputStream){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }

    //改变目录路径
    public boolean changeWorkingDirectory(String directory) {
        boolean flag = true;
        try {
            flag = ftpClient.changeWorkingDirectory(directory);
            if (flag) {
                System.out.println("进入文件夹" + directory + " 成功！");
            } else {
                System.out.println("进入文件夹" + directory + " 失败！开始创建文件夹");
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return flag;
    }

    //创建多层目录文件，如果ftp服务器已存在该文件，则不创建，如果无，则创建
    public boolean CreateDirecroty(String remote) throws IOException {
        boolean success = true;
        String directory =File.separator+ remote + File.separator;
        // 如果远程目录不存在，则递归创建远程服务器目录
        if (!directory.equalsIgnoreCase(File.separator) && !changeWorkingDirectory(new String(directory))) {
            int start = 0;
            int end = 0;
            if (directory.startsWith(File.separator)) {
                start = 1;
            } else {
                start = 0;
            }
            end = directory.indexOf(File.separator, start);
            String path = "";
            String paths = "";
            while (true) {
                String subDirectory = new String(directory.substring(start, end).getBytes("GBK"), "iso-8859-1");
                path = path + File.separator + subDirectory;
                if (!existFile(path)) {
                    if (makeDirectory(subDirectory)) {
                        changeWorkingDirectory(subDirectory);
                    } else {
                        System.out.println("创建目录[" + subDirectory + "]失败");
                        changeWorkingDirectory(subDirectory);
                    }
                } else {
                    changeWorkingDirectory(subDirectory);
                }

                paths = paths + File.separator + subDirectory;
                start = end + 1;
                end = directory.indexOf(File.separator, start);
                // 检查所有目录是否创建完毕
                if (end <= start) {
                    break;
                }
            }
        }
        return success;
    }

    //判断ftp服务器文件是否存在
    public boolean existFile(String path) throws IOException {
        boolean flag = false;
        FTPFile[] ftpFileArr = ftpClient.listFiles(path);
        if (ftpFileArr.length > 0) {
            flag = true;
        }
        return flag;
    }

    //创建目录
    public boolean makeDirectory(String dir) {
        boolean flag = true;
        try {
            flag = ftpClient.makeDirectory(dir);
            if (flag) {
                System.out.println("创建文件夹" + dir + " 成功！");

            } else {
                System.out.println("创建文件夹" + dir + " 失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /** * 下载文件 *
     * @param remotePath FTP服务器文件目录 *
     * @param filename 文件名称 *
     * @param localpath 下载后的文件路径 *
     * @return */
    public  boolean downloadFile(String remotePath, String filename, String localpath){
        boolean flag = false;
        OutputStream os=null;
        try {
            if(initFtpClient()!=null){
                //切换FTP目录
                ftpClient.changeWorkingDirectory(remotePath);
                FTPFile[] ftpFiles = ftpClient.listFiles();
                for(FTPFile file : ftpFiles){
                    if(filename.equalsIgnoreCase(file.getName())){
                        File localFile = new File(localpath + "/" + file.getName());
                        os = new FileOutputStream(localFile);
                        ftpClient.retrieveFile(file.getName(), os);
                        os.close();
                    }
                }
                ftpClient.logout();
                flag = true;
                System.out.println("下载文件成功");
            }
        } catch (Exception e) {
            System.out.println("下载文件失败");
            e.printStackTrace();
        } finally{
            if(ftpClient!=null && ftpClient.isConnected()){
                try{
                    ftpClient.disconnect();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            if(null != os){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }


    /** * 下载文件 *
     * @param remotePath FTP服务器文件目录 *
     * @param filename 文件名称 *
     * @return */
    public  InputStream getFileStream(String remotePath, String filename){
        boolean flag = false;
        try {
        	
            if(initFtpClient()!=null){
                //切换FTP目录
            	flag =ftpClient.changeWorkingDirectory(new String(remotePath.getBytes("GBK"), "iso-8859-1"));
                return ftpClient.retrieveFileStream(filename);
            }
        } catch (Exception e) {
            System.out.println("下载文件失败");
            e.printStackTrace();
        }
        return null;
    }

    /** * 删除文件 *
     * @param remotePath FTP服务器保存目录 *
     * @param filename 要删除的文件名称 *
     * @return */
    public boolean deleteFile(String remotePath, String filename){
        boolean flag = false;
        try {
            if(initFtpClient()!=null){
                //切换FTP目录
                ftpClient.changeWorkingDirectory(remotePath);
                ftpClient.dele(filename);
                ftpClient.logout();
                flag = true;
                System.out.println("删除文件成功");
            }
        } catch (Exception e) {
            System.out.println("删除文件失败");
            e.printStackTrace();
        } finally {
            if(ftpClient!=null && ftpClient.isConnected()){
                try{
                    ftpClient.disconnect();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }


    public static void main(String[] args) throws Exception{
        Map map = new HashMap();
        FTPUtils ftp =new FTPUtils();
        File targetFile=new File(ConfigureParser.getPropert("ftp.upload.path")+File.separator+ UUID.randomUUID()+".txt");
        ftp.uploadFileInputStream(targetFile,new FileInputStream(new File("D:\\ftptest\\存储过程.txt")),map);
        System.out.println(map);

//        ftp.deleteFile(ConfigureParser.getPropert("ftp.upload.path"),"4419019a-62bc-4b87-b590-c5e05f1c8363.txt");


//        ftp.uploadFileInputStream(ConfigureParser.getPropert("ftp.upload.path"), "存储过程.txt", new FileInputStream(new File("D:\\ftptest\\存储过程.txt")));
    }

}
