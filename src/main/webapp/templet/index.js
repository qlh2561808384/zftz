function appendNotice() {
    $.ajax({
        type:'post',
        url:'baseNotice/query.do',
        dataType:'json',
        success:function (data) {
            $('.carousel-inner').empty();
            $('.carousel-indicators').empty();
            if(data.length>0){
                var filterData = [];
                for(var k=0;k<data.length;k++){
                    if(data[k].enable=='0' && k<5){
                        filterData.push(data[k]);
                    }
                }
                var appendHtml = filterData.map(function (item,index) {
                    var divActive = index==0?"<div class=\"item active\">":"<div class=\"item\">";
                    return divActive+"<div class=\"notice-title clearfix\"><a href=\"\" target=\"_blank\" class=\"pull-left\"><b>"+item.title
                        +"</b></a><b class=\"pull-right\">From 办公室</b></div><div class=\"notice-txt\">"+item.content+"</div><div class=\"notice-date\">4月20日</div></div>";
                })
                for(var i=0;i<appendHtml.length;i++){
                    $('.carousel-inner').append(appendHtml[i]);
                    var liClass = i==0?"class='active'":"class=''";
                    $('.carousel-indicators').append("<li "+liClass+" style='margin-left: 5px' data-target=\"#carousel-example-generic\" data-slide-to=\""+i+"\"></li>");
                }
                $('.panel-heading .badge').text($('.carousel-indicators').children().length);
            }else{
                $('.carousel-inner').append("<div style='text-align: center;margin-top: 65px'>暂无公告</div>");
            }
        }
    })
}



function saveDesktop(){
     var html = $("#deskMain").html();
    $.ajax({
		url:"user/saveDesktop.do",
        type: "POST",
        data: {html:html}
	});
}


function initDesktop(){
    $.ajax({
        url:"user/getDesktop.do",
        type: "POST",
        dataType: "json",
        success:function( data){
           if(data.success){
               $("#deskMain").html(data.content);
               $("[data-drag='draggable']").draggable(dragOptions);
               appendNotice();
           }
        }
    });
}