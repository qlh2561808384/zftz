<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
   <base href="<%=basePath%>">
	<TITLE>首页</TITLE>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1">
	<script type="text/javascript" src="bootstrap2/js/jquery.js"></script>
	<script type="text/javascript" src="bootstrap2/js/bootstrap.datanew.js"></script>
	<script src="bootstrap2/plugins/tree/js/jquery.ztree.sidebar.js"></script>
	<link rel="stylesheet" href="bootstrap2/css/font-awesome.min.css"/>
	<link rel="stylesheet" href="bootstrap2/css/sideTree.css"/>
	<link rel="stylesheet" href="css/index.css"/>
	<script type="text/javascript" src="js/index.js"></script>
	  <script>
           $(function () {

               $("#myTab").on("click",'li a',function () {
                   var id = $(this).attr("data-href");
                   var li = $("#myTabContent .tab-pane")
                   // $("")
                   $.each(li,function (index,item) {
                       $(item).removeClass("active in")
                      if( id == '#'+$(item)[0].id){
                          $(item).addClass("active in")

					  }
                   })
                   // $("#myTabContent .tab-pane").addClass("active in")
                       // .slibings().toggleClass("active in")
               })
           })
	  </script>
</head>

<body>
<div class="mainPage">
	<!-- 导航 -->
	<div class="navbar">
		<div class="navbar-inner container-fluid">
			<!-- <button data-target=".sidebar" data-toggle="collapse" class="btn btn-navbar collapsed pull-left" type="button"><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button> -->
			<a href="#" class="brand"><img src="img/tubiao.png" alt=""/>政府投资项目管理系统</a>
			<div class="userInfo2">
				<div class="user_name">
					<a data-toggle="dropdown" class="dropdown-toggle" href="#">
						<img src="img/gly2.png" width="25" />
						<span class="uname" id="uname"><b class="caret"></b></span>
					</a>
					<ul class="dropdown-menu">
						<li><a href="javascript:void(0)" onclick="updatePassword()">修改密码</a></li>
						<li><a href="user/loginout.do">退出</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	
	<div class="main-content">
		<!-- Sidebar -->
		<div class="sidebar" id="sidebar">
			<div class="tree_side ztreeBox">
				<ul class="ztree" id="treeSide"></ul>
			</div>
			<div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
				<i class="ace-icon fa fa-angle-double-left" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
			</div>
		</div>

		<!-- main -->
		<div class="main-content-inner">
			<!-- tab标签 -->
			<div class="tabBox">
				<div class="tab_wrap clearfix">
					<ul id="myTab" class="nav nav-tabs">
						<li class="active" id="tabli-11"><a href="#tab-11" id="tabclose-11" data-toggle="tab">首页 <i class="fa fa-times-circle"></i></a></li>
					</ul>
				</div>
				<div class="tab_btn">
					<a href="javascript:void(0);" class="closeAll pull-right hide">关闭所有</a>
					<a href="javascript:void(0);" class="showtab showRightTabs pull-right hide"><i class="fa fa-angle-double-right"></i></a>
					<a href="javascript:void(0);" class="showtab showLeftTabs pull-right hide"><i class="fa fa-angle-double-left"></i></a>
				</div>
				<div id="myTabContent" class="tab-content">
					<div class="tab-pane fade in active" id="tab-11">
						<iframe src="indexcontent.jsp" frameborder="0" class="iframeCon" id="iframe-11" width="100%"></iframe>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

</body>
</HTML>
<script>
	var usermenus; //用户对应的菜单权限
	var loginuser; //用户信息
	var userbuttons;//用户对应的按钮权限

	$(function () {
		$.dajax({
			url: "user/getUserInfo.do", // Ajax 获取数据的 URL 地址
			success: function (data) {
				if (data.success) {
					usermenus = data.content.usermenus;
					loginuser = data.content.loginuser;
                    loginuserinfo= data.content.loginuser;
					userbuttons = data.content.userbuttons;
					$("#uname").prepend(loginuserinfo.xm);
					
					
					//侧边栏
					var treeSide = $("#treeSide").atree({
						localdata: usermenus,
						view: {
							dblClickExpand: false,
							showLine: false,
							showBadge: true //显示消息数字
						},
						//oralce使用 
						data: { // 必须使用data
							simpleData: {
								enable: true,
								idKey: "MENUID", // id编号命名 默认
								pIdKey: "PARENTID", // 父id编号命名 默认
								rootPId: 0
								// 用于修正根节点父节点数据，即 pIdKey 指定的属性值
							},
							key: {
								name: "MENUNAME",
								url: "MENUURL",
								icon: "MENUICON"
							}
						},
//                      mysql使用
// 						data: { // 必须使用data
// 							simpleData: {
// 								enable: true,
// 								idKey: "menuid", // id编号命名 默认
// 								pIdKey: "parentid", // 父id编号命名 默认
// 								rootPId: 0
// 								// 用于修正根节点父节点数据，即 pIdKey 指定的属性值
// 							},
// 							key: {
// 								name: "menuname",
// 								url: "menuurl",
// 								icon: "menuicon"
// 							}
// 						},
						callback: {
							beforeExpand: beforeExpand,
							onExpand: onExpand,
							onClick: onClick
						}
					});
				}
			}
		});

        //显示某个标签页时刷新iframe
        /* $("#myTab").on("click", "li", function(){
            var liId = $(this).attr("id");
            var iframeId = liId.replace(/tabli/, "iframe");
            $("#"+iframeId)[0].contentWindow.location.reload(true);
        }); */
    });
	function updatePassword(){
		$.dopen({
               btns: 2, 
               btn: ['保存', '取消'],
               area: ['310px','250px'],
               title :"修改密码",
               content: '<form id="updateform" />',
               yes:function(index){
            	   
            	   if(!$("#updateform").dform("validate")){
            		   
            	   }else if($("#updateform input[name=newpassword]").val()!=$("#updateform input[name=rnewpassword]").val()){
            		   $.dalert({text:"您两次输入的新密码不一致,请确认"})
            	   }else{
            		   $("#updateform").dform("submit",{
	       					url:"user/updatePassword.do",
	       					success:function(data){
	       						if(data.success){
	       							$.dalert({text:data.content});
	       							layer.close(index);
	       						}else{
	       							$.dalert({text:data.content});
	       						}
	       						
	       					}
       				   })
            		   
            	   }
               },
               no:function(index){
                  alert("点击了取消按钮");
                  layer.close(index);		
			   }
				    	    
		});
		$("#updateform").dform({
			 labelwidth:"100px",
			 rownum:1,   //每行控件数目
	 		 inputs:[
	 		         {
	 		             proving:"notEmpty",
	 		        	 title:"原密码",
	 		        	 name:"oldpassword",
	 		        	 type:"password"
	 		         },{
	 		             proving:"notEmpty",
	 		        	 title:"新密码",
	 		        	 name:"newpassword",
	 		        	 type:"password"
	 		         },{
	 		             proving:"notEmpty",
	 		        	 title:"重复新密码",
	 		        	 name:"rnewpassword",
	 		        	 type:"password"
	 		         }
	 		 ]
		})
	}
	
</script>
