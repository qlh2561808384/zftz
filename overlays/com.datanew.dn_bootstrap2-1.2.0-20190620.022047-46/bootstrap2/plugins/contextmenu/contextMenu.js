window.ContextMenu={_className:"yuri2-context-menu",_stopProp:function(a){if(a.cancelable){if(!a.defaultPrevented){a.preventDefault()}}a.stopImmediatePropagation();a.stopPropagation()},_getMainContent:function(a){return a.replace(/<\/?.+?>/g,"")},render:function(g,a,b,d){d||(d="");var i=g.clientX,h=g.clientY;var j=/^url/;var k=null;this._stopProp(g);this._removeContextMenu();if(a===true){return}if(typeof a==="object"&&a.length===0){a=[["..."]]}var c=$("<div class='"+ContextMenu._className+" "+d+"'><ul></ul></div>");$("body").append(c);var f=c.find("ul");if(i+150>document.body.clientWidth){i-=150;f.addClass("left")}a.forEach(function(m){if(m==="|"){f.append($("<hr/>"))}else{if(typeof(m)==="string"){k='<li><div class="title" title="'+ContextMenu._getMainContent(m)+'">'+m+"</div></li>";f.append($(k))}else{if(typeof(m)==="object"){var l=null;if(j.test(m[2])){l=$('<li><b class="setting" style="background-image:'+m[2]+'"></b><div class="title '+(m[2]===true?"disable":"")+'" title="'+ContextMenu._getMainContent(m[0])+'">'+m[0]+"</div></li>")}else{l=$('<li><div class="title '+(m[1]===true?"disable":"")+'" title="'+ContextMenu._getMainContent(m[0])+'">'+m[0]+"</div></li>")}f.append(l);if(typeof(m[1])==="object"){var e=$("<div class='sub "+ContextMenu._className+" "+d+"'></div>");var n=$("<ul></ul>");l.addClass("sub");e.append(n);if(i+300>document.body.clientWidth){e.addClass("left")}l.append(e);var o=-1;m[1].forEach(function(q){if(q==="|"){n.append($("<hr/>"))}else{if(typeof(q)==="string"){n.append($('<li><div class="title" title="'+ContextMenu._getMainContent(q)+'">'+q+"</div></li>"));o++}else{if(typeof(q)==="object"){var p=$('<li><div class="title '+(q[2]===true?"disable":"")+'" title="'+ContextMenu._getMainContent(q[0])+'">'+q[0]+"</div></li>");n.append(p);if(q[2]!==true){p.click(b,q[1]);p.click(function(){ContextMenu._removeContextMenu()})}o++}}}});if(h+c.height()>document.body.clientHeight&&document.body.clientHeight>0){e.css("top","-"+(o*30)+"px")}}else{if(typeof(m[1])==="function"&&m[2]!==true){l.click(b,m[1]);l.click(function(){ContextMenu._removeContextMenu()})}}}}}});if(h+c.height()>document.body.clientHeight&&document.body.clientHeight>0){h-=c.height()}c.css({top:h,left:i,})},_removeContextMenu:function(){$("."+ContextMenu._className).remove()},};$(document).click(function(a){if($(a.target).hasClass(ContextMenu._className)||$(a.target).parents("."+ContextMenu._className).length>0){return}ContextMenu._removeContextMenu()});