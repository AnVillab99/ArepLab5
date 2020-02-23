package edu.escuelaing.arep.reto2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class db {


	private final static String url = "jdbc:postgresql://ec2-54-225-95-183.compute-1.amazonaws.com:5432/d1eklfanov8b4e";
	private final static String user = "lhwhsablrhjkzl";
	private final static String password = "4ae800cc156641d730e12a8c0d5c76321dc49e24174bf119e16035c8a114cd34";
	private static Connection conn;


	public db(){}
	/***
	 * Creates the connection to the database
	 * @return
	 */
	public static Connection connect() {
		conn = null;
		try {

			System.out.println("crearla");
			conn = DriverManager.getConnection(url, user, password);
			
			System.out.println("Connected to the PostgreSQL server successfully.");
		} catch (SQLException e) {
			System.out.println("error " + e.getMessage());
		}

		return conn;
	}

	/**
	 * get the users info from the database
	 * @return String[] the array of user names
	 */
	public static String[] consultarUsuarios() {
		ResultSet rs;
		// String SQL = "select exists(select 1 from users WHERE username=(?) and
		// password = (?))";
		String SQL = "select * from users";
		String[] res = null;
		try {
			Connection conn = connect();
			PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
			rs = pstmt.executeQuery();
			ArrayList<String> us = new ArrayList<String>();
			while(rs.next()){
                us.add(rs.getString("username"));

			}
			res=new String[us.size()];
			for(int i=0;i<us.size();i++){
				res[i]=us.get(i);

			}
			
			
		} catch (SQLException ex) {
			System.out.println("Error en la consulta dentro de db: ");
			System.out.println(ex.getMessage());
		}

		
		return res;
	}
}
