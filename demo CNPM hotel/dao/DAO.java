package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DAO {
	public static Connection con;
	
	public DAO(){
		if(con == null){
			String dbUrl = "jdbc:sqlserver://localhost;instanceName=SQLEXPRESS02;databaseName=hotel;encrypt=true;trustServerCertificate=true;";
			String dbClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

			try {
				Class.forName(dbClass);
				con = DriverManager.getConnection (dbUrl, "sa", "123456");
			}catch(Exception e) {
				System.out.println("Loi ket noi database");
				e.printStackTrace();
			}
		}
	}
}
