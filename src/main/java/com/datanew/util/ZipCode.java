package com.datanew.util;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ZipCode {
    private ZipOutputStream zipOutputStream;
    private String encode;
    private OutputStream outputStream;

    public ZipCode(OutputStream out, String encode){
        this.outputStream=out;
        this.encode=encode;
        this.zipOutputStream=new ZipOutputStream(this.outputStream);
        this.zipOutputStream.setEncoding(encode);
    }
    public void write(String name,String content)  {
       try{
           this.zipOutputStream.putNextEntry(new ZipEntry(name));
           this.zipOutputStream.write(content.getBytes(encode));
           this.zipOutputStream.closeEntry();
       }catch (IOException e){
           e.printStackTrace();
       }
    }
    public void write(String name,byte[] content) {

    }
    public void write(String name, InputStream in){
        try{this.zipOutputStream.putNextEntry(new ZipEntry(name));
            int b;
            while((b= in.read())!=-1){
                this.zipOutputStream.write(b);
            }
            this.zipOutputStream.closeEntry();}catch (IOException e){
            e.printStackTrace();
        }
    }

    public ZipOutputStream getZipOutputStream() {
        return zipOutputStream;
    }



    public String getEncode() {
        return encode;
    }

    public void setEncode(String encode) {
        this.encode = encode;
        this.zipOutputStream.setEncoding(encode);
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void close()  {
//       try{
//           if(this.outputStream!=null)
//               this.outputStream.close();
//       }catch (Exception e){
//           e.printStackTrace();
//       }
       try{
           if(this.zipOutputStream!=null)
               this.zipOutputStream.close();
       }catch (IOException e){
           e.printStackTrace();
       }
    }

    public static void main(String[] args) {
//        System.out.println(new File( RegexUtil.formateDir("code/template/HqlGen.template")).getAbsolutePath());;
    }
}
