CKEDITOR.dialog.add("button",function(a){function c(e){var d=this.getValue();d?(e.attributes[this.id]=d,"name"==this.id&&(e.attributes["data-cke-saved-name"]=d)):(delete e.attributes[this.id],"name"==this.id&&delete e.attributes["data-cke-saved-name"])}return{title:a.lang.forms.button.title,minWidth:350,minHeight:150,onShow:function(){delete this.button;var b=this.getParentEditor().getSelection().getSelectedElement();b&&b.is("input")&&b.getAttribute("type") in {button:1,reset:1,submit:1}&&(this.button=b,this.setupContent(b))},onOk:function(){var g=this.getParentEditor(),f=this.button,i=!f,j=f?CKEDITOR.htmlParser.fragment.fromHtml(f.getOuterHtml()).children[0]:new CKEDITOR.htmlParser.element("input");this.commitContent(j);var h=new CKEDITOR.htmlParser.basicWriter;j.writeHtml(h);j=CKEDITOR.dom.element.createFromHtml(h.getHtml(),g.document);i?g.insertElement(j):(j.replace(f),g.getSelection().selectElement(j))},contents:[{id:"info",label:a.lang.forms.button.title,title:a.lang.forms.button.title,elements:[{id:"name",type:"text",bidi:!0,label:a.lang.common.name,"default":"",setup:function(b){this.setValue(b.data("cke-saved-name")||b.getAttribute("name")||"")},commit:c},{id:"value",type:"text",label:a.lang.forms.button.text,accessKey:"V","default":"",setup:function(b){this.setValue(b.getAttribute("value")||"")},commit:c},{id:"type",type:"select",label:a.lang.forms.button.type,"default":"button",accessKey:"T",items:[[a.lang.forms.button.typeBtn,"button"],[a.lang.forms.button.typeSbm,"submit"],[a.lang.forms.button.typeRst,"reset"]],setup:function(b){this.setValue(b.getAttribute("type")||"")},commit:c}]}]}});