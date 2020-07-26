<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
	<base href="<%=basePath%>">
	<title>菜单管理</title>
	<link rel="stylesheet" href="css/common.css"/>
	<script src="./bootstrap2/js/jquery.js"></script>
	<script src="./bootstrap2/js/bootstrap.datanew.js"></script>
	<script type="text/javascript">
        var islock = false;
        var lltt;
        $(function() {
            resetbtn();
            $('#fm').dform('clear');
            $('#fm').dform('disabled');

            $.ajax({
                type:'post',
                url:"user/getMenuInformation.do",
                data:{start:-1,limit:-1},
                dataType:'json',
                success:function (data) {
                    $("#parentid").searchTree({
                        localdata:data.rows,
                        data:{
                            key : {name:"MENUNAME"},
                            simpleData :{
                                enable : true,
                                idKey : "MENUID",
                                pIdKey : "PARENTID",
                                rootPId : "0"
                            }
                        },
                        modalTitle: '查找名称', //模态框标题
                        checkType: 'radio', //选择框类型，值为checkbox或radio或nocheck，默认为nocheck
                        ISLEAF: true, //checkType为checkbox或radio时，非叶子节点是否有选择框，false为不可选
                    });
                    $('#parentid').searchTree("disable");

                    $("#lefttree").tree({
                        localdata:data.rows,
                        rootElement:true,
                        data: {
                            key: {name: "MENUNAME"},
                            simpleData: {
                                enable: true,
                                idKey: "MENUID",
                                pIdKey: "PARENTID",
                                rootPId: "0"
                            }
                        },
                        callback: {
                            clickNode: function (e, treeId, treeNode) { //节点的点击事件
                                var id = treeNode.MENUID;

                                if(treeNode.level=='0' || id==undefined){
                                    return false;
                                }
                                if (!islock) {
                                    var menuInfo = {};
                                    for(var key in treeNode){
                                        var k = key.toLowerCase();
                                        menuInfo[k] = treeNode[key];
                                    }
                                    $.dloadformdata($("#fm"), menuInfo);

                                    //设置父级菜单值
                                    $('#parentid').searchTree("setValue",treeNode.PARENTID);
                                    var imenu = {};
                                    var udata = data.rows;
                                    for(var i=0;i<udata.length;i++){
                                        if(udata[i].MENUID==treeNode.PARENTID){
                                            imenu = udata[i];
                                        }
                                    }
                                    $('#parentid').searchTree("setText",imenu.MENUNAME);

                                }
                            }
                        }
                    });
                    lltt =$("#lefttree").tree('getTree')
                }
            })

        });


        var stuts = 0;
        function add() {
            stuts = -1;
            var selectNode = lltt.getSelectedNodes();
            clearForm("#fm");
            $('#fm').dform('enable');
            if(selectNode.length==0 || selectNode[0].level==0){
                $('#parentid').searchTree("disable");
                $('#parentid').searchTree("setValue",'0');

            }else{
                $('#parentid').searchTree("setValue",selectNode[0].MENUID);
                $('#parentid').searchTree("setText",selectNode[0].MENUNAME);

            }
            enableBtn('savebtn', 'cancelbtn');
        }

        function reloadMenuTree() {
            $.ajax({
                type:'post',
                url:'user/getMenuInformation.do',
                data:{start:-1,limit:-1},
                dataType:'json',
                success:function (data) {
                    $("#lefttree").tree("reload", {localdata:data.rows});
                }
            })
        }

        function savebtn() {
            if (!$("#fm").dform("validate")) {
                return;
            }
            var formData = $("#fm").dform('getData');
            var selectedNode = lltt.getSelectedNodes();

            //新增时处理parentid
            if(!editflag){
                if(selectedNode.length==0 || selectedNode[0].level==0){
                    formData.parentid = '0';
                }else{
                    formData.parentid = formData.parentid==''?selectedNode[0].MENUID:formData.parentid;
                }
            }else{
                formData.parentid = formData.parentid==''?'0':formData.parentid;
            }

            //处理编辑时选择父节点为不可用,其下所有子节点改为不可用
            if(editflag && selectedNode[0].children && formData.enable=='0'){
                selectedNode[0].children.push(selectedNode[0]);
                $.each(selectedNode[0].children,function (index,value) {
                    var cdata = {
                        menuid:value.MENUID,
                        parentid:value.PARENTID,
                        enable:formData.enable,
                        menuicon:value.MENUICON,
                        menuname:value.MENUNAME,
                        menutype:value.MENUTYPE,
                        menuurl:value.MENUURL
                    };
                    $.ajax({
                        type:'post',
                        url:'user/saveMenu.do',
                        data:$.extend(cdata,{stuts:0}),
                        dataType:'json',
                        success:function (data) {
                            if(index==selectedNode[0].children.length-1 && data.success){
                                $.dalert({text: '修改成功', icon: 1});
                                reloadMenuTree();
                                resetbtn();
                                editflag = false;
                            }
                        }
                    })
                })
            }else{
                $.dajax({
                    cache : true,
                    type : "POST",
                    url : "user/saveMenu.do",
                    data : $.extend(formData,{stuts:stuts}),
                    success: function (data) {
                        if (data.success) {
                            resetbtn();
                            if (editflag) {
                                $.dalert({text: '修改成功', icon: 1});
                                reloadMenuTree();
                                editflag = false;
                            } else {
                                $.dalert({text: '保存成功', icon: 1});
                                reloadMenuTree();
                            }
                        } else {
                            $.dalert({text: data.content, icon: 2});
                        }
                    }
                });
            }

        }

        var editflag = false;
        function edit() {
            stuts = 0;
            var selectedNode = lltt.getSelectedNodes();
            if (selectedNode.length == 0 || selectedNode[0].level==0) {
                $.dalert({
                    text: "请选择要修改的菜单",
                    icon: 7
                });
            } else {
                $('#fm').dform('enable');
                enableBtn('savebtn', 'cancelbtn');
                editflag = true;
            }
        }

        function del() {
            var selectedNode = lltt.getSelectedNodes();

            if (selectedNode.length == 0 || selectedNode[0].level==0) {
                $.dalert({
                    text: "未选择要删除的菜单",
                    icon: 7
                });
            } else {
                $.dconfirm({
                    text: "确定要删除此菜单信息？其下的菜单也会被删除！", btn: ["确定", "取消"], funs: [function () {
                        var nodes = lltt.getNodesByParam('PARENTID',selectedNode[0].MENUID,selectedNode[0]);
                        nodes.push(selectedNode[0]);
                        for(var i=0;i<nodes.length;i++){
                            $.dajax({
                                type: "POST",
                                url: "user/delMenu.do",
                                data: {
                                    menuid: nodes[i].MENUID
                                },
                                dataType: "json",
                                success: function (data) {
                                    if(data.success){
                                        $.dalert({
                                            text: data.content
                                        });
                                        lltt.removeNode(selectedNode[0]);
                                        clearForm("#fm");
                                    }else{
                                    	$.dalert({
                                            text: data.content,icon:2
                                        });
                                    }
                                }
                            });
                        }
                    }, function () {
                        $.dalert({text: "取消操作", icon: 7});
                    }]
                });
            }
        }

        function enableBtn() {
            islock = true;
            $('button[type="button"]').css('display', 'none');
            if (arguments) {
                for (var i in arguments) {
                    $('#' + arguments[i]).css('display', 'inline');
                }
            }
        }

        function resetbtn() {
            islock = false;
            $('#fm').dform('disabled');
            $('button[type="button"]').css('display', 'inline');
            $('#cancelbtn').css('display', 'none');
            $('#savebtn').css('display', 'none');
        }

        function clearForm(form) {
            $(':input', form).each(function () {
                var type = this.type;
                var tag = this.tagName.toLowerCase();
                if (type == 'text' || type == 'password' || tag == 'textarea' || type == 'number')
                    this.value = "";
                else if (type == 'checkbox' || type == 'radio')
                    this.checked = false;
                else if (tag == 'select')
                    this.selectedIndex = -1;
            });
        }

	</script>
