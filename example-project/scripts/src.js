/* Generated file */
// The function name without underlining and in capital letters corresponds to a tag with same name
// For example, the _ap function corresponds to the AP tag
// The SC tag has no function, this tag corresponds to the script and will be placed at the end of the file

// After pause
function _ap() {

}

// Before pause
function _bp() {

}

// After reload
function _ar() {

}

// Before reload
function _br() {

}

// anyButtonDownAction
function _bd() {
	ac(-0.2);
}

// anyButtonClickAction
function _bk() {

}

// anyButtonsUpAction
function _bu() {
	ac(0.05);
}

// dpadButtonsDownAction
function _pd() {

}

// dpadButtonClickAction
function _pk() {

}

// dpadButtonsUpAction
function _pu() {

}

// actionButtonsDownAction
function _ad() {

}

// actionButtonsClickAction
function _ak() {

}

// actionButtonsUpAction
function _au() {

}

// upButtonDownAction
function _ud() {

}

// upButtonClickAction
function _uk() {

}

// upButtonUpAction
function _uu() {

}

// rightButtonDownAction
function _rd() {

}

// righButtonClickAction
function _rk() {

}

// rightButtonUpAction
function _ru() {

}

// downButtonDownAction
function _dd() {

}

// downButtonClickAction
function _dk() {

}

// downButtonUpAction
function _du() {

}

// leftButtonDownAction
function _ld() {

}

// leftButtonClickAction
function _lk() {

}

// leftButtonUpAction
function _lu() {

}
// qButtonClickAction
function _qd() {

}

// qButtonClickAction
function _qk() {

}

// qButtonUpAction
function _qu() {

}

// rButtonDownAction
function _hd() {

}

// rButtonClickAction
function _hk() {

}

// rButtonUpAction
function _hu() {

}

// startGame
function _sg() {
	scr = new cmpt(chpx(5), "Consolas", "black", 20, 30, "text");
	gmPc = new cmpt(chp(10), chp(10), "#e9472a" , 10, chp(30));
	gmPc.g = 0.05;
}

// afterClearGameArea
function _ac() {
	for (i = 0; i < obts.length; i += 1) {
		gmPc.cCrh(obts[i]);

		if (!iAlv) {
			stp = tr;
			cCP = fl;
		}
	}
}

// beforeClearGameArea
function _bc() {
	
	if (gA.frN == 1 || evIn(tchp(36))) {
		let y = gA.cvsh, mH = chp(7), xH = chp(74), h = mfmr(mH, xH), mG = chp(20), xG = chp(50), g = mfmr(mG, xG);
		obts.push(new cmpt(chp(3), h, "green", y, 0));
		obts.push(new cmpt(chp(3), y - h - g, "green", y, h  + g));
	}

	for (i = 0; i < obts.length; i += 1) {
		obts[i].x += -1;
		obts[i].upt();
	}
	scr.text="SCORE: " + gA.frN;
	scr.upt();
	gmPc.nPs();
	gmPc.upt();
}

/* SC tag area */

let iAlv = tr, gmPc,  obts = [], scr;

function cmpt(w, h, c, x, y, t) {
	this.w = w;
	this.h = h;
	this.sX = 0;
	this.sY = 0;
	this.x = x;
	this.y = y;
	this.g = 0;
	this.gS = 0;
	this.upt = function() {
		ctx = gA.ctx;
		if (t == "text") {
			ctx.font = this.w + " " + this.h;
			ctx.fillStyle = c;
			ctx.fillText(this.text, this.x, this.y);
		} else {
			ctx.fillStyle = c;
			ctx.fillRect(this.x, this.y, this.w, this.h);
		}
	}
	this.nPs = function() {
		this.gS += this.g;
		this.x += this.sX;
		this.y += this.sY + this.gS;
	}
	this.cCrh = function(oObj) {
		if (!(this.y + this.h < oObj.y || 
			this.y > oObj.y + oObj.h ||
			this.x + this.w < oObj.x ||
			this.x > oObj.x + oObj.w) || 
		this.y > gA.cvsh - this.h ||
			this.y < 0) {
			iAlv = fl;
		}
	}
}

//accelerate
function ac(n) {
	gmPc.g = n;
}

//everyinterval
function evIn(n) {
	if ((gA.frN / n) % 1 == 0) {return tr}
	return fl;
}