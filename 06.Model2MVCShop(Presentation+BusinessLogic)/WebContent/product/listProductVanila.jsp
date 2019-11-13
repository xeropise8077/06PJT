<%@ page contentType="text/html; charset=euc-kr"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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

	<div style="width: 98%; margin-left: 10px;">

	<form name="detailForm"	action="/listProductVanila.do" method="post">
		


		<table width="100%" height="37" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="15" height="37"><img src="/images/ct_ttl_img01.gif"
					width="15" height="37" /></td>
				<td background="/images/ct_ttl_img02.gif" width="100%"
					style="padding-left: 10px;">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="93%" class="ct_ttl01">��ǰ ��� ��ȸ</td>
						</tr>
					</table>
				</td>
				<td width="12" height="37"><img src="/images/ct_ttl_img03.gif"
					width="12" height="37"></td>
			</tr>
		</table>

		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			style="margin-top: 10px;">
			<tr>
				<td align="right"><select name="searchCondition"
					class="ct_input_g" style="width: 80px">
						<option value="0"
							${ search.searchCondition=="0" ? 'selected' : '' }>��ǰ��ȣ</option>
						<option value="1"
							${ search.searchCondition=="1" ? 'selected' : '' }>��ǰ��</option>
						<option value="2"
							${ search.searchCondition=="2" ? 'selected' : '' }>����</option>
				</select> <input type="text" name="searchKeyword"
					value="${ search.searchKeyword }" class="ct_input_g"
					style="width: 200px; height: 20px"></td>
				<td align="right" width="70">
					<table border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="17" height="23"><img src="/images/ct_btnbg01.gif"
								width="17" height="23" /></td>
							<td background="/images/ct_btnbg02.gif" class="ct_btn01"
								style="padding-top: 3px;"><a
								href="javascript:fncGetUserList('1');">�˻�</a></td>
							<td width="14" height="23"><img src="/images/ct_btnbg03.gif"
								width="14" height="23" /></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>

		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			style="margin-top: 10px;">
			<tr>
				<td colspan="11">��ü ${ resultPage.totalCount } �Ǽ�, ���� ${ resultPage.currentPage }
					������</td>
			</tr>
			<tr>
				<td class="ct_list_b" width="100">No</td>
				<td class="ct_line02"></td>
				<td class="ct_list_b" width="150">��ǰ��</td>
				<td class="ct_line02"></td>
				<td class="ct_list_b" width="150">����</td>
				<td class="ct_line02"></td>
				<td class="ct_list_b" width="150">�����</td>
				<td class="ct_line02"></td>
				<td class="ct_list_b" width="150">����</td>
				<td class="ct_line02"></td>
			</tr>
			<tr>
				<td colspan="11" bgcolor="808285" height="1"></td>
			</tr>

			<c:set var="n" value="0" />
			<c:forEach var="i" items="${ list }" begin="0">
				<c:set var="n" value="${ n+1 }" />
				<c:set var="Purchase" value="${ i }" />

				<tr class="ct_list_pop">
					<td align="center">${ n }</td>
					<td></td>
					<td align="left">
					
					<a href="/getProduct.do?prodNo=${ Purchase.prodNo }&menu=search">${ Purchase.prodName }</</a>
	

					</td>
					<td></td>
					<td align="left">${ Purchase.price }</td>
					<td></td>
					<td align="left">${ Purchase.regDate }</td>
					<td></td>
					<td align="left"><img src="${Purchase.fileName}" width="200" height="200" alt="My Image"></td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="11" bgcolor="D6D7D6" height="1"></td>
			</tr>

		</table>

		<!-- PageNavigation Start... -->
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			style="margin-top: 10px;">
			<tr>
				<td align="center"><input type="hidden" id="currentPage"
					name="currentPage" value="" /> <jsp:include page="../common/pageNavigator.jsp" /></td>
			</tr>
		</table>
		<!-- PageNavigation End... -->

		</form>
	</div>

</body>
</html>