// 数字保留位数
var fixLength = 2;
$.include(["js/bluebird.js"]);
if (!Array.prototype.indexOf) {
    Array.prototype.indexOf = function (elt /*, from*/) {
        var len = this.length >>> 0;
        var from = Number(arguments[1]) || 0;
        from = (from < 0) ? Math.ceil(from) : Math.floor(from);
        if (from < 0) from += len;
        for (; from < len; from++) {
            if (from in this && this[from] === elt) return from;
        }
        return -1;
    };
}

$.extend({
    dopen: function (options) {
        $.include([__bootstrapVersionPath + "/plugins/layer/layer.js"]);
        $.include([__bootstrapVersionPath + "/plugins/layer/theme/default/layer.css"]);
        $.include([__bootstrapVersionPath + "/plugins/layer/theme/default/layer.datanew.css"]);
        var defaultsetting = {type: 1, maxmin: true, parent: false, fixed: true, resize: true};
        options = $.extend({}, defaultsetting, options);
        if (options.parent && self.location != top.location) {
            return parent.$.dopen(options)
        } else {
            if (options.type == "5") {
                delete options.type;
                return layer.tab(options)
            } else {
                var originType = options.type;
                if (options.type == 6) {
                    var newContent = createOpenHtml(options.content);
                    options.content = newContent;
                    options.type = 1;
                    options.success = function (layero, index) {
                        newContent.removeClass("hide")
                    };
                    var endFun = options.end;
                    options.end = function () {
                        newContent.remove();
                        endFun && endFun();
                    }
                } else {
                    if (options.type == "2" && options.data) {
                        options.getDataFunc = options.getDataFunc ? options.getDataFunc : "getParentData";
                        options.success_ = options.success ? options.success.toString() : "";
                        options.success_ = options.success_.substring(options.success_.indexOf("{") + 1, options.success_.lastIndexOf("}"));
                        options.success = function (layero, index) {
                            var iframeWin = window[layero.find("iframe")[0]["name"]];
                            iframeWin[options.getDataFunc](options.data);
                            eval(options.success_)
                        }
                    }
                }
                var index = layer.open(options);
                $("#layui-layer" + index).css("display", "none");
                $("#layui-layer-shade" + index).css("display", "none");
                setTimeout(function () {
                    $("#layui-layer" + index).css("display", "block");
                    $("#layui-layer-shade" + index).css("display", "block");
                }, 80);
                // if(originType == 6){
                //     $("#layui-layer" + index +" .layui-layer-content .layui-layer-wrap").wrap('<div class="outer-container"><div class="inner-container"></div></div>');
                // }
                // $(".layui-layer-content").addClass("inner-container");
                return index;
            }
        }

        function createOpenHtml(content) {
            var $parent = $('<div class="dopenPlugs hide"></div>');
            $("body").append($parent);
            $.initPage(content, $parent);
            return $parent
        }
    }
});
$.extend({
    dalert: function (options, yes) {
        $.include([__bootstrapVersionPath + "/plugins/layer/layer.js"]);
        $.include([__bootstrapVersionPath + "/plugins/layer/theme/default/layer.css"]);
        $.include([__bootstrapVersionPath + "/plugins/layer/theme/default/layer.datanew.css"]);
        if (typeof options === "string") {
            return layer.alert(options)
        } else {
            var defaultsetting = {
                text: "",
                icon: 1
            };
            options = $.extend({}, defaultsetting, $.dalertConfig, options);
            return layer.alert(options.text, options, yes)
        }
    }
});
$.extend({
    isNumber: function (val) {
        var regPos = /^\d+(\.\d+)?$/; //非负浮点数
        var regNeg = /^(-(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*)))$/; //负浮点数
        return regPos.test(val) || regNeg.test(val);
    },
    IEVersion: function () {
        var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串
        var isIE = userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1; //判断是否IE<11浏览器
        var isEdge = userAgent.indexOf("Edge") > -1 && !isIE; //判断是否IE的Edge浏览器
        var isIE11 = userAgent.indexOf('Trident') > -1 && userAgent.indexOf("rv:11.0") > -1;
        if (isIE) {
            var reIE = new RegExp("MSIE (\\d+\\.\\d+);");
            reIE.test(userAgent);
            var fIEVersion = parseFloat(RegExp["$1"]);
            if (fIEVersion === 7) {
                return 7;
            } else if (fIEVersion === 8) {
                return 8;
            } else if (fIEVersion === 9) {
                return 9;
            } else if (fIEVersion === 10) {
                return 10;
            } else {
                return 6;//IE版本<=7
            }
        } else if (isEdge) {
            return 12;//edge
        } else if (isIE11) {
            return 11; //IE11
        } else {
            return 99999;//不是ie浏览器
        }
    },
    checkIsEmptyOrHaveEmptyProperties: function (obj, excludeFields) {
        excludeFields = excludeFields || [];
        if (Object.keys(obj).length === 0) {
            return true;
        }
        for (var key in obj) {
            if ((obj[key] === undefined || obj[key] === '') && excludeFields.indexOf(key) < 0) {
                return true;
            }
        }
        return false;
    }
});

