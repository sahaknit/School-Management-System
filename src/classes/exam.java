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
public class exam {
 private int examid;
 private String examtypeid;
 private String name;
 private String start_date;

    public exam() {
    }

    public exam(int examid, String examtypeid, String name, String star_date) {
        this.examid = examid;
        this.examtypeid = examtypeid;
        this.name = name;
        this.start_date = star_date;
    }
 
    private PreparedStatement ps;
    private TableModel mod;
    private ResultSet r;
    private String sql;
    
    
    public ResultSet selectexamtype()throws Exception{
        sql = "SELECT CONCAT(exam_type_id,' - ',name) AS examtype FROM tbexam_type";
        ps=connectionDB.getConnection().prepareStatement(sql);
        r=ps.executeQuery();
        return r;
    }
    public Boolean insert(){
        try {
            sql="INSERT INTO tbexam(exam_type_id, name, start_date) VALUES(?,?,?)";
            ps=connectionDB.getConnection().prepareStatement(sql);
            ps.setString(1,examtypeid);
            ps.setString(2,name);
            ps.setString(3,start_date);
            
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
            sql="UPDATE tbexam SET exam_type_id=?, name=?, start_date=? WHERE exam_id=?";
            ps=connectionDB.getConnection().prepareStatement(sql);
            ps.setString(1,examtypeid);
            ps.setString(2,name);
            ps.setString(3,start_date);
            ps.setInt(4, examid);
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
