//truncCanvasHeightPercent
function tchp(percent) {
	return Math.trunc(chp(percent));
}

//truncCanvasWidthPercent
function tcwp(percent) {
	return Math.trunc(cwp(percent));
}

//canvasHeightPercentPx
function chpx(percent) {
	return chp(percent) + "px";
}

//canvasWidthPercentPx
function cwpx(percent) {
	return cwp(percent) + "px";
}

//canvasHeightPercent
function chp(percent) {
	return gA.cvsh * (percent / 100);
}

//canvasWidthPercent
function cwp(percent) {
	return gA.cvsw * (percent / 100);
}