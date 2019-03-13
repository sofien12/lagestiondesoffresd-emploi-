var domLib_userAgent=navigator.userAgent.toLowerCase();var domLib_isMac=navigator.appVersion.indexOf("Mac")!=-1;var domLib_isWin=domLib_userAgent.indexOf("windows")!=-1;var domLib_isOpera=domLib_userAgent.indexOf("opera")!=-1;var domLib_isOpera7up=domLib_userAgent.match(/opera.(7|8)/i);var domLib_isSafari=domLib_userAgent.indexOf("safari")!=-1;var domLib_isKonq=domLib_userAgent.indexOf("konqueror")!=-1;var domLib_isKHTML=(domLib_isKonq||domLib_isSafari||domLib_userAgent.indexOf("khtml")!=-1);var domLib_isIE=(!domLib_isKHTML&&!domLib_isOpera&&(domLib_userAgent.indexOf("msie 5")!=-1||domLib_userAgent.indexOf("msie 6")!=-1||domLib_userAgent.indexOf("msie 7")!=-1||domLib_userAgent.indexOf("msie 8")!=-1));var domLib_isIE5up=domLib_isIE;var domLib_isIE50=(domLib_isIE&&domLib_userAgent.indexOf("msie 5.0")!=-1);var domLib_isIE55=(domLib_isIE&&domLib_userAgent.indexOf("msie 5.5")!=-1);var domLib_isIE5=(domLib_isIE50||domLib_isIE55);var domLib_isGecko=domLib_userAgent.indexOf("gecko/")!=-1;var domLib_isMacIE=(domLib_isIE&&domLib_isMac);var domLib_isIE55up=domLib_isIE5up&&!domLib_isIE50&&!domLib_isMacIE;var domLib_isIE6up=domLib_isIE55up&&!domLib_isIE55;var domLib_standardsMode=(document.compatMode&&document.compatMode=="CSS1Compat");var domLib_useLibrary=(domLib_isOpera7up||domLib_isKHTML||domLib_isIE5up||domLib_isGecko||domLib_isMacIE||document.defaultView);var domLib_hasBrokenTimeout=(domLib_isMacIE||(domLib_isKonq&&domLib_userAgent.match(/konqueror\/3.([2-9])/)==null));var domLib_canFade=(domLib_isGecko||domLib_isIE||domLib_isSafari||domLib_isOpera);var domLib_canDrawOverSelect=(domLib_isMac||domLib_isOpera||domLib_isGecko);var domLib_canDrawOverFlash=(domLib_isMac||domLib_isWin);var domLib_eventTarget=domLib_isIE?"srcElement":"currentTarget";var domLib_eventButton=domLib_isIE?"button":"which";var domLib_eventTo=domLib_isIE?"toElement":"relatedTarget";var domLib_stylePointer=domLib_isIE?"hand":"pointer";var domLib_styleNoMaxWidth=domLib_isOpera?"10000px":"none";var domLib_hidePosition="-1000px";var domLib_scrollbarWidth=14;var domLib_autoId=1;var domLib_zIndex=100;var domLib_collisionElements;var domLib_collisionsCached=false;var domLib_timeoutStateId=0;var domLib_timeoutStates=new Hash();if(!document.ELEMENT_NODE){document.ELEMENT_NODE=1;document.ATTRIBUTE_NODE=2;document.TEXT_NODE=3;document.DOCUMENT_NODE=9;document.DOCUMENT_FRAGMENT_NODE=11;}function domLib_clone(i){var h={};for(var g in i){var e=i[g];try{if(e!=null&&typeof(e)=="object"&&e!=window&&!e.nodeType){h[g]=domLib_clone(e);}else{h[g]=e;}}catch(j){h[g]=e;}}return h;}function Hash(){this.length=0;this.numericLength=0;this.elementData=[];for(var b=0;b<arguments.length;b+=2){if(typeof(arguments[b+1])!="undefined"){this.elementData[arguments[b]]=arguments[b+1];this.length++;if(arguments[b]==parseInt(arguments[b])){this.numericLength++;}}}}Hash.prototype.get=function(b){return this.elementData[b];};Hash.prototype.set=function(d,c){if(typeof(c)!="undefined"){if(typeof(this.elementData[d])=="undefined"){this.length++;if(d==parseInt(d)){this.numericLength++;}}return this.elementData[d]=c;}return false;};Hash.prototype.remove=function(c){var d;if(typeof(this.elementData[c])!="undefined"){this.length--;if(c==parseInt(c)){this.numericLength--;}d=this.elementData[c];delete this.elementData[c];}return d;};Hash.prototype.size=function(){return this.length;};Hash.prototype.has=function(b){return typeof(this.elementData[b])!="undefined";};Hash.prototype.find=function(d){for(var c in this.elementData){if(this.elementData[c]==d){return c;}}};Hash.prototype.merge=function(c){for(var d in c.elementData){if(typeof(this.elementData[d])=="undefined"){this.length++;if(d==parseInt(d)){this.numericLength++;}}this.elementData[d]=c.elementData[d];}};Hash.prototype.compare=function(c){if(this.length!=c.length){return false;}for(var d in this.elementData){if(this.elementData[d]!=c.elementData[d]){return false;}}return true;};function domLib_isDescendantOf(d,e){if(d==e){return true;}while(d!=document.documentElement){try{if((tmp_object=d.offsetParent)&&tmp_object==e){return true;}else{if((tmp_object=d.parentNode)==e){return true;}else{d=tmp_object;}}}catch(f){return true;}}return false;}function domLib_detectCollisions(o,p,m){if(!domLib_collisionsCached){var k=[];if(!domLib_canDrawOverFlash){k[k.length]="object";}if(!domLib_canDrawOverSelect){k[k.length]="select";}domLib_collisionElements=domLib_getElementsByTagNames(k,true);domLib_collisionsCached=m;}if(p){for(var s=0;s<domLib_collisionElements.length;s++){var t=domLib_collisionElements[s];if(!t.hideList){t.hideList=new Hash();}t.hideList.remove(o.id);if(!t.hideList.length){domLib_collisionElements[s].style.visibility="visible";if(domLib_isKonq){domLib_collisionElements[s].style.display="";}}}return;}else{if(domLib_collisionElements.length==0){return;}}var q=domLib_getOffsets(o);for(var s=0;s<domLib_collisionElements.length;s++){var t=domLib_collisionElements[s];if(domLib_isDescendantOf(t,o)){continue;}if(domLib_isKonq&&t.tagName=="SELECT"&&(t.size<=1&&!t.multiple)){continue;}if(!t.hideList){t.hideList=new Hash();}var r=domLib_getOffsets(t);var l=Math.sqrt(Math.pow(r.get("leftCenter")-q.get("leftCenter"),2)+Math.pow(r.get("topCenter")-q.get("topCenter"),2));var n=r.get("radius")+q.get("radius");if(l<n){if((q.get("leftCenter")<=r.get("leftCenter")&&q.get("right")<r.get("left"))||(q.get("leftCenter")>r.get("leftCenter")&&q.get("left")>r.get("right"))||(q.get("topCenter")<=r.get("topCenter")&&q.get("bottom")<r.get("top"))||(q.get("topCenter")>r.get("topCenter")&&q.get("top")>r.get("bottom"))){t.hideList.remove(o.id);if(!t.hideList.length){t.style.visibility="visible";if(domLib_isKonq){t.style.display="";}}}else{t.hideList.set(o.id,true);t.style.visibility="hidden";if(domLib_isKonq){t.style.display="none";}}}}}function domLib_getOffsets(h){var i=h;var j=h.offsetWidth;var k=h.offsetHeight;var l=0;var g=0;while(h){l+=h.offsetLeft;g+=h.offsetTop;h=h.offsetParent;}if(domLib_isMacIE){l+=10;g+=10;}return new Hash("left",l,"top",g,"right",l+j,"bottom",g+k,"leftCenter",l+j/2,"topCenter",g+k/2,"radius",Math.max(j,k));}function domLib_setTimeout(h,k,g){if(typeof(g)=="undefined"){g=[];}if(k==-1){return;}else{if(k==0){h(g);return 0;}}var l=domLib_clone(g);if(!domLib_hasBrokenTimeout){return setTimeout(function(){h(l);},k);}else{var i=domLib_timeoutStateId++;var j=new Hash();j.set("function",h);j.set("args",l);domLib_timeoutStates.set(i,j);j.set("timeoutId",setTimeout("domLib_timeoutStates.get("+i+").get('function')(domLib_timeoutStates.get("+i+").get('args')); domLib_timeoutStates.remove("+i+");",k));return i;}}function domLib_clearTimeout(b){if(!domLib_hasBrokenTimeout){clearTimeout(b);}else{if(domLib_timeoutStates.has(b)){clearTimeout(domLib_timeoutStates.get(b).get("timeoutId"));domLib_timeoutStates.remove(b);}}}function domLib_getEventPosition(d){var e=new Hash("x",0,"y",0,"scrollX",0,"scrollY",0);if(domLib_isIE){var f=(domLib_standardsMode?document.documentElement:document.body);if(f){e.set("x",d.clientX+f.scrollLeft);e.set("y",d.clientY+f.scrollTop);e.set("scrollX",f.scrollLeft);e.set("scrollY",f.scrollTop);}}else{e.set("x",d.pageX);e.set("y",d.pageY);e.set("scrollX",d.pageX-d.clientX);e.set("scrollY",d.pageY-d.clientY);}return e;}function domLib_cancelBubble(c){var d=c?c:window.event;d.cancelBubble=true;}function domLib_getIFrameReference(g){if(domLib_isGecko||domLib_isIE){return g.frameElement;}else{var f=g.name;if(!f||!g.parent){return;}var h=g.parent.document.getElementsByTagName("iframe");for(var e=0;e<h.length;e++){if(h[e].name==f){return h[e];}}}}function domLib_getElementsByClass(h){var i=domLib_isIE5?document.all:document.getElementsByTagName("*");var j=[];var f=0;for(var g=0;g<i.length;g++){if((" "+i[g].className+" ").indexOf(" "+h+" ")!=-1){j[f++]=i[g];}}return j;}function domLib_getElementsByTagNames(k,l){var i=[];for(var g=0;g<k.length;g++){var j=document.getElementsByTagName(k[g]);for(var h=0;h<j.length;h++){if(l&&domLib_getComputedStyle(j[h],"visibility")=="hidden"){continue;}i[i.length]=j[h];}}return i;}function domLib_getComputedStyle(in_obj,in_property){if(domLib_isIE){var humpBackProp=in_property.replace(/-(.)/,function(a,b){return b.toUpperCase();});return eval("in_obj.currentStyle."+humpBackProp);}else{if(domLib_isKonq){var humpBackProp=in_property.replace(/-(.)/,function(a,b){return b.toUpperCase();});return eval("in_obj.style."+in_property);}else{return document.defaultView.getComputedStyle(in_obj,null).getPropertyValue(in_property);}}}function makeTrue(){return true;}function makeFalse(){return false;}