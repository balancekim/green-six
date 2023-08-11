/**
 * 
 */
$(document).ready(function() {
    // 메시지 전송 버튼 클릭 이벤트 처리
    $('#sendButton').click(function() {
        var message = $('#messageInput').val();
        sendMessage(message);
    });

    // 메시지 수신 함수 호출 (주기적으로 메시지 확인)
    //receiveMessage();
});

// 메시지 전송 함수
function sendMessage(message) {
    var exchangeName = exchangeName; // 익스체인지 이름
    var routingKey = routingKey; // 라우팅 키

    var data = {
        exchangeName: exchangeName,
        routingKey: routingKey,
        message: message
    };

    $.ajax({
        type: 'POST',
        url: '/rabbit/messages',
        data: JSON.stringify(data),
        contentType: 'application/json',
        success: function(response) {
            console.log('Message sent successfully!');
        },
        error: function(error) {
            console.error('Failed to send message:', error);
        }
    });
}

// 메시지 수신 함수
function receiveMessage() {
    //var queueName = 'your_queue_name'; // 수신할 큐의 이름

    $.ajax({
        type: 'GET',
        url: '/rabbit/messages/' + queueName,
        success: function(response) {
            var message = response; // 수신한 메시지
            displayMessage(message); // 수신한 메시지를 화면에 표시
        },
        error: function(error) {
            console.error('Failed to receive message:', error);
        },
        complete: function() {
            // 일정 시간 후에 다시 메시지를 수신하기 위해 재귀 호출 (1초에 한 번씩)
            setTimeout(receiveMessage, 1000);
        }
    });
}

// 수신한 메시지를 화면에 표시하는 함수
function displayMessage(message) {
    $('#messageDisplay').text('Received message: ' + message);
}
