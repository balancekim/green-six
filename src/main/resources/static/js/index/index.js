
var images;
var bullets;

var delay=3000;
var target_idx=0;
var timmer;
$(function(){
	
	//async를 통한 순차 진행
	visualLodding();
	goodsList();
	saleList();
	noticeList();
	eventList();

	
	
});
///////////////////////////////////////
function visualLodding(){
	$.ajax({
		async : false,
		url:"/index/visual",
		timeout: 5000,
		success: function(result){
			$("#visual>.wrap").html(result);
			
			images=$(".visual-size .in .image");
			bullets=$(".bullet");
			move(0);//초기화
			
			auto();//타이머를 이용한 자동실행
			
			
			$(".bullet").click(bulletClicked);
			
			
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
		async: false,
		url:"/index/goods",
		timeout: 5000,
		success:function(result){
			$("#product-view .view-area").html(result);
			
		}
	});
}

/////////////////////////////////
/////////////////////////////////
function saleList(){
	$.ajax({
		async: false,
		url:"/index/sale",
		timeout: 5000,
		success:function(result){
			$("#product-sale .view-area").html(result);
			
		}
	});
}
/////////////////////////////////

/////////////////////////////////
function noticeList(){
	$.ajax({
		async: false,
		url:"/index/notice",
		timeout: 5000,
		success:function(result){
			$(".index-notice").html(result);
			
		}
	});
}
/////////////////////////////////

/////////////////////////////////
function eventList(){
	$.ajax({
		async: false,
		url:"/index/event",
		timeout: 5000,
		success:function(result){
			$(".index-event").html(result);
			
			$("#chat-img").attr("src","/images/index/quick-chatbot.gif");
		}
	});
}

/*///////////// 챗봇 //////////////*/
//챗봇얼굴누르면 검색창표시
function chatbot(){
	$(".chatbot-content").show();
}
//챗봇상단 x 버튼 누르면 검색창숨기기
function chatbotClose(){
	$(".chatbot-content").hide();
}

var search2;
//챗봇 검색버튼
function chatbotSearch(){
	var search = $(".search").val();
	search2=search;
	
	
	var token = $("meta[name='_csrf']").attr('content');
    var header = $("meta[name='_csrf_header']").attr('content');
    if(token && header) {
        $(document).ajaxSend(function(event, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
    }
	
	$.ajax({
		async: false,
		url:`/index/chatbot/${search}`,
		timeout: 5000,
		success:function(result){
			var searchWithDiv = 
			`
			<div class="ques-section">
				<span class="question">${search}</span>
			</div>
			<div class="wrap">
				<img id="chat-img" alt="#" width="30px"
				src="/images/index/quick-chatbot.gif">
				<span class="chat-tit">'${search}'로 검색한 결과입니다!</span>
			</div>
			`;
            $(".chatbot-section").append(searchWithDiv);
			$(".chatbot-section").append(result);
		}
	});
}

//질문 누르면 답변 나오기
function showAnswer(qusetion){
	var open = $(qusetion).hasClass("open");
	
	if(open!=true){
		$(qusetion).addClass("open").siblings().children().show();
	}
	if(open==true){
		$(qusetion).removeClass("open").siblings().children().hide();
	}
	
}


//검색결과 페이징 처리
function pageBtn(button){
	search = search2;
	var page = $(button).text();
	var token = $("meta[name='_csrf']").attr('content');
    var header = $("meta[name='_csrf_header']").attr('content');
    if(token && header) {
        $(document).ajaxSend(function(event, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
    }
	
	$.ajax({
		async: false,
		url:`/index/chatbot/${search}`,
		data : {page:page-1},
		timeout: 5000,
		success:function(result){
			$(".chatbot-section").append(result);
		}
	});
	
}
