/**
 * 
 */
$(function(){
	
})

function addCart(button){
	
	var gno = $(button).siblings('.gno').val();
	
	var token = $("meta[name='_csrf']").attr('content');
    var header = $("meta[name='_csrf_header']").attr('content');
    if(token && header) {
        $(document).ajaxSend(function(event, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
    }
	$.ajax({
		url:"/cart",
		data:{gno : gno},
		type:"post",
		success:function(result){
			var check=confirm("카드에 담았습니다.<br>카드로 이동?");
			if(check){
				location.href="/cart";
			}
		}
	
	})
	
}
//수량 증가 버튼
function addCount(button){
	
	var gno = $(button).siblings('.gno').val();
	
	var token = $("meta[name='_csrf']").attr('content');
    var header = $("meta[name='_csrf_header']").attr('content');
    if(token && header) {
        $(document).ajaxSend(function(event, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
    }
	$.ajax({
		url:"/cart",
		data:{gno : gno},
		type:"post",
		success:function(result){
			
				location.href="/cart";
			
		}
	
	})
	
}
//수량 감소 버튼
function deleteCount(button){
	
	
	var gno = $(button).siblings('.gno').val();
	
	
	$.ajax({
		url:"/cart/countDown",
		data:{gno : gno	},
		type:"post",
		beforeSend: function (jqXHR, settings) {
           var header = $("meta[name='_csrf_header']").attr("content");
           var token = $("meta[name='_csrf']").attr("content");
           jqXHR.setRequestHeader(header, token);
		},
		success:function(result){
			location.href="/cart";
		}
	
	})
}

function scrollCart(gno){
	var scrollValue = $(".cart").scrollTop()
	if(!$(button).hasClass("cart-RBtn")){
		$("#showCart").html(result)
		var offset=$("#showCart .gno[value='"+gno+"']").parent().position().top
		$(".cart").scrollTop(offset-200)
	}else if($(button).hasClass("cart-RBtn")){
		$("#showCart").html(result)
		$(".cart").scrollTop(scrollValue)
	}
}