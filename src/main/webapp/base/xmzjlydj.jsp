<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1">
    <title>项目资金来源登记</title>
    <script src="bootstrap2/js/jquery.js"></script>
    <script src="bootstrap2/js/bootstrap.datanew.js"></script>
    <script src="js/file.js"></script>
    <script src="js/proj.js"></script>
    <script src="js/common.js"></script>
    <link rel="stylesheet" href="css/common.css">
    <script>
        var getRootPath = function () {
            var curWwwPath = window.document.location.href;
            var pathName = window.document.location.pathname;
            var pos = curWwwPath.indexOf(pathName);
            var localhostPaht = curWwwPath.substring(0, pos);
            var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
            return (localhostPaht + projectName);
        }
        //        var savaSldh;
        //        var xmqqchid;
        var ztzhjz=0;
    	var ztzzjlyz=0;
        var xmmcData = [];
        var xmlx = [];
        var jsdw = [];
        var zgbm = [];
        var selxmid;
        var xmid;
        var xmzbid;   //项目主表id
        $(function () {
            $.dajax({
                url: "xmzjlydj/selectXmmc.do",
                data: {},
                async:false,
                type: "POST",
                dataType: "json",
                success: function (data) {
                    xmmcData = data;
                }
            });
            $.dajax({
                url: "xmqqch/selectXmlx.do",
                data: {},
                async:false,
                type: "POST",
                dataType: "json",
                success: function (data) {
                    xmlx = data;
                }
            });
            $.dajax({
                url: "xmzjlydj/selectJsdw.do",
                data: {},
                async:false,
                type: "POST",
                dataType: "json",
                success: function (data) {
                    jsdw = data;
                }
            });
            $.dajax({
                url: "xmqqch/selectZgbm.do",
                data: {},
                async:false,
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
                                            "dtype": "html",
                                            "dragHtml": "<h4 class='text-center'><b>项目资金来源登记</b></h4>"
                                        },
                                        {
                                            "dtype": "dtable",
                                            "id": "teb",
                                            "height": $(window).height() - 80,
                                            "columns": [
                                                {field: "checkType", checkbox: true},
                                                {
                                                    field: "",
                                                    title: "序号",
                                                    width: 40,
                                                    align: "center",
                                                    formatter: function (value, row, index) {
                                                        return index + 1;
                                                    }
                                                },
                                                
                                                {field: "xmzjid", visible: false},
                                                {field: "id", visible: false},
                                                {field: "ide", visible: false},
                                                {field: "xmid", visible: false},
//                                                 {field: "xmbh", title: "项目编号", width: 100, align: "center"},
                                                {field: "xmmc", title: "项目名称", width: 100, align: "center"},
                                                {
                                                    field: "zgbm",
                                                    title: "主管部门",
                                                    width: 100,
                                                    align: "center",
                                                    formatter: treeFormatterZgbm
                                                },
                                                {
                                                    field: "xmlx",
                                                    title: "项目类型",
                                                    width: 100,
                                                    align: "center",
                                                    formatter: treeFormatterXmxmlx
                                                },
                                                {
                                                    field: "jsdw",
                                                    title: "建设单位",
                                                    width: 100,
                                                    align: "center",
                                                    formatter: treeFormatterJsdw
                                                },
                                                {field: "ydmj", title: "用地面积(亩)", width: 100, align: "center"},
                                                {field: "tzxe", title: "投资限额(万元)", width: 100, align: "center"},
                                                {field: "xmztz", title: "项目总投资(万元)", width: 100, align: "center"},
                                                {field: "isjs", title: "项目是否已决算", width: 100, align: "center",formatter: Formatterisjs},
                                            ],
                                            "url": "xmzjlydj/getZjly.do",
                                            queryParams: queryParams,//查询参数方法
                                            pageNumber: 1,//起始页
                                            pageSize: 20,//页面大小
                                            showRefresh:false,
                                            pagination:true,
                                            paginationHAlign: 'left',//分页按钮位置  left/right
                                            sidePagination: 'server',
                                            resizable:true,
                                            "toolbar": [
                                                {
                                                    "name": "新增",
                                                    "classes": "btn bootstrap-table-add",
                                                    "type": "button",
                                                    "onclick": "addRow()"
                                                },
                                                {
                                                    "name": "删除",
                                                    "classes": "btn bootstrap-table-delete",
                                                    "type": "button",
                                                    "onclick": "delRow()"
                                                },
                                                {
                                                    "name": "编辑/查看",
                                                    "classes": "btn bootstrap-table-edit",
                                                    "type": "button",
                                                    "onclick": "editRow()"
                                                },

                                            ],
                                            "clickToSelect": true
//                                             searchbar:{
//                                                 rownum:2,
//                                                 labelwidth: "70px",
//                                                 "inputs":[
//                                                     {title:"资金来源对应的项目是否已决算", name:"isjs", type:"comboBox",id:"xmmcid",localdata:[{id:0,text:'未决算'},{id:1,text:'已决算'}]},
                                                   
//                                                 ]
//                                             }

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
                                "plug": [
                                    {
                                        "dtype": "html",
                                        "dragHtml": '<ul class="nav nav-tabs" role="tablist">' +
                                        '    <li role="presentation" class="active"><a href="#infoTab1" aria-controls="infoTab1" role="tab" data-toggle="tab">项目资金来源登记</a></li>' +
                                        '    <li role="presentation"><a href="#infoTab2" aria-controls="infoTab2" role="tab" data-toggle="tab" onclick="getXmqqsh()">项目前期策划信息</a></li>' +
                                        '</ul>'
                                    },
                                    {
                                        "dtype": "div",
                                        "classes": "tab-content",
                                        "plug": [
                                            {
                                                "dtype": "div",
                                                "classes": "tab-pane active",
                                                "attrs": {role: "tabpanel", id: "infoTab1"},
                                                "plug": [
                                                    {
                                                        "dtype": "html",
                                                        "dragHtml": "<h4 class='text-center'><b>项目资金来源登记<b></h4>"
                                                    },
                                                    {
                                                        "dtype": "html",
                                                        "dragHtml": '<div class="toolbar layout_toolbar clearfix">' +
                                                        '<button class="bootstrap-table-save" type="button" id="saveZjlys" onclick="saveZjly()">保存</button>' +
                                                        '<button class="bootstrap-table-submit" type="button" id="zbks" onclick="zbk()">标准库</button>' +
                                                        '<span class="pull-right">单位：万元</span>' +
                                                        '</div>'
                                                    },
                                                    {
                                                        "dtype": "dform",
                                                        "id": "openForm",
                                                        "classes": "tableForm",
                                                        "rownum": 3,
                                                        "labelwidth": "200px",
                                                        "labelAlign": "center",
                                                        "inputs": [

                                                            {title: "项目id", name: "id", type: "hidden"},
                                                            {title: "id", name: "ide", type: "hidden"},
                                                            {title: "xmqqchid", name: "xmqqchguid", type: "hidden"},
                                                            {
                                                                title: "项目名称",
                                                                name: "xmmc",
                                                                required:true,
                                                                type: "textBox",
                                                                id: "mmm",
                                                                onLoaded: function($el){
                                                                    openProj($el, function (node) {
                                                                        ywid = node.id;
                                                                        ddid = node.id;
                                                                        $("#openForm").dform("setValueByName", "id", node.id);
                                                                        $("#openForm").dform("setValueByName", "xmmc", node.name);
                                                                        selxmid=node.id;
                                                                        xmid = node.id;
                                                                        getGsxxByXmid(node.id);
                                                                    }, "Zjlydj");
                                                                }
//                                                                 textField: 'xmmc',
//                                                                 valueField: 'id',
//                                                                 url: "xmzjlydj/selectXmmc.do",
//                                                                 onChange: function (newValue, oldValue, itemData) {
//                                                                     selxmid = newValue;
//                                                                     (newValue);
//                                                                 },
//                                                                 formatter: xsgcXmmc
                                                            },
                                                            {
                                                                title: '建设单位',//表单lable显示名
                                                                type: 'dsearchtree',//表单类型：目前支持 select/text
                                                                // placeholder: '请选择建设单位',
                                                                searchOption: true,
                                                                id: 'depart',
                                                                required:true,
                                                                name: 'jsdw',
                                                                checkType: 'radio',
                                                                url: 'xmzjlydj/getJSDWTree.do',
                                                                onAckCallback: function (nodes) {
                                                                    if (nodes.length > 0) {
                                                                        $('#depart').searchTree("setValue", nodes[0].id);
                                                                    }
                                                                }
                                                            },
                                                            {
//                                                title: '项目主管部门',//表单lable显示名
                                                                type: 'hidden',
//                                                placeholder: '请选择主管部门',
                                                                searchOption: true,
                                                                id: 'batch',
                                                                name: 'zgbm',
                                                                checkType: 'radio',
                                                                url: 'xmqqch/getZGBMTree.do',
                                                                onAckCallback: function (nodes) {
                                                                    if (nodes.length > 0) {
                                                                        $('#batch').searchTree("setValue", nodes[0].id);
                                                                    }
                                                                }
                                                            },
                                                            {title: "项目规划选址", name: "xmghxz",required:true, type: "textBox"},
                                                            {
                                                                title: "项目总投资（万元）",
                                                                name: "xmztz",
                                                                type: "textBox",
                                                                disabled: "disabled",
                                                                setReadonly: true
                                                            },
                                                            {
                                                                title: '项目类型',//表单lable显示名
                                                                type: 'dsearchtree',//表单类型：目前支持 select/text
                                                                placeholder: '项目类型',
                                                                searchOption: true,
                                                                required:true,
                                                                id: 'xmlx',
                                                                name: 'xmlx',
                                                                checkType: 'radio',
                                                                url: 'xmqqch/getXMLXTree.do',
                                                                onAckCallback: function (nodes) {

                                                                    if (nodes.length > 0) {
                                                                        $('#xmlx').searchTree("setValue", nodes[0].id);
                                                                    }
                                                                }
                                                            },
                                                            {title: "容积率", name: "rjl",textAlign:'right', type: "decimal"},
                                                            {title: "用地面积(平方米)", name: "ydmj",textAlign:'right', type: "decimal"},
                                                            {title: "建筑总面积（平方米）", name: "jzzmj",textAlign:'right', type: "decimal"},
                                                            {title: "其中：地上建筑面积(平方米)", name: "dsjzmj", textAlign:'right',type: "decimal"},
                                                            {title: "项目里程（公里）", name: "xmlc",textAlign:'right', type: "decimal"},
                                                            {title: "道路宽度(米)", name: "dlkd", textAlign:'right',type: "decimal"},
                                                            {
                                                            	title: "计量单位(造价标准)",
                                                            	name: "jldw", 
                                                            	type: "comboBox",
                                                            	id:"jldw",
                                                            	valueField:"id",
                                                                textField:"text",
                                                            	url:"xmqqch/selectJldw.do",
                                                            	required:true
//                                                             	
                                                            	},
//                                                             {title: "计量单位(造价标准)", name: "jldw", type: "textBox"},
                                                            {title: "投资限额(万元)", name: "tzxe",textAlign:'right', type: "textBox"},
                                                            {title: "建议控制造价标准(元)", name: "jykzzjbz", textAlign:'right',type: "decimal"},
                                                            {title: "项目单位造价标准(元)", name: "xmdwzj",textAlign:'right', type: "decimal"},
                                                            {
                                                                title: "建设内容",
                                                                name: "jsnr",
                                                                type: "textBox",
                                                                multiline: true,
                                                                colspan: 3
                                                            },
                                                            {
                                                                title: "项目总投资",
                                                                name: "",
                                                                type: "html",
                                                                rowspan: 2,
                                                                html: '<div class="customLabel text-center"><label class="control-label">合计</label></div><div class="customInput text-center"><input type="textBox" name="ztzHj" style="text-align: right" readonly="true" ></div>'
                                                            },
                                                            {
                                                                title: '<div class="customLabel"><label class="control-label">建筑安装投资</label></div><div class="customInput"><input type="decimal" name="ztzJzaztz" style="text-align: right" onchange="sum()" oninput="sum()" onblur="sum()"></div>',
                                                                name: "",
                                                                type: "html",
                                                                rowspan: 2,
                                                                html: '<div class="customLabel"><label class="control-label">设备投资</label></div><div class="customInput"><input type="decimal" name="ztzSbtz" style="text-align: right" onchange="sum()" oninput="sum()" onblur="sum()"></div>'
                                                            },
                                                            {
                                                                title: '<div class="customLabel"><label class="control-label">待摊投资</label></div><div class="customInput"><input type="decimal" name="ztzDttz" style="text-align: right" onchange="sum()" oninput="sum()" onblur="sum()"></div>',
                                                                name: "",
                                                                type: "html",
                                                                rowspan: 2,
                                                                html: '<div class="customLabel"><label class="control-label">其他投资</label></div><div class="customInput" ><input type="decimal" name="ztzQttz" style="text-align: right" onchange="sum()" oninput="sum()" onblur="sum()"></div>'
                                                            },

                                                            {
                                                                title: "项目资金来源",
                                                                name: "",
                                                                type: "html",
                                                                rowspan: 2,
                                                                html: '<div class="customLabel text-center"><label class="control-label">合计</label></div><div class="customInput text-center"><input type="textBox" name="xmzjlyHj" style="text-align: right" readonly="true" ></div>'
                                                            },
                                                            {
                                                                title: '<div class="customLabel"><label class="control-label">财政性资金</label></div><div class="customInput"><input type="decimal" name="xmzjlyCzxzj" style="text-align: right" onchange="sumXm()" oninput="sumXm()" onblur="sumXm()"></div>',
                                                                name: "",
                                                                type: "html",
                                                                rowspan: 2,
                                                                html: '<div class="customLabel"><label class="control-label">资源平衡</label></div><div class="customInput"><input type="decimal" name="xmzjlyZyph" style="text-align: right" onchange="sumXm()" oninput="sumXm()" onblur="sumXm()"></div>'
                                                            },
                                                            {
                                                                title: '<div class="customLabel"><label class="control-label">其他</label></div><div class="customInput"><input type="decimal" name="xmzjlyQt" style="text-align: right" onchange="sumXm()" oninput="sumXm()" onblur="sumXm()"></div>',
                                                                name: "",
                                                                type: "html",
                                                                rowspan: 2,
                                                                html: '<div class="customLabel"><label class="control-label">--</label></div><div class="customInput text-center">--</div>'
                                                            }
                                                        ]
                                                    },
                                                    {
                                                        "dtype":"html",
                                                        "dragHtml":'<div style="padding:20px 0 10px 15px;"><b>附件列表：</b><button type="button" class="bootstrap-table-upload" id="pageUploadFiles" onclick="pageUploadFile()">上传附件</button>&nbsp;<button type="button" class="bootstrap-table-delete" id="deleteFileId" onclick="deleteFile()">删除附件</button></div>'
                                                    },
                                                    {
                                                        "dtype": "dtable",
                                                        "height": 250,
                                                        "id":"uploadTable",
                                                        editable :true,
                                                        "clickToSelect": true,
                                                       "url": "xmqqch/getXmqqchfj.do",
                                                        "columns": [
                                                        	{radio: true, width: 20},
                                                            {field : 'FILEID',title : 'guid',visible : false},
                                                            {field:"xh", title:"序号", width:40, align:"center", formatter:function(value,row,index){return index+1;}},
                                                            {field:"FILENAME", title:"附件名", width:300, align:"center"},
                                                            {field:"FILEDL", title:"附件大类", width:120, align:"center",type:'comboBox',selected: 1,localdata:[{id:1,text:"资金来源登记"}],formatter:function(value,row,index){return "资金来源登记";}},
//                                             {field:"filexl", title:"附件小类", width:120, align:"center",editor:{type:'comboBox',localdata:[{id:701,text:"类别1"},{id:702,text:"类别2"}]}},
                                                            {field:"FILESIZE", title:"大小", width:80, align:"center",formatter: function (value, row, index) {
                                                                return formatSize(value);
                                                            }},
//                                             {field:"filebz", title:"备注", width:80, align:"center",editor:{type:'textBox'}}
                                                            {
                                                                field : '',
                                                                title : '操作 ',
                                                                width : 100,
                                                                align : 'center',
                                                                formatter : function(value, row, index) {
                                                                    return '<a href='+getRootPath()+'/file/downloadByid.do?id='+row.FILEID+'>下载</a>';
                                                                }
                                                            }
                                                        ]
                                                    }
                                                ],
                                                "colspan": "12",
                                                "dtype": "column"
                                            },
                                            {

                                                "dtype": "div",
                                                "classes": "tab-pane",
                                                "attrs": {role: "tabpanel", id: "infoTab2"},
                                                "plug": [
                                                    {
                                                        "dtype": "html",
                                                        "dragHtml": "<h4 class='text-center'><b>项目前期策划信息</b></h4>"
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
															{
															    title: "项目guid",
															    name: "GUIDS",
															    type: "hidden"

															},
                                                            {
                                                                title: "项目名称",
                                                                name: "XMMC",
                                                                type: "textBox",
                                                                disabled: "true",
                                                                setReadonly: true
                                                            },
                                                            {
                                                                title: '建设单位',//表单lable显示名
                                                                type: 'dsearchtree',//表单类型：目前支持 select/text
                                                                // placeholder: '请选择建设单位',
                                                                searchOption: true,
                                                                id: 'dpe',
                                                                name: 'JSDW',
                                                                checkType: 'radio',
                                                                url: 'xmzjlydj/getJSDWTree.do',
                                                                disabled: "true",
                                                                setReadonly: true,
                                                                onAckCallback: function (nodes) {

                                                                    if (nodes.length > 0) {
                                                                        $('#dpe').searchTree("setValue", nodes[0].id);
                                                                    }
                                                                }
                                                            },
                                                            {
                                                                title: '项目主管部门',//表单lable显示名
                                                                type: 'dsearchtree',//表单类型：目前支持 select/text
                                                                // placeholder: '请选择主管部门',
                                                                searchOption: true,
                                                                id: 'zg',
                                                                name: 'ZGBM',
                                                                checkType: 'radio',
                                                                url: 'pro/getAllZGBMTree.do',
                                                                disabled: "true",
                                                                setReadonly: true,
                                                                onAckCallback: function (nodes) {
                                                                    if (nodes.length > 0) {
                                                                        $('#zg').searchTree("setValue", nodes[0].id);
                                                                    }
                                                                }
                                                            },
                                                            {
                                                                title: "项目规划选址",
                                                                name: "XMGHXZ",
                                                                type: "textBox",
                                                                disabled: "true",
                                                                setReadonly: true
                                                            },
                                                            {
                                                                title: '项目类型',//表单lable显示名
                                                                type: 'dsearchtree',//表单类型：目前支持 select/text
                                                                // placeholder: '请选择项目类型',
                                                                searchOption: true,
                                                                id: 'xml',
                                                                name: 'XMLX',
                                                                checkType: 'radio',
                                                                url: 'pro/getXMLXTree.do',
                                                                disabled: "true",
                                                                setReadonly: true,
                                                                onAckCallback: function (nodes) {
                                                                    if (nodes.length > 0) {
                                                                        $('#xml').searchTree("setValue", nodes[0].id);
                                                                    }
                                                                }
                                                            },
                                                            {
                                                                title: "容积率",
                                                                name: "RJL",
                                                                type: "textBox",
                                                                textAlign:'right',
                                                                disabled: "true",
                                                                setReadonly: true
                                                            },
                                                            {
                                                                title: "用地面积(平方米)",
                                                                name: "YDMJ",
                                                                type: "textBox",
                                                                textAlign:'right',
                                                                disabled: "true",
                                                                setReadonly: true
                                                            },
                                                            {
                                                                title: "建筑总面积（平方米）",
                                                                name: "JZZMJ",
                                                                type: "textBox",
                                                                textAlign:'right',
                                                                disabled: "true",
                                                                setReadonly: true
                                                            },
                                                            {
                                                                title: "其中：地上建筑面积（平方米）",
                                                                name: "DSJZMJ",
                                                                type: "textBox",
                                                                textAlign:'right',
                                                                disabled: "true",
                                                                setReadonly: true
                                                            },
                                                            {
                                                                title: "项目里程（公里）",
                                                                name: "XMLC",
                                                                type: "textBox",
                                                                textAlign:'right',
                                                                disabled: "true",
                                                                setReadonly: true
                                                            },
                                                            {
                                                                title: "道路宽度(米)",
                                                                name: "DLKD",
                                                                type: "textBox",
                                                                textAlign:'right',
                                                                disabled: "true",
                                                                setReadonly: true
                                                            },
                                                            {
                                                                title: "联系电话",
                                                                name: "LXDH",
                                                                type: "textBox",
                                                                disabled: "true",
                                                                setReadonly: true
                                                            },
                                                            {
                                                                title: "项目单位造价(元)",
                                                                name: "XMDWZJ",
                                                                type: "textBox",
                                                                textAlign:'right',
                                                                disabled: "true",
                                                                setReadonly: true
                                                            },
                                                            {
                                                                title: "同类型造价标准(元)",
                                                                name: "TLXZJBZ",
                                                                type: "textBox",
                                                                textAlign:'right',
                                                                disabled: "true",
                                                                setReadonly: true
                                                            },
                                                            {
                                                                title: "联系人",
                                                                name: "LXR",
                                                                type: "textBox",
                                                                disabled: "true",
                                                                setReadonly: true
                                                            },
                                                            {
                                                                title: "文件造价标准(元)",
                                                                name: "WJZJBZ",
                                                                type: "textBox",
                                                                textAlign:'right',
                                                                disabled: "true",
                                                                setReadonly: true
                                                            },
                                                            {
                                                            	title: "计量单位(造价标准)",
                                                            	name: "JLDW", 
                                                            	type: "comboBox",
                                                            	id:"jldw",
                                                            	valueField:"id",
                                                                textField:"text",
                                                            	url:"xmqqch/selectJldw.do",
                                                            	disabled: "true",
                                                                setReadonly: true
//                                                             	
                                                            	},
                                                            {
                                                                title: "建设内容",
                                                                name: "JSNR",
                                                                type: "textBox",
                                                                multiline: true,
                                                                colspan: 3,
                                                                disabled: "true",
                                                                setReadonly: true
                                                            },
                                                            {
                                                                title: "项目总投资",
                                                                name: "",
                                                                type: "html",
                                                                rowspan: 2,
                                                                html: '<div class="customLabel text-center"><label class="control-label">合计</label></div><div class="customInput text-center"><input type="textBox" name="ZTZHJ1" style="text-align: right" disabled="true"></div>'
                                                            },
                                                            {
                                                                title: '<div class="customLabel"><label class="control-label">建筑安装投资</label></div><div class="customInput"><input type="textBox" name="ZTZJZAZTZ1" style="text-align: right" disabled="true"></div>',
                                                                name: "",
                                                                type: "html",
                                                                rowspan: 2,
                                                                html: '<div class="customLabel"><label class="control-label">设备投资</label></div><div class="customInput"><input type="textBox" name="ZTZSBTZ1" style="text-align: right" disabled="true"></div>'
                                                            },
                                                            {
                                                                title: '<div class="customLabel"><label class="control-label">待摊投资</label></div><div class="customInput"><input type="textBox" name="ZTZDTTZ1" style="text-align: right" disabled="true"></div>',
                                                                name: "",
                                                                type: "html",
                                                                rowspan: 2,
                                                                html: '<div class="customLabel"><label class="control-label">其他投资</label></div><div class="customInput" ><input type="textBox" name="ZTZQTTZ1" style="text-align: right" disabled="true"></div>'
                                                            },

                                                            {
                                                                title: "项目资金来源",
                                                                name: "",
                                                                type: "html",
                                                                rowspan: 2,
                                                                html: '<div class="customLabel text-center"><label class="control-label">合计</label></div><div class="customInput text-center"><input type="textBox" name="XMZJLYHJ1" style="text-align: right" disabled="true"></div>'
                                                            },
                                                            {
                                                                title: '<div class="customLabel"><label class="control-label">财政性资金</label></div><div class="customInput"><input type="textBox" name="XMZJLYCZXZJ1" style="text-align: right" disabled="true"></div>',
                                                                name: "",
                                                                type: "html",
                                                                rowspan: 2,
                                                                html: '<div class="customLabel"><label class="control-label">资源平衡</label></div><div class="customInput"><input type="textBox" name="XMZJLYZYPH1" style="text-align: right" disabled="true"></div>'
                                                            },
                                                            {
                                                                title: '<div class="customLabel"><label class="control-label">其他</label></div><div class="customInput"><input type="textBox" name="XMZJLYQT1" style="text-align: right" disabled="true"></div>',
                                                                name: "",
                                                                type: "html",
                                                                rowspan: 2,
                                                                html: '<div class="customLabel"><label class="control-label">--</label></div><div class="customInput text-center">--</div>'
                                                            },
                                                            {
                                                                title: "项目评审建议",
                                                                name: "XMPHJY",
                                                                type: "textBox",
                                                                multiline: true,
                                                                colspan: 3,
                                                                colspan: 2,
                                                                disabled: "true",
                                                                setReadonly: true
                                                            },
                                                            /*   {
                                                             title: "项目评审建议",
                                                             name: "XMPHJY",
                                                             type: "html",
                                                             rowspan: 3,
                                                             colspan: 2
                                                             // html: '<div class="customInput"><input type="textBox" data-multiline="true" data-height="84" disabled="true"></div>'
                                                             ,disabled:"true",setReadonly:true
                                                             },*/
                                                            {
                                                                title: "投资限额",
                                                                name: "TZXE",
                                                                type: "textBox",
                                                                disabled: "true",
                                                                textAlign:'right',
                                                                setReadonly: true
                                                            },
                                                            {
                                                                title: "建议控制造价标准(元)",
                                                                name: "JYKZZJBZ",
                                                                type: "textBox",
                                                                disabled: "true",
                                                                textAlign:'right',
                                                                setReadonly: true
                                                            },
                                                            {
                                                                title: "核减金额",
                                                                name: "HJJE",
                                                                type: "textBox",
                                                                textAlign:'right',
                                                                disabled: "true",
                                                                setReadonly: true
                                                            }
                                                        ],
                                                        "url": "xmzjlydj/getXmqqsh.do",
                                                    },
                                                    {
                                                        "dtype": "html",
                                                        "dragHtml": '<div style="padding:20px 0 10px 15px;"><b>附件列表：</b></div>'
                                                    },
                                                    {
                                                        "dtype": "dtable",
                                                        "height": 250,
                                                        "id":"uploadTable1",
                                                        editable :true,
                                                        "url": "xmqqch/getXmqqchfj.do",
                                                        "columns": [
                                                            {field : 'FILEID',title : 'guid',visible : false},
                                                            {field:"xh", title:"序号", width:40, align:"center", formatter:function(value,row,index){return index+1;}},
                                                            {field:"FILENAME", title:"附件名", width:300, align:"center"},
                                                            {field:"FILEDL", title:"附件大类", width:120, align:"center",type:'comboBox',selected: 1,localdata:[{id:7,text:"变更联系单备案"}],formatter:function(value,row,index){return "变更联系单备案";}},
//                                             {field:"filexl", title:"附件小类", width:120, align:"center",editor:{type:'comboBox',localdata:[{id:701,text:"类别1"},{id:702,text:"类别2"}]}},
                                                            {field:"FILESIZE", title:"大小", width:80, align:"center",formatter: function (value, row, index) {
                                                                return formatSize(value);
                                                            }},
//                                             {field:"filebz", title:"备注", width:80, align:"center",editor:{type:'textBox'}}
                                                            {
                                                                field : '',
                                                                title : '操作 ',
                                                                width : 100,
                                                                align : 'center',
                                                                formatter : function(value, row, index) {
                                                                    return '<a href='+getRootPath()+'/file/downloadByid.do?id='+row.FILEID+'>下载</a>';
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


        var dopenZbk = [
            {
                "plug": [
                    {
                        "plug": [
                            {
                                "plug": [
                                    {
                                        "dtype": "html",
                                        // "dragHtml": "<h5 class='text-center'><b>项目标准库</b><span id='xmbhid'></span></h5>"
                                        "dragHtml": '<ul class="nav nav-tabs" role="tablist">' +
                                        '    <li role="presentation" class="active"><a href="#tab1" aria-controls="tab1" role="tab" data-toggle="tab">标准库</a></li>' +
                                        '    <li role="presentation"><a href="#tab2" aria-controls="tab2" role="tab" data-toggle="tab" onclick="getalk()">案例库</a></li>' +
                                        '  </ul>'
                                    },
                                    {
                                        "dtype": "div",
                                        "classes":"tab-content",
                                        plug:[
                                            {
                                                dtype:"div",
                                                classes:"tab-pane active",
                                                id:"tab1",
                                                styles:{role:"tabpanel"},
                                                plug:[
                                                    {
                                                        "dtype": "html",
                                                        "dragHtml": '<div class="toolbar layout_toolbar clearfix">' +
                                                        '<button class="bootstrap-table-save" type="button" onclick="select()">选择</button>' +
                                                        '</div>'
                                                    },

                                                    {
                                                        "dtype": "dtable",
                                                        "id": "zbkTeb",
                                                        clickToSelect:true,
                                                        "height": $(window).height() - 180,
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
                                                            {
                                                                field: 'YJZB', title: '一级指标', align: 'center', width: 120,
//                                                editor:{
                                                                type: "textBox"
//                                                }
                                                            },
                                                            {
                                                                field: 'EJZB', title: '二级指标', align: 'center', width: 120,
//                                                editor:{
                                                                type: "textBox"
//                                                }
                                                            },
                                                            {
                                                                field: 'SJZB', title: '三级指标', align: 'center', width: 150,
//                                                editor:{
                                                                type: "textBox"
//                                                }
                                                            },
                                                            {
                                                                field: 'DWTZE', title: '单位投资额', align: 'center', width: 140,
//                                                editor:{
                                                                type: "textBox"
                                                            },
                                                            {
                                                                field: 'JLDW', title: '计量单位', align: 'center', width: 140,
//                                                editor:{
                                                                type: "textBox"
                                                            },
                                                            {
                                                                field: 'ZBLY', title: '指标来源', align: 'center', width: 140,
//                                                editor:{
                                                                type: "textBox"
                                                            },

                                                            {
                                                                field: 'BZ', title: '备注', align: 'center', width: 80,
//                                                editor:{
                                                                type: "textBox"
                                                            },
                                                            {field: 'ZBLY', title: '指标类型', align: 'center', width: 140, visible: false},
                                                            {field: 'ZFRQ', title: '作废日期', align: 'center', width: 140, visible: false},
                                                            {field: 'CJSJ', title: '操作时间', align: 'center', width: 140, visible: false},
                                                            {field: 'CZR', title: '操作人', align: 'center', width: 140, visible: false}
                                                        ],
                                                        "url": "xmqqsh/getZbk.do",
                                                        queryParams: queryParams,//查询参数方法
                                                        pageNumber: 1,//起始页
                                                        pageSize: 10,//页面大小
                                                        pagination: false,
                                                        showRefresh: false,
                                                        paginationHAlign: 'left',//分页按钮位置  left/right
                                                        sidePagination: 'server',
                                                        searchbar: {
                                                            rownum: 3,//搜索栏表单列数  最大支持3
                                                            labelwidth: 150,
                                                            inputs: [//搜索栏表单参数
                                                                {
                                                                    title: '指标名称:',
                                                                    name: 'zbmc',
//                                                    url:getRootPath()+"/yhglggryqz/getsxList.do" ,
                                                                    type: 'textBox',
                                                                    multiple: false

                                                                }
//                                             ,
//                                                 {
//                                                     title: '指标来源:',
//                                                     name: 'zbly',
//                                                 }

                                                            ]
                                                        }
//                                        "url": "pro/getShyj.do"
                                                    },
                                                    {
                                                        "dtype": "html",
                                                        "dragHtml": '<div class="toolbar layout_toolbar clearfix">' +
                                                        '<button class="bootstrap-table-save" type="button" onclick="saveZbk()">保存</button>' +
                                                        '<button class="bootstrap-table-submit" type="button" onclick="delZbkRow()">删除</button>' +
                                                        '<button class="bootstrap-table-submit" type="button" onclick="PrintBzk()">打印</button>' +
                                                        '</div>'
                                                    },
                                                    {
                                                        "dtype": "dtable",
                                                        "id": "zbkSelect",
                                                        clickToSelect:true,
                                                        "height": $(window).height() - 180,
                                                        "columns": [
                                                            {field: "checkType", checkbox: true},
                                                            {field: 'ID', title: 'guid', align: 'center', width: 120, visible: false},
                                                            {
                                                                field: "DD",
                                                                title: "序号",
                                                                width: 40,
                                                                align: "center",
                                                                formatter: function (value, row, index) {
                                                                    return index + 1;
                                                                }
                                                            },
                                                            {
                                                                field: 'YJZB', title: '一级指标', align: 'center', width: 120,
                                                                //editor:{
                                                                type: "textBox"
                                                                //}
                                                            },
                                                            {
                                                                field: 'EJZB', title: '二级指标', align: 'center', width: 120,
                                                                //editor:{
                                                                type: "textBox"
                                                                // }
                                                            },
                                                            {
                                                                field: 'SJZB', title: '三级指标', align: 'center', width: 150,
                                                                // editor:{
                                                                type: "textBox"
                                                                // }
                                                            },
                                                            {
                                                                field: 'DWTZE', title: '单位投资额', align: 'center', width: 140,
                                                                // editor:{
                                                                type: "textBox"
                                                            },
                                                            {
                                                                field: 'JLDW', title: '计量单位', align: 'center', width: 140,
                                                                //editor:{
                                                                type: "textBox"
                                                            },
                                                            {
                                                                field: 'SL',
                                                                title: '数量',
                                                                align: 'center',
                                                                width: 140,
                                                                editor: {type: "decimal"},
                                                                required: true
                                                            },
                                                            {
                                                                field: 'XJ', title: '小计', align: 'center', width: 80,
                                                                // editor:{
                                                                type: "textBox",
                                                                footerFormatter:function(data){
                                                                    var sum=0;
                                                                    for(var i=0;i<data.length;i++){
                                                                        sum+=parseFloat(data[i].XJ);
                                                                    }
                                                                    return "<strong>合计："+sum+"</strong>";
                                                                }
                                                            },
                                                        ],
                                                        "url": "xmqqsh/getXmbzcs.do",
                                                        queryParams: queryParams,//查询参数方法
                                                        pageNumber: 1,//起始页
                                                        pageSize: 10,//页面大小
                                                        showFooter:true,
                                                        pagination: false,
                                                        showRefresh: false,
                                                        paginationHAlign: 'left',//分页按钮位置  left/right
                                                        sidePagination: 'server',
                                                        onLoadSuccess:function(data){
                                                            $("#zbkSelect").tableEditor("initAll");
                                                        }
                                                    }
                                                ]
                                            },
                                            {
                                                dtype:"div",
                                                classes:"tab-pane",
                                                id:"tab2",
                                                styles:{role:"tabpanel"},
                                                plug:[
//                                                     {
//                                                         dtype:"dform",
//                                                         rownum: 3,
//                                                         inputs: [
//                                                             {
//                                                                 title: '案例:',
//                                                                 name: '',
//                                                                 type: 'textBox'
//                                                             },
//                                                             {
//                                                                 title: '状态:',
//                                                                 name: '',
//                                                                 type: 'comboBox'
//                                                             },
//                                                             {
//                                                                 title: '',
//                                                                 name: '',
//                                                                 type: 'html',
//                                                                 html:'<button class="bootstrap-table-query" type="button" onclick="changeTh()">查询</button>'
//                                                             }
//                                                         ]
//                                                     },
                                                    {
                                                        "dtype": "html",
                                                        "dragHtml": '<div class="toolbar layout_toolbar clearfix">' +
                                                        '<button class="bootstrap-table-save" type="button" onclick="savealk()">保存</button>' +
//                                                         '<button class="bootstrap-table-delete" type="button" onclick="">关闭</button>' +
                                                        '</div>'
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
                                                        searchbar:{
                                                            rownum:2,
                                                            labelwidth: "70px",
                                                            "inputs":[
                                                                {title:"案例", name:"al", type:"textBox"},
                                                                {title:"状态", name:"zt", type:"comboBox",selected:1,localdata:[{id:0,text:'全部'},{id:1,text:'已选择'},{id:2,text:'未选择'}]}
                                                            ]
                                                        }
                                                    },
                                                ]
                                            }
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
        function changeTh(){
            var $table = $("#alkTable");
            var newColumns = [
                {field:"bm", title:"编码", width:100, align:"center"},
                {field:"mc", title:"名称", width:100, align:"center"},
                {field:"nf", title:"年份", width:100, align:"center"},
                {field:"zmj", title:"总面积", width:100, align:"center"},
                {field:"xmdwzj", title:"项目单位造价", width:100, align:"center"}
            ];
            $table.dtable("refreshOptions", {columns:newColumns});
        }
        function pageUploadFile() {
            uploadfile({server:getRootPath()+'/file/uploader.do?filebstype=13'}, function (rowData) {
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
        function queryParams(params) {
            return {
                //如果需要后端进行分页 limit 和offset是必须参数
                limit: params.limit,
                offset: params.offset,
                id: xmid
            }
        }

        function saveZjly() {
            var fileIds="";
            if(ztzhjz!=ztzzjlyz){
            	$.dalert({text:'总投资金额需要和资金来源金额相等，请检查！',icon:7});
            	return ;
            }
//            if (isEdit == 1) {
//                 $("#openForm").dform("setValueByName", "xmid", xmid);
//                 $("#openForm").dform("setValueByName", "id",selxmid);
//            }
            //dfrom表单数据
            var openFormData=$("#openForm").dform('getData');
            //附件数据
            $("#uploadTable").tableEditor("updateAll");
            var uploadTableData = $("#uploadTable").dtable("getData");
            $.each(uploadTableData,function (i,v) {
                if(v.filexl==''&&v.filebz==''&&v.fileid==''){
                    uploadTableData.splice(i,1);
                }
            });
            openFormData.ZxFile=uploadTableData;
           
            var dataArrss = $("#uploadTable").dtable("getData");
            for(i=0;i<dataArrss.length;i++){
                fileIds +=dataArrss[i].FILEID+",";
            }
            if( !$("#openForm").dform("validate")){
                return ;
            }

            $.dajax({
                url: "xmzjlydj/saveZjly.do",
                type: "POST",
                data: $.extend($("#openForm").dform('getData'),{"fileId":fileIds}),
                dataType: "json",
                success: function (data) {
                    if (data.success) {
                        $.dalert({text: '保存成功', icon: 1});
                        $("#openForm").dform("setValueByName","ide",data.content);
                        $("#teb").dtable("refresh");

                    }
                }
            });
        }


        function zbk() {
           if(!xmid || xmid === ""){
                $.dalert({text:"请先选择项目",icon:2});
                return;
           }
            $.dopen({
                type: 6,
                title: "标准库查询",
                area: ["95%", "100%"],
                content: dopenZbk,
                btn: ["关闭"],
                btn1: function (index) {
                    layer.close(index);
                    $("#teb").dtable("refresh");
                }
            });
        }
        function select() {
            var data = $("#zbkTeb").dtable("getSelections");
//            var data1 = $("#zbkSelect").dtable("getSelections");
            zbkId = data[0].ZBKID;
            var data1 = $("#zbkSelect").dtable('getData');
            data1.push.apply(data1,data);
            $("#zbkSelect").dtable('load',data1);
            // $("#zbkSelect").dtable('append', data);
            // try {
            $("#zbkSelect").tableEditor("initAll");
            // }catch (e) {

            // }
            removRow1();
        }

        function removRow1() {
            var $table = $("#zbkTeb");
            var selectData = getselectdata();
            if (selectData != undefined) {
                {
                    var tableData = $table.dtable("getData");
                    $table.tableEditor("updateAll");
                    $.each(selectData, function (i) {
                        var dataIndex = tableData.indexOf(selectData[i]);
                        $table.dtable("removeByRowIndex", dataIndex);
                    });
                    $table.tableEditor("updateAll");

                    var dataLen = $table.dtable("getData").length;
                    $table.tableEditor("initAll");

//                    layer.msg('删除成功', {icon: 1});
                }
            }
        }

        function getselectdata() {
            var dataArr = $("#zbkTeb").dtable("getSelections");
            if (dataArr.length === 0) {
                $.dalert({text: "请选择数据", icon: 7});
                return;
            }
            return dataArr;
        }
        function saveZbk() {
            var zbkId='';
            var sl='';
            var data = $("#zbkSelect").dtable("getData");
            $("#zbkSelect").tableEditor('updateAll');
//            var data1 = $("#zbkSelect").dtable("getData");

            if (data.length == 0) {
                $.dalert({text: '没有数据可以保存', icon: 7});
                return;
            }
            if(data.length>0) {
                for (var i = 0; i < data.length; i++) {
                    zbkId += data[i].ZBKID + ",";
                    sl += data[i].SL + ",";
                }
            }
            xmid = selxmid;
                $.dajax({
                    url: "xmqqsh/saveZbk.do",
                    type: "POST",
                    data: {
                        idZftzXm:selxmid,
                        idZftzZbk:zbkId,
                        sl:sl
                    },
                    success: function (data) {
                        if (data.success) {
                            $.dalert({text:'保存成功',icon:1});
                            $('#zbkSelect').dtable('refresh');
                        }else{
                            $.dalert({text:data.content,icon:1});
                        }
                    }
                });
        }

        function delZbkRow(){
            var XMZJYLDJ="";
            var $table = $("#zbkSelect");
            var selectData = $("#zbkSelect").dtable("getSelections");
         
            if(selectData != undefined){
                layer.confirm('确定删除吗？', {
                    btn: ['确定','取消'] //按钮
                }, function(){
                    for(i=0;i<selectData.length;i++){
                        if(selectData[i].XMBZCSID==undefined){
                            XMZJYLDJ += "-1" + ",";
                        }else{
                            XMZJYLDJ += selectData[i].XMBZCSID + ",";
                        }
                    }
                    $.dajax({
                        cache : true,
                        type : "POST",
                        url:"xmqqsh/delZbk.do",
                        data:$.param({ids:XMZJYLDJ}),
                        success : function(data) {
                            if (data.success) {
                                $.dalert({text:data.content, icon: 1});
                                // var rowIndex = $table.find("tr.editing").data("index");
                                // $table.tableEditor("cancel", rowIndex);
                                var oldData = $('#zbkTeb').dtable('getData');
                                oldData.push.apply(oldData, selectData);
                                $('#zbkTeb').dtable('load', oldData);
                            } else {
                                $.dalert({text:data.content, icon: 2});
                                var rowIndex = $table.find("tr.editing").data("index");
                                $table.tableEditor("cancel", rowIndex);
                                $('#zbkTeb').dtable('refresh');
                            }
                        }
                    });
                    $table.tableEditor("updateAll");
                    var tableData = $table.dtable("getData");
                    $.each(selectData, function(i){
                        var dataIndex = tableData.indexOf(selectData[i]);
                        $table.dtable("removeByRowIndex", dataIndex);
                    });
                    $table.dtable("load",tableData);

                    $table.tableEditor("initAll");
                });
            }
        }


        function delRow() {
            var ids = '';
            var data = $("#teb").dtable("getSelections");
            if (data.length == 0) {
                $.dalert({text: '请选择数据', icon: 7});
                return;
            }
            if (data.length > 0) {
                for (var i = 0; i < data.length; i++) {
                    ids += data[i].ide + ",";
                    if(data[i].isjs==1){
                    	$.dalert({text: '项目中包含已决算项目，不允许删除！', icon: 7});
                    	return false;
                    }   
                }
            }
           
            layer.confirm('确定删除吗？', {
                btn: ['确定', '取消'] //按钮
            }, function () {
                $.dajax({
                    type: 'post',
                    url: "xmzjlydj/delXmzjlydj.do",
                    data: {ids: ids.substring(0, ids.length - 1)},
                    dataType: 'json',
                    success: function (data) {
                        if (data.success) {
                            $.dalert({text: '删除成功', icon: 1});
                            $('#teb').dtable('refresh');
                        } else {
                            return $.dalert({text: '删除失败', icon: 1});
                        }
                    }
                })
            });
        }

        function addRow() {
            isEdit = 0;
            dopenIndex=$.dopen({
                type: 6,
                title: "项目资金来源登记",
                area: ["95%", "100%"],
                content: dopenJson,
                btn: ["关闭"],
                btn1: function (index) {
                    layer.close(index);
                    $("#teb").dtable("refresh");

                }
            });
            xmid = null;
            $(".customInput input").each(function () {
                var $inp = $(this),
                        dtype = $inp.attr("type"),
                        options = $inp.data();
                $inp[dtype](options);
            })
        }

        function disableButton(arr) {
	        $.each(arr,function (i,v) {
	            $('#'+v).attr('disabled',true);
	        })
	    }
        
        function editRow() {
            isEdit = 1;
            var data = $("#teb").dtable("getSelections");
            if (data.length == 0) {
                $.dalert({text: '请选择数据', icon: 7});
                return;
            } else if (data.length > 1) {
                $.dalert({text: '最多只能选择一行数据', icon: 7});
                return;
            } else {

                xmid = data[0].xmid;
                selxmid= data[0].xmid;
// 				alert(xmid);
                $.dopen({
                    type: 6,
                    title: "项目资金来源登记",
                    area: ["95%", "100%"],
                    content: dopenJson,
                    btn: ["关闭"],
                    btn1: function (index) {
                        layer.close(index);
                        $("#teb").dtable("refresh");

                    },
                });
             
                if(data[0].isjs==1){
                	disableButton(['saveZjlys','zbks',"pageUploadFiles","deleteFileId"]);
                }
                
                $(".customInput input").each(function () {
                    var $inp = $(this),
                            dtype = $inp.attr("type"),
                            options = $inp.data();
                    $inp[dtype](options);
                });
                setTimeout(function () {
                    var jzaztz = $("input[name=ztzJzaztz]").parents(".ztreeBox_inp").find(".form-control")["decimal"]("setValue", data[0].ztz_jzaztz);
                    var sbtz = $("input[name=ztzSbtz]").parents(".ztreeBox_inp").find(".form-control")["decimal"]("setValue", data[0].ztz_sbtz);
                    var dttz = $("input[name=ztzDttz]").parents(".ztreeBox_inp").find(".form-control")["decimal"]("setValue", data[0].ztz_dttz);
                    var qttz = $("input[name=ztzQttz]").parents(".ztreeBox_inp").find(".form-control")["decimal"]("setValue", data[0].ztz_qttz);
                    var czxzj = $("input[name=xmzjlyCzxzj]").parents(".ztreeBox_inp").find(".form-control")["decimal"]("setValue", data[0].xmzjly_czxzj);
                    var zyph = $("input[name=xmzjlyZyph]").parents(".ztreeBox_inp").find(".form-control")["decimal"]("setValue", data[0].xmzjly_zyph);
                    var qt = $("input[name=xmzjlyQt]").parents(".ztreeBox_inp").find(".form-control")["decimal"]("setValue", data[0].xmzjly_qt);
                    var result = parseFloat(data[0].ztz_jzaztz) + parseFloat(data[0].ztz_sbtz) + parseFloat(data[0].ztz_dttz) + parseFloat(data[0].ztz_qttz);
                    var result1 = parseFloat(data[0].xmzjly_czxzj) + parseFloat(data[0].xmzjly_zyph) + parseFloat(data[0].xmzjly_qt);
                    if(isNaN(result)==true){
                        $("input[name=ztzHj]").parents(".ztreeBox_inp").find(".form-control")["textBox"]("setValue", "");
                        $("input[name=xmztz]").parents(".ztreeBox_inp").find(".form-control")["textBox"]("setValue", "");
                        ztzhjz=0;
                    }else{
                        $("input[name=ztzHj]").parents(".ztreeBox_inp").find(".form-control")["textBox"]("setValue", result.toFixed(2));
                        $("input[name=xmztz]").parents(".ztreeBox_inp").find(".form-control")["textBox"]("setValue", result.toFixed(2));
                        ztzhjz=result.toFixed(2);
                    }
                    if(isNaN(result1)==true){
                        $("input[name=xmzjlyHj]").parents(".ztreeBox_inp").find(".form-control")["textBox"]("setValue","");
                        ztzzjlyz=0;
                    }else{
                        $("input[name=xmzjlyHj]").parents(".ztreeBox_inp").find(".form-control")["textBox"]("setValue",result1.toFixed(2));
                        ztzzjlyz=result1.toFixed(2);
                    }
                }, 0);
                var guid=data[0].ide;
                var guids=data[0].xmqqchguid;
                $("#uploadTable").dtable("refreshOptions",{url:"xmqqch/getXmqqchfj.do?lx=13&guid="+guid});
                $("#openForm").dform('load', data[0]);
//                var guids = $("#teb").dtable("getSelections").GUIDS;
//                var guids=$("#xmqqch").dform("getData").GUIDS;
                $("#uploadTable1").dtable("refreshOptions",{url:"xmqqch/getXmqqchfj.do?lx=1&guid="+guids});
                $("#teb").dtable("refresh");
            }
        }
        function clearForm() {
            var formObj = $("#openForm");
            formObj.dform("clear");
            formObj.find(".customInput .form-control").each(function () {
                var $input = $(this),
                        dtype = $input.parents(".ztreeBox_inp").find(".valueBox").attr("boxtype");
                $input[dtype]("clear");
            })
        }

        function getXmqqsh() {
//             $("#openForm").dform("setValueByName", "xmid",selxmid);

            if (selxmid != undefined && selxmid != "") {
                $.dajax({
                    url: "xmzjlydj/getXmqqsh.do",
                    data: {
                        'id': selxmid,
                    },
                    cache: true,
                    type: "POST",
                    dataType: "json",
                    success: function (data) {

                        $("#xmqqch").dform("load", data[0]);



                        $("#uploadTable1").dtable("refreshOptions",{url:"xmqqch/getXmqqchfj.do?lx=1&guid="+data[0].GUIDS});
                    }
                });
            } else {
                return $.dalert("选择单位才能显示项目前期策划数据");
            }
        }

        function getGsxxByXmid(xmid) {
            
            $.dajax({
                type: 'post',
                url: "xmzjlydj/getGsxxByXmid.do",
                data: {
                    xmid: xmid
                },
                dataType: 'json',
                success: function (data) {
                    $("#openForm").dform("setValueByName", "xmlx", data.xmlx);
                    $("#openForm").dform("setValueByName", "jsdw", data.jsdw);
                }
            })
            $.dajax({
                type: 'post',
                url: "xmzjlydj/getXmqqch.do",
                data: {
                    xmid: xmid
                },
                dataType: 'json',
                success: function (data) {
                	$("#openForm").dform("setValueByName", "xmghxz", data[0].xmghxz);
                	$("#openForm").dform("setValueByName", "rjl", data[0].rjl);
                	$("#openForm").dform("setValueByName", "ydmj", data[0].ydmj);
                	$("#openForm").dform("setValueByName", "jzzmj", data[0].jzzmj);
                	$("#openForm").dform("setValueByName", "dsjzmj", data[0].dsjzmj);
                	$("#openForm").dform("setValueByName", "xmlc", data[0].xmlc);
                	$("#openForm").dform("setValueByName", "dlkd", data[0].dlkd);
                	$("#openForm").dform("setValueByName", "jldw", data[0].jldw);
                	$("#openForm").dform("setValueByName", "tzxe", (data[0].tzxe / 10000).toFixed(2));
                	$("#openForm").dform("setValueByName", "jykzzjbz", (data[0].jykzzjbz==null?0:data[0].jykzzjbz).toFixed(2));
                	$("#openForm").dform("setValueByName", "xmdwzj", data[0].xmdwzj);
                	$("#openForm").dform("setValueByName", "jsnr", data[0].jsnr);

//                          $("#openForm").dform("load", data[0]);
                        setTimeout(function () {
                            var jzaztz = $("input[name=ztzJzaztz]").parents(".ztreeBox_inp").find(".form-control")["decimal"]("setValue", (data[0].ztz_jzaztz / 10000).toFixed(2));
                            var sbtz = $("input[name=ztzSbtz]").parents(".ztreeBox_inp").find(".form-control")["decimal"]("setValue", (data[0].ztz_sbtz / 10000).toFixed(2));
                            var dttz = $("input[name=ztzDttz]").parents(".ztreeBox_inp").find(".form-control")["decimal"]("setValue", (data[0].ztz_dttz / 10000).toFixed(2));
                            var qttz = $("input[name=ztzQttz]").parents(".ztreeBox_inp").find(".form-control")["decimal"]("setValue", (data[0].ztz_qttz / 10000).toFixed(2));
                            var czxzj = $("input[name=xmzjlyCzxzj]").parents(".ztreeBox_inp").find(".form-control")["decimal"]("setValue", (data[0].xmzjly_czxzj / 10000).toFixed(2));
                            var zyph = $("input[name=xmzjlyZyph]").parents(".ztreeBox_inp").find(".form-control")["decimal"]("setValue", (data[0].xmzjly_zyph / 10000).toFixed(2));
                            var qt = $("input[name=xmzjlyQt]").parents(".ztreeBox_inp").find(".form-control")["decimal"]("setValue", (data[0].xmzjly_qt / 10000).toFixed(2));
                            var result = (parseFloat(data[0].ztz_jzaztz) + parseFloat(data[0].ztz_sbtz) + parseFloat(data[0].ztz_dttz) + parseFloat(data[0].ztz_qttz)) / 10000;
                            var result1 = (parseFloat(data[0].xmzjly_czxzj) + parseFloat(data[0].xmzjly_zyph) + parseFloat(data[0].xmzjly_qt)) / 10000;
                            if (isNaN(result) == true) {
                                $("input[name=ztzHj]").parents(".ztreeBox_inp").find(".form-control")["textBox"]("setValue", "");
                                $("input[name=xmztz]").parents(".ztreeBox_inp").find(".form-control")["textBox"]("setValue", "");
                                ztzhjz=0;
                            } else {
                                $("input[name=ztzHj]").parents(".ztreeBox_inp").find(".form-control")["textBox"]("setValue", result.toFixed(2));
                                $("input[name=xmztz]").parents(".ztreeBox_inp").find(".form-control")["textBox"]("setValue", result.toFixed(2));
                                ztzhjz=result.toFixed(2);
                            }
                            if (isNaN(result1) == true) {
                                $("input[name=xmzjlyHj]").parents(".ztreeBox_inp").find(".form-control")["textBox"]("setValue", "");
                                ztzzjlyz=0;
                            } else {
                                $("input[name=xmzjlyHj]").parents(".ztreeBox_inp").find(".form-control")["textBox"]("setValue", result1.toFixed(2));
                                ztzzjlyz=result1.toFixed(2);
                            }
                        }, 0);
                    }
            })
        }

        function xsgcXmmc(value, row, index) {
            var treeIdKey = "id", treeNameKey = "xmmc", res = "";
            $.each(xmmcData, function (key, val) {
                if (val[treeIdKey] == value) {
                    res = val[treeNameKey];
                    return res;
                }
            });
            return res;
        }
        //获得建设单位树
        function treeFormatterJsdw(value, row, index) {
            var treeIdKey = "ID", treeNameKey = "MC", res = "";
            $.each(jsdw, function (key, val) {
                if (val[treeIdKey] == value) {
                    res = val[treeNameKey];
                    return false;
                }
            });
            return res;
        }
        //获得项目类型树
        function treeFormatterXmxmlx(value, row, index) {
            var treeIdKey = "BM", treeNameKey = "MC", res = "";
            $.each(xmlx, function (key, val) {
                if (val[treeIdKey] == value) {
                    res = val[treeNameKey];
                    return false;
                }
            });
            return res;
        }

        //主管类型树
        function treeFormatterZgbm(value, row, index) {
            var treeIdKey = "ID", treeNameKey = "MC", res = "";
            $.each(zgbm, function (key, val) {
                if (val[treeIdKey] == value) {
                    res = val[treeNameKey];
                    return false;
                }
            });
            return res;
        }
        
        function Formatterisjs(value){
        	switch (value){
	        	case 0:
	                return "项目未决算";
	            case 1:
	            	return "项目已决算";
        	}
        }


        function sum() {
            var jzaztz = $("input[name=ztzJzaztz]").val() || 0;
            var sbtz = $("input[name=ztzSbtz]").val() || 0;
            var dttz = $("input[name=ztzDttz]").val() || 0;
            var qt = $("input[name=ztzQttz]").val() || 0;
            var result = parseFloat(jzaztz) + parseFloat(sbtz) + parseFloat(dttz) + parseFloat(qt);
            $("input[name=ztzHj]").prev().val(result.toFixed(2));
            $("input[name=xmztz]").prev().val(result.toFixed(2));
            ztzhjz  =result.toFixed(2);
        }
        function sumXm() {
            var xczxzj = $("input[name=xmzjlyCzxzj]").val() || 0;
            var xzjph = $("input[name=xmzjlyZyph]").val() || 0;
            var xqt = $("input[name=xmzjlyQt]").val() || 0;
            var result = parseFloat(xczxzj) + parseFloat(xzjph) + parseFloat(xqt);
            $("input[name=xmzjlyHj]").prev().val(result.toFixed(2));
            ztzzjlyz=result.toFixed(2);
        }

		function getFiledata(){
        	var dataArr = $("#uploadTable").dtable("getSelections");
	        if (dataArr.length === 0) {
	            $.dalert({text:"请选择数据",icon:7});
	            return;
	        }
	        return dataArr;
        }

	    function deleteFile(){
	    	var data = $("#uploadTable").dtable("getSelections");
        	if(data.length==0){
                $.dalert({text:'请选择您要删除的附件',icon:7});
                return;
            }else{

	           var $table = $("#uploadTable");
	            var selectData = getFiledata();
	            if(selectData != undefined){
	                layer.confirm('确定删除吗？', {
	                    btn: ['确定','取消'] //按钮
	                }, function(){

	                	$.dajax({
			               type:'post',
			               url:getRootPath()+'/file/deleteById.do',
			               data:{
			               		id:data[0].FILEID
			               },
			               dataType:'json',
			               success:function (data) {

			                   var tableData = $table.dtable("getData");
			                    $table.tableEditor("updateAll");
			                    $.each(selectData, function(i){
			                        var dataIndex = tableData.indexOf(selectData[i]);
			                        $table.dtable("removeByRowIndex", dataIndex);
			                    });
			                    $table.tableEditor("updateAll");
								$('#uploadTable').dtable('refresh');
			                    /* var dataLen = $table.dtable("getData").length;
			                    $table.dtable("refreshOptions",{height:200+30*dataLen});
			                    $table.tableEditor("initAll"); */

			                    layer.msg('删除成功', {icon: 1});
			               }
			           })


	                });
	            }
            }
	    }

	    function  PrintBzk(){
       		$.dajax({
                url: "xmqqsh/getZbkPrint.do",
                type: "POST",
                data: {
                    xmid: xmid                
                },
                success: function (data) {                
                	$.dopen({
                        type: 2,
                        title: "指标库打印",
                        area: ["95%", "98%"],
                        content:data.content,
                        btn: ["关闭"],
                        btn1: function (index) {
                            layer.close(index);
                           

                        },
                    });
               		
                   
                }
            });
       		
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
       	
       	function savealk(){
//        		var alkid="";
//        		var alkTables = $("#alkTable").dtable("getSelections");
//        		if(alkTables.length>0){
//        			for(i=0;i<alkTables.length;i++){
//        				alkid +=alkTables[i].ID+",";	  					
// 	    		}
//        		}
			var alk = $("#alkTable").dtable("getData");
       		$.dajax({
    			cache : true,
    			type : "POST",
    			url : "xmqqsh/savealk.do",
    			data:$.param({"xmid":xmid,"alk" : JSON.stringify(alk)}),
    			success : function(data) {
    				if (data.success) {
    					$.dalert({text:data.content, icon: 1});
    					 $("#alkTable").dtable("refresh");
    					
    				} else {	    				
    					$.dalert({text:"保存失败", icon: 2});
    				  					
    				}
    			}
    		});
       		
       	}
    </script>
</head>
<body>

</body>
</html>