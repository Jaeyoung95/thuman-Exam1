package com.exam.biz;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/memberInsert")
public class MemberInsert extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		System.out.println("/memberInsert");
		
		int custno=Integer.parseInt(request.getParameter("custno"));
		String custname= request.getParameter("custname");
		String phone=request.getParameter("phone");
		String address=request.getParameter("address");
		String joindate=request.getParameter("joindate");
		String grade=request.getParameter("grade");
		String city=request.getParameter("city");
		
		System.out.println(custno+" "+custname+" "+phone+" "+address+" "+joindate+" "+grade+" "+city);
		
		Connection conn=null;
		PreparedStatement stmt=null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String url="jdbc:oracle:thin:@localhost:1521:xe";
			conn=DriverManager.getConnection(url, "system", "1234");
			String sql="insert into member_tbl_02 values(?,?,?,?,?,?,?)";
			stmt=conn.prepareStatement(sql);
			
			stmt.setInt(1, custno);
			stmt.setString(2, custname);
			stmt.setString(3, phone);
			stmt.setString(4, address);
			stmt.setString(5, joindate);
			stmt.setString(6, grade);
			stmt.setString(7, city);
			
			int cnt=stmt.executeUpdate();
			
			System.out.println(cnt+"개 레코드 추가");
			
			response.sendRedirect("memberJoin");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(stmt!=null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

}
