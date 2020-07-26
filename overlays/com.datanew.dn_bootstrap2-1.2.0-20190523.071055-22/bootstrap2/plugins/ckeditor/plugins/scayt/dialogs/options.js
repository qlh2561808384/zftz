CKEDITOR.dialog.add("scaytDialog",function(i){var f=i.scayt,a='\x3cp\x3e\x3cimg src\x3d"'+f.getLogo()+'" /\x3e\x3c/p\x3e\x3cp\x3e'+f.getLocal("version")+f.getVersion()+'\x3c/p\x3e\x3cp\x3e\x3ca href\x3d"http://scayt.com/user_manual/scayt_plugin_for_ckeditor4_user_manual.pdf" target\x3d"_blank" style\x3d"text-decoration: underline; color: blue;"\x3e'+f.getLocal("btn_userManual")+"\x3c/a\x3e\x3c/p\x3e\x3cp\x3e"+f.getLocal("text_copyrights")+"\x3c/p\x3e",h=CKEDITOR.document,e={isChanged:function(){return null===this.newLang||this.currentLang===this.newLang?!1:!0},currentLang:f.getLang(),newLang:null,reset:function(){this.currentLang=f.getLang();this.newLang=null},id:"lang"},a=[{id:"options",label:f.getLocal("tab_options"),onShow:function(){},elements:[{type:"vbox",id:"scaytOptions",children:function(){var d=f.getApplicationConfig(),g=[],k={"ignore-all-caps-words":"label_allCaps","ignore-domain-names":"label_ignoreDomainNames","ignore-words-with-mixed-cases":"label_mixedCase","ignore-words-with-numbers":"label_mixedWithDigits"},j;for(j in d){d={type:"checkbox"},d.id=j,d.label=f.getLocal(k[j]),g.push(d)}return g}(),onShow:function(){this.getChild();for(var c=i.scayt,d=0;d<this.getChild().length;d++){this.getChild()[d].setValue(c.getApplicationConfig()[this.getChild()[d].id])}}}]},{id:"langs",label:f.getLocal("tab_languages"),elements:[{id:"leftLangColumn",type:"vbox",align:"left",widths:["100"],children:[{type:"html",id:"langBox",style:"overflow: hidden; white-space: normal;margin-bottom:15px;",html:'\x3cdiv\x3e\x3cdiv style\x3d"float:left;width:45%;margin-left:5px;" id\x3d"left-col-'+i.name+'" class\x3d"scayt-lang-list"\x3e\x3c/div\x3e\x3cdiv style\x3d"float:left;width:45%;margin-left:15px;" id\x3d"right-col-'+i.name+'" class\x3d"scayt-lang-list"\x3e\x3c/div\x3e\x3c/div\x3e',onShow:function(){var c=i.scayt.getLang();h.getById("scaytLang_"+i.name+"_"+c).$.checked=!0}},{type:"html",id:"graytLanguagesHint",html:'\x3cdiv style\x3d"margin:5px auto; width:95%;white-space:normal;" id\x3d"'+i.name+'graytLanguagesHint"\x3e\x3cspan style\x3d"width:10px;height:10px;display: inline-block; background:#02b620;vertical-align:top;margin-top:2px;"\x3e\x3c/span\x3e - This languages are supported by Grammar As You Type(GRAYT).\x3c/div\x3e',onShow:function(){var c=h.getById(i.name+"graytLanguagesHint");i.config.grayt_autoStartup||(c.$.style.display="none")}}]}]},{id:"dictionaries",label:f.getLocal("tab_dictionaries"),elements:[{type:"vbox",id:"rightCol_col__left",children:[{type:"html",id:"dictionaryNote",html:""},{type:"text",id:"dictionaryName",label:f.getLocal("label_fieldNameDic")||"Dictionary name",onShow:function(c){var d=c.sender,g=i.scayt;c=SCAYT.prototype.UILib;var j=d.getContentElement("dictionaries","dictionaryName").getInputElement().$;g.isLicensed()||(j.disabled=!0,c.css(j,{cursor:"not-allowed"}));setTimeout(function(){d.getContentElement("dictionaries","dictionaryNote").getElement().setText("");null!=g.getUserDictionaryName()&&""!=g.getUserDictionaryName()&&d.getContentElement("dictionaries","dictionaryName").setValue(g.getUserDictionaryName())},0)}},{type:"hbox",id:"udButtonsHolder",align:"left",widths:["auto"],style:"width:auto;",children:[{type:"button",id:"createDic",label:f.getLocal("btn_createDic"),title:f.getLocal("btn_createDic"),onLoad:function(){this.getDialog();var c=i.scayt,d=SCAYT.prototype.UILib,g=this.getElement().$,j=this.getElement().getChild(0).$;c.isLicensed()||(d.css(g,{cursor:"not-allowed"}),d.css(j,{cursor:"not-allowed"}))},onClick:function(){var c=this.getDialog(),d=b,g=i.scayt,j=c.getContentElement("dictionaries","dictionaryName").getValue();g.isLicensed()&&g.createUserDictionary(j,function(k){k.error||d.toggleDictionaryState.call(c,"dictionaryState");k.dialog=c;k.command="create";k.name=j;i.fire("scaytUserDictionaryAction",k)},function(k){k.dialog=c;k.command="create";k.name=j;i.fire("scaytUserDictionaryActionError",k)})}},{type:"button",id:"restoreDic",label:f.getLocal("btn_connectDic"),title:f.getLocal("btn_connectDic"),onLoad:function(){this.getDialog();var c=i.scayt,d=SCAYT.prototype.UILib,g=this.getElement().$,j=this.getElement().getChild(0).$;c.isLicensed()||(d.css(g,{cursor:"not-allowed"}),d.css(j,{cursor:"not-allowed"}))},onClick:function(){var c=this.getDialog(),d=i.scayt,g=b,j=c.getContentElement("dictionaries","dictionaryName").getValue();d.isLicensed()&&d.restoreUserDictionary(j,function(k){k.dialog=c;k.error||g.toggleDictionaryState.call(c,"dictionaryState");k.command="restore";k.name=j;i.fire("scaytUserDictionaryAction",k)},function(k){k.dialog=c;k.command="restore";k.name=j;i.fire("scaytUserDictionaryActionError",k)})}},{type:"button",id:"disconnectDic",label:f.getLocal("btn_disconnectDic"),title:f.getLocal("btn_disconnectDic"),onClick:function(){var c=this.getDialog(),d=i.scayt,g=b,j=c.getContentElement("dictionaries","dictionaryName"),k=j.getValue();d.isLicensed()&&(d.disconnectFromUserDictionary({}),j.setValue(""),g.toggleDictionaryState.call(c,"initialState"),i.fire("scaytUserDictionaryAction",{dialog:c,command:"disconnect",name:k}))}},{type:"button",id:"removeDic",label:f.getLocal("btn_deleteDic"),title:f.getLocal("btn_deleteDic"),onClick:function(){var c=this.getDialog(),d=i.scayt,g=b,j=c.getContentElement("dictionaries","dictionaryName"),k=j.getValue();d.isLicensed()&&d.removeUserDictionary(k,function(l){j.setValue("");l.error||g.toggleDictionaryState.call(c,"initialState");l.dialog=c;l.command="remove";l.name=k;i.fire("scaytUserDictionaryAction",l)},function(l){l.dialog=c;l.command="remove";l.name=k;i.fire("scaytUserDictionaryActionError",l)})}},{type:"button",id:"renameDic",label:f.getLocal("btn_renameDic"),title:f.getLocal("btn_renameDic"),onClick:function(){var c=this.getDialog(),d=i.scayt,g=c.getContentElement("dictionaries","dictionaryName").getValue();d.isLicensed()&&d.renameUserDictionary(g,function(j){j.dialog=c;j.command="rename";j.name=g;i.fire("scaytUserDictionaryAction",j)},function(j){j.dialog=c;j.command="rename";j.name=g;i.fire("scaytUserDictionaryActionError",j)})}},{type:"button",id:"editDic",label:f.getLocal("btn_goToDic"),title:f.getLocal("btn_goToDic"),onLoad:function(){this.getDialog()},onClick:function(){var c=this.getDialog(),d=c.getContentElement("dictionaries","addWordField");b.clearWordList.call(c);d.setValue("");b.getUserDictionary.call(c);b.toggleDictionaryState.call(c,"wordsState")}}]},{type:"hbox",id:"dicInfo",align:"left",children:[{type:"html",id:"dicInfoHtml",html:'\x3cdiv id\x3d"dic_info_editor1" style\x3d"margin:5px auto; width:95%;white-space:normal;"\x3e'+(i.scayt.isLicensed&&i.scayt.isLicensed()?f.getLocal("text_descriptionDicForPaid"):f.getLocal("text_descriptionDicForFree"))+"\x3c/div\x3e"}]},{id:"addWordAction",type:"hbox",style:"width: 100%; margin-bottom: 0;",widths:["40%","60%"],children:[{id:"addWord",type:"vbox",style:"min-width: 150px;",children:[{type:"text",id:"addWordField",label:"Add word",maxLength:"64"}]},{id:"addWordButtons",type:"vbox",style:"margin-top: 20px;",children:[{type:"hbox",id:"addWordButton",align:"left",children:[{type:"button",id:"addWord",label:f.getLocal("btn_addWord"),title:f.getLocal("btn_addWord"),onClick:function(){var c=this.getDialog(),j=i.scayt,k=c.getContentElement("dictionaries","itemList"),m=c.getContentElement("dictionaries","addWordField"),n=m.getValue(),o=j.getOption("wordBoundaryRegex"),l=this;n&&(-1!==n.search(o)?i.fire("scaytUserDictionaryAction",{dialog:c,command:"wordWithBannedSymbols",name:n,error:!0}):k.inChildren(n)?(m.setValue(""),i.fire("scaytUserDictionaryAction",{dialog:c,command:"wordAlreadyAdded",name:n})):(this.disable(),j.addWordToUserDictionary(n,function(d){d.error||(m.setValue(""),k.addChild(n,!0));d.dialog=c;d.command="addWord";d.name=n;l.enable();i.fire("scaytUserDictionaryAction",d)},function(d){d.dialog=c;d.command="addWord";d.name=n;l.enable();i.fire("scaytUserDictionaryActionError",d)})))}},{type:"button",id:"backToDic",label:f.getLocal("btn_dictionaryPreferences"),title:f.getLocal("btn_dictionaryPreferences"),align:"right",onClick:function(){var c=this.getDialog(),d=i.scayt;null!=d.getUserDictionaryName()&&""!=d.getUserDictionaryName()?b.toggleDictionaryState.call(c,"dictionaryState"):b.toggleDictionaryState.call(c,"initialState")}}]}]}]},{id:"wordsHolder",type:"hbox",style:"width: 100%; height: 170px; margin-bottom: 0;",children:[{type:"scaytItemList",id:"itemList",align:"left",style:"width: 100%; height: 170px; overflow: auto",onClick:function(j){j=j.data.$;var k=i.scayt,l=SCAYT.prototype.UILib,o=l.parent(j.target)[0],p=l.attr(o,"data-cke-scayt-ud-word"),q=this.getDialog(),n=q.getContentElement("dictionaries","itemList"),c=this;l.hasClass(j.target,"cke_scaytItemList_remove")&&!this.isBlocked()&&(this.block(),k.deleteWordFromUserDictionary(p,function(d){d.error||n.removeChild(o,p);c.unblock();d.dialog=q;d.command="deleteWord";d.name=p;i.fire("scaytUserDictionaryAction",d)},function(d){c.unblock();d.dialog=q;d.command="deleteWord";d.name=p;i.fire("scaytUserDictionaryActionError",d)}))}}]}]}]},{id:"about",label:f.getLocal("tab_about"),elements:[{type:"html",id:"about",style:"margin: 5px 5px;",html:'\x3cdiv\x3e\x3cdiv id\x3d"scayt_about_"\x3e'+a+"\x3c/div\x3e\x3c/div\x3e"}]}];i.on("scaytUserDictionaryAction",function(g){var j=SCAYT.prototype.UILib,n=g.data.dialog,k=n.getContentElement("dictionaries","dictionaryNote").getElement(),l=g.editor.scayt,m;void 0===g.data.error?(m=l.getLocal("message_success_"+g.data.command+"Dic"),m=m.replace("%s",g.data.name),k.setText(m),j.css(k.$,{color:"blue"})):(""===g.data.name?k.setText(l.getLocal("message_info_emptyDic")):(m=l.getLocal("message_error_"+g.data.command+"Dic"),m=m.replace("%s",g.data.name),k.setText(m)),j.css(k.$,{color:"red"}),null!=l.getUserDictionaryName()&&""!=l.getUserDictionaryName()?n.getContentElement("dictionaries","dictionaryName").setValue(l.getUserDictionaryName()):n.getContentElement("dictionaries","dictionaryName").setValue(""))});i.on("scaytUserDictionaryActionError",function(j){var k=SCAYT.prototype.UILib,o=j.data.dialog,n=o.getContentElement("dictionaries","dictionaryNote").getElement(),m=j.editor.scayt,l;""===j.data.name?n.setText(m.getLocal("message_info_emptyDic")):(l=m.getLocal("message_error_"+j.data.command+"Dic"),l=l.replace("%s",j.data.name),n.setText(l));k.css(n.$,{color:"red"});null!=m.getUserDictionaryName()&&""!=m.getUserDictionaryName()?o.getContentElement("dictionaries","dictionaryName").setValue(m.getUserDictionaryName()):o.getContentElement("dictionaries","dictionaryName").setValue("")});var b={title:f.getLocal("text_title"),resizable:CKEDITOR.DIALOG_RESIZE_BOTH,minWidth:"moono-lisa"==(CKEDITOR.skinName||i.config.skin)?450:340,minHeight:300,onLoad:function(){if(0!=i.config.scayt_uiTabs[1]){var c=b,d=c.getLangBoxes.call(this);this.getContentElement("dictionaries","addWordField");d.getParent().setStyle("white-space","normal");c.renderLangList(d);this.definition.minWidth=this.getSize().width;this.resize(this.definition.minWidth,this.definition.minHeight)}},onCancel:function(){e.reset()},onHide:function(){i.unlockSelection()},onShow:function(){i.fire("scaytDialogShown",this);if(0!=i.config.scayt_uiTabs[2]){var c=this.getContentElement("dictionaries","addWordField");b.clearWordList.call(this);c.setValue("");b.getUserDictionary.call(this);b.toggleDictionaryState.call(this,"wordsState")}},onOk:function(){var c=b,d=i.scayt;this.getContentElement("options","scaytOptions");c=c.getChangedOption.call(this);d.commitOption({changedOptions:c})},toggleDictionaryButtons:function(d){var g=this.getContentElement("dictionaries","existDic").getElement().getParent(),j=this.getContentElement("dictionaries","notExistDic").getElement().getParent();d?(g.show(),j.hide()):(g.hide(),j.show())},getChangedOption:function(){var c={};if(1==i.config.scayt_uiTabs[0]){for(var g=this.getContentElement("options","scaytOptions").getChild(),j=0;j<g.length;j++){g[j].isChanged()&&(c[g[j].id]=g[j].getValue())}}e.isChanged()&&(c[e.id]=i.config.scayt_sLang=e.currentLang=e.newLang);return c},buildRadioInputs:function(j,l,r){var p=new CKEDITOR.dom.element("div"),q="scaytLang_"+i.name+"_"+l,o=CKEDITOR.dom.element.createFromHtml('\x3cinput id\x3d"'+q+'" type\x3d"radio"  value\x3d"'+l+'" name\x3d"scayt_lang" /\x3e'),n=new CKEDITOR.dom.element("label"),c=i.scayt;p.setStyles({"white-space":"normal",position:"relative","padding-bottom":"2px"});o.on("click",function(d){e.newLang=d.sender.getValue()});n.appendText(j);n.setAttribute("for",q);r&&i.config.grayt_autoStartup&&n.setStyles({color:"#02b620"});p.append(o);p.append(n);l===c.getLang()&&(o.setAttribute("checked",!0),o.setAttribute("defaultChecked","defaultChecked"));return p},renderLangList:function(r){var s=r.find("#left-col-"+i.name).getItem(0);r=r.find("#right-col-"+i.name).getItem(0);var n=f.getScaytLangList(),p=f.getGraytLangList(),q={},o=[],j=0,c=!1,d;for(d in n.ltr){q[d]=n.ltr[d]}for(d in n.rtl){q[d]=n.rtl[d]}for(d in q){o.push([d,q[d]])}o.sort(function(k,g){var l=0;k[1]>g[1]?l=1:k[1]<g[1]&&(l=-1);return l});q={};for(c=0;c<o.length;c++){q[o[c][0]]=o[c][1]}o=Math.round(o.length/2);for(d in q){j++,c=d in p.ltr||d in p.rtl,this.buildRadioInputs(q[d],d,c).appendTo(j<=o?s:r)}},getLangBoxes:function(){return this.getContentElement("langs","langBox").getElement()},toggleDictionaryState:function(x){var y=this.getContentElement("dictionaries","dictionaryName").getElement().getParent(),w=this.getContentElement("dictionaries","udButtonsHolder").getElement().getParent(),v=this.getContentElement("dictionaries","createDic").getElement().getParent(),u=this.getContentElement("dictionaries","restoreDic").getElement().getParent(),t=this.getContentElement("dictionaries","disconnectDic").getElement().getParent(),s=this.getContentElement("dictionaries","removeDic").getElement().getParent(),q=this.getContentElement("dictionaries","renameDic").getElement().getParent(),r=this.getContentElement("dictionaries","dicInfo").getElement().getParent(),j=this.getContentElement("dictionaries","addWordAction").getElement().getParent(),o=this.getContentElement("dictionaries","wordsHolder").getElement().getParent();switch(x){case"initialState":y.show();w.show();v.show();u.show();t.hide();s.hide();q.hide();r.show();j.hide();o.hide();break;case"wordsState":y.hide();w.hide();r.hide();j.show();o.show();break;case"dictionaryState":y.show(),w.show(),v.hide(),u.hide(),t.show(),s.show(),q.show(),r.show(),j.hide(),o.hide()}},clearWordList:function(){this.getContentElement("dictionaries","itemList").removeAllChild()},getUserDictionary:function(){var c=this;i.scayt.getUserDictionary("",function(d){d.error||b.renderItemList.call(c,d.wordlist)})},renderItemList:function(d){for(var g=this.getContentElement("dictionaries","itemList"),j=0;j<d.length;j++){g.addChild(d[j])}},contents:function(g,j){var m=[],l=j.config.scayt_uiTabs;if(l){for(var k in l){1==l[k]&&m.push(g[k])}m.push(g[g.length-1])}else{return g}return m}(a,i)};return b});CKEDITOR.tools.extend(CKEDITOR.ui.dialog,{scaytItemList:function(f,b,a){if(arguments.length){var e=this;f.on("load",function(){e.getElement().on("click",function(d){})});CKEDITOR.ui.dialog.uiElement.call(this,f,b,a,"",null,null,function(){var d=['\x3cp class\x3d"cke_dialog_ui_',b.type,'"'];b.style&&d.push('style\x3d"'+b.style+'" ');d.push("\x3e");d.push("\x3c/p\x3e");return d.join("")})}}});CKEDITOR.ui.dialog.scaytItemList.prototype=CKEDITOR.tools.extend(new CKEDITOR.ui.dialog.uiElement,{children:[],blocked:!1,addChild:function(g,e){var a=new CKEDITOR.dom.element("p"),f=new CKEDITOR.dom.element("a"),b=this.getElement().getChildren().getItem(0);this.children.push(g);a.addClass("cke_scaytItemList-child");a.setAttribute("data-cke-scayt-ud-word",g);a.appendText(g);f.addClass("cke_scaytItemList_remove");f.addClass("cke_dialog_close_button");f.setAttribute("href","javascript:void(0)");a.append(f);b.append(a,e?!0:!1)},inChildren:function(a){return SCAYT.prototype.Utils.inArray(this.children,a)},removeChild:function(b,a){this.children.splice(SCAYT.prototype.Utils.indexOf(this.children,a),1);this.getElement().getChildren().getItem(0).$.removeChild(b)},removeAllChild:function(){this.children=[];this.getElement().getChildren().getItem(0).setHtml("")},block:function(){this.blocked=!0},unblock:function(){this.blocked=!1},isBlocked:function(){return this.blocked}});(function(){commonBuilder={build:function(e,b,a){return new CKEDITOR.ui.dialog[b.type](e,b,a)}};CKEDITOR.dialog.addUIElement("scaytItemList",commonBuilder)})();