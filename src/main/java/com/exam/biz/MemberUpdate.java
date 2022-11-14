package com.exam.biz;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/memberUpdate")
public class MemberUpdate extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/memberUpdate");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		request.setCharacterEncoding("UTF-8");
		
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
//			String sql="insert into member_tbl_02 values(?,?,?,?,?,?,?)";
			String sql="update member_tbl_02 set custno=?,custname=?,phone=?,address=?,joindate=?,grade=?,city=? where custno=?";
			stmt=conn.prepareStatement(sql);
			
			stmt.setInt(1, custno);
			stmt.setString(2, custname);
			stmt.setString(3, phone);
			stmt.setString(4, address);
			stmt.setString(5, joindate);
			stmt.setString(6, grade);
			stmt.setString(7, city);
			stmt.setInt(8, custno);
			
			int cnt=stmt.executeUpdate();
			
			System.out.println(cnt+"개 레코드 수정");
			
			out.print("<script>alert('회원정보수정이 완료되었습니다.');location.href='memberList';</script>");
			out.close();
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
