/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author MSI Gaming
 */
public class Exam_result {
    private String exam_id;
    private String student_id;
    private String course_id;
    private String marks;

    public Exam_result() {
    }

    public Exam_result(String exam_id, String student_id, String course_id, String marks) {
        this.exam_id = exam_id;
        this.student_id = student_id;
        this.course_id = course_id;
        this.marks = marks;
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
      public ResultSet selectcourseid()throws Exception{
        sql = "SELECT CONCAT(course_id,' - ',name) AS course FROM tbcourse";
        ps=connectionDB.getConnection().prepareStatement(sql);
        r=ps.executeQuery();
        return r;
    }
      public ResultSet selectexamid()throws Exception{
        sql = "SELECT CONCAT(exam_id,' - ',name) AS exam FROM tbexam";
        ps=connectionDB.getConnection().prepareStatement(sql);
        r=ps.executeQuery();
        return r;
    }
    public Boolean insert(){
        try {
            // FIX: Explicitly list the exact 4 columns we are inserting data into!
            sql = "INSERT INTO tbexam_result (exam_id, student_id, course_id, marks) VALUES (?,?,?,?)";
            
            ps = connectionDB.getConnection().prepareStatement(sql);
            ps.setString(1, exam_id);
            ps.setString(2, student_id);
            ps.setString(3, course_id);
            ps.setString(4, marks);
            
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
