/**
 * 
 */
var queueName=null;
var exchangeName=null;
var routingKey=null;
// RabbitMQ 설정 함수-가맹점별 주문을위한 큐생성
// 큐이름은 점주 가입시 자동으로 부여-영문또는 no값 으로 점포명 구현
function setupRabbitMQ() {
	
	queueName=$("#queueName").val();
	exchangeName=$("#exchangeName").val();
	routingKey=$("#routingKey").val();
	
    // 1.메시지 큐 생성
    createQueue(queueName);

    // 2.익스체인지 생성
    createExchange(exchangeName);

    // 3. 큐와 익스체인지 바인딩
    var bindingInfo = {
        queueName: queueName,
        exchangeName: exchangeName,
        routingKey: routingKey
    };
    bindQueueToExchange(bindingInfo);
}

// 메시지 큐 생성 함수
function createQueue(queueName) {
    $.ajax({
        type: 'POST',
        url: '/rabbit/queues/' + queueName,
        success: function(response) {
            console.log('Queue ' + queueName + ' created successfully!');
        },
        error: function(error) {
            console.error('Failed to create queue:', error);
        }
    });
}

// 익스체인지 생성 함수
function createExchange(exchangeName) {
    $.ajax({
        type: 'POST',
        url: '/rabbit/exchanges/' + exchangeName,
        success: function(response) {
            console.log('Exchange ' + exchangeName + ' created successfully!');
        },
        error: function(error) {
            console.error('Failed to create exchange:', error);
        }
    });
}

// 큐와 익스체인지 바인딩 함수
function bindQueueToExchange(bindingInfo) {
    $.ajax({
        type: 'POST',
        url: '/rabbit/bindings',
        data: JSON.stringify(bindingInfo),
        contentType: 'application/json',
        success: function(response) {
            console.log('Queue ' + bindingInfo.queueName + ' bound to Exchange ' + bindingInfo.exchangeName + ' with routing key ' + bindingInfo.routingKey);
        },
        error: function(error) {
            console.error('Failed to bind queue to exchange:', error);
        }
    });
}
