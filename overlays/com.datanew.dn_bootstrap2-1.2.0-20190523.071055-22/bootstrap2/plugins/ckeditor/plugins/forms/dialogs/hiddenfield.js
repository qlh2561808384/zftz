CKEDITOR.dialog.add("hiddenfield",function(a){return{title:a.lang.forms.hidden.title,hiddenField:null,minWidth:350,minHeight:110,onShow:function(){delete this.hiddenField;var e=this.getParentEditor(),d=e.getSelection(),f=d.getSelectedElement();f&&f.data("cke-real-element-type")&&"hiddenfield"==f.data("cke-real-element-type")&&(this.hiddenField=f,f=e.restoreRealElement(this.hiddenField),this.setupContent(f),d.selectElement(this.hiddenField))},onOk:function(){var d=this.getValueOf("info","_cke_saved_name"),c=this.getParentEditor(),d=CKEDITOR.env.ie&&8>CKEDITOR.document.$.documentMode?c.document.createElement('\x3cinput name\x3d"'+CKEDITOR.tools.htmlEncode(d)+'"\x3e'):c.document.createElement("input");d.setAttribute("type","hidden");this.commitContent(d);d=c.createFakeElement(d,"cke_hidden","hiddenfield");this.hiddenField?(d.replace(this.hiddenField),c.getSelection().selectElement(d)):c.insertElement(d);return !0},contents:[{id:"info",label:a.lang.forms.hidden.title,title:a.lang.forms.hidden.title,elements:[{id:"_cke_saved_name",type:"text",label:a.lang.forms.hidden.name,"default":"",accessKey:"N",setup:function(b){this.setValue(b.data("cke-saved-name")||b.getAttribute("name")||"")},commit:function(b){this.getValue()?b.setAttribute("name",this.getValue()):b.removeAttribute("name")}},{id:"value",type:"text",label:a.lang.forms.hidden.value,"default":"",accessKey:"V",setup:function(b){this.setValue(b.getAttribute("value")||"")},commit:function(b){this.getValue()?b.setAttribute("value",this.getValue()):b.removeAttribute("value")}}]}]}});