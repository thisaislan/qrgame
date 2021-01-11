function loadSrc() {
    let srcPath = AndroidInterface.getSrcPath();
    let src = document.createElement("script");
    src.setAttribute("src", srcPath);
    src.onload = () => startGame();    

    document.head.appendChild(src);
}