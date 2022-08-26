/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Controlador.Producto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author andre
 */
public class ModeloProducto {
    Connection c = null;
    Statement stmt = null;
    public void creaTabla(){
           
        try{
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:database.db");
            System.out.println("la conexion fue extisosa");
            
        stmt = c.createStatement();
        String sql = "CREATE TABLE productos" + 
                "(ID INT PRIMARY KEY    NOT NULL," +
                 "NOMBRE            TEXT   NOT NULL," +
                "TEMPERATURA      DOUBLE    NOT NULL," +
                "VALORBASE        DOUBLE NOT NULL)";
        stmt.executeUpdate(sql);
        stmt.close();
        c.close();
        }catch(Exception e){
        System.err.println(e.getClass().getName() + ":" + e.getMessage() );
        }  
        System.out.println("Se creo la tabla");
    }
    
    public void insertarProducto(Producto p){
               
        try{
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:database.db");
            System.out.println("la conexion fue extisosa");
            
        stmt = c.createStatement();
        String id = p.getId();
        String nombre = p.getNombre();
        double temperatura = p.getTemperatura();
        double valorbase = p.getValorBase();
        
        String sql = "insert into productos(id, nombre, temperatura, valorbase) VALUES ('" + id + "','" + nombre + "'," + temperatura + "," + valorbase + ")";
        System.out.println(sql);
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        }catch(Exception e){
        System.err.println(e.getClass().getName() + ":" + e.getMessage() );
        }  
        System.out.println("Se inserto dato en la tabla");
    }
    
      public Producto buscarProducto(int id){
      Producto p = new Producto();
       try {
       Class.forName("org.sqlite.JDBC");
       c = DriverManager.getConnection("jdbc:sqlite:database.db"); 
          stmt = c.createStatement();
               
         String sql = "select * from productos where id=" + id;
           ResultSet rs  = stmt.executeQuery(sql);
          while (rs.next()){
           p.setId(rs.getString("id"));
           p.setNombre(rs.getString("nombre"));
           p.setTemperatura(rs.getDouble("temperatura"));
           p.setValorBase(rs.getDouble("valorBase"));
          
          }
        
         
           stmt.close();
           c.close();
           
       }catch (Exception e){
       System.err.println(e.getClass().getName() + ": " + e.getMessage() );
       } 
     
         return p ;
     }
    
    
     public List<Producto> leerProductos(){ 
          List<Producto> listaProductos = new ArrayList();
       try {
       Class.forName("org.sqlite.JDBC");
       c = DriverManager.getConnection("jdbc:sqlite:database.db");     

         stmt = c.createStatement();
         String sql = "select * from productos"; 
          
           ResultSet rs  = stmt.executeQuery(sql);
           
           while (rs.next()){
           String id = rs.getString("id");
           String nombre = rs.getString("nombre");
           double temperatura = rs.getDouble("temperatura");
           double valorbase = rs.getDouble("valorbase");
           
           Producto p = new Producto(id, nombre, temperatura, valorbase);
           
           listaProductos.add(p);   
          
           
           }
           
           stmt.close();
           c.close();
           
       }catch (Exception e){
       System.err.println(e.getClass().getName() + ": falta la clase " + e.getMessage() );
       } 
    
       return listaProductos;
    }
    
     
      public int actualizarDatos(Producto p, int id){
    int resultado=-1;
           try {
        Class.forName("org.sqlite.JDBC");
       c = DriverManager.getConnection("jdbc:sqlite:database.db");    
           
          stmt = c.createStatement();
          
         String nombre = p.getNombre();
         double temperatura = p.getTemperatura();
         double valorBase = p.getValorBase();
         
         String sql = "update productos set nombre='" + nombre + "',temperatura="+ temperatura + ",valorBase=" + valorBase + " where id=" + id;
       
         resultado = stmt.executeUpdate(sql);
           stmt.close();
           c.close();
           
       }catch (Exception e){
       System.err.println(e.getClass().getName() + ": " + e.getMessage() );
       } 
    
          return resultado;
    
    }
      
      
       public int borrarDatos(int id){
    
             int resultado=-1;
           try {
       Class.forName("org.sqlite.JDBC");
       c = DriverManager.getConnection("jdbc:sqlite:database.db");    
           
          stmt = c.createStatement();
         
         String sql = "delete from productos where id=" + id;
      
         resultado = stmt.executeUpdate(sql);
           stmt.close();
           c.close();
           
       }catch (Exception e){
       System.err.println(e.getClass().getName() + ": " + e.getMessage() );
       } 
    
          return resultado;
    
    
    
    }
}
