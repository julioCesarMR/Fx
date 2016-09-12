package pe.edu.system.jcmr.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SQLConnect {

	Connection cn=null;


	public SQLConnect(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","mysql");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public String Ejecutar(String SQL,Object[] arreglo){
		String result="";
		try {
			PreparedStatement cmd=cn.prepareStatement(SQL);
			//SQL="insert into ciudad values(?,?)";
			for(int i=0;i<arreglo.length;i++){
				cmd.setObject(i+1, arreglo[i]);
			}
			int f=cmd.executeUpdate();
			result="Se afectaron "+f+" filas";
			 if (cmd != null)  cmd.close();
		     if (cn != null)   cn.close();
		} catch (Exception e) {
			// TODO: handle exception
			result ="Error "+e.getMessage();
		}


		return result;
	}

	public ResultSet Listado(String SQL,Object[] arreglo){
		ResultSet rs=null;
		try {
			PreparedStatement cmd=cn.prepareStatement(SQL);
			for(int i=0;i<arreglo.length;i++){
				cmd.setObject(i+1, arreglo[i]);
			}
			rs=cmd.executeQuery();

			if (cn != null) cn.close();

			if (cmd != null) cmd.close();

			if (rs != null) rs.close();

		} catch (Exception e) {

			e.printStackTrace();
		}
		return rs;
	}

}
