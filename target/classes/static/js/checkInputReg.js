var passError = document.getElementById("passError");
var pasInp = document.getElementById('floatingPassword');

console.log(passError);

passError.style.display = 'none';

pasInp.oninput = function () {
    if (pasInp.value.length > 7){
        passError.style.display = 'none';
        return;
    }

    passError.style.display = 'block';
}