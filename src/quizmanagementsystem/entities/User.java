/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizmanagementsystem.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import services.Database_Connection;

/**
 *
 * @author Hassaan.Siddique
 */
public class User {
    private String id;
    private String name;
    private String userName;
    private String password;
    private String email;
    private String role;
    private Boolean deleted;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
    
    
    Database_Connection database_Connection = new Database_Connection();
    
    public void save(){
        Connection con = database_Connection.db_connction();
        try {
            PreparedStatement ps = con.prepareStatement("Insert into user values (?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, this.id);
            ps.setString(2, this.name);
            ps.setString(3, this.userName);
            ps.setString(4, this.password);
            ps.setString(5, this.email);
            ps.setString(6, this.role);
            ps.setBoolean(7, false);
            ps.executeUpdate();
            System.out.println("userEntity: user added successfully...");
            JOptionPane.showMessageDialog(null, "User Registered Successfully...");
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try {
                con.close();
                System.out.println("userEntity: connection closed");
            } catch (SQLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void delete(){
        Connection con = database_Connection.db_connction();
        try {
            PreparedStatement ps = con.prepareStatement("update user set deleted = ? where id=?");
            ps.setBoolean(1, true);
            ps.setString(2, this.id);
            ps.executeUpdate();
            System.out.println("userEntity: user deleted successfully...");
            JOptionPane.showMessageDialog(null, "User Deleted Successfully...");
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try {
                con.close();
                System.out.println("userEntity: connection closed");
            } catch (SQLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public Boolean login(String userName, String password){
        Connection con = database_Connection.db_connction();
        try {
            
            PreparedStatement ps = con.prepareStatement("select * from user where userName = ? and password = ? and deleted = ?");
            ps.setString(1, userName);
            ps.setString(2, password);
            ps.setBoolean(3, false);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                System.out.println("userEntity: login successfully...");
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try {
                con.close();
                System.out.println("userEntity: connection closed");
            } catch (SQLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
}
