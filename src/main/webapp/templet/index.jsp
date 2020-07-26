<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
<head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <title>桌面首页</title>
    <meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1">
    <script src="bootstrap2/js/jquery.js"></script>
    <script src="bootstrap2/js/bootstrap.datanew.js"></script>
    <script src="bootstrap2/js/jquery-ui.js"></script>
    <script src="templet/index.js"></script>
    <style>
        *{-moz-user-select:none; -webkit-user-select:none; -ms-user-select:none; -khtml-user-select:none; user-select:none;}
        html, body{height: 100%;}
        body{margin:0 auto; background:url('./img/pagebg.jpg') no-repeat; background-size: 100% 100%; background-repeat: repeat \9;}

        .deskWrap{position: relative; width: 100%; height: 100%;}
        .deskWrap .deskBottom{position: absolute; left: 0; bottom: 0; z-index: 99999999; z-index: auto \9; width:100%; height:45px; padding: 0 1%; opacity: 0.8; filter:alpha(opacity=80); background:url('./img/bottombg.png') repeat-x; background-size: 100% 100%; background:#6D695D \9;}
        .deskWrap .deskMain{position: absolute; top:0; bottom:45px; left:0; right:0; margin:10px;}

        .deskBottom .showMenu{border-radius:5px; padding:8px 12px; margin-top:4px; cursor: default;}
        .deskBottom .showMenu:hover{background:#0F1212; background:rgba(15,18,18,.8);}
        .deskBottom .showMenu img{display:block; width:22px; height:22px;}
        .deskBottom .deskBtmTab{padding:5px 0 0 10px; margin-bottom:0;}
        .deskBottom .deskBtmTab li{float:left; margin-right:5px; width:150px; width:120px \9; height:35px; line-height: 35px; color:#FFF; overflow:hidden; font-size:13px; padding:0 10px; border-radius:5px; background:#909293; background:rgba(111, 112, 112, 0.5);}
        .deskBottom .deskBtmTab li.tabActive, .deskBottom .deskBtmTab li:hover{background:#929292;}
        .deskBottom .deskBtmTab li img{width:22px; height:22px; margin-right:5px; vertical-align: middle;}

        .deskBottom .btn-group.dropup .caret{border-width:5px;}
        .deskBottom .btn-group{position:static !important; padding:5px;}
        .deskBottom .btn-group .btn{width:28px; height:28px; padding:0;background:url('./img/dropdown.png') no-repeat; border:none;}
        .deskBottom .btn-group .btn:hover{opacity:.8; filter:alpha(opacity=80);}
        .deskBottom .btn-group .dropdown-menu{z-index: 999999999 \9; padding:0; margin:0; background:url('./img/opacitybg_40.png') repeat; border-bottom:1px solid #262425; border-radius:0;}
        .deskBottom .btn-group .dropdown-menu li{height:33px; border-top:1px solid #423F3C;}
        .deskBottom .btn-group .dropdown-menu li:hover{background:#3C3429;}
        .deskBottom .btn-group .dropdown-menu li:first-child{border-top:none;}
        .deskBottom .btn-group .dropdown-menu a{height:100%; line-height:33px; padding:0 0 0 20px; color:#FFF;}
        .deskBottom .btn-group .dropdown-menu li a:hover{color:#DDD; background:none;}

        .deskBottom .baseInfo{line-height:42px; color:#FFF;}
        .deskBottom .baseInfo > span{margin-right:10px;}
        .deskBottom .baseInfo b{font-weight:500;}

        .chooseYear{padding:0 15px; line-height:42px; color:#FFF;}
        .chooseYear a{color:#FFF;}
        .chooseYear label{margin-bottom:0;}

        .deskMain .deskItem{position:absolute; width:80px; padding: 5px 0; min-width:50px; cursor: default; text-align: center; border:1px solid transparent; border-radius:5px; font-size:14px; cursor: default;}
        .deskMain .deskItem:hover{border-color:#B1CEF1; background:rgba(223,229,235, 0.5);}
        .deskMain .deskItemAcyive{border-color:#7A9FCA; background:rgba(188,214,244, 0.5);}
        .deskMain .deskItem .item-txt{width:100%; font-weight:600; color:#FFF; margin-bottom:0; margin-top:5px; text-shadow:0 0 5px #000;}
        .deskMain .deskItem .item-img{width:35px; height:35px;}
        .deskMain .deskItem .item-trash{color:#e5e5e5; font-size:28px;}
        .deskMain .deskItem .item-badge{position:absolute; right:10px; top:-5px; background:#FFF; color:#333;}

        /* 屏幕右侧 公告&待办 */
        .deskMain .desk_module{position:absolute; right:0; width:400px;}
        .deskMain .desk_notice{top:0; height:260px;}
        .deskMain .desk_todolist{top:50%;}
        .deskMain .desk_module a{color:#61aff2;}
        .deskMain .desk_module .panel{margin:0; background:url('./img/opacitybg_40.png') repeat; color:#fff;}
        .deskMain .desk_module .panel-heading{font-size:15px; font-weight:600; border-bottom:1px solid #ACACAC; background:url(./img/opacitybg_40.png) repeat;}
        .deskMain .desk_module .panel-body{padding:0;}

        .deskMain .desk_notice .carousel{padding:20px 0 40px;}
        .deskMain .desk_notice .carousel-inner{width:80%; height:150px; margin:0 auto; border:5px solid #EEE; border-radius:4px;}
        .deskMain .desk_notice .carousel-inner .item{padding:10px;}
        .deskMain .desk_notice .carousel-inner .notice-title{height:25px;}
        .deskMain .desk_notice .carousel-inner .notice-txt{position:relative; line-height:22px; height:66px; overflow:hidden;}
        .deskMain .desk_notice .carousel-inner .notice-txt::after {
            content: "\02026"; position:absolute; bottom:0; right:0; padding-left:30px;
            background:-webkit-linear-gradient(left,transparent,#283443 55%);
            background:-o-linear-gradient(right,transparent,#283443 55%);
            background:-moz-linear-gradient(right,transparent,#283443 55%);
            background:linear-gradient(to right,transparent,#283443 55%);
        }
        .deskMain .desk_notice .carousel-inner .notice-date{margin-top:5px; color:#A3A3A3;}
        .deskMain .desk_notice .carousel-control{width:10%;}
        .deskMain .desk_notice .carousel-indicators{bottom:0;}

        .deskMain .desk_todolist .panel-body .list-group-item{background-color:transparent; border-color:#7E7E7E;}
        .deskMain .desk_todolist .panel-body .list-group-item .list-title{width:55%; color:#FFF;}
        .deskMain .desk_todolist .panel-body .list-group-item .list-time{color:#b8b8b8; width:80px; text-align:right;}
        .deskMain .desk_todolist .panel-body .list-group-item .list-user{color:#DDD;}

        /* 弹窗标题 */
        .windowTit img{width:25px; height:25px; margin-right:5px; vertical-align: middle;}

        /* 左下角主菜单 */
        .mainMenu{display:none; position:absolute; left:0; bottom:45px; z-index:999999999; width:250px; height:80%; overflow-y:auto; overflow-x:hidden; background:#F9F9F9; border:1px solid #EEE;}
        .mainMenu .menuTree{width:106%; height:93%; overflow-y:scroll; overflow-x:hidden;}
        #menuTree.ztree{padding:0;}
        #menuTree.ztree li ul{ margin:0; padding:0}
        #menuTree.ztree li ul.level0{border-bottom:1px solid #DDD;}
        #menuTree.ztree li {position:relative; line-height:30px;}
        #menuTree.ztree li > a {width:100%; padding:5px 0 5px 10px;}
        #menuTree.ztree li.level1 > a{padding-left:25px;}
        #menuTree.ztree li > a:hover {text-decoration:none; background-color: #E7E7E7;}
        #menuTree.ztree li span{font-size:14px;}
        #menuTree.ztree li a span.button.switch {visibility:hidden}
        #menuTree.ztree.showIcon li a span.button.switch {visibility:visible}
        #menuTree.ztree li a.curSelectedNode {background-color:#D4D4D4;border:0;height:inherit;}

        #menuTree.ztree li span.button.switch{width:0; height:0;}

        #menuTree.ztree li a.level0 span.button {width:30px; height:25px; vertical-align:middle; background:url("./img/win_icon.png") no-repeat center center; background-size:auto 100%;}

        #menuTree.ztree li span.button {background:none;}
        #menuTree.ztree li span.button.noline_close,
        #menuTree.ztree li span.button.noline_open{width: 16px;height: 16px; position:absolute; right:10px; top:8px; font-family: 'Glyphicons Halflings'; line-height: 1;}
        #menuTree.ztree li span.button.noline_close:before,
        #menuTree.ztree li span.button.noline_open:before{content: "\e259";}

        #menuTree.ztree li .tooltip{width:90%; right:5px; top:30px;}
        .deskMain .tooltip{width:200%; left:80px; top:0;}
        .tooltip{opacity:1; filter:alpha(opacity=100);}
        .tooltip.bottom .tooltip-arrow{left:20%;}
        .tooltip .tooltip-inner{max-width:100%; padding:5px; text-align:left; text-indent:20px;}
        .tooltip .tooltip-inner p{padding:6px 0; margin:0;}
        .tooltip .tooltip-inner p:hover{background:#536474;}

        .closeAllOpen{width:100px; margin-left:-50px; bottom:45px; z-index:99999999;}
    </style>
</head>
<body oncontextmenu="self.event.returnValue=false" onselectstart="return false">
<div class="deskWrap">
    <div class="deskMain" id="deskMain">
        <div class="deskItem desk_trash">
            <div class="item-trash"><i class="glyphicon glyphicon-trash"></i></div>
            <p class="textover-ell item-txt">回收站</p>
        </div>
        <%--<div class="deskItem desk_icon" data-drag="draggable" data-type="deskIcon" data-id="10" data-url="base/enterpriseManager.jsp">
            <img src="./img/win_icon.png" class="item-img" alt="">
            <span class="badge item-badge">3</span>
            <p class="textover-ell item-txt">单位管理</p>
        </div>--%>
        <div class="desk_module desk_notice" data-drag="draggable" data-type="deskModule">
            <div class="panel">
                <div class="panel-heading">通知公告 <span class="badge">2</span> <a href="" class="pull-right" target="_blank">全部通知</a></div>
                <div class="panel-body">
                    <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                        <!-- Indicators -->
                        <ol class="carousel-indicators">
                            <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                            <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                        </ol>

                        <!-- Wrapper for slides -->
                        <div class="carousel-inner" role="listbox">
                            <div class="item active">
                                <div class="notice-title clearfix"><a href="" target="_blank" class="pull-left"><b>关于五一放假的通知</b></a><b class="pull-right">From 办公室</b></div>
                                <div class="notice-txt">五一劳动节即将来临，根据公司的实际情况，现对2018年五一劳动节放假做如下安排：五一劳动节即将来临，根据公司的实际情况，现对2018年五一劳动节放假做如下安排</div>
                                <div class="notice-date">4月20日</div>
                            </div>
                            <div class="item">
                                <div class="notice-title clearfix"><a href="" target="_blank" class="pull-left"><b>关于五一放假的通知</b></a><b class="pull-right">From 办公室</b></div>
                                <div class="notice-txt">五一劳动节即将来临，根据公司的实际情况，现对2018年五一劳动节放假做如下安排：五一劳动节即将来临，根据公司的实际情况，现对2018年五一劳动节放假做如下安排</div>
                                <div class="notice-date">4月20日</div>
                            </div>
                        </div>

                        <!-- Controls -->
                        <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                            <span class="sr-only">Previous</span>
                        </a>
                        <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                            <span class="sr-only">Next</span>
                        </a>
                    </div>
                </div>
            </div>
        </div>
        <div class="desk_module desk_todolist" data-drag="draggable" data-type="deskModule">
            <div class="panel">
                <div class="panel-heading">待办事项 <a href="" class="pull-right" target="_blank">全部待办</a></div>
                <ul class="panel-body list-group">
                    <li class="list-group-item clearfix">
                        <a href="" class="pull-left textover-ell list-title" target="_blank">关于组织架构的调整</a>
                        <span class="pull-right list-time">17分钟前</span>
                        <span class="pull-right list-user">人力资源</span>
                    </li>
                    <li class="list-group-item clearfix">
                        <a href="" class="pull-left textover-ell list-title" target="_blank">近期项目进度总结汇报总结汇报总结汇报</a>
                        <span class="pull-right list-time">3月30日</span>
                        <span class="pull-right list-user">开发组</span>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <div class="deskBottom clearfix">
        <div class="pull-left showMenu" id="showMenu"><img src="./img/favicon.png" /></div>
        <div class="pull-left">
            <ul class="deskBtmTab clearfix" id="deskBtmTab"></ul>
        </div>
        <div class="pull-right systemInfo clearfix">
            <div class="pull-right btn-group dropup">
                <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu dropdown-menu-right">
                    <li><a href="javascript:void(0);" onclick="updatePassword()"><i class="glyphicon glyphicon-lock"></i>&nbsp;&nbsp;修改密码</a></li>
                    <li><a href="user/loginout.do"><i class="glyphicon glyphicon-log-out"></i>&nbsp;&nbsp;退出</a></li>
                </ul>
            </div>
            <div class="pull-right baseInfo">
                <span><i class="glyphicon glyphicon-time"></i>&nbsp;今天是<b id="today"></b></span>
                <span><i class="glyphicon glyphicon-user"></i>&nbsp;您好！admin</span>
            </div>
        </div>
    </div>
    <div class="mainMenu" id="mainMenu">
        <ul class="menuTree" id="menuTree"></ul>
    </div>
</div>
</body>
</html>
<script>
    var dragOptions = {
        containment: "#deskMain",
        scroll: false,
        start: function(event, ui){
        },
        drag: function(event, ui){

        },
        stop: function(event, ui){
            //移入回收站
            var target = $(event.target),
                position = ui.position;
            if(position.top >=0 && position.top<=20 && position.left >=0 && position.left<=20){
                target.remove();
            }

            saveDesktop();//保存桌面信息
        }
    };
    $(document).ready(function(){
        //获取当前日期
        $("#today").html(getToday());

        //桌面图标排列
        showDeskIcon();
        //底部栏 点击tab
        $("#deskBtmTab").on("click", "li", function(){
            var layerId = $(this).attr("data-layerId");
            var layero = $("#"+layerId);
            var max = arrSort()+1;
            if(layero.is(":hidden")){ //该窗口隐藏状态时，显示
                layero.show()
                    .css({zIndex: max+1})
                    .attr("data-zindex", max+1);
                $(this).addClass("tabActive").siblings().removeClass("tabActive");
            }else if(!$(this).hasClass("tabActive")){ //该窗口不隐藏且不是置顶状态时，置顶
                if(max !== parseInt($(this).attr("data-zindex"))){
                    layero.css({zIndex: max+1})
                        .attr("data-zindex", max+1);
                    $(this).addClass("tabActive").siblings().removeClass("tabActive");
                }
            }else if($(this).hasClass("tabActive")){ //该窗口置顶时，隐藏
                layero.hide();
                $(this).removeClass("tabActive");
            }
        });

        //右键deskBottom
        $(".deskBottom").on("mousedown", function(e){
            if(e.which === 3 && $(this).find(".btmTab_item").length > 0){
                var menuHtml = '<div class="tooltip top closeAllOpen" role="tooltip" style="left:'+ e.pageX +'px;">' +
                    '<div class="tooltip-arrow"></div>' +
                    '<div class="tooltip-inner">' +
                    '<p class="tooltip_menu menu_closeAll">关闭所有</p>' +
                    '<p class="tooltip_menu menu_hideAll">显示桌面</p>' +
                    '</div></div>';
                setTimeout(function(){
                    $("body").append(menuHtml);
                    //点击桌面其他区域，tooltip关闭
                    var tooltip = $(".closeAllOpen");
                    removeTooltip(tooltip);
                }, 200);

            }
        });
        $("body").on("click", ".menu_closeAll", function(){
            layer.closeAll();
            $(".closeAllOpen").remove();

        }).on("click", ".menu_hideAll", function(){
            $(".layui-layer").each(function(){
                $(this).hide();
                $("#deskBtmTab li").removeClass("tabActive");
            });
            $(".closeAllOpen").remove();
        });

        //点击桌面图标
        $("#deskMain").on("dblclick", ".desk_icon", function(){
            openWindow(this);

        }).on("mousedown", ".desk_icon", function(e){
            var othis = $(this);
            if(e.which === 1){//点击左键
                othis.addClass("deskItemAcyive").find("p").removeClass("textover-ell")
                    .end()
                    .siblings().removeClass("deskItemAcyive").find("p").addClass("textover-ell");
            }else if(e.which === 3){//点击右键
                var target = $("#deskMain");
                othis.addClass("deskItemAcyive").siblings().removeClass("deskItemAcyive");
                if(othis.find("[role='tooltip']").length === 0){
                    var menuHtml = '<div class="tooltip right showShortCut" role="tooltip">' +
                        '<div class="tooltip-arrow"></div>' +
                        '<div class="tooltip-inner">' +
                        '<p class="tooltip_menu removeShortcut">删除快捷方式</p>' +
                        '</div></div>';
                    setTimeout(function(){
                        othis.append(menuHtml);
                        //点击桌面其他区域，tooltip关闭
                        var tooltip = $(".showShortCut");
                        removeTooltip(tooltip);
                    }, 200);
                }
            }
            $(".removeShortcut").on("click", function(){
                othis.remove();
            });
        });
        $("#deskMain").click(function(e){
            var targetClass = $(e.target).attr("class");
            if(targetClass.indexOf("deskItem") === -1 && !$(e.target).parent().is(".deskItem")){
                $(".deskItem").removeClass("deskItemAcyive");
            }

            //点击桌面其他区域，tooltip关闭
            var tooltip = $(this).find("[role='tooltip']");
            removeTooltip(tooltip);
        });
        $.dajax({
            url: "user/getUserInfo.do", // Ajax 获取数据的 URL 地址
            success: function (data) {
                if (data.success) {
                    var usermenus = data.content.usermenus;
                    //点击左下角图标，显示主菜单
                    $("#menuTree").tree({
                        localdata: usermenus,
                        checkType: "nocheck",
                        view: {
                            showLine: false,
                            dblClickExpand: false
                        },
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
                                durl: "MENUURL",
                                icon: "MENUICON"
                            }
                        },
                        callback:{
                            clickNode : function(e, treeId, treeNode, clickFlag){
                                var ztree = $("#"+treeId).tree("getTree");
                                ztree.expandNode(treeNode);

                                //打开窗口
                                if(!!treeNode[ztree.setting.data.key.durl]){
                                    openWindow(treeNode);
                                    hideMainMenu();
                                }
                            },
                            onRightClick: function(e, treeId, treeNode){
                                if(treeNode != null){
                                    $("#" + treeId).find("a").removeClass("treeNode");
                                    $("#" + treeNode.tId + "_a").addClass("treeNode");
                                    showDropdownMenu(treeNode);
                                }
                            }
                        }
                    });
                }
            }
        });
        $("#showMenu").on("click", function(){
            var mainMenu = $("#mainMenu");
            if(mainMenu.is(":hidden")){
                mainMenu.slideDown(200);

                //点击桌面其他区域，mainmenu关闭
                $(document).mouseup(function(e){
                    if(!mainMenu.is(e.target) && mainMenu.has(e.target).length === 0){
                        hideMainMenu();
                    }
                });
            }else{
                hideMainMenu();
            }
        });

        $("[data-drag='draggable']").draggable(dragOptions);


        initDesktop();//恢复用户桌面设置
    });

    //关闭右下角菜单
    function hideMainMenu(){
        $("#mainMenu").fadeOut(10);
    }

    //获取当前日期
    function getToday(){
        var today = new Date();
        var year = today.getFullYear();
        var month = today.getMonth() + 1;
        var day = today.getDate();
        return year + "年" + month + "月" + day + "日";
    }
    //桌面图标排列
    function showDeskIcon(){
        var deskItem = $("#deskMain .deskItem");
        var hasLiNum = deskItem.length;
        var leftLen = 100, topLen = 100;
        var colNum = Math.round($("#deskMain").height()/topLen),
            rowNum = 0,
            totalRowNum = Math.ceil(hasLiNum/colNum);
        $.each(deskItem, function(i){
            $(this).css({left:leftLen*rowNum, top:topLen*(i%colNum)});
            if((i+1)%colNum === 0 && i!==0) rowNum++;
        });
    }

    //打开窗口
    var defaultWinIcon = "./img/win_icon.png";
    function openWindow(obj){
        var ztree = $("#menuTree").tree("getTree");
        var dataId, dataUrl, winImg, winTitle;
        if(obj.nodeType === 1){//元素节点，双击桌面图标时
            dataId = $(obj).attr("data-id");
            dataUrl = $(obj).attr("data-url");
            winImg = $(obj).find(".item-img").attr("src");
            winTitle = $(obj).find(".item-txt").text();
        }else{
            var ico = obj[ztree.setting.data.key.icon];
            dataId = obj[ztree.setting.data.simpleData.idKey];
            dataUrl = obj[ztree.setting.data.key.durl];
            winImg = (ico===undefined || ico==null || ico==="") ? defaultWinIcon : ico;
            winTitle = obj[ztree.setting.data.key.name];
        }
        var imgTxtHtml = "<img src='"+ winImg +"' />" + winTitle,
            deskBtmTab = $("#deskBtmTab"),
            layerId;
        //若该窗口不存在
        var maxH = $(window).height() - $(".deskBottom").height();
        if($(".layui-layer[data-id='"+dataId+"']").length === 0){
            $.dopen({
                type: 2,
                title: "<div class='windowTit'>" + imgTxtHtml+ "</div>",
                area: ['100%', maxH+'px'],
                content: dataUrl,
                shade: 0,
                offset: "t",
                success: function(layero, index){
                    layerId = layero.attr("id");

                    //插入layer弹窗并置顶显示
                    var tabli = "<li class='btmTab_item tabActive' data-layerId='"+ layerId +"'>"+ imgTxtHtml +"</li>";
                    deskBtmTab.find("li").removeClass("tabActive");
                    deskBtmTab.append(tabli);
                    var max = arrSort()+1;
                    layero.css({zIndex: max}).attr({"data-zindex":layero.css("z-index"), "data-id":dataId});

                    var ulMaxWid;//底部tab最大宽度
                    //ie6-8, 则加载js, 兼容
                    if(navigator.userAgent.indexOf("MSIE")>0 && (navigator.userAgent.indexOf("MSIE 6.0")>0 || navigator.userAgent.indexOf("MSIE 7.0")>0 || navigator.userAgent.indexOf("MSIE 8.0")>0)){
                        ulMaxWid = $(".deskBottom").width()-$(".showMenu").outerWidth()-$(".systemInfo").outerWidth()-50;
                    }else{
                        ulMaxWid = $(".deskBottom").width()-$(".showMenu").outerWidth()-$(".systemInfo").outerWidth();
                    }
                    var ulCurWid = 0;
                    deskBtmTab = $("#deskBtmTab");
                    $.each(deskBtmTab.find("li"), function(){
                        ulCurWid += $(this).outerWidth();
                    });
                    //如果tab标签打开过多，减小标签宽度
                    if(ulCurWid >= ulMaxWid){
                        deskBtmTab.css("max-width", ulMaxWid);
                        var len = deskBtmTab.find("li").length;
                        deskBtmTab.find("li").width(Math.floor(ulMaxWid/len)-30);
                    }
                },
                min: function(layero){
                    layero.hide();
                    deskBtmTab.find("[data-layerId="+ layerId +"]").removeClass("tabActive");
                    return false;
                },
                full: function(layero){
                    //修改最大化时弹窗的高度，只占满deskMain的高度
                    var layerMaxH = $("#deskMain").outerHeight(true);
                    var iframeMaxH = layerMaxH - $(".layui-layer-title").outerHeight();
                    layero.css({height:layerMaxH, top: $(".deskTop").outerHeight()});
                    layero.find("iframe").css("height", iframeMaxH);
                },
                end: function(){
                    //底部tab标签删除
                    deskBtmTab.find("[data-layerId="+ layerId +"]").remove();
                }
            });
        }else{ //已存在该窗口，则置顶
            var layero = $(".layui-layer[data-id='"+dataId+"']");
            var max = arrSort()+1;
            layero.show().css({zIndex: max}).attr({"data-zindex":layero.css("z-index")});
        }
    }

    //zindex值组成数组，排序后返回最大值
    function arrSort(){
        var zindexArr = [];
        $(".layui-layer").each(function(){
            zindexArr.push(parseInt($(this).attr("data-zindex")));
        });
        zindexArr.sort(function(a,b){return b-a});//数组倒序
        return zindexArr[0];
    }

    //右键菜单，显示下拉菜单栏
    function showDropdownMenu(treeNode){
        var obj = $("#" + treeNode.tId);
        var ztree = $("#menuTree").tree("getTree");
        if(obj.find("[role='tooltip']").length === 0 && !!treeNode[ztree.setting.data.key.durl]){
            var menuHtml = "<div class='tooltip bottom createShortcut' role='tooltip'>" +
                "<div class='tooltip-arrow'></div>" +
                "<div class='tooltip-inner'>" +
                "<p class='tooltip_menu menu_createShortcut'>创建快捷方式</p>" +
                "</div></div>";
            obj.append(menuHtml);

            $(".tooltip_menu").on("click", function(){
                obj.find("[role='tooltip']").remove();
                hideMainMenu();
            });
            $(".menu_createShortcut").on("click", function(){
                createShortcut(treeNode);
            });

            //点击桌面其他区域，tooltip关闭
            var tooltip = $(".createShortcut");
            removeTooltip(tooltip);
        }
    }

    function removeTooltip(tooltip){
        $(document).mouseup(function(e){
            if(!tooltip.is(e.target) && tooltip.has(e.target).length === 0){
                tooltip.remove();
            }
        });
    }

    //新建桌面快捷方式
    function createShortcut(treeNode){
        var deskMain = $("#deskMain");
        var ztree = $("#menuTree").tree("getTree");
        var ico = treeNode[ztree.setting.data.key.icon];
        var winIcon = (ico===undefined || ico==null || ico==="") ? defaultWinIcon : ico;
        if($(".desk_icon[data-id='"+ treeNode[ztree.setting.data.simpleData.idKey] +"']").length === 0){
            var deskItemHtml = '<div class="deskItem desk_icon" data-drag="draggable" data-type="deskIcon" data-id="'+ treeNode[ztree.setting.data.simpleData.idKey] +'" data-url="'+ treeNode[ztree.setting.data.key.durl] +'">' +
                '<img src="'+ winIcon +'" class="item-img" alt=""/>' +
                '<span class="badge item-badge"></span>' +
                '<p class="textover-ell item-txt">'+ treeNode[ztree.setting.data.key.name] +'</p>' +
                '</div>';
            deskMain.append(deskItemHtml);
            //添加可拖拽事件
            $(".desk_icon").last().draggable(dragOptions);
        }
        showDeskIcon();
        hideMainMenu();

        saveDesktop();//保存桌面信息
    }

    //修改密码
    function updatePassword(){
        if($("#updateform").length === 0){
            $.dopen({
                shade: false,
                maxmin: false,
                btn: ['保存', '取消'],
                area: ['310px','250px'],
                title :"修改密码",
                content: '<form id="updateform"></form>',
                btn1:function(index){
                    if(!$("#updateform").dform("validate")){

                    }else if($("#updateform input[name=newpassword]").val()!=$("#updateform input[name=rnewpassword]").val()){
                        $.dalert({text:"您两次输入的新密码不一致,请确认", icon:2})
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
            });
        }
    }

</script>