<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
    <base href="<%=basePath%>">
    <title>选项表维护</title>
    <link rel="stylesheet" href="css/common.css"/>
    <script src="./bootstrap2/js/jquery.js"></script>
    <script src="./bootstrap2/js/bootstrap.datanew.js"></script>
    <script>
        var nodeData = {};
        $(function () {
            var json = [{
                "dtype": "html",
                "dragHtml": '<div class="toolbar layout_toolbar" id="toolbar"><button id="tool-add" class="bootstrap-table-add" type="button" onclick="add()">新增</button><button id="tool-edit" class="bootstrap-table-edit" type="button" onclick="editbtn()">修改</button><button id="tool-save" class="bootstrap-table-save" type="button" onclick="savebtn()">保存</button><button id="tool-cancel" class="bootstrap-table-cancel" type="button" onclick="resetbtn()">取消</button><button id="tool-delete" class="bootstrap-table-delete" type="button" onclick="delbtn()">删除</button>' +
                '</div>'
            },{
                "dtype": "body",
                "classes": "layout_treeTable",
                "plug": [
                    {
                        "plug": [{
                            "colspan": "3",
                            "dtype": "column",
                            "classes": "selectTree",
                            "plug":[{
                                id:"treeDemo",
                                "dtype": "tree",
                                "rootElement":true,
                                "url": "xtPc/queryXxb.do",
                                "outerHeight": $(window).height() - 60,
                                "callback": {
                                    clickNode: function (e, treeId, treeNode) {
                                        if(treeNode.level==0){$('#formDemo').dform('clear');return false;}
                                        $.extend(nodeData,treeNode);
                                        if ($('#tool-save').css('display')=='none') {
                                            $('#formDemo').dform('load',treeNode);
                                        }else{
                                            $.dconfirm({text:"是否需要保存？",btn:["保存","取消"],funs:[function(index){
                                                layer.close(index);
                                                savebtn();
                                            },function (index) {
                                                disableBtn(['#tool-cancel','#tool-save']);
                                                $('#formDemo').dform('setDisabled',true);
                                                $('#formDemo').dform('load',treeNode);
                                                layer.close(index);
                                            }]});
                                        }
                                    }
                                },
                                "data": {
                                    key: {name: "mc"},
                                    simpleData: {
                                        enable: true,
                                        idKey: "bm",
                                        pIdKey: "fjbm",
                                        rootPId: "0"
                                    }
                                },
                            }],
                        },
                            {
                                "plug": [
                                    {
                                        id:'formDemo',
                                        "dtype": "dform",
                                        "inputs": [
                                            {
                                                "name": "id",
                                                "type": "hidden"
                                            }, {
                                                "title": "父级编码",
                                                "id":"fjbm",
                                                "name": "fjbm",
                                                "type": "textBox"
                                            },
                                            {
                                                "title": "编码:",
                                                "name": "bm",
                                                "type": "textBox",
                                                "required":"true"
                                            },
                                            {
                                                "title": "名称:",
                                                "name": "mc",
                                                "type": "textBox",
                                                "required":"true"
                                            },
                                            {    "title":"是否有效:",
                                                "id":"yxbz",
                                                "name":"yxbz",
                                                "type":"comboBox",
                                                "localdata":[{"id":"Y","text":"有效"},{"id":"N","text":"无效"}]
                                            },
                                            {
                                                "title": "类型:",
                                                "name": "lx",
                                                "type": "textBox",
                                                "required":"true"
                                            },
                                            {
                                                "title": "描述:",
                                                "name": "ms",
                                                "type": "textBox",
                                                "multiline":true,
                                                colspan:2
                                            },
                                            {
                                                "title": "field:",
                                                "name": "field",
                                                "type": "hidden"
                                            }
                                        ],
                                        "formtitle": "基础数据信息",
                                        "rownum": "2",
                                        "labelwidth": "150px",
                                        "showtitle": "left",
                                        "labelAlign": "right"
                                    }
                                ],
                                "colspan": "9",
                                "dtype": "column",
                                "classes": "selectCon"
                            }
                        ],
                        "dtype": "row"
                    }]
            }];

            $.initPage(json);
            resetbtn();
        });
    </script>
</head>
<body>

