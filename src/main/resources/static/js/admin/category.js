/**
 * 
 */
/*//////////////////category button///////////////////////////*/
function cateBtnClicked(updateBtn){
	//수정버튼눌렀을 때 수정버튼 없애고 완료,삭제 버튼활성화
	$(updateBtn).parents(".cate-wrap").siblings().children().children().siblings(".category-update").removeClass("open")
	$(updateBtn).addClass("hide");
	$(updateBtn).parents(".cate-wrap").siblings().children().children("#cate-update").removeClass("hide");
	
	//다른 수정버튼 눌렀을 때 input박스 hide
	$(updateBtn).parents(".cate-wrap").siblings().children().children(".hideInput").hide();
	//$(updateBtn).parents(".cate-wrap2").siblings().children(".category-update").hide();
	//$(updateBtn).parents().siblings().children(".category-update").removeClass("open")
	
	
	//수정버튼 눌렀을때 hide된 수정삭제버튼 활성화
	$(updateBtn).siblings(".category-update").addClass("open");
	
	$(updateBtn).siblings(".hideInput").show();
	
 }

/*//////////////////category delete///////////////////////////*/
function cateDel(btn){
	var no =$(btn).parents().siblings("#cateNo").val();
	console.log(no);
	var token = $("meta[name='_csrf']").attr('content');
    var header = $("meta[name='_csrf_header']").attr('content');
    if(token && header) {
        $(document).ajaxSend(function(event, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
    }
	$.ajax({
		url:`/admin/category/${no}`,
		type:"DELETE",
		success:function(){
			location.href = "/admin/category/admin-categorylist"
			
		}
	
	})

}

/*//////////////////category update///////////////////////////*/

function cateUpdateBtn(btn){
	//cateUpdateBtn이 onclick되면 실행
	
	//no 와 name 값을 변수에 저장
	var no =$(btn).parents().siblings("#cateNo").val();
	var cateNameUpdate = $(btn).parent().siblings("#cateName").val();
	//alert(cateNameUpdate);
	
	//ajax csrf토큰
	var token = $("meta[name='_csrf']").attr('content');
    var header = $("meta[name='_csrf_header']").attr('content');
    if(token && header) {
        $(document).ajaxSend(function(event, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
    }
	//Json 
	var dataToSend = {name: cateNameUpdate};
	console.log(dataToSend);
	
  // 카테고리 update
  $.ajax({
    url: `/admin/category/${no}`,
    type: "PUT",
   	data: dataToSend,
    success: function () {
		//update 성공후 redirect 
     	location.href = "/admin/category/admin-categorylist"
    }
  });
}



