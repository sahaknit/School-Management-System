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
public class teacher {
    private int id;
    private String email;
    private String firstname;
    private String lastname;
    private String DOB;
    private String phone;
    private int status;

    public teacher() {
    }

    public teacher(int id, String email, String firstname, String lastname, String DOB, String phone, int status) {
        this.id = id;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.DOB = DOB;
        this.phone = phone;
        this.status = status;
    }


    private PreparedStatement ps;
    private TableModel mod;
    private ResultSet r;
    private String sql;
    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    public boolean insert(){
        try {
            sql="INSERT INTO tbteacher(email,fname,lname,Dob, phone, status) VALUES(?,?,?,?,?,?)";
            ps = connectionDB.getConnection().prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, firstname);
            ps.setString(3, lastname);
            ps.setString(4, DOB);
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
    public Boolean update(){
        try {
            sql="UPDATE tbteacher SET email=?,fname=?,lname=?,Dob=?, phone=?, status=? WHERE teacher_id=?";
            ps = connectionDB.getConnection().prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, firstname);
            ps.setString(3, lastname);
            ps.setString(4, DOB);
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
            sql="DELETE FROM tbteacher WHERE teacher_id=?";
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
    public TableModel search(String value){
        try {
            sql="SELECT * FROM tbteacher WHERE CONCAT(email,fname,lname,Dob, phone) LIKE ?";
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
