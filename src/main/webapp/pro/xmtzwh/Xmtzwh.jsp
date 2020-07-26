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
    <title>项目台账维护</title>
    <script src="bootstrap2/js/jquery.js"></script>
    <script src="bootstrap2/js/bootstrap.datanew.js"></script>
    <script src="js/file.js"></script>
    <script src="pro/js/uploadFile.js"></script>
    <script src="js/common.js"></script>
    <script src="pro/js/deleteFile.js"></script>
    <script src="js/proj1.js"></script>
    <link rel="stylesheet" href="css/common.css">
    <script>
        var ywid;
        var ddid;
        var zcdjData;
        var tzwhid;
        var xmlx = [];
        var xmsslx = [];
        var xmjd = [];
        var DL = [];
        var XL = [];
        $(function(){
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
                url: "pro/selectXmsslx.do",
                data: {},
                cache: true,
                type: "POST",
                dataType: "json",
                success: function (data) {
                    xmsslx = data;
                }
            });
            $.dajax({
                url: "pro/selectXmjd.do",
                data: {},
                cache: true,
                type: "POST",
                dataType: "json",
                success: function (data) {
                    xmjd = data;
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
                                            "dtype":"dform",
                                            "classes":"searchForm",
                                            "id":"search",
                                            "rownum":4,
                                            "labelwidth":"80px",
                                        },
                                        {
                                            "dtype":"html",
                                            "dragHtml":"<h4 class='text-center'><b>项目基本信息维护</b></h4>"
                                        },
                                        {
                                            "dtype": "dtable",
                                            "id":"tab",
                                            "height": $(window).height() - 80,
                                            "columns": [
                                                {field:"checkType", checkbox:true},
                                                {field:"DD", title:"序号", width:50, align:"center", formatter:function(value,row,index){return index+1;}},
                                                {field:"ID", title:"项目唯一标示id", width:100, align:"center", visible: false},
                                                {field:"ZTWHID", title:"项目台账维护id", width:100, align:"center", visible: false},
                                                {field:"XMBH", title:"项目编号", width:100, align:"center"},
                                                {field:"XMMC", title:"项目名称", width:100, align:"center"},
                                                {field:"XMLX", title:"项目类型", width:100, align:"center",formatter:treeFormatterXmxmlx},
                                                {field:"XMJD", title:"项目阶段", width:100, align:"center",formatter:treeFormatterXmjd},
                                                {field:"XMSSLX", title:"项目实施类型", width:100, align:"center",formatter:treeFormatterXmsslx},
                                                {field:"LXND", title:"立项年度", width:100, align:"center"},
                                                {field:"JSDW", title:"建设单位", width:100, align:"center",formatter:treeFormatterXmlx},
                                                {field:"ZTZ", title:"总投资(万元)", width:100, align:"center"},

                                                {field:"XMFM", title:"项目赋码", width:100, align:"center", visible: false},
                                                {field:"ZGBM", title:"主管部门", width:100, align:"center", visible: false},
                                                {field: "JHKGRQ", title:"计划开工日期", width:100, align:"center", visible: false},
                                                {field:"TZGS", title:"估算投资(万元)", width:100, align:"center", visible: false},
                                                {field:"JSDZ", title:"建设地址", width:100, align:"center", visible: false},
                                                {field:"JHJGRQ", title:"计划竣工日期", width:100, align:"center", visible: false},
                                                {field:"GSTZ", title:"概算投资(万元)", width:100, align:"center", visible: false},
                                                {title:"项目建议文书号(项目联系单)", field:"XMJYWSH", width:100, align:"center", visible: false},
                                                {title:"形象进度(%)", field:"XXJD", width:100, align:"center", visible: false},
                                                {title:"其中：建安概算投资(万元)", field:"JAGSTZ", width:100, align:"center", visible: false},
                                                {title:"项目可研批复文号", field:"XMKYPFWH", width:100, align:"center", visible: false},
                                                {title:"受托审核事务所(竣工决算)", field:"STSHSWS", width:100, align:"center", visible: false},
                                                {title:"审定投资数(万元)", field:"SDTZS", width:100, align:"center", visible: false},
                                                {title:"概算批复文号", field:"GSPFWH", width:100, align:"center", visible: false},
                                                {title:"项目初步设计批复文号", field:"XMCBSJPFWH", width:100, align:"center", visible: false},
                                                {title:"是否封闭项目", field:"SFFB", width:100, align:"center", visible: false},
                                                {title:"项目联系人", field:"XMLXR", width:100, align:"center", visible: false},
                                                {title:"实际开工日期", field:"SJKGRQ", width:100, align:"center", visible: false},
                                                {title:"项目绩效", field:"XMJX", width:100, align:"center", visible: false},
                                                {title:"项目联系电话", field:"XMLXDH", width:100, align:"center", visible: false},
                                                {title:"实际竣工日期", field:"SJJGRQ", width:100, align:"center", visible: false},
                                                {title:"建设内容和规模", field:"JSNR", width:100, align:"center", visible: false}
                                                //资产类型
                                            ],
                                            searchbar: {
                                                rownum: 3,//搜索栏表单列数  最大支持3
                                                labelwidth:150 ,
                                                inputs: [//搜索栏表单参数
                                                    {
                                                        title : '项目名称:',
                                                        name : 'xmmc',
                                                        type:'textBox'

                                                    }, {
                                                        title: '项目类型',//表单lable显示名
                                                        type: 'dsearchtree',//表单类型：目前支持 select/text
                                                        placeholder: '请选择项目类型',
                                                        searchOption:true,
                                                        id:'xmlx',
                                                        name:'XMLX',
                                                        checkType:'radio',
                                                        url : 'pro/getXMLXTree.do',
                                                        onAckCallback:function(nodes){
                                                            if(nodes.length>0){
                                                                $('#xmlx').searchTree("setValue",nodes[0].id);
                                                            }
                                                        }
                                                    },{
                                                        title: '项目实施类型',//表单lable显示名
                                                        type: 'dsearchtree',//表单类型：目前支持 select/text
                                                        placeholder: '请选择项目实施类型',
                                                        searchOption:true,
                                                        id:'xmsslx',
                                                        name:'XMSSLX',
                                                        checkType:'radio',
                                                        url : 'pro/getXmsslxTree.do',
                                                        onAckCallback:function(nodes){
                                                            if(nodes.length>0){
                                                                $('#xmsslx').searchTree("setValue",nodes[0].id);
                                                            }
                                                        }
                                                    }
                                                ]
                                            },
                                            "url": "pro/getXMData.do",
                                            "toolbar": [
                                                {"name":"新增", "classes":"btn bootstrap-table-add", "type":"button", "onclick":"addRow()"},
                                                {"name":"删除", "classes":"btn bootstrap-table-delete", "type":"button", "onclick":"delRow()"},
                                                {"name":"查看/修改", "classes":"btn bootstrap-table-edit", "type":"button", "onclick":"editRow()"}
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
                                            '    <li role="presentation" class="active"><a href="#infoTab1" aria-controls="infoTab1" role="tab" data-toggle="tab">项目信息登记</a></li>' +
                                            '    <li role="presentation"><a href="#infoTab2" aria-controls="infoTab2" role="tab" data-toggle="tab" onclick="flush()">项目资产登记</a></li>' +
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
                                                        "dragHtml": "<h4 class='text-center'><b>项目信息登记</b></h4>"
                                                    },
                                                    {
                                                        "dtype":"html",
                                                        "dragHtml":'<div class="toolbar layout_toolbar clearfix">' +
                                                            '<button class="bootstrap-table-save" type="button" onclick="save()">保存</button>' +
                                                            /*'<button class="bootstrap-table-review" type="button" onclick="">重填</button>' +*/
                                                            /*'<button class="bootstrap-table-clear" type="button" onclick="">关闭</button>' +*/
                                                            '<span class="pull-right">单位：万元</span>'+
                                                            '</div>'
                                                    },
                                                    {
                                                        "dtype":"dform",
                                                        "classes":"tableForm",
                                                        "id": "table",
                                                        "rownum":3,
                                                        "labelwidth":"140px",
                                                        "labelAlign":"center",
                                                        "inputs":[
                                                            {title: "项目唯一标示id", name:"ID", type:"hidden"},
                                                            // {title: "项目名称", name:"XMMC", type:"textBox"},
                                                            {
                                                                title: '项目名称',//表单lable显示名
                                                                type:"textBox",
                                                                name:"XMMC",
                                                                id: "mmm",
                                                                required: true,
                                                                onLoaded: function($el){
                                                                    openProj($el, function (node) {
                                                                        ywid = node.id;
                                                                        ddid = node.id;
                                                                        $("#table").dform("setValueByName", "ID", node.id);
                                                                        $("#table").dform("setValueByName", "XMMC", node.name);
                                                                        loadxmData();
                                                                    }, "Xmtzwh");
                                                                }
                                                            },
                                                            {title:"项目编号", name:"XMBH", type:"textBox"},
                                                            {title:"项目赋码", name:"XMFM", type:"textBox"},
                                                            {
                                                                title: '项目阶段',//表单lable显示名
                                                                type: 'textBox',//表单类型：目前支持 select/text
                                                                // placeholder: '请选择项目阶段',
                                                                // searchOption:true,
                                                                id:'xmjdxm',
                                                                name:'XMJD',
                                                                checkType:'radio',
                                                                setReadonly:true,
                                                                formatter:treeFormatterXmjd
                                                               /* url : 'pro/getXMJDTree.do',
                                                                onAckCallback:function(nodes){
                                                                    if(nodes.length>0){
                                                                        $('#xmjdxm').searchTree("setValue",nodes[0].id);
                                                                    }
                                                                }*/
                                                            },
                                                            // {title:"项目阶段", name:"XMJD", type:"comboBox", localdata:[{id:1,text:"前期"},{id:2,text:"新建"},{id:3,text:"续建"},{id:4,text:"尾款"}]},
                                                            {
                                                                title: '项目类型',//表单lable显示名
                                                                type: 'dsearchtree',//表单类型：目前支持 select/text
                                                                placeholder: '请选择项目类型',
                                                                searchOption:true,
                                                                id:'xmlxxm',
                                                                name:'XMLX',
                                                                checkType:'radio',
                                                                url : 'pro/getXMLXTree.do',
                                                                onAckCallback:function(nodes){
                                                                    if(nodes.length>0){
                                                                        $('#xmlxxm').searchTree("setValue",nodes[0].id);
                                                                    }
                                                                }
                                                            },
                                                            // {title:"项目类型", name:"XMLX", type:"comboBox", localdata:[{id:1,text:"房建类"},{id:2,text:"市政园林类"},{id:3,text:"公路交通类"},{id:4,text:"水利类"},{id:5,text:"其他"}]},
                                                            {
                                                                title: '项目实施类型',//表单lable显示名
                                                                type: 'dsearchtree',//表单类型：目前支持 select/text
                                                                placeholder: '请选择项目实施类型',
                                                                searchOption:true,
                                                                id:'xmsslxxm',
                                                                name:'XMSSLX',
                                                                checkType:'radio',
                                                                url : 'pro/getXmsslxTree.do',
                                                                onAckCallback:function(nodes){
                                                                    if(nodes.length>0){
                                                                        $('#xmsslxxm').searchTree("setValue",nodes[0].id);
                                                                    }
                                                                }
                                                            },
                                                            // {title:"项目实施类型", name:"XMSSLX", type:"comboBox", localdata:[{id:1,text:"施工承包模式"},{id:2,text:"PPP模式"},{id:3,text:"EPC模式"},{id:4,text:"其他"}]},
                                                            {
                                                                title: '项目主管部门',//表单lable显示名
                                                                type: 'dsearchtree',//表单类型：目前支持 select/text
                                                                placeholder: '请选择主管部门',
                                                                searchOption:true,
                                                                id:'batch',
                                                                name:'ZGBM',
                                                                checkType:'radio',
                                                                url : 'pro/getZGBMTree.do',
                                                                onAckCallback:function(nodes){
                                                                    if(nodes.length>0){
                                                                        $('#batch').searchTree("setValue",nodes[0].id);
                                                                }
                                                                }
                                                            },
                                                            {title:"立项年度", name:"LXND", type:"dateBox",format:"yyyy",minView:4},
                                                            {title:"总投资/匡算投资", name:"ZTZ", type:"textBox",disabled:"true",setReadonly:true,textAlign:"right"},
                                                            // {title:"建设单位", name:"JSDW", type:"comboTree"},
                                                            {
                                                                title: '建设单位',//表单lable显示名
                                                                type: 'dsearchtree',//表单类型：目前支持 select/text
                                                                placeholder: '请选择建设单位',
                                                                searchOption:true,
                                                                id:'depart',
                                                                name:'JSDW',
                                                                checkType:'radio',
                                                                url : 'pro/getJSDWTree.do',
                                                                onAckCallback:function(nodes){
                                                                    if(nodes.length>0){
                                                                        $('#depart').searchTree("setValue",nodes[0].id);
                                                                    }
                                                                }
                                                            },
                                                            {title:"计划开工日期", name:"JHKGRQ", type:"dateBox"},
                                                            {title:"估算投资(万元)", name:"TZGS", type:"textBox",disabled:"true",setReadonly:true,textAlign:"right"},
                                                            {title:"建设地址", name:"JSDZ", type:"textBox"},
                                                            {title:"计划竣工日期", name:"JHJGRQ", type:"dateBox"},
                                                            {title:"概算投资(万元)", name:"GSTZ", type:"textBox",disabled:"true",setReadonly:true,textAlign:"right"},
                                                            {title:"项目建议文书号(项目联系单)", name:"XMJYWSH", type:"textBox"},
                                                            {title:"形象进度(%)", name:"XXJD", type:"decimal",decimalPlaces:2,textAlign:"right"},
                                                            {title:"其中：建安概算投资(万元)", name:"JAGSTZ", type:"textBox",disabled:"true",setReadonly:true,textAlign:"right"},
                                                            {title:"项目可研批复文号", name:"XMKYPFWH", type:"textBox"},
                                                            {title:"受托审核事务所(竣工决算)", name:"STSHSWS", type:"textBox"},
                                                            {title:"审定投资数(万元)", name:"SDTZS", type:"decimal",decimalPlaces:2,textAlign:"right"},
                                                            {title:"概算批复文号", name:"GSPFWH", type:"textBox"},
                                                            {title:"项目初步设计批复文号", name:"XMCBSJPFWH", type:"textBox"},/*,{id:1,text:"是"}*/
                                                            {title:"是否封闭项目", name:"SFFB", type:"comboBox", localdata:[{id:0,text:"否"}],setReadonly:true},
                                                            {title:"项目联系人", name:"XMLXR", type:"textBox"},
                                                            {title:"实际开工日期", name:"SJKGRQ", type:"dateBox"},
                                                            {title:"项目绩效", name:"XMJX", type:"textBox",disabled:true,setReadonly:true},
                                                            {title:"项目联系电话", name:"XMLXDH", type:"textBox"},
                                                            {title:"实际竣工日期", name:"SJJGRQ", type:"dateBox"},
                                                            {title:"建设内容和规模", name:"JSNR", type:"textBox", multiline:true, colspan:3,disabled:"true",setReadonly:true}
                                                        ]
                                                    },
                                                    {
                                                        "dtype":"html",
                                                        "dragHtml": '<div style="padding:20px 0 10px 15px;">' +
                                                            '<b>附件列表：</b>' +
                                                            '<button type="button" class="bootstrap-table-upload" onclick="uploadFile(14,\'#uploadTable\')">上传附件</button>' +
                                                            '&nbsp;<button type="button" class="bootstrap-table-delete" id="deleteFileId" onclick="deleteFile(\'#uploadTable\')">删除附件</button>'+
                                                            '&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp' +
                                                            '&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp' +
                                                            '&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp'+
                                                            '&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp'+
                                                            '&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp'+
                                                            '&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp'+
                                                            // '&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp'+
                                                            /*'<div style="padding:10px 0 10px 10px;"  style="text-align: right">' +*/
                                                            '<b>照片：</b>' +
                                                            '<button type="button" class="bootstrap-table-upload" onclick="uploadJpg()">上传照片</button>' +
                                                            '</div>'
                                                    },
                                                    {
                                                        "dtype":"div",
                                                        "classes":"clearfix",
                                                        "plug":[
                                                            {
                                                                "dtype":"div",
                                                                "classes":"pull-left",
                                                                "attrs":{"style":"width:70%;"},
                                                                "plug":[
                                                                    {
                                                                        "dtype": "dtable",
                                                                        "id":"uploadTable",
                                                                        "height": 250,
                                                                        "clickToSelect": true,
                                                                        // "url": "pro/getFile.do",
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
                                                                                localdata: [{id: 14, text: "项目台账"}],
                                                                                formatter: function (value, row, index) {
                                                                                    return "项目台账";
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
                                                            {
                                                                "dtype":"html",
                                                                "dragHtml":'<div class="pull-right" style="width:28%;"><img src="" id="dtl_img" width="100%" /></div>'
                                                            }
                                                        ]
                                                    }
                                                ]
                                            },
                                            {
                                                "dtype":"div",
                                                "classes":"tab-pane",
                                                "attrs":{role:"tabpanel", id:"infoTab2"},
                                                "plug": [
                                                    {
                                                        "dtype": "html",
                                                        "dragHtml": "<h4 class='text-center'><b>项目资产登记</b></h4>"
                                                    },
                                                    {
                                                        "dtype": "dtable",
                                                        "height": 300,
                                                        "id": "row",
                                                        "url": "pro/getZcdjData.do",
                                                        onLoadSuccess:function (data) {
                                                            eeee();
                                                        },
                                                        "columns": [
                                                            {field: "checkType", checkbox: true},
                                                            {
                                                                field: "UUID",
                                                                title: "序号",
                                                                width: 50,
                                                                align: "center",
                                                                formatter: function (value, row, index) {
                                                                    return index + 1;
                                                                }
                                                            },
                                                            {
                                                                field: "ID",
                                                                title: "项目资产登记id",
                                                                width: 100,
                                                                align: "center",
                                                                visible: false
                                                            },
                                                            {
                                                                field: "ZCBH",
                                                                title: "资产编号",
                                                                width: 100,
                                                                align: "center",
                                                                editor: {
                                                                    type: "textBox",
                                                                }
                                                            },
                                                            {
                                                                field: "ZCMC",
                                                                title: "资产名称",
                                                                width: 100,
                                                                align: "center",
                                                                editor: {
                                                                    type: "textBox",
                                                                }
                                                            },
                                                            {
                                                                field: "ZCLB",
                                                                title: "资产类别",
                                                                width: 100,
                                                                align: "center",
                                                                formatter:treeFormatterZclb,
                                                                editor: {
                                                                    onlyLeaf:true,
                                                                    rootElement:true,
                                                                    type: 'searchTree',
                                                                    url: 'pro/getJclbTreeData.do',
                                                                    modalTitle: '请选择资产类别',
                                                                    checkType: 'radio'
                                                                }
                                                            },
                                                            {
                                                                field: "ZCJZ",
                                                                title: "资产价值(万元)",
                                                                width: 100,
                                                                align: "right",
                                                                editor: {
                                                                    type:"decimal",
                                                                    decimalPlaces:2,
                                                                    textAlign:"right"
                                                                }
                                                            },
                                                            {
                                                                field: "MJ",
                                                                title: "面积(平方米)",
                                                                width: 100,
                                                                align: "right",
                                                                editor: {
                                                                    type:"decimal",
                                                                    decimalPlaces:2,
                                                                    textAlign:"right"
                                                                }
                                                            },
                                                            {
                                                                field: "RZFS",
                                                                title: "入账方式",
                                                                width: 100,
                                                                formatter:treeFormatterRzfs,
                                                                align: "center",
                                                                editor: {
                                                                    type: 'searchTree',
                                                                    url: 'pro/getRzfsTreeData.do',
                                                                    modalTitle: '请选择入账方式',
                                                                    checkType: 'radio'
                                                                }
                                                            },
                                                            {
                                                                field: "RZSJ",
                                                                title: "入账时间",
                                                                width: 100,
                                                                align: "center",
                                                                editor: {
                                                                    type: "dateBox",
                                                                    startView: 3,
                                                                    minView: 2,
                                                                    format: "yyyy-mm-dd",
                                                                    pickerPosition: "bottom-left"
                                                                }
                                                            },
                                                            {
                                                                field: "BZ",
                                                                title: "备注",
                                                                width: 100,
                                                                align: "center",
                                                                editor: {
                                                                    type: "textBox",
                                                                }
                                                            }
                                                        ],
                                                        "toolbar": [
                                                            {
                                                                "name": "增加行",
                                                                "classes": "btn bootstrap-table-add",
                                                                "type": "button",
                                                                "onclick": "add()"
                                                            },
                                                            {
                                                                "name": "删除行",
                                                                "classes": "btn bootstrap-table-delete",
                                                                "type": "button",
                                                                "onclick": "remov()"
                                                            },
                                                            {
                                                                "name": "保存",
                                                                "classes": "btn bootstrap-table-save",
                                                                "type": "button",
                                                                "onclick": "addSave()"
                                                            },
                                                            {
                                                                "name": "明细导入",
                                                                "classes": "btn bootstrap-table-upload",
                                                                "type": "button",
                                                                "onclick": "importExcel()"
                                                            },
                                                            /*{
                                                                "name": "编辑",
                                                                "classes": "btn bootstrap-table-save",
                                                                "type": "button",
                                                                "onclick": "eeee()"
                                                            },*/
                                                            /*{"name":"关闭", "classes":"btn bootstrap-table-clear", "type":"button", "onclick":"submit()"}*/
                                                        ],
                                                        "clickToSelect": true
                                                    },
                                                    {
                                                        "dtype": "html",
                                                        "dragHtml": '<div style="padding:20px 0 10px 15px;"><b>附件列表：</b><button type="button" class="bootstrap-table-upload" onclick="uploadFile(15,\'#zcdjFile\')">上传附件</button>' +
                                                            '&nbsp;<button type="button" class="bootstrap-table-delete" id="deleteFileId" onclick="deleteFile(\'#zcdjFile\')">删除附件</button>' +
                                                            '</div>'
                                                    },
                                                    {
                                                        "dtype": "dtable",
                                                        "id": "zcdjFile",
                                                        "height": 250,
                                                        "url": "",
                                                        "clickToSelect": true,
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
                                                                localdata: [{id: 15, text: "资产登记"}],
                                                                formatter: function (value, row, index) {
                                                                    return "资产登记";
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
        //tab2增加行按钮
        function addRow() {
            ywid = "";
            ddid = "";
            $.dopen({
                type: 6,
                title :"项目登记",
                area: ["95%", "100%"],
                content: dopenJson,
                btn: ["关闭"],
                btn1:function(index){
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

       /* function uploadFile(fileType,id){

        	uploadfile({server: 'file/uploader.do?filebstype=' + fileType}, function (rowData) {
                var $table = $(id),
                    dataLen = $table.dtable("getData").length;
	            $table.tableEditor("updateAll");
	            for (var i = 0; i < rowData.length; i++) {
	                var data = {
	                    FILENAME: rowData[i].name,
	                    FILESIZE: rowData[i].size,
	                    FILEID: rowData[i].fileId
	                };
	                /!*alert(rowData[i].name);
	                alert(rowData[i].size);
	                alert(rowData[i].fileId);*!/
	                $table.dtable("insertRow", {index: dataLen + i, row: data});
	            }
	            $table.tableEditor("initAll");
	        });
        }*/

        function showJpg(ywID) {
            //图片显示
            $.dajax({
                cache: true,
                type: "post",
                url: "index/getXmpic.do",
                dataType: "json",
                data:{guid:ywID},
                success: function (data) {
                    if (undefined != data[0]) {
                        $("#dtl_img").attr("src", "file/downloadByid.do?id=" + data[0].GUID);
                    }
                }
            });
        }
        //tab1的保存  （保存tab1 tab2的所有数据）
        function save(){
            var flag = $("#table").dform("validate");
            if(!flag){
                $.dalert({text: "请先填写必填项", icon: 2});
            }else {
                //保存前先保存资产登记的table
                saveTabel();
                var fileId = $("#uploadTable").dtable("getData");
                var zcdjFileId = $("#zcdjFile").dtable("getData");
                $("#table").dform("setValueByName","ID",ywid);
                var jsonFormData = $("#table").dform("getData");
                var id = jsonFormData.ID;
                ddid = jsonFormData.ID;
                //资产登记数据
                $.dajax({
                    url: "pro/save.do",
                    data: {
                        'jsonData': JSON.stringify(jsonFormData),
                        'id': id,
                        'dataArr': JSON.stringify(zcdjData),
                        'fileId': JSON.stringify(fileId),
                        'zcdjFileId': JSON.stringify(zcdjFileId)
                    },
                    cache: true,
                    type: "POST",
                    dataType: "json",
                    success: function (data) {
                        if (data.success) {
                            ywid = data.content;
                            showJpg(ywid);
                            $.dalert({text: "保存成功", icon: 1});
                            $("#row").dtable("refresh", {query: {id: ddid}});
                            // $("#table").dform("refresh");
                            flush();
                            $("#tab").dtable("refresh");
                            $("#uploadTable").dtable("refresh");
                            $.dajax({
                                url: "pro/getXMJD.do",
                                data:{
                                    id: ywid
                                },
                                cache: true,
                                type: "POST",
                                dataType: "json",
                                success: function (data) {
                                    var da = treeFormatterXmjd(data[0].XMJD);
                                    $("#table").dform("setValueByName", "XMJD", da);
                                }
                            });
                        } else {
                            $.dalert({text: data.content, icon: 2});
                        }
                    }
                });
            }
        }
        function treeFormatterXmlx(value, row, index) {
            var treeIdKey = "JSDW", treeNameKey = "MC", res = "";
            $.each($("#tab").dtable("getData"), function(key, val){
                if(val[treeIdKey] == value){
                    res = val[treeNameKey];
                    return false;
                }
            });
            return res;
        }
        function treeFormatterXmjd(value, row, index){
            var treeIdKey = "BM", treeNameKey = "MC", res = "";
            $.each(xmjd, function(key, val){
                if(val[treeIdKey] == value){
                    res = val[treeNameKey];
                    return false;
                }
            });
            return res;
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
        function treeFormatterXmsslx(value, row, index){
            var treeIdKey = "BM", treeNameKey = "MC", res = "";
            $.each(xmsslx, function(key, val){
                if(val[treeIdKey] == value){
                    res = val[treeNameKey];
                    return false;
                }
            });
            return res;
        }
        function treeFormatterZclb(value, row, index){
            var treeIdKey = "ZCLB", treeNameKey = "ZCLBMC", res = "";
            $.each($("#row").dtable("getData"), function(key, val){
                if(val[treeIdKey] == value){
                    res = val[treeNameKey];
                    return false;
                }
            });
            return res;
        }
        function treeFormatterRzfs(value, row, index){
            var treeIdKey = "RZFS", treeNameKey = "RZFSMC", res = "";
            $.each($("#row").dtable("getData"), function(key, val){
                if(val[treeIdKey] == value){
                    res = val[treeNameKey];
                    return false;
                }
            });
            return res;
        }

        //主页面修改查看
        function editRow(){
            var dataArr = $("#tab").bootstrapTable("getSelections");
            if (dataArr.length === 0) {
                return $.dalert("请选着一条要修改的数据");
            }else if (dataArr.length > 1) {
                return $.dalert("请选择一条要修改的数据");
            }
            showJpg(dataArr[0].ID);
            $.dajax({
                url: "pro/getFile.do",
                data:{
                    id: dataArr[0].TZWHID
                },
                cache: true,
                type: "POST",
                dataType: "json",
                success: function (data) {
                    $("#uploadTable").dtable("load", data);
                }
            });
            $.dajax({
                url: "pro/getZcdjFile.do",
                data:{
                    id: dataArr[0].TZWHID
                },
                cache: true,
                type: "POST",
                dataType: "json",
                success: function (data) {
                    $("#zcdjFile").dtable("load", data);
                }
            });
            $.dopen({
                type: 6,
                title :"项目登记",
                area: ["95%", "100%"],
                content: dopenJson,
                btn: ["关闭"],
                btn1:function(index){
                    layer.close(index);
                    $("#tab").bootstrapTable("refresh");
                }
            });
            $("#table").dform("load",dataArr[0]);
            var da = treeFormatterXmjd(dataArr[0].XMJD);
            $("#table").dform("setValueByName", "XMJD", da);
            ddid = dataArr[0].ID;
            ywid = dataArr[0].ID;
            $("#mmm").textBox("disable");
        }
        //主页面删除按钮
        function delRow() {
            var ids = new Array();
            var dataArr = $("#tab").bootstrapTable("getSelections");
            for(var i = 0;i<dataArr.length;i++){
                ids[i] = dataArr[i].ID;
            }
            if (dataArr.length > 0) {
                // id = dataArr[0].ID;
                $.dajax({
                    url: "pro/delete.do",
                    data:JSON.stringify({
                        id: ids
                    }),
                    contentType: 'application/json',
                    cache: true,
                    type: "POST",
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
            } else {
                return $.dalert("请选择要删除的数据");
            }
        }
        //tab2增加按钮
        function add(){
            var $table = $("#row");
            var dataLen = $table.dtable("getData").length;
            $table.tableEditor("updateAll");

            $table.bootstrapTable('insertRow', {index:dataLen, row:{}});

            $table.tableEditor("initAll");
        }
        //tab2删除按钮
        function remov() {
            var $table = $("#row");
            var id = "";
            var selectData = getselectdata();
            if(selectData != undefined){
                layer.confirm('确定删除吗？', {
                    btn: ['确定','取消'] //按钮
                }, function(){
                    for(i=0;i<selectData.length;i++){
                        if (!selectData[i].ID) {
                            id += "-1" + ",";
                        } else {
                            id += selectData[i].ID + ",";
                        }
                    }
                    $.dajax({
                        type: "POST",
                        data: $.param({"id":id}),
                        url: "pro/deleteZcdj.do",
                        datatype: "json",
                        cache: true,
                        success: function (data) {
                            if (data.success) {
                                $.dalert({text: data.content, icon: 1});
                                flush();
                            } else {
                                $.dalert({text: data.content, icon: 2});
                            }
                        }
                    });

                    var tableData = $table.dtable("getData");
                    $table.tableEditor("updateAll");
                    $.each(selectData, function(i){
                        var dataIndex = tableData.indexOf(selectData[i]);
                        $table.dtable("removeByRowIndex", dataIndex);
                    });
                    $table.tableEditor("updateAll");


                    $table.tableEditor("initAll");


                });
            }
        }
        function getselectdata() {
            var dataArr = $("#row").dtable("getSelections");
            if (dataArr.length === 0) {
                $.dalert({text:"请选择数据",icon:2});
                return;
            }
            return dataArr;
        }
        function saveTabel() {
            var $table = $("#row");
            $table.tableEditor("updateAll"); //编辑后，保存全部
            var dataArr = $table.dtable("getData");
            zcdjData = dataArr;
        }
        //tab2里面的暂时保存按钮
        function addSave(){
            save();
        }

        //tab2带参数调后台
        function flush() {
            if (ddid != undefined && ddid != "") {
                $("#row").dtable("refresh", {query: {id: ddid}});
            }
        }

        function eeee() {
            var $table = $("#row");
            $table.tableEditor("updateAll");
            $table.tableEditor("initAll");
        }

        function uploadJpg() {
            uploadfileJpg({server: 'file/uploaderJpg.do?filebstype=14'}, function (rowData) {
                var $table = $("#uploadTable"),
                    dataLen = $table.dtable("getData").length;
                $table.tableEditor("updateAll");
                for (var i = 0; i < rowData.length; i++) {
                    var data = {
                        FILENAME: rowData[i].name,
                        FILESIZE: rowData[i].size,
                        FILEID: rowData[i].fileId
                    };
                    $table.dtable("insertRow", {index: dataLen + i, row: data});
                }
                $table.tableEditor("initAll");
            });
        }

        function loadxmData() {
            $.dajax({
                url: "pro/loadxmData.do",
                data: {
                    'id': ddid
                },
                cache: true,
                type: "POST",
                dataType: "json",
                success: function (data) {
                    if (data.length > 0) {
                        /*$("#table").dform("load", data[0]);*/
                        var da = treeFormatterXmjd(data[0].XMJD);
                        $("#table").dform("setValueByName", "XMJD", da);
                        $("#table").dform("setValueByName", "XMLX", data[0].XMLX);
                        $("#table").dform("setValueByName", "JSDW", data[0].JSDW);
                        $("#table").dform("setValueByName", "ZGBM", data[0].ZGBM);
                        // $("#table").dform("setValueByName", "XMJD", data[0].XMJD);
                        $("#table").dform("setValueByName", "XMFM", data[0].XMFM);
                        $("#table").dform("setValueByName", "SFFB", data[0].SFFB);

                        $("#table").dform("setValueByName", "ZTZ", data[0].ZTZ);
                        $("#table").dform("setValueByName", "TZGS", data[0].TZGS);
                        $("#table").dform("setValueByName", "GSTZ", data[0].GSTZ);
                        $("#table").dform("setValueByName", "JAGSTZ", data[0].JAGSTZ);
                        $("#table").dform("setValueByName", "JSNR", data[0].JSNR);

                        $("#table").dform("setValueByName", "XMBM", data[0].XMBM);
                        $("#table").dform("setValueByName", "XMSSLX", data[0].XMSSLX);
                        $("#table").dform("setValueByName", "LXND", data[0].LXND);
                        $("#table").dform("setValueByName", "JHKGRQ", data[0].JHKGRQ);
                        $("#table").dform("setValueByName", "JSDZ", data[0].JSDZ);

                        $("#table").dform("setValueByName", "JHJGRQ", data[0].JHJGRQ);
                        $("#table").dform("setValueByName", "XMJYWSH", data[0].XMJYSWH);
                        $("#table").dform("setValueByName", "XXJD", data[0].XXJD);
                        $("#table").dform("setValueByName", "XMKYPFWH", data[0].XMKYPFWH);
                        $("#table").dform("setValueByName", "STSHSWS", data[0].STSHSWS);


                        $("#table").dform("setValueByName", "SDTZS", data[0].SDTZS);
                        $("#table").dform("setValueByName", "GSPFWH", data[0].GSPFWH);
                        $("#table").dform("setValueByName", "XMCBSJPFWH", data[0].XMCBSJPFWH);
                        $("#table").dform("setValueByName", "XMLXR", data[0].XMLXR);
                        $("#table").dform("setValueByName", "SJKGRQ", data[0].SJKGRQ);

                        $("#table").dform("setValueByName", "SJJGRQ", data[0].SJJGRQ);
                        $("#table").dform("setValueByName", "XMLXDH", data[0].XMLXDH);

                    }
                }
            });
        }
        function importExcel() {
            if (ywid != "" && ywid != undefined) {
                $.dopen({
                    title: "excle导入",
                    content: '<div style="padding:40px 10px">' +
                        '<form  id="fileForm"></form>' +
                        '</div>',
                    area: ['400px', '200px'],
                    btn: ['关闭'],
                    btn1: function (index) {
                        setTimeout(function () {
                            $("#row").dtable("refresh");

                            setTimeout(function () {
                                $("#row").tableEditor("initAll");
                            }, 1000)
                        }, 100);
                        layer.close(index);
                    }
                });
                var fileObj = {
                    rownum: 1,
                    labelwidth: "100px",
                    "inputs": [
                        {
                            type: "webupload",
                            title: "附件:",
                            name: "file1",
                            isDownload: false,
                            isDel: false,
                            auto: true,
                            singleMode: true,
                            onFileQueued: function (File) {

                            },
                            onFileDequeued: function (File) {

                            }/*,onLoaded: function (obj) {
                        $(this).webupload("loadData", obj.options.url + "?uuid=" + row.FJUUID)
                    }*/, onUploadBeforeSend: function (object, data, headers) {
                                data.token = $.dcookie('token');
                            }, onUploadSuccess: function (file, response) {
                                if (response.success) {
                                    $.dalert({text: "导入成功", icon: 1});
                                    $("#row").dtable("refresh", {query: {id: ddid}});
                                } else {
                                    $.dalert({text:"导入失败",icon:2})
                                }
                            },
                            server: "pro/webuploadExcel.do?xmid=" + ywid,
                            thumbnailWidth: 80,
                            thumbnailHeight: 50
                            // colspan:"3"
                        }
                    ]
                };
                $('#fileForm').dform(fileObj);
            }else {
                $.dalert("请选择项目名称");
            }

        }
        function deleteFile(id){
            delFile(id);
         }
    </script>
</head>
<body>

</body>
</html>
