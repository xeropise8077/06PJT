<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<html>
<head>
<title>Insert title here</title>
<link rel="stylesheet" href="/css/admin.css" type="text/css">
</head>

<body>

<form name="updatePurchase" action="/updatePurchaseView.do?tranNo=0" method="post" >

다음과 같이 구매가 되었습니다.

<table border=1>
	<tr>
		<td>물품번호</td>
		<td>${ purchase.purchaseProd.prodNo}</td>
		<td></td>
	</tr>
	<tr>
		<td>구매자아이디</td>
		<td>${ purchase.buyer.userId}</td>
		<td></td>
	</tr>
	<tr>
		<td>구매방법</td>
		<td>
		<c:choose>
			<c:when test="${ purchase.paymentOption=='1' }">
				현금구매
			</c:when>
			
			<c:otherwise>
				신용구매
			</c:otherwise>
		</c:choose>

	
		</td>
		<td></td>
	</tr>
	<tr>
		<td>구매자이름</td>
		<td>${ purchase.receiverName }<%--=purchase.getReceiverName() --%></td>
		<td></td>
	</tr>
	<tr>
		<td>구매자연락처</td>
		<td>${ purchase.receiverPhone }<%--=purchase.getReceiverPhone() --%></td>
		<td></td>
	</tr>
	<tr>
		<td>구매자주소</td>
		<td>${ purchase.divyAddr }<%--=purchase.getDivyAddr() --%></td>
		<td></td>
	</tr>
		<tr>
		<td>구매요청사항</td>
		<td>${ purchase.divyRequest }<%--=purchase.getDivyRequest() --%></td>
		<td></td>
	</tr>
	<tr>
		<td>배송희망일자</td>
		<td>${ purchase.divyDate }<%--=purchase.getDivyDate() --%></td>
		<td></td>
	</tr>
</table>
</form>

</body>
</html>