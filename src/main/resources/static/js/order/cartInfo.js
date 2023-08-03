/**
 * 
 */

$(function(){
})
function deleteCount(button){
	
	
	var gno = $(button).siblings('.gno').val();
	var email = $(button).siblings('.email').text();
	
	
	$.ajax({
		url:"/order/delete-count",
		data:{gno : gno,
		email : email},
		type:"post",
		beforeSend: function (jqXHR, settings) {
           var header = $("meta[name='_csrf_header']").attr("content");
           var token = $("meta[name='_csrf']").attr("content");
           jqXHR.setRequestHeader(header, token);
		},
		success:function(result){
			
			$("#showCart").html(result)
		}
	
	})
}

function deleteItem(button){
		var gno = $(button).siblings('.gno').val();
		var email = $(button).siblings('.email').text();
		$.ajax({
		url:"/order/item-delete",
		data:{gno : gno,
		email : email},
		type:"post",
		beforeSend: function (jqXHR, settings) {
           var header = $("meta[name='_csrf_header']").attr("content");
           var token = $("meta[name='_csrf']").attr("content");
           jqXHR.setRequestHeader(header, token);
		},
		success:function(result){
			
			$("#showCart").html(result)
		}
	
	})
}

