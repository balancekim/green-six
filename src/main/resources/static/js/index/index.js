
var images;
var bullets;

var delay=3000;
var target_idx=0;
var timmer;
$(function(){
	visualLodding();
});
///////////////////////////////////////
function visualLodding(){
	$.ajax({
		url:"/index/visual",
		timeout: 5000,
		success: function(result){
			$("#visual>.wrap").html(result);
			
			images=$(".visual-size .in .image");
			bullets=$(".bullet");
			move(0);//초기화
			
			auto();//타이머를 이용한 자동실행
			
			
			$(".bullet").click(bulletClicked);
			
			goodsList();
		}
		
		
	});
}

function bulletClicked(){
	var bi=$(this).index();
	target_idx=bi;
	move(0);
}

function auto(){
	stop();
	timmer=setTimeout(start, delay);
}
function start(){
	move(1);
	auto();
}

function move(dir){
	target_idx=(target_idx+dir) % images.length;
	var next=(target_idx+1) % images.length;
	var prev=(target_idx-1) % images.length;
	images.removeClass("target next prev");
	images.eq(target_idx).addClass("target");
	images.eq(next).addClass("next");
	images.eq(prev).addClass("prev");
	
	bullets.removeClass("target");
	bullets.eq(target_idx).addClass("target");
	
}
//////////////////////////////

//////////////////////////////
function goodsList(){
	$.ajax({
		url:"/index/goods",
		timeout: 5000,
		success:function(result){
			$("#product-view .view-area").html(result);
			saleList();
		}
	});
}

/////////////////////////////////
/////////////////////////////////
function saleList(){
	$.ajax({
		url:"/index/sale",
		timeout: 5000,
		success:function(result){
			$("#product-sale .view-area").html(result);
			noticeList();
		}
	});
}
/////////////////////////////////

/////////////////////////////////
function noticeList(){
	$.ajax({
		url:"/index/notice",
		timeout: 5000,
		success:function(result){
			$(".index-notice").html(result);
			eventList();
		}
	});
}
/////////////////////////////////

/////////////////////////////////
function eventList(){
	$.ajax({
		url:"/index/event",
		timeout: 5000,
		success:function(result){
			$(".index-event").html(result);
		}
	});
}
/////////////////////////////////