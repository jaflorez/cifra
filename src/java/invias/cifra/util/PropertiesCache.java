/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invias.cifra.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author jaflorez
 */
public class PropertiesCache {

    private final Properties configProp = new Properties();

    private PropertiesCache() {
        //Private constructor to restrict new instances
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("cifra.properties");
        
        try {
            configProp.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static class LazyHolder {

        private static final PropertiesCache INSTANCE = new PropertiesCache();
    }
    public static PropertiesCache getInstance() {
        return LazyHolder.INSTANCE;
    }
    public String getProperty(String key) {
        return configProp.getProperty(key);
    }
    public boolean containsKey(String key) {
        return configProp.containsKey(key);
    }

}
