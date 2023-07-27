/**
 * 
 */

 function changeForm1(btn){
	$(btn).parents().siblings().children("a").removeClass("target");
	$(btn).addClass("target");
 }

 
 $(function(){
	 $("#order-wait").click(function(){
		 
		$.ajax({
			url:"/franchisee/orderwait",
			success:function(result){
				$("#wait").html(result);
			}
			
		})
	 });
 })
 
  $(function(){
	 $("#order-processing").click(function(){
		 
		$.ajax({
			url:"/franchisee/processing",
			success:function(result){
				$("#wait").html(result);
			}
			
		})
	 });
 })
   $(function(){
	 $("#order-cancel").click(function(){
		 
		$.ajax({
			url:"/franchisee/cancel",
			success:function(result){
				$("#wait").html(result);
			}
			
		})
	 });
 })
    $(function(){
	 $("#order-end").click(function(){
		 
		$.ajax({
			url:"/franchisee/end",
			success:function(result){
				$("#wait").html(result);
			}
			
		})
	 });
 })