// if ($.IEVersion() <= 10) {
// String.prototype.toFixed = function () {
//     return '';
// };
// var originParseFloat = window.parseFloat;
// var parseFloat = function (num) {
//     if ((!num && num !== 0) || num === '') {
//         return '';
//     }
//     return originParseFloat(num);
// };
// }
var InputType = {
    TextBox: function (options) {
        return $.extend({
            type: "textBox",
            name: "textBox",
            onLoaded: function ($el) {
            }
        }, options);
    },
    ComboTree: function (options) {
        return $.extend({
            panelHeight: 150,
            panelWidth: 200,
            type: 'comboTree',
            name: 'comboTree',
            multiple: false,
            // required: true,
            validText: '测试',
            localdata: {},
            rootElement: false,
            onlyLeaf: false,
            onChange: function (newValue, oldValue) {
            }
        }, options)
    },
    ComboBox: function (options) {
        return $.extend({
            type: 'comboBox',
            name: 'comboBox',
            panelHeight: 150,
            panelWidth: 200,
            pinyinSearch: true,
            // textField: "name",
            align: 'center',
            // selected: 1,
            // searchOption: false,
            // required:true,
            multiple: false,
            localdata: {}
        }, options);
    },
    DateBox: function (options) {
        return $.extend({
            type: 'dateBox',
            name: 'datebox',
            startView: 3,
            minView: 2,
            // removeIcon: false,
            // width:200,
            // inline: true,
            format: 'yyyy-mm-dd',
            pickerPosition: "bottom-left"
            // defaultValue: "2016-09-02 02:05"
        }, options);
    },
    SearchTree: function (options) {
        return $.extend({
            type: 'searchTree',
            name: 'searchTree',
            checkType: 'radio',
            required: true,
            localdata: {},
            onlyLeaf: true,
            onChange: function (newValue, oldValue) {
            },
            onAckCallback: function (nodes) {
            },
            onLoaded: function (tree) {
            }
        }, options);
    },
    DecimalTextBox: function (options) {
        return $.extend({
            title: "小数",
            type: "decimal",
            //小数位数
            decimalPlaces: 2
        }, options);
    },
    WebUpload: function (options) {
        return $.extend({
            title: "webupload",
            name: "webupload33",
            type: "webupload",
            colspan: 1
        }, options)
    }
};

function downloadFile(url) {
    // $("#_downloadLink" + id + " ._download").click();
    window.location = url;
}

