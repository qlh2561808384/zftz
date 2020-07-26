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
    <title>项目前期策划审核</title>
    <script src="bootstrap2/js/jquery.js"></script>
    <script src="bootstrap2/js/bootstrap.datanew.js"></script>
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
        var xmlx = [];
        var jsdw = [];
        var id;
        var xmId;
        var zbkId;
        var ztzHj = '';
        var dopenIndex = 0;
        //        $(function () {
        //            //项目ID
        //            $.dajax({
        //                type:'post',
        //                url:"xmqqsh/getxmId.do",
        //                dataType:'json',
        //                async:false,
        //                success:function (data) {
        //                    xmId = data;
        //                }
        //            });
        //        })
        $(function () {
            $.dajax({
                url: "xmqqch/selectXmlx.do",
                data: {},
                cache: true,
                type: "POST",
                dataType: "json",
                success: function (data) {
                    xmlx = data;
                }
            });
            $.dajax({
                url: "xmqqch/selectJsdw.do",
                data: {},
                cache: true,
                type: "POST",
                dataType: "json",
                success: function (data) {
                    jsdw = data;
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
                                            "dragHtml": "<h4 class='text-center'><b>项目前期策划审核</b></h4>"
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
                                                {field: "xmid", visible: false},
                                                {field: "id", visible: false},
                                                {field: "id1", visible: false},
                                                {field: "issh", visible: false},
                                                {
                                                    field: "xmphjy",
                                                    title: "项目评审建议",
                                                    width: 100,
                                                    align: "center",
                                                    visible: false
                                                },
                                                {field: "sldh", title: "受理单号", width: 100, align: "center"},
                                                {field: "xmmc", title: "项目名称", width: 100, align: "center"},
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
                                                {field: "xmztz", title: "项目总投资(万元)", width: 100, align: "center"},
                                                {field: "tzxe", title: "核准投资限额(万元)", width: 100, align: "center"},
                                                {
                                                    field: "lchj",
                                                    title: "状态",
                                                    width: 100,
                                                    align: "center",
                                                    formatter: zt
                                                },
                                                {field: "czr", title: "申请人", width: 100, align: "center"},
                                                {field: "czsj", title: "申请时间", width: 100, align: "center"}
                                            ],
                                            "url": "xmqqsh/getXmqqsh.do",
                                            queryParams: queryParams,//查询参数方法
                                            pageNumber: 1,//起始页
                                            pageSize: 20,//页面大小
                                            showRefresh: false,
                                            pagination: true,
                                            paginationHAlign: 'left',//分页按钮位置  left/right
                                            sidePagination: 'server',
                                            resizable: true,
                                            "toolbar": [
                                                {
                                                    "name": "审核",
                                                    "classes": "bootstrap-table-verify",
                                                    "type": "button",
                                                    "id":"sh",
                                                    "onclick": "submitRow()"
                                                },
                                                {
                                                    "name": "退回",
                                                    "classes": "bootstrap-table-enable",
                                                    "type": "button",
                                                    "id":"th",
                                                    "onclick": "setThYj()"
                                                },
                                                {
                                                    "name": "查看/修改",
                                                    "classes": "btn bootstrap-table-edit",
                                                    "type": "button",
                                                    "onclick": "editRow()"
                                                },
                                            ],
                                            "clickToSelect": true,
                                            searchbar: {
                                                rownum: 2,
                                                labelwidth: "70px",
                                                "inputs": [
                                                    {title: "项目名称", name: "xmid", type: "textBox", id: "xmmcid"},
                                                    {title:"项目状态", name:"zt", type:"comboBox",id:"xmzt",selected:1,localdata:[{id:0,text:'审核项目'},{id:1,text:'经办项目'}],
                                                    	onChange:function (newValue,oldValue) {
                                                    	    if (1 == newValue) {
                                                    	        disableButton(['sh', 'th']);
                                                    	    }else {
                                                    	        showButton(['sh', 'th'])
                                                    	    }
                                                    	}
                                                    }

                                                ]
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
            $.initPage(json);
        });

        var dopenJson = [
            {
                "plug": [
                    {
                        "plug": [
                            {
                                "plug": [
                                    {
                                        "dtype": "html",
                                        "dragHtml": "<h4 class='text-center'><b>项目前期策划审核</b></h4>"
                                    },
                                    {
                                        "dtype": "html",
                                        "dragHtml": '<div class="toolbar layout_toolbar clearfix">' +
                                            '<button class="bootstrap-table-save" type="button" id="bcdopen" onclick="saveOrCheck()">保存</button>' +
                                            '<button class="bootstrap-table-submit" type="button" id="spdopen" onclick="submitSh()">审批</button>' +
                                            '<button class="bootstrap-table-clear" type="button" id="thdopen" onclick="backSh()">退回</button>' +
                                            '<button class="bootstrap-table-review" type="button" id="bzkdopen" onclick="zbk()">标准库</button>' +
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
                                            {
                                                title: "项目名称",
                                                name: "xmmc",
                                                type: "textBox",
                                                disabled: "disabled",
                                                setReadonly: true
                                            },
                                            {
                                                title: '建设单位',//表单lable显示名
                                                type: 'dsearchtree',//表单类型：目前支持 select/text
                                                // placeholder: '请选择建设单位',
                                                searchOption: true,
                                                id: 'depart',
                                                name: 'jsdw',
                                                checkType: 'radio',
                                                url: 'xmqqch/getJSDWTree.do',
                                                disabled: "true",
                                                setReadonly: true,
                                                onAckCallback: function (nodes) {
                                                    if (nodes.length > 0) {
                                                        $('#depart').searchTree("setValue", nodes[0].id);
                                                    }
                                                }
                                            },
                                            {
                                                title: '主管部门',//表单lable显示名
                                                type: 'dsearchtree',//表单类型：目前支持 select/text
                                                placeholder: '主管部门',
                                                searchOption: true,
                                                setReadonly: true,
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
                                            {
                                                title: "项目规划选址",
                                                name: "xmghxz",
                                                type: "textBox",
                                                disabled: "disabled",
                                                setReadonly: true
                                            },
                                            {
                                                title: '项目类型',//表单lable显示名
                                                type: 'dsearchtree',//表单类型：目前支持 select/text
                                                setReadonly: true,
                                                placeholder: '项目类型',
                                                searchOption: true,
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
                                            {
                                                title: "容积率",
                                                name: "rjl",
                                                type: "decimal",
                                                disabled: "disabled",
                                                textAlign:'right',
                                                setReadonly: true
                                            },
                                            {
                                                title: "用地面积(平方米)",
                                                name: "ydmj",
                                                type: "decimal",
                                                disabled: "disabled",
                                                textAlign:'right',
                                                setReadonly: true
                                            },
                                            {
                                                title: "建筑总面积（平方米）",
                                                name: "jzzmj",
                                                type: "decimal",
                                                disabled: "disabled",
                                                textAlign:'right',
                                                setReadonly: true
                                            },
                                            {
                                                title: "其中：地上建筑面积(平方米)",
                                                name: "dsjzmj",
                                                type: "decimal",
                                                disabled: "disabled",
                                                textAlign:'right',
                                                setReadonly: true
                                            },
                                            {
                                                title: "项目里程（公里）",
                                                name: "xmlc",
                                                type: "decimal",
                                                disabled: "disabled",
                                                textAlign:'right',
                                                setReadonly: true
                                            },
                                            {
                                                title: "道路宽度(米)",
                                                name: "dlkd",
                                                type: "decimal",
                                                disabled: "disabled",
                                                textAlign:'right',
                                                setReadonly: true
                                            },
                                            {
                                                title: "联系电话",
                                                name: "lxdh",
                                                type: "textBox",
                                                disabled: "disabled",
                                                setReadonly: true
                                            },
                                            {
                                                title: "项目单位造价(元)",
                                                name: "xmdwzj",
                                                type: "decimal",
                                                disabled: "disabled",
                                                textAlign:'right',
                                                setReadonly: true
                                            },
                                            {
                                                title: "同类型造价标准(元)",
                                                name: "tlxzjbz",
                                                type: "decimal",
                                                disabled: "disabled",
                                                textAlign:'right',
                                                setReadonly: true
                                            },
                                            {
                                                title: "联系人",
                                                name: "lxr",
                                                type: "textBox",
                                                disabled: "disabled",
                                                setReadonly: true
                                            },
                                            {
                                                title: "文件造价标准(元)",
                                                name: "wjzjbz",
                                                type: "decimal",
                                                disabled: "disabled",
                                                textAlign:'right',
                                                setReadonly: true
                                            },
                                            {
                                            	title: "计量单位(造价标准)",
                                            	name: "jldw", 
                                            	type: "comboBox",
                                            	id:"jldw",
                                            	valueField:"id",
                                                textField:"text",
                                            	url:"xmqqch/selectJldw.do",
                                            	disabled: "disabled",
                                            	setReadonly: true
                                            	                                           
                                            },
                                            {
                                                title: "建设内容",
                                                name: "jsnr",
                                                type: "textBox",
                                                multiline: true,
                                                colspan: 3,
                                                disabled: "disabled",
                                                setReadonly: true
                                            },
                                            {
                                                title: "项目总投资",
                                                name: "",
                                                type: "html",
                                                rowspan: 2,
                                                html: '<div class="customLabel text-center"><label class="control-label">合计</label></div><div class="customInput text-center"><input type="textBox" name="ztzHj" disabled="disabled" style="text-align: right" setReadonly:true></div>'
                                            },
                                            {
                                                title: '<div class="customLabel"><label class="control-label">建筑安装投资</label></div><div class="customInput"><input type="decimal" name="ztzJzaztz" style="text-align: right "disabled="disabled"=></div>',
                                                name: "",
                                                type: "html",
                                                rowspan: 2,
                                                html: '<div class="customLabel"><label class="control-label">设备投资</label></div><div class="customInput"><input type="decimal" name="ztzSbtz" style="text-align: right" disabled="disabled" ></div>'
                                            },
                                            {
                                                title: '<div class="customLabel"><label class="control-label">待摊投资</label></div><div class="customInput"><input type="decimal" name="ztzDttz" style="text-align: right "disabled="disabled"></div>',
                                                name: "",
                                                type: "html",
                                                rowspan: 2,
                                                html: '<div class="customLabel"><label class="control-label">其他投资</label></div><div class="customInput" ><input type="decimal" name="ztzQttz" style="text-align: right" disabled="disabled"></div>'
                                            },

                                            {
                                                title: "项目资金来源",
                                                name: "",
                                                type: "html",
                                                rowspan: 2,
                                                html: '<div class="customLabel text-center"><label class="control-label">合计</label></div><div class="customInput text-center"><input type="textBox" name="xmzjlyHj" style="text-align: right" disabled="disabled"></div>'
                                            },
                                            {
                                                title: '<div class="customLabel"><label class="control-label">财政性资金</label></div><div class="customInput"><input type="decimal" name="xmzjlyCzxzj" style="text-align: right" disabled="disabled"></div>',
                                                name: "",
                                                type: "html",
                                                rowspan: 2,
                                                html: '<div class="customLabel"><label class="control-label">资源平衡</label></div><div class="customInput"><input type="decimal" name="xmzjlyZyph" style="text-align: right" disabled="disabled"></div>'
                                            },
                                            {
                                                title: '<div class="customLabel"><label class="control-label">其他</label></div><div class="customInput"><input type="decimal" name="xmzjlyQt" style="text-align: right" disabled="disabled"></div>',
                                                name: "",
                                                type: "html",
                                                rowspan: 2,
                                                html: '<div class="customLabel"><label class="control-label">--</label></div><div class="customInput text-center">--</div>'
                                            },

                                            {
                                                title: "项目评审建议",
                                                name: "",
                                                type: "html",
                                                rowspan: 3,
                                                colspan: 2,
                                                required: true,
                                                html: '<div class="customInput"><input type="textBox" data-multiline="true" data-height="84" name="xmphjy"></div>'
                                            },
                                            {
                                                title: "投资限额",
                                                name: "tzxe",
                                                type: "decimal",
                                                required: true,
                                                textAlign:'right',
                                                onChange: sum,
                                                onInputChange: sum
                                            },
                                            {title: "建议控制造价标准(元)", name: "jykzzjbz", type: "decimal",textAlign:'right', required: true},
                                            {
                                                title: "核减金额",
                                                name: "hjje",
                                                id:"hjjeId",
                                                type: "textBox",
                                                disabled: "true",
                                                textAlign:'right',
                                                setReadonly: true
                                            }
                                        ]
                                    },

                                    {
                                        "dtype": "html",
                                        "dragHtml": "<h5 class='text-center'><b><span style='text-align: left'>处理过程意见:</span></b></h5>"
                                    },
                                    {
                                        "dtype": "dtable",
                                        "id": "gcyj",
                                        "height": $(window).height() - 180,
                                        "columns": [
                                            {field: "shjlid", visible: false},
                                            {
                                                field: "DD",
                                                title: "序号",
                                                width: 40,
                                                align: "center",
                                                formatter: function (value, row, index) {
                                                    return index + 1;
                                                }
                                            },
                                            {field: "GUID", title: "GUID", width: 100, align: "center", visible: false},
//                                             {field: "DQHJBM", title: "环节", width: 100, align: "center"},
											{field: "DQHJMC", title: "环节", width: 100, align: "center"},
                                            {field: "FKR", title: "处理人", width: 100, align: "center"},
                                            {
                                                field: "DDRQ",
                                                title: "到达时间",
                                                width: 100,
                                                align: "center",
                                                formatter: changeDateFormat
                                            },
                                            {
                                                field: "CLSJ",
                                                title: "处理时间",
                                                width: 100,
                                                align: "center",
                                                formatter: changeDateFormat
                                            },
                                            {field: "CLYJ", title: "处理意见", width: 100, align: "center"},
                                        ],
                                        "url": "xmqqsh/getShyj.do"
                                    },

                                    {
                                        "dtype": "html",
                                        "dragHtml": '<div style="padding:20px 0 10px 15px;"><b>附件列表：</b></div>'
                                    },
                                    {
                                        "dtype": "dtable",
                                        "height": 250,
                                        "id": "uploadTable",
                                        editable: true,
                                        "url": "xmqqch/getXmqqchfj.do",
                                        "columns": [
                                            {field: 'FILEID', title: 'guid', visible: false},
                                            {
                                                field: "xh",
                                                title: "序号",
                                                width: 40,
                                                align: "center",
                                                formatter: function (value, row, index) {
                                                    return index + 1;
                                                }
                                            },
                                            {field: "FILENAME", title: "附件名", width: 300, align: "center"},
                                            {
                                                field: "FILEDL",
                                                title: "附件大类",
                                                width: 120,
                                                align: "center",
                                                type: 'comboBox',
                                                selected: 1,
                                                localdata: [{id: 7, text: "变更联系单备案"}],
                                                formatter: function (value, row, index) {
                                                    return "变更联系单备案";
                                                }
                                            },
//                                             {field:"filexl", title:"附件小类", width:120, align:"center",editor:{type:'comboBox',localdata:[{id:701,text:"类别1"},{id:702,text:"类别2"}]}},
                                            {field: "FILESIZE", title: "大小", width: 80, align: "center",formatter: function (value, row, index) {
                                                return formatSize(value);
                                            }},
//                                             {field:"filebz", title:"备注", width:80, align:"center",editor:{type:'textBox'}}
                                            {
                                                field: '',
                                                title: '操作 ',
                                                width: 100,
                                                align: 'center',
                                                formatter: function (value, row, index) {
                                                    return '<a href=' + getRootPath() + '/file/downloadByid.do?id=' + row.FILEID + '>下载</a>';
                                                }
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

        //格式化文件大小, 输出成带单位的字符串
        function formatSize(size, pointLength, units) {
            var unit;

            units = units || ['B', 'K', 'M', 'G', 'TB'];

            while ((unit = units.shift()) && size > 1024) {
                size = size / 1024;
            }

            return (unit === 'B' ? size : size.toFixed(pointLength || 2)) +
                unit;
        }
        var dopenZbk = [
            {
                "plug": [
                    {
                        "plug": [
                            {
                                "plug": [
                                    {
                                        "dtype": "html",
                                        // "dragHtml": "<h4 class='text-center'><b>项目标准库</b><span id='xmbhid'></span></h4>"
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
                                                        clickToSelect: "true",
                                                        "dtype": "dtable",
                                                        "id": "zbkTeb",
                                                        "height": $(window).height() - 180,
                                                        "columns": [
                                                            {field: "checkType", checkbox: true},
                                                            {
                                                                field: "DD",
                                                                title: "序号",
                                                                width: 40,
                                                                align: "center",
                                                                clickToSelect: "true",
                                                                formatter: function (value, row, index) {
                                                                    return index + 1;
                                                                }
                                                            },
                                                            {
                                                                field: 'YJZB',
                                                                title: '一级指标',
                                                                align: 'center',
                                                                width: 120,
                                                                clickToSelect: "true",
//                                                editor:{
                                                                type: "textBox"
//                                                }
                                                            },
                                                            {
                                                                field: 'EJZB',
                                                                title: '二级指标',
                                                                align: 'center',
                                                                width: 120,
                                                                clickToSelect: "true",
//                                                editor:{
                                                                type: "textBox"
//                                                }
                                                            },
                                                            {
                                                                field: 'SJZB',
                                                                title: '三级指标',
                                                                align: 'center',
                                                                width: 150,
                                                                clickToSelect: "true",
//                                                editor:{
                                                                type: "textBox"
//                                                }
                                                            },
                                                            {
                                                                field: 'DWTZE',
                                                                title: '单位投资额',
                                                                align: 'center',
                                                                width: 140,
                                                                clickToSelect: "true",
//                                                editor:{
                                                                type: "textBox"
                                                            },
                                                            {
                                                                field: 'JLDW',
                                                                title: '计量单位',
                                                                align: 'center',
                                                                width: 140,
                                                                clickToSelect: "true",
//                                                editor:{
                                                                type: "textBox"
                                                            },
                                                            {
                                                                field: 'ZBLY',
                                                                title: '指标来源',
                                                                align: 'center',
                                                                width: 140,
                                                                clickToSelect: "true",
//                                                editor:{
                                                                type: "textBox"
                                                            },

                                                            {
                                                                field: 'BZ',
                                                                title: '备注',
                                                                align: 'center',
                                                                width: 80,
                                                                clickToSelect: "true",
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
                                                        pageSize: 8,//页面大小
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
                                                        "height": $(window).height() - 180,
                                                        clickToSelect: "true",
                                                        "columns": [
                                                            {field: "checkType", checkbox: true},
                                                            {field: 'ID', title: 'guid', align: 'center', width: 120, visible: false},
                                                            {
                                                                field: '项目标准测算ID',
                                                                title: 'XMBZCSID',
                                                                align: 'center',
                                                                width: 120,
                                                                visible: false
                                                            },
                                                            {
                                                                field: "DD",
                                                                title: "序号",
                                                                width: 40,
                                                                align: "center",
                                                                clickToSelect: "true",
                                                                formatter: function (value, row, index) {
                                                                    return index + 1;
                                                                }
                                                            },
                                                            {
                                                                field: 'YJZB',
                                                                title: '一级指标',
                                                                align: 'center',
                                                                width: 120,
                                                                clickToSelect: "true",
                                                                //editor:{
                                                                type: "textBox"
                                                                //}
                                                            },
                                                            {
                                                                field: 'EJZB',
                                                                title: '二级指标',
                                                                align: 'center',
                                                                width: 120,
                                                                clickToSelect: "true",
                                                                //editor:{
                                                                type: "textBox"
                                                                // }
                                                            },
                                                            {
                                                                field: 'SJZB',
                                                                title: '三级指标',
                                                                align: 'center',
                                                                width: 150,
                                                                clickToSelect: "true",
                                                                // editor:{
                                                                type: "textBox"
                                                                // }
                                                            },
                                                            {
                                                                field: 'DWTZE',
                                                                title: '单位投资额',
                                                                align: 'center',
                                                                width: 140,
                                                                clickToSelect: "true",
                                                                // editor:{
                                                                type: "textBox"
                                                            },
                                                            {
                                                                field: 'JLDW',
                                                                title: '计量单位',
                                                                align: 'center',
                                                                width: 140,
                                                                clickToSelect: "true",
                                                                //editor:{
                                                                type: "textBox"
                                                            },
                                                            {
                                                                field: 'SL',
                                                                title: '数量',
                                                                align: 'center',
                                                                width: 140,
                                                                clickToSelect: "true",
                                                                editor: {type: "decimal"},
                                                                required: true,
                                                                onChange: sumXj,
                                                                onInputChange: sumXj
                                                            },
                                                            {
                                                                field: 'XJ',
                                                                title: '小计',
                                                                align: 'center',
                                                                width: 80,
                                                                clickToSelect: "false",
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
                                                        pagination: false,
                                                        showRefresh: false,
                                                        showFooter:true,
                                                        paginationHAlign: 'left',//分页按钮位置  left/right
                                                        sidePagination: 'server',
                                                        onLoadSuccess: function (data) {
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
//                                                                 name: 'zt',
//                                                                 type: 'comboBox',
                                                                
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
//         function changeTh(){
//             var $table = $("#alkTable");
//             var newColumns = [
//                 {field:"bm", title:"编码", width:100, align:"center"},
//                 {field:"mc", title:"名称", width:100, align:"center"},
//                 {field:"nf", title:"年份", width:100, align:"center"},
//                 {field:"zmj", title:"总面积", width:100, align:"center"},
//                 {field:"xmdwzj", title:"项目单位造价", width:100, align:"center"}
//             ];
//             $table.dtable("refreshOptions", {columns:newColumns});
//         }

        function queryParams(params) {
            return {
                //如果需要后端进行分页 limit 和offset是必须参数
                limit: params.limit,
                offset: params.offset,
                id: xmId
            }
        }


        //        function pageUploadFile() {
        //            uploadfile({server:getRootPath()+'/file/uploader.do?filebstype=1'}, function (rowData) {
        //                var $table = $("#uploadTable"),
        //                        dataLen = $table.dtable("getData").length;
        //                $table.tableEditor("updateAll");
        //                for (var i = 0; i < rowData.length; i++) {
        //                    var data = {
        //                        FILENAME: rowData[i].name,
        //                        FILESIZE: rowData[i].size,
        //                        FILEID: rowData[i].fileId
        //                    };
        //                    $table.dtable("insertRow", {index: dataLen + i, row: data});
        //                }
        //                $table.tableEditor("initAll");
        //            });
        //        }
        function clearForm() {
            var formObj = $("#openForm");
            formObj.dform("clear");
            formObj.find(".customInput .form-control").each(function () {
                var $input = $(this),
                    dtype = $input.parents(".ztreeBox_inp").find(".valueBox").attr("boxtype");
                $input[dtype]("clear");
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
                
            	dopenIndex = $.dopen({
                    type: 6,
                    title: "项目前期策划受理单审核",
                    area: ["95%", "100%"],
                    content: dopenJson,
                    btn: ["关闭"],
                    btn1: function (index) {
                        layer.close(index);
                        $("#teb").dtable("refresh");

                    },
                });
                $(".customInput input").each(function () {
                    var $inp = $(this),
                        dtype = $inp.attr("type"),
                        options = $inp.data();
                    $inp[dtype](options);
                })
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
                    if (isNaN(result) == true) {
                        $("input[name=ztzHj]").parents(".ztreeBox_inp").find(".form-control")["textBox"]("setValue", "");
                        $("#hjjeId").textBox("setValue",0-data[0].tzxe.toFixed(2));
                    } else {
                        $("input[name=ztzHj]").parents(".ztreeBox_inp").find(".form-control")["textBox"]("setValue", result.toFixed(2));
                        $("#hjjeId").textBox("setValue",result.toFixed(2)-data[0].tzxe.toFixed(2));
                    }
                    if (isNaN(result1) == true) {
                        $("input[name=xmzjlyHj]").parents(".ztreeBox_inp").find(".form-control")["textBox"]("setValue", "");
                    } else {
                        $("input[name=xmzjlyHj]").parents(".ztreeBox_inp").find(".form-control")["textBox"]("setValue", result1.toFixed(2));
                    }
                    sum();
                }, 0);
                var guid = data[0].id1;

                $("#uploadTable").dtable("refreshOptions", {url: "xmqqch/getXmqqchfj.do?lx=1&guid=" + guid});
                $("#openForm").dform('load', data[0]);
                $("#gcyj").dtable("refreshOptions", {url: "xmqqsh/getShyj.do?id=" + guid});
                if(data[0].issh=='0'){
                	disableButton(['bcdopen','spdopen','thdopen','bzkdopen']);
                }
                id = data[0].id;
                xmId = data[0].xmid;
                shjlid = data[0].ide;
            }
        }

        //审批更新
        function saveOrCheck() {
            var data = $("#openForm").dform('getData');
            var flag = $("#openForm").dform('validate');
            if (flag) {
                $.dajax({
                    url: "xmqqsh/saveOrCheck.do",
                    type: "POST",
                    data: {
                        id: id,
                        tzxe: data.tzxe,
                        jykj: data.jykzzjbz,
                        spjy: data.xmphjy,
                    },
                    dataType: "json",
                    success: function (data) {
                        if (data.success) {
                            $.dalert({text: '保存成功', icon: 1});
                            savaSldh = data.content;
                        } else {
                            $.dalert({text: '请输入数量', icon: 1});
                        }
                    }
                });
            }

        }

        function zbk() {
            $.dopen({
                type: 6,
                title: "项目前期策划受理单申请",
                area: ["95%", "100%"],
                content: dopenZbk,
                btn: ["关闭"],
                btn1: function (index) {
                    layer.close(index);
//                     $("#teb").dtable("refresh");
                }
            });

//            $(".customInput input").each(function () {
//                var $inp = $(this),
//                        dtype = $inp.attr("type"),
//                        options = $inp.data();
//                $inp[dtype](options);
//            })
        }

        function select() {
//            $('#zbkTeb').dtable('refresh');
            var data = $("#zbkTeb").dtable("getSelections");
//            var data1 = $("#zbkSelect").dtable("getSelections");
            zbkId = data[0].ZBKID;
//            zbkId1=data1[0].ZBKID;
            $("#zbkSelect").dtable('append', data);
            try {
                $("#zbkSelect").tableEditor("initAll");
            }catch (e) {

            }
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
                }
            }
        }

        function removRow2() {
            var $table = $("#zbkSelect");
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
            var zbkId = '';
            var sl = '';
            var data = $("#zbkSelect").dtable("getData");
            $("#zbkSelect").tableEditor('updateAll');
            if (data.length == 0) {
                $.dalert({text: '没有数据需要保存', icon: 7});
                return;
            }
            if (data.length > 0) {
                for (var i = 0; i < data.length; i++) {
                    zbkId += data[i].ZBKID + ",";
                    sl += data[i].SL + ",";
                }
            }
            $.dajax({
                url: "xmqqsh/saveZbk.do",
                type: "POST",
                data: {
                    idZftzXm: xmId,
                    idZftzZbk: zbkId,
                    sl: sl
                },
                success: function (data) {
                    if (data.success) {
                        $.dalert({text: '保存成功', icon: 1});
                        $('#zbkSelect').dtable('refresh');
                    } else {
                        $.dalert({text: data.content, icon: 1});
                    }
                }
            });

        }

        function delZbkRow() {
            var XMZJYLDJ = "";
            var $table = $("#zbkSelect");
            var $table1 = $("#zbkTeb");
            var selectData = $("#zbkSelect").dtable("getSelections");
            if (selectData.length <= 0) {
                $.dalert({text: "请选择一条数据", icon: 2});
                return;
            }
            if (selectData != undefined) {
                layer.confirm('确定删除吗？', {
                    btn: ['确定', '取消'] //按钮
                }, function () {
                    for (i = 0; i < selectData.length; i++) {
                        if (selectData[i].XMBZCSID == undefined) {
                            XMZJYLDJ += "-1" + ",";
                        } else {
                            XMZJYLDJ += selectData[i].XMBZCSID + ",";
                        }
                    }
                    $.dajax({
                        cache: true,
                        type: "POST",
                        url: "xmqqsh/delZbk.do",
                        data: $.param({ids: XMZJYLDJ}),
                        success: function (data) {
                            if (data.success) {
                                $.dalert({text: data.content, icon: 1});
                                // var rowIndex = $table.find("tr.editing").data("index");
                                // $table.tableEditor("cancel", rowIndex);
                                var oldData = $('#zbkTeb').dtable('getData');
                                oldData.push.apply(oldData, selectData);
                                $('#zbkTeb').dtable('load', oldData);
                                // $('#zbkSelect').dtable('refresh');
                                //  $('#zbkTeb').d
                            } else {
                                $.dalert({text: data.content, icon: 2});
                                var rowIndex = $table.find("tr.editing").data("index");
                                $table.tableEditor("cancel", rowIndex);
//                                 $('#zbkTeb').dtable('refresh');
                            }
                        }
                    });
                    $table.tableEditor("updateAll");
                    var tableData = $table.dtable("getData");
                    $.each(selectData, function (i) {
                        var dataIndex = tableData.indexOf(selectData[i]);
                        $table.dtable("removeByRowIndex", dataIndex);
                    });
//                    $("#zbkTeb").dtable('append', selectData);
//                    $('#zbkTeb').dtable('refresh');
//                    $("#zbkTeb").tableEditor("initAll");
//                     $table.tableEditor("updateAll");
                    $table.dtable("load",tableData);
                    $table.tableEditor("initAll");
                });
            }
        }


        function submitSh() {
            var guids = '';
            var data = $("#teb").dtable("getSelections");

            if (data.length > 0) {
                for (var i = 0; i < data.length; i++) {
                    guids += data[i].id1 + ",";
                }
//                 if(data[i].xmphjy!=''||data[i].xmphjy!=undefined){
//                 	$.dalert({text:'请先填写评审意见！',icon:7});
//                 	return;
//                 }
            }
            layer.confirm('确定提交吗？', {
                btn: ['确定', '取消'] //按钮
            }, function () {
                $.dajax({
                    url: "xmqqsh/submitSh.do",
                    type: "POST",
                    data: {guids: guids},
                    success: function (data) {
                        if (data.success) {
                            $.dalert({text: '审核成功', icon: 1});
                            $('#teb').dtable('refresh');
                            layer.close(dopenIndex);
                        } else {
                            $.dalert({text: data.content, icon: 1});
                        }
                    }
                })
            })
        }

        function submitRow() {
            var guids = '';
            var data = $("#teb").dtable("getSelections");
            
            if (data.length > 0) {
                for (var i = 0; i < data.length; i++) {
                    guids += data[i].id1 + ",";
	                    if(data[i].issh=='0'){
	                    	$.dalert({text:'所选项目无审核权限',icon:7});
	                    	return;
	                    }
                    }
                }
//                 alert(data[i].xmphjy);
//                 if(data[i].xmphjy!=null||data[i].xmphjy!='null'){
//                 	$.dalert({text:'请先填写评审意见！',icon:7});
//                 	return;
//                 }
            
            if (data.length == 0) {
                $.dalert({text: '请选择数据', icon: 7});
                return;
            }
            layer.confirm('确定提交吗？', {
                btn: ['确定', '取消'] //按钮
            }, function () {
//                 $('#sldh').val(data[0].sldh);
                $.dajax({
                    url: "xmqqsh/submitSh.do",
                    type: "POST",
                    data: {guids: guids},
                    success: function (data) {
                        if (data.success) {
                            $.dalert({text: '审核成功', icon: 1});
                            $('#teb').dtable('refresh');
                            layer.close(dopenIndex);
                        } else {
                            $.dalert({text: data.content, icon: 1});
                        }
                    }
                })
            });

        }

        function backSh() {
            var guids = '';
            var data = $("#teb").dtable("getSelections");
            if (data.length > 0) {
                for (var i = 0; i < data.length; i++) {
                    guids += data[i].id1 + ",";
                    
                }
            }
            layer.confirm('确定退回吗？', {
                btn: ['确定', '取消'] //按钮
            }, function () {
                $.dajax({
                    url: "xmqqsh/backSh.do",
                    type: "POST",
                    data: {guids: guids},
                    success: function (data) {
                        if (data.success) {
                            $.dalert({text: '退回成功', icon: 1});
                            $('#teb').dtable('refresh');
                            layer.close(dopenIndex);
                        } else {
                            $.dalert({text: data.content, icon: 2});
                        }
                    }
                })
            })
        }

        function backRow(value) {
            var shyj=value;
        	var guids = '';
            var data = $("#teb").dtable("getSelections");
            if (data.length > 1) {
            	$.dalert({text: "只允许单条退回", icon: 2});
            }
			if (data.length <1) {
				$.dalert({text: "请选择要退回的数据", icon: 2});
            }
            if (data.length > 0) {
                for (var i = 0; i < data.length; i++) {
                    guids += data[i].id1 + ",";
                    
                }
            }
            if (data.length == 0) {
                $.dalert({text: '请选择数据', icon: 7});
                return;
            }
            layer.confirm('确定提交吗？', {
                btn: ['确定', '取消'] //按钮
            }, function () {
//                 $('#sldh').val(data[0].sldh);
                $.dajax({
                    url: "xmqqsh/backSh.do",
                    type: "POST",
                    data: {guids: guids,shyj:shyj},
                    success: function (data) {
                        if (data.success) {
                            $.dalert({text: '退回成功', icon: 1});
                            $('#teb').dtable('refresh');
                            layer.close(dopenIndex);
                        } else {
                            $.dalert({text: data.content, icon: 1});
                        }
                    }
                })
            });

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

        function zt(value) {
            if (value == -1) {
                value = "未提交"
            } else if (value == 0) {
                value = "已审核"
            } else {
                value = "审核中"
            }
            return value;
        }

        function sum() {
            var ztzHj = $("input[name=ztzHj]").val() || 0;
            var tzxe = $("#openForm").dform("getValueByName", "tzxe");
            var result = parseFloat(ztzHj) - parseFloat(tzxe);
            if (isNaN(result) == true) {
                $("input[name=hjje]").parents(".ztreeBox_inp").find(".form-control").textBox("setValue", "");
            } else {
                $("input[name=hjje]").parents(".ztreeBox_inp").find(".form-control").textBox("setValue", result.toFixed(2));
            }


        }

        function sumXj() {

            var data = $("#zbkSelect").dtable("getSelections");
            $("#zbkSelect").tableEditor('updateAll');
            var result = parseInt(data.DWTZES) - parseInt(data.SL);
            $("input[name=XJ]").parents(".ztreeBox_inp").find(".form-control").textBox("setValue", result);

        }

        //转换日期格式(时间戳转换为datetime格式)
        function changeDateFormat(cellval) {
            var dateVal = cellval + "";
            if (cellval != null) {
                var date = new Date(parseInt(dateVal.replace("/Date(", "").replace(")/", ""), 10));
                var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
                var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();

                var hours = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
                var minutes = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
                var seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();

                return date.getFullYear() + "-" + month + "-" + currentDate + " " + hours + ":" + minutes + ":" + seconds;
            }
        }

        function uploadFile() {
            $.dopen({
                title: "上传附件",
                area: ["600px", "300px"],
                content: '<form id="uploadForm"></form>',
                btn: ["保存", "取消"],
                btn1: function (index) {

                }
            });
            $("#uploadForm").dform({
                rownum: 2,
                labelwidth: "100px",
                inputs: [
                    {
                        title: "选择文件", name: "", type: "webupload", colspan: 2,
                        isDel: true,
                        isDownload: true,
                        height: 60,
                        previewTarget: "parent",
                        server: 'file/uploader.do?filebstype=1',//用于提交文件
                        //delUrl: 'file/deleteById.do',
                        //delUrl: '../plugins/webuploader/webuploader.json',
                        dataUrl: 'file/getFilesByids.do',
                        auto: true,
                        downloadUrl: 'file/downloadByid.do'/*,
                     accept:{
                     title : "Image", //{String} 文字描述
                     extensions: "gif,jpg,jpeg,bmp,png", //{String} 允许的文件后缀，不带点，多个用逗号分割
                     mimeTypes: "image/!*" //{String} 多个用逗号分割
                     }*/
                    }
                ]
            })
        }
        
       	function  PrintBzk(){
       		$.dajax({
                url: "xmqqsh/getZbkPrint.do",
                type: "POST",
                data: {
                    xmid: xmId                
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
       	
       	function setThYj(){
       		var data = $("#teb").dtable("getSelections");
            if (data.length > 1) {
            	$.dalert({text: "只允许单条退回", icon: 2});
            }
			if (data.length <1) {
				$.dalert({text: "请选择要退回的数据", icon: 2});
            }
            if (data.length > 0) {
                for (var i = 0; i < data.length; i++) {
                	if(data[i].issh=='0'){
                    	$.dalert({text:'所选项目无退回权限',icon:7});
                    	return;
                    }
                    
                }
            }
      
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
			    		backRow(formdata.textBox4);
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
       	
       	function getalk(){
       		
       		var $table = $("#alkTable");
       		$.dajax({
                url: "xmqqsh/getAlkmb.do",
                type: "POST",
                dataType: "json",
                data: {
                	xmid: xmId                
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
	       
       		$table.dtable("refreshOptions", {url: "xmqqsh/getAlk.do?xmid="+xmId});
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
    			data:$.param({"xmid":xmId,"alk" : JSON.stringify(alk)}),
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
       	
       	function disableButton(arr) {
	        $.each(arr,function (i,v) {
	            $('#'+v).attr('disabled',true);
	        })
	    }
    </script>
</head>
<body>

</body>
</html>