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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import services.Database_Connection;

/**
 *
 * @author Hassaan.Siddique
 */
public class Subject {
    private String id;
    private String name;
    private String quizInstructions;
    private int quizTime;
    private int quizLength;
    private int passingMarks;
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

    public String getQuizInstructions() {
        return quizInstructions;
    }

    public void setQuizInstructions(String quizInstructions) {
        this.quizInstructions = quizInstructions;
    }

    public int getQuizTime() {
        return quizTime;
    }

    public void setQuizTime(int quizTime) {
        this.quizTime = quizTime;
    }

    public int getQuizLength() {
        return quizLength;
    }

    public void setQuizLength(int quizLength) {
        this.quizLength = quizLength;
    }

    public int getPassingMarks() {
        return passingMarks;
    }

    public void setPassingMarks(int passingMarks) {
        this.passingMarks = passingMarks;
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
            PreparedStatement ps = con.prepareStatement("Insert into quiz values (?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, this.id);
            ps.setString(2, this.name);
            ps.setString(3, this.quizInstructions);
            ps.setInt(4, this.quizTime);
            ps.setInt(5, this.quizLength);
            ps.setInt(6, this.passingMarks);
            ps.setBoolean(7, false);
            ps.executeUpdate();
            System.out.println("subjectEntity: Subject added successfully...");
            JOptionPane.showMessageDialog(null, "Subject Added Successfully...");
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try {
                con.close();
                System.out.println("subjectEntity: connection closed");
            } catch (SQLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public List<Subject> getAllByUserId(String userId){
        Connection con = database_Connection.db_connction();
        List<Subject> subjectList = new ArrayList<>();
        try {
            
            PreparedStatement ps = con.prepareStatement("select * from subject where deleted = ?");
            ps.setBoolean(1, false);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Subject subject = new Subject();
                subject.setId(rs.getString("id"));
                subject.setName(rs.getString("name"));
                subject.setQuizInstructions(rs.getString("quizInstructions"));
                subject.setQuizLength(rs.getInt("quizLength"));
                subject.setQuizTime(rs.getInt("quizTime"));
                subject.setPassingMarks(rs.getInt("passingMarks"));
                subject.setDeleted(false);
                subjectList.add(subject);
            } 
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try {
                con.close();
                System.out.println("subjectEntity: connection closed");
            } catch (SQLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return subjectList;
    }
    
    public void delete(){
        Connection con = database_Connection.db_connction();
        try {
            PreparedStatement ps = con.prepareStatement("update subject set deleted = ? where id=?");
            ps.setBoolean(1, true);
            ps.setString(2, this.id);
            ps.executeUpdate();
            System.out.println("subjectEntity: subject deleted successfully...");
            JOptionPane.showMessageDialog(null, "Subject Deleted Successfully...");
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try {
                con.close();
                System.out.println("subjectEntity: connection closed");
            } catch (SQLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
