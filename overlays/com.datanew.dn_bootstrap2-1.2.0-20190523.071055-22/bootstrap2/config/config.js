(function($){
    $.extend({
         //debugModal:true,//是否使用debug模式  确保每次重新获取静态文件
        //globalValidConfig:{},
        //treeConfig:{},
        //textBoxConfig:{defaultValue:'测试'},
        //dateBoxConfig:{},
        //comboBoxConfig:{},
        //comboTreeConfig:{},
        //searchTreeConfig:{},
        /*dajaxConfig:{        //dajax 默认值
           sendbefore:function(xhr){    //请求前方法
               xhr.setRequestHeader("aaaa",111);
           }

        }*/
        /*dalertConfig:{
            time: 2000 //2s 后自动关闭
        }*/
        plugMiniConfig: false, //组件样式是否最小化，包括间隙、字体等样式
        plugAsyncConfig: false //组件是否异步加载
    })
})(jQuery);