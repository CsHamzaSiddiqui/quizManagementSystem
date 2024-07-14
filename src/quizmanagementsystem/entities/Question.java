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
public class Question {
    private String id;
    private String subjectId;
    private String statement;
    private String options; // Oprions Seprated by %%
    private String answer; // A/B/C/D
    private Boolean deleted;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "Question{" + "id=" + id + ", subjectId=" + subjectId + ", statement=" + statement + ", options=" + options + ", answer=" + answer + ", deleted=" + deleted + ", database_Connection=" + database_Connection + '}';
    }
    
    Database_Connection database_Connection = new Database_Connection();
    
    public void save(){
        Connection con = database_Connection.db_connction();
        try {
            PreparedStatement ps = con.prepareStatement("Insert into question values (?, ?, ?, ?, ?, ?)");
            ps.setString(1, this.id);
            ps.setString(2, this.subjectId);
            ps.setString(3, this.statement);
            ps.setString(4, this.options);
            ps.setString(5, this.answer);
            ps.setBoolean(6, false);
            ps.executeUpdate();
            System.out.println("questionEntity: Question added successfully...");
            JOptionPane.showMessageDialog(null, "Question Added Successfully...");
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try {
                con.close();
                System.out.println("questionEntity: connection closed");
            } catch (SQLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void delete(){
        Connection con = database_Connection.db_connction();
        try {
            PreparedStatement ps = con.prepareStatement("update question set deleted = ? where id=?");
            ps.setBoolean(1, true);
            ps.setString(2, this.id);
            ps.executeUpdate();
            System.out.println("questionEntity: question deleted successfully...");
            JOptionPane.showMessageDialog(null, "Question Deleted Successfully...");
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try {
                con.close();
                System.out.println("questionEntity: connection closed");
            } catch (SQLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void deleteBySubject(String subjectId){
        Connection con = database_Connection.db_connction();
        try {
            PreparedStatement ps = con.prepareStatement("update question set deleted = ? where subjectId=?");
            ps.setBoolean(1, true);
            ps.setString(2, subjectId);
            ps.executeUpdate();
            System.out.println("questionEntity: question deleted successfully...");
//            JOptionPane.showMessageDialog(null, "Question Deleted Successfully...");
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try {
                con.close();
                System.out.println("questionEntity: connection closed");
            } catch (SQLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public List<Question> getAll(){
        Connection con = database_Connection.db_connction();
        List<Question> questionList = new ArrayList<>();
        try {
            
            PreparedStatement ps = con.prepareStatement("select * from question where deleted = ?");
            ps.setBoolean(1, false);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Question q = new Question();
                q.setId(rs.getString("id"));
                q.setSubjectId(rs.getString("subjectId"));
                q.setOptions(rs.getString("options"));
                q.setAnswer(rs.getString("answer"));
                q.setStatement(rs.getString("statement"));
                q.setDeleted(false);
                questionList.add(q);
            } 
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try {
                con.close();
                System.out.println("questionEntity: connection closed");
            } catch (SQLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return questionList;
    }
    
    public List<Question> getAllBySubject(String subjectId){
        Connection con = database_Connection.db_connction();
        List<Question> questionList = new ArrayList<>();
        try {
            
            PreparedStatement ps = con.prepareStatement("select * from question where subjectId = ? and deleted = ?");
            ps.setString(1, subjectId);
            ps.setBoolean(2, false);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Question q = new Question();
                q.setId(rs.getString("id"));
                q.setSubjectId(rs.getString("subjectId"));
                q.setOptions(rs.getString("options"));
                q.setAnswer(rs.getString("answer"));
                q.setStatement(rs.getString("statement"));
                q.setDeleted(false);
                questionList.add(q);
            } 
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try {
                con.close();
                System.out.println("questionEntity: connection closed");
            } catch (SQLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return questionList;
    }
    
    public void update(){
        Connection con = database_Connection.db_connction();
        try {
            PreparedStatement ps = con.prepareStatement("update question set statement=? , options=?, answer=?, subjectId=? where id=?");
            ps.setString(1, this.statement);
            ps.setString(2, this.options);
            ps.setString(3, this.answer);
            ps.setString(4, this.subjectId);
            ps.setString(5, this.id);
            ps.executeUpdate();
            System.out.println("questionEntity: question updated successfully...");
            JOptionPane.showMessageDialog(null, "Question Updated Successfully...");
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try {
                con.close();
                System.out.println("questionEntity: connection closed");
            } catch (SQLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
