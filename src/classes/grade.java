/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author MSI Gaming
 */
public class grade {
  private int gradeid;
  private String gradename;
  private String desp;

    public grade() {
    }

    public grade(int gradeid, String gradename, String desp) {
        this.gradeid = gradeid;
        this.gradename = gradename;
        this.desp = desp;
    }

   
    private PreparedStatement ps;
    private TableModel mod;
    private ResultSet r;
    private String sql;
    public boolean insert(){
        try {
            sql="INSERT INTO tbgrade(name, description) VALUES(?,?)";
            
            ps = connectionDB.getConnection().prepareStatement(sql);
            ps.setString(1, gradename);
            ps.setString(2, desp);
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
            sql="UPDATE tbgrade SET name=?,description=? WHERE grade_id=?";
            ps = connectionDB.getConnection().prepareStatement(sql);
            ps.setString(1, gradename);
            ps.setString(2, desp);
            ps.setInt(3, gradeid);
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
            sql="DELETE FROM tbgrade WHERE grade_id=?";
            ps = connectionDB.getConnection().prepareStatement(sql);
            ps.setInt(1, gradeid);
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
     * @param gradeid the gradeid to set
     */
    public void setGradeid(int gradeid) {
        this.gradeid = gradeid;
    }
}
