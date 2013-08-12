/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package krisa.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import krisa.util.collector.SvGCLog;
import org.yaml.snakeyaml.Yaml;
 

/**
 *
 * @author KZ
 */
public class Config {

    private Map conf = null;
    
    public void load() {

        FileReader fr = null;
        try {
            Yaml yml = new Yaml();
            //log.info("reading:"+prefix+"config.yml");
            fr = new FileReader("config.yml");
            //fr = new FileReader("g:\\config.yml");
            conf = (Map) yml.load(fr);

        } catch (FileNotFoundException ex) {
            //log.error("Exception thrown", ex);
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fr.close();
            } catch (IOException ex) {
                Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public String getWLSDomainName(){
        
        return (String) conf.get("wlsdomain");
    }
    public String getWLSDomainPath(){
        
        return (String) conf.get("domainpath");
    }
    public String getThreaddumpPath(){
        
        return (String) conf.get("threaddump");
    }
    public String getNumInstances(){
        
        return (String) conf.get("num_instances");
    }
    public static void main(String[] args){
    Config conf = new Config();
    conf.load();
        System.out.println(conf.getThreaddumpPath());
        System.out.println(conf.getWLSDomainName());
        System.out.println(conf.getWLSDomainPath());
        System.out.println(conf.getNumInstances());
        
       
    }
}
