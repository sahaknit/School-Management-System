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
public class attendance {
   private int attendance_id;
   private String Date;
   private String student_id;
   private int status;
   private String remark;

    public attendance() {
    }

    public attendance(int attendance_id, String Date, String student_id, int status, String remark) {
        this.attendance_id = attendance_id;
        this.Date = Date;
        this.student_id = student_id;
        this.status = status;
        this.remark = remark;
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
     
   public Boolean insert(){
        try {
            sql="INSERT INTO tbattendance(date, student_id, status, remark) VALUES(?,?,?,?)";
            ps=connectionDB.getConnection().prepareStatement(sql);
            ps.setString(1,Date);
            ps.setString(2,student_id);
            ps.setInt(3,status);
            ps.setString(4, remark);
           
            
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
            sql="UPDATE tbclassroom SET date=?, student_id=?, status=?, remark=? WHERE attendance_id=?";
            ps=connectionDB.getConnection().prepareStatement(sql);
            ps.setString(1,Date);
            ps.setString(2,student_id);
            ps.setInt(3,status);
            ps.setString(4, remark);
            ps.setInt(5, attendance_id);
            
          
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
    public TableModel search(String value){
        try {
            sql="SELECT * FROM tbattendance WHERE CONCAT(date, student_id, status, remark) LIKE ?";
            ps = connectionDB.getConnection().prepareStatement(sql);
            ps.setString(1, "%"+value+"%");
            r = ps.executeQuery();
            mod = DbUtils.resultSetToTableModel(r);
            return mod;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