</head>
<body>
<div class="container-fluid">
	<div class="row-fluid row">
		<div id="toolbar">
			<button class="bootstrap-table-add" type="button" onclick="add()">新增</button>
			<button class="bootstrap-table-edit" type="button" onclick="edit()">修改</button>
			<button class="bootstrap-table-delete" type="button" onclick="del()">删除</button>
			<button id="savebtn" class="bootstrap-table-add" type="button" onclick="savebtn()" style="display: none">
				保存
			</button>
			<button id="cancelbtn" class="bootstrap-table-delete" type="button" onclick="resetbtn()"
					style="display: none">取消
			</button>
		</div>
	</div>
	<div class="row-fluid row">
		<div class="col-sm-2 div-list">
			<div class="pro-list" style="">菜单列表</div>
			<div>
				<ul id="lefttree"></ul>
			</div>
		</div>
		<div class="col-sm-10">
			<div>
				<div class="pro-msg">详细信息</div>
				<div>
					<form class="form-horizontal" role="form" method="post" id="fm">
						<div class="form-group">
							<input class="from-control" id="menuid" name="menuid" dname="MENUID" type="hidden" />
							<div class="col-sm-12" style="margin-bottom: 10px;">
								<label for="menuname" class="col-sm-2 control-label">菜单名称：</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="menuname" name="menuname"
										   dname="MENUNAME" proving="notEmpty"/>
								</div>
								<label for="menuurl" class="col-sm-2 control-label">菜单地址：</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="menuurl"
										   name="menuurl" dname="MENUURL" />
								</div>
