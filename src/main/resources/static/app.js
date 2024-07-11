var stompClient = null;
var topic = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
	$("#name").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
		topic = $("#topic").val() ;
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

//연결
function connect() {
    var socket = new SockJS('/endpoint');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings/'+topic, function (greeting) {
			
			const body = JSON.parse(greeting.body);
			if(body.type == 'userCount'){
				showUserCount(body.message);	
			}else{
				showGreeting(JSON.parse(greeting.body));	
			}
            
        });
        
        stompClient.send("/app/enter/"+topic, {}, 
			JSON.stringify({'name': $("#name").val()} )
		);
        
    });
}

//연결종료
function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}
//접속자 수
function showUserCount(message) {
    $('#userCount').html("Active users: " + message);
}



//메시지전송
function send() {
	var content = $("#content").val();
	if(content=="") return false;
	$("#content").val("");
    stompClient.send("/app/hello/"+topic, {}, 
		JSON.stringify({'name': $("#name").val(), 'content': content } )
	);
}

function showGreeting(json) {
	var txt = "<tr><td>{name}</td><td>{dt}</td><td>{content}</td></tr>";
	txt = txt.replace("{name}",json.name)
			 .replace("{dt}",json.dt)
			.replace("{content}",json.content);
    $("#greetings").append(txt);
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { send(); });
});

