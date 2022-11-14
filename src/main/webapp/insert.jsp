<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% int custno= (Integer)request.getAttribute("custno"); %>
<h1 class="titleCenter" style="margin-top:30px;margin-bottom:30px;">홈쇼핑 회원등록</h1>
<form name="memberInsert">
<table class="table">
<tr><th>회원번호(자동발생)</th> <td><input type="text" name="custno" size="10" value="<%=custno %>"></td></tr>
<tr><th>회원성명</th> <td><input type="text" name="custname" size="10"></td></tr>
<tr><th>회원전화</th> <td><input type="text" name="phone" size="25"></td></tr>
<tr><th>회원주소</th> <td><input type="text" name="address" size="35"></td></tr>
<tr><th>가입일자</th> <td><input type="text" name="joindate" size="10"></td></tr>
<tr><th>고객등급(A:VIP,B:일반,C:직원)</th> <td><input type="text" name="grade" size="10"></td></tr>
<tr><th>도시코드</th> <td><input type="text" name="city" size="10"></td></tr>
<tr><td colspan="2" style="text-align:center;"> <button onclick="memberJoin();return false;">등록</button> &nbsp;<button onclick="location.href='memberList';return false;">조회</button> </td></tr>
</table>
</form>

<script> 
function memberJoin(){
	var custname=document.memberInsert.custname.value;
	var phone=document.memberInsert.phone.value;
	var address=document.memberInsert.address.value;
	var joindate=document.memberInsert.joindate.value;
	var grade=document.memberInsert.grade.value;
	var city=document.memberInsert.city.value;
	
	if(custname=="" || custname.length==0){
		alert("회원성명이 입력되지 않았습니다.");
		document.memberInsert.custname.focus();
	}else if(phone=="" || phone.length==0){
		alert("회원전화번호가 입력되지 않았습니다.");
		document.memberInsert.phone.focus();
	}else if(address=="" || address.length==0){
		alert("회원주소가 입력되지 않았습니다.");
		document.memberInsert.address.focus();
	}else if(joindate=="" || joindate.length==0){
		alert("가입일자가 입력되지 않았습니다.");
		document.memberInsert.joindate.focus();
	}else if(grade=="" || grade.length==0){
		alert("등급이 입력되지 않았습니다.");
		document.memberInsert.grade.focus();
	}else if(city=="" || city.length==0){
		alert("도시코드가 입력되지 않았습니다.");
		document.memberInsert.city.focus();
	}else{
		document.memberInsert.method="post";
		document.memberInsert.action="memberInsert";
		alert("회원등록이 완료되었습니다.");
		document.memberInsert.submit();
		
	}
}

</script>