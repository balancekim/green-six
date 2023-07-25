var images;
var bullets;

var delay=3000;
var target_idx=0;
var timmer;


$(function(){
	images=$(".visual-size .in .image");
	bullets=$(".visual-size .in .bullet");
	move(0);//초기화
	
	auto();//타이머를 이용한 자동실행
	
	
	$(".visual-size .in .bullet").click(bulletClicked);
});

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
	//var target_idx=$(".item.target").index();
	target_idx=(target_idx+dir) % images.length;
	var next=(target_idx+1) % images.length;
	var prev=(target_idx-1) % images.length;
	images.removeClass("target next prev");
	images.eq(target_idx).addClass("target");
	images.eq(next).addClass("next");
	images.eq(prev).addClass("prev");
	
	bullets.removeClass("target");
	bullets.eq(target_idx).addClass("target");
	
	//$(".txt .target").text($(".item.target").index()+1);
}