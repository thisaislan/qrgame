<a href="https://aimeos.org/">
    <img src="https://raw.githubusercontent.com/thisaislan/qrgame/main/images/app_icon.png" alt="QRGame" title="QRGame" align="right" height="40" />
</a>

# QRGame
Can we storage a game inside a qr code? Yes! At least in theory. That was the question that started that entire project.
QRGame is an Android project that uses QR code as a game cartridge allowing you to read and run a game stored inside it.

<p float="left">
  <img src="https://raw.githubusercontent.com/thisaislan/qrgame/main/images/cover_1.png" width="200" />
  <img src="https://raw.githubusercontent.com/thisaislan/qrgame/main/images/cover_2.png" width="200" /> 
  <img src="https://raw.githubusercontent.com/thisaislan/qrgame/main/images/cover_3.png" width="200" />
  <img src="https://raw.githubusercontent.com/thisaislan/qrgame/main/images/cover_4.png" width="200" />
  <img src="https://raw.githubusercontent.com/thisaislan/qrgame/main/images/cover_5.png" width="200" />
</p>

## Table of Contents
- [Usage](#Usage)
- [Getting started](#Getting-started)
  - [Prerequisites](#Prerequisites)
  - [How it works](#How-it-works)
  - [Tags](#Tags)
  - [Example](#Example)
- [Possible improvements](#Possible-improvements)
- [Thanks](#Thanks)
- [Links](#Links)
- [License](#License)

## Usage
To start using QRGame, just run the app on your smartphone and read the QR code bellow. 

<p align="center">
<img src="https://raw.githubusercontent.com/thisaislan/qrgame/main/images/qrcode.png"
  alt="Qr code"
  width="686">
</p>

You can clone this project and run it on Android Studio or download it from the [Google Play Store](https://play.google.com/store/apps/details?id=thisaislan.qrgame).

## Getting started

### Prerequisites
If you want to create your own game to run on QRGame you will need to know some `java script`, be familiar with a `code editor` and a platform to `create a QR code` (there is one in the [Links](#Links) section :smile:).

### How it works
The QR code has instructions to fill specific functions with actions, when a button is pressed or actions that should be invoked before or after pause for instance. Furthermore, it's possible create new functions to make entire news behaves.
In order to inform if a instruction should fill a specific function or is a new one, it's necessary informe a tag that way:


```
TAG~code instructions
```

### Tags
Bellow you can see all tags and its descriptions.

#### Buttons
Each button can have three states, down, click and up. The down state is when a button is initially pressed (corresponds to onpointerdown in javascript), up when the button has been released (corresponds to onpointerup in javascript), and the click triggers when a full click has been performed, it mens, down and up (corresponds to onclick in javascript). It´s possible control each button separately or in groups.

| Tag | Description                            |
|-----|----------------------------------------|
| BD  | All buttons down                       | 
| Bk  | All buttons click                      |
| BU  | All buttons up                         |


| Tag | Description                            |
|-----|----------------------------------------|
| PD  | Up, right, down and left buttons down  |
| Pk  | Up, right, down and left buttons click |
| PU  | Up, right, down and left buttons up    |

| Tag | Description                            |
|-----|----------------------------------------|
| AD  | Q and R buttons down                   |
| AK  | Q and R buttons click                  |
| AU  | Q and R buttons up                     |

| Tag | Description                            |
|-----|----------------------------------------|
| UD  | Up button down                         |
| UK  | Up button click                        |
| UA  | Up button up                           |
| RD  | Right button down                      |
| RK  | Right button click                     |
| RU  | Right button up                        |
| DD  | Down button down                       |
| DK  | Down button click                      |
| DU  | Down button up                         |
| LD  | Left button down                       |
| LK  | Left button click                      |
| LU  | Left button up                         |

| Tag | Description                            |
|-----|----------------------------------------|
| QD  | Q button down                          |
| QK  | Q button click                         |
| QU  | Q button up                            |
| HD  | R button down                          |
| HK  | R button click                         |
| HU  | R button up                            |

#### Pause
It's possible create custom behaviors to perform after and before pause.

| Tag | Description                            |
|-----|----------------------------------------|
| AP  | After pause code                       |
| BP  | Before pause code                      |

#### Relaod
Like pause, it's possible create custom behaviors to perform after and before reload.

| Tag | Description                            |
|-----|----------------------------------------|
| AR  | After reload code                      |
| BR  | Before reload code                     |

#### Start Game
Instructions in this tag will be called right after the screen is drawn for the first time. The perfect place to initialize variables and make the first settings.

| Tag | Description                            |
|-----|----------------------------------------|
| SG  | Start game code                        |

#### Update Game Area
At each screen update it is erased and redrawn, it is possible to create behaviors to be executed before and after the screen is erased.

| Tag | Description                            |
|-----|----------------------------------------|
| AC  | After clean                            |
| BC  | Before clean                           |

#### Source Game
This tag loads the core of the game, in it you can put any extra behavior you want. What is written for this tag will not be inside specific functions, so if you want to create functions you will have to declare it entirely inside the tag.

| Tag | Description                            |
|-----|----------------------------------------|
| SC  | Source game code                       |

For example, the qr code on [Usage](#Usage) section has the follow instructions:

```js
BD~ac(-0.2);BU~ac(0.05);SG~scr=new cmpt(chpx(5),"Consolas","black",20,30,"text"),gmPc=new cmpt(chp(10),chp(10),"#e9472a",10,chp(30)),gmPc.g=.05;AC~for(i=0;i<obts.length;i+=1)gmPc.cCrh(obts[i]),iAlv||(stp=tr,cCP=fl);BC~if(1==gA.frN||evIn(tchp(36))){let t=gA.cvsh,c=chp(7),p=chp(74),h=mfmr(c,p),s=chp(20),e=chp(50),r=mfmr(s,e);obts.push(new cmpt(chp(3),h,"green",t,0)),obts.push(new cmpt(chp(3),t-h-r,"green",t,h+r))}for(i=0;i<obts.length;i+=1)obts[i].x+=-1,obts[i].upt();scr.text="SCORE: "+gA.frN,scr.upt(),gmPc.nPs(),gmPc.upt();SC~let gmPc,scr,iAlv=tr,obts=[];function cmpt(t,i,h,s,c,x){this.w=t,this.h=i,this.sX=0,this.sY=0,this.x=s,this.y=c,this.g=0,this.gS=0,this.upt=function(){ctx=gA.ctx,"text"==x?(ctx.font=this.w+" "+this.h,ctx.fillStyle=h,ctx.fillText(this.text,this.x,this.y)):(ctx.fillStyle=h,ctx.fillRect(this.x,this.y,this.w,this.h))},this.nPs=function(){this.gS+=this.g,this.x+=this.sX,this.y+=this.sY+this.gS},this.cCrh=function(t){(!(this.y+this.h<t.y||this.y>t.y+t.h||this.x+this.w<t.x||this.x>t.x+t.w)||this.y>gA.cvsh-this.h||this.y<0)&&(iAlv=fl)}}function ac(t){gmPc.g=t}function evIn(t){return gA.frN/t%1==0?tr:fl}
```

With breaklines to help see each tag:

```js
BD~ac(-0.2);

BU~ac(0.05);

SG~scr=new cmpt(chpx(5),"Consolas","black",20,30,"text"),gmPc=new cmpt(chp(10),chp(10),"#e9472a",10,chp(30)),gmPc.g=.05;

AC~for(i=0;i<obts.length;i+=1)gmPc.cCrh(obts[i]),iAlv||(stp=tr,cCP=fl);

BC~if(1==gA.frN||evIn(tchp(36))){let t=gA.cvsh,c=chp(7),p=chp(74),h=mfmr(c,p),s=chp(20),e=chp(50),r=mfmr(s,e);obts.push(new cmpt(chp(3),h,"green",t,0)),obts.push(new cmpt(chp(3),t-h-r,"green",t,h+r))}for(i=0;i<obts.length;i+=1)obts[i].x+=-1,obts[i].upt();scr.text="SCORE: "+gA.frN,scr.upt(),gmPc.nPs(),gmPc.upt();

SC~let gmPc,scr,iAlv=tr,obts=[];function cmpt(t,i,h,s,c,x){this.w=t,this.h=i,this.sX=0,this.sY=0,this.x=s,this.y=c,this.g=0,this.gS=0,this.upt=function(){ctx=gA.ctx,"text"==x?(ctx.font=this.w+" "+this.h,ctx.fillStyle=h,ctx.fillText(this.text,this.x,this.y)):(ctx.fillStyle=h,ctx.fillRect(this.x,this.y,this.w,this.h))},this.nPs=function(){this.gS+=this.g,this.x+=this.sX,this.y+=this.sY+this.gS},this.cCrh=function(t){(!(this.y+this.h<t.y||this.y>t.y+t.h||this.x+this.w<t.x||this.x>t.x+t.w)||this.y>gA.cvsh-this.h||this.y<0)&&(iAlv=fl)}}function ac(t){gmPc.g=t}function evIn(t){return gA.frN/t%1==0?tr:fl}
```

Note that the code is minified to be as small as possible to fit the qr code.
If you look closely, you'll see some functions undeclared nowhere but being called, like the `chp` function. This is because there are already some functions declared to help with writing new qr code. These functions can be seen in the [example project](https://github.com/thisaislan/qrgame/tree/main/example-project) or in the [template project](https://github.com/thisaislan/qrgame/tree/main/template-project).

### Example
Inside the [example folder](https://github.com/thisaislan/qrgame/tree/main/example-project) you can see the same project that is inside the [Usage](#Usage) section qr code. Or if you prefer, inside the [template folder](https://github.com/thisaislan/qrgame/tree/main/template-project) there is a clean project that you can  start your project from zero.

## Possible improvements
QRGame is a proof of concept, because that, there are a lot of possibilities to improve the project. For now I just want to show the poc, but the project is open to new ideas :relaxed:.

## Thanks :sparkles:
Thanks to these wonderful people for all their support, partnership and friendship:

<table>
  <tr>
    <td align="center"><a href="https://github.com/DiegoLuzBraga"><img src="https://avatars.githubusercontent.com/u/42652945?v=4" width="100px;"/><br /><sub><b>Diego Luz</b></sub></a><br/></td>
    <td align="center"><a href="https://github.com/MrMenezes"><img src="https://avatars.githubusercontent.com/u/9851134?v=4" width="100px;"/><br /><sub><b>Erick</b></sub></a><br/></td>
    <td align="center"><a href="https://github.com/jamile-dev"><img src="https://avatars.githubusercontent.com/u/15880523?v=4" width="100px;"/><br /><sub><b>Jamile Lima</b></sub></a><br/></td>
    <td align="center"><a href="https://github.com/marcelovicentegc"><img src="https://avatars.githubusercontent.com/u/33183880?v=4" width="100px;"/><br /><sub><b>Marcelo Cardoso</b></sub></a><br/></td>
    <td align="center"><a href="https://github.com/MNascimentoS"><img src="https://avatars.githubusercontent.com/u/18469148?v=4" width="100px;"/><br /><sub><b>Mateus Nascimento</b></sub></a><br/></td>
    <td align="center"><a href="https://github.com/yagolasse"><img src="https://avatars.githubusercontent.com/u/9968852?v=4" width="100px;"/><br /><sub><b>Yago Ferreira</b></sub></a><br/></td>
  </tr>
</table>

## Links
Some sites used during project development:

  - [Android for Developers](https://developer.android.com/) - Documentation for android application developers
  - [Code Scan](https://github.com/yuriy-budiyev/code-scanner) - Library used to read QR codes
  - [Everything You Need To Know](http://qrcode.meetheed.com/index.php) - website to learn more about how qr codes work
  - [Free Online QR Code Generator](https://www.online-qrcode-generator.com/) - Site to gerate qrcode
  - [Gitignore.io](https://www.toptal.com/developers/gitignore) - Site to create useful .gitignore files for your project
  - [Gitmoji](https://gitmoji.dev/) - An emoji guide for your commit messages
  - [HTML Game Example](https://www.w3schools.com/graphics/game_intro.asp) - Project used to create the example
  - [JavaScript Minifier](https://javascript-minifier.com/) - Site to minifier javascript code
  - [Material.io](https://material.io/) - Material is a design system – backed by open-source code – that helps teams build high-quality digital experiences
  - [MattKC (youtube) - Can you fit a whole game into a QR code?](https://www.youtube.com/watch?v=ExwqNreocpg) - An interesting video about the topic we encountered during the project
  - [Nicole Borrelli (Medium) - Deep Dive: MediaPlayer Best Practices](https://medium.com/androiddevelopers/deep-dive-mediaplayer-best-practices-feb4d15a66f5) - Nice article about Android MediaPlayer
  - [Stack Overflow](https://stackoverflow.com/) - Who doesn't...lol

## License
Copyright (c) 2021-present Aislan Tavares (@thisaislan) and Contributors. QRGame is free and open-source software licensed under the [MIT License](https://github.com/thisaislan/qrgame/blob/main/LICENSE).

#### Third-party library licenses
- [Code Scan](https://github.com/yuriy-budiyev/code-scanner/blob/main/LICENSE)