var openUploadDialog = function (title, url, extendData, fileLimit, onClose) {
    var data;
    var i = $.dopen({
        title: title,
        content: '<div id="upload-form"></div>',
        area: ['600px', '210px'],
        maxmin: false,
        end: function () {//层被彻底关闭后执行的回调函数。
            if (onClose) {
                onClose(data);
            }
        }
    });
    $("#layui-layer" + i).css("top", "150px");

    $("#upload-form").dform({
        rownum: 1,
        formVertical: true,
        inputs: [
            InputType.WebUpload({
                title: "",
                name: "file",
                required: false,
                isDel: false,
                fileNumLimit: fileLimit || 1,
                fileIdKey: "content",
                server: url,
                auto: true,
                // defaultView: "detailList",
                accept: {
                    title: "选择导入文件",
                    extensions: "xlsx,xls",
                    mimeTypes: "application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
                },
                onFileQueued: function (file) {
                },
                onUploadBeforeSend: function (obj, data, headers) {
                    $.extend(data, extendData);
                },
                onUploadError: function (file, reason) {
                    $.dalert({text: "excel 上传失败", icon: 2});
                },
                onUploadAccept: function (file, response) {
                    return response.success;
                },
                onUploadSuccess: function (file, response) {
                    if (!response.success) {
                        $.dalert({text: response.msg, icon: 2});
                        layer.close(i);
                    } else {
                        data = response.content;
                        $.dalert({text: "导入成功，请点击保存按钮确认保存！", icon: 1}, function (index) {
                            layer.close(index);
                            layer.close(i);
                        });
                    }
                    // $(".layui-layer-dialog").css("top", "150px");
                },
                onIgnore: function (e, uploader) {
                }
            })
        ]
    });
};

function watchInputChange($doms, onChange) {
    var newDoms = [];
    $.each($doms, function (index, $dom) {
        var newDom = $($dom);
        newDoms.push(newDom);
        newDom.on('input propertychange change blur', function () {
            var temp = [];
            $.each(newDoms, function (index, $dom) {
                var num = parseFloat($dom.val());
                temp.push($.isNumber(num) ? num : 0);
            });
            onChange.apply({$doms: $doms}, temp);
        });
    });
}

