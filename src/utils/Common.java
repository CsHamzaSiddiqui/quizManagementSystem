/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.UUID;
import javax.swing.JOptionPane;


/**
 *
 * @author Hassaan.Siddique
 */
public class Common {
    
    public static String generatePrimaryKey(){
        UUID uniqueID = UUID.randomUUID();
        return uniqueID.toString();
    }
    
    public static String getEnvVariable(String variableName) {
        String value = System.getenv(variableName);
        if (value != null) {
            System.out.println(variableName + ": " + value);
        } else {
            System.out.println(variableName + " environment variable is not set.");
            JOptionPane.showMessageDialog(null, variableName + " environment variable is not set.");
        }
        return value;
    }
    
    public static boolean isEmptyString(String str) {
        if(str == null || str.equals("")){
            return true;
        }
        return false;
    }
    
}
