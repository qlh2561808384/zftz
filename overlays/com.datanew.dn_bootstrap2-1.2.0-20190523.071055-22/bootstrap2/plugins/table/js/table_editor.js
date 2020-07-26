(function(b){b.fn.tableEditor=function(d){var c=Array.prototype.slice.call(arguments,1);c.unshift(this);return a[d].apply(this,c)};var a={getTdNum:function(i,f){var e=b(i),h=e.find("thead tr"),g=h.length;if(g>1){var c="<thead>",d=0;h.eq(0).find("th").each(function(){var k=b(this),l=parseInt(k.attr("colspan"));if(l===1||!l){c+=k.prop("outerHTML")}else{for(var j=d;j<d+l;j++){c+=h.eq(1).find("th").eq(j).prop("outerHTML")}d+=l}});c+="</thead>"}return((g>1)?b(c):e).find("[data-field='"+f+"']").index()},init:function(f,c){var e=b(this).data("bootstrap.table"),d=e.columns;b.each(d,function(g,h){a.initTd(f,c,h)})},initTd:function(j,k,d){if(d.editor){var n=b(j),o=n.data("bootstrap.table"),h=o.data[k],m=d.field+"",e="tr[data-index='"+k+"']",i=n.find(e),g=d.editor.mappingValue==undefined?(h[m]==null?"":h[m]):h[d.editor.mappingValue],f=a.getTdNum(j,m),c=i.find("td").eq(f).css("overflow","visible");i.addClass("editing");i.find("td").css({padding:"0","vertical-align":"middle"});c.attr({oldValue:g,colNum:f,type:d.editor.type});var l=b("<input/>");l.appendTo(c.empty())[d.editor.type](d.editor);if(g!=""&&g!==undefined){l[d.editor.type]("setValue",g)}}},initAll:function(e){var d=b(this).data("bootstrap.table"),c=b(e);b.each(d.data,function(f){c.tableEditor("init",f)})},cancel:function(j,e){var h=b(this).data("bootstrap.table"),d=b(j),f=h.columns,c=h.data,i=c[e],g=b.extend({},i);b.each(f,function(k,m){if(m.editor){var n="tr[data-index='"+e+"']",p=d.find(n),r=m.field+"",o=a.getTdNum(j,r),l=p.find("td").eq(o),q=l.attr("type");var s=l.find(".form-control");if(s.length>0){s[q]("destroy")}p.removeClass("editing")}d.bootstrapTable("updateRow",{index:e,row:g})})},update:function(j,k){var m=b(this).data("bootstrap.table"),l=b(j),d=m.columns,e="tr[data-index='"+k+"']",i=l.find(e),h=m.data,g=h[k],n=b.extend({},g),c={},f;if(i.hasClass("editing")){b.each(d,function(o,q){if(q.editor){var s=q.field+"",v=a.getTdNum(j,s),u=i.find("td").eq(v),p=u.attr("type");if(u.find(".form-control").length>0){if(m.options.clickToEditMode==="td"){f=s}var r=u.find(".form-control")[p]("getValue"),t=u.find(".form-control")[p]("getText");g[s]=r;c[s]=r;if(q.editor.mappingText){g[q.editor.mappingText]=t}if(q.editor.mappingValue){g[q.editor.mappingValue]=r}}}});b.each(c,function(p,o){if(o!=n[p]&&!(n[p]==null&&o=="")){g.tableEdited=true;return false}});if(m.options.onEditLineSave){if(m.options.onEditLineSave.apply(this,[k,g,n,f])){b.each(d,function(p,r){if(r.editor){var o="tr[data-index='"+k+"']",s=l.find(o),t=r.field+"",v=a.getTdNum(j,t),u=s.find("td").eq(v),q=u.attr("type");if(u.find(".form-control").length>0){u.find(".form-control")[q]("destroy")}}});l.bootstrapTable("updateRow",{index:k,row:g})}}}},updateAll:function(j){var h=b(this).data("bootstrap.table"),f=b(j),g=h.columns,c=h.data,i=[],e=false,d;b.each(c,function(l){var p=c[l],n=b.extend({},p),k="tr[data-index='"+l+"']",o=f.find(k),m={};b.each(g,function(q,s){if(s.editor&&o.hasClass("editing")){var u=s.field+"",x=a.getTdNum(j,u),w=o.find("td").eq(x),r=w.attr("type");if(w.find(".form-control").length>0){if(h.options.clickToEditMode==="td"){d=u}var t=w.find(".form-control")[r]("getValue"),v=w.find(".form-control")[r]("getText");p[u]=t;m[u]=t;if(s.editor.mappingText){p[s.editor.mappingText]=v}if(s.editor.mappingValue){p[s.editor.mappingValue]=t}}}});b.each(m,function(r,q){if(q!=n[r]&&!(n[r]==null&&q=="")){p.tableEdited=true;return false}});i.push(p);if(h.options.onEditLineSave){if(h.options.onEditLineSave.apply(this,[l,p,n,d])){e=true}else{e=false}}});if(e){f.bootstrapTable("load",i)}}}})(jQuery);