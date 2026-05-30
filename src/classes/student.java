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
public class student {
    private int Student_id;
    private String email;
    private String firstname;
    private String lastname;
    private String DOB;
    private String phone;
    private String parent_id;
    private String DOJ;
    private int status;

    public student() {
    }

    public student(int Student_id, String email, String firstname, String lastname, String DOB, String phone, String parent_id, String DOJ, int status) {
        this.Student_id = Student_id;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.DOB = DOB;
        this.phone = phone;
        this.parent_id = parent_id;
        this.DOJ = DOJ;
        this.status = status;
    }
      
    private PreparedStatement ps;
    private TableModel mod;
    private ResultSet r;
    private String sql;
    
    public ResultSet selectparentid() throws Exception {
        // We changed \" - \" to ' - ' so the cloud database will accept it
        sql = "SELECT CONCAT(parent_id, ' - ', lname) AS parentid FROM tbparent";
        ps = connectionDB.getConnection().prepareStatement(sql);
        r = ps.executeQuery();
        return r;
    }
     public Boolean insert(){
        try {
            sql="INSERT INTO tbstudent(email,fname,lname,dob, phone,parent_id,date_of_join,status) VALUES(?,?,?,?,?,?,?,?)";
            ps=connectionDB.getConnection().prepareStatement(sql);
            ps.setString(1,email);
            ps.setString(2,firstname);
            ps.setString(3,lastname);
            ps.setString(4, DOB);
            ps.setString(5, phone);
            ps.setString(6, parent_id);
            ps.setString(7, DOJ);
            ps.setInt(8, status);
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
            sql="SELECT * FROM tbstudent WHERE CONCAT(email,fname,lname,dob, phone,parent_id,date_of_join,status) LIKE ?";
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
     public Boolean update(){
        try {
            sql="UPDATE tbstudent SET email=?,fname=?,lname=?,dob=?, phone=?,parent_id=?,date_of_join=?,status=? WHERE student_id=?";
            ps=connectionDB.getConnection().prepareStatement(sql);
            ps.setString(1,email);
            ps.setString(2,firstname);
            ps.setString(3,lastname);
            ps.setString(4, DOB);
            ps.setString(5, phone);
            ps.setString(6, parent_id);
            ps.setString(7, DOJ);
            ps.setInt(8, status);
            ps.setInt(9, Student_id);
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
