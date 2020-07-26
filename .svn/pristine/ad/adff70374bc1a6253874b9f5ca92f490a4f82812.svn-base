<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
<head>
    <base href="<%=basePath%>">
    <title>待办</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <script src="bootstrap2/js/jquery.js"></script>
    <script src="bootstrap2/js/bootstrap.datanew.js"></script>
    <style>
        .todoBody{padding-bottom:0;}
        .todoBody ul{margin:0;}
        .todo_item{position:relative; float:left; margin:10px; text-align:center; width:120px;}
        .todo_item .todo-img{width:50px; height:50px;}
        .todo_item .todo-name{display:block; height:40px;}
        .todo_item .todo-num{position:absolute; left:50%; top:-8px; margin-left:15px; background:#F00;}
    </style>
    <script>
        $(function(){
            refush();
            $(".todoPanel").on("click", ".todo_item", function(){
                var todoItem = $(this);
                parent.openNewTab(todoItem.data("url"), todoItem.data("name"), todoItem.data("id"))
            })


        })

        function refush(){
            $.dajax({
                url: "user/getUserDaiban.do", // Ajax 获取数据的 URL 地址
                data:{},
                success:function(data){
                    var html="";
                    for(var i=0;i<data.length;i++){
                        var numstr="";
                        if(data[i].count>0){
                            numstr='<span class="badge todo-num">'+data[i].count+'</span>';
                        }

                        html+=(' <li class="todo_item" data-id="10" data-name="'+data[i].name+'" data-url="'+data[i].url+'"><img src="images/todo.png" class="todo-img"><span class="todo-name">'+data[i].name+'</span>'+numstr+'</li>')
                    }
                    if(html==""){
                        $("#db_ul").html("您目前没有待处理的事项")
                    }else{
                        $("#db_ul").html(html);
                    }


                }
            });
        }
    </script>
</head>
<body>
<div class="panel panel-default todoPanel">
    <div class="panel-heading clearfix">待办事项 <button class="btn btn-default pull-right" onclick="refush()"><i class="glyphicon glyphicon-refresh"></i></button></div>
    <div class="panel-body">
        <ul class="list-unstyled clearfix" id="db_ul">
        </ul>
    </div>
</div>
</body>
</html>