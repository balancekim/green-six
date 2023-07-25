var delay=3000;
var target_idx=0;
var timmer;


$(function(){
	items=$(".vi .item");
	bullets=$("#visual .bullet");
	move(0);//초기화
	
	auto();//타이머를 이용한 자동실행
	
	$(".bullet-wrap .bullet").click(bulletClicked);
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