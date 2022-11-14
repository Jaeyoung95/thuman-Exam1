<%@page import="com.exam.vo.Member"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
ArrayList<Member> list = (ArrayList<Member>) request.getAttribute("list");
%>

<h1 class="titleCenter" style="margin-top: 30px; margin-bottom: 30px;">회원목록/조회
	수정</h1>
<table style="margin-left:50px;" id="li">
	<tr>
		<th>회원번호</th>
		<th>회원성명</th>
		<th>전화번호</th>
		<th>주소</th>
		<th>가입일자</th>
		<th>고객등급</th>
		<th>거주지역</th>
	</tr>

	<%
	for (int i = 0; i < list.size(); i++) {
		Member member = list.get(i);
	%>
	<tr>
		<td> <a href="memberInfo?custno=<%=member.getCustno()%>"><%=member.getCustno()%></a> </td>
		<td><%=member.getCustname()%></td>
		<td><%=member.getPhone()%></td>
		<td><%=member.getAddress()%></td>
		<td><%=member.getJoindate()%></td>
		<td><%=member.getGrade()%></td>
		<td><%=member.getCity()%></td>
	</tr>
	<%
	}
	%>
</table>