<!-- 								<label for="menutype" class="col-sm-3 control-label">菜单类型：</label> -->
<!-- 								<div class="col-sm-3"> -->
<!-- 									<select id="menutype" name="menutype" dname="MENUTYPE"proving="notEmpty" -->
<!-- 											class="selectpicker show-tick form-control"	 data-live-search="false" > -->
<!-- 										<option value="1">菜单</option> -->
<!-- 										<option value="2">按钮</option> -->
<!-- 									</select> -->
<!-- 								</div> -->
							</div>

<!-- 							<div class="col-sm-12" style="margin-bottom: 10px;"> -->
<!-- 								<label for="menutype" class="col-sm-3 control-label">菜单类型：</label> -->
<!-- 								<div class="col-sm-3"> -->
<!-- 									<select id="menutype" name="menutype" dname="MENUTYPE"proving="notEmpty" -->
<!-- 											class="selectpicker show-tick form-control"	 data-live-search="false" > -->
<!-- 										<option value="1">菜单</option> -->
<!-- 										<option value="2">按钮</option> -->
<!-- 									</select> -->
<!-- 								</div> -->
<!-- 								<label for="menuicon" class="col-sm-3 control-label">菜单图标地址：</label> -->
<!-- 								<div class="col-sm-3"> -->
<!-- 									<input type="text" class="form-control" id="menuicon" -->
<!-- 										   name="menuicon" dname=MENUICON /> -->
<!-- 								</div> -->
<!-- 							</div> -->

							<div class="col-sm-12" style="margin-bottom: 10px;">
								<label for="enable" class="col-sm-2 control-label">是否可用：</label>
								<div class="col-sm-4">
									<select id="enable" name="enable" dname="ENABLE" proving="notEmpty"
											class="selectpicker show-tick form-control"	 data-live-search="false" >
										<option value="0">不可用</option>
										<option value="1">可用</option>
									</select>
								</div>
								<label for="parentid" class="col-sm-2 control-label">父类菜单：</label>
								<div class="col-sm-4">
									<input id="parentid" name="parentid" class="form-control"/>
									<%--<select id="parentid" name="parentid" dname="PARENTID" proving="notEmpty"
                                            class="selectpicker show-tick form-control"	 data-live-search="false" >
                                    </select>--%>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>

<style>

	body {
		background-color: #eee;
		padding: 15px;
		font-family: "微软雅黑";
	}
	.row {
		margin: 0;
	}

	#toolbar {
		border: 1px solid #DEDEDE;
		background-color: #FFFFFF;
		padding: 8px;
		margin-bottom: 5px;
	}

	.div-list {
		background-color: #fff;
		padding: 0;
		height: 600px;
		border: 1px solid #DEDEDE;
	}

	.div-list .pro-list {
		width: 100%;
		line-height: 35px;
		font-weight: 700;
		color: #707070;
		border-bottom: 1px solid #EEEEEE;
		box-sizing: border-box;
		padding: 0;
		margin: 0;
		text-indent: 15px;
		border-left: 5px solid gray;
	}

	.col-sm-10 {
		padding: 0 0 0 15px;
	}

	.col-sm-10 .pro-msg {
		background-color: #fff;
		font-weight: 700;
		color: #707070;
		line-height: 35px;
		border: 1px solid #DEDEDE;
		border-bottom: 1px solid #EEEEEE;
		box-sizing: border-box;
		padding: 0;
		margin: 0;
		text-indent: 15px;
		border-left: 5px solid gray;
	}
	.col-sm-10 #fm {
		background-color: #fff;
		border: none;
		margin-bottom: 15px;
		padding-top: 15px;
		border: 1px solid #DEDEDE;
		border-top: none;
	}
</style>
</body>
</html>
