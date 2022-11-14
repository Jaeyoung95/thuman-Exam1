<%@page import="com.exam.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% Member member=(Member)request.getAttribute("member"); %>
<h1 class="titleCenter" style="margin-top:30px;margin-bottom:30px;">홈쇼핑 회원 정보 수정</h1>
<form name="memberInsert" method="post" action="memberUpdate">
<table>
<tr><th>회원번호</th> <td><input type="text" name="custno" size="10" value="<%=member.getCustno() %>"></td></tr>
<tr><th>회원성명</th> <td><input type="text" name="custname" size="10" value="<%=member.getCustname() %>"></td></tr>
<tr><th>회원전화</th> <td><input type="text" name="phone" size="25" value="<%=member.getPhone() %>"></td></tr>
<tr><th>회원주소</th> <td><input type="text" name="address" size="35" value="<%=member.getAddress() %>"></td></tr>
<tr><th>가입일자</th> <td><input type="text" name="joindate" size="10" value="<%=member.getJoindate() %>"></td></tr>
<tr><th>고객등급(A:VIP,B:일반,C:직원)</th> <td><input type="text" name="grade" size="10" value="<%=member.getGrade() %>"></td></tr>
<tr><th>도시코드</th> <td><input type="text" name="city" size="10" value="<%=member.getCity() %>"></td></tr>
<tr><td colspan="2" style="text-align:center;"> <button type="submit">수정</button> &nbsp;<button onclick="location.href='memberList';return false;">조회</button> </td></tr>
</table>
</form>

