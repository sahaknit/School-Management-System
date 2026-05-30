/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.TableModel;

/**
 *
 * @author MSI Gaming
 */
public class classroom {
  private int classroom_id;
  private String student_id;
  private String year;
  private String grade_id;
  private String semester;
  private int status;
  private String remarks;
  private String teacher_id;

    public classroom() {
    }

    public classroom(int classroom_id, String student_id, String year, String grade_id, String semester, int status, String remarks, String teacher_id) {
        this.classroom_id = classroom_id;
        this.student_id = student_id;
        this.year = year;
        this.grade_id = grade_id;
        this.semester = semester;
        this.status = status;
        this.remarks = remarks;
        this.teacher_id = teacher_id;
    }
  private PreparedStatement ps;
    private TableModel mod;
    private ResultSet r;
    private String sql;
    
      public ResultSet selectstudentid()throws Exception{
        sql = "SELECT CONCAT(student_id,' - ',lname) AS student FROM tbstudent";
        ps=connectionDB.getConnection().prepareStatement(sql);
        r=ps.executeQuery();
        return r;
    }
       public ResultSet selectgradeid()throws Exception{
        sql = "SELECT CONCAT(grade_id,' - ',name) AS grade FROM tbgrade";
        ps=connectionDB.getConnection().prepareStatement(sql);
        r=ps.executeQuery();
        return r;
    }
       public ResultSet selectteacherid()throws Exception{
        sql = "SELECT CONCAT(teacher_id,' - ',lname) AS teacher FROM tbteacher";
        ps=connectionDB.getConnection().prepareStatement(sql);
        r=ps.executeQuery();
        return r;
    }
       public Boolean insert(){
        try {
            sql="INSERT INTO tbclassroom(student_id,year,grade_id,semester, Status, remarks, teacher_id) VALUES(?,?,?,?,?,?,?)";
            ps=connectionDB.getConnection().prepareStatement(sql);
            ps.setString(1,student_id);
            ps.setString(2,year);
            ps.setString(3,grade_id);
            ps.setString(4, semester);
            ps.setInt(5, status);
            ps.setString(6, remarks);
            ps.setString(7, teacher_id);
            
            int index = ps.executeUpdate();
            if(index > 0){
                return true;
                
            }else{
                return false;
            }
        } catch (Exception e) {
            
            e.printStackTrace();
            return false;
        }
        
    }
        public Boolean update(){
        try {
            sql="UPDATE tbclassroom SET student_id=?,year=?,grade_id=?,semester=?, Status=?, remarks=?, teacher_id=? WHERE classroom_id=?";
            ps=connectionDB.getConnection().prepareStatement(sql);
            ps.setString(1,student_id);
            ps.setString(2,year);
            ps.setString(3,grade_id);
            ps.setString(4, semester);
            ps.setInt(5, status);
            ps.setString(6, remarks);
            ps.setString(7, teacher_id);
            ps.setInt(8, classroom_id);
          
            int index = ps.executeUpdate();
            if(index > 0){
                return true;
                
            }else{
                return false;
            }
        } catch (Exception e) {
            
            e.printStackTrace();
            return false;
        }
        
    }
}
