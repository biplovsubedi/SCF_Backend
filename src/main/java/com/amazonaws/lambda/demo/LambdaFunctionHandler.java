package com.amazonaws.lambda.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class LambdaFunctionHandler implements RequestHandler<RequestParams, ResponseContent> {

    @Override
    public ResponseContent handleRequest(RequestParams request, Context context) {

		ResponseContent response = new ResponseContent();

		try {
			if(request==null) {
				request = new RequestParams("1500000011");
			}
			String input = request.getCin();
			context.getLogger().log("Input: " + input);
			response.setCin(input);

			
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://scfdata.cbfvh5rhloo8.ap-south-1.rds.amazonaws.com:3306/scfschema", "biplov",
					"Scfrbs1234");

			// 3 SQL Statements to be executed from here

			// SQL 1: To get total discounted
			String sql = null;

			// input= "1500000014";
			sql = "select sum(INVOICE_AMOUNT) " + 
					"from scfdata " + 
					"where (INVOICE_STATUS='300')&&(PAYMENT_STATUS='1600')"
					+"&&(DUE_DATE>NOW())&&(CIN=" + input+")";

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			// context.getLogger().log("Total Discounted Query Executed");

			while (rs.next()) {
				response.setTotal_discounted(rs.getDouble(1));

			}

			// SQL 2: To get last month discounted
			sql = "select sum(INVOICE_AMOUNT) " + 
					"from scfdata " + 
					"where (INVOICE_STATUS='900'||INVOICE_STATUS='175'||INVOICE_STATUS='400')" + 
					"&&(PAYMENT_STATUS='1400'||PAYMENT_STATUS='1800')" + 
					"&&(DUE_DATE>now()" + 
					"&&(CIN=" + input +"))";

			rs = stmt.executeQuery(sql);

			// context.getLogger().log("Last Month Query Executed");

			while (rs.next()) {
				response.setLast_month(rs.getDouble(1));
			}

			// SQL 1: To get total discounted
			sql = "select sum(INVOICE_AMOUNT) " + 
					"from scfdata " + 
					"where (INVOICE_STATUS='300')&&(PAYMENT_STATUS='1600')" + 
					"&&(DISCOUNTED_DATE > (NOW() - INTERVAL 1 MONTH))" + 
					"&&(CIN=" + input +")";

			rs = stmt.executeQuery(sql);

			// context.getLogger().log("Invoice Amount Query Executed");

			while (rs.next()) {
				response.setPending_payments(rs.getDouble(1));
			}

			if (response.isValid())
				response.setStatus(true);

			// System.out.println(res);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		context.getLogger().log(response.toString());
		return response;

    }

}
