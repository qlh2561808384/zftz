<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/5/21
  Time: 9:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>项目概算</title>
    <script src="bootstrap2/js/jquery.js"></script>
    <script src="bootstrap2/js/bootstrap.datanew.js"></script>
    <script src="js/file.js"></script>
    <script src="pro/js/uploadFile.js"></script>
    <script src="js/common.js"></script>
    <script src="js/proj.js"></script>
    <script src="pro/js/deleteFile.js"></script>
    <link rel="stylesheet" href="css/common.css">
    <script>
        var dopenindex = 0;
        var flag;
        var zxywid;
        var xmid;//项目id
        var xmgsyjid;
        var fkyj;
        var xmlx = [];
        var jsdw = [];
        var zgbm = [];
        var jldw = [];
        var ZT = [{id:-1,text:"未提交"},{id:1,text:"审核中"},{id:0,text:"已审核完成"}];
        $(function(){
            $.dajax({
                url: "xmqqch/selectJldw.do",
                data: {},
                cache: true,
                type: "POST",
                dataType: "json",
                success: function (data) {
                    jldw = data;
                }
            });
            $.dajax({
                url: "pro/selectXmlx.do",
                data: {},
                cache: true,
                type: "POST",
                dataType: "json",
                success: function (data) {
                    xmlx = data;
                }
            });
            $.dajax({
                url: "pro/selectNoJsdw.do",
                data: {},
                cache: true,
                type: "POST",
                dataType: "json",
                success: function (data) {
                    jsdw = data;
                }
            });
            $.dajax({
                url: "pro/selectZgbm.do",
                data: {},
                cache: true,
                type: "POST",
                dataType: "json",
                success: function (data) {
                    zgbm = data;
                }
            });
            var json = [
                {
                    "plug": [
                        {
                            "plug": [
                                {
                                    "plug": [
                                        {
                                            "dtype":"html",
                                            "dragHtml":"<h4 class='text-center'><b>项目概算意见征询</b></h4>"
                                        },
                                        {
                                            "dtype": "dtable",
                                            "id":"tab",
                                            "height": $(window).height() - 80,
                                            "columns": [
                                                {field:"checkType", checkbox:true},
                                                {field:"DD", title:"序号", width:50, align:"center", formatter:function(value,row,index){return index+1;}},
                                                {field:"YJID", title:"项目概算意见征询id", width:100, align:"center",visible:false},
                                                {field:"XMBH", title:"项目编号", width:100, align:"center"},
                                                {field:"XMMC", title:"项目名称", width:100, align:"center"},
                                                {field:"ZGBM", title:"主管部门", width:100, align:"center",formatter:treeFormatterZgbm},
                                                {field:"XMLX", title:"项目类型", width:100, align:"center",formatter:treeFormatterXmxmlx},
                                                {field:"JSDW", title:"建设单位", width:100, align:"center",formatter:treeFormatterJsdw},
                                                {field:"YDMJ", title:"用地面积（亩）", width:100, align:"center"},
                                                {field:"TZXE", title:"投资限额", width:100, align:"center"},
                                                {field:"XMZTZ", title:"项目总投资(万元)", width:100, align:"center"},
                                                {field:"FKYJ", title:"反馈意见", width:100, align:"center",visible:false},
                                                {field:"ZJLYDJID", title:"资金来源登记id", width:100, align:"center",visible:false},
                                                {field:"QQCHID", title:"前期策划id", width:100, align:"center",visible:false},
                                                {field:"ZT", title:"", width:100, align:"center",visible:false},
                                                {field:"LCHJ", title:"状态", width:100, align:"center",formatter:treeFormatterZT,visible:false},
                                                {field: "ZT1", title: "状态", width: 100, align: "center",
                                                    formatter : function(value, row, index) {
                                                        if(value=='退回'){
                                                            return '<a href="javascript:void(0)" onclick="selThyj('+row.YJID+')">退回</a>';
                                                        }else{
                                                            return value;
                                                        }

                                                    }
                                                }
                                            ],
                                            searchbar: {
                                                rownum: 2,//搜索栏表单列数  最大支持3
                                                labelwidth:150 ,
                                                inputs: [//搜索栏表单参数
                                                    {
                                                        title : '项目名称:',
                                                        name : 'xmmc',
                                                        // url:getRootPath()+"/yhglggryqz/getsxList.do" ,
                                                        type:'textBox'
                                                        // multiple:false

                                                    }, {
                                                        title : '状态:',
                                                        name : 'zt',
                                                        // url:getRootPath()+"/yhglggryqz/getsxList.do" ,
                                                        type:'comboBox',
                                                        // multiple:false
                                                        defaultValue:-1,
                                                        localdata:ZT,
                                                        onChange:function (newValue,oldValue) {
                                                            if (1 == newValue || 0 == newValue) {
                                                                disableButton(['TJ', 'SC']);
                                                            } else {
                                                                showButton(['TJ', 'SC']);
                                                            }
                                                        }
                                                    }

                                                ]
                                            },
                                            "url": "pro/getXmGSYJData.do",
                                            "toolbar": [
                                                {"name":"新增", "classes":"btn bootstrap-table-add", "type":"button", "onclick":"addRow()"},
                                                {"name":"删除", "classes":"btn bootstrap-table-delete","id":"SC", "type":"button", "onclick":"delRow()"},
                                                {"name":"查看/修改", "classes":"btn bootstrap-table-edit", "type":"button", "onclick":"editRow()"},
                                                {"name":"提交", "classes":"btn bootstrap-table-submit","id":"TJ", "type":"button", "onclick":"submit()"}
                                            ],
                                            "clickToSelect": true
                                        }
                                    ],
                                    "colspan": "12",
                                    "dtype": "column"
                                }
                    ],
                            "dtype": "row"
                        }
                    ],
                    "dtype": "body"
                }
            ];
            $.initPage(json);
        });

        var dopenJson = [
            {
                "plug": [
                    {
                        "plug": [
                            {
                                "colspan": "12",
                                "dtype": "column",
                                "plug":[
                                    {
                                        "dtype":"html",
                                        "dragHtml":'<ul class="nav nav-tabs" role="tablist">' +
                                            '    <li role="presentation" class="active"><a href="#infoTab1" aria-controls="infoTab1" role="tab" data-toggle="tab">项目概算意见征询</a></li>' +
                                            '    <li role="presentation" class="info"><a href="#infoTab2" aria-controls="infoTab2" role="tab" data-toggle="tab" onclick="getZjlydj()">项目资金来源登记</a></li>' +
                                            '    <li role="presentation"><a href="#infoTab3" aria-controls="infoTab3" role="tab" data-toggle="tab" onclick="getXmqqch()">项目前期策划信息</a></li>' +
                                            '</ul>'
                                    },
                                    {
                                        "dtype":"div",
                                        "classes":"tab-content",
                                        "plug":[
                                            {
                                                "dtype":"div",
                                                "classes":"tab-pane active",
                                                "attrs":{role:"tabpanel", id:"infoTab1"},
                                                "plug":[
                                                    {
                                                        "dtype": "html",
                                                        "dragHtml": "<h4 class='text-center'><b>项目概算意见征询</b></h4>"
                                                    },
                                                    {
                                                        "dtype":"html",
                                                        "dragHtml":'<div class="toolbar layout_toolbar clearfix">' +
                                                            '<button class="bootstrap-table-save" id="saveid" type="button" onclick="save()">保存</button>' +
                                                            '<button class="bootstrap-table-submit" type="button" onclick="submitInside()">提交</button>' +
                                                            '<button class="bootstrap-table-clear" type="button" onclick="openZBK()">标准库</button>' +
                                                            '</div>'
                                                    },
                                                    {
                                                        "dtype":"dform",
                                                        "classes":"tableForm",
                                                        "id": "table",
                                                        "rownum":2,
                                                        "labelwidth":"200px",
                                                        "labelAlign":"center",
                                                        "inputs":[
                                                            {title: "项目概算唯一标示id", name:"XMGSYJID", type:"hidden"},
                                                            {title: "流程环节", name:"LCHJ", type:"hidden"},
                                                            {title: "项目编码", name:"MC", type:"hidden"},
                                                            // {title: "项目名称", name:"XMMC", type:"textBox"},
                                                            {
                                                                title: '项目名称',//表单lable显示名
                                                                type:"textBox",
                                                                name:"XMMC",
                                                                id: "mmm",
                                                               /* isOpen: true, //节点是否全部展开，默认为不展开
                                                                ISLEAF: true,
                                                                rootElement: true,  //是否添加根节点“全部”*/
                                                                onLoaded: function($el){
                                                                    openProj($el,function(node){
                                                                        xmid = node.id;
                                                                        $("#table").dform("setValueByName","MC",node.id);
                                                                        $("#table").dform("setValueByName","XMMC",node.name);
                                                                        loadXMData();
                                                                    },"Xmgsyjzx");
                                                                }
                                                            },
                                                            /*{
                                                                title: '项目名称',//表单lable显示名
                                                                type: 'dsearchtree',//表单类型：目前支持 select/text
                                                                placeholder: '请选择项目名称',
                                                                searchOption:true,
                                                                id:'xmmc',
                                                                name:'MC',
                                                                checkType:'radio',
                                                                url : 'pro/getXmmcTree.do',
                                                                onAckCallback:function(nodes){
                                                                    if(nodes.length>0){
                                                                        $('#xmmc').searchTree("setValue",nodes[0].id);
                                                                    }
                                                                },
                                                                onChange:function (newValue,oldValue,) {
                                                                    xmid = newValue;
                                                                    loadXMData();
                                                                }
                                                            },*/
                                                            {
                                                                title: '项目类型',//表单lable显示名
                                                                type: 'dsearchtree',//表单类型：目前支持 select/text
                                                                // placeholder: '请选择项目类型',
                                                                searchOption:true,
                                                                id:'xmlxxm',
                                                                name:'XMLX',
                                                                checkType:'radio',
                                                                url : 'pro/getXMLXTree.do',
                                                                disabled:"true",
                                                                setReadonly:true,
                                                                onAckCallback:function(nodes){
                                                                    if(nodes.length>0){
                                                                        $('#xmlxxm').searchTree("setValue",nodes[0].id);
                                                                    }
                                                                }
                                                            },
                                                            {
                                                                title: '建设单位',//表单lable显示名
                                                                type: 'dsearchtree',//表单类型：目前支持 select/text
                                                                // placeholder: '请选择建设单位',
                                                                searchOption:true,
                                                                id:'depart',
                                                                name:'JSDW',
                                                                checkType:'radio',
                                                                url : 'pro/getJSDWNoTree.do',
                                                                disabled:"true",
                                                                setReadonly:true,
                                                                onAckCallback:function(nodes){
                                                                    if(nodes.length>0){
                                                                        $('#depart').searchTree("setValue",nodes[0].id);
                                                                    }
                                                                }
                                                            },
                                                            {
                                                                title: '项目主管部门',//表单lable显示名
                                                                type: 'dsearchtree',//表单类型：目前支持 select/text
                                                                // placeholder: '请选择主管部门',
                                                                searchOption:true,
                                                                id:'zgdw',
                                                                name:'ZGBM',
                                                                checkType:'radio',
                                                                url : 'pro/getZGBMNOTree.do',
                                                                disabled:"true",
                                                                setReadonly:true,
                                                                onAckCallback:function(nodes){
                                                                    if(nodes.length>0){

                                                                        $('#zgdw').searchTree("setValue",nodes[0].id);
                                                                    }
                                                                }
                                                            },
                                                            {title: "项目规划选址", name:"XMGHXZ", type:"textBox",colspan:2,disabled:"true",setReadonly:true},
                                                            {
                                                                title: "<br/>建设内容和规模",
                                                                name: "JSNR",
                                                                id: "jsnrid",
                                                                type: "textBox",
                                                                multiline: true,
                                                                colspan: 2
                                                            },
                                                            {title: "<br/>反馈意见", name:"FKYJ", type:"textBox", multiline:true, colspan:2,disabled:"true",setReadonly:true},
                                                        ]
                                                    },
                                                    {
                                                        "dtype":"html",
                                                        "dragHtml":'<div style="padding:20px 0 10px 15px;"><b>附件列表：</b><button id="butid" type="button" class="bootstrap-table-upload" onclick="uploadFile(2,\'#xmgszxd \')">上传附件</button>' +
                                                            '&nbsp;<button type="button" class="bootstrap-table-delete" id="deleteFileId" onclick="deleteFile(\'#xmgszxd\')">删除附件</button>' +
                                                            '</div>'
                                                    },
                                                    {
                                                        "dtype":"div",
                                                        "classes":"clearfix",
                                                        "plug":[
                                                            {
                                                                "dtype":"div",
                                                                "classes":"pull-left",
                                                                "attrs":{"style":"width:100%;"},
                                                                "plug":[
                                                                    {
                                                                        "dtype": "dtable",
                                                                        "height": 250,
                                                                        "id": "xmgszxd",
                                                                        "url": "",
                                                                        "columns": [
                                                                            {radio: true, width: 20},
                                                                            {field:"XH", title:"序号", width:50, align:"center", formatter:function(value,row,index){return index+1;}},
                                                                            {field:"FILEID", title:"附件id", width:200, align:"center",visible:false},
                                                                            {field:"FILENAME", title:"附件名", width:200, align:"center"},
                                                                            {
                                                                                field: "FILEDL",
                                                                                title: "附件大类",
                                                                                width: 100,
                                                                                align: "center",
                                                                                type: 'comboBox',
                                                                                selected: 1,
                                                                                localdata: [{id: 2, text: "概算意见征询"}],
                                                                                formatter: function (value, row, index) {
                                                                                    return "概算意见征询";
                                                                                }
                                                                            },

                                                                            {
                                                                                field: "FILEXL",
                                                                                title: "附件小类",
                                                                                width: 120,
                                                                                align: "center",
                                                                                /*editor: {
                                                                                    type: "comboBox",
                                                                                    // localdata: fjlx[0]
                                                                                }*/
                                                                            },
                                                                            {
                                                                                field: "FILESIZE",
                                                                                title: "大小",
                                                                                width: 80,
                                                                                align: "center",
                                                                                formatter: function (value, row, index) {
                                                                                    return formatSize(value);
                                                                                }
                                                                            },
                                                                            {field:"BZ", title:"备注", width:80, align:"center"},
                                                                            {
                                                                                field : '',
                                                                                title : '操作 ',
                                                                                width : 100,
                                                                                align : 'center',
                                                                                formatter : function(value, row, index) {
                                                                                    return  '<a href=file/downloadByid.do?id='+row.FILEID+'>下载</a>';
                                                                                }
                                                                            }
                                                                        ]
                                                                    }
                                                                ]
                                                            },
                                                          /*  {
                                                                "dtype":"html",
                                                                "dragHtml":'<div class="pull-right" style="width:28%;"><img src="bootstrap2/images/image.png" width="100%" /></div>'
                                                            }*/
                                                        ]
                                                    }
                                                ]
                                            },
                                            {
                                                "dtype":"div",
                                                "classes":"tab-pane info",
                                                "attrs":{role:"tabpanel", id:"infoTab2"},
                                                "plug":[
                                                    {
                                                        "dtype": "html",
                                                        "dragHtml": "<h4 class='text-center'><b>项目资金来源登记</b></h4>"
                                                    },
                                                    {
                                                        "dtype":"html",
                                                        "dragHtml":'<div class="toolbar layout_toolbar clearfix">' +
                                                            '<span class="pull-right">单位：万元</span>'+
                                                            '</div>'
                                                    },
                                                    {
                                                        "dtype":"html"
                                                    },{
                                                        "dtype":"dform",
                                                        "classes":"tableForm",
                                                        "id": "zjlydj",
                                                        "rownum":3,
                                                        "labelwidth":"200px",
                                                        "labelAlign":"center",
                                                        "inputs":[
                                                            {title: "项目名称", name:"XMMC", type:"textBox",disabled:"true",setReadonly:true},
                                                            {
                                                                title: '建设单位',//表单lable显示名
                                                                type: 'dsearchtree',//表单类型：目前支持 select/text
                                                                // placeholder: '请选择建设单位',
                                                                searchOption:true,
                                                                id:'dp',
                                                                name:'JSDW',
                                                                checkType:'radio',
                                                                url : 'pro/getJSDWNoTree.do',
                                                                disabled:"true",
                                                                setReadonly:true,
                                                                onAckCallback:function(nodes){
                                                                    if(nodes.length>0){
                                                                        $('#dp').searchTree("setValue",nodes[0].id);
                                                                    }
                                                                }
                                                            },
                                                            {title: "项目规划地址", name:"XMGHXZ", type:"textBox",disabled:"true",setReadonly:true},
                                                            {title: "项目总投资", name:"XMZTZ", type:"decimal",disabled:"true",setReadonly:true,textAlign:"right"},
                                                            {
                                                                title: '项目类型',//表单lable显示名
                                                                type: 'dsearchtree',//表单类型：目前支持 select/text
                                                                // placeholder: '请选择项目类型',
                                                                searchOption:true,
                                                                id:'xm',
                                                                name:'XMLX',
                                                                checkType:'radio',
                                                                url : 'pro/getXMLXTree.do',
                                                                disabled:"true",
                                                                setReadonly:true,
                                                                onAckCallback:function(nodes){
                                                                    if(nodes.length>0){
                                                                        $('#xm').searchTree("setValue",nodes[0].id);
                                                                    }
                                                                }
                                                            },
                                                            {title: "容积率", name:"RJL", type:"decimal",disabled:"true",setReadonly:true,textAlign:"right"},
                                                            {title: "用地面积(平方米)", name:"YDMJ", type:"decimal",disabled:"true",setReadonly:true,textAlign:"right"},
                                                            {title: "建筑总面积(平方米)", name:"JZZMJ", type:"decimal",disabled:"true",setReadonly:true,textAlign:"right"},
                                                            {title: "其中：地上建筑面积(平方米)", name:"DSJZMJ", type:"decimal",disabled:"true",setReadonly:true,textAlign:"right"},
                                                            {title: "项目里程(公里)", name:"XMLC", type:"decimal",disabled:"true",setReadonly:true,textAlign:"right"},
                                                            {title: "道路宽度(米)", name:"DLKD", type:"decimal",disabled:"true",setReadonly:true,textAlign:"right"},
                                                            {title: " 计量单位(造价标准)", name:"JLDW", type:"textBox",disabled:"true",setReadonly:true},
                                                            {title: "投资限额", name:"TZXE", type:"decimal",disabled:"true",setReadonly:true,textAlign:"right"},
                                                            {title: "建议控制造价标准(元)", name:"JYKZZJBZ", type:"decimal",disabled:"true",setReadonly:true,textAlign:"right"},
                                                            {title: "项目单位造价标准(元)", name:"XMDWZJ", type:"decimal",disabled:"true",setReadonly:true,textAlign:"right"},
                                                            {title: "<br/>建设内容", name:"JSNR", type:"textBox", multiline:true, colspan:3,disabled:"true",setReadonly:true},
                                                            {
                                                                title: "项目总投资",
                                                                name: "",
                                                                type: "html",
                                                                rowspan: 2,
                                                                html: '<div class="customLabel text-center"><label class="control-label">合计</label></div><div class="customInput text-center"><input type="textBox" name="ZTZHJ" disabled="true" style="text-align: right"></div>'
                                                            },
                                                            {
                                                                title: '<div class="customLabel"><label class="control-label">建筑安装投资</label></div><div class="customInput"><input type="textBox" name="ZTZJZAZTZ" disabled="true" style="text-align: right"></div>',
                                                                name: "",
                                                                type: "html",
                                                                rowspan: 2,
                                                                html: '<div class="customLabel"><label class="control-label">设备投资</label></div><div class="customInput"><input type="textBox" name="ZTZSBTZ" disabled="true" style="text-align: right"></div>'
                                                            },
                                                            {
                                                                title: '<div class="customLabel"><label class="control-label">待摊投资</label></div><div class="customInput"><input type="textBox" name="ZTZDTTZ" disabled="true" style="text-align: right"></div>',
                                                                name: "",
                                                                type: "html",
                                                                rowspan: 2,
                                                                html: '<div class="customLabel"><label class="control-label">其他投资</label></div><div class="customInput" ><input type="textBox" name="ZTZQTTZ" disabled="true" style="text-align: right"></div>'
                                                            },

                                                            {
                                                                title: "项目资金来源",
                                                                name: "",
                                                                type: "html",
                                                                rowspan: 2,
                                                                html: '<div class="customLabel text-center"><label class="control-label">合计</label></div><div class="customInput text-center"><input type="textBox" name="XMZJLYHJ" disabled="true" style="text-align: right"></div>'
                                                            },
                                                            {
                                                                title: '<div class="customLabel"><label class="control-label">财政性资金</label></div><div class="customInput"><input type="textBox" name="XMZJLYCZXZJ" disabled="true" style="text-align: right"></div>',
                                                                name: "",
                                                                type: "html",
                                                                rowspan: 2,
                                                                html: '<div class="customLabel"><label class="control-label">资源平衡</label></div><div class="customInput"><input type="textBox" name="XMZJLYZYPH" disabled="true" style="text-align: right"></div>'
                                                            },
                                                            {
                                                                title: '<div class="customLabel"><label class="control-label">其他</label></div><div class="customInput"><input type="textBox" name="XMZJLYQT" disabled="true" style="text-align: right"></div>',
                                                                name: "",
                                                                type: "html",
                                                                rowspan: 2,
                                                                html: '<div class="customLabel"><label class="control-label">--</label></div><div class="customInput text-center">--</div>'
                                                            },
                                                        ]
                                                    },/*{
                                                        "dtype":"html",
                                                        "dragHtml":'<div style="padding:20px 0 10px 15px;"><b>附件列表：</b><button type="button" class="bootstrap-table-upload" onclick="uploadFile(13,\'#zjlydj \')">上传附件</button></div>'
                                                    },*/
                                                    {
                                                        "dtype":"div",
                                                        "classes":"clearfix",
                                                        "plug":[
                                                            {
                                                                "dtype":"div",
                                                                "classes":"pull-left",
                                                                "attrs":{"style":"width:100%;"},
                                                                "plug":[
                                                                    {
                                                                        "dtype": "dtable",
                                                                        "height": 250,
                                                                        "id": "zjlydj1",
                                                                        "url": "",
                                                                        "columns": [
                                                                            {field:"XH", title:"序号", width:50, align:"center", formatter:function(value,row,index){return index+1;}},
                                                                            {field:"FILEID", title:"附件id", width:200, align:"center",visible:false},
                                                                            {field:"FILENAME", title:"附件名", width:200, align:"center"},
                                                                            {
                                                                                field: "FILEDL",
                                                                                title: "附件大类",
                                                                                width: 100,
                                                                                align: "center",
                                                                                type: 'comboBox',
                                                                                selected: 1,
                                                                                localdata: [{id: 13, text: "资金来源登记"}],
                                                                                formatter: function (value, row, index) {
                                                                                    return "资金来源登记";
                                                                                }
                                                                            },

                                                                            {
                                                                                field: "FILEXL",
                                                                                title: "附件小类",
                                                                                width: 120,
                                                                                align: "center",
                                                                                /*editor: {
                                                                                    type: "comboBox",
                                                                                    // localdata: fjlx[0]
                                                                                }*/
                                                                            },
                                                                            {
                                                                                field: "FILESIZE",
                                                                                title: "大小",
                                                                                width: 80,
                                                                                align: "center",
                                                                                formatter: function (value, row, index) {
                                                                                    return formatSize(value);
                                                                                }
                                                                            },
                                                                            {field:"BZ", title:"备注", width:80, align:"center"},
                                                                            {
                                                                                field : '',
                                                                                title : '操作 ',
                                                                                width : 100,
                                                                                align : 'center',
                                                                                formatter : function(value, row, index) {
                                                                                    return  '<a href=file/downloadByid.do?id='+row.FILEID+'>下载</a>';
                                                                                }
                                                                            }
                                                                        ]
                                                                    }
                                                                ]
                                                            },
                                                            /*  {
                                                                  "dtype":"html",
                                                                  "dragHtml":'<div class="pull-right" style="width:28%;"><img src="bootstrap2/images/image.png" width="100%" /></div>'
                                                              }*/
                                                        ]
                                                    }
                                                ]
                                            },
                                            {
                                                "dtype":"div",
                                                "classes":"tab-pane",
                                                "attrs":{role:"tabpanel", id:"infoTab3"},
                                                "plug": [
                                                    {
                                                        "dtype": "html",
                                                        "dragHtml": "<h4 class='text-center'><b>项目前期策划信息</b></h4>"
                                                    },
                                                    {
                                                        "dtype":"html",
                                                        "dragHtml":'<div class="toolbar layout_toolbar clearfix">' +
                                                            '<span class="pull-right">单位：万元</span>'+
                                                            '</div>'
                                                    },
                                                    {
                                                        "dtype": "html",
                                                    },
                                                    {
                                                        "dtype": "dform",
                                                        "id": "xmqqch",
                                                        "classes": "tableForm",
                                                        "rownum": 3,
                                                        "labelwidth": "200px",
                                                        "labelAlign": "center",
                                                        "inputs": [
                                                            {title: "项目名称", name: "XMMC", type: "textBox",disabled:"true",setReadonly:true},
                                                            {
                                                                title: '建设单位',//表单lable显示名
                                                                type: 'dsearchtree',//表单类型：目前支持 select/text
                                                                // placeholder: '请选择建设单位',
                                                                searchOption:true,
                                                                id:'dpe',
                                                                name:'JSDW',
                                                                checkType:'radio',
                                                                url : 'pro/getJSDWNoTree.do',
                                                                disabled:"true",
                                                                setReadonly:true,
                                                                onAckCallback:function(nodes){
                                                                    if(nodes.length>0){
                                                                        $('#dpe').searchTree("setValue",nodes[0].id);
                                                                    }
                                                                }
                                                            },
                                                            {
                                                                title: '项目主管部门',//表单lable显示名
                                                                type: 'dsearchtree',//表单类型：目前支持 select/text
                                                                // placeholder: '请选择主管部门',
                                                                searchOption:true,
                                                                id:'zg',
                                                                name:'ZGBM',
                                                                checkType:'radio',
                                                                url : 'pro/getZGBMNOTree.do',
                                                                disabled:"true",
                                                                setReadonly:true,
                                                                onAckCallback:function(nodes){
                                                                    if(nodes.length>0){

                                                                        $('#zg').searchTree("setValue",nodes[0].id);
                                                                    }
                                                                }
                                                            },
                                                            {title: "项目规划选址", name: "XMGHXZ", type: "textBox",disabled:"true",setReadonly:true},
                                                            {
                                                                title: '项目类型',//表单lable显示名
                                                                type: 'dsearchtree',//表单类型：目前支持 select/text
                                                                // placeholder: '请选择项目类型',
                                                                searchOption:true,
                                                                id:'xml',
                                                                name:'XMLX',
                                                                checkType:'radio',
                                                                url : 'pro/getXMLXTree.do',
                                                                disabled:"true",
                                                                setReadonly:true,
                                                                onAckCallback:function(nodes){
                                                                    if(nodes.length>0){
                                                                        $('#xml').searchTree("setValue",nodes[0].id);
                                                                    }
                                                                }
                                                            },
                                                            {title: "容积率", name: "RJL", type: "decimal",disabled:"true",setReadonly:true,textAlign:"right"},
                                                            {title: "用地面积(平方米)", name: "YDMJ", type: "decimal",disabled:"true",setReadonly:true ,textAlign:"right"},
                                                            {title: "建筑总面积（平方米）", name: "JZZMJ", type: "decimal",disabled:"true",setReadonly:true,textAlign:"right"},
                                                            {title: "其中：地上建筑面积(平方米)", name: "DSJZMJ", type: "decimal",disabled:"true",setReadonly:true,textAlign:"right"},
                                                            {title: "项目里程（公里）", name: "XMLC", type: "decimal",disabled:"true",setReadonly:true,textAlign:"right"},
                                                            {title: "道路宽度(米)", name: "DLKD", type: "decimal",disabled:"true",setReadonly:true,textAlign:"right"},
                                                            {title: "联系电话", name: "LXDH", type: "textBox",disabled:"true",setReadonly:true},
                                                            {title: "项目单位造价(元)", name: "XMDWZJ", type: "decimal",disabled:"true",setReadonly:true,textAlign:"right"},
                                                            {title: "同类型造价标准(元)", name: "TLXZJBZ", type: "decimal",disabled:"true",setReadonly:true,textAlign:"right"},
                                                            {title: "联系人", name: "LXR", type: "textBox",disabled:"true",setReadonly:true},
                                                            {title: "文件造价标准(元)", name: "WJZJBZ", type: "decimal",disabled:"true",setReadonly:true,textAlign:"right"},
                                                            {title: "计量单位(造价标准)", name: "JLDW", type: "textBox",disabled:"true",setReadonly:true},
                                                            {title: "建设内容", name: "JSNR", type: "textBox", multiline: true, colspan: 3,disabled:"true",setReadonly:true},
                                                            {
                                                                title: "项目总投资",
                                                                name: "",
                                                                type: "html",
                                                                rowspan: 2,
                                                                html: '<div class="customLabel text-center"><label class="control-label">合计</label></div><div class="customInput text-center"><input type="textBox" name="ZTZHJ1" disabled="true" style="text-align: right" decimalPlaces=2></div>'
                                                            },
                                                            {
                                                                title: '<div class="customLabel"><label class="control-label">建筑安装投资</label></div><div class="customInput"><input type="textBox" name="ZTZJZAZTZ1" disabled="true" style="text-align: right" decimalPlaces=2></div>',
                                                                name: "",
                                                                type: "html",
                                                                rowspan: 2,
                                                                html: '<div class="customLabel"><label class="control-label">设备投资</label></div><div class="customInput"><input type="textBox" name="ZTZSBTZ1" disabled="true" style="text-align: right" decimalPlaces=2></div>'
                                                            },
                                                            {
                                                                title: '<div class="customLabel"><label class="control-label">待摊投资</label></div><div class="customInput"><input type="textBox" name="ZTZDTTZ1" disabled="true" style="text-align: right" decimalPlaces=2></div>',
                                                                name: "",
                                                                type: "html",
                                                                rowspan: 2,
                                                                html: '<div class="customLabel"><label class="control-label">其他投资</label></div><div class="customInput" ><input type="textBox" name="ZTZQTTZ1" disabled="true" style="text-align: right" decimalPlaces=2></div>'
                                                            },

                                                            {
                                                                title: "项目资金来源",
                                                                name: "",
                                                                type: "html",
                                                                rowspan: 2,
                                                                html: '<div class="customLabel text-center"><label class="control-label">合计</label></div><div class="customInput text-center"><input type="textBox" name="XMZJLYHJ1" disabled="true" style="text-align: right" decimalPlaces=2></div>'
                                                            },
                                                            {
                                                                title: '<div class="customLabel"><label class="control-label">财政性资金</label></div><div class="customInput"><input type="textBox" name="XMZJLYCZXZJ1" disabled="true" style="text-align: right" decimalPlaces=2></div>',
                                                                name: "",
                                                                type: "html",
                                                                rowspan: 2,
                                                                html: '<div class="customLabel"><label class="control-label">资源平衡</label></div><div class="customInput"><input type="textBox" name="XMZJLYZYPH1" disabled="true" style="text-align: right" decimalPlaces=2></div>'
                                                            },
                                                            {
                                                                title: '<div class="customLabel"><label class="control-label">其他</label></div><div class="customInput"><input type="textBox" name="XMZJLYQT1" disabled="true" style="text-align: right" decimalPlaces=2></div>',
                                                                name: "",
                                                                type: "html",
                                                                rowspan: 2,
                                                                html: '<div class="customLabel"><label class="control-label">--</label></div><div class="customInput text-center">--</div>'
                                                            },
                                                            {title:"项目评审建议", name:"XMPHJY", type:"textBox", multiline:true, colspan:3,colspan: 2,disabled:"true",setReadonly:true},
                                                         /*   {
                                                                title: "项目评审建议",
                                                                name: "XMPHJY",
                                                                type: "html",
                                                                rowspan: 3,
                                                                colspan: 2
                                                                // html: '<div class="customInput"><input type="textBox" data-multiline="true" data-height="84" disabled="true"></div>'
                                                                ,disabled:"true",setReadonly:true
                                                            },*/
                                                            {title: "投资限额", name: "TZXE", type: "decimal",disabled:"true",setReadonly:true,textAlign:"right"},
                                                            {title: "建议控制造价标准(元)", name: "JYKZZJBZ", type: "decimal",disabled:"true",setReadonly:true,textAlign:"right"},
                                                            {title: "核减金额", name: "HJJE", type: "decimal",disabled:"true",setReadonly:true,textAlign:"right"}
                                                        ]
                                                    },
                                                    /*{
                                                        "dtype": "html",
                                                        "dragHtml": '<div style="padding:20px 0 10px 15px;"><b>附件列表：</b><button type="button" class="bootstrap-table-upload" onclick="uploadFile(1,\'#qqch \')">上传附件</button></div>'
                                                    },*/
                                                    {
                                                        "dtype": "dtable",
                                                        "height": 250,
                                                        "id": "qqch",
                                                        "url": "",
                                                        "columns": [
                                                            {field:"XH", title:"序号", width:50, align:"center", formatter:function(value,row,index){return index+1;}},
                                                            {field:"FILEID", title:"附件id", width:200, align:"center",visible:false},
                                                            {field:"FILENAME", title:"附件名", width:200, align:"center"},
                                                            {
                                                                field: "FILEDL",
                                                                title: "附件大类",
                                                                width: 100,
                                                                align: "center",
                                                                type: 'comboBox',
                                                                selected: 1,
                                                                localdata: [{id: 1, text: "前期策划"}],
                                                                formatter: function (value, row, index) {
                                                                    return "前期策划";
                                                                }
                                                            },

                                                            {
                                                                field: "FILEXL",
                                                                title: "附件小类",
                                                                width: 120,
                                                                align: "center",
                                                                /*editor: {
                                                                    type: "comboBox",
                                                                    // localdata: fjlx[0]
                                                                }*/
                                                            },
                                                            {
                                                                field: "FILESIZE",
                                                                title: "大小",
                                                                width: 80,
                                                                align: "center",
                                                                formatter: function (value, row, index) {
                                                                    return formatSize(value);
                                                                }
                                                            },
                                                            {field:"BZ", title:"备注", width:80, align:"center"},
                                                            {
                                                                                field : '',
                                                                                title : '操作 ',
                                                                                width : 100,
                                                                                align : 'center',
                                                                                formatter : function(value, row, index) {
                                                                                    return  '<a href=file/downloadByid.do?id='+row.FILEID+'>下载</a>';
                                                                                }
                                                                            }
                                                        ]
                                                    }
                                                ]
                                            }
                                        ]
                                    }
                                ]
                            }
                        ],
                        "dtype": "row"
                    }
                ],
                "dtype": "body"
            }
        ];

        function addRow() {
            flag = 0;
            xmid = "";
            xmgsyjid = "";
            dopenindex = $.dopen({
                type: 6,
                title: "项目概算",
                area: ["95%", "100%"],
                content: dopenJson,
                btn: ["关闭"],
                btn1: function (index) {
                    layer.close(index);
                    $("#tab").bootstrapTable("refresh");
                }
            });
            $(".customInput input").each(function(){
                var $inp = $(this),
                    dtype = $inp.attr("type"),
                    options = $inp.data();
                $inp[dtype](options);
            })
        }

        
        function save1() {
            var xmgszxd = $("#xmgszxd").dtable("getData");
            $("#table").dform("setValueByName","XMGSYJID",xmgsyjid);
            var json = $("#table").dform("getData");
            $.dajax({
                url: "pro/saveXmgs.do",
                data: {
                    'json': JSON.stringify(json),
                    'xmgsyjid': xmgsyjid,
                    'xmgszxd': JSON.stringify(xmgszxd)
                },
                cache: true,
                type: "POST",
                dataType: "json",
                success: function (data) {
                  
                }
            });
        }
        function save() {
            var xmgszxd = $("#xmgszxd").dtable("getData");
            $("#table").dform("setValueByName","XMGSYJID",xmgsyjid);
            var json = $("#table").dform("getData");
            $.dajax({
                url: "pro/saveXmgs.do",
                data: {
                    'json': JSON.stringify(json),
                    'xmgsyjid': xmgsyjid,
                    'xmgszxd': JSON.stringify(xmgszxd)
                },
                cache: true,
                type: "POST",
                dataType: "json",
                success: function (data) {
                    if (data.success) {
                        xmgsyjid = data.content;
                        $.dalert({text:"保存成功", icon: 1});
                        $("#tab").dtable("refresh");
                    } else {
                        $.dalert({text: data.content, icon: 2});
                    }
                }
            });
        }
        function loadXMData() {
            $.dajax({
                url: "pro/loadXMData.do",
                data: {
                    'id': xmid
                },
                cache: true,
                type: "POST",
                dataType: "json",
                success: function (data) {
                    if (data.length > 0) {
                        $("#table").dform("setValueByName", "XMLX", data[0].XMLX);
                        $("#table").dform("setValueByName", "JSDW", data[0].JSDW);
                        $("#table").dform("setValueByName", "ZGBM", data[0].ZGBM);
                        $("#table").dform("setValueByName", "XMGHXZ", data[0].XMGHXZ);
                        $("#table").dform("setValueByName", "JSNR", data[0].JSNR);
                        $("#table").dform("setValueByName", "FKYJ", data[0].FKYJ);
                    }
                }
            });
        }

        function treeFormatterXmxmlx(value, row, index){
            var treeIdKey = "BM", treeNameKey = "MC", res = "";
            $.each(xmlx, function(key, val){
                if(val[treeIdKey] == value){
                    res = val[treeNameKey];
                    return false;
                }
            });
            return res;
        }
        function treeFormatterZgbm(value, row, index){
            var treeIdKey = "ID", treeNameKey = "MC", res = "";
            $.each(zgbm, function(key, val){
                if(val[treeIdKey] == value){
                    res = val[treeNameKey];
                    return false;
                }
            });
            return res;
        }
        function treeFormatterJsdw(value, row, index){
            var treeIdKey = "ID", treeNameKey = "MC", res = "";
            $.each(jsdw, function(key, val){
                if(val[treeIdKey] == value){
                    res = val[treeNameKey];
                    return false;
                }
            });
            return res;
        }
        function treeFormatterJLDW(value, row, index){
            var treeIdKey = "id", treeNameKey = "text", res = "";
            $.each(jldw, function(key, val){
                if(val[treeIdKey] == value){
                    res = val[treeNameKey];
                    return false;
                }
            });
            return res;
        }
        function treeFormatterZT(value, row, index){
            var treeIdKey = "id", treeNameKey = "text", res = "";
            $.each(ZT, function(key, val){
                if(val[treeIdKey] == value){
                    res = val[treeNameKey];
                    return false;
                }/*else if (val[treeIdKey] != value) {
                    res = "审核中";
                    return false;
                }*/
                if (value != 0 && value != -1) {
                    res = "审核中";
                    return false;
                }
            });
            return res;
        }
        function editRow(){
            flag = 1;
            var dataArr = $("#tab").bootstrapTable("getSelections");
            var lchjzt = dataArr[0].LCHJ;
            if (dataArr.length === 0) {
                return $.dalert("请选择要修改的数据");
            }else if (dataArr.length > 1) {
                return $.dalert("请选择一条要修改的数据");
            }
            $.dajax({
                url: "pro/getXmgszxdFile.do",
                data:{
                    id: dataArr[0].XMGSYJID
                },
                cache: true,
                type: "POST",
                dataType: "json",
                success: function (data) {
                    $("#xmgszxd").dtable("load", data);
                }
            });
            $.dajax({
                url: "pro/getZjlyFile.do",
                data:{
                    id: dataArr[0].ZJLYDJID
                },
                cache: true,
                type: "POST",
                dataType: "json",
                success: function (data) {
                    $("#zjlydj1").dtable("load", data);
                }
            });
            $.dajax({
                url: "pro/getQqchFile.do",
                data:{
                    id: dataArr[0].QQCHID
                },
                cache: true,
                type: "POST",
                dataType: "json",
                success: function (data) {
                    $("#qqch").dtable("load", data);
                }
            });
            dopenindex = $.dopen({
                type: 6,
                title :"项目概算",
                area: ["95%", "100%"],
                content: dopenJson,
                btn: ["关闭"],
                btn1:function(index){
                    layer.close(index);
                    $("#tab").bootstrapTable("refresh");
        }
            });
            xmgsyjid = dataArr[0].XMGSYJID;
            xmid = dataArr[0].MC;
            fkyj = dataArr[0].FKYJ;
            $("#table").dform("setValueByName","XMMC",dataArr[0].XMMC);
            // $("#xmmc").searchTree("reload",{url: "pro/getXmmcTree.do?id=0"});
            $("#table").dform("load",dataArr[0]);
            $("#mmm").textBox("disable");
            if (lchjzt != -1) {
                $("#jsnrid").textBox("disable");
                $("#butid").attr('disabled',true);
                $("#saveid").attr('disabled',true);
                $("#deleteFileId").attr('disabled',true);
                // $("#butid").hide();
                // $("#saveid").hide();
            }
        }


        function delRow(){
            var ids = new Array();
            var dataArr = $("#tab").bootstrapTable("getSelections");
            for(var i = 0;i<dataArr.length;i++){
                ids[i] = dataArr[i].YJID;
                if (dataArr[i].LCHJ != -1) {
                    var msg = "所选行第" + (i + 1) + "行是审核中或已审核完成数据不能删除";
                    return $.dalert({text:msg,icon:2});
                }
            }
            if (dataArr.length === 0) {
                return $.dalert("请选着一条要删除的数据");
            }
            if (dataArr.length > 0) {
                $.dajax({
                    url: "pro/deleteXmgsyj.do",
                    data: JSON.stringify({
                        id: ids
                    }),
                    cache: true,
                    type: "POST",
                    contentType: 'application/json',
                    dataType: "json",
                    success: function (data) {
                        if (data.success) {
                            $.dalert({text: data.content, icon: 1});
                            $("#tab").bootstrapTable("refresh");
                        } else {
                            $.dalert({text: data.content, icon: 2});
                        }
                    }
                });

            }else {
                return $.dalert("请选择一条要删除的数据");
            }
        }

        function openZBK(){
            var ZBK1 = [
                {
                    "plug": [
                        {
                            "plug": [
                                {
                                    "plug": [
                                        {
                                            "dtype":"html",
                                            "dragHtml":"<h4 class='text-center'><b>项目标准</b></h4>"
                                        },
                                        {
                                            "dtype": "dtable",
                                            "id":"ZBKtab",
                                            "height": 300,
                                            columns: [
                                                {checkbox: true, footerFormatter:function(data){
                                                        return "合计";
                                                    }},
                                                {
                                                    field: 'ID',
                                                    title: 'guid',
                                                    align: 'center',
                                                    width: 120,
                                                    visible: false,
                                                    disabled: "true",
                                                    setReadonly: true
                                                },
                                                {
                                                    field: 'YJZB', title: '一级指标', align: 'center', width: 120,
                                                    editor: {
                                                        type: "textBox",
                                                    },
                                                    disabled: "true", setReadonly: true
                                                },
                                                {
                                                    field: 'EJZB', title: '二级指标', align: 'center', width: 120,
                                                    editor: {
                                                        type: "textBox"
                                                    },
                                                    disabled: "true", setReadonly: true
                                                },
                                                {
                                                    field: 'SJZB', title: '三级指标', align: 'center', width: 150,
                                                    editor: {
                                                        type: "textBox"
                                                    },
                                                    disabled: "true", setReadonly: true
                                                },
                                                {
                                                    field: 'DWTZE', title: '单位投资额', align: 'center', width: 140,
                                                    editor: {
                                                        type: "textBox"
                                                    },
                                                    disabled: "true", setReadonly: true
                                                },
                                                {
                                                    field: 'JLDW', title: '计量单位', align: 'center', width: 140,
                                                    editor: {
                                                        type: "textBox"
                                                    },
                                                    disabled: "true", setReadonly: true
                                                },
                                                {
                                                    field: 'SL', title: '数量', align: 'center', width: 140,
                                                    editor: {
                                                        type: "textBox"
                                                    },
                                                    disabled: "true", setReadonly: true
                                                },
                                                {
                                                    field: 'XJ', title: '小计', align: 'center', width: 140,
                                                    editor: {
                                                        type: "textBox"
                                                    },
                                                    footerFormatter:function(data){
                                                        var DATA = 0;
                                                        for(var i = 0;i<data.length;i++){
                                                            DATA += parseFloat(data[i].XJ);
                                                        }
                                                        return DATA;
                                                    },
                                                    disabled: "true",
                                                    setReadonly: true
                                                },
                                                {field: 'ZBLY', title: '指标类型', align: 'center', width: 140, visible: false},
                                                {field: 'ZFRQ', title: '作废日期', align: 'center', width: 140, visible: false},
                                                {field: 'CJSJ', title: '操作时间', align: 'center', width: 140, visible: false},
                                                {field: 'CZR', title: '操作人', align: 'center', width: 140, visible: false}
                                            ],
                                            "url": "pro/getZBKData.do",
                                            "clickToSelect": true,
                                            "queryParams":{id: xmid},
                                            showFooter:true,
                                            onPostBody: function () {
                                                merge_footer();
                                            }
                                        },
                                        {
                                            dtype:"div",
                                            classes:"tab-pane",
                                            id:"tab2",
                                            styles:{role:"tabpanel"},
                                            plug:[
                                                {
                                                    "dtype":"html",
                                                    "dragHtml":"<h4 class='text-center'><b>项目案例库</b></h4>"
                                                },
                                                {
                                                    "dtype": "dtable",
                                                    id:"alkTable",
                                                    clickToSelect:true,
                                                    queryParams: queryParamsalk,//查询参数方法
                                                    "height": 400,
                                                    "columns": [
                                                        {field: "checkType", checkbox: true},
                                                        {
                                                            field: "DD",
                                                            title: "序号",
                                                            width: 40,
                                                            align: "center",
                                                            formatter: function (value, row, index) {
                                                                return index + 1;
                                                            }
                                                        },
                                                        {field:"bm", title:"编码", width:100, align:"center"},
                                                        {field:"mc", title:"名称", width:100, align:"center"},
                                                        {field:"nf", title:"年份", width:100, align:"center"},
                                                        {field:"zmj", title:"总面积", width:100, align:"center"},
                                                        {field:"ydmj", title:"用地面积", width:100, align:"center"},
                                                        {field:"xmdwzj", title:"项目单位造价", width:100, align:"center"}
                                                    ],
                                                    "url": "xmqqsh/getAlk.do",
                                                    pageNumber: 1,//起始页
                                                    pageSize: 10,//页面大小
                                                    pagination: false,
                                                    showRefresh: false,
                                                    paginationHAlign: 'left',//分页按钮位置  left/right
                                                    sidePagination: 'server',
                                                },
                                            ]
                                        }
                                    ],
                                    "colspan": "12",
                                    "dtype": "column"
                                }
                            ],
                            "dtype": "row"
                        }
                    ],
                    "dtype": "body"
                }
            ];
            function merge_footer() {
                var $table = $("#ZBKtab"),
                    bootstrapTable = $table.parents(".bootstrap-table"),
                    footer_tbody = bootstrapTable.find('.fixed-table-footer table tbody'),
                    footer_tr = footer_tbody.find('>tr'),
                    footer_td = footer_tr.find('>td'),
                    footer_td_1 = footer_td.eq(0),
                    colLength = $table.find("thead > tr:eq(0) > th").length;

                for(var i=1; i<colLength-1; i++) {
                    footer_td.eq(i).hide();
                }
                footer_td_1.width("auto").attr('colspan', colLength-1).show();
            }
          /*  if (xmid != undefined) {
                $.dopen({
                    type: 6,
                    title: "指标库",
                    area: ["95%", "100%"],
                    content: ZBK1,
                    btn: ["关闭"],
                    btn1:function(index){
                        layer.close(index);
                    }
                });
            }else {
                $.dalert("请先选择项目");
            }*/
            if (xmid == "") {
                $.dalert("请先选择项目名称");
            }else {
                $.dopen({
                    type: 6,
                    title: "指标库",
                    area: ["95%", "100%"],
                    content: ZBK1,
                    btn: ["关闭"],
                    btn1:function(index){
                        layer.close(index);
                    }
                });
                getalk(xmid);
            }

        }

        function getalk(){

            var $table = $("#alkTable");
            $.dajax({
                url: "xmqqsh/getAlkmb.do",
                type: "POST",
                dataType: "json",
                data: {
                    xmid: xmid
                },
                success: function (data) {
                    var columnsJson = [{field:'FLAG',checkbox: true}, {
                        field: 'INDEX',
                        title: '序号',
                        width: 50,
                        align: 'center',
                        formatter: function (value, row, index) {
                            return index + 1;
                        }
                    },{
                        field: "ID",
                        title: "alkid",
                        align: 'center',
                        width:100,
                        visible: false
                    }
                    ];
                    $.each(data, function (i, item) {
                        var temp = {
                            field: data[i].ZDM,
                            title: data[i].XSMC,
                            align: 'center',
                            width:100,
                        }
                        columnsJson.push(temp);

                    });
                    $table.dtable("refreshOptions", {columns:columnsJson});

                }
            });

            $table.dtable("refreshOptions", {url: "xmqqsh/getAlk.do?xmid="+xmid});
        }
        function queryParamsalk(params) {

            return {
                //如果需要后端进行分页 limit 和offset是必须参数
                limit: params.limit,
                offset: params.offset
//                 xmid: xmId
            }
        }
        function submit() {
            var dataArr = $("#tab").bootstrapTable("getSelections");
            var data = new Array();
            var DqLchjs = new Array();
            var type = 2;
            for(var i = 0;i<dataArr.length;i++){
                data[i] = dataArr[i].YJID;
                DqLchjs[i] = dataArr[i].LCHJ + "";
                if (DqLchjs[i] == 0) {
                    return $.dalert({text: "所选的第" + (i + 1) + "行流程已结束",icon:2});
                }
            }
            if (dataArr.length === 0) {
                return $.dalert("请至少选着一条要提交的数据");
            }
            $.dajax({
                url: "pro/submit/" + type + ".do",
                data: JSON.stringify({
                    ids: data,
                    lchj: DqLchjs
                }),
                cache: true,
                type: "POST",
                contentType: 'application/json',
                dataType: "json",
                success: function (data) {
                    if (data.success) {
                        $.dalert({text: data.content, icon: 1});
                        $("#tab").dtable("refresh");
                    } else {
                        $.dalert({text: data.content, icon: 2});
                    }
                }
            });
        }
        function submitInside() {
            var type = 2;
            var data = [xmgsyjid-0];
            // data[0] = xmgsyjid;
            if (flag == 0) {
                save1();
                //新增进来
                $.dajax({
                    url: "pro/submit/" + type + ".do",
                    data: JSON.stringify({
                        ids: data
                    }),
                    cache: true,
                    type: "POST",
                    contentType: 'application/json',
                    dataType: "json",
                    success: function (data) {
                        if (data.success) {
                            $.dalert({text: data.content, icon: 1});
                            $("#tab").dtable("refresh");
                            layer.close(dopenindex);
                        } else {
                            $.dalert({text: data.content, icon: 2});
                        }
                    }
                });
            }else if (flag == 1) {
                save1();
                //编辑进来
                var dataArr = $("#tab").bootstrapTable("getSelections");
                var data = [dataArr[0].YJID];
                if (dataArr[0].LCHJ == 0) {
                    return $.dalert({text:"流程已结束不允许提交", icon: 2});
                }
                $.dajax({
                    url: "pro/submit/" + type + ".do",
                    data: JSON.stringify({
                        ids: data,
                    }),
                    cache: true,
                    type: "POST",
                    contentType: 'application/json',
                    dataType: "json",
                    success: function (data) {
                        if (data.success) {
                            $.dalert({text: data.content, icon: 1});
                            $("#tab").dtable("refresh");
                            layer.close(dopenindex);
                        } else {
                            $.dalert({text: data.content, icon: 2});
                        }
                    }
                });
            }
        }
        function getZjlydj() {
            var zjlydjid;
            if (xmid != undefined && xmid != "") {
                $.dajax({
                    url: "pro/getZjlydj.do",
                    data: {
                        'id': xmid,
                    },
                    cache: true,
                    type: "POST",
                    dataType: "json",
                    success: function (data) {
                        if(undefined != data[0]){
                            zjlydjid = data[0].ID;
                            $("#zjlydj").dform("load",data[0]);
                            $(":input[name='ZTZHJ']").val(data[0].ZTZHJ);
                            $(":input[name='ZTZJZAZTZ']").val(data[0].ZTZJZAZTZ);
                            $(":input[name='ZTZSBTZ']").val(data[0].ZTZSBTZ);
                            $(":input[name='ZTZDTTZ']").val(data[0].ZTZDTTZ);
                            $(":input[name='ZTZQTTZ']").val(data[0].ZTZQTTZ);

                            $(":input[name='XMZJLYHJ']").val(data[0].XMZJLYHJ);
                            $(":input[name='XMZJLYCZXZJ']").val(data[0].XMZJLYCZXZJ);
                            $(":input[name='XMZJLYZYPH']").val(data[0].XMZJLYZYPH);
                            $(":input[name='XMZJLYQT']").val(data[0].XMZJLYQT);
                            $.dajax({
                                url: "pro/getZjlyFile.do",
                                data:{
                                    id: zjlydjid
                                },
                                cache: true,
                                type: "POST",
                                dataType: "json",
                                success: function (data) {
                                    $("#zjlydj1").dtable("load", data);
                                }
                            });
                            var da = data[0].JLDW;
                            var text = treeFormatterJLDW(da);
                            $("#zjlydj").dform("setValueByName", "JLDW", text);
                        }
                    }
                });

            }else {
                return $.dalert("选择单位才能显示资金来源登记");
            }
        }
        function getXmqqch() {
            var Xmqqch;
            if (xmid != undefined && xmid != "") {
                $.dajax({
                    url: "pro/getXmqqch.do",
                    data: {
                        'id': xmid,
                    },
                    cache: true,
                    type: "POST",
                    dataType: "json",
                    success: function (data) {
                        if(undefined != data[0]){
                            Xmqqch = data[0].ID;
                            $("#xmqqch").dform("load",data[0]);
                            $(":input[name='ZTZHJ1']").val(data[0].ZTZHJ1);
                            $(":input[name='ZTZJZAZTZ1']").val(data[0].ZTZJZAZTZ1);
                            $(":input[name='ZTZSBTZ1']").val(data[0].ZTZSBTZ1);
                            $(":input[name='ZTZDTTZ1']").val(data[0].ZTZDTTZ1);
                            $(":input[name='ZTZQTTZ1']").val(data[0].ZTZQTTZ1);

                            $(":input[name='XMZJLYHJ1']").val(data[0].XMZJLYHJ1);
                            $(":input[name='XMZJLYCZXZJ1']").val(data[0].XMZJLYCZXZJ1);
                            $(":input[name='XMZJLYZYPH1']").val(data[0].XMZJLYZYPH1);
                            $(":input[name='XMZJLYQT1']").val(data[0].XMZJLYQT1);
                            $.dajax({
                                url: "pro/getQqchFile.do",
                                data:{
                                    id: Xmqqch
                                },
                                cache: true,
                                type: "POST",
                                dataType: "json",
                                success: function (data) {
                                    $("#qqch").dtable("load", data);
                                }
                            });
                            var da = data[0].JLDW;
                            var text = treeFormatterJLDW(da);
                            $("#xmqqch").dform("setValueByName", "JLDW", text);
                        }
                    }
                });
            }else {
                return $.dalert("选择单位才能显示项目前期策划数据");
            }
        }
        function deleteFile(id){
            delFile(id);
        }
        function selThyj(sgtbaid){
            var yj = '';
            var type = 2;
            $.dopen({
                title: "退回意见",
                //type:6,
                content: '<div width:98%;height:98%><form id="form"></form></div>',
                //content:yjJson,
                area: ['45%','55%'],
                btn: ['确定', '取消'],
                btn1:function(index){
                    layer.close(index);
                },
                btn2:function(index){
                    layer.close(index);
                },
                cancel:function(index){//层右上角关闭按钮的点击事件触发回调函数。
                },
                end: function(){//层被彻底关闭后执行的回调函数。
                }
            });
            $("#form").dform({
                rownum:2,
                formVertical: true,
                inputs: [
                    {title: "意见输入框：", id:"textBox4",name: "textBox4", type: "textBox", multiline:true, height: 150, colspan:2},
                ]
            });
            $.dajax({
                type:'post',
                url:'pro/selThyj.do',
                data:{
                    id:sgtbaid,
                    type:type
                },
                dataType:'json',
                success:function (data) {
                    $("#textBox4").textBox("setValue",data.content);
                    $("#textBox4").textBox("disable");
                }
            })

        }

        function disableButton(arr) {
            $.each(arr,function (i,v) {
                $('#'+v).attr('disabled',true);
            })
        }
        function showButton(arr) {
            $.each(arr,function (i,v) {
                $('#'+v).attr('disabled',false);
            })
        }
    </script>
</head>
<body>

</body>
</html>
