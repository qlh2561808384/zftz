function openProj($el,callback,xmid){
    $el.on("click", function(){
        $.dopen({
            title: "请选择工程",
            area: ["500px", "300px"],
            content: "<div id='proj_open_div'></div>",
            btn: ["新增工程","确认", "取消"],
            btn1: function(index){
                openProj_add(callback,index);
            },
            btn2: function(index){
                var nodes = $("#proj_open_div").tree("getCheckedNodes");
                if(nodes.length!=1){
                    $.dalert({text: "请先选择一条项目", icon: 2});
                    return;
                }
                callback(nodes[0]);
                layer.close(index);
            }
        });
        $("#proj_open_div").tree({
            id:'searchtree1',
            opensearch: true,
            url : '../../pro/getGcfymctree.do?xmid='+xmid,
            checktype: 'radio'
        })

    })


}

function openProj_add(callback,pindex){
    var dopenJson = [
        {
            "plug": [
                {
                    "plug": [
                        {
                            "plug": [
                                {
                                    "dtype": "dform",
                                    "id": "openProj_add",
                                    "classes": "tableForm",
                                    "rownum": 1,
                                    "labelwidth": "100px",
                                    "labelAlign": "center",
                                    "inputs": [
                                        // {title: "工程或费用id", name: "ID", type: "hidden"},
                                        {title: "工程或费用名称",required:true, name: "GCMC", type: "textBox"}
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
    $.dopen({
        type: 6,
        title: "添加工程",
        area: ["50%", "25%"],
        content: dopenJson,
        btn: ["保存","关闭"],
        btn1:function(index){
            var formdata = $("#openProj_add").dform("getData");
            var da = {};
            da.id = 0;
            da.name = formdata.GCMC;
            da.isnew = '1';
            callback(da);
            layer.close(index);
            layer.close(pindex);
            // $.dajax({
            //     url: "../../pro/saveGsmx.do",
            //     async:false,
            //     type:"post",
            //     data: {sdata:JSON.stringify(formdata)},
            //     success: function (data) {
            //         if (data.success) {

            //
            //             $.dalert({text: "保存成功", icon: 1});
            //             var da = {};
            //             da.id = data.content.id;
            //             da.name = data.content.xmmc;
            //             callback(da);
            //         } else {
            //             $.dalert({text: "保存失败", icon: 2});
            //         }
            //     }
            // })
        },
        btn2:function(index){
            layer.close(index);

        },
    });


}