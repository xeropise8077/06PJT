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

������ ���� ���Ű� �Ǿ����ϴ�.

<table border=1>
	<tr>
		<td>��ǰ��ȣ</td>
		<td>${ purchase.purchaseProd.prodNo}</td>
		<td></td>
	</tr>
	<tr>
		<td>�����ھ��̵�</td>
		<td>${ purchase.buyer.userId}</td>
		<td></td>
	</tr>
	<tr>
		<td>���Ź��</td>
		<td>
		<c:choose>
			<c:when test="${ purchase.paymentOption=='1' }">
				���ݱ���
			</c:when>
			
			<c:otherwise>
				�ſ뱸��
			</c:otherwise>
		</c:choose>

	
		</td>
		<td></td>
	</tr>
	<tr>
		<td>�������̸�</td>
		<td>${ purchase.receiverName }<%--=purchase.getReceiverName() --%></td>
		<td></td>
	</tr>
	<tr>
		<td>�����ڿ���ó</td>
		<td>${ purchase.receiverPhone }<%--=purchase.getReceiverPhone() --%></td>
		<td></td>
	</tr>
	<tr>
		<td>�������ּ�</td>
		<td>${ purchase.divyAddr }<%--=purchase.getDivyAddr() --%></td>
		<td></td>
	</tr>
		<tr>
		<td>���ſ�û����</td>
		<td>${ purchase.divyRequest }<%--=purchase.getDivyRequest() --%></td>
		<td></td>
	</tr>
	<tr>
		<td>����������</td>
		<td>${ purchase.divyDate }<%--=purchase.getDivyDate() --%></td>
		<td></td>
	</tr>
</table>
</form>

</body>
</html>