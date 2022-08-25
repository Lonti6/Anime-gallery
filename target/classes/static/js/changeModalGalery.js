let elements = document.getElementsByName("carrd");

let modalLable = document.getElementById("cardTitle");
let modalImg = document.getElementById("cardImg");
let modalId = document.getElementById("cardId");
let cardRating = document.getElementById("cardRating");

for (let elem of elements) {
    elem.onclick = function(){
        modalLable.textContent = elem.querySelector('.cardTitle').textContent;
        modalImg.src = elem.querySelector('.card-img-top').src;
        cardRating.textContent = elem.querySelector('.cardRating').textContent;
        modalId.value = elem.querySelector('.postID').value;
    }
  }