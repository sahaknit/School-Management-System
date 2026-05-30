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
public class parent {
    private int id;
    private String email;
    private String firstname;
    private String lastname;
    private String Dob; 
    private String phone;
    private int status;

    public parent() {
    }

    public parent(int id, String email, String firstname, String lastname, String Dob, String phone, int status) {
        this.id = id;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.Dob = Dob;
        this.phone = phone;
        this.status = status;
    }

   
    
    
    private PreparedStatement ps;
    private TableModel mod;
    private ResultSet r;
    private String sql;
    
     public boolean insert(){
        try {
            sql="INSERT INTO tbparent(email,fname,lname,Dob, phone, status) VALUES(?,?,?,?,?,?)";
            ps = connectionDB.getConnection().prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, firstname);
            ps.setString(3, lastname);
            ps.setString(4, Dob);
            ps.setString(5, phone);
            ps.setInt(6, status);
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
     public TableModel select(){
        try {
            sql = "SELECT * FROM tbparent";
            ps = connectionDB.getConnection().prepareStatement(sql);
            r = ps.executeQuery();
            mod = DbUtils.resultSetToTableModel(r);
            return mod;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
     public TableModel search(String value){
        try {
            sql="SELECT * FROM tbparent WHERE CONCAT(email,fname,lname,Dob, phone, status) LIKE ?";
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
            sql="UPDATE tbparent SET email=?,fname=?,lname=?,Dob=?, phone=?, status=? WHERE parent_id=?";
            ps = connectionDB.getConnection().prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, firstname);
            ps.setString(3, lastname);
            ps.setString(4, Dob);
            ps.setString(5, phone);
            ps.setInt(6, status);
            ps.setInt(7, id);
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
      public Boolean delete(){
        try {
            sql="DELETE FROM tbparent WHERE parent_id=?";
            ps = connectionDB.getConnection().prepareStatement(sql);
            ps.setInt(1, id);
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

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
}
