package com.exam.biz;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exam.vo.Member;


@WebServlet("/memberList")
public class MemberList extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/memberList");
		
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String url="jdbc:oracle:thin:@localhost:1521:xe";
			conn=DriverManager.getConnection(url, "system", "1234");
			String sql="select custno,custname,phone,address,to_char(joindate,'yyyy-mm-dd') as joindate,grade,city from member_tbl_02";
			stmt=conn.prepareStatement(sql);
			
			rs=stmt.executeQuery();
			ArrayList<Member> list=new ArrayList<Member>();
			while(rs.next()) {
				Member vo=new Member();
				vo.setCustno(rs.getInt("custno"));
				vo.setCustname(rs.getString("custname"));
				vo.setPhone(rs.getString("phone"));
				vo.setAddress(rs.getString("address"));
				vo.setJoindate(rs.getString("joindate"));
				vo.setGrade(rs.getString("grade"));
				vo.setCity(rs.getString("city"));
				
				list.add(vo);
			}
			
			request.setAttribute("list", list);
			
			RequestDispatcher dispatcher=request.getRequestDispatcher("index.jsp?filePath=list");
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
