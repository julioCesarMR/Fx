/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.system.jcmr.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Julio Cesar Meza Rios
 */
public class ConectaSql {


	public static Connection getConexion(){
		Connection cn=null;
		try {

			Class.forName("com.mysql.jdbc.Driver");
			cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","mysql");

		} catch (ClassNotFoundException | SQLException e) {

			   System.out.println(""+e.getMessage());
		}
		return cn;

	}



}