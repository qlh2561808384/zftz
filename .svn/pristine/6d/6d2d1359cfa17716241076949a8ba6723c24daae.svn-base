<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
    <base href="<%=basePath%>">
    <title>岗位对归口</title>
    <style>
        body, div {
            margin: 0;
            padding: 0;
        }
        .demo {
            display: inline-block;
            width: 48%;
            box-sizing: border-box;
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
        }

        .demo .demo-tit {
            height: 38px;
            line-height: 38px;
            font-size: 14px;
            /*border-radius: 4px 4px 0 0;*/
            border-right: 1px solid #4A68A5;
            background: #7892C7;
            color: #fff;
            margin: 0;
            padding: 0;
            text-indent: 10px;
        }

        .demo .demo-tree {
            background: #FBFBFB;
            border: 1px solid #D4DBE5;
            padding: 20px 30px;
        }

        .demo:nth-child(2) .demo-tree {
            background: #EDECFB;
            border: 1px solid #BBC6D6;
        }
        body div.container-fluid{
            border: 1px solid #DDDDDD;
            background: #F5F5F5;
            box-sizing: border-box;
            padding: 20px 15px 10px 25px;
            margin:0 10px 0 15px;
        }
        .demo .demo-tit {
            margin: 10px 10px 0 0;
            border-radius: 4px 4px 0 0;
        }
        .demo .demo-tree {margin-right:10px;}
    </style>
    <script src="bootstrap2/js/jquery.js"></script>
    <script src="bootstrap2/js/bootstrap.datanew.js"></script>
    <script type="text/javascript">

        var postTree;
        var menuTree
        $(function () {
            menuTree = $("#contractTee").dtree({
            	data:{
					key : {name:"NAME"},
					simpleData :{
						enable : true,
						idKey : "ID",
						pIdKey : "PID",
						rootPId : 0
					}
				},
                checkType: "checkbox", //选择框类型，值为checkbox或radio或nocheck，默认为nocheck
                isOpen: true, //节点是否全部展开，默认为不展开
                ISLEAF: true,
                rootElement: true,  //是否添加根节点“全部”
                url: "user/getGkTree.do",
            });

            postTree = $("#postTree").dtree({
            	data:{
					key : {name:"NAME"},
					simpleData :{
						enable : true,
						idKey : "ID",
						pIdKey : "PID",
						rootPId : 0
					}
				},
                checkType: "radio", //选择框类型，值为checkbox或radio或nocheck，默认为nocheck
                isOpen: true, //节点是否全部展开，默认为不展开
                ISLEAF: true,
                rootElement: false,  //是否添加根节点“全部”
                url: "user/getPostTree.do",
                callback: {
                    onCheck: getMenu
                }
            });
        });

        var curPostId;
        function getMenu(event, treeId, treeNode) {
            curPostId = treeNode.ID;
            $.dajax({
                data : {postId : curPostId},
                url : "user/getPostGk.do",
                success : function(data) {
                    if (data.success) {
                        menuTree.checkAllNodes(false);
                        var menuIds = data.content;
                        menuIds = menuIds.split(",");
                        for (var i = 0; i < menuIds.length; i++) {
                            if (menuIds[i] != null && menuIds[i] != "") {
                                var checking = menuTree.getNodeByParam('ID', menuIds[i], null);
                                if(checking!=null){
                                	menuTree.checkNode(checking, true, false);
                                }
                                
                            }
                        }
                    }
                }
            });
        }

        function save() {
            var menuIds = "";
            var checkedNodes = menuTree.getCheckedNodes();
            $.each(checkedNodes, function(i, node) {
                menuIds +=  this.ID + ",";
            });

            $.dajax({
                data : {postId : curPostId, gks : menuIds},
                url : "user/savePostToGk.do",
                success : function(data) {
                    if (data.success) {
                        $.dalert({text : data.content, icon : 1});
                    } else {
                        $.dalert({text : data.content, icon : 2});
                    }
                }
            });
        }
    </script>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <button type="submit" class="bootstrap-table-submit" onclick="save()">保存
        </button>
    </div>

    <div class="row">
        <div class="col-xs-5 demo">
            <div class="row"><div class="demo-tit">岗位信息</div></div>
            <div class="row">
                <ul id="postTree" class="demo-tree"></ul>
            </div>
        </div>
        <div class="col-xs-7 demo">
            <div class="row"><div class="demo-tit">归口信息</div></div>
            <div class="row">
                <ul id="contractTee" class="demo-tree"></ul>
            </div>
        </div>
    </div>
</div>
</body>
</html>
