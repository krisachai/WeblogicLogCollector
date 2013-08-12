/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package krisa.util.collector;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import krisa.util.CompareDate;

/**
 *
 * @author KZ
 */
public class FilesLookup {

    List<String> serverList;
    FilenameFilter fileFilter;

    public FilesLookup(List<String> serverList, FilenameFilter fileFilter) {
        this.serverList = serverList;
        this.fileFilter = fileFilter;
    }

    public List<File> listFiles(int days) {
        List<File> targetFiles = new ArrayList<File>();
        for (String path : serverList) {
            File folder = new File(path);
            File[] fileList = folder.listFiles(fileFilter);
            System.out.println("==========");
            for (File file : fileList) {
                if (file.isFile()) {
                    if (CompareDate.compare(file.lastModified()) <= days) {
                        //files that less than days
                        System.out.println(file.getName());
                        targetFiles.add(file);
                    }
                }

            }
        }
        return targetFiles;
    }
}
