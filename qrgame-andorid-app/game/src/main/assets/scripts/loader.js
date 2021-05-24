function load() {
    loadColors();
    loadCore();
}

function loadColors() {
    loadColor("--color-primary", AndroidInterface.getColorPrimary());
    loadColor("--color-secondary", AndroidInterface.getColorSecondary());
    loadColor("--color-secondary-variant", AndroidInterface.getColorSecondaryVariant());
}

function loadColor(colorName, hex) {
    document.documentElement.style.setProperty(colorName, hex);
}

function loadCore() {
    let CorePath = AndroidInterface.getCorePath();
    let core = document.createElement("script");
    core.setAttribute("src", CorePath);
    core.onload = () => startGame();    

    document.head.appendChild(core);
}
