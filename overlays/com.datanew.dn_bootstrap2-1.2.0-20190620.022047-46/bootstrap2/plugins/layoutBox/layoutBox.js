var LayoutBox=function(b,a){this.options=a;this.target=$(b);this.init()};LayoutBox.DEFAULTS={classes:"",styles:"",items:[]};LayoutBox.prototype.init=function(){var d=this,a=d.target,c=d.options,b=c.items;a.addClass("layoutBox clearfix");$.each(b,function(m,f){var l=$('<div class="layout_item pull-left"></div>');l.addClass(c.classes).attr("style",c.styles);if(f.id){l.attr("id",f.id)}var g=c.styles.split(";"),j=f.styles?g.concat(f.styles.split(";")):g;l.addClass(f.classes).attr("style",j.join(";")).width(f.width).height(f.height).html(f.html);a.append(l);var n=f.children,e=parseInt(l.css("border-top-width"))+parseInt(l.css("border-bottom-width")),k=parseInt(f.height),i=k-e,h=n.dtype,o=$("<DIV>");l.append(o);if(n.id){o.attr("id",n.id)}if(h==="html"){o.append(n.dragHtml)}else{if(h.indexOf("tree")>-1){n.outerHeight=i}else{if(h==="webupload"){h.listHeight=i}else{if(h==="dtable"||h==="mediaBox"||h==="videoBox"){n.height=i}}}o[h](n)}})};