</body>
<script type="text/javascript">
    // 取消
    function resetbtn() {
        disableBtn(['#tool-cancel','#tool-save']);
        $('#formDemo').dform('clear');
        $('#formDemo').dform('setDisabled',true);
        var selectedNode = $("#treeDemo").tree("getTree").getSelectedNodes();
        if(selectedNode.length>0 && selectedNode[0].level!=0){
            $('#formDemo').dform('load',nodeData);
        }
    }


    function add() {
        var selectNode = $("#treeDemo").tree("getTree").getSelectedNodes();

        if(selectNode==null||selectNode==""){
            $.dalert({text:"请选择树节点",icon:7});
            return;
        }

        $('#formDemo').dform('setDisabled',false);
        $('#formDemo').dform('clear');
        if(selectNode[0].id=="0"){
            fjbm='0';
        }else{
            fjbm=selectNode[0].bm;
        }

        // //设置表单默认值
        $('#formDemo').dform('load',{fjbm:fjbm});
        $("#fjbm").textBox("disable");
        $("#yxbz").comboBox("setValue","Y");
        enableBtn(['#tool-cancel','#tool-save']);
    }

    function editbtn() {
        var selectedNode = $("#treeDemo").tree("getTree").getSelectedNodes();
        if (selectedNode.length == 0 || selectedNode[0].level==0) {
            $.dalert({text: "未选择要修改的选项",icon: 7});
            return;
        }
        $('#formDemo').dform('setDisabled',false);
        $("#fjbm").textBox("disable");
        enableBtn(['#tool-cancel','#tool-save']);
    }

    function savebtn() {
        if ($("#formDemo").dform("validate")) {
            // $.dajax({
            //     url: "xtPc/validateMc.do",
            //     data:  $("#formDemo").dform('getData'),
            //     success: function (data) {
            //         if (data.success) {
            //
            //         } else {
            //             return $.dalert({text: data.content, icon: 7});
            //         }
            //     }
            // });
            $.dajax({
                type : "post",
                url : $("#formDemo").dform('getData').id==''?'xtPc/saveXxb.do':'xtPc/updateXxb.do',
                data : $("#formDemo").dform('getData'),
                success: function (data) {
                    if (data.success) {
                        disableBtn(['#tool-cancel','#tool-save']);
                        $.dalert({text: data.content, icon: 1});
                        $("#treeDemo").tree("reload", {url:'xtPc/queryXxb.do',queryParam:{}});
                        $('#formDemo').dform('setDisabled',true);
                    } else {
                        $.dalert({text: data.content, icon: 2});
                    }
                }
            });


        }
    }

    function delbtn() {
        var selectedNode = $("#treeDemo").tree("getTree").getSelectedNodes();
        if (selectedNode.length == 0 || selectedNode[0].level==0) {
            $.dalert({text: "未选择要删除的选项",icon: 7});
            return;
        }
        $.dconfirm({
            text: "确定要删除此选项信息？其下的选项也会被删除！", btn: ["确定", "取消"], funs: [function () {
                var nodes = [];
                nodes = getChildrens(nodes,selectedNode[0]);
                for(var i=0;i<nodes.length;i++){
                    $.dajax({
                        type: "POST",
                        url: "xtPc/deleteXxb.do",
                        data: {
                            id: nodes[i].id
                        },
                        dataType: "json",
                        success: function (data) {
                            if(data.success){
                                if(i==nodes.length){
                                    $.dalert({icon:1,text: data.content});
                                    $("#treeDemo").tree("getTree").removeNode(selectedNode[0]);
                                    $('#formDemo').dform('clear');
                                }
                            }else{
                                $.dalert({text:data.content,icon:2});
                            }
                        }
                    });
                }
            }]
        });
    }

    // 按钮状态调整 显示/隐藏
    function enableBtn(obj) {
        $('#toolbar button').css('display','none');
        if (obj) {
            for (var i in obj) {
                $(obj[i]).css('display', 'inline');
            }
        }
    }
    function disableBtn(obj) {
        $('#toolbar button').css('display','inline');
        if (obj) {
            for (var i in obj) {
                $(obj[i]).css('display', 'none');
            }
        }
    }
    //获取某个节点下的所有子节点
    function getChildrens(nodes,node) {
        nodes.push(node);
        if(node.isParent){
            for(var obj in node.children){
                getChildrens(nodes,node.children[obj]);
            }
        }
        return nodes;
    }

    function disableTextBox() {
        $.each(arguments,function (i,v) {
            $(v).textBox('disable');
        })
    }
</script>
</html>
