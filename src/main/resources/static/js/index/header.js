/**
 * 
 */
$(function() {
	var li = $("#gnb > .wrap > ul > li");
	li.mouseenter(function() {
	});

	li.mouseleave(function() {
	});

});

function categoryMenu(tag){
	var parentNo=$(tag).attr("value");
	var no=$(tag).index()
	var len= $(".menu-list > div").length
	console.log(len)
	$.ajax({
			url: `/category/${parentNo}`,
			success: function(result) {
				$(".sub-list").html(result)
				var menuH= -(50*len) +(50*no) + 'px';
				$(".sub-list").css("left","179px")
				$(".sub-list").css("top",menuH)
			}
		})
}

function showSubMenu() {

	var parentNo = $("#gnb > .wrap > ul > li:first-child").val();

	if (parentNo == 0) {
		$.ajax({
			url: `/category/${parentNo}`,
			success: function(result) {
				$(".menu-list").html(result)
			}
		})

	}

	$(".menu-list").show()
	$(".sub-list").show()
		
	
}

function hideSubMenu() {
	$("#gnb > .wrap > ul > li:nth-of-type(1) > div").hide()
	//$("#gnb > .wrap > ul > li:nth-of-type(" + index + ") > div.sub-list > *").remove()
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
			<section class="chat-con1">
				<div class="ques-section">
					<span class="question">${search}</span>
				</div>
				<div class="wrap">
					<img id="chat-img" alt="#" width="30px"
					src="/images/index/quick-chatbot.gif">
					<span class="chat-tit">'${search}'로 검색한 결과입니다!</span>
				</div>
			</section>
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
