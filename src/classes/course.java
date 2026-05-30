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
public class course {
    private int course_id;
    private String name;
    private String desc;
    private String grade_id;

    public course() {
    }

    public course(int course_id, String name, String desc, String grade_id) {
        this.course_id = course_id;
        this.name = name;
        this.desc = desc;
        this.grade_id = grade_id;
    }
    
    private PreparedStatement ps;
    private TableModel mod;
    private ResultSet r;
    private String sql;
    
    public ResultSet selectgradeid()throws Exception{
        sql = "SELECT CONCAT(grade_id,' - ',name) AS grade FROM tbgrade";
        ps=connectionDB.getConnection().prepareStatement(sql);
        r=ps.executeQuery();
        return r;
    }
     public Boolean insert(){
        try {
            sql="INSERT INTO tbcourse(name,description,grade_id) VALUES(?,?,?)";
            ps=connectionDB.getConnection().prepareStatement(sql);
            ps.setString(1,name);
            ps.setString(2,desc);
            ps.setString(3,grade_id);
            
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
            sql="UPDATE tbcourse SET name=?,description=?,grade_id=? WHERE course_id=?";
            ps=connectionDB.getConnection().prepareStatement(sql);
            ps.setString(1,name);
            ps.setString(2,desc);
            ps.setString(3,grade_id);
            ps.setInt(4, course_id);
          
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
