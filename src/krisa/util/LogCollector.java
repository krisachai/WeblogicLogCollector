/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package krisa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import krisa.util.collector.SvGCLog;
import krisa.util.collector.ThreaddumpLog;
import org.joda.time.DateTime;

/**
 *
 * @author KZ
 */
public class LogCollector {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Loding Configuration");
        Config conf = new Config();
        conf.load();
        String domainName = conf.getWLSDomainName();
        String numInstances = conf.getNumInstances();
        String domainPath = conf.getWLSDomainPath();
        String threaddumpPath = conf.getThreaddumpPath();
        int days = 0;
        if (args.length > 0) {
            days = Integer.parseInt(args[0]);
        }
        List<File> targetFiles = new ArrayList();
        SvGCLog gc = new SvGCLog(domainName, domainPath, numInstances, days);
        targetFiles = gc.process();
        ThreaddumpLog tdl = new ThreaddumpLog(threaddumpPath, days);

        targetFiles.addAll(tdl.process());
        DateTime today = new DateTime();
        
        zipIt(domainName+"_"+today.getDayOfMonth()+"_"+today.getMonthOfYear()+"_"+today.getYear()+".zip", targetFiles);
        
    }
        public static void zipIt(String zipFile,List<File> fileList){
 
     byte[] buffer = new byte[1024];
 
     try{
 
    	FileOutputStream fos = new FileOutputStream(zipFile);
    	ZipOutputStream zos = new ZipOutputStream(fos);
 
    	System.out.println("Output to Zip : " + zipFile);
 
    	for(File file :  fileList){
 
    		System.out.println("File Added : " + file.getName());
    		ZipEntry ze= new ZipEntry(file.getName());
        	zos.putNextEntry(ze);
 
        	FileInputStream in = 
                       new FileInputStream(file);
 
        	int len;
        	while ((len = in.read(buffer)) > 0) {
        		zos.write(buffer, 0, len);
        	}
 
        	in.close();
    	}
 
    	zos.closeEntry();
    	//remember close it
    	zos.close();
 
    	System.out.println("Done");
    }catch(IOException ex){
       ex.printStackTrace();   
    }
   }
}
