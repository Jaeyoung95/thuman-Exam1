package com.exam.biz;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/memberJoin")
public class MemberJoin extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/memberJoin");
		// DB연동 후 회원번호의 가장 큰 번호를 조회 후 해당 번호를 전송.
		
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String url="jdbc:oracle:thin:@localhost:1521:xe";
			conn=DriverManager.getConnection(url, "system", "1234");
			String sql="select max(custno)+1 as custno from member_tbl_02";
			stmt=conn.prepareStatement(sql);
			
			rs=stmt.executeQuery();
			Integer custno = null;
			if(rs.next()) {
				custno=rs.getInt("custno");
			}
			
			System.out.println(custno);
			
			request.setAttribute("custno", custno);
			
			RequestDispatcher dispatcher=request.getRequestDispatcher("index.jsp?filePath=insert");
			dispatcher.forward(request, response);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
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
