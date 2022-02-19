// var div2 = document.getElementsByClassName("div6");
var timePicker = document.getElementsByClassName("timePicker")
var pickedTime = document.getElementById("reservationTime")
var pickedDate = document.getElementById("calender")
var button = document.getElementById("reservationBtn")
var screenClinicIdx = document.getElementById("screenClinicIdx");
var cntReservation = document.getElementById("cntReservation");

function handleClick(event) {
  // console.log(event.target);
  //
  // console.log(event.target.classList);
  //   console.log(pickedDate.value);

  if (event.target.classList[1] === "clicked") {
    event.target.classList.remove("clicked");
  } else {
    for (var i = 0; i < timePicker.length; i++) {
        timePicker[i].classList.remove("clicked")
    }
    pickedTime.value=event.target.textContent;
    // console.log(pickedTime.value);
    event.target.classList.add("clicked");
  }
}

function postReservation(event){
    var url = "/screenClinic/"+screenClinicIdx.value;
    httpRequest = new XMLHttpRequest();
    httpRequest.onreadystatechange = () => {
        console.log(httpRequest);
        if (httpRequest.readyState === XMLHttpRequest.DONE) {
            if (httpRequest.status === 200) {
                // console.log(httpRequest.response);
                alert('예약이 완료되었습니다');
                location.href = url;
            } else {
                alert('request에 뭔가 문제가 있어요.');
            }
        }
    };

    /* Post 방식으로 요청 */
    httpRequest.open('POST', '/reserve',false);
    /* Response Type을 Json으로 사전 정의 */
    // httpRequest.responseType = "json";

    /* 요청 Header에 컨텐츠 타입은 Json으로 사전 정의 */
    httpRequest.setRequestHeader('Content-Type', 'application/json');
    /* 정의된 서버에 Json 형식의 요청 Data를 포함하여 요청을 전송 */
    const data = {
        "screenClinicIdx" : screenClinicIdx.value,
        "time": pickedTime.value,
        "date": pickedDate.value
    };
    httpRequest.send(JSON.stringify(data));
}

function init() {
  for (var i = 0; i < timePicker.length; i++) {
      timePicker[i].addEventListener("click", handleClick);
  }
    button.addEventListener("click", postReservation);
  pickedDate.value = dateFormat(new Date());
}
function dateFormat(date) {
    let month = date.getMonth() + 1;
    let day = date.getDate();

    month = month >= 10 ? month : '0' + month;
    day = day >= 10 ? day : '0' + day;

    return date.getFullYear() + '-' + month + '-' + day;
}

function colorChange() {
    var color = ["#FC5C7D", "#6A82FB", "#38ef7d", "#fffbd5", "#b20a2c", "#CAC531"];

    var num = Math.floor(Math.random() * color.length);
    var bodyTag = document.getElementById("colorCont");
    bodyTag.style.backgroundColor = color[num];
  }

init();