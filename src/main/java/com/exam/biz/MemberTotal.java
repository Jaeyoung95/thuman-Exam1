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
import com.exam.vo.MemberTotalVO;


@WebServlet("/memberTotal")
public class MemberTotal extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/memberTotal");
		
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String url="jdbc:oracle:thin:@localhost:1521:xe";
			conn=DriverManager.getConnection(url, "system", "1234");
			String sql="select member.custno,member.custname,member.grade,sum(money.price) as totalprice from member_tbl_02 member,money_tbl_02 money where member.custno=money.custno group by member.custno,member.custname,member.grade order by totalprice desc";
			stmt=conn.prepareStatement(sql);
			
			rs=stmt.executeQuery();
			ArrayList<MemberTotalVO> list=new ArrayList<MemberTotalVO>();
			while(rs.next()) {
				MemberTotalVO vo=new MemberTotalVO();
				vo.setCustno(rs.getInt(1));
				vo.setCustname(rs.getString(2));
				vo.setGrade(rs.getString(3));
				vo.setTotalprice(rs.getInt(4));
				
				list.add(vo);
			}
			
			request.setAttribute("list", list);
			
			RequestDispatcher dispatcher=request.getRequestDispatcher("index.jsp?filePath=total");
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
