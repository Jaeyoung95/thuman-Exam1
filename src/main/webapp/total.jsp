<%@page import="com.exam.vo.MemberTotalVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% ArrayList<MemberTotalVO> list=(ArrayList<MemberTotalVO>)request.getAttribute("list"); %>

<h1 class="titleCenter" style="margin-top: 30px; margin-bottom: 30px;">회원매출조회</h1>
<table style="margin-left:450px;" id="li">
	<tr>
		<th>회원번호</th>
		<th>회원성명</th>
		<th>등급</th>
		<th>매출</th>
	</tr>

	<%
	for (int i = 0; i < list.size(); i++) {
		MemberTotalVO vo = list.get(i);
		String grade;
		if(vo.getGrade().equals("A")){
			grade="VIP";
		}else if(vo.getGrade().equals("B")){
			grade="일반";
		}else{
			grade="직원";
		}
	%>
	<tr>
		<td><%=vo.getCustno()%></td>
		<td><%=vo.getCustname()%></td>
		<td><%=grade%></td>
		<td><%=vo.getTotalprice()%></td>
	</tr>
	<%
	}
	%>
</table>