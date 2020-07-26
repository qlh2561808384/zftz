/**
 * 上传文件
 * type 文件业务类型
 * param 上传组件配置
 * callback(list)   回调函数,参数为上传的文件的信息
 *
 */

function uploadfile(param, callback) {
    var shouldSave = false;
    var i = $.dopen({
        title: "上传附件",
        area: ["50%", "50%"],
        content: '<form id="uploadForm"></form>',
        btn: ["保存", "取消"],
        maxmin: false,
        btn1: function (index) {
            if(shouldSave){
                var fileData = $("#uploadForm_uploadfile").webupload("getData");
                layer.close(index);
                callback(fileData);
            }
        }
    });
    var saveBtn = $("#layui-layer" + i + " .layui-layer-btn0");
    saveBtn.css("background", "#999")
        .css("border-color", "#999")
        .css("cursor", "not-allowed");
    var paramdefault = {
        id: "uploadForm_uploadfile",
        title: "", name: "", type: "webupload", colspan: 1,
        isDel: true,
        isDownload: true,
        previewTarget: "parent",
        //delUrl: 'file/deleteById.do',
        //delUrl: '../plugins/webuploader/webuploader.json',
        dataUrl: 'file/getFilesByids.do',
        auto: true,
        fileSingleSizeLimit: 104857600,
//        fileSizeLimit:1024000000,
        pick: {multiple: true},
        downloadUrl: 'file/downloadByid.do',/*,
         accept:{
         title : "Image", //{String} 文字描述
         extensions: "gif,jpg,jpeg,bmp,png", //{String} 允许的文件后缀，不带点，多个用逗号分割
         mimeTypes: "image/!*" //{String} 多个用逗号分割
         }*/
        onFileQueued: function (file) {
            saveBtn.css("background", "#999")
                .css("border-color", "#999")
                .css("cursor", "not-allowed");
            shouldSave = false;
        },
        onUploadSuccess: function (file, response) {
            saveBtn.css("background", "#1e9fff")
                .css("border-color", "#1e9fff")
                .css("cursor", "pointer");
            shouldSave = true;
        }
    };
    var paramnew = $.extend({}, paramdefault, param);

    $("#uploadForm").dform({
        rownum: 1,
        // labelwidth: "100px",
        formVertical: true,
        inputs: [
            paramnew
        ]
    });
}

/**
 * 上传图片
 * type 文件业务类型
 * param 上传组件配置
 * callback(list)   回调函数,参数为上传的文件的信息
 *
 */

function uploadfileJpg(param, callback) {
    $.dopen({
        title: "上传照片",
        area: ["600px", "300px"],
        content: '<form id="uploadFormJpg"></form>',
        btn: ["保存", "取消"],
        btn1: function (index) {
            var fileData = $("#uploadForm_uploadfileJpg").webupload("getData");
            layer.close(index);
            callback(fileData);
        }
    });
    var paramdefault = {
        id: "uploadForm_uploadfileJpg",
        title: "选择文件", name: "", type: "webupload", colspan: 2,
        isDel: true,
        // fileSingleSizeLimit:6000,
        isDownload: true,
        height: 60,
        previewTarget: "parent",
        //delUrl: 'file/deleteById.do',
        //delUrl: '../plugins/webuploader/webuploader.json',
        dataUrl: 'file/getFilesByids.do',
        auto: true,
        pick: {multiple: true},
        accept: {extensions: "jpg,png"},
        // defaultView: "detaiList",
        fileNumLimit: 1,
        // fileSizeLimit: 60000,
        downloadUrl: 'file/downloadByid.do'/*,
         accept:{
         title : "Image", //{String} 文字描述
         extensions: "gif,jpg,jpeg,bmp,png", //{String} 允许的文件后缀，不带点，多个用逗号分割
         mimeTypes: "image/!*" //{String} 多个用逗号分割
         }*/
    };
    var paramnew = $.extend({}, paramdefault, param);

    $("#uploadFormJpg").dform({
        rownum: 2,
        labelwidth: "100px",
        inputs: [
            paramnew
        ]
    })

}