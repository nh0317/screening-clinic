var div2 = document.getElementsByClassName("div6");

function handleClick(event) {
  console.log(event.target);

  console.log(event.target.classList);

  if (event.target.classList[1] === "clicked") {
    event.target.classList.remove("clicked");
  } else {
    for (var i = 0; i < div2.length; i++) {
      div2[i].classList.remove("clicked");
    }

    event.target.classList.add("clicked");
  }
}

function init() {
  for (var i = 0; i < div2.length; i++) {
    div2[i].addEventListener("click", handleClick);
  }
}

function colorChange() {
    var color = ["#FC5C7D", "#6A82FB", "#38ef7d", "#fffbd5", "#b20a2c", "#CAC531"];
  
    var num = Math.floor(Math.random() * color.length);
    var bodyTag = document.getElementById("colorCont");
    bodyTag.style.backgroundColor = color[num];
  }

init();