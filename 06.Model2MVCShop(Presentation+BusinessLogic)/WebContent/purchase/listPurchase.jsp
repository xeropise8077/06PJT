<%@ page contentType="text/html; charset=euc-kr" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>��ǰ ��� ��ȸ</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
	// �˻� / page �ΰ��� ��� ��� Form ������ ���� JavaScrpt �̿�  
	function fncGetUserList(currentPage) {
		document.getElementById("currentPage").value = currentPage;
	   	document.detailForm.submit();		
	}
</script>

</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width:98%; margin-left:10px;">

<form name="detailForm" action="/listPurchase.do" method="post">
					
<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37"/>
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">���� ��� ��ȸ</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37">
			<img src="/images/ct_ttl_img03.gif" width="12" height="37">
		</td>
	</tr>
</table>


<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td colspan="11" >
			��ü  ${ resultPage.totalCount }<%--= resultPage.getTotalCount() --%> �Ǽ�,	���� ${ resultPage.currentPage }<%--= resultPage.getCurrentPage() --%> ������
		</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">ȸ��ID</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">ȸ����</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">��ȭ��ȣ</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">�����Ȳ</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">��������</td>		
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>
	
	<c:set var="j" value="0"/>
	
	<c:forEach var="i" items="${ list }" begin="0">
		<c:set var="purchase" value="${ i }" />
	
	<tr class="ct_list_pop">
		<td align="center">
			<c:choose>
				<c:when test="${purchase.getTranCode().equals('1  ') }">
					<a href="/getPurchase.do?tranNo=${ purchase.tranNo }<%--=purchase.getTranNo()--%>" >${ j+1 }<%--=i+1--%></a>
				</c:when>
				
				<c:otherwise>
					${ j+1 }
				</c:otherwise>
				
			</c:choose>

	
		
		</td>
		<td></td>
		<td align="left">
			<a href="/getUser.do?userId=${ purchase.buyer.userId }<%--=purchase.getBuyer().getUserId() --%>" >${ purchase.buyer.userId }</a>
		</td>
		<td></td>
		<td align="left">${ purchase.receiverName  }</td>
		<td></td>
		<td align="left">${ purchase.receiverPhone }<%--=purchase.getReceiverPhone() --%></td>
		<td></td>
	
		<td align="left">
		<c:choose>
				<c:when test="${purchase.getTranCode().trim().equals('3') }">
					��� �Ϸ� �����Դϴ�
				</c:when>

				<c:when test="${purchase.getTranCode().trim().equals('2') }">
					����� �����Դϴ�
				</c:when>							
				
				<c:when test="${purchase.getTranCode().trim().equals('1') }">
					���ſϷ�����Դϴ�. 
				</c:when>			
		
				
		</c:choose>
				</td>
		<td></td>
		<td align="left">
			<c:if test="${purchase.getTranCode().trim().equals('2')}">
				<a href="/updateTranCode.do?tranno=${ purchase.tranNo }&trancode=${ purchase.tranCode }" target="rightFrame">���ǵ���</a>
			</c:if>

				
		</td>
	</tr>
	<tr>
		<td colspan="11" bgcolor="D6D7D6" height="1"></td>
	</tr>
	</c:forEach>
</table>

<!-- PageNavigation Start... -->
<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top:10px;">
	<tr>
		<td align="center">
		   
 <input type="hidden" id="currentPage" name="currentPage" value=""/>

		<jsp:include page="../common/pageNavigator.jsp"/>	
    	</td>
	</tr>
</table>
<!-- PageNavigation End... -->

</form>
</div>

</body>
</html>