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

import com.exam.vo.Member;


@WebServlet("/memberInfo")
public class MemberInfo extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/memberInfo");
		
		int custno=Integer.parseInt(request.getParameter("custno"));  
		
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String url="jdbc:oracle:thin:@localhost:1521:xe";
			conn=DriverManager.getConnection(url, "system", "1234");
			
			String sql="select custno,custname,phone,address,to_char(joindate,'yyyy-mm-dd') as joindate,grade,city from member_tbl_02 where custno=?";
			stmt=conn.prepareStatement(sql);
			
			stmt.setInt(1, custno);
			
			rs=stmt.executeQuery();
			Member member=new Member();
			if(rs.next()) {
				member.setCustname(rs.getString("custname"));
				member.setCustno(rs.getInt("custno"));
				member.setAddress(rs.getString("address"));
				member.setPhone(rs.getString("phone"));
				member.setJoindate(rs.getString("joindate"));
				member.setGrade(rs.getString("grade"));
				member.setCity(rs.getString("city"));
			}
			
			request.setAttribute("member", member);
			
			RequestDispatcher dispatcher=request.getRequestDispatcher("index.jsp?filePath=info");
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
