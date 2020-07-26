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
    <title>项目前期策划申请</title>
    <script src="bootstrap2/js/jquery.js"></script>
    <script src="bootstrap2/js/bootstrap.datanew.js"></script>
    <script src="js/file.js"></script>
    <script src="js/common.js"></script>
    <link rel="stylesheet" href="css/common.css">
    <script>
    	var ztzhjz=0;
    	var ztzzjlyz=0;
        var getRootPath = function(){
            var curWwwPath=window.document.location.href;
            var pathName=window.document.location.pathname;
            var pos=curWwwPath.indexOf(pathName);
            var localhostPaht=curWwwPath.substring(0,pos);
            var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
            return(localhostPaht+projectName);
        }
        var xmlx = [];
        var jsdw = [];
        var isEdit = 0;
  
        var xmqqchid;
        var zxXmid;
        var czr;
        var dopenIndex = 0;
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
                                            "dragHtml": "<h4 class='text-center'><b>项目前期策划申请</b></h4>"
                                        },
                                        {
                                            "dtype": "dtable",
                                            "id":"teb",
                                            "height": $(window).height() - 80,
                                            "columns": [
                                                {field: "checkType", checkbox: true},
                                                {
                                                    field: "",
                                                    title: "序号",
                                                    width: 50,
                                                    align: "center",
                                                    formatter: function (value, row, index) {
                                                        return index + 1;
                                                    }
                                                },
                                                {field: "xmid", visible:false},
                                                {field: "id", visible:false},
                                                {field: "zxXmid", visible:false},
                                                {field: "lchj", visible:false},
                                                {field: "id1", visible:false},
                                                {field: "sldh", title: "受理单号", width: 80, align: "center"},
                                                {field: "xmmc", title: "项目名称", width: 100, align: "center"},
                                                {field: "xmlx", title: "项目类型", width: 80, align: "center",formatter:treeFormatterXmxmlx},
                                                {field: "jsdw", title: "建设单位", width: 100, align: "center", formatter:treeFormatterJsdw},
                                                {field: "xmztz", title: "项目总投资(万元)", width: 100, align: "center", visible:false},
                                                {field: "hztzxe", title: "核准投资限额(万元)", width: 100, align: "center", visible:false},
                                                {field: "lchj", title: "状态", width: 100, align: "center",formatter:zt, visible:false},
                                                {field: "yhxm", title: "申请人", width: 100, align: "center"},
                                                {field: "czsj", title: "申请时间", width: 100, align: "center"},
                                                {field: "zt1", title: "状态", width: 100, align: "center",
                                                	formatter : function(value, row, index) {
                                                		if(value=='退回'){
                                                			return '<a href="javascript:void(0)" onclick="selThyj('+row.id1+')">退回</a>';
                                                		}else{
                                                			return value;
                                                		}
																					
													}
                                                }
                                  
                                            ],
                                                "url": "xmqqch/getXmqqch.do",
                                            queryParams: queryParams,//查询参数方法
                                            pageNumber: 1,//起始页
                                            pageSize: 20,//页面大小
                                            showRefresh:false,
                                            pagination:true,
                                            paginationHAlign: 'left',//分页按钮位置  left/right
                                            sidePagination: 'server',
//                                             resizable:true,
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
                                                    "name": "查看/修改",
                                                    "classes": "btn bootstrap-table-edit",
                                                    "type": "button",
                                                    "onclick": "editRow()"
                                                },
                                                {
                                                    "name": "提交",
                                                    "classes": "btn bootstrap-table-review",
                                                    "type": "button",
                                                    "onclick": "submitRow()"
                                                }
                                            ],
                                            "clickToSelect": true,
                                            searchbar:{
                                                rownum:2,
                                                labelwidth: "70px",
                                                "inputs":[
                                                    {title:"项目名称", name:"xmid", type:"textBox",id:"xmmcid"},
                                                    {title:"状态", name:"zt", type:"comboBox",id:"ztid",selected:1,localdata:[{id:-1,text:'未提交'},{id:1,text:'审核中'},{id:0,text:'已审核'}]}
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
            $("#ztid").comboBox("setValue",-1);
            $("#teb").dtable("refresh");
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
                                        "dragHtml": "<h4 class='text-center'><b>项目前期策划申请</b></h4>"
                                    },
                                    {
                                        "dtype": "html",
                                        "dragHtml": '<div class="toolbar layout_toolbar clearfix">' +
                                        '<button class="bootstrap-table-save" type="button" id="saveZxAndFile" onclick="saveZxAndFile()">保存</button>' +
                                        '<button class="bootstrap-table-submit" type="button" id="savaSubmits" onclick="savaSubmit()">提交</button>' +
                                        '<button class="bootstrap-table-review" type="button" id="clearForm" onclick="clearForm()">重填</button>' +
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
                                            {title: "项目zxXmid", name: "zxXmid", type: "hidden"},
                                            {title: "id", name: "id1", type: "hidden"},
                                            {title: "项目名称", name: "xmmc", type: "textBox" ,required:true},
                                            {
                                                title: '建设单位',//表单lable显示名
                                                type: 'dsearchtree',//表单类型：目前支持 select/text
                                                // placeholder: '请选择建设单位',
                                                searchOption:true,
                                                id:'depart',
                                                name:'jsdw',
                                                checkType:'radio',
                                                required:true,
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
                                                id:'batch',
                                                name:'zgbm',
                                                required:true,
                                                checkType:'radio',
                                                url : 'xmqqch/getZGBMTree.do',
                                                onAckCallback:function(nodes){
                                                    if(nodes.length>0){
                                                        $('#batch').searchTree("setValue",nodes[0].id);
                                                    }
                                                }
                                            },
                                            {title: "项目规划选址", name: "xmghxz", type: "textBox"},
                                            {
                                                title: '项目类型',//表单lable显示名
                                                type: 'dsearchtree',//表单类型：目前支持 select/text
                                                placeholder: '请选择项目类型',
                                                searchOption:true,
                                                id:'xmlx',
                                                required:true,
                                                name:'xmlx',
                                                checkType:'radio',
                                                url : 'xmqqch/getXMLXTree.do',
                                                onAckCallback:function(nodes){

                                                    if(nodes.length>0){
                                                        $('#xmlx').searchTree("setValue",nodes[0].id);
                                                    }
                                                }
                                            },
                                            {title: "容积率", name: "rjl",textAlign:'right', type: "decimal"},
                                            {title: "用地面积(平方米)", name: "ydmj",textAlign:'right', type: "decimal"},
                                            {title: "建筑总面积（平方米）", name: "jzzmj",textAlign:'right', type: "decimal"},
                                            {title: "其中：地上建筑面积(平方米)", name: "dsjzmj",textAlign:'right', type: "decimal"},
                                            {title: "项目里程（公里）", name: "xmlc",textAlign:'right', type: "decimal"},
                                            {title: "道路宽度(米)", name: "dlkd",textAlign:'right', type: "decimal"},
                                            {title: "联系电话", name: "lxdh", type: "textBox"},
                                            {title: "项目单位造价(元)", name: "xmdwzj",textAlign:'right', type: "decimal",required:true},
                                            {title: "同类型造价标准(元)", name: "tlxzjbz",textAlign:'right', type: "decimal",required:true},
                                            {title: "联系人", name: "lxr", type: "textBox"},
                                            {title: "文件造价标准(元)", name: "wjzjbz", type: "decimal",textAlign:'right',required:true},
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
                                            {title: "建设内容", name: "jsnr", type: "textBox", multiline: true, colspan: 3,required:true},
                                            {
                                                title: "项目总投资",
                                                name: "",
                                                type: "html",
                                                rowspan: 2,
                                                html: '<div class="customLabel text-center"><label class="control-label">合计</label></div><div class="customInput text-center"><input type="textBox" name="ztzHj" readonly="true" style="text-align: right" decimalPlaces=2></div>'
                                            },
                                            {
                                                title: '<div class="customLabel"><label class="control-label">建筑安装投资</label></div><div class="customInput"><input type="decimal" name="ztzJzaztz" onchange="sum()" oninput="sum()" onblur="sum()"style="text-align: right" decimalPlaces=2></div>',
                                                name: "",
                                                decimalPlaces:2,
                                                type: "html",
                                                rowspan: 2,
                                                html: '<div class="customLabel"><label class="control-label">设备投资</label></div><div class="customInput"><input type="decimal" name="ztzSbtz" onchange="sum()" oninput="sum()" onblur="sum()"style="text-align: right" decimalPlaces="2"></div>'
                                            },
                                            {
                                                title: '<div class="customLabel"><label class="control-label">待摊投资</label></div><div class="customInput"><input type="decimal" name="ztzDttz" onchange="sum()" oninput="sum()" onblur="sum()" style="text-align: right" decimalPlaces="2"></div>',
                                                name: "",
                                                type: "html",
                                                rowspan: 2,
                                                html: '<div class="customLabel"><label class="control-label">其他投资</label></div><div class="customInput" ><input type="decimal" name="ztzQttz" onchange="sum()" oninput="sum()" onblur="sum()" style="text-align: right" decimalPlaces="2"s></div>'
                                            },

                                            {
                                                title: "项目资金来源",
                                                name: "",
                                                type: "html",
                                                rowspan: 2,
                                                html: '<div class="customLabel text-center"><label class="control-label">合计</label></div><div class="customInput text-center"><input type="textBox" name="xmzjlyHj" readonly="true" style="text-align:right" decimalPlaces="2"></div>'
                                            },
                                            {
                                                title: '<div class="customLabel"><label class="control-label">财政性资金</label></div><div class="customInput"><input type="decimal" name="xmzjlyCzxzj" onchange="sumXm()" oninput="sumXm()" onblur="sumXm()" style="text-align: right" decimalPlaces="2"></div>',
                                                name: "",
                                                type: "html",
                                                rowspan: 2,
                                                html: '<div class="customLabel"><label class="control-label">资源平衡</label></div><div class="customInput"><input type="decimal" name="xmzjlyZyph" onchange="sumXm()" oninput="sumXm()" onblur="sumXm()" style="text-align: right" decimalPlaces="2"></div>'
                                            },
                                            {
                                                title: '<div class="customLabel"><label class="control-label">其他</label></div><div class="customInput"><input type="decimal" name="xmzjlyQt" onchange="sumXm()" oninput="sumXm()" onblur="sumXm()" style="text-align: right" decimalPlaces="2"></div>',
                                                name: "",
                                                type: "html",
                                                rowspan: 2,
                                                html: '<div class="customLabel"><label class="control-label">--</label></div><div class="customInput text-center">--</div>'
                                            },

                                            {
                                                title: "项目评审建议",
                                                name: "xmphjy",
                                                type: "html",
                                                rowspan: 3,
                                                colspan: 2,
                                                html: '<div class="customInput"><input type="textBox" data-multiline="true" name="xmphjy"  data-height="84" disabled="true" setReadonly="true"></div>'
                                            },
                                            {title: "投资限额", name: "tzxe", type: "decimal" ,disabled:"disabled",textAlign:'right',setReadonly:true},
                                            {title: "建议控制造价标准(元)", name: "jykzzjbz", type: "decimal",disabled:"disabled",textAlign:'right',setReadonly:true},
                                            {title: "核减金额", name: "hjje", id:"hjjeId",type: "textBox",disabled:"true",textAlign:'right', setReadonly:true}
                                        ]
                                    },
                                    {
                                        "dtype":"html",
                                        "dragHtml":'<div style="padding:20px 0 10px 15px;"><b>附件列表：</b><button type="button" class="bootstrap-table-upload" id="scFjId" onclick="pageUploadFile()">上传附件</button>&nbsp;<button type="button" class="bootstrap-table-delete" id="deleteFileId" onclick="deleteFile()">删除附件</button></div>'
                                    },
                                    {
                                        "dtype": "dtable",
                                        "height": 250,
                                        "id":"uploadTable",
                                        editable :true,
                                        "url": "xmqqch/getXmqqchfj.do",
                                        "clickToSelect": true,
                                        "columns": [
                                        	{radio: true, width: 20}, 
	                                        //{field : 'GUID',title : 'guid',visible : false},
											{field : 'FILEID',title : 'guid',visible : false},
                                            {field:"xh", title:"序号", width:40, align:"center", formatter:function(value,row,index){return index+1;}},
                                            {field:"FILENAME", title:"附件名", width:300, align:"center"},
                                            {field:"FILEDL", title:"附件大类", width:120, align:"center",type:'comboBox',selected: 1,localdata:[{id:1,text:"前期策划申请"}],formatter:function(value,row,index){return "前期策划申请";}},
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
                            }
                        ],
                        "dtype": "row"
                    }
                ],
                "dtype": "body"
            }
        ];
        function pageUploadFile() {
            uploadfile({server:getRootPath()+'/file/uploader.do?filebstype=1'}, function (rowData) {
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

        function queryParams(params){
            return{
                //如果需要后端进行分页 limit 和offset是必须参数
                limit:params.limit,
                offset:params.offset,
            }
        }
        function delRow(){
            var ids = '';
            var data = $("#teb").dtable("getSelections");
            if(data.length==0){
                $.dalert({text:'请选择数据',icon:7});
                return;
            }
            
            if(data.length>0){
                for(var i = 0;i<data.length;i++){
                    ids +=data[i].zxxmid+",";
                    if(data[i].lchj!='-1'){
                    	$.dalert({text:'选中数据中包含审核中或完成审核数据，不允许删除！',icon:7});
                    	return;
                    }
                }
            }
            
            layer.confirm('确定删除吗？', {
                btn: ['确定','取消'] //按钮
            }, function(){
                $.dajax({
                    type:'post',
                    url:"xmqqch/delXmqqch.do",
                    data:{ids:ids.substring(0, ids.length-1)},
                    dataType:'json',
                    success:function (data) {
                        if(data.success){
                            $.dalert({text:'删除成功',icon:1});
                            $('#teb').dtable('refresh');
                        }else{
                            $.dalert({text:'删除失败',icon:1});
                        }
                    }
                })
            });
        }

        function addRow() {
            isEdit = 0;
            dopenIndex=$.dopen({
                type: 6,
                title: "项目前期策划受理单申请",
                area: ["98%", "100%"],
                content: dopenJson,
                btn: ["关闭"],
                btn1:function(index){
                	
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
        }

        function editRow(){
            isEdit = 1;
            var data = $("#teb").dtable("getSelections");

            var lchj=data[0].lchj;
           
                if(data.length==0){
                $.dalert({text:'请选择数据',icon:7});
                return;
            }else if(data.length>1){
                $.dalert({text:'最多只能选择一行数据',icon:7});
                return;
            }else{
                if(lchj==-1){
                zxXmid=data[0].zxxmid;
                xmqqchid = data[0].id;
                var guid=data[0].id1;
                $('#sldh').val(data[0].sldh);
                dopenIndex = $.dopen({
                    type: 6,
                    title: "项目前期策划受理单申请",
                    area: ["95%", "100%"],
                    content: dopenJson,
                    btn: ["关闭"],
                    btn1:function(index){
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
                   var jzaztz=  $("input[name=ztzJzaztz]").parents(".ztreeBox_inp").find(".form-control")["decimal"]("setValue",data[0].ztz_jzaztz);
                   var sbtz= $("input[name=ztzSbtz]").parents(".ztreeBox_inp").find(".form-control")["decimal"]("setValue",data[0].ztz_sbtz);
                   var dttz= $("input[name=ztzDttz]").parents(".ztreeBox_inp").find(".form-control")["decimal"]("setValue",data[0].ztz_dttz);
                   var qttz= $("input[name=ztzQttz]").parents(".ztreeBox_inp").find(".form-control")["decimal"]("setValue",data[0].ztz_qttz);
                   var czxzj= $("input[name=xmzjlyCzxzj]").parents(".ztreeBox_inp").find(".form-control")["decimal"]("setValue",data[0].xmzjly_czxzj);
                   var zyph= $("input[name=xmzjlyZyph]").parents(".ztreeBox_inp").find(".form-control")["decimal"]("setValue",data[0].xmzjly_zyph);
                   var qt= $("input[name=xmzjlyQt]").parents(".ztreeBox_inp").find(".form-control")["decimal"]("setValue",data[0].xmzjly_qt);
                   var result=parseFloat(data[0].ztz_jzaztz)+parseFloat(data[0].ztz_sbtz)+parseFloat(data[0].ztz_dttz)+parseFloat(data[0].ztz_qttz);
                   var result1=parseFloat(data[0].xmzjly_czxzj)+parseFloat(data[0].xmzjly_zyph)+parseFloat(data[0].xmzjly_qt);
                    if(isNaN(result)==true){
                        $("input[name=ztzHj]").parents(".ztreeBox_inp").find(".form-control")["textBox"]("setValue","");
                        $("#hjjeId").textBox("setValue",0-data[0].tzxe.toFixed(2));
                        ztzhjz=0;  
                    }else {
                        $("input[name=ztzHj]").parents(".ztreeBox_inp").find(".form-control")["textBox"]("setValue",result.toFixed(2));
                        $("#hjjeId").textBox("setValue",result.toFixed(2)-data[0].tzxe.toFixed(2));
                        ztzhjz=result.toFixed(2);
                    }
                   if(isNaN(result1)==true){
                       $("input[name=xmzjlyHj]").parents(".ztreeBox_inp").find(".form-control")["textBox"]("setValue","");
                       ztzzjlyz=0;
                   }else{
                       $("input[name=xmzjlyHj]").parents(".ztreeBox_inp").find(".form-control")["textBox"]("setValue",result1.toFixed(2));
                       ztzzjlyz=result1.toFixed(2);
                   }
                   sum();
                },0);
                $("#openForm").dform('load',data[0]);
                $("#openForm").dform("setValueByName", "jykzzjbz", data[0].jykzzjbz );
                $("#uploadTable").dtable("refreshOptions",{url:"xmqqch/getXmqqchfj.do?lx=1&guid="+guid});
                }else {
                    zxXmid=data[0].zxxmid;
                    xmqqchid = data[0].id;
                    var guid=data[0].id1;
                    $('#sldh').val(data[0].sldh);
                    $.dopen({
                        type: 6,
                        title: "项目前期策划受理单申请",
                        area: ["95%", "100%"],
                        content: dopenJson,
                        btn: ["关闭"],
                        btn1:function(index){
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
                        var jzaztz=  $("input[name=ztzJzaztz]").parents(".ztreeBox_inp").find(".form-control")["decimal"]("setValue",data[0].ztz_jzaztz);
                        var sbtz= $("input[name=ztzSbtz]").parents(".ztreeBox_inp").find(".form-control")["decimal"]("setValue",data[0].ztz_sbtz);
                        var dttz= $("input[name=ztzDttz]").parents(".ztreeBox_inp").find(".form-control")["decimal"]("setValue",data[0].ztz_dttz);
                        var qttz= $("input[name=ztzQttz]").parents(".ztreeBox_inp").find(".form-control")["decimal"]("setValue",data[0].ztz_qttz);
                        var czxzj= $("input[name=xmzjlyCzxzj]").parents(".ztreeBox_inp").find(".form-control")["decimal"]("setValue",data[0].xmzjly_czxzj);
                        var zyph= $("input[name=xmzjlyZyph]").parents(".ztreeBox_inp").find(".form-control")["decimal"]("setValue",data[0].xmzjly_zyph);
                        var qt= $("input[name=xmzjlyQt]").parents(".ztreeBox_inp").find(".form-control")["decimal"]("setValue",data[0].xmzjly_qt);
                        var result=parseFloat(data[0].ztz_jzaztz)+parseFloat(data[0].ztz_sbtz)+parseFloat(data[0].ztz_dttz)+parseFloat(data[0].ztz_qttz);
                        var result1=parseFloat(data[0].xmzjly_czxzj)+parseFloat(data[0].xmzjly_zyph)+parseFloat(data[0].xmzjly_qt);
                        if(isNaN(result)==true){
                            $("input[name=ztzHj]").parents(".ztreeBox_inp").find(".form-control")["textBox"]("setValue","");
                        	$("#hjjeId").textBox("setValue",0-data[0].tzxe.toFixed(2));
                        	ztzhjz=0; 
                        }else {
                            $("input[name=ztzHj]").parents(".ztreeBox_inp").find(".form-control")["textBox"]("setValue",result.toFixed(2));
                        	$("#hjjeId").textBox("setValue",result.toFixed(2)-data[0].tzxe.toFixed(2));
                        	 ztzhjz=result.toFixed(2); 
                        }
                        if(isNaN(result1)==true){
                            $("input[name=xmzjlyHj]").parents(".ztreeBox_inp").find(".form-control")["textBox"]("setValue","");
                            ztzzjlyz=0;
                        }else{
                            $("input[name=xmzjlyHj]").parents(".ztreeBox_inp").find(".form-control")["textBox"]("setValue",result1.toFixed(2));
                            ztzzjlyz=result1.toFixed(2);
                        }
                        sum();
                    },0);
                    $("#openForm").dform('load',data[0]);
                    $("#uploadTable").dtable("refreshOptions",{url:"xmqqch/getXmqqchfj.do?lx=1&guid="+guid});
                    $("#openForm").dform("setReadonly", true);
                    $("#teb").dtable("refresh");
                    disableButton(['deleteFileId','scFjId','saveZxAndFile','savaSubmits','clearForm']);
                }
            }
        }
        function disableButton(arr) {
	        $.each(arr,function (i,v) {
	            $('#'+v).attr('disabled',true);
	        })
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



        function saveZxAndFile() {
        	var fileIds="";
            if(isEdit==1){
                $("#openForm").dform("setValueByName","zxXmid",zxXmid);
                
            }
            
            if(ztzhjz!=ztzzjlyz){
            	$.dalert({text:'总投资金额需要和资金来源金额相等，请检查！',icon:7});
            	return ;
            }
            //判断是否为必填项
            var flag=$("#openForm").dform("validate");
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
    
            if(flag){
                $.dajax({
                    url: "xmqqch/saveZx.do",
                    type: "POST",
                    data:$.extend($("#openForm").dform('getData'),{"fileId":fileIds}),
                    dataType: "json",
                    success: function (data) {
                        if (data.success) {
                            $.dalert({text:'保存成功',icon:1});
                            var result=data.content.split(",");
                          
                            //前期策划主键
                            $("#openForm").dform("setValueByName","id1",result[0]);
                            //项目主键
                            $("#openForm").dform("setValueByName","id",result[1]);
                            $("#openForm").dform("setValueByName","zxXmid",result[1]);
                            $("#teb").dtable("refresh");
                           
                        }
                    }
                });
            }
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
        function savaSubmit() {
        	var fileIds="";
        	
            if(ztzhjz!=ztzzjlyz){
            	$.dalert({text:'总投资金额需要和资金来源金额相等，请检查！',icon:7});
            	return ;
            }
            if(isEdit==1){
                $("#openForm").dform("setValueByName","zxXmid",zxXmid);
            }
            //判断是否为必填项
            var flag=$("#openForm").dform("validate");
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
    
            if(flag){
                $.dajax({
                    url: "xmqqch/saveZx.do",
                    type: "POST",
                    data:$.extend($("#openForm").dform('getData'),{"fileId":fileIds}),
                    dataType: "json",
                    success: function (data) {
                        if (data.success) {
                        	var result=data.content.split(",");
                            
                            //前期策划主键
                            $("#openForm").dform("setValueByName","id1",result[0]);
                            //项目主键
                            $("#openForm").dform("setValueByName","id",result[1]);
                            $("#openForm").dform("setValueByName","zxXmid",result[1]);
//                             $.dalert({text:'保存成功',icon:1});
                            var guids='';
//                          var data = $("#teb").dtable("getSelections");
//                          if(data.length>0){
//                              for(var i = 0;i<data.length;i++){
//                                  guids +=data[i].id1+",";
//                              }
//                          }
							var openFormData1=$("#openForm").dform('getData');
             				guids=openFormData1.id1;
                             $.dajax({
                                 url:"xmqqch/submitZx.do",
                                 type:"POST",
                                 data:{guids:guids},
                                 success:function (data) {
                                     if(data.success){
                                     	
                                         $.dalert({text:'提交成功',icon:1});
                                         $('#teb').dtable('refresh');
                                         layer.close(dopenIndex);
                                     }else{
                                         $.dalert({text:data.content,icon:1});
                                     }
                                 }
                             })
//                             $("#teb").dtable("refresh");
                           
                        }
                    }
                });
            }
        	      	
        	
               

        }

        function submitRow() {
            var guids='';
            var data = $("#teb").dtable("getSelections");
            if(data.length>0){
                for(var i = 0;i<data.length;i++){
                	guids +=data[i].id1+",";
                }
            }
            if(data.length==0){
                $.dalert({text:'请选择数据',icon:7});
                return;
            }
         
            if(data[0].lchj!='-1'){
            	$.dalert({text:'该项目已经提交，请勿重复提交！',icon:7});
                return;
            }
            layer.confirm('确定提交吗？', {
                btn: ['确定', '取消'] //按钮
            },function () {
//                 $('#sldh').val(data[0].sldh);
                $.dajax({
                    url:"xmqqch/submitZx.do",
                    type:"POST",
                    data:{guids:guids},
                    success:function (data) {
                        if(data.success){
                            $.dalert({text:'提交成功',icon:1});
                            $('#teb').dtable('refresh');
                            layer.close(dopenIndex);
                        }else{
                            $.dalert({text:data.content,icon:1});
                        }
                    }
                })
            });

        }
//获得建设单位树
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
//获得项目类型树
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
        function zt(value){
            if(value==-1){
                value="未提交"
            }else if (value==0){
                value="已审核"
            }else{
                value="审核中"
            }
             return value;
        }

        function sum(){
            var jzaztz=$("input[name=ztzJzaztz]").val() || 0;
            var sbtz=$("input[name=ztzSbtz]").val() || 0;
            var dttz=$("input[name=ztzDttz]").val() || 0;
            var qt=$("input[name=ztzQttz]").val() || 0;
            var result=parseFloat(jzaztz)+parseFloat(sbtz)+parseFloat(dttz)+parseFloat(qt);
            $("input[name=ztzHj]").prev().val(result.toFixed(2));
            ztzhjz  =result.toFixed(2);
        }
        function sumXm(){
            var xczxzj=$("input[name=xmzjlyCzxzj]").val() || 0;
            var xzjph=$("input[name=xmzjlyZyph]").val() || 0;
            var xqt=$("input[name=xmzjlyQt]").val() || 0;
            var result=parseFloat(xczxzj)+parseFloat(xzjph)+parseFloat(xqt);
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

	    
	    function selThyj(value){
	    	var xmqqchid=value;
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
		            {title: "退回意见：", id:"textBox4",name: "textBox4", type: "textBox", multiline:true, height: 150, colspan:2},
		        ]
		    });
		    $.dajax({
	               type:'post',
	               url:'xmqqch/getThyj.do',
	               data:{
	               		id:xmqqchid
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