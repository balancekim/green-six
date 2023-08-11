/**
 * 
 */
var stompClient=null;
var key=null;

function sendMessage(){
	//var sendMessage=document.getElementById("message").value;
	var sendMessage=$.trim($("#message").val());
	if(sendMessage==""){
		alert("메시지가 입력되지 않았어요");$("#message").val("");return;
	}
	var data={
		key: key,
		name: $("#name").val(),
		content: sendMessage
	};
	//메세지 전달 
	stompClient.send("/greensix/order",{}, JSON.stringify(data))
}

function connect(){
	//socket 접속
	var socket=new SockJS("/green-six");//
	stompClient=Stomp.over(socket);
	
	//접속
	stompClient.connect({},function(frame){
		console.log("Connected:"+frame);
		key=$("#key").val()==""?dateTimeString():$("#key").val();
		var data={
			key: key,
			name: $("#name").val(),
			content: $("#name").val()+"님! 입장하셧습니다."
		};
		//인사말 메세지 전송
		stompClient.send("/greensix/hello",{}, JSON.stringify(data));
		
		
		//메세지 수신을위한 구독 설정 구독할브로커-사용자마다-주문메시지이므로 다르게
		stompClient.subscribe(`/topic/order/${key}`,function(msgData){
			//자바의 메세지 객체-> JSON(name:value)
			var msg=JSON.parse(msgData.body);
			var me=$("#name").val();
			var msgText=`
				<tr>
					<td class="msg-left ${msg.name==me?'me':''}">
						${
							msg.name!="hello" ?
							`<div class="name">${msg.name}</div>`:
							``
						}
						
						<div>${msg.content}</div>
					</td>
				</tr>
			`;
				
			$("#greetings").append(msgText);
			
		});
	});
	
	connectMode(true);
}
function disconnect(){
	//socket close
	if(stompClient!=null){
		stompClient.disconnect();
	}
	console.log(">>>>Disconnected!!!");
	connectMode(false);
}
//버튼조작
function connectMode(isTrue){
	
	$("#connect").prop("disabled", isTrue);
	$("#disconnect").prop("disabled", !isTrue);
	//if(isTrue){
		$("#name").prop("disabled", isTrue);
	//}else{
		//$("#name").prop("disabled", !isTrue);
	//}
}


$(function(){
	/*
	$("form").on('submit',function(e){
		e.preventDefault();
	})
	//*/
	//*
	$("form").submit(function(e){
		e.preventDefault();
	})
	//*/
	
	$("#connect").click(connect);
	$("#disconnect").click(disconnect);
	$("#send").click(sendMessage);
});


function dateTimeString(){
	const currentDate = new Date();
	//const year = currentDate.getFullYear();
	//const month = String(currentDate.getMonth() + 1).padStart(2, '0');
	//const day = String(currentDate.getDate()).padStart(2, '0');
	//const hours = String(currentDate.getHours()).padStart(2, '0');
	//const minutes = String(currentDate.getMinutes()).padStart(2, '0');
	//const seconds = String(currentDate.getSeconds()).padStart(2, '0');
	const milliseconds = currentDate.getTime();
	
	//const currentDateTimeString = `${year}${month}${day}${hours}${minutes}${seconds}${milliseconds}`;
	const currentDateTimeString = `${milliseconds}`;
	console.log(currentDateTimeString);
	return currentDateTimeString;
}