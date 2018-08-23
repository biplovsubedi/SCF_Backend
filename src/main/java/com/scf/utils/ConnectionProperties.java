package com.scf.utils;

public class ConnectionProperties {

	private final String DB_URL = "jdbc:mysql://scfdata.cbfvh5rhloo8.ap-south-1.rds.amazonaws.com:3306/scfschema";
	private final String USER = "biplov";
	private final String PASS = "Scfrbs1234";
	private final String DB_TABLE = "scfdata";
	
	public String getDB_URL() {
		return DB_URL;
	}
	public String getUSER() {
		return USER;
	}
	public String getPASS() {
		return PASS;
	}
	public String getDB_TABLE() {
		return DB_TABLE;
	}

}
