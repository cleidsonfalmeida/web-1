let colors = ['red', 'green', 'blue'];
let indiceColor = 0;
let p;

window.onload = function(){
    p = document.getElementById("p1");
    p.classList.add(colors[indiceColor]);
    p.classList.add("pointer");
}

function changeColor() {
    const nextColor = colors[(indiceColor + 1) % colors.length];
    confirmation = confirm(`Deseja mudar a cor para ${nextColor.toUpperCase()}?`);

    if(confirmation){
        p.classList.remove(colors[indiceColor]);
        indiceColor = (indiceColor + 1) % colors.length;
        p.classList.add(colors[indiceColor])
    }
}