/**
 * 
 */

function cateBtnClicked(btn){
	$(btn).parents(".cate-wrap").siblings().children().children().siblings("div").removeClass("open")
	//$(btn).parents().siblings().addClass("success")
	$(btn).siblings("div").addClass("open");
	//수정버튼이 눌렸을 때 수정버튼을 hide 해야하는데 뭔지모르겠슴  ㅠㅠ 살려줘
 }

 //카테고리 delete
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

/* update 비동기하다가 포기*/

function cateUpdateBtn(btn){
	var cateNameUpdate = $(btn).parent().siblings("#cateName").val();
	alert(cateNameUpdate);
	var token = $("meta[name='_csrf']").attr('content');
    var header = $("meta[name='_csrf_header']").attr('content');
    if(token && header) {
        $(document).ajaxSend(function(event, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
    }

  var dataToSend = { "categoryName": cateNameUpdate };


  var jsonData = JSON.stringify(dataToSend);

  // 카테고리 update
  $.ajax({
    url: `/admin/category/${no}`,
    type: "PUT",
    data: jsonData, // 
    contentType: 'application/json', 
    success: function (result) {
      console.log(result);
    }
  });
}
	