var BaseApi = {
    isDebug: false,
    prefix: '../',
    urlGenerator: function (ignorePrefix) {
        var paths = [].splice.call(arguments, 0, arguments.length);
        var url = this.prefix;
        if (ignorePrefix === true) {
            paths.splice(0, 1);
            url = "";
        }
        $.each(paths, function (index, path) {
            if (!path) {
                return;
            }
            if (url.indexOf("?") >= 0) {
                return;
            }
            if (typeof path === 'object') {
                url += ".do?";
                var queries = [];
                for (var queryKey in path) {
                    queries.push(queryKey + "=" + path[queryKey]);
                }
                url += (queries.join("&"));
            } else {
                if (url[url.length - 1] !== '/' && url !== "") {
                    url += '/';
                }
                url += path;
            }
        });
        return url.indexOf(".do") >= 0 ? url : url.concat(".do");
    },
    doConnection: function (method, url, data, options, callback) {
        $.dajax($.extend({
            url: url,
            data: data,
            method: method,
            dataType: 'json',
            cache: false,
            success: function (res) {
                if (res.success) {
                    callback && callback(res.content);
                } else {
                    if (!API.isDebug) {
                        $.dalert({text: res.content, icon: 2});
                    } else {
                        $.dalert({
                            text: url + ',' + res.content, icon: 2
                        });
                    }
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                try {
                    var response = JSON.parse(XMLHttpRequest.responseText);
                    $.dalert({text: response.msg + "," + response.data, icon: 2});
                } catch (e) {
                    $.dalert({text: "服务器异常", icon: 2});
                }
            }
        }, options));
    },
    doGet: function (url, query, callback, options) {
        this.doConnection("GET", url, query, options || {}, callback);
    },
    doPost: function (url, data, callback, options) {
        this.doConnection("POST", url, data, options || {}, callback);
    },
    doJsonPost: function (url, data, callback, options) {
        this.doPost(url, JSON.stringify(data), callback, $.extend({
            contentType: "application/json"
        }, options));
    }
};

var API = $.extend({}, BaseApi, {
    xxFormatter: function (list) {
        var newList = [];
        $.each(list, function (index, item) {
            newList.push({
                id: item.BM,
                text: item.MC
            })
        });
        return newList;
    },
    getFjlx: function (type, callback) {
        return new Promise(function (resolve, reject) {
            API.doGet('../common/fjlx.do?sxbm=' + type, {}, function (list) {
                var newData = {};
                $.each(list, function (index, item) {
                    if (!newData[item.TYPE]) {
                        newData[item.TYPE] = [];
                    }
                    newData[item.TYPE].push({
                        id: item.BM,
                        text: item.MC
                    });
                });
                callback && callback(newData);
                resolve(newData);
            })
        });
    },
    getXx: function (lx, callback) {
        return new Promise(function (resolve, reject) {
            API.doGet("../common/xx.do", {lx: lx}, function (list) {
                callback && callback(list);
                resolve(list);
            });
        });
    },
    getZjjg: function (callback) {
        return new Promise(function (resolve, reject) {
            API.doGet("../common/zjjg.do", {}, function (list) {
                var newList = [];
                $.each(list, function (index, item) {
                    newList.push({
                        id: item.JGBM,
                        text: item.JGMC
                    })
                });
                callback && callback(newList);
                resolve(newList);
            })
        });
    },
    getBtnPermission: function (prefix, callback) {
        return new Promise(function (resolve, reject) {
            API.doGet("../common/permission/button.do", {btnGroupId: prefix}, function (list) {
                callback && callback(list);
                resolve(list);
            })
        });
    },
    submit: function (type, baIds, callback) {
        return new Promise(function (resolve, reject) {
            API.doPost('../flow/submit/' + type + '.do', JSON.stringify({ids: baIds}), function (result) {
                callback && callback(result);
                resolve(result);
            }, {contentType: 'application/json'});
        });
    },
    review: function (type, baIds, lchjs, comment, callback) {
        return new Promise(function (resolve, reject) {
            API.doPost('../flow/next/' + type + '.do', JSON.stringify({
                ids: baIds,
                lchjs: lchjs,
                comment: comment
            }), function (result) {
                callback && callback(result);
                resolve(result);
            }, {contentType: 'application/json'});
        });
    },
    back: function (type, baIds, lchjs, comment, callback) {
        return new Promise(function (resolve, reject) {
            API.doPost('../flow/back/' + type + '.do', JSON.stringify({
                ids: baIds,
                lchjs: lchjs,
                comment: comment
            }), function (result) {
                callback && callback(result);
                resolve(result);
            }, {contentType: 'application/json'});
        });
    },
    getUserType: function (callback) {
        return new Promise(function (resolve, reject) {
            API.doGet('../common/user/type.do', {}, function (result) {
                callback && callback(result);
                resolve(result);
            });
        });
    }
});

function numberShowFormatter(number) {
    number = parseFloat(number);
    if (!number || number == 'NaN') {
        return 0;
    }
    return (number / 10000).toFixed(fixLength);
}

function numberSaveFormatter(number) {
    number = parseFloat(number);
    if (!number || number == 'NaN') {
        return 0;
    }
    return (number * 10000).toFixed(2);
}

function dateShowFormatter(date) {
    date = date + "";
    if (date.length !== 8) {
        return date;
    }
    return date.substring(0, 4).concat("-").concat(date.substring(4, 6)).concat("-").concat(date.substring(6))
}

function dateSaveFormatter(date) {
    date = date.replace(/-/g, "");
    return date;
}

function getLocalTime(timestamp) {
    if (!timestamp) {
        return "-";
    }
    var d = new Date(timestamp);
    var date = (d.getFullYear()) + "-" +
        (d.getMonth() + 1) + "-" +
        (d.getDate()) + " " +
        (d.getHours()) + ":" +
        (d.getMinutes()) + ":" +
        (d.getSeconds());
    return date;
}

var CustomTable = {
    $inps: [],
    opts: null,
    data: {},
    init: function (id, opts) {
        opts = opts || {dName: {}, dType: {}};
        var _this = this;
        $("#" + id + " input").each(function () {
            var $inp = $(this);
            var dName = $inp.attr("name");
            var dType = $inp.attr("type");
            var options = $inp.data();
            if (opts.dName[dName]) {
                if (opts.dName[dName]['switchType']) {
                    dType = opts.dName[dName]['switchType'];
                    options = $.extend({}, options, opts.dName[dName]['switchOpts']);
                } else {
                    options = $.extend({}, options, opts.dName[dName]);
                }
            }
            if (opts.dType[dType]) {
                options = $.extend({}, options, opts.dType[dType]);
            }

            $inp[dType](options);
            $inp.parent().css("width", "100%");
            _this.$inps.push({name: dName, type: dType});
        });
        $("#" + id + " td").css("vertical-align", "middle");
        this.opts = opts;
        if ($.IEVersion() >= 10) {
            $("input[type=decimal]").attr("type", "number");
        }
        return $.extend({}, CustomTable, {init: null});
    },
    exec: function (name, method, data) {
        var $inp = $("input[name=" + name + "]");
        var dType = $inp.attr("boxType");
        var $plug = $inp.parents(".ztreeBox_inp").find(".form-control");
        if ((data === '' || !data) && dType === "decimal") {
            data = 0;
        }
        return $plug[dType](method, data);
    },
    getData: function () {
        var vals = {};
        var _this = this;
        $.each(this.$inps, function (index, $inp) {
            var dName = $inp.name;
            var dType = $inp.type;
            if (_this.opts.dName[dName] && _this.opts.dName[dName].getterFormatter && typeof _this.opts.dName[dName].getterFormatter === 'function') {
                vals[dName] = _this.opts.dName[dName].getterFormatter(_this.exec(dName, "getValue"));
            } else if (_this.opts.dType[dType] && _this.opts.dType[dType].getterFormatter && typeof _this.opts.dType[dType].getterFormatter === 'function') {
                vals[dName] = _this.opts.dType[dType].getterFormatter(_this.exec(dName, "getValue"));
            } else {
                vals[dName] = _this.exec(dName, "getValue");
            }
        });
        return $.extend({}, this.data, vals);
    },
    loadData: function (data) {
        var _this = this;
        $.each(this.$inps, function (index, $inp) {
            var dName = $inp.name;
            var dType = $inp.type;
            if (data[dName]) {
                if (_this.opts.dName[dName] && _this.opts.dName[dName].setterFormatter && typeof _this.opts.dName[dName].setterFormatter === 'function') {
                    _this.exec(dName, "setValue", _this.opts.dName[dName].setterFormatter(data[dName]));
                } else if (_this.opts.dType[dType] && _this.opts.dType[dType].setterFormatter && typeof _this.opts.dType[dType].setterFormatter === 'function') {
                    _this.exec(dName, "setValue", _this.opts.dType[dType].setterFormatter(data[dName]));
                } else {
                    _this.exec(dName, "setValue", data[dName]);
                }
            }
        });
        this.data = $.extend({}, this.data, data);
    },
    clear: function (names) {
        var eachData = this.$inps;
        if (names) {
            eachData = names;
        }
        var _this = this;
        $.each(eachData, function (index, item) {
            var dName = names ? item : item.name;
            _this.exec(dName, "setValue", '');
        })
    },
    setReadonly: function (names, include) {
        var _this = this;
        if (include) {
            $.each(names, function (index, name) {
                _this.exec(name, "setReadonly", true);
            });
        } else {
            $.each(this.$inps, function (index, $inp) {
                if (names.indexOf($inp.name) >= 0) {
                    return;
                }
                _this.exec($inp.name, "setReadonly", true);
            });
        }
    }
};

function getSelectedData(table, isMultiple) {
    var selectData = table.dtable("getSelections");
    if (selectData.length <= 0) {
        $.dalert({text: "请选择数据", icon: 7});
        return false;
    }
    if (selectData.length > 1 && !isMultiple) {
        $.dalert({text: "只能选择一条数据", icon: 2});
        return false;
    }
    return isMultiple ? selectData : selectData[0];
}

function getSelectedIds(table) {
    var selectData = getSelectedData(table, true);
    if (!selectData) {
        return false;
    }
    var ids = [];
    $.each(selectData, function (index, item) {
        ids.push(item.ID);
    });
    return ids;
}

function showConfirm(message, callback) {
    $.dconfirm({
        text: message, btn: ["确定", "取消"], funs: [function (index) {
            callback();
            layer.close(index);
        }, function (index) {
            layer.close(index);
        }]
    });
}

//格式化文件大小, 输出成带单位的字符串
function formatSize(size, pointLength, units) {
    var unit;

    units = units || ['B', 'K', 'M', 'G', 'TB'];

    while ((unit = units.shift()) && size > 1024) {
        size = size / 1024;
    }

    return (unit === 'B' ? size : size.toFixed(pointLength || 2)) +
        unit;
}

function pageUploadFile(type, dl) {
    uploadfile({server: '../file/uploader.do?filebstype=' + type}, function (rowData) {
        var $table = $("#uploadTable"),
            dataLen = $table.dtable("getData").length;
        $table.tableEditor("updateAll");
        for (var i = 0; i < rowData.length; i++) {
            var data = {
                FILENAME: rowData[i].name,
                FILESIZE: rowData[i].size,
                FILEID: rowData[i].fileId,
                FILEDL: dl
            };
            $table.dtable("insertRow", {index: dataLen + i, row: data});
        }
        // $table.tableEditor("initAll");
        // $table.tableEditor("updateAll");
        // $table.tableEditor("initAll");
    });
}

function deleteFjFile() {
    var uploadTable = $("#uploadTable");
    var data = uploadTable.dtable("getData");
    var selectedDatas = getSelectedData(uploadTable, true);
    if (!selectedDatas) {
        return;
    }
    showConfirm("确认删除？", function () {

        $.each(selectedDatas, function (index, selectedData) {
            API.doPost("../file/deleteById.do", {id: selectedData.FILEID || selectedData.GUID}, function (result) {
                for (var i = 0; i < data.length; i += 1) {
                    var item = data[i];
                    if (((item.GUID || item.GUID == 0) && item.GUID === selectedData.GUID) || ((item.FILEID || item.FILEID) && item.FILEID === selectedData.FILEID)) {
                        uploadTable.dtable("removeByRowIndex", i);
                        break;
                    }
                }
            });
        });
    });
}

function createToolBarBtn(name, icon, id, onClickCallback) {
    var callback = "fun" + Math.random().toString(36).substr(2);
    window[callback] = onClickCallback;
    return {
        id: id,
        "name": name,
        "classes": "btn btn-table bootstrap-table-" + icon,
        "type": "button",
        "onclick": callback + "()"
    }
}


function showDom() {
    var doms = [].splice.call(arguments, 0, arguments.length);
    $.each(doms, function (index, dom) {
        $(dom).css("display", "inline");
    });
}

function hiddenDom() {
    var doms = [].splice.call(arguments, 0, arguments.length);
    $.each(doms, function (index, dom) {
        $(dom).css("display", "none");
    });
}

function openReadCommentDialog(text) {
    $.dopen({
        title: "退回意见查看",
        content: '<div id="thyj-form"></div>',
        area: ['300px', '200px'],
        btn: ['关闭'],
        maxmin: false,
        btn1: function (index) {
            layer.close(index);
        }
    });
    $("#thyj-form").dform({
        rownum: 1,
        formVertical: true,
        inputs: [
            InputType.TextBox({
                title: "退回意见",
                multiline: true,
                name: "thyj",
                defaultValue: text,
                setReadonly: true
            })
        ]
    });
}

function openCommentDialog(result, callback, defaultValue) {
    $.dopen({
        title: "输入审核意见",
        content: '<div id="shyj-form"></div>',
        area: ['300px', '200px'],
        btn: ['提交', '取消'],
        maxmin: false,
        btn1: function (index) {
            // alert($("input[name=shyj]").prev().val());
            var comment = $("#shyj-form").dform("getData")["shyj"];
            if (result === 0 && (!comment || comment === "")) {
                $.dalert({text: "退回意见必须填写！", icon: 2});
                return;
            }
            callback && callback(comment);
            layer.close(index);
        },
        btn2: function (index) {
            layer.close(index);
        }
    });
    $("#shyj-form").dform({
        rownum: 1,
        formVertical: true,
        inputs: [
            InputType.TextBox({
                title: "审核意见",
                multiline: true,
                name: "shyj",
                defaultValue: defaultValue || (result === 1 ? '同意' : '')
            })
        ]
    });
}

function calcTablePageSize(tableHeight) {
    return Math.floor((tableHeight - 124) / 23) - 2;
}

function calcTableHeight() {
    return $("html").height() - $("h3").height() - 36;
}