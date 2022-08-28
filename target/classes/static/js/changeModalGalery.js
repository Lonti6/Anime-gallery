let elements = document.getElementsByName("carrd");

let modalLable = document.getElementById("cardTitle");
let modalImg = document.getElementById("cardImg");
let modalId = document.getElementById("cardId");
let cardRating = document.getElementById("cardRating");
let likeBut = document.getElementById("likeBut");
let likeCountText = document.getElementById("likeCount");

for (let elem of elements) {
    elem.onclick = function(){
        modalLable.textContent = elem.querySelector('.cardTitle').textContent;
        modalImg.src = elem.querySelector('.card-img-top').src;
        cardRating.textContent = elem.querySelector('.cardRating').textContent;
        modalId.value = elem.querySelector('.postID').value;
        

        var xhr = new XMLHttpRequest();

        // 2. Конфигурируем его: GET-запрос на URL 'phones.json'
        xhr.open('GET', `/postLiked?postId=${modalId.value}`, false);

        // 3. Отсылаем запрос
        xhr.send();

        likeCountText.textContent = xhr.getResponseHeader("likeCount");

        if (xhr.getResponseHeader("isGood") == "true")
            likeBut.src = "images/likeButFull.png"
        else
            likeBut.src = "images/likeBut.png"
    }
  }