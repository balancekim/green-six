/**
 * 
 */
$(function() {
	var li = $("#gnb > .wrap > ul > li");
	li.mouseenter(function() {
		var index = li.index(this) + 1;
		showSubMenu(index);
	});

	li.mouseleave(function() {
		var index = li.index(this) + 1;
		hideSubMenu(index);
	});
	menuShow();
});

function menuShow(){
	$.ajax({
		url:"",
		success:function(){
			
		}
	})
}

function showSubMenu(index) {
	$.ajax({
		url:"/category/{parentno}",
		method:"POST",
		success:function(){
			
		}
	})
	
	
	
	$("#gnb > .wrap > ul > li:nth-of-type(" + index + ") > div").show()
}

function hideSubMenu(index) {
	$("#gnb > .wrap > ul > li:nth-of-type(" + index + ") > div").hide()
}