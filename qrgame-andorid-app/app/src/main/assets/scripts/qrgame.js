/* Conts */
const tr = true;
const fl = false;

/* Game control variables */
// canChangePause
let cCP = tr;
// canReload
let cRl = tr;
// stop
let stp = fl;
// onPause
let oPs = fl;

/* Meta game */
function pause() {
	if (!cCP) {
		return;
	}
	_bp();

	if (oPs) {
		updateGameArea();
	} else {
		gA.ctx.font = chpx(5) + " Arial";
		gA.ctx.fillStyle = "black";
		gA.ctx.fillText("Pause", cwp(42), chp(50));				
	}
	oPs = !oPs;

	_ap();
}

function reload() {
	if (!cRl) {
		return;
	}
	_ar();
	window.location.reload()
	_br();
}

/* Buttons */
// All buttons
function anyButtonDownAction() {
	_bd();
}

function anyButtonClickAction() {
	_bk();
}

function anyButtonsUpAction() {
	_bu();
}

// All dpad buttons
function dpadButtonsDownAction() {
	_pd();
	anyButtonDownAction();
}

function dpadButtonClickAction() {
	_pk();
	anyButtonClickAction();
}

function dpadButtonsUpAction() {
	_pu();
	anyButtonsUpAction();
}

// All action buttons
function actionButtonsDownAction() {
	_ad();
	anyButtonDownAction();
}

function actionButtonsClickAction() {
	_ak();
	anyButtonClickAction();
}

function actionButtonsUpAction() {
	_au();
	anyButtonsUpAction();
}

// Dpad buttons
function upButtonDownAction() {
	_ud();
	dpadButtonsDownAction();
}

function upButtonClickAction() {
	_uk();
	dpadButtonClickAction();
}

function upButtonUpAction() {
	_uu();
	dpadButtonsUpAction();
}

function rightButtonDownAction() {
	_rd();
	dpadButtonsDownAction();
}

function righButtonClickAction() {
	_rk();
	dpadButtonClickAction();
}

function rightButtonUpAction() {
	_ru();
	dpadButtonsUpAction();
}

function downButtonDownAction() {
	_dd();
	dpadButtonsDownAction();
}

function downButtonClickAction() {
	_dk();
	dpadButtonClickAction();
}

function downButtonUpAction() {
	_du();
	dpadButtonsUpAction();
}

function leftButtonDownAction() {
	_ld();
	dpadButtonsDownAction();
}

function leftButtonClickAction() {
	_lk();
	dpadButtonClickAction();
}

function leftButtonUpAction() {
	_lu();
	dpadButtonsUpAction();
}

// Action buttons
function qButtonDownAction() {
	_qd();
	actionButtonsDownAction();
}

function qButtonClickAction() {
	_qk();
	actionButtonsClickAction();
}

function qButtonUpAction() {
	_qu();
	actionButtonsUpAction();
}

function rButtonDownAction() {
	_hd();
	actionButtonsDownAction();
}

function rButtonClickAction() {
	_hk();
	actionButtonsClickAction();
}

function rButtonUpAction() {
	_hu();
	actionButtonsUpAction();
}

// gameArea
let gA = {
	// canvas
	cvs : document.getElementById("canvas"),
	start : function() {
		this.cvs.width  = this.cvs.offsetWidth;
		this.cvs.height = this.cvs.offsetHeight;
		// context
		this.ctx = this.cvs.getContext("2d");
		// frameNumber
		this.frN = 0;
		// canvas.height
		this.cvsh = this.cvs.height;
		// canvas.width
		this.cvsw = this.cvs.width;
		this.invl = setInterval(updateGameArea, 20);
	},
	// clear
	clr : function() {
		this.ctx.clearRect(0, 0, this.cvsw, this.cvsh);
	},
}

function startGame() {
	gA.start();
	_sg();
}

function updateGameArea() {		
	if (oPs || stp) {			
		return;
	}
	gA.frN += 1;
	_ac();
	
	if (!stp) {
		gA.clr();
		_bc();
	}
}