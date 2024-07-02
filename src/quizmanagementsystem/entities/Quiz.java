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
public class Quiz {
    private String id;
    private String userId;
    private String subjectId;
    private String questionsIds; // Questions Ids seprated By %%
    private String answers;   
    private int totalMarks;
    private int obtainedMarks;
    private Boolean deleted;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getQuestionsIds() {
        return questionsIds;
    }

    public void setQuestionsIds(String questionsIds) {
        this.questionsIds = questionsIds;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    public int getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(int totalMarks) {
        this.totalMarks = totalMarks;
    }

    public int getObtainedMarks() {
        return obtainedMarks;
    }

    public void setObtainedMarks(int obtainedMarks) {
        this.obtainedMarks = obtainedMarks;
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
            PreparedStatement ps = con.prepareStatement("Insert into quiz values (?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, this.id);
            ps.setString(2, this.userId);
            ps.setString(3, this.subjectId);
            ps.setString(4, this.questionsIds);
            ps.setString(5, this.answers);
            ps.setInt(6, this.totalMarks);
            ps.setInt(7, this.obtainedMarks);
            ps.setBoolean(8, false);
            ps.executeUpdate();
            System.out.println("quizEntity: Quiz added successfully...");
//            JOptionPane.showMessageDialog(null, "Question Added Successfully...");
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try {
                con.close();
                System.out.println("quizEntity: connection closed");
            } catch (SQLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public List<Quiz> getAllByUserId(String userId){
        Connection con = database_Connection.db_connction();
        List<Quiz> quizList = new ArrayList<>();
        try {
            
            PreparedStatement ps = con.prepareStatement("select * from quiz where userId = ? and deleted = ?");
            ps.setString(1, userId);
            ps.setBoolean(2, false);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Quiz q = new Quiz();
                q.setId(rs.getString("id"));
                q.setUserId(rs.getString("userId"));
                q.setSubjectId(rs.getString("subjectId"));
                q.setAnswers(rs.getString("answers"));
                q.setQuestionsIds(rs.getString("questionIds"));
                q.setTotalMarks(rs.getInt("totalMarks"));
                q.setObtainedMarks(rs.getInt("obtainedMarks"));
                q.setDeleted(false);
                quizList.add(q);
            } 
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try {
                con.close();
                System.out.println("quizEntity: connection closed");
            } catch (SQLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return quizList;
    }
    
    
}
