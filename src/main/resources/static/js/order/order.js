/**
 * 
 */
$(function(){
	
})

function addCart(button){
	
	var gno = $(button).siblings('.gno').val();
	
	var email = $(button).siblings('.email').text();
	var token = $("meta[name='_csrf']").attr('content');
    var header = $("meta[name='_csrf_header']").attr('content');
    if(token && header) {
        $(document).ajaxSend(function(event, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
    }
	$.ajax({
		url:"/order/addCart",
		data:{gno : gno,
		email:email},
		type:"post",
		success:function(result){
			$("#showCart").html(result)
			var offset=$("#showCart .gno[value='"+gno+"']").parent().position().top
			$(".cart").scrollTop(offset-200)
		}
	
	})
	
}