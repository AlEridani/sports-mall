<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<style type="text/css">
 #final {
	border-bottom : 1px solid #ddd;
    width: 50%;
    height: 400px;
}
</style>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<title>Insert title here</title>
</head>
<body>
<h2 align="center">장바구니</h2>

<input type="hidden" id="memberId" value="${pageContext.request.userPrincipal.name}">
<input type="hidden" id="csrfToken" name="${_csrf.parameterName}" value="${_csrf.token}"/>

<c:if test="${not empty list}">
<div class="container mt-5">

	<form action="update" method="POST">
    <c:forEach var="vo" items="${list }">
        <div class="row mb-3" id="cartmenu_${vo.cart.cartId}">
            <div class="col-md-2">
                <img class="card-img-top"
                    src="https://storage.googleapis.com/edu-mall-img/${vo.product.productImgPath }" alt="이미지" />
            </div>
            <div class="col-md-7">
                <h5>상품 명 : ${vo.product.productName}</h5>
                <p>카테고리 : ${vo.product.productCategory }</p>
                
                <input type="hidden" name="cartId" value="${vo.cart.cartId}" readonly>
                <input type="hidden" name="memberId" value="${pageContext.request.userPrincipal.name}" readonly>
                <input type="hidden" name="productId" value="${vo.product.productId}" readonly>
                
                <p>
                   가격 : <input type="number" name="productPriceOne" value="${vo.product.productPrice }" id="productPrice_${vo.cart.cartId}" readonly="readonly">
                </p>
                <p>재고 : ${vo.product.productStock }</p>
                <p>갯수 : 
                    <input type="number" name="productQuantity" id="productQuantity_${vo.cart.cartId}"value=${vo.cart.productQuantity } oninput="calculateTotalPrice('${vo.cart.cartId}')" min="1"><br>             
                </p>
                <p>총 가격 : 
                    <input type="number" name="productPrice" id="totalPrice_${vo.cart.cartId}" value=${vo.cart.productPrice } readonly="readonly">
                </p>                   
            </div>
            <div class="col-md-3">
                <button type="button" class="btn btn-danger btn-delete" data-product-id="${vo.product.productId}">삭제</button>
            </div>
            <hr>
        </div>
    </c:forEach>
    <button type="submit" class="btn btn-primary btn-lg">결제하기</button>
    </form>
    
    
</div>
</c:if>

<div style="text-align: center;">
<h1>
    <p>총 상품금액</p>
    <input type="number" name="allTotalPrice" id="allTotalPrice" readonly="readonly"> 원&nbsp;&nbsp;&nbsp;&nbsp;
</h1>
</div>


<script type="text/javascript">
function calculateTotalPrice(cartId) {
    const productQuantity = document.getElementById('productQuantity_' + cartId).value;
    const productPrice = document.getElementById('productPrice_' + cartId).value;
    const totalPrice = productQuantity * productPrice;
    document.getElementById('totalPrice_' + cartId).value = totalPrice;
    
    // 전체 상품 가격 다시 계산
    updateAllTotalPrice();
}

function updateAllTotalPrice() {
    let total = 0;

    // 각 상품의 총 가격을 더함
    const elements = document.querySelectorAll('[id^="totalPrice_"]');
    elements.forEach(element => {
        total += parseFloat(element.value) || 0;
    });

    // 결과를 allTotalPrice에 반영
    document.getElementById('allTotalPrice').value = total;
}






</script>

    

		
</body>
</html>