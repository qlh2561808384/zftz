<%@ page import="com.datanew.model.YhglYwYh" %>
<%@ page import="com.datanew.util.StaticData" %>
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
    YhglYwYh operator = (YhglYwYh) request.getSession().getAttribute(StaticData.LOGINUSERINFO);
    String buttons = session.getAttribute(StaticData.USERBUTTONSSTR).toString();
    String yhlx = operator.getYhlx();
    request.setAttribute("yhlx",yhlx);
    request.setAttribute("buttons", buttons);
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
    <script src="js/proj1.js"></script>
    <script src="pro/js/deleteFile.js"></script>
    <link rel="stylesheet" href="css/common.css">
    <script>
        var flag;
        var dopenindex = 0;
        var xmid;//项目id
        var xmgsid;
        var fkyj;
        var xmgsmx;
        var xmlx = [];
        var jsdw = [];
        var zgbm = [];
        var fylx = [];
        var toolbar = new Array();
        var dragHtml = '';
        // var ZTDW = [{id:-1,text:"未备案"},{id:1,text:"已备案"},{id:2,text:"经办项目"}];
        var ZTDW = [{id:-1,text:"未备案"},{id:1,text:"已备案"}];
        var ZTCZ = [{id:1,text:"待核实"},{id:2,text:"已核实"}];
        var dopenJson;
        var jldw = [];
        var mrzt;
        $(function(){
            if(1==${yhlx}){
                mrzt=-1;
            }else if(2==${yhlx}){
                mrzt=1;
            }
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
                type:'post',
                url:"user/getButtons.do",
                dataType:'json',
                async:false,
                success:function (data) {

                    if(-1!=data.buttons.indexOf("7002701")){
                        toolbar.push({"name":"新增", "classes":"btn bootstrap-table-add", "type":"button", "onclick":"addRow()"});
                        dragHtml = '<div class="toolbar layout_toolbar clearfix">' +
                            '<button class="bootstrap-table-save" id="saveid" type="button" onclick="save()">保存</button>' +
                            '<button class="bootstrap-table-submit" id="baid" type="button" onclick="Bainside()">备案</button>' +
                            '<button class="bootstrap-table-clear" id="importid" type="button" onclick="importExcel()">明细导入</button>' +
                            /*'<button class="bootstrap-table-submit" id="hsid" type="button" onclick="Hs()">核实</button>' +*/
                            '<span class="pull-right">单位：万元</span>'+
                            '</div>';
                    }
                    if(-1!=data.buttons.indexOf("7002702")){
                        toolbar.push({"name":"删除", "classes":"btn bootstrap-table-delete", "id":"SC","type":"button", "onclick":"delRow()"});
                    }
                    if(-1!=data.buttons.indexOf("7002703")){
                        toolbar.push({"name":"查看/修改", "classes":"btn bootstrap-table-edit", "type":"button", "onclick":"editRow()"});
                    }
                    if(-1!=data.buttons.indexOf("7002704")){
                        toolbar.push({"name":"备案", "classes":"btn bootstrap-table-submit", "id":"BA" ,"type":"button", "onclick":"Ba()"});
                    }
                    if(-1!=data.buttons.indexOf("7002705")){
                        toolbar.push({"name":"核实", "classes":"btn bootstrap-table-submit","id":"HS" ,"type":"button", "onclick":"Hs()"});
                        if(dragHtml==''){
                            dragHtml='<div class="toolbar layout_toolbar clearfix">' +
                                '<button class="bootstrap-table-submit" type="button" onclick="Bainside()">备案</button>' +
                                /*'<button class="bootstrap-table-submit" type="button" onclick="Hs()">核实</button>' +*/
                                '<span class="pull-right">单位：万元</span>'+
                                '</div>';
                        }
                    }
                    if(-1!=data.buttons.indexOf("7002706")){
                        toolbar.push({"name":"退回", "classes":"bootstrap-table-disable", "id":"back","type":"button", "onclick":"back()"});
                    }
                }
            });
            $.dajax({
                url: "pro/selectXmlx.do",
                data: {},
                cache: true,
                type: "POST",
                async:false,
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
                async:false,
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
                async:false,
                dataType: "json",
                success: function (data) {
                    zgbm = data;
                }
            });
            $.dajax({
                url: "pro/getFylx.do",
                data: {},
                cache: true,
                type: "POST",
                async:false,
                dataType: "json",
                success: function (data) {
                    fylx = data;
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
                                            "dragHtml":"<h4 class='text-center'><b>项目概算备案登记</b></h4>"
                                        },
                                        {
                                            "dtype": "dtable",
                                            "id":"tab",
                                            "height": $(window).height() - 80,
                                            "columns": [
                                                {field:"checkType", checkbox:true},
                                                {field:"DD", title:"序号", width:50, align:"center", formatter:function(value,row,index){return index+1;}},
                                                {field:"GSID", title:"项目概算id", width:100, align:"center",visible:false},
                                                {field:"XMBH", title:"项目编号", width:100, align:"center"},
                                                {field:"XMMC", title:"项目名称", width:100, align:"center"},
                                                {field:"ZGBM", title:"主管部门", width:100, align:"center",formatter:treeFormatterZgbm},
                                                {field:"XMLX", title:"项目类型", width:100, align:"center",formatter:treeFormatterXmxmlx},
                                                {field:"JSDW", title:"建设单位", width:100, align:"center",formatter:treeFormatterJsdw},
                                                {field:"YDMJ", title:"用地面积（亩）", width:100, align:"center"},
                                                {field:"GSXMZTZ", title:"概算项目总投资", width:100, align:"center"},
                                                {field:"TZXE", title:"投资限额", width:100, align:"center"},
                                                {field:"XMZTZ", title:"项目总投资(万元)", width:100, align:"center"},
                                                {field:"CZR", title:"申请人", width:100, align:"center"},
                                                {field:"CZSJ", title:"申请时间", width:100, align:"center"},
                                                {field:"ZJLYDJID", title:"资金来源登记id", width:100, align:"center",visible:false},
                                                {field:"QQCHID", title:"前期策划id", width:100, align:"center",visible:false},
                                                {field:"LCHJ", title:"状态", width:100, align:"center",formatter:treeFormatterZT,visible:false},
                                                {field:"ZT", title:"", width:100, align:"center",visible:false},
                                               /* {
                                                    field : '',
                                                    title : '操作 ',
                                                    width : 100,
                                                    align : 'center',
                                                    formatter : function(value, row, index) {
                                                        return '<a href="javascript:void(0)" onclick="selThyj('+row.GSID+')">退回意见</a>';
                                                    }
                                                }*/
                                                {field: "ZT1", title: "状态", width: 100, align: "center",
                                                    formatter : function(value, row, index) {
                                                        if(value=='退回'){
                                                            return '<a href="javascript:void(0)" onclick="selThyj('+row.GSID+')">退回</a>';
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
                                                        id: 'ztid',
                                                        // url:getRootPath()+"/yhglggryqz/getsxList.do" ,
                                                        type:'comboBox',
                                                        // multiple:fals
                                                        defaultValue:mrzt,
                                                        localdata:${yhlx}==1?ZTDW:ZTCZ,
                                                        onChange:function (newValue,oldValue) {
                                                            if (2 == newValue) {
                                                                disableButton(['HS', 'back','BA','SC']);
                                                            }else {
                                                                showButton(['HS', 'back','BA','SC'])
                                                            }
                                                        }
                                                    }

                                                ]
                                            },
                                            "url": "pro/getXmgsData.do",
                                            "toolbar": /*[
                                                {"name":"新增", "classes":"btn bootstrap-table-add", "type":"button", "onclick":"addRow()"},
                                                {"name":"删除", "classes":"btn bootstrap-table-delete", "type":"button", "onclick":"delRow()"},
                                                {"name":"查看/修改", "classes":"btn bootstrap-table-edit", "type":"button", "onclick":"editRow()"},
                                                {"name":"备案", "classes":"btn bootstrap-table-submit", "type":"button", "onclick":"Ba()"},
                                                {"name":"核实", "classes":"btn bootstrap-table-submit", "type":"button", "onclick":"Hs()"},
                                                {"name":"退回", "classes":"bootstrap-table-disable", "type":"button", "onclick":"back()"}
                                            ]*/toolbar,
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
            if(1==${yhlx}){
                $("#ztid").comboBox("setValue",-1);
            }else if(2==${yhlx}){
                $("#ztid").comboBox("setValue",1);
            }

            dopenJson = [
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
                                                '    <li role="presentation" class="active"><a href="#infoTab1" aria-controls="infoTab1" role="tab" data-toggle="tab">项目概算备案登记</a></li>' +
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
                                                            "dragHtml": "<h4 class='text-center'><b>项目概算登记备案</b></h4>"
                                                        },

                                                        {
                                                            "dtype":"html",
                                                            "dragHtml": /*'<div class="toolbar layout_toolbar clearfix">' +
                                                                '<button class="bootstrap-table-clear" id="importid" type="button" onclick="importExcel()">明细导入</button>' +
                                                                '<button class="bootstrap-table-save" id="saveid" type="button" onclick="save()">保存</button>' +
                                                                '<button class="bootstrap-table-submit" id="baid" type="button" onclick="Ba()">备案</button>' +
                                                                /!*'<button class="bootstrap-table-submit" id="hsid" type="button" onclick="Hs()">核实</button>' +*!/
                                                                '</div>*/dragHtml
                                                        },
                                                        {
                                                            "dtype":"dform",
                                                            "classes":"tableForm",
                                                            "id": "table",
                                                            "rownum":3,
                                                            "labelwidth":"200px",
                                                            "labelAlign":"center",
                                                            "inputs":[
                                                                {title: "项目概算id", name:"XMGSID", type:"hidden"},
                                                                {title: "项目编码", name:"MC", type:"hidden"},
                                                                // {title: "项目名称", name:"XMMC", type:"textBox"},
                                                                {
                                                                    title: '项目名称',//表单lable显示名
                                                                    type:"textBox",
                                                                    name:"XMMC",
                                                                    id: "mmm",
                                                                    onLoaded: function($el){
                                                                        openProj($el,function(node){
                                                                            xmid = node.id;

                                                                           $("#table").dform("setValueByName","MC",node.id);
                                                                            $("#table").dform("setValueByName","XMMC",node.name);
                                                                            loadXMData();
                                                                        },"Xmgsbadj");
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
                                                                    // onClickInput: true,
                                                                    url : 'pro/getXmgsmcTree.do',
                                                                    onAckCallback:function(nodes){
                                                                        if(nodes.length>0){
                                                                            $('#xmmc').searchTree("setValue",nodes[0].id);
                                                                        }
                                                                    },
                                                                    onChange:function (newValue,oldValue) {
                                                                        xmid = newValue;
                                                                        // loadXMData();
                                                                    }
                                                                },*/
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
                                                                {
                                                                    title: '项目主管部门',//表单lable显示名
                                                                    type: 'dsearchtree',//表单类型：目前支持 select/text
                                                                    placeholder: '请选择主管部门',
                                                                    searchOption:true,
                                                                    id:'zgdw',
                                                                    name:'ZGBM',
                                                                    checkType:'radio',
                                                                    url : 'pro/getZGBMTree.do',
                                                                    onAckCallback:function(nodes){
                                                                        if(nodes.length>0){
                                                                            $('#zgdw').searchTree("setValue",nodes[0].id);
                                                                        }
                                                                    }
                                                                },
                                                                {title: "项目规划选址", name:"XMGHXZ", type:"textBox"},
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
                                                                {title: "容积率", name: "RJL",type:"decimal",decimalPlaces:2,textAlign:"right"},
                                                                {title: "用地面积(平方米)", name: "YDMJ",type:"decimal",decimalPlaces:2,textAlign:"right"},
                                                                {title: "建筑总面积（平方米）", name: "JZZMJ",type:"decimal",decimalPlaces:2,textAlign:"right"},
                                                                {title: "其中：地上建筑面积(平方米)", name: "DSJZMJ",type:"decimal",decimalPlaces:2,textAlign:"right"},
                                                                {title: "项目里程（公里）", name: "XMLC",type:"decimal",decimalPlaces:2,textAlign:"right"},
                                                                {title: "道路宽度(米)", name: "DLKD",type:"decimal",decimalPlaces:2,textAlign:"right"},
                                                                {title: "投资限额", name: "TZXE",type:"decimal",decimalPlaces:2,textAlign:"right"},
                                                                {title: "建议控制造价标准(元)", name: "JYKZZJBZ",type:"decimal",decimalPlaces:2,textAlign:"right"},
                                                                {title: "项目单位造价标准(元)", name: "XMDWZJ",type:"decimal",decimalPlaces:2,textAlign:"right"},
                                                                {title: "标准库", name: "BZK", type: "html",html:'<a onclick="openBZK()" style="text-align: right;color: red" >点击查看</a>'
                                                                },

                                                                {
                                                                    title: "<br/>建设内容和规模",
                                                                    name: "JSNR",
                                                                    type: "textBox",
                                                                    multiline: true,
                                                                    colspan: 3
                                                                }
                                                            ]
                                                        },
                                                        {
                                                            "dtype": "dtable",
                                                            "height": 400,
                                                            "id":"badjtab",
                                                            "url": "",
                                                            "headerHeight": 30,
                                                            /*  onLoadSuccess:function (data) {
                                                                  eeee();
                                                              },*/
                                                            "columns": [
                                                                [
                                                                    {field: "checkType", checkbox: true, rowspan: 3},
                                                                    {
                                                                        field: "UUID",
                                                                        title: "序号",
                                                                        width: 40,
                                                                        align: "center",
                                                                        rowspan: 3,
                                                                        clickToSelect: true,
                                                                        formatter: function (value, row, index) {
                                                                            return index + 1;
                                                                        }
                                                                    },
                                                                    {
                                                                        field: "XMGSMXID",
                                                                        title: "项目概算明细id",
                                                                        width: 100,
                                                                        align: "center",
                                                                        clickToSelect: true,
                                                                        rowspan: 3,
                                                                        visible: false
                                                                    },
                                                                    {
                                                                        id: "a",
                                                                        field: "GCFYMC",
                                                                        title: "工程或者费用名称",
                                                                        width: 100,
                                                                        align: "center",
                                                                        clickToSelect: true,
                                                                        rowspan: 3,
                                                                        editor: {
                                                                            type: "textBox",
                                                                        }
                                                                    },
                                                                    {
                                                                        id: "b",
                                                                        field: "JLDW",
                                                                        title: "计量单位",
                                                                        width: 80,
                                                                        align: "center",                                                                    clickToSelect: true,
                                                                        clickToSelect: true,
                                                                        rowspan: 3,
                                                                        editor: {
                                                                            type: "textBox",
                                                                        }
                                                                    },
                                                                    {
                                                                        id: "c",
                                                                        field: "GCL",
                                                                        title: "工程量",
                                                                        width: 80,
                                                                        align: "center",
                                                                        clickToSelect: true,
                                                                        rowspan: 3,
                                                                        editor: {
                                                                            title: "小数",
                                                                            type: "decimal",
                                                                            decimalPlaces: 2,
                                                                        }
                                                                    },
                                                                    {
                                                                        id: "d",
                                                                        field: "FYLX",
                                                                        title: "费用类型",
                                                                        width: 80,
                                                                        align: "right",
                                                                        halign: "center",
                                                                        clickToSelect: true,
                                                                        rowspan: 3,
                                                                        editor: {
                                                                            /*data:{
                                                                                key : {name:"NAME"},
                                                                                simpleData :{
                                                                                    enable : true,
                                                                                    idKey : "BM",
                                                                                    pIdKey : "PID",
                                                                                    rootPId : 0
                                                                                }
                                                                            },
                                                                            type:"searchTree",
                                                                            checkType:'radio',
                                                                            url:"pro/getFylx.do",
                                                                            rootElement:false,
                                                                            onlyLeaf:true,*/
                                                                            type: 'searchTree',
                                                                            url: 'pro/getFylx.do',
                                                                            modalTitle: '请选择费用类型',
                                                                            checkType: 'radio',
                                                                            onlyLeaf:true,
                                                                            rootElement:true
                                                                        },
                                                                        formatter: treeFormatterFylx
                                                                    },
                                                                    {
                                                                        id: "e",
                                                                        field: "DJHFL",
                                                                        title: "单价或者费率",
                                                                        width: 100,
                                                                        align: "right",
                                                                        halign: "center",
                                                                        clickToSelect: true,
                                                                        rowspan: 3,
                                                                        editor: {
                                                                            title: "小数",
                                                                            type: "decimal",
                                                                            decimalPlaces: 2,
                                                                        }
                                                                    },
                                                                    {
                                                                        field: "TZE",
                                                                        title: "投资额(万元)",
                                                                        width: 600,
                                                                        align: "center",
                                                                        clickToSelect: true,
                                                                        colspan: 7,
                                                                        editor: {
                                                                            title: "小数",
                                                                            type: "decimal",
                                                                            decimalPlaces: 2,
                                                                        }
                                                                    },
                                                                    // {field:"", title:"其他", width:200, align:"center",colspan:5}
                                                                ], [
                                                                    {
                                                                        field: "HJ",
                                                                        title: "合计",
                                                                        width: 50,
                                                                        align: "right",
                                                                        halign: "center",
                                                                        clickToSelect: true,
                                                                        rowspan: 2
                                                                    },
                                                                    {
                                                                        id: "f",
                                                                        field: "JZTZ",
                                                                        title: "建筑投资",
                                                                        width: 50,
                                                                        align: "right",
                                                                        halign: "center",
                                                                        clickToSelect: true,
                                                                        rowspan: 2,
                                                                        editor: {
                                                                            title: "小数",
                                                                            type: "decimal",
                                                                            decimalPlaces: 2,
                                                                        }
                                                                    },
                                                                    {
                                                                        id: "h",
                                                                        field: "AZTZ",
                                                                        title: "安装投资",
                                                                        width: 50,
                                                                        align: "right",
                                                                        halign: "center",
                                                                        clickToSelect: true,
                                                                        rowspan: 2,
                                                                        editor: {
                                                                            title: "小数",
                                                                            type: "decimal",
                                                                            decimalPlaces: 2,
                                                                        }
                                                                    },
                                                                    {
                                                                        id: "i",
                                                                        field: "SBTZ",
                                                                        title: "设备投资",
                                                                        width: 50,
                                                                        align: "right",
                                                                        halign: "center",
                                                                        clickToSelect: true,
                                                                        rowspan: 2,
                                                                        editor: {
                                                                            title: "小数",
                                                                            type: "decimal",
                                                                            decimalPlaces: 2,
                                                                        }
                                                                    },
                                                                    {
                                                                        field: "QTTZ",
                                                                        title: "其他投资",
                                                                        width: 400,
                                                                        align: "center",
                                                                        clickToSelect: true,
                                                                        colspan: 3,
                                                                        editor: {
                                                                            title: "小数",
                                                                            type: "decimal",
                                                                            decimalPlaces: 2,
                                                                        }
                                                                    }

                                                                ], [
                                                                    {
                                                                        id: "g",
                                                                        field: "XJ",
                                                                        title: "小计",
                                                                        width: 80,
                                                                        align: "right",
                                                                        halign: "center",
                                                                        clickToSelect: true,
                                                                        editor: {
                                                                            title: "小数",
                                                                            type: "decimal",
                                                                            decimalPlaces: 2,

                                                                        }
                                                                    },{
                                                                        id: "k",
                                                                        field: "TDZQFY",
                                                                        title: "其中:土地征迁费用",
                                                                        width: 160,
                                                                        align: "right",
                                                                        halign: "center",
                                                                        clickToSelect: true,
                                                                        editor: {
                                                                            title: "小数",
                                                                            type: "decimal",
                                                                            decimalPlaces: 2,
                                                                        }
                                                                    },
                                                                    {
                                                                        id: "l",
                                                                        field: "LXFY",
                                                                        title: "其中:利息费用",
                                                                        width: 160,
                                                                        align: "right",
                                                                        halign: "center",
                                                                        clickToSelect: true,
                                                                        editor: {
                                                                            title: "小数",
                                                                            type: "decimal",
                                                                            decimalPlaces: 2,
                                                                        }
                                                                    }
                                                                ]
                                                            ],
                                                            "toolbar": [
                                                                {
                                                                    "name": "增加行",
                                                                    "classes": "btn bootstrap-table-add",
                                                                    "type": "button",
                                                                    "id": "addid",
                                                                    "onclick": "add()"
                                                                },
                                                                {
                                                                    "name": "删除行",
                                                                    "classes": "btn bootstrap-table-delete",
                                                                    "type": "button",
                                                                    "id": "removid",
                                                                    "onclick": "remov()"
                                                                },
                                                            ],
                                                            "clickToSelect": true
                                                        },
                                                        {
                                                            "dtype":"html",
                                                            "dragHtml":'<div style="padding:20px 0 10px 15px;"><b>附件列表：</b><button type="button" id="uploadid" class="bootstrap-table-upload" onclick="uploadFile(3,\'#xmgsFile \')">上传附件</button>' +
                                                                '&nbsp;<button type="button" class="bootstrap-table-delete" id="deleteFileId" onclick="deleteFile(\'#xmgsFile\')">删除附件</button>'+
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
                                                                            "id": "xmgsFile",
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
                                                                                    localdata: [{id: 3, text: "项目概算备案登记"}],
                                                                                    formatter: function (value, row, index) {
                                                                                        return "项目概算备案登记";
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
                                                                {title: "容积率", name:"RJL", type:"decimal",disabled:"true",setReadonly:true},
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
                                                        "dragHtml":'<div style="padding:20px 10px 10px 15px;"><b>附件列表：</b><button type="button" class="bootstrap-table-upload" onclick="uploadFile()">上传附件</button></div>'
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
                                                                {title: "用地面积(平方米)", name: "YDMJ", type: "decimal",disabled:"true",setReadonly:true,textAlign:"right"},
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
                                                                    html: '<div class="customLabel text-center"><label class="control-label">合计</label></div><div class="customInput text-center"><input type="textBox" name="ZTZHJ1" disabled="true" style="text-align: right"></div>'
                                                                },
                                                                {
                                                                    title: '<div class="customLabel"><label class="control-label">建筑安装投资</label></div><div class="customInput"><input type="textBox" name="ZTZJZAZTZ1" disabled="true" style="text-align: right"></div>',
                                                                    name: "",
                                                                    type: "html",
                                                                    rowspan: 2,
                                                                    html: '<div class="customLabel"><label class="control-label">设备投资</label></div><div class="customInput"><input type="textBox" name="ZTZSBTZ1" disabled="true" style="text-align: right"></div>'
                                                                },
                                                                {
                                                                    title: '<div class="customLabel"><label class="control-label">待摊投资</label></div><div class="customInput"><input type="textBox" name="ZTZDTTZ1" disabled="true" style="text-align: right"></div>',
                                                                    name: "",
                                                                    type: "html",
                                                                    rowspan: 2,
                                                                    html: '<div class="customLabel"><label class="control-label">其他投资</label></div><div class="customInput" ><input type="textBox" name="ZTZQTTZ1" disabled="true" style="text-align: right"></div>'
                                                                },

                                                                {
                                                                    title: "项目资金来源",
                                                                    name: "",
                                                                    type: "html",
                                                                    rowspan: 2,
                                                                    html: '<div class="customLabel text-center"><label class="control-label">合计</label></div><div class="customInput text-center"><input type="textBox" name="XMZJLYHJ1" disabled="true" style="text-align: right"></div>'
                                                                },
                                                                {
                                                                    title: '<div class="customLabel"><label class="control-label">财政性资金</label></div><div class="customInput"><input type="textBox" name="XMZJLYCZXZJ1" disabled="true" style="text-align: right"></div>',
                                                                    name: "",
                                                                    type: "html",
                                                                    rowspan: 2,
                                                                    html: '<div class="customLabel"><label class="control-label">资源平衡</label></div><div class="customInput"><input type="textBox" name="XMZJLYZYPH1" disabled="true" style="text-align: right"></div>'
                                                                },
                                                                {
                                                                    title: '<div class="customLabel"><label class="control-label">其他</label></div><div class="customInput"><input type="textBox" name="XMZJLYQT1" disabled="true" style="text-align: right"></div>',
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
                                                            "dragHtml": '<div style="padding:20px 0 10px 15px;"><b>附件列表：</b><button type="button" class="bootstrap-table-upload" onclick="uploadFile()">上传附件</button></div>'
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
        });



        function addRow() {
            flag = 0;
            xmid = "";
            xmgsid = "";
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
            saveTable();
            $("#table").dform("setValueByName", "XMGSID", xmgsid);
            var fileId = $("#xmgsFile").dtable("getData");
            var xmgsJson = $("#table").dform("getData");
            $.dajax({
                url: "pro/saveGs.do",
                data: JSON.stringify({
                    xmgsmx:xmgsmx,
                    xmgs:xmgsJson,
                    xmgsid: xmgsid,
                    gsFileId: fileId
                }),
                contentType: 'application/json',
                cache: true,
                type: "POST",
                dataType: "json",
                success: function (data) {
                    if (data.success) {
                        xmgsid = data.content;
                        $.dalert({text: "保存成功", icon: 1});
                        $("#tab").dtable("refresh");
                        $.dajax({
                            url: "pro/getXmgsmxData.do",
                            data: {
                                'id': xmgsid
                            },
                            cache: true,
                            type: "POST",
                            dataType: "json",
                            success: function (data) {
                                $("#badjtab").dtable("load", data);
                                    eeee();
                            }
                        });
                    } else {
                        $.dalert({text: data.content, icon: 2});
                    }
                }
            });
        }
        
        function save() {
            saveTable();
            $("#table").dform("setValueByName", "XMGSID", xmgsid);
            var fileId = $("#xmgsFile").dtable("getData");
            var xmgsJson = $("#table").dform("getData");
            $.dajax({
                url: "pro/saveGs.do",
                data: JSON.stringify({
                    xmgsmx:xmgsmx,
                    xmgs:xmgsJson,
                    xmgsid: xmgsid,
                    gsFileId: fileId
                }),
                contentType: 'application/json',
                cache: true,
                type: "POST",
                dataType: "json",
                success: function (data) {
                    if (data.success) {
                        xmgsid = data.content;
                        $.dalert({text: "保存成功", icon: 1});
                        $("#tab").dtable("refresh");
                        $.dajax({
                            url: "pro/getXmgsmxData.do",
                            data: {
                                'id': xmgsid
                            },
                            cache: true,
                            type: "POST",
                            dataType: "json",
                            success: function (data) {
                                $("#badjtab").dtable("load", data);
                                    eeee();
                            }
                        });
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

                        $("#table").dform("setValueByName", "RJL", data[0].RJL);
                        $("#table").dform("setValueByName", "YDMJ", data[0].YDMJ);
                        $("#table").dform("setValueByName", "JZZMJ", data[0].JZZMJ);
                        $("#table").dform("setValueByName", "DSJZMJ", data[0].DSJZMJ);

                        $("#table").dform("setValueByName", "XMLC", data[0].XMLC);
                        $("#table").dform("setValueByName", "DLKD", data[0].DLKD);
                        $("#table").dform("setValueByName", "TZXE", data[0].SDTZS);
                        $("#table").dform("setValueByName", "JYKZZJBZ", data[0].JYKZZJBZ);
                        $("#table").dform("setValueByName", "XMDWZJ", data[0].XMDWZJ);
                        $("#table").dform("setValueByName", "JSNR", data[0].JSNR);
                    }
                }
            });
        }
        function treeFormatterFylx(value, row, index){
            var treeIdKey = "BM", treeNameKey = "NAME", res = "";
            $.each(fylx, function(key, val){
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
                }
                if (val[treeIdKey] != -1) {
                    res = "已备案";
                    return false;
                }
            });
            return res;
        }
        function cancel() {

            alert(0);
            var $table = $("#badjtab");
            var rowIndex = $table.find("tr.editing").data("index");
            $table.dtable("cancel", rowIndex);
            alert(1);
        }

        function editRow(){
            flag = 1;
            var dataArr = $("#tab").bootstrapTable("getSelections");
            var lchjid = dataArr[0].LCHJ;
            if (dataArr.length === 0) {
                return $.dalert("请选择要修改的数据");
            }else if (dataArr.length > 1) {
                return $.dalert("请选择一条要修改的数据");
            }
            $.dajax({
                url: "pro/getXmgsmxData.do",
                data: {
                    'id': dataArr[0].GSID
                },
                cache: true,
                type: "POST",
                dataType: "json",
                success: function (data) {
                    $("#badjtab").dtable("load", data);
                    if (lchjid == -1) {
                        eeee();
                    }else {
                        // cancel();
                        eeee();
                        /*setTimeout(function () {
                            cancel()
                        }, 1000);*/

                      /*  $("#a").attr('disabled',true);*/
                        // disableButton(['a','b','c','d','e','f','h','i','g','k','l']);
                    }
                }
            });
            $.dajax({
                url: "pro/getXmgsFile.do",
                data:{
                    id: dataArr[0].GSID
                },
                cache: true,
                type: "POST",
                dataType: "json",
                success: function (data) {
                    $("#xmgsFile").dtable("load", data);
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
                title: "项目概算",
                area: ["95%", "100%"],
                content: dopenJson,
                btn: ["关闭"],
                btn1: function (index) {
                    layer.close(index);
                    $("#tab").bootstrapTable("refresh");
                    table();
                }
            });
            xmgsid = dataArr[0].GSID;
            xmid = dataArr[0].MC;
            fkyj = dataArr[0].FKYJ;
            // $("#xmmc").searchTree("reload",{url: "pro/getXmgsmcTree.do?id=0"});
            $("#table").dform("load",dataArr[0]);
            $("#mmm").textBox("disable");
            if (lchjid != -1) {
                disableButton(['importid','saveid','baid','hsid','uploadid','addid','removid','deleteFileId']);
                $("#table").dform("setReadonly", true);
            }
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
        function delRow(){
            var ids = new Array();
            var dataArr = $("#tab").bootstrapTable("getSelections");
            if (dataArr.length === 0) {
                return $.dalert("请选着一条要删除的数据");
            }
            for(var i = 0;i<dataArr.length;i++){
                ids[i] = dataArr[i].GSID;
                if (dataArr[i].LCHJ != -1) {
                    var msg = "所选中行第" + (i + 1) + "行是已备案数据，不能删除";
                    return $.dalert({text:msg,icon:2});
                }
            }
            if (dataArr.length > 0) {
               /* $.dajax({
                    url: "pro/delXmgs.do",
                    data: JSON.stringify({
                        id: ids
                    }),
                    cache: true,
                    type: "POST",
                    dataType: "json",
                    contentType: 'application/json',
                    success: function (data) {
                        if (data.success) {
                            $.dalert({text: data.content, icon: 1});
                            $("#tab").bootstrapTable("refresh");
                        } else {
                            $.dalert({text: data.content, icon: 2});
                        }
                    }
                });*/
            }else {
                return $.dalert("请选择要删除的数据");
            }
            // var id = dataArr[0].GSID;

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
                                            "height": $(window).height() - 80,
                                            columns: [
                                                {checkbox: true},
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
                                            onPostBody: function () {
                                                merge_footer();
                                            }
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
                    colLength = $table.find("tbody > tr:eq(0) > td").length;

                for(var i=1; i<colLength-1; i++) {
                    footer_td.eq(i).hide();
                }
                footer_td_1.width("auto").attr('colspan', colLength-1).show();
            }
            if (xmid != undefined) {
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
            }

        }

        function submit(value) {
            var dataArr = $("#tab").bootstrapTable("getSelections");
            var data = new Array();
            var DqLchjs = new Array();
            for(var i = 0;i<dataArr.length;i++){
                data[i] = dataArr[i].GSID;
                DqLchjs[i] = dataArr[i].LCHJ;
            }
            if (dataArr.length === 0) {
                return $.dalert("请至少选着一条要提交的数据");
            }
            $.dajax({
                url: "pro/submit.do",
                data: JSON.stringify({
                    ids: data,
                    flag: value,
                    lchj: DqLchjs
                }),
                cache: true,
                type: "POST",
                contentType: 'application/json',
                dataType: "json",
                success: function (data) {
                    if (data.success) {
                        $.dalert({text: data.content, icon: 1});
                    } else {
                        $.dalert({text: data.content, icon: 2});
                    }
                }
            });
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
        function table() {
            var $table = $("#badjtab");
            $table.dtable("refresh");
            var dataLen = $table.dtable("getData").length;
            $table.dtable("refreshOptions",{height:250+30*dataLen});
        }
        //tab2增加按钮
        function add(){
            var $table = $("#badjtab");
            var dataLen = $table.dtable("getData").length;
            $table.tableEditor("updateAll");

            $table.bootstrapTable('insertRow', {index:dataLen, row:{}});

            $table.dtable("refreshOptions",{height:250+30*dataLen});
            $table.tableEditor("initAll");
        }
        function remov() {
            var $table = $("#badjtab");
            var ids = new Array();
            var selectData = getselectdata();
            if(selectData != undefined){
                for(var i = 0;i<selectData.length;i++){
                    ids[i] = selectData[i].XMGSMXID;
                }
                layer.confirm('确定删除吗？', {
                    btn: ['确定','取消'] //按钮
                }, function(){
                    $.dajax({
                        url: "pro/deleteGSMX.do",
                        data: JSON.stringify({
                            id: ids
                        }),
                        cache: true,
                        type: "POST",
                        dataType: "json",
                        contentType: 'application/json',
                        success: function (data) {
                            var tableData = $table.dtable("getData");
                            $table.tableEditor("updateAll");
                            $.each(selectData, function(i){
                                var dataIndex = tableData.indexOf(selectData[i]);
                                $table.dtable("removeByRowIndex", dataIndex);
                            });
                            $table.tableEditor("updateAll");

                            var dataLen = $table.dtable("getData").length;

                            $table.dtable("refreshOptions",{height:250+30*dataLen});
                            $table.tableEditor("initAll");
                            layer.msg('删除成功', {icon: 1});
                        }
                    });
                });
            }
        }
        function getselectdata() {
            var dataArr = $("#badjtab").dtable("getSelections");
            if (dataArr.length === 0) {
                $.dalert({text:"请选择数据",icon:2});
                return;
            }
            return dataArr;
        }

        function openBZK(){
            var ZBK1 = [
                {
                    "plug": [
                        {
                            "plug": [
                                {
                                    "plug": [
                                        {
                                            "dtype":"html",
                                            "dragHtml":"<h4 class='text-center'><b>项目指标库</b></h4>"
                                        },
                                        {
                                            "dtype": "dtable",
                                            "id":"ZBKtab",
                                            "height": 300,
                                            columns: [
                                                {checkbox: true,footerFormatter:function(data){
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
                                            showFooter:true,
                                            "queryParams":{id: xmid},
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

            if (xmid == "") {
                $.dalert("请先选择项目名称");
            }else {
                $.dopen({
                    type: 6,
                    title: "指标库",
                    area: ["95%", "95%"],
                    content: ZBK1,
                    btn: ["关闭"],
                    btn1:function(index){
                        layer.close(index);
                    }
                });
                getalk();
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
        function saveTable(){
            var $table = $("#badjtab");
            $table.tableEditor("updateAll"); //编辑后，保存全部
            var dataArr = $table.dtable("getData");
            xmgsmx = dataArr;
        }
        function eeee() {
            var $table = $("#badjtab");
            $table.tableEditor("updateAll");
            $table.tableEditor("initAll");
        }
        function importExcel() {
            var form = $("#table").dform("getData");
            var mcid = form.MC;
            if (mcid != "" && mcid != undefined) {
                $.dopen({
                    title: "excle导入",
                    content: '<div style="padding:40px 10px">' +
                        '<form  id="fileForm"></form>' +
                        '</div>',
                    area: ['400px', '200px'],
                    btn: ['关闭'],
                    btn1: function (index) {
                        setTimeout(function () {
                            $("#badjtab").dtable("refresh");

                            setTimeout(function () {
                                $("#badjtab").tableEditor("initAll");
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
                                    $.dajax({
                                        url: "pro/getXmgsmxData.do",
                                        data: {
                                            'id': xmgsid
                                        },
                                        cache: true,
                                        type: "POST",
                                        dataType: "json",
                                        success: function (data) {
                                            $("#badjtab").dtable("load", data);
                                           /* if (lchjid == -1) {
                                                eeee();
                                            }*/
                                        }
                                    });
                                    $("#badjtab").dtable("refresh");
                                } else {
                                    $.dalert({text:"导入失败",icon:2})
                                }
                            },
                            server: "pro/webupload.do?xmid=" + mcid,
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
        function Ba() {
            var dataArr = $("#tab").bootstrapTable("getSelections");
            var data = new Array();
            var DqLchjs = new Array();
            var type = 3;
            for(var i = 0;i<dataArr.length;i++){
                data[i] = dataArr[i].GSID;
                DqLchjs[i] = dataArr[i].LCHJ + "";
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

        function Bainside() {
            var type = 3;
           var data = [ xmgsid -0];
           if(flag==0) {
               //新增进来
               save1();
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
               var data = [dataArr[0].GSID];
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
           }

        }
        function Hs() {
            var dataArr = $("#tab").bootstrapTable("getSelections");
            var data = new Array();
            var DqLchjs = new Array();
            var type = 3;
            for(var i = 0;i<dataArr.length;i++){
                data[i] = dataArr[i].GSID;
                DqLchjs[i] = dataArr[i].LCHJ + "";
            }
            if (dataArr.length === 0) {
                return $.dalert("请至少选着一条要提交的数据");
            }
            $.dajax({
                url: "pro/next/" + type + ".do",
                data: JSON.stringify({
                    ids: data,
                    lchjs: DqLchjs
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
        function checkDatas(id,callback) {
            $.dajax({
                url: "pro/checkData.do",
                data: {
                    'id': id,
                },
                cache: true,
                type: "POST",
                // contentType: 'application/json',
                dataType: "json",
                success: function (data) {
                    callback(data.content);
                }
            });
        }
        function back1(thyj) {
            //判断是否可以退回
            var dataArr = $("#tab").bootstrapTable("getSelections");
            var checkId = "";
            var data = new Array();
            var DqLchjs = new Array();
            var THYJ = [thyj];
            var type = 3;
            for(var i = 0;i<dataArr.length;i++){
                data[i] = dataArr[i].GSID;
                DqLchjs[i] = dataArr[i].LCHJ +"";
                if (i != dataArr.length - 1) {
                    checkId += dataArr[i].XMID + ",";
                }else {
                    checkId += dataArr[i].XMID;
                }
            }
            if (dataArr.length === 0) {
                return $.dalert("请至少选着一条退回的数据");
            }
            checkDatas(checkId,function (content) {
                if (content != 0) {
                    $.dalert({text:"所选数据已有备案调整数据或施工图备案数据不能退回", icon: 2});
                } else {
                    $.dajax({
                        url: "pro/back/" + type + ".do",
                        data: JSON.stringify({
                            ids: data,
                            lchjs: DqLchjs,
                            comment: THYJ
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
            });
        }
        function deleteFile(id){
            delFile(id);
        }
        function back(){
            var ids = '';
            var data = $("#tab").bootstrapTable("getSelections");
            var checkId = data[0].XMID;

            if(data.length==0){
                $.dalert({text:'请选择数据',icon:7});
                return;
            }else if(data.length>1){
                $.dalert({text:'只能选择一条数据',icon:7});
                return;
            }
            checkDatas(checkId,function (content) {
                if (content != 0) {
                    $.dalert({text:"所选数据已有备案调整数据或施工图备案数据不能退回", icon: 2});
                }else {
                    setThYj();
                }
            });
        }

        function setThYj(){
            $.dopen({
                title: "退回意见",
                //type:6,
                content: '<div width:98%;height:98%><form id="form"></form></div>',
                //content:yjJson,
                area: ['45%','55%'],
                btn: ['确定', '取消'],
                btn1:function(index){
                    var formdata = $("#form").dform("getData");
                    if(formdata.textBox4!=""){
                        back1(formdata.textBox4);
                        layer.close(index);
                    }else{
                        $.dalert({text:'退回意见不能为空',icon:7});
                    }
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
                    {title: "意见输入框：", name: "textBox4", type: "textBox", multiline:true, height: 150, colspan:2},
                ]
            })
        }
        function selThyj(sgtbaid){
            var yj = '';
            var type = 3;
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
    </script>
</head>
<body>

</body>
</html>
