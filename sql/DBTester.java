import java.sql.*;
public class DBTester
{
	public static void main(String[] argv)
	{


		DBManager.createDataBase("Students","root","ThisPassword");
		String tableEntry = "PID char(100) NOT NULL,FirstName char(100) NOT NULL,LastName char(100) NOT NULL,Pass varchar(100) NOT NULL,"+
		"Icon char(100) NOT NULL,PRIMARY KEY(PID)";
		DBManager.createTable("Students","users",tableEntry,"root","ThisPassword");

		DataBaseConnector ltmdb = new DataBaseConnector("Students","root","ThisPassword");
		ltmdb.insertIntoDB("users","null,'Eric','Sullivan','CSCI400','ICON1.png'");

		ltmdb.showDBUsers();//test method -- needs revision to be more generic

		ltmdb.closeDBConnection();

		DBManager.deleteTable("Students","users","root","ThisPassword");
		DBManager.deleteDataBase("Students","root","ThisPassword");
	}
}