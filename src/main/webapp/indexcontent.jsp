<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
<head>
    <base href="<%=basePath%>">
    <title>首页</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <script src="bootstrap2/js/jquery.js"></script>
    <script src="bootstrap2/js/bootstrap.datanew.js"></script>
    <script src="js/echarts.min.js"></script>
    <style>
        .wd1200{width:1200px; margin:0 auto;}
        .banner{height:250px; background:url('images/banner.jpg') no-repeat top center; background-size:100% 100%;}
        .showResult{padding:10px 0; margin-bottom:20px; background:url('images/textbg.jpg') no-repeat top center; background-size:100% 100%;}
        .showResult .result_head{font-size:20px; line-height:2.3;}
        .showResult .result_item{font-size:16px; text-align:center;}
        .showResult .result_item strong{display:block; color:#E34840; font-size:20px;}
        .showResult .result_item small{font-size:14px;}
        .chartBox{float:left; width:31%; margin:0 1%; height:300px;}
        .subChartBox{width:100%;}
        .dtl_info .subChartBox{height:200px;}
        .dtl_chart .col-xs-6{padding:0;}
        .dtl_chart .subChartBox{height:300px;}
        .programDetail .dtl_head{font-size:24px; text-align:center; padding:10px 0; margin-bottom:20px; border-bottom:6px solid #0FCC8C;}
        .programDetail .dtl_list{padding-left:30px;}
        .programDetail .dtl_list li{font-size:16px; margin-bottom:10px;}
        .programDetail .dtl_list li:first-child{font-size:18px; line-height:1.5; height:40px; color:#000;}
        #chart7, #chart8, #chart8_2{height:250px;}
        #chart9{height:300px;}
        #chart6_2{margin-top:-300px;}
        #chart8_2{margin-top:-250px;}

        .program3Detail .col-xs-4 .subChartBox{height:180px;}
        .program3Detail .col-xs-6 .subChartBox{height:280px;}

        /* 时序图样式 */
        .chartBlock{position:relative; padding-top:10px; margin-right:20px;}
        .chartBlock .yAxis{position:relative; z-index:100; width:15%; overflow:hidden; border-right:1px solid  #EBEBEB;}
        .chartBlock .yAxis .yAxis_inner{width:125%;}
        .chartBlock .yAxis .axisLine .axis_label{height:50px; padding-right:10px;}

        .chartBlock .xAxis{position:relative; width:85%; height:100%; overflow-x:auto;}
        .chartBlock .xAxis .axisLine{position:relative; z-index:1; border-top:1px solid #EBEBEB;}
        .chartBlock .xAxis .axisLine .axis_item{position:relative; float:left; width:20%; height:20px;}
        .chartBlock .xAxis .axisLine .axis_item:before{content:""; position:absolute; left:0; bottom:20px; z-index:0; width:1px; height:500px; background:#EBEBEB;}
        .chartBlock .xAxis .axisLine .axis_item span{position:absolute; left:-20%; display:block; width:100%;}
        .chartBlock .xAxis .axisLine .axis_item:first-child span{left:0;}
        .chartBlock .xAxis .axisLine .axis_item_last{position:absolute; right:0;}
        .chartBlock .xAxis .axisLine .axis_item_last:before{right:0; left:auto;}
        .chartBlock .xAxis .axisLine .axis_item_last span{position:absolute; left:20%; text-align:right;}
        .chartBlock .xAxis .axisLine .axisLine_long{position:relative;}

        .chartBlock .xAxis .axisBars{position:relative; z-index:10;}
        .chartBlock .xAxis .axisBars .axis_bar{height:50px;}
        .chartBlock .xAxis .axisBars .axis_bar .barLine{position:relative; height:20px; background:#F4F250;}
        .chartBlock .xAxis .axisBars .axis_bar .bar_item{position:absolute; top:0; height:100%;}
        .chartBlock .xAxis .axisBars .axis_bar .bar_item-gc{left:0; background:#04964D;}
        .chartBlock .xAxis .axisBars .axis_bar .bar_item-zf{background:#5A9CD9;}
        .colorIntro{margin-top:10px; text-align:center;}
        .colorIntro .item{display:inline-block; margin:0 10px;}
        .colorIntro .item .color{display:inline-block; width:24px; height:13px; margin-right:3px; vertical-align:middle; border-radius:2px;}
        .colorIntro .item .color-1{background:#04964D;}
        .colorIntro .item .color-2{background:#5A9CD9;}
        .colorIntro .item .color-3{background:#F4F250;}
          /* 设定滚动高度 */
        .chartBlock{height:260px !important;}
        .chartBlock .yAxis .yAxis_inner,
        .chartBlock .xAxis .axisBars{height:210px; overflow-y:auto;}

        .showBarInfo{position:absolute; padding:10px; z-index:999999999; color:#FFF; background:#828282; border-radius:6px;}
        .showBarInfo li{line-height:25px;}

        /* 封闭资源柱状图 */
        .barChart{height:300px; padding-right:15px;}
        .barChart .yAxis{position:relative; z-index:100; width:25%; border-right:1px solid  #000;}
        .barChart .yAxis .axisLine .axis_label{height:27px; padding-right:10px; font-size:12px;}
        .barChart .xAxis{position:relative; width:75%;}
        .barChart .xAxis:before{content:"0"; position:absolute; bottom:0; left:0;}
        .barChart .xAxis .axisLine{position:relative; z-index:1; border-top:1px solid #000;}
        .barChart .xAxis .axisLine .axis_item{position:relative; float:left; width:20%; height:20px; text-align:right;}
        .barChart .xAxis .axisLine .axis_item:before{content:""; position:absolute; right:0; bottom:20px; z-index:0; width:1px; height:190px; background:#EBEBEB;}
        .barChart .xAxis .axisLine .axis_item span{position:absolute; right:-10px;}
        .barChart .xAxis .axisLine .axis_item_last{position:absolute; right:0;}
        .barChart .xAxis .axisLine .axis_item_last:before{right:0; left:auto;}
        .barChart .xAxis .axisLine .axis_item_last span{position:absolute; left:20%; text-align:right;}
        .barChart .xAxis .axisLine .axisLine_long{position:relative;}
        .barChart .xAxis .axisBars{position:relative; z-index:10;}
        .barChart .xAxis .axisBars .axis_bar{position:relative; height:27px; cursor:pointer;}
        .barChart .xAxis .axisBars .axis_bar .bar_item{position:absolute; left:0; top:0; height:15px;}
        .barChart .xAxis .axisBars .axis_bar .bar_item-jhs{background:#1C9493;}
        .barChart .xAxis .axisBars .axis_bar .bar_item-zxs{background:#7CB554;}

        /* 年度执行情况 */
        .bar2Chart{height:300px; padding-top:10px; padding-right:10px;}
        .bar2Chart .xAxis .axisLine{position:relative; width:10%; border-right:1px solid #000;}
        .bar2Chart .xAxis .axisLine:before{content:"0"; position:absolute; bottom:-10px; right:10px;}
        .bar2Chart .xAxis .axisLine .axis_label{position:relative; height:53px;}
        .bar2Chart .xAxis .axisLine .axis_label:before{content:""; position:absolute; right:0; top:0; width:5px; height:1px; background:#000;}
        .bar2Chart .xAxis .axisLine .axis_label:after{content:""; position:absolute; left:100%; top:0; width:900%; height:1px; background:#BEBEBE;}
        .bar2Chart .xAxis .axisLine .axis_label span{position:absolute; right:10px; top:-10px;}
        .bar2Chart .xAxis .axisBars{width:90%; height:200px;}
        .bar2Chart .xAxis .axisBars .axis_bar{height:100%;}
        .bar2Chart .xAxis .axisBars .axis_bar-inner{width:60px; height:100%; margin:0 auto;}
        .bar2Chart .xAxis .axisBars .bar_item{position:relative; width:20px; height:100%; margin:0 5px;}
        .bar2Chart .xAxis .axisBars .bar_item .color_item{position:absolute; bottom:0; width:100%;}
        .bar2Chart .xAxis .axisBars .bar_item .color_item-tdjh{background:#C23531;}
        .bar2Chart .xAxis .axisBars .bar_item .color_item-tdzx{background:#2F4554;}
        .bar2Chart .xAxis .axisBars .bar_item .color_item-xmjh{background:#61A0A8;}
        .bar2Chart .xAxis .axisBars .bar_item .color_item-xmzx{background:#D48265;}
        .bar2Chart .yAxis{margin-left:10%;}
        .bar2Chart .yAxis .axisLine{position:relative; z-index:1; border-top:1px solid #000;}
        .bar2Chart .yAxis .axis_item:before{content:""; position:absolute; right:0; top:0; width:1px; height:5px; background:#000;}
        .bar2Chart .yAxis .axisLine .axis_item{position:relative; float:left; height:20px; text-align:center;}
    </style>
    <script>

        $(function(){
            var myChart1 = echarts.init(document.getElementById('chart1')),
                myChart2 = echarts.init(document.getElementById('chart2'));
			//项目保证成果基础数据
            $.dajax({
                cache: true,
                type: "post",
                url: "index/getJcsj.do",
                dataType: "json",
                success: function (data) {
                    var xms = data[0].XMS;
                    var ztz = data[0].ZTZ;
                    var ndzjjh = data[0].NDZJJH;
                    var nddwzj = data[0].NDDWZJ;
                    var ljdwzj = data[0].LJDWZJ;
                    $(".showResult").find(".result_item").eq(0).find("strong > b").html(xms);
                    $(".showResult").find(".result_item").eq(1).find("strong > b").html(ztz);
                    $(".showResult").find(".result_item").eq(2).find("strong > b").html(ndzjjh);
                    $(".showResult").find(".result_item").eq(3).find("strong > b").html(nddwzj);
                    $(".showResult").find(".result_item").eq(4).find("strong > b").html(ljdwzj);
                }
            });
			//项目分类投资
            $.dajax({
                cache: true,
                type: "post",
                url: "index/getXmfltz.do",
                dataType: "json",
                success: function (data) {
                	seriesData = [];
                	seriesData1= [];
                	$.each(data, function (i, item) {
                		var temp = {
                				name: data[i].MC,
                				type: 'line', 
                				// stack: '总量',
                				data:[]
                               }
                		seriesData.push(temp);
                		seriesData1.push(data[i].MC);
                    });
                	
                    var option1 = {
                        title: {
                            text: '项目分类投资情况',
                            left: 'center'
                        },
                        tooltip: {
                            trigger: 'axis'
                        },
                        legend: {
                            data: seriesData1,
                            left: 'center',
                            bottom: '0'
                        },
                        grid: {
                            left: '3%',
                            right: '4%',
                            top: '10%',
                            bottom: '17%',
                            containLabel: true
                        },
                        xAxis: {
                            type: 'category',
                            data: []
                        },
                        yAxis: {
                            type: 'value'
                        },
                        series: seriesData

                    };
                  
                    $.each(data, function (i, item) {
                        
                        option1.series[i].data.push(item.YEAR1);
                        option1.series[i].data.push(item.YEAR2);
                        option1.series[i].data.push(item.YEAR3);
                        option1.series[i].data.push(item.YEAR4);
                        option1.series[i].data.push(item.YEAR5);
    
                    });
                    var date=new Date;
                    var year=date.getFullYear(); 
                    option1.xAxis.data.push(year-2);
                    option1.xAxis.data.push(year-1);
                    option1.xAxis.data.push(year);
                    option1.xAxis.data.push(year+1);
                    option1.xAxis.data.push(year+2);
                    myChart1.setOption(option1);
                }
            });

			
			//重点平台年度投资情况
            $.dajax({
                url: "index/getPtxmtz.do",
                dataType: "json",
                cache: true,
                type: "post",
                success: function (data) {
                	guidData = [];
                    var option2 = {
                        title: {
                            text: '重点平台年度项目投资情况',
                            left: 'center'
                        },
                        tooltip: {
                            trigger: 'axis',
                            axisPointer: {
                                type: 'shadow'
                            }
                        },
                        grid: {
                            left: '3%',
                            right: '4%',
                            top: '10%',
                            bottom: '17%',
                            containLabel: true
                        },
                        legend: {
                            data: ['投资数', '实际投资数'],
                            left: 'center',
                            bottom: '5%'
                        },
                        xAxis: {
                            type: 'category',
                            data: []
                        },
                        yAxis: {
                            type: 'value'
                        },
                        series: [
                            {
                                name: '投资数',
                                type: 'bar',
                                barWidth: 20,
                                barGap: 0,
                                data: []
                            },
                            {
                                name: '实际投资数',
                                type: 'bar',
                                barWidth: 20,
                                data: []
                            }
                        ]
                    };
                    var maxYValue = 0;
                    $.each(data, function (i, item) {
                        item.TZS = parseInt(item.TZS);
                        item.SJTZS = parseInt(item.SJTZS);
                        guidData[i]=item.GUID;
                        option2.series[0].data.push(item.TZS);
                        option2.series[1].data.push(item.SJTZS);
                        option2.xAxis.data.push(item.MC);
                        if (maxYValue < item.TZS) maxYValue = item.TZS;
                        if (maxYValue < item.SJTZS) maxYValue = item.SJTZS;
                    });
                    myChart2.setOption(option2);

                    myChart2.on('click', function (params) {

                        viewProgram(guidData[params.dataIndex]);
                    });
                }
            });

			//封闭资源盘活情况
            $.dajax({
                url: "index/getZjph.do",
                dataType: "json",
                cache: true,
                type: "post",
                success: function (data) {
                    drawFBZY(data, $("#chart3"));
                }
            });
            
            
				
                
            
                
                


            // 使用刚指定的配置项和数据显示图表。
            
           
            

            /* 表格初始化 */
            $("#programTable").dtable({
                height:250,
                url:"index/getXminfo.do",
                columns:[
//                     {checkbox: false},
					{field:"", title:"序号", width:100, align:"center", formatter:function(value,row,index){return index+1;}},
					{field:'GUID', title:"项目guid", width:100, align:"center",visible:false},
                    {field:'MC', title: '项目名称', align: 'center', width: 200, formatter:function(value, row, index){
                        return '<a href="javascript:void(0);" onclick="viewRow('+ index +')" style="text-decoration:underline;">'+ value +'</a>'
                        }},
                    {field:'ZTZ', title: '总投资', align: 'center',width:100},
                    {field:'NDZJJH', title: '年度资金计划', align: 'center',width: 100},
                    {field:'NDDWZJ', title: '年度到位资金',  align: 'center',width: 100},
                    {field:'LJDWZJ', title: '累计到位资金',  align: 'center',width: 100},
                    {field:'NDDWZJL', title: '年度到位资金率', align: 'center',width: 100},
                    {field:'LJDWZJL', title: '累计到位资金率', align: 'center',width: 100}
                ],
                pageNumber: 1,//起始页
                pageSize: 5,//页面大小
                paginationHAlign: 'left',//分页按钮位置  left/right
                sidePagination: 'server',
                pagination:false,
                showRefresh:false,
                clickToSelect:true
            })
        });
        function viewRow(index) {
            var $table = $("#programTable"),
                tableData = $table.dtable("getData"),
                selectRow = tableData[index];
            var guid=selectRow.GUID;
           
            $.dopen({
                title: "详情",
                content: '<div class="rowDetail">' +bootstrap2/images/image.png
                '    <div class="dtl_info clearfix">' +
                '        <div class="col-xs-4">' +
                '            <h4 class="text-center">项目效果图</h4>' +
                '            <img src="" id="dtl_img" 	width="400px" height="260px"/>' +
                '        </div>' +
                '        <div class="col-xs-4">' +
                '            <h4 class="text-center">项目情况介绍</h4>' +
                '            <div id="dtl_intro"></div>' +
                '        </div>' +
                '        <div class="col-xs-4">' +
                '            <h4 class="text-center">执行率</h4>' +
                '            <div class="subChartBox" id="chart4"></div>' +
                '        </div>' +
                '    </div>' +
                '    <br>' +
                '    <div class="dtl_chart clearfix">' +
                '        <div class="col-xs-6">' +
                '            <h4 class="text-center"><b>工程时序进度</b></h4>' +
                '            <div class="subChartBox" id="chart5"></div>' +
                '        </div>' +
                '        <div class="col-xs-6">' +
                '            <div class="subChartBox" id="chart6"></div>' +
                '            <div class="subChartBox" id="chart6_2"></div>' +
                '        </div>' +
                '    </div>' +
                '</div>',
                area: ["98%","98%"],
                btn: ["关闭"]
            });
			
		
            //图片显示

             $.dajax({
                 cache: true,
                 type: "post",
                 url: "index/getXmpic.do",
                 dataType: "json",
                 data:{guid:guid},
                 success: function (data) {
                        $("#dtl_img").attr("src", "file/downloadByid.do?id="+data[0].GUID);
//              			$("#dtl_img").attr("src", "file/downloadByid.do?id="+guid);
                 }
             });
            //项目情况介绍
            $.dajax({
                cache: true,
                type: "post",
                url: "index/getXmnr.do",
                dataType: "json",
                data:{guid:guid},
                success: function (data) {
                    var xmnr = data[0].XMNR;
                    $("#dtl_intro").html(xmnr);
                }
            });
            
            var myChart4 = echarts.init(document.getElementById('chart4')),
                // myChart5 = echarts.init(document.getElementById('chart5')),
                myChart6 = echarts.init(document.getElementById('chart6')),
                myChart6_2 = echarts.init(document.getElementById('chart6_2'));
            
            
            //项目执行率
            $.dajax({
                cache: true,
                type: "post",
                url: "index/getZxl.do",
                dataType: "json",
                data:{guid:guid},
                success: function (data) {
                	seriesData = [];
                	seriesData1 = [];
                	seriesData2 = [];
                	var temp = {
                            value: data[0].ZJDWL,
                            name: '资金到位率',                            
                           }
                	var temp1 = {
                            value: 100-data[0].ZJDWL,
                            name: '',                            
                           }
                	var temp2 = {
                            value: data[0].TZWCL,
                            name: '投资完成率',                            
                           }
                	var temp3 = {
                            value: 100-data[0].TZWCL,
                            name: '',                            
                           }
                	var temp4 = {
                            value: data[0].CWZXL,
                            name: '财务执行率',                            
                           }
                	var temp5 = {
                            value: 100-data[0].CWZXL,
                            name: '',                            
                           }
                	seriesData.push(temp);
                	seriesData.push(temp1);
                	
                	seriesData1.push(temp2);
                	seriesData1.push(temp3);
                	
                	seriesData2.push(temp4);
                	seriesData2.push(temp5);
                	
                	var option4 = {
                            tooltip : {
                                trigger: 'item',
                                formatter: "{c} ({d}%)"
                            },
                            calculable : true,
                            series : [
                                {
                                    name:'资金到位率',
                                    type:'pie',
                                    radius : [0, 50],
                                    center : ['50%', '25%'],
                                    hoverAnimation: false,
                                    label: {
                                        normal: {
                                            show: true,
                                            position: 'inside',
                                            formatter: '{b}\n{d}%'
                                        },
                                        emphasis: {
                                            show: true
                                        }
                                    },
                                    data:seriesData
                                },
                                {
                                    name:'投资完成率',
                                    type:'pie',
                                    radius : [0, 50],
                                    center : ['25%', '75%'],
                                    hoverAnimation: false,
                                    label: {
                                        normal: {
                                            show: true,
                                            position: 'inside',
                                            formatter: "{b}\n{d}%"
                                        },
                                        emphasis: {
                                            show: true
                                        }
                                    },
                                    data:seriesData1
                                },
                                {
                                    name:'财务执行率',
                                    type:'pie',
                                    radius : [0, 50],
                                    center : ['75%', '75%'],
                                    hoverAnimation: false,
                                    label: {
                                        normal: {
                                            show: true,
                                            position: 'inside',
                                            formatter: '{b}\n{d}%'
                                        },
                                        emphasis: {
                                            show: true
                                        }
                                    },
                                    data:seriesData2
                                }
                            ]
                        };
          
                    myChart4.setOption(option4);
                }
            });
            
            
          	//工程进度
            $.dajax({
                url: "index/getGcjd.do",
                dataType: "json",
                cache: true,
                type: "post",
                data:{guid:guid},
                success: function (data) {
                    if(data.TASKLIST.length > 0) setChart(data, $("#chart5"));

                    var colorIntro = '<div class="colorIntro clearfix">' +
                        '<div class="item"><i class="color color-1"></i><span>工程进度</span></div>' +
                        '<div class="item"><i class="color color-2"></i><span>支付进度</span></div>' +
                        '<div class="item"><i class="color color-3"></i><span>项目计划周期</span></div>' +
                        '</div>';
                    $("#chart5").after(colorIntro);
                }
            });

          //资金情况
            $.dajax({
                url: "index/getZjqk.do",
                dataType: "json",
                cache: true,
                type: "post",
                data:{guid:guid},
                success: function (data) {
                	seriesData = [];
                	seriesData1 = [];
                	seriesData2 = [];
                    $.each(data, function (i, item) {
                        var temp = {
                            value: data[i].YDWZJ,
                            name: '已到位资金',
                            
                        };
                        var temp1 = {
                                value: data[i].WDWZJ,
                                name: '未到位资金',
                               
                            };
                        var temp2 = {
                                value: data[i].ZZJ,
                                name:  data[i].MC,
                               
                            };
                       
                        seriesData.push(temp);
                        seriesData.push(temp1);
                        seriesData1.push(temp2);
                        seriesData2.push(data[i].MC);
                    });
                   
            	
                	var option6 = {
                            title:{
                                text:"资金情况",
                                left:"center"
                            },
                            tooltip: {
                                trigger: 'item',
                                formatter: "{a} <br/>{b}: {c} ({d}%)"
                            },
                            legend: {
                                orient: 'vertical',
                                left: '80%',
                                bottom: 30,
//                                 data:['政府性资源','一般公共预算','共建共享资金','PPP社会资本','自筹资金']
                            	data:seriesData2
//                             	 show: false
                            },
                            series: [
                                {
                                    name:'资金来源',
                                    type:'pie',
                                    radius: ['60%', '75%'],
                                    center: ['40%', '60%'],
                                    hoverAnimation: false,
                                    label: {
                                        normal:{
                                            formatter: '{b}：\n{c}',
                                            show:true
                                        }
                                    },
                                    data:seriesData1
                                }
                            ]
                        },
                        option6_2 = {
                            color: ['#1C9493','#7CB554'],
                            tooltip: {
                                trigger: 'item',
                                formatter: "{a} <br/>{b}: {c} ({d}%)"
                            },
                            legend: {
                                orient: 'vertical',
                                left: '80%',
                                top: 80,
                                data:['已到位资金', '未到位资金']
                            },
                            series: [
                                {
                                    name:'到位资金情况',
                                    type:'pie',
                                    selectedMode: 'single',
                                    hoverAnimation: false,
                                    radius: [0, '50%'],
                                    center: ['40%', '60%'],
                                    label: {
                                        normal: {
                                            show: false
                                        }
                                    },
                                    labelLine: {
                                        normal: {
                                            show: false
                                        }
                                    },
                                    data:seriesData
                                }
                            ]
                        };

                    myChart6.setOption(option6);
                    myChart6_2.setOption(option6_2);
                }
            });
                
        
        }

        function viewProgram(value){
        	var guid=value;
        	//TODO
        	guid='';
        	
            $.dopen({
                title: "详情",
                content: '<div class="programDetail">' +
                '    <div class="dtl_head" >详情介绍</div>' +
                '    <div class="clearfix">' +
                '        <div class="col-xs-4">' +
                '            <div class="subChartBox" id="chart7"></div>' +
                '        </div>' +
                '        <div class="col-xs-5">' +
                '            <div class="subChartBox" id="chart8"></div>' +
                '            <div class="subChartBox" id="chart8_2"></div>' +
                '        </div>' +
                '        <div class="col-xs-3">' +
                '            <h4 class="text-center">风险预警</h4>' +
                '            <div id="dtl_intro"></div>' +             
                '        </div>' +
                '        <div class="col-xs-10">' +
                '            <div class="subChartBox" id="chart9"></div>' +
                '        </div>' +
                '        <div class="col-xs-2">' +
                '            <ul class="chartIntro list-unstyled">' +
                '                <li>11：竣工财务决算</li>' +
                '                <li>10：工程结算</li>' +
                '                <li>9：验收及移交</li>' +
                '                <li>8：施工</li>' +
                '                <li>7：合同</li>' +
                '                <li>6：工程招投标</li>' +
                '                <li>5：招标文件及施工图预算</li>' +
                '                <li>4：概算</li>' +
                '                <li>3：方案设计</li>' +
                '                <li>2：可研</li>' +
                '                <li>1：立项</li>' +
                '                <li>0：前期策划</li>' +
                '            </ul>' +
                '        </div>' +
                '    </div>' +
                '</div>',
                area: ["98%","98%"],
                btn: ["关闭"]
            });
            
            
            
          //风险预警
            $.dajax({
                cache: true,
                type: "post",
                url: "index/getFxyj.do",
                dataType: "json",
                data:{guid:guid},
                success: function (data) {
                    var nr = data[0].NR;
                    $("#dtl_intro").html(nr);
                }
            });
            
            var myChart7 = echarts.init(document.getElementById('chart7')),
                myChart8 = echarts.init(document.getElementById('chart8')),
                myChart8_2 = echarts.init(document.getElementById('chart8_2')),
                myChart9 = echarts.init(document.getElementById('chart9'));
            
          //平台项目执行率
            $.dajax({
                cache: true,
                type: "post",
                url: "index/getPtZxl.do",
                dataType: "json",
                data:{guid:guid},
                success: function (data) {
                	seriesData = [];
                	seriesData1 = [];
                	seriesData2 = [];
                	var temp = {
                            value: data[0].ZJDWL,
                            name: '资金到位率',                            
                           }
                	var temp1 = {
                            value: 100-data[0].ZJDWL,
                            name: '',                            
                           }
                	var temp2 = {
                            value: data[0].TZWCL,
                            name: '投资完成率',                            
                           }
                	var temp3 = {
                            value: 100-data[0].TZWCL,
                            name: '',                            
                           }
                	var temp4 = {
                            value: data[0].CWZXL,
                            name: '财务执行率',                            
                           }
                	var temp5 = {
                            value: 100-data[0].CWZXL,
                            name: '',                            
                           }
                	seriesData.push(temp);
                	seriesData.push(temp1);
                	
                	seriesData1.push(temp2);
                	seriesData1.push(temp3);
                	
                	seriesData2.push(temp4);
                	seriesData2.push(temp5);
                	
                	var option7 = {
                            tooltip : {
                                trigger: 'item',
                                formatter: "{c} ({d}%)"
                            },
                            calculable : true,
                            series : [
                                {
                                    name:'资金到位率',
                                    type:'pie',
                                    radius : [0, 50],
                                    center : ['50%', '25%'],
                                    hoverAnimation: false,
                                    label: {
                                        normal: {
                                            show: true,
                                            position: 'inside',
                                            formatter: '{b}\n{d}%'
                                        },
                                        emphasis: {
                                            show: true
                                        }
                                    },
                                    data:seriesData
                                },
                                {
                                    name:'投资完成率',
                                    type:'pie',
                                    radius : [0, 50],
                                    center : ['25%', '75%'],
                                    hoverAnimation: false,
                                    label: {
                                        normal: {
                                            show: true,
                                            position: 'inside',
                                            formatter: "{b}\n{d}%"
                                        },
                                        emphasis: {
                                            show: true
                                        }
                                    },
                                    data:seriesData1
                                },
                                {
                                    name:'财务执行率',
                                    type:'pie',
                                    radius : [0, 50],
                                    center : ['75%', '75%'],
                                    hoverAnimation: false,
                                    label: {
                                        normal: {
                                            show: true,
                                            position: 'inside',
                                            formatter: '{b}\n{d}%'
                                        },
                                        emphasis: {
                                            show: true
                                        }
                                    },
                                    data:seriesData2
                                }
                            ]
                        };
          
                    myChart7.setOption(option7);
                }
            });
            
          //平台资金情况
            $.dajax({
                url: "index/getPtZjqk.do",
                dataType: "json",
                cache: true,
                type: "post",
                data:{guid:guid},
                success: function (data) {
                	seriesData = [];
                	seriesData1 = [];
                	seriesData2 = [];
                    $.each(data, function (i, item) {
                        var temp = {
                            value: data[i].YDWZJ,
                            name: '已到位资金',
                            
                        };
                        var temp1 = {
                                value: data[i].WDWZJ,
                                name: '未到位资金',
                               
                            };
                        var temp2 = {
                                value: data[i].ZZJ,
                                name:  data[i].MC,
                               
                            };
                       
                        seriesData.push(temp);
                        seriesData.push(temp1);
                        seriesData1.push(temp2);
                        seriesData2.push(data[i].MC);
                    });
                	
                	var option8 = {
                            title:{
                                text:"资金情况",
                                left:"center"
                            },
                            tooltip: {
                                trigger: 'item',
                                formatter: "{a} <br/>{b}: {c} ({d}%)"
                            },
                            legend: {
                                orient: 'vertical',
                                left: '75%',
                                bottom: 20,
                                data:seriesData2
                            },
                            series: [
                                {
                                    name:'资金来源',
                                    type:'pie',
                                    radius: ['60%', '75%'],
                                    center: ['40%', '60%'],
                                    hoverAnimation: false,
                                    label: {
                                        normal:{
                                            formatter: '{b}：\n{c}',
                                            show:true
                                        }
                                    },
                                    data:seriesData1
                                }
                            ]
                        },
                        option8_2 = {
                            color: ['#1C9493','#7CB554'],
                            tooltip: {
                                trigger: 'item',
                                formatter: "{a} <br/>{b}: {c} ({d}%)"
                            },
                            legend: {
                                orient: 'vertical',
                                left: '75%',
                                top: 0,
                                data:['已到位资金', '未到位资金']
                            },
                            series: [
                                {
                                    name:'到位资金情况',
                                    type:'pie',
                                    selectedMode: 'single',
                                    hoverAnimation: false,
                                    radius: [0, '50%'],
                                    center: ['40%', '60%'],
                                    label: {
                                        normal: {
                                            show: false
                                        }
                                    },
                                    labelLine: {
                                        normal: {
                                            show: false
                                        }
                                    },
                                    data:seriesData
                                }
                            ]
                        };

                    myChart8.setOption(option8);
                    myChart8_2.setOption(option8_2);
                }
            });
                
          
          
            $.dajax({
                url: "index/getXmjd.do",
                dataType: "json",
                cache: true,
                type: "post",
                data:{guid:guid},
                success: function (data) {
                	
                	guidDatas = [];
                	option9 = {
                            color: ['#1C9493','#7CB554'],
                            tooltip : {
                                trigger: 'axis',
                                axisPointer : {
                                    type : 'shadow'
                                }
                            },
                            grid: {
                                left: '3%',
                                right: '3%',
                                top: 0,
                                bottom: 0,
                                containLabel: true
                            },
                            xAxis: {
                                type : 'value',
                                min: 0,
                                max: 11,
                                splitNumber: 12
                            },
                            yAxis: {
                                type : 'category',
                                inverse: true,
                                data : []
                            },
                            series : [
                                {
                                    name:'执行',
                                    type:'bar',
                                    barWidth:5,
                                    stack: '总量',
                                    data:[]
                                }
                            ]
                        };
                  
                	//TODO
                	$.each(data, function (i, item) {

                        guidDatas[i]=item.GUID;
                        option9.series[0].data.push(item.ZX);
                        option9.yAxis.data.push(item.XMMC);
                     
                    });
                   
                    myChart9.setOption(option9);
                    myChart9.on('click', function (params) {

                        viewRowLink(guidDatas[params.dataIndex]);
                    });
                	
                	
                	
                }
            });   
                
        }
        
        function viewRowLink(value) {
            var guid=value;
            //TODO
            guid='';
         
            $.dopen({
                title: "详情",
                content: '<div class="rowDetail">' +
                '    <div class="dtl_info clearfix">' +
                '        <div class="col-xs-4">' +
                '            <h4 class="text-center">项目效果图</h4>' +
                '            <img src="" id="dtl_img1" width="400px" height="260px"/>' +
                '        </div>' +
                '        <div class="col-xs-4">' +
                '            <h4 class="text-center">项目情况介绍</h4>' +
                '            <div id="dtl_intro1"></div>' +
                '        </div>' +
                '        <div class="col-xs-4">' +
                '            <h4 class="text-center">执行率</h4>' +
                '            <div class="subChartBox" id="chart4"></div>' +
                '        </div>' +
                '    </div>' +
                '    <br>' +
                '    <div class="dtl_chart clearfix">' +
                '        <div class="col-xs-6">' +
                '            <div class="subChartBox" id="chart5"></div>' +
                '        </div>' +
                '        <div class="col-xs-6">' +
                '            <div class="subChartBox" id="chart6"></div>' +
                '            <div class="subChartBox" id="chart6_2"></div>' +
                '        </div>' +
                '    </div>' +
                '</div>',
                area: ["98%","98%"],
                btn: ["关闭"]
            });
		
            //图片显示
            $.dajax({
                cache: true,
                type: "post",
                url: "index/getXmpic.do",
                dataType: "json",
                data:{guid:guid},
                success: function (data) {
                	 $("#dtl_img1").attr("src", "file/downloadByid.do?id="+data[0].GUID);
                }
            });
            //项目情况介绍
            $.dajax({
                cache: true,
                type: "post",
                url: "index/getXmnr.do",
                dataType: "json",
                data:{guid:guid},
                success: function (data) {
                    var xmnr = data[0].XMNR;
                    $("#dtl_intro1").html(xmnr);
                }
            });
            
            var myChart4 = echarts.init(document.getElementById('chart4')),
                myChart5 = echarts.init(document.getElementById('chart5')),
                myChart6 = echarts.init(document.getElementById('chart6')),
                myChart6_2 = echarts.init(document.getElementById('chart6_2'));
            
            
            //项目执行率
            $.dajax({
                cache: true,
                type: "post",
                url: "index/getZxl.do",
                dataType: "json",
                data:{guid:guid},
                success: function (data) {
                	seriesData = [];
                	seriesData1 = [];
                	seriesData2 = [];
                	var temp = {
                            value: data[0].ZJDWL,
                            name: '资金到位率',                            
                           }
                	var temp1 = {
                            value: 100-data[0].ZJDWL,
                            name: '',                            
                           }
                	var temp2 = {
                            value: data[0].TZWCL,
                            name: '投资完成率',                            
                           }
                	var temp3 = {
                            value: 100-data[0].TZWCL,
                            name: '',                            
                           }
                	var temp4 = {
                            value: data[0].CWZXL,
                            name: '财务执行率',                            
                           }
                	var temp5 = {
                            value: 100-data[0].CWZXL,
                            name: '',                            
                           }
                	seriesData.push(temp);
                	seriesData.push(temp1);
                	
                	seriesData1.push(temp2);
                	seriesData1.push(temp3);
                	
                	seriesData2.push(temp4);
                	seriesData2.push(temp5);
                	
                	var option4 = {
                            tooltip : {
                                trigger: 'item',
                                formatter: "{c} ({d}%)"
                            },
                            calculable : true,
                            series : [
                                {
                                    name:'资金到位率',
                                    type:'pie',
                                    radius : [0, 50],
                                    center : ['50%', '25%'],
                                    hoverAnimation: false,
                                    label: {
                                        normal: {
                                            show: true,
                                            position: 'inside',
                                            formatter: '{b}\n{d}%'
                                        },
                                        emphasis: {
                                            show: true
                                        }
                                    },
                                    data:seriesData
                                },
                                {
                                    name:'投资完成率',
                                    type:'pie',
                                    radius : [0, 50],
                                    center : ['25%', '75%'],
                                    hoverAnimation: false,
                                    label: {
                                        normal: {
                                            show: true,
                                            position: 'inside',
                                            formatter: "{b}\n{d}%"
                                        },
                                        emphasis: {
                                            show: true
                                        }
                                    },
                                    data:seriesData1
                                },
                                {
                                    name:'财务执行率',
                                    type:'pie',
                                    radius : [0, 50],
                                    center : ['75%', '75%'],
                                    hoverAnimation: false,
                                    label: {
                                        normal: {
                                            show: true,
                                            position: 'inside',
                                            formatter: '{b}\n{d}%'
                                        },
                                        emphasis: {
                                            show: true
                                        }
                                    },
                                    data:seriesData2
                                }
                            ]
                        };
          
                    myChart4.setOption(option4);
                }
            });
            
            
          //工程进度
            $.dajax({
                url: "index/getGcjd.do",
                dataType: "json",
                cache: true,
                type: "post",
                data:{guid:guid},
                success: function (data) {
                    if(data.TASKLIST.length > 0) setChart(data, $("#chart5"));
                    
                    var colorIntro = '<div class="colorIntro clearfix">' +
                    '<div class="item"><i class="color color-1"></i><span>工程进度</span></div>' +
                    '<div class="item"><i class="color color-2"></i><span>支付进度</span></div>' +
                    '<div class="item"><i class="color color-3"></i><span>项目计划周期</span></div>' +
                    '</div>';
                $("#chart5").after(colorIntro);
                }
            });
            
            
                
                
          //资金情况
            $.dajax({
                url: "index/getZjqk.do",
                dataType: "json",
                cache: true,
                type: "post",
                data:{guid:guid},
                success: function (data) {
                	seriesData = [];
                	seriesData1 = [];
                	seriesData2 = [];
                    $.each(data, function (i, item) {
                        var temp = {
                            value: data[i].YDWZJ,
                            name: '已到位资金',
                            
                        };
                        var temp1 = {
                                value: data[i].WDWZJ,
                                name: '未到位资金',
                               
                            };
                        var temp2 = {
                                value: data[i].ZZJ,
                                name:  data[i].MC,
                               
                            };
                       
                        seriesData.push(temp);
                        seriesData.push(temp1);
                        seriesData1.push(temp2);
                        seriesData2.push(data[i].MC);
                    });
                	
                	var option6 = {
                            title:{
                                text:"资金情况",
                                left:"center"
                            },
                            tooltip: {
                                trigger: 'item',
                                formatter: "{a} <br/>{b}: {c} ({d}%)"
                            },
                            legend: {
                                orient: 'vertical',
                                left: "80%",
                                bottom: 30,
                                data:seriesData2
                            },
                            series: [
                                {
                                    name:'资金来源',
                                    type:'pie',
                                    radius: ['60%', '75%'],
                                    center: ['40%', '60%'],
                                    hoverAnimation: false,
                                    label: {
                                        normal:{
                                            formatter: '{b}：\n{c}',
                                            show:true
                                        }
                                    },
                                    data:seriesData1
                                }
                            ]
                        },
                        option6_2 = {
                            color: ['#1C9493','#7CB554'],
                            tooltip: {
                                trigger: 'item',
                                formatter: "{a} <br/>{b}: {c} ({d}%)"
                            },
                            legend: {
                                orient: 'vertical',
                                left: "80%",
                                top: 80,
                                data:['已到位资金', '未到位资金']
                            },
                            series: [
                                {
                                    name:'到位资金情况',
                                    type:'pie',
                                    selectedMode: 'single',
                                    hoverAnimation: false,
                                    radius: [0, '50%'],
                                    center: ['40%', '60%'],
                                    label: {
                                        normal: {
                                            show: false
                                        }
                                    },
                                    labelLine: {
                                        normal: {
                                            show: false
                                        }
                                    },
                                    data:seriesData
                                }
                            ]
                        };

                    myChart6.setOption(option6);
                    myChart6_2.setOption(option6_2);
                }
            });
                
        
        }

        //封闭运行图表
        function  viewProgram3(value){
        	
        	var guid=value;
        	//TODO
        	guid="";
        	
        	$.dopen({
                title: "详情",
                content: '<div class="program3Detail">' +
                '    <div class="dtl_chart clearfix">' +
                '        <div class="col-xs-4">' +
                '            <h4 class="text-center"><b>年度收入执行率</b></h4>' +
                '            <div class="subChartBox" id="pro3Chart_1"></div>' +
                '        </div>' +
                '        <div class="col-xs-4">' +
                '            <h4 class="text-center"><b>年度支出执行率</b></h4>' +
                '            <div class="subChartBox" id="pro3Chart_2"></div>' +
                '        </div>' +
                '        <div class="col-xs-4">' +
                '            <h4 class="text-center"><b>年度化债执行率</b></h4>' +
                '            <div class="subChartBox" id="pro3Chart_3"></div>' +
                '        </div>' +
                '    </div>' +
                '    <div class="dtl_chart clearfix">' +
                '        <div class="col-xs-6">' +
                '            <h4 class="text-center"><b>政府投资项目资源封闭运行分年收支情况</b></h4>' +
                '            <div class="subChartBox" id="pro3Chart_4"></div>' +
                '        </div>' +
                '        <div class="col-xs-6">' +
                '            <h4 class="text-center"><b>年度执行情况</b></h4>' +
                '            <div class="subChartBox" id="pro3Chart_5"></div>' +
                '        </div>' +
                '    </div>' +
                '</div>',
                area: ["98%","98%"],
                btn: ["关闭"]
            });

            var chart1 = echarts.init(document.getElementById('pro3Chart_1')),
                chart2 = echarts.init(document.getElementById('pro3Chart_2')),
                chart3 = echarts.init(document.getElementById('pro3Chart_3')),
                chart4 = echarts.init(document.getElementById('pro3Chart_4'));

            //执行率
            $.dajax({
                cache: true,
                type: "post",
                url: "index/getFbglzxl.do",
                dataType: "json",
                data:{guid:guid},
                success: function (data) {
                    var colorArr = ['#61A0A8', '#91C7AE', '#D48265'],
                        nameArr = ['年度收入执行率', '年度支出执行率', '年度化债执行率'],
                        dataArr = [[100-data[0].NDSR, data[0].NDSR], [100-data[0].NDZC, data[0].NDZC], [100-data[0].NDHZ, data[0].NDHZ]],
                        option = {};
                    for(var i=0; i<3; i++){
                        option = {
                            color: [colorArr[i], '#FFF'],
                            series: [
                                {
                                    name:nameArr[i],
                                    type:'pie',
                                    center: ['50%', '40%'],
                                    radius: ['70%', '80%'],
                                    hoverAnimation: false,
                                    label: {
                                        normal: {
                                            show: true,
                                            formatter: '{d}%',
                                            position: 'center',
                                            textStyle: {
                                                fontSize: '30'
                                            }
                                        }
                                    },
                                    data:[
                                        {value:dataArr[i][0], name:nameArr[i]},
                                        {value:dataArr[i][1], name:''}
                                    ]
                                }
                            ]
                        };
                        switch(i){
                            case 0:
                                chart1.setOption(option);
                                break;
                            case 1:
                                chart2.setOption(option);
                                break;
                            case 2:
                                chart3.setOption(option);
                                break;
                        }
                    }
                }
            });

            //收支情况
            $.dajax({
                cache: true,
                type: "post",
                url: "index/getFbglszqk.do",
                dataType: "json",
                data:{guid:guid},
                success: function (data) {
                    var option4 = {
                        tooltip: {
                            trigger: 'axis'
                        },
                        legend: {
                            data:['收入计划','收入执行','支出计划','支出执行','化债计划'],
                            bottom:'0'
                        },
                        grid: {
                            top: '3%',
                            left: '6%',
                            right: '4%',
                            bottom: '13%',
                            containLabel: true
                        },
                        xAxis: {
                            type: 'category',
                            boundaryGap: true,
                            data: []
                        },
                        yAxis: {
                            type: 'value'
                        },
                        series: [
                            {
                                name:'收入计划',
                                type:'line',
                                smooth:true,
                                data:[]
                            },
                            {
                                name:'收入执行',
                                type:'line',
                                smooth:true,
                                data:[]
                            },
                            {
                                name:'支出计划',
                                type:'line',
                                smooth:true,
                                data:[]
                            },
                            {
                                name:'支出执行',
                                type:'line',
                                smooth:true,
                                data:[]
                            },
                            {
                                name:'化债计划',
                                type:'line',
                                smooth:true,
                                data:[]
                            }
                        ]
                    };
                    $.each(data, function (i, item) {
                        option4.xAxis.data.push(data[i].YEAR);
                        option4.series[i].data.push(item.SRJH);
                        option4.series[i].data.push(item.SRZX);
                        option4.series[i].data.push(item.ZCJH);
                        option4.series[i].data.push(item.ZCZX);
                        option4.series[i].data.push(item.HZJH);
                        option4.series[i].data.push(item.HZZX);
                    });
                    chart4.setOption(option4);
                }
            });

            //年度执行情况
            $.dajax({
                cache: true,
                type: "post",
                url: "index/getFbglndzxqk.do",
                dataType: "json",
                data:{guid:guid},
                success: function (data) {
                    drawNDZX(data, $("#pro3Chart_5"));
                }
            });
        }

        function drawFBZY(data, target){
            var totalWidth = parseInt(data.MAXDATE),//x轴总宽
                splitNum = 5,//x轴的分割段数
                interval = Math.ceil(totalWidth / splitNum),//x轴等分后每份的值
                jh_percentArr = [],//计划占比
                zx_percentArr = [],//执行占比
                xAxisValArr = [],//x轴的值
                yAxisHtml = '<div class="yAxis pull-left">',
                xAxisHtml = '<div class="xAxis pull-left">',
                xAxis_barHtml = '<div class="axisBars">',
                xAxis_labelHtml = '<div class="axisLine clearfix">',
                titleHtml = '<h4 class="col-xs-12 text-center"><b>封闭资源盘活情况</b></h4>';

            //计算x轴
            for(var i=0; i<splitNum; i++){
                var prevVal = (i===0) ? 0 : xAxisValArr[i-1];
                xAxisValArr.push(prevVal + interval);
            }
            $.each(data.TASKLIST, function(key, val){
                jh_percentArr.push(val.JHS / totalWidth);
                zx_percentArr.push(val.ZXS / totalWidth);

                //执行和计划，值小的覆盖值大的
                var classNameArr = ["jhs", "zxs"], widthValArr = [jh_percentArr[key]*100, zx_percentArr[key]*100];
                if(jh_percentArr[key] < zx_percentArr[key]){
                    classNameArr.reverse();
                    widthValArr.reverse();
                }

                xAxis_barHtml += '<div class="axis_bar clearfix" data-guid="'+ val.GUID +'">' +
                    '    <div class="bar_item bar_item-'+ classNameArr[0] +'" style="width:'+ widthValArr[0] +'%;"></div>' +
                    '    <div class="bar_item bar_item-'+ classNameArr[1] +'" style="width:'+ widthValArr[1] +'%;"></div>' +
                    '</div>';

                yAxisHtml += '<div class="axisLine"><div class="axis_label text-right">'+ val.MC +'</div></div>';
            });
            $.each(xAxisValArr, function (i) {
                xAxis_labelHtml += '<div class="axis_item"><span>'+ xAxisValArr[i] +'</span></div>';
            });

            xAxis_barHtml += '</div>';
            xAxis_labelHtml += '</div>';
            xAxisHtml += xAxis_barHtml + xAxis_labelHtml + '</div>';
            yAxisHtml += '</div>';

            var colors = ["#1C9493", "#7CB554"];
            var colorIntro = '<div class="colorIntro clearfix col-xs-12">' +
                '<div class="item"><i class="color" style="background:'+colors[0]+';"></i><span>计划</span></div>' +
                '<div class="item"><i class="color" style="background:'+colors[1]+';"></i><span>执行</span></div>' +
                '</div>';

            target.addClass("barChart clearfix").html(titleHtml + yAxisHtml + xAxisHtml + colorIntro);

            //绑定鼠标移入事件;绑定点击事件
            var timer = null;
            target.find(".bar_item").hover(function(e){
                var othis = $(this);
                timer = setTimeout(function(){
                    var barIndex = othis.parent().index(),
                        barData = data["TASKLIST"][barIndex],
                        infoHtml = $('<ul class="list-unstyled showBarInfo" style="display:none; left:'+ e.pageX +'px; top:'+ e.pageY +'px;">' +
                            '<li>'+ barData.MC +'</li>' +
                            '<li><b>计划：</b>'+ barData.JHS +'</li>' +
                            '<li><b>执行：</b>'+ barData.ZXS +'</li>' +
                            '</ul>');
                    infoHtml.appendTo($("body")).fadeIn(400);
                }, 100);
            }, function(){
                clearTimeout(timer);
                $(".showBarInfo").remove();

            });
            target.find(".axis_bar").on("click", function(){
                viewProgram3($(this).parent().data("guid"));
            });
        }

        //年度执行情况
        function drawNDZX(data, target){
            var colors = ['#C23531', '#2F4554', '#61A0A8', '#D48265'],
                multi = 20,
                totalHeight = getTenMultiple(parseInt(data.MAXDATE), multi),//x轴总宽
                splitNum = totalHeight / multi,//x轴的分割段数
                interval = Math.ceil(totalHeight / splitNum),//x轴等分后每份的值
                intervalWidth = 100 / data.TASKLIST.length,
                tdjh_percentArr = [],//土地计划占比
                tdzx_percentArr = [],//土地执行占比
                xmjh_percentArr = [],//项目计划占比
                xmzx_percentArr = [],//项目执行占比
                yAxisValArr = [],//y轴的值
                yAxisHtml = '<div class="yAxis"><div class="axisLine clearfix">',
                xAxisHtml = '<div class="xAxis clearfix">',
                xAxis_barHtml = '<div class="axisBars pull-left">',
                xAxis_LineHtml = '<div class="axisLine pull-left">';

            //计算y轴
            for(var i=0; i<splitNum; i++){
                var prevVal = (i===0) ? 0 : yAxisValArr[i-1];
                yAxisValArr.push(prevVal + interval);
            }
            yAxisValArr.reverse();

            $.each(data.TASKLIST, function(key, val){
                tdjh_percentArr.push(val.TDJH / totalHeight);
                tdzx_percentArr.push(val.TDZX / totalHeight);
                xmjh_percentArr.push(val.XMJH / totalHeight);
                xmzx_percentArr.push(val.XMZX / totalHeight);

                yAxisHtml += '<div class="axis_item" style="width:'+ intervalWidth +'%;"><span>'+ val.MC +'</span></div>';

                //执行和计划，值小的覆盖值大的
                var classNameArr1 = ["tdjh", "tdzx"],
                    classNameArr2 = ["xmjh", "xmzx"],
                    widthValArr1 = [tdjh_percentArr[key]*100, tdzx_percentArr[key]*100],
                    widthValArr2 = [xmjh_percentArr[key]*100, xmzx_percentArr[key]*100];
                if(tdjh_percentArr[key] < tdzx_percentArr[key]){
                    classNameArr1.reverse();
                    widthValArr1.reverse();
                }
                if(xmjh_percentArr[key] < xmzx_percentArr[key]){
                    classNameArr2.reverse();
                    widthValArr2.reverse();
                }
                xAxis_barHtml += '<div class="axis_bar pull-left clearfix" style="width:'+ intervalWidth +'%;">' +
                    '<div class="axis_bar-inner">' +
                    '    <div class="bar_item bar_item_1 pull-left">' +
                    '        <div class="color_item color_item-'+ classNameArr1[0] +'" style="height:'+ widthValArr1[0] +'%;"></div>' +
                    '        <div class="color_item color_item-'+ classNameArr1[1] +'" style="height:'+ widthValArr1[1] +'%;"></div>' +
                    '    </div>' +
                    '    <div class="bar_item bar_item_2 pull-left">' +
                    '        <div class="color_item color_item-'+ classNameArr2[0] +'" style="height:'+ widthValArr2[0] +'%;"></div>' +
                    '        <div class="color_item color_item-'+ classNameArr2[1] +'" style="height:'+ widthValArr2[1] +'%;"></div>' +
                    '    </div>' +
                    '</div></div>';
            });

            $.each(yAxisValArr, function (i) {
                xAxis_LineHtml += '<div class="axis_label text-right"><span>'+ yAxisValArr[i] +'</span></div>';
            });

            xAxis_barHtml += '</div>';
            xAxis_LineHtml += '</div>';
            xAxisHtml += xAxis_LineHtml + xAxis_barHtml + '</div>';
            yAxisHtml += '</div></div>';

            var colorIntro = '<div class="colorIntro clearfix col-xs-12">' +
                '<div class="item"><i class="color" style="background:'+colors[0]+';"></i><span>土地计划</span></div>' +
                '<div class="item"><i class="color" style="background:'+colors[1]+';"></i><span>土地执行</span></div>' +
                '<div class="item"><i class="color" style="background:'+colors[2]+';"></i><span>项目计划</span></div>' +
                '<div class="item"><i class="color" style="background:'+colors[3]+';"></i><span>项目执行</span></div>' +
                '</div>';

            target.addClass("bar2Chart clearfix").html(xAxisHtml + yAxisHtml + colorIntro);
            target.find(".axisBars").height(target.find(".xAxis .axisLine").height());

            //绑定鼠标移入事件;绑定点击事件
            var timer = null;
            target.find(".axis_bar-inner").hover(function(e){
                var othis = $(this);
                timer = setTimeout(function(){
                    var barIndex = othis.index(),
                        barData = data["TASKLIST"][barIndex],
                        infoHtml = $('<ul class="list-unstyled showBarInfo" style="display:none; left:'+ e.pageX +'px; top:'+ e.pageY +'px;">' +
                            '<li>'+ barData.MC +'</li>' +
                            '<li><b>土地计划：</b>'+ barData.TDJH +'</li>' +
                            '<li><b>土地执行：</b>'+ barData.TDZX +'</li>' +
                            '<li><b>项目计划：</b>'+ barData.XMJH +'</li>' +
                            '<li><b>项目执行：</b>'+ barData.XMZX +'</li>' +
                            '</ul>');
                    infoHtml.appendTo($("body")).fadeIn(400);
                }, 100);
            }, function(){
                clearTimeout(timer);
                $(".showBarInfo").remove();
            });
        }
        //获取20的倍数
        function getTenMultiple(number, multi){
            var multi = (multi===undefined) ? 20 : multi,
                res = number;
            for(var j = 1; j <= multi; j++){
                if(number % multi === 0){
                    res = number;
                    return res;
                }

                if((number+j) % multi === 0){
                    res = number + j;
                    return res;
                }
            }
        }

        //画 图表
        function setChart(data, target){
            var task_dateRangeArr = [],//每个项目的总天数
                task_percentArr = [],//每个项目所占的百分比
                task_dateRangeArr_s = [],//每个项目内每个阶段的天数范围
                task_percentArr_s = [],//每个项目内每个阶段所占的百分比
                blankArr = [],//每个项目最开始的空白部分
                blankPercentArr = [],//每个项目最开始的空白部分占比
                xAxisHtml = '<div class="xAxis pull-left">',
                yAxisHtml = '<div class="yAxis pull-left"><div class="yAxis_inner">',
                xAxisRangeArr = setXAxisRange(data.MINDATE, data.MAXDATE),
                totalDateRange = getDateRange(data.MINDATE, xAxisRangeArr[xAxisRangeArr.length-1]);//总时间天数

            var longWidth = (xAxisRangeArr.length > 5) ? 100*(xAxisRangeArr.length-1) : "100%";
            xAxisHtml += '<div class="axisLine_long clearfix" style="width:'+ longWidth +'px;">' +
                '<div class="axisBars">';

            $.each(data.TASKLIST, function(i){
                blankArr[i] = getDateRange(data.MINDATE, this.DATE1);
                blankPercentArr[i] = blankArr[i] / totalDateRange;
                task_dateRangeArr[i] = getDateRange(this.DATE1, this.DATE4);
                task_percentArr[i] = task_dateRangeArr[i] / totalDateRange;
                task_dateRangeArr_s[i] = [getDateRange(this.DATE1, this.DATE2), getDateRange(this.DATE1, this.DATE3)];

                task_percentArr_s[i] = [];
                $.each(task_dateRangeArr_s[i], function(k, v){
                    task_percentArr_s[i].push(v / task_dateRangeArr[i]);
                });

                var task_perHtml = "", classArr = ['gc', 'zf'];
                $.each(task_percentArr_s[i], function (k) {
                    if(task_percentArr_s[i][0] < task_percentArr_s[i][1]){
                        task_percentArr_s[i].reverse();
                        classArr.reverse();
                    }
                    task_perHtml += '<div class="bar_item bar_item-'+ classArr[k] +'" style="width:'+ task_percentArr_s[i][k]*100 +'%;"></div>';
                });

                xAxisHtml += '<div class="axis_bar">' +
                    '    <div class="barLine clearfix" style="width:'+ task_percentArr[i]*longWidth +'px; margin-left:'+ blankPercentArr[i]*longWidth +'px;">' +
                    task_perHtml +
                    '    </div>' +
                    '</div>';

                yAxisHtml += '<div class="axisLine clearfix" data-id="'+ this.ID +'">' +
                    '    <div class="axis_label text-right">'+ this.NAME +'</div>' +
                    '</div>'
            });
            xAxisHtml += '</div><div class="axisLine clearfix">';
            $.each(xAxisRangeArr, function(i){
                var lastClass = (i < xAxisRangeArr.length - 1) ?"" : " axis_item_last",
                    itemWid = (xAxisRangeArr.length > 5) ? "100px" : "20%";
                xAxisHtml += '<div class="axis_item'+ lastClass +'" style="width:'+ itemWid +';"><span>'+ xAxisRangeArr[i].substr(0, 7) +'</span></div>';
            });
            xAxisHtml += '</div></div></div>';
            yAxisHtml += '</div></div>';

            target.addClass("chartBlock clearfix").html(yAxisHtml + xAxisHtml);

            //绑定鼠标移入事件
            var timer = null;
            target.find(".barLine").hover(function(e){
                var othis = $(this);
                timer = setTimeout(function(){
                    var barIndex = othis.parents(".axis_bar").index(),
                        barData = data["TASKLIST"][barIndex],
                        infoHtml = $('<ul class="list-unstyled showBarInfo" style="display:none; left:'+ e.pageX +'px; top:'+ e.pageY +'px;">' +
                            '<li><b>项目名称：</b>'+ barData.NAME +'</li>' +
                            '<li><b>阶段1：</b>'+ barData.DATE1 +'</li>' +
                            '<li><b>阶段2：</b>'+ barData.DATE2 +'</li>' +
                            '<li><b>阶段3：</b>'+ barData.DATE3 +'</li>' +
                            '<li><b>阶段4：</b>'+ barData.DATE4 +'</li>' +
                            '</ul>');
                    infoHtml.appendTo($("body")).fadeIn(400);
                }, 100);
            }, function(){
                clearTimeout(timer);
                $(".showBarInfo").remove();
            });

            //图表区和y轴同步滚动
            target.find(".axisBars").scroll(function(){
                target.find(".yAxis_inner").scrollTop($(this).scrollTop());
            });
        }
        //获取时间范围
        function getDateRange(start, end){
            var minDate = new Date(start).getTime(),
                maxDate = new Date(end).getTime();
            return getDayNum(maxDate - minDate);
        }
        //获取时间范围内的天数
        function getDayNum(d){
            return parseInt(d) / (1000 * 60 * 60 * 24);
        }
        //日期格式化，返回值形式为yy-mm-dd
        function formatDate(d) {
            if (typeof(d) === "string") {
                d = new Date(d);
            }
            var year = d.getFullYear(),
                month = formatMonth(d.getMonth() + 1),
                date = formatMonth(d.getDate());
            return year + "-" + month + "-" + date;
        }
        //初始化月、日，个位数则前面补0
        function formatMonth(str){
            str = parseInt(str, 10);
            return (str < 10) ? ("0" + str) : str;
        }
        //设置x轴坐标
        function setXAxisRange(start, end){
            var rangeArr = [],
                addDate = start,
                startDate = new Date(start),
                year = startDate.getFullYear(),
                month = startDate.getMonth() + 1,
                date = startDate.getDate(),
                addYear = year, addMonth = month,
                split = 6;//半年间隔
            rangeArr.push(start);
            for(var i=0; i<100; i++){
                if(new Date(addDate).getTime() <= new Date(end).getTime()){
                    if(addMonth < 7){
                        addMonth = month + split;
                        addDate = addYear + "-" + formatMonth(addMonth) + "-" + formatMonth(date);
                        rangeArr.push(addDate);
                    }else{
                        addYear = addYear + 1;
                        addMonth = addMonth + split - 12;
                        addDate = addYear + "-" + formatMonth(addMonth) + "-" + formatMonth(date);
                        rangeArr.push(addDate);
                    }
                }else{
                    return rangeArr;
                }
            }
        }
    </script>
</head>
<body>
<div class="banner"></div>
<div class="showResult">
    <div class="clearfix">
        <div class="col-xs-2 result_head">项目保证成果</div>
        <div class="col-xs-2 result_item"><strong><b>150</b><small>个</small></strong><span>项目数</span></div>
        <div class="col-xs-2 result_item"><strong><b>1500</b><small>亿元</small></strong><span>总投资</span></div>
        <div class="col-xs-2 result_item"><strong><b>200</b><small>亿元</small></strong><span>年度资金计划</span></div>
        <div class="col-xs-2 result_item"><strong><b>80</b><small>亿元</small></strong><span>年度到位资金</span></div>
        <div class="col-xs-2 result_item"><strong><b>500</b><small>亿元</small></strong><span>累计到位资金</span></div>
    </div>
</div>
<div class="clearfix">
    <div class="chartBox" id="chart1"></div>
    <div class="chartBox" id="chart2"></div>
    <div class="chartBox" id="chart3"></div>
</div>
<br>
<h4 class="text-center">重点项目（10亿元以上）推进情况</h4>
<table id="programTable"></table>
</body>
</html>