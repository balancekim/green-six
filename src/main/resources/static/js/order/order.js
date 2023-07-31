/**
 * 
 */
$(function(){
	
})

function addCart(button){
	var gno = $(button).siblings('.gno').val();
	var name = $(button).siblings('.name').val();
	var price = $(button).siblings('.price').val();
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
		name : name,
		price:price,
		email:email},
		type:"post",
		success:function(result){
			
			$("#showCart").html(result)
		}
	
	})
	
}