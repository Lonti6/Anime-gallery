likeBut.onclick = function()
{
    // 1. Создаём новый объект XMLHttpRequest
    var xhr = new XMLHttpRequest();

    // 2. Конфигурируем его: GET-запрос на URL 'phones.json'
    xhr.open('GET', `/liking?postId=${modalId.value}`, false);

    // 3. Отсылаем запрос
    xhr.send();

    likeCountText.textContent = xhr.getResponseHeader("likeCount");
    let isGood = xhr.getResponseHeader("isGood");

    if (isGood == "true")
        likeBut.src = "images/likeButFull.png"
    else
        likeBut.src = "images/likeBut.png"

}
