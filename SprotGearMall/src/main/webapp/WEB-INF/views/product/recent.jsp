<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/views/includes/header.jsp" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<title>Insert title here</title>
<style type="text/css">
table, th, td {
   border-style : solid;
   border-width : 0px;
   text-align : center;
}

th, td {
   border-top: 1px solid #ddd;
   border-bottom: 1px solid #ddd;
}

ul {
    list-style-type : none;
}

li {
    display : inline-block;
}

#productTableBody {
	vertical-align: middle;
    text-align: center;
    font-size: 20px; 
}

.form-control-plaintext{
	text-align: center;
}

</style>
</head>
<body>


<div align="center">

<h2>최근 본 상품</h2>

<table>
	<thead>
		<tr>
			<th>상품정보</th>
			<th>상품정보</th>
            <th>이름</th>
            <th>가격</th>
            <th>재고</th>
            <th>삭제버튼</th>
        </tr>    
	</thead>
	
    <tbody align="center" id="productTableBody">
	<c:forEach var="vo" items="${productList}">
    	<tr class="align-middle">

    	</tr>
	</c:forEach>
	</tbody>
</table>


</div>

<button style="float: left; margin-right: 10px; font-size: 20px;" id="cookieDelete" onclick="deleteRecentProducts()">전체삭제</button>





<script type="text/javascript">

//쿠키 이름을 기반으로 쿠키 값을 가져오는 함수
function getCookieValue(cookieName) {
    // 쿠키 문자열 가져오기
    var cookies = document.cookie.split(';');

    // 쿠키 목록을 반복하면서 원하는 쿠키를 찾음
    for (var i = 0; i < cookies.length; i++) {
        var cookie = cookies[i].trim();
        // 쿠키 이름이 일치하면 해당 쿠키의 값을 반환
        if (cookie.startsWith(cookieName + '=')) {
            return cookie.substring(cookieName.length + 1);
        }
    }

    // 해당 쿠키가 없으면 빈 문자열 반환
    return '';
}

// "recentProducts" 쿠키 값을 가져와서 리스트로 변환
var recentProductsCookieValue = getCookieValue('recentProducts');
var recentProductsList = recentProductsCookieValue.split(',');
console.log('recentProductsCookieValue:', recentProductsCookieValue);
console.log('recentProductsList:', recentProductsList);
var url = "recents" + recentProductsCookieValue
// CSRF 토큰 값 가져오기 (이 부분은 페이지에 존재하는지 확인 필요)


// 서버로 데이터를 POST 방식으로 전송
$.ajax({
    type: "GET",
    url: "recents/",
    headers: {
        'Content-Type': 'application/json'
    },
    data: { recentProductsCookieValue: recentProductsCookieValue },
    success: function (productList) {
        // 성공 시 처리
        if (productList !== null) {
           
            console.log('productList서버에서 얘 왔는지 확인:', productList);
//            $('#test').val(productList);
//           $('#productQuantity').val(productList);



					// 기존 내용을 지우기
                    $('#productTableBody').empty();

                    // 받은 데이터를 기반으로 동적으로 HTML 엘리먼트 추가
                    for (var i = 0; i < productList.length; i++) {
                        var product = productList[i];

                        // 새로운 행 생성
                        var newRow = $('<tr class="align-middle">');

                        // 입력 엘리먼트를 포함한 셀 추가
                        var cell1 = $('<td>').append('<input type="text" class="form-control-plaintext" value="' + product.productId + '">');
                        var cell2 = $('<td>').append('<img class="img-thumbnail" src="https://storage.googleapis.com/edu-mall-img/' 
                        		+ product.productImgPath + '" alt="Product Image" style="width: 150px; height: auto;" />');
                        var cell3 = $('<td>').append('<a href="../product/detail?productId=' + product.productId + '">' + product.productName + '</a>');
                        var cell4 = $('<td>').append('<input type="text" class="form-control-plaintext" value="' + product.productPrice + '">');
                        var cell5 = $('<td>').append('<input type="text" class="form-control-plaintext" value="' + product.productStock + '">');
                        var cell6 = $('<td>').append('<input type="button" class="btn btn-outline-danger" value="삭제버튼">');

                        // 행에 셀 추가
                        newRow.append(cell1);
                        newRow.append(cell2);
                        newRow.append(cell3);
                        newRow.append(cell4);
                        newRow.append(cell5);
                        newRow.append(cell6);

                        // 행을 테이블 본문에 추가
                        $('#productTableBody').append(newRow);
                    }



        } else {
            alert('실패');
        }
    }
});


function deleteRecentProducts() {
    // Set the expiration date in the past to delete the cookie
    document.cookie = "recentProducts=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
    alert('최근 본 상품이 삭제되었습니다.');
    // Reload the page or perform any other necessary actions
    location.reload();
}




//"삭제" 버튼에 대한 클릭 이벤트 리스너 추가
$('#productTableBody').on('click', '.btn-outline-danger', function() {
    // 클릭된 행의 인덱스 가져오기
    var rowIndex = $(this).closest('tr').index();

    // recentProductsList에서 해당 항목 제거
    recentProductsList.splice(rowIndex, 1);

    // 수정된 recentProductsList로 쿠키 업데이트
    document.cookie = "recentProducts=" + recentProductsList.join(',') + "; path=/";

    // HTML 테이블에서 해당 행 제거
    $(this).closest('tr').remove();
});


</script>




    
</body>
</html>