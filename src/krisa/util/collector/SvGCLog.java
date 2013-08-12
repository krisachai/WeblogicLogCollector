/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package krisa.util.collector;

import java.io.File;
import java.io.FilenameFilter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import krisa.util.CompareDate;

/**
 *
 * @author KZ
 */
public class SvGCLog {

    String domainName, domainPath, numInstance;
    FilenameFilter gcfilter;
    int days;
    public SvGCLog(String domainName, String domainPath, String numInstance,int days) {
        this.domainName = domainName;
        this.domainPath = domainPath;
        this.numInstance = numInstance;
        this.days = days;
        gcfilter = new FilenameFilter() {
            @Override
            public boolean accept(File file, String name) {

                if (name.contains("gc")||name.contains("MS")) {

                    return true;
                }
                return false;
            }
        };
    }

    public List<File> process() {
        List<String> serverList = new ArrayList();
        for (int i = 1; i < Integer.parseInt(numInstance); i++) {
            String server_path = domainPath + domainName + "/servers/" + domainName + "_MS" + i + "/logs";
            System.out.println(server_path);
            serverList.add(server_path);
        }
        //Getting target files
        FilesLookup lookup = new FilesLookup(serverList, gcfilter);
        List<File> files = lookup.listFiles(days);
        for (File file : files) {
            System.out.println(file.getName());
           
        }
        
        return files;
    }
}
