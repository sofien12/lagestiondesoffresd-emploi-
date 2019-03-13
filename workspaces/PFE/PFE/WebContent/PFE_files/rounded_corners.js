var isIE=navigator.userAgent.toLowerCase().indexOf("msie")>-1;var isMoz=document.implementation&&document.implementation.createDocument;var isSafari=((navigator.userAgent.toLowerCase().indexOf("safari")!=-1)&&(navigator.userAgent.toLowerCase().indexOf("mac")!=-1))?true:false;function curvyCorners(){if(typeof(arguments[0])!="object"){throw newCurvyError("First parameter of curvyCorners() must be an object.");}if(typeof(arguments[1])!="object"&&typeof(arguments[1])!="string"){throw newCurvyError("Second parameter of curvyCorners() must be an object or a class name.");}if(typeof(arguments[1])=="string"){var l=0;var k=getElementsByClass(arguments[1]);}else{var l=1;var k=arguments;}var o=new Array();if(arguments[0].validTags){var m=arguments[0].validTags;}else{var m=["div"];}for(var p=l,h=k.length;p<h;p++){var n=k[p].tagName.toLowerCase();if(inArray(m,n)!==false){o[o.length]=new curvyObject(arguments[0],k[p]);}}this.objects=o;this.applyCornersToAll=function(){for(var b=0,a=this.objects.length;b<a;b++){this.objects[b].applyCorners();}};}function curvyObject(){this.box=arguments[1];this.settings=arguments[0];this.topContainer=null;this.bottomContainer=null;this.masterCorners=new Array();this.contentDIV=null;var n=get_style(this.box,"height","height");var q=get_style(this.box,"width","width");var l=get_style(this.box,"borderTopWidth","border-top-width");var m=get_style(this.box,"borderTopColor","border-top-color");var r=get_style(this.box,"backgroundColor","background-color");var p=get_style(this.box,"backgroundImage","background-image");var k=get_style(this.box,"position","position");var o=get_style(this.box,"paddingTop","padding-top");this.boxHeight=parseInt(((n!=""&&n!="auto"&&n.indexOf("%")==-1)?n.substring(0,n.indexOf("px")):this.box.scrollHeight));this.boxWidth=parseInt(((q!=""&&q!="auto"&&q.indexOf("%")==-1)?q.substring(0,q.indexOf("px")):this.box.scrollWidth));this.borderWidth=parseInt(((l!=""&&l.indexOf("px")!==-1)?l.slice(0,l.indexOf("px")):0));this.boxColour=format_colour(r);this.boxPadding=parseInt(((o!=""&&o.indexOf("px")!==-1)?o.slice(0,o.indexOf("px")):0));this.borderColour=format_colour(m);this.borderString=this.borderWidth+"px"+" solid "+this.borderColour;this.backgroundImage=((p!="none")?p:"");this.boxContent=this.box.innerHTML;if(k!="absolute"){this.box.style.position="relative";}this.box.style.padding="0px";if(isIE&&q=="auto"&&n=="auto"){this.box.style.width="100%";}if(this.settings.autoPad==true&&this.boxPadding>0){this.box.innerHTML="";}this.applyCorners=function(){for(var ab=0;ab<2;ab++){switch(ab){case 0:if(this.settings.tl||this.settings.tr){var ah=document.createElement("DIV");ah.style.width="100%";ah.style.fontSize="1px";ah.style.overflow="hidden";ah.style.position="absolute";ah.style.paddingLeft=this.borderWidth+"px";ah.style.paddingRight=this.borderWidth+"px";var Y=Math.max(this.settings.tl?this.settings.tl.radius:0,this.settings.tr?this.settings.tr.radius:0);ah.style.height=Y+"px";ah.style.top=0-Y+"px";ah.style.left=0-this.borderWidth+"px";this.topContainer=this.box.appendChild(ah);}break;case 1:if(this.settings.bl||this.settings.br){var ah=document.createElement("DIV");ah.style.width="100%";ah.style.fontSize="1px";ah.style.overflow="hidden";ah.style.position="absolute";ah.style.paddingLeft=this.borderWidth+"px";ah.style.paddingRight=this.borderWidth+"px";var ae=Math.max(this.settings.bl?this.settings.bl.radius:0,this.settings.br?this.settings.br.radius:0);ah.style.height=ae+"px";ah.style.bottom=0-ae+"px";ah.style.left=0-this.borderWidth+"px";this.bottomContainer=this.box.appendChild(ah);}break;}}if(this.topContainer){this.box.style.borderTopWidth="0px";}if(this.bottomContainer){this.box.style.borderBottomWidth="0px";}var T=["tr","tl","br","bl"];for(var f in T){if(f>-1<4){var V=T[f];if(!this.settings[V]){if(((V=="tr"||V=="tl")&&this.topContainer!=null)||((V=="br"||V=="bl")&&this.bottomContainer!=null)){var ai=document.createElement("DIV");ai.style.position="relative";ai.style.fontSize="1px";ai.style.overflow="hidden";if(this.backgroundImage==""){ai.style.backgroundColor=this.boxColour;}else{ai.style.backgroundImage=this.backgroundImage;}switch(V){case"tl":ai.style.height=Y-this.borderWidth+"px";ai.style.marginRight=this.settings.tr.radius-(this.borderWidth*2)+"px";ai.style.borderLeft=this.borderString;ai.style.borderTop=this.borderString;ai.style.left=-this.borderWidth+"px";break;case"tr":ai.style.height=Y-this.borderWidth+"px";ai.style.marginLeft=this.settings.tl.radius-(this.borderWidth*2)+"px";ai.style.borderRight=this.borderString;ai.style.borderTop=this.borderString;ai.style.backgroundPosition="-"+(Y+this.borderWidth)+"px 0px";ai.style.left=this.borderWidth+"px";break;case"bl":ai.style.height=ae-this.borderWidth+"px";ai.style.marginRight=this.settings.br.radius-(this.borderWidth*2)+"px";ai.style.borderLeft=this.borderString;ai.style.borderBottom=this.borderString;ai.style.left=-this.borderWidth+"px";ai.style.backgroundPosition="-"+(this.borderWidth)+"px -"+(this.boxHeight+(ae+this.borderWidth))+"px";break;case"br":ai.style.height=ae-this.borderWidth+"px";ai.style.marginLeft=this.settings.bl.radius-(this.borderWidth*2)+"px";ai.style.borderRight=this.borderString;ai.style.borderBottom=this.borderString;ai.style.left=this.borderWidth+"px";ai.style.backgroundPosition="-"+(ae+this.borderWidth)+"px -"+(this.boxHeight+(ae+this.borderWidth))+"px";break;}}}else{if(this.masterCorners[this.settings[V].radius]){var ai=this.masterCorners[this.settings[V].radius].cloneNode(true);}else{var ai=document.createElement("DIV");ai.style.height=this.settings[V].radius+"px";ai.style.width=this.settings[V].radius+"px";ai.style.position="absolute";ai.style.fontSize="1px";ai.style.overflow="hidden";var aj=parseInt(this.settings[V].radius-this.borderWidth);for(var U=0,g=this.settings[V].radius;U<g;U++){if((U+1)>=aj){var af=-1;}else{var af=(Math.floor(Math.sqrt(Math.pow(aj,2)-Math.pow((U+1),2)))-1);}if(aj!=g){if((U)>=aj){var ak=-1;}else{var ak=Math.ceil(Math.sqrt(Math.pow(aj,2)-Math.pow(U,2)));}if((U+1)>=g){var am=-1;}else{var am=(Math.floor(Math.sqrt(Math.pow(g,2)-Math.pow((U+1),2)))-1);}}if((U)>=g){var an=-1;}else{var an=Math.ceil(Math.sqrt(Math.pow(g,2)-Math.pow(U,2)));}if(af>-1){this.drawPixel(U,0,this.boxColour,100,(af+1),ai,-1,this.settings[V].radius);}if(aj!=g){for(var W=(af+1);W<ak;W++){if(this.settings.antiAlias){if(this.backgroundImage!=""){var al=(pixelFraction(U,W,aj)*100);if(al<30){this.drawPixel(U,W,this.borderColour,100,1,ai,0,this.settings[V].radius);}else{this.drawPixel(U,W,this.borderColour,100,1,ai,-1,this.settings[V].radius);}}else{var X=BlendColour(this.boxColour,this.borderColour,pixelFraction(U,W,aj));this.drawPixel(U,W,X,100,1,ai,0,this.settings[V].radius,V);}}}if(this.settings.antiAlias){if(am>=ak){if(ak==-1){ak=0;}this.drawPixel(U,ak,this.borderColour,100,(am-ak+1),ai,0,0);}}else{if(am>=af){this.drawPixel(U,(af+1),this.borderColour,100,(am-af),ai,0,0);}}var aa=this.borderColour;}else{var aa=this.boxColour;var am=af;}if(this.settings.antiAlias){for(var W=(am+1);W<an;W++){this.drawPixel(U,W,aa,(pixelFraction(U,W,g)*100),1,ai,((this.borderWidth>0)?0:-1),this.settings[V].radius);}}}this.masterCorners[this.settings[V].radius]=ai.cloneNode(true);}if(V!="br"){for(var ab=0,h=ai.childNodes.length;ab<h;ab++){var S=ai.childNodes[ab];var t=parseInt(S.style.top.substring(0,S.style.top.indexOf("px")));var d=parseInt(S.style.left.substring(0,S.style.left.indexOf("px")));var b=parseInt(S.style.height.substring(0,S.style.height.indexOf("px")));if(V=="tl"||V=="bl"){S.style.left=this.settings[V].radius-d-1+"px";}if(V=="tr"||V=="tl"){S.style.top=this.settings[V].radius-b-t+"px";}switch(V){case"tr":S.style.backgroundPosition="-"+Math.abs((this.boxWidth-this.settings[V].radius+this.borderWidth)+d)+"px -"+Math.abs(this.settings[V].radius-b-t-this.borderWidth)+"px";break;case"tl":S.style.backgroundPosition="-"+Math.abs((this.settings[V].radius-d-1)-this.borderWidth)+"px -"+Math.abs(this.settings[V].radius-b-t-this.borderWidth)+"px";break;case"bl":S.style.backgroundPosition="-"+Math.abs((this.settings[V].radius-d-1)-this.borderWidth)+"px -"+Math.abs((this.boxHeight+this.settings[V].radius+t)-this.borderWidth)+"px";break;}}}}if(ai){switch(V){case"tl":if(ai.style.position=="absolute"){ai.style.top="0px";}if(ai.style.position=="absolute"){ai.style.left="0px";}if(this.topContainer){this.topContainer.appendChild(ai);}break;case"tr":if(ai.style.position=="absolute"){ai.style.top="0px";}if(ai.style.position=="absolute"){ai.style.right="0px";}if(this.topContainer){this.topContainer.appendChild(ai);}break;case"bl":if(ai.style.position=="absolute"){ai.style.bottom="0px";}if(ai.style.position=="absolute"){ai.style.left="0px";}if(this.bottomContainer){this.bottomContainer.appendChild(ai);}break;case"br":if(ai.style.position=="absolute"){ai.style.bottom="0px";}if(ai.style.position=="absolute"){ai.style.right="0px";}if(this.bottomContainer){this.bottomContainer.appendChild(ai);}break;}}}}var ad=new Array();ad["t"]=Math.abs(this.settings.tl.radius-this.settings.tr.radius);ad["b"]=Math.abs(this.settings.bl.radius-this.settings.br.radius);for(z in ad){if(z=="t"||z=="b"){if(ad[z]){var e=((this.settings[z+"l"].radius<this.settings[z+"r"].radius)?z+"l":z+"r");var ag=document.createElement("DIV");ag.style.height=ad[z]+"px";ag.style.width=this.settings[e].radius+"px";ag.style.position="absolute";ag.style.fontSize="1px";ag.style.overflow="hidden";ag.style.backgroundColor=this.boxColour;switch(e){case"tl":ag.style.bottom="0px";ag.style.left="0px";ag.style.borderLeft=this.borderString;this.topContainer.appendChild(ag);break;case"tr":ag.style.bottom="0px";ag.style.right="0px";ag.style.borderRight=this.borderString;this.topContainer.appendChild(ag);break;case"bl":ag.style.top="0px";ag.style.left="0px";ag.style.borderLeft=this.borderString;this.bottomContainer.appendChild(ag);break;case"br":ag.style.top="0px";ag.style.right="0px";ag.style.borderRight=this.borderString;this.bottomContainer.appendChild(ag);break;}}var ac=document.createElement("DIV");ac.style.position="relative";ac.style.fontSize="1px";ac.style.overflow="hidden";ac.style.backgroundColor=this.boxColour;ac.style.backgroundImage=this.backgroundImage;switch(z){case"t":if(this.topContainer){if(this.settings.tl.radius&&this.settings.tr.radius){ac.style.height=Y-this.borderWidth+"px";ac.style.marginLeft=this.settings.tl.radius-this.borderWidth+"px";ac.style.marginRight=this.settings.tr.radius-this.borderWidth+"px";ac.style.borderTop=this.borderString;if(this.backgroundImage!=""){ac.style.backgroundPosition="-"+(Y+this.borderWidth)+"px 0px";}this.topContainer.appendChild(ac);}this.box.style.backgroundPosition="0px -"+(Y-this.borderWidth)+"px";}break;case"b":if(this.bottomContainer){if(this.settings.bl.radius&&this.settings.br.radius){ac.style.height=ae-this.borderWidth+"px";ac.style.marginLeft=this.settings.bl.radius-this.borderWidth+"px";ac.style.marginRight=this.settings.br.radius-this.borderWidth+"px";ac.style.borderBottom=this.borderString;if(this.backgroundImage!=""){ac.style.backgroundPosition="-"+(ae+this.borderWidth)+"px -"+(this.boxHeight+(Y+this.borderWidth))+"px";}this.bottomContainer.appendChild(ac);}}break;}}}if(this.settings.autoPad==true&&this.boxPadding>0){var Z=document.createElement("DIV");Z.style.position="relative";Z.innerHTML=this.boxContent;Z.className="autoPadDiv";var c=Math.abs(Y-this.boxPadding);var a=Math.abs(ae-this.boxPadding);if(Y<this.boxPadding){Z.style.paddingTop=c+"px";}if(ae<this.boxPadding){Z.style.paddingBottom=ae+"px";}Z.style.paddingLeft=this.boxPadding+"px";Z.style.paddingRight=this.boxPadding+"px";this.contentDIV=this.box.appendChild(Z);}};this.drawPixel=function(u,b,h,c,a,v,f,d){var g=document.createElement("DIV");g.style.height=a+"px";g.style.width="1px";g.style.position="absolute";g.style.fontSize="1px";g.style.overflow="hidden";var e=Math.max(this.settings["tr"].radius,this.settings["tl"].radius);if(f==-1&&this.backgroundImage!=""){g.style.backgroundImage=this.backgroundImage;g.style.backgroundPosition="-"+(this.boxWidth-(d-u)+this.borderWidth)+"px -"+((this.boxHeight+e+b)-this.borderWidth)+"px";}else{g.style.backgroundColor=h;}if(c!=100){setOpacityRC(g,c);}g.style.top=b+"px";g.style.left=u+"px";v.appendChild(g);};}function insertAfter(d,f,e){d.insertBefore(f,e.nextSibling);}function BlendColour(o,q,t){var w=parseInt(o.substr(1,2),16);var p=parseInt(o.substr(3,2),16);var u=parseInt(o.substr(5,2),16);var x=parseInt(q.substr(1,2),16);var r=parseInt(q.substr(3,2),16);var v=parseInt(q.substr(5,2),16);if(t>1||t<0){t=1;}var s=Math.round((w*t)+(x*(1-t)));if(s>255){s=255;}if(s<0){s=0;}var y=Math.round((p*t)+(r*(1-t)));if(y>255){y=255;}if(y<0){y=0;}var A=Math.round((u*t)+(v*(1-t)));if(A>255){A=255;}if(A<0){A=0;}return"#"+IntToHex(s)+IntToHex(y)+IntToHex(A);}function IntToHex(b){base=b/16;rem=b%16;base=base-(rem/16);baseS=MakeHex(base);remS=MakeHex(rem);return baseS+""+remS;}function MakeHex(b){if((b>=0)&&(b<=9)){return b;}else{switch(b){case 10:return"A";case 11:return"B";case 12:return"C";case 13:return"D";case 14:return"E";case 15:return"F";}}}function pixelFraction(m,n,t){var r=0;var s=new Array(1);var o=new Array(1);var l=0;var q="";var p=Math.sqrt((Math.pow(t,2)-Math.pow(m,2)));if((p>=n)&&(p<(n+1))){q="Left";s[l]=0;o[l]=p-n;l=l+1;}var p=Math.sqrt((Math.pow(t,2)-Math.pow(n+1,2)));if((p>=m)&&(p<(m+1))){q=q+"Top";s[l]=p-m;o[l]=1;l=l+1;}var p=Math.sqrt((Math.pow(t,2)-Math.pow(m+1,2)));if((p>=n)&&(p<(n+1))){q=q+"Right";s[l]=1;o[l]=p-n;l=l+1;}var p=Math.sqrt((Math.pow(t,2)-Math.pow(n,2)));if((p>=m)&&(p<(m+1))){q=q+"Bottom";s[l]=p-m;o[l]=0;}switch(q){case"LeftRight":r=Math.min(o[0],o[1])+((Math.max(o[0],o[1])-Math.min(o[0],o[1]))/2);break;case"TopRight":r=1-(((1-s[0])*(1-o[1]))/2);break;case"TopBottom":r=Math.min(s[0],s[1])+((Math.max(s[0],s[1])-Math.min(s[0],s[1]))/2);break;case"LeftBottom":r=(o[0]*s[1])/2;break;default:r=1;}return r;}function rgb2Hex(e){try{var p=rgb2Array(e);var l=parseInt(p[0]);var n=parseInt(p[1]);var k=parseInt(p[2]);var o="#"+IntToHex(l)+IntToHex(n)+IntToHex(k);}catch(m){alert("There was an error converting the RGB value to Hexadecimal in function rgb2Hex");}return o;}function rgb2Array(e){var f=e.substring(4,e.indexOf(")"));var d=f.split(", ");return d;}function setOpacityRC(k,n){n=(n==100)?99.999:n;if(isSafari&&k.tagName!="IFRAME"){var g=rgb2Array(k.style.backgroundColor);var l=parseInt(g[0]);var m=parseInt(g[1]);var h=parseInt(g[2]);k.style.backgroundColor="rgba("+l+", "+m+", "+h+", "+n/100+")";}else{if(typeof(k.style.opacity)!="undefined"){k.style.opacity=n/100;}else{if(typeof(k.style.MozOpacity)!="undefined"){k.style.MozOpacity=n/100;}else{if(typeof(k.style.filter)!="undefined"){k.style.filter="alpha(opacity:"+n+")";}else{if(typeof(k.style.KHTMLOpacity)!="undefined"){k.style.KHTMLOpacity=n/100;}}}}}}function inArray(f,d){for(var e=0;e<f.length;e++){if(f[e]===d){return e;}}return false;}function inArrayKey(c,d){for(key in c){if(key===d){return true;}}return false;}function addEvent(h,k,f,g){if(h.addEventListener){h.addEventListener(k,f,g);return true;}else{if(h.attachEvent){var l=h.attachEvent("on"+k,f);return l;}else{h["on"+k]=f;}}}function removeEvent(h,k,f,g){if(h.removeEventListener){h.removeEventListener(k,f,g);return true;}else{if(h.detachEvent){var l=h.detachEvent("on"+k,f);return l;}else{alert("Handler could not be removed");}}}function format_colour(c){var d="#ffffff";if(c!=""&&c!="transparent"){if(c.substr(0,3)=="rgb"){d=rgb2Hex(c);}else{if(c.length==4){d="#"+c.substring(1,2)+c.substring(1,2)+c.substring(2,3)+c.substring(2,3)+c.substring(3,4)+c.substring(3,4);}else{d=c;}}}return d;}function get_style(obj,property,propertyNS){try{if(obj.currentStyle){var returnVal=eval("obj.currentStyle."+property);}else{if(isSafari&&obj.style.display=="none"){obj.style.display="";var wasHidden=true;}var returnVal=document.defaultView.getComputedStyle(obj,"").getPropertyValue(propertyNS);if(isSafari&&wasHidden){obj.style.display="none";}}}catch(e){}return returnVal;}function getElementsByClass(l,n,k){var o=new Array();if(n==null){n=document;}if(k==null){k="*";}var p=n.getElementsByTagName(k);var h=p.length;var m=new RegExp("(^|s)"+l+"(s|$)");for(i=0,j=0;i<h;i++){if(m.test(p[i].className)){o[j]=p[i];j++;}}return o;}function newCurvyError(b){return new Error("curvyCorners Error:\n"+b);}