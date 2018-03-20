package org.ingservicios.IST1718_Practica1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import jdk.nashorn.internal.ir.RuntimeNode.Request;

public class DAOUsuarios {
//Conexión con base de datos
		private String DRIVER_MYSQL = "com.mysql.jdbc.Driver";
		private String URL_MYSQL = "jdbc:mysql://localhost:3306/usuarios";
		private Connection conn;
		public DAOUsuarios(){
		loadDriver();
		}
		private void loadDriver(){
		try {
		Class.forName(DRIVER_MYSQL);
		} catch (ClassNotFoundException e) { e.printStackTrace();} }
		private void getConnect(){
		try {
		conn = DriverManager.getConnection(URL_MYSQL,"root","");
		} catch (SQLException e) { e.printStackTrace();}
		}
		
		public ArrayList<DTOUsuarios> leeUsuarios(){
			getConnect();
			String sql = "SELECT * FROM usuarios";
			String nombre, password, email, dni;
			ArrayList<DTOUsuarios> listout = new ArrayList<DTOUsuarios>();
			Statement stm = null;
			ResultSet rs = null;
			try{
			stm = conn.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()){
			nombre=rs.getString("Nombre");
			password = rs.getString("Password");
			email=rs.getString("Email");
			dni=rs.getString("DNI");
			DTOUsuarios usuario = new
			DTOUsuarios(nombre, password, email, dni);
			listout.add(usuario);
			}
			} catch(SQLException e){System.out.println(e);}
			finally{
				if (stm!=null) {
					try{ stm.close(); } catch(SQLException e){e.printStackTrace();}
					}
					if (conn!=null) {
					try{ conn.close(); } catch(SQLException e){e.printStackTrace(); }
					}
					}
			return listout;

}
		public boolean buscarUsuario(String nombre, String password) {
			loadDriver();
			getConnect();
			String sql = "SELECT * FROM usuarios WHERE Nombre='"+nombre+"' && Password='"+password+"';";
			Statement stm = null;
			ResultSet rs = null;
			try{
				stm = conn.createStatement();
				rs = stm.executeQuery(sql);
				} catch(SQLException e){System.out.println(e);}
				finally{
					if (stm!=null) {
						try{ stm.close(); } catch(SQLException e){e.printStackTrace();}
						}
						if (conn!=null) {
						try{ conn.close(); } catch(SQLException e){e.printStackTrace(); }
						}
						}
			return false;
		}
		
		//Añadir usuario
		public void addUsuario(String nombre, String password, String email, String dni) {
			getConnect();
			String sql="INSERT INTO usuarios (Nombre, Password, Email, DNI)"
					+ " VALUES ('"+nombre+"','"+password+"','"+email+"','"+dni+"');";
			PreparedStatement stmt = null;
			try {
			stmt = conn.prepareStatement(sql);
			//Método .execute() para operaciones de cualquier tipo, sin devolver objeto
			//ResultSet: INSERT, UPDATE, DELETE
			stmt.execute();
			}catch (Exception e) { System.out.println(e); }
			finally{
				if (stmt!=null) {
					try{ stmt.close(); } catch(SQLException e){e.printStackTrace();}
				}
				if (conn!=null) {
					try{ conn.close(); } catch(SQLException e){e.printStackTrace(); }
				}
			}
	
		}
}

