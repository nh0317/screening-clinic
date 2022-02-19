// var div2 = document.getElementsByClassName("div6");
var completeBtn = document.getElementsByClassName("completeBtn");
var reservationTable = document.getElementById("reservationTable");
var currentTime = document.getElementById("currentTime");
var today = document.getElementById("today");
var screenClinicIdx = document.getElementById("screenClinicIdx");

function postComplete(event){
    var result;
    httpRequest = new XMLHttpRequest();
    httpRequest.onreadystatechange = () => {
        if (httpRequest.readyState === XMLHttpRequest.DONE) {
            if (httpRequest.status === 200) {
                result = httpRequest.response;
                console.log(result);
                reservationTable.innerHTML = result;
                init();
            } else {
                alert('request에 뭔가 문제가 있어요.');
            }
        }
    };
    httpRequest.open('POST', '/check_reservation/complete');
    httpRequest.setRequestHeader('Content-Type', 'application/json');
    var reservationIdx=event.target.value;
    const data = {
        "reservationIdx": reservationIdx,
        "screenClinicIdx": screenClinicIdx.value,
        "currentTime" : currentTime.textContent,
        "date" : today.textContent
    };
    httpRequest.send(JSON.stringify(data));
}

function init() {
   for (var i = 0; i < completeBtn.length; i++) {
        completeBtn[i].addEventListener("click", postComplete);
   }
}

init();