var cookiePath="/";function getCookieVal(c){var d=document.cookie.indexOf(";",c);if(d==-1){d=document.cookie.length;}return unescape(document.cookie.substring(c,d));}function getCookie(k){var g=k+"=";var i=g.length;var h=document.cookie.length;var j=0;while(j<h){var l=j+i;if(document.cookie.substring(j,l)==g){return getCookieVal(l);}j=document.cookie.indexOf(" ",j)+1;if(j==0){break;}}return null;}function setCookie(p,n){var j=setCookie.arguments;var k=setCookie.arguments.length;var i=(k>2)?j[2]:null;var l=(k>3)?j[3]:null;var o=(k>4)?j[4]:null;var m=(k>5)?j[5]:window.location.protocol=="https:";document.cookie=p+"="+escape(n)+((i==null)?"":("; expires="+i.toGMTString()))+((l==null)?"":("; path="+l))+((o==null)?"":("; domain="+o))+((m==true)?"; secure":"");}function deleteCookie(d){var c=new Date();c.setTime(c.getTime()-1);setCookie(d,"",c,cookiePath);}function testCookie(){var c=new Date();c.setTime(c.getTime()+(60*1000));setCookie("testCookie","OK",c);var d=getCookie("testCookie");if(d=="OK"){return true;}return false;}