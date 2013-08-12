/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package krisa.util.collector;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author KZ
 */
public class ThreaddumpLog {

    String path;
    int days;
    FilenameFilter fileFilter;

    public ThreaddumpLog(String path, int days) {
        this.path = path;
        this.days = days;
        fileFilter = new FilenameFilter() {
            @Override
            public boolean accept(File file, String string) {
                return true;
            }
        };
    }

    public List<File> process() {
        List<String> serverList = new ArrayList();
        serverList.add(path);
        FilesLookup lookup = new FilesLookup(serverList, fileFilter);
        List<File> fileList = lookup.listFiles(days);
        for (File file : fileList) {
            System.out.println(file.getName());
        }
        return fileList;
        
    }

    public static void main(String[] args) {
    }
}
