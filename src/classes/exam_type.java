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
public class exam_type {
   private int id;
   private String name;
   private String desc;

    public exam_type() {
    }

    public exam_type(int id, String name, String desc) {
        this.id = id;
        this.name = name;
        this.desc = desc;
    }
    private PreparedStatement ps;
    private TableModel mod;
    private ResultSet r;
    private String sql;
    
     public boolean insert(){
        try {
            sql="INSERT INTO tbexam_type(name,description) VALUES(?,?)";
            ps = connectionDB.getConnection().prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, desc);
         
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
            sql="UPDATE tbexam_type SET name=?,description=? WHERE exam_type_id=?";
            ps = connectionDB.getConnection().prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, desc);
            ps.setInt(3, id);
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
            sql="DELETE FROM tbexam_type WHERE exam_type_id=?";
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
