/**
 * 
 */
	$(function(){
			
			$("#btn-update").click(clickedBtnUpdate);
			$("#btn-cancle").click(clickedBtnCancle);
			$(".update-form").hide();
			
	});
	
	function clickedBtnUpdate(){
		$(".basic").hide();
		$(".update-form").show();
	}
	
	function clickedBtnCancle(){
		$(".basic").show();
		$(".update-form").hide();
	}