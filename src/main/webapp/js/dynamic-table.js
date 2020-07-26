var DynamicTable = {};

DynamicTable.ColumnMap = {
    "1": InputType.TextBox,
    "2": InputType.DateBox,
    "3": InputType.DecimalTextBox
};
DynamicTable.getDomId = function () {
    return "#".concat(this._id)
};

DynamicTable._api = $.extend({}, BaseApi, {
    prefix: "../alk",
    getColumnsDefine: function (bm, callback) {
        var _this = this;
        return new Promise(function (resolve, reject) {
            _this.doGet(_this.urlGenerator("columnsDefine", bm), {}, function (list) {
                callback && callback(list);
                resolve(list);
            });
        });
    },
    saveData: function (data, bm) {
        var _this = this;
        return new Promise(function (resolve, reject) {
            var postData = {
                data: data
            };
            _this.doJsonPost(_this.urlGenerator('save', bm), postData, function (result) {
                resolve(result);
            });
        })
    },
    getData: function (bm) {
        var _this = this;
        return new Promise(function (resolve, reject) {
            _this.doGet(_this.urlGenerator("list", bm), {}, function (result) {
                resolve(result);
            })
        });
    },
    deleteData: function (ids) {
        var _this = this;
        return new Promise(function (resolve, reject) {
            _this.doJsonPost(_this.urlGenerator("delete"), {ids: ids}, function (result) {
                resolve(result);
            })
        })
    },
    importData: function () {

    },
    exportData: function () {

    }
});

DynamicTable._getToolBar = function () {
    $("body").append(
        '<div id="_dynamic_table_toolbar">' +
        '   <input value="增加行" id="_add_btn" class="bootstrap-table-add" type="button" />' +
        '   <input value="删除行" id="_delete_btn" class="bootstrap-table-delete" type="button" />' +
        '   <input value="保存" id="_save_btn" class="bootstrap-table-save" type="button" />' +
        '   <input value="导入" id="_import_btn" class="bootstrap-table-upload" type="button" />' +
        '   <a href="' + BaseApi.urlGenerator("alk/excel/export", this._bm, $.extend({}, this._extraData, {getValue: true})) + '"><input value="导出" id="_export_btn" class="bootstrap-table-download" type="button" /><a/>' +
        '   <a href="' + BaseApi.urlGenerator("alk/excel/export", this._bm, $.extend({}, this._extraData, {getValue: false})) + '"><input value="模板下载" id="_export_btn" class="bootstrap-table-download" type="button" /><a/>' +
        '</div>'
    );
    return '#_dynamic_table_toolbar';
};
DynamicTable._setToolBarBtnClickEvent = function () {
    var _this = this;
    $("#_add_btn").on('click', function () {
        _this._addBtnCallback();
    });
    $("#_delete_btn").on('click', function () {
        _this._deleteBtnCallback();
    });
    $("#_save_btn").on('click', function () {
        _this._saveBtnCallback();
    });
    $("#_import_btn").on('click', function () {
        _this._importBtnCallback();
    });
    // $("#_export_btn").on('click', function () {
    //     _this._exportBtnCallback();
    // });
};

DynamicTable._addBtnCallback = function () {
    var dataLen = this.$table.dtable("getData").length;
    this.$table.dtable("updateAll");
    this.$table.dtable("insertRow", {index: dataLen, row: {}});
    this.$table.dtable("initAll");
};


DynamicTable._deleteBtnCallback = function () {
    var ids = getSelectedIds(this.$table);
    if (!ids) {
        return;
    }
    var _this = this;

    function realDelete() {
        var selectedDatas = getSelectedData(_this.$table, true);
        var data = _this.$table.dtable("getData");
        _this.$table.dtable("updateAll");
        $.each(selectedDatas, function (index, item) {
            var dataIndex = data.indexOf(item);
            _this.$table.dtable("removeByRowIndex", dataIndex);
        });
        _this.$table.dtable("initAll");
    }

    showConfirm("确认删除？", function () {
        if (_this._options._isPreview) {
            realDelete();
            $.dalert({text: '删除成功', icon: 1});
        } else {
            _this._api.deleteData(ids).then(function (result) {
                realDelete();
                $.dalert({text: result, icon: 1});
            })
        }
    });
};
DynamicTable._saveBtnCallback = function () {
    this.$table.dtable("updateAll");
    this.$table.dtable("initAll");
    if (this._options._isPreview) {
        $.dalert({text: "保存成功！", icon: 1});
    } else {
        var tableList = this.$table.dtable("getData");
        $.each(tableList, function (index, item) {
            delete item.tableAdd;
            delete item.tableEdited;
        });
        this._api.saveData(tableList, this._bm).then(function (value) {
            $.dalert({text: value, icon: 1});
        });
    }
};
DynamicTable._importBtnCallback = function () {
    var _this = this;
    openUploadDialog("导入文件",
        BaseApi.urlGenerator("alk/excel/parse", this._bm), this._extraData, 1,
        function (list) {
            _this.$table.dtable("prepend", list);
            _this.$table.dtable("initAll");
        });
};
DynamicTable._exportBtnCallback = function () {
    var exportBtn = $("#_export_btn");
    exportBtn.parent().attr("href", BaseApi.urlGenerator("alk/excel/export", this._bm, this._extraData));
};


DynamicTable._generateColumnsJson = function (columns) {
    var columnsJson = [{checkbox: true}, {
        field: 'INDEX',
        title: '序号',
        width: 50,
        align: 'center',
        formatter: function (value, row, index) {
            return index + 1;
        }
    }];
    $.each(columns, function (index, column) {
        if (column["ZT"] == 2) {
            return;
        }
        var options = {};
        if (column["ZDLX"] == 1) {
            var maxLength = column["ZDGS"] - 0;
            options["onInputChange"] = function (newVal, oldVal) {
                if (newVal.length > maxLength) {
                    $(this)['textBox']("setValue", newVal.substring(0, maxLength));
                    $.dalert({text: "此处文本最大长度不能超过" + maxLength, icon: 2});
                }
            }
        }
        if (column["ZDLX"] == 2) {
            if (column["ZDGS"].indexOf("HH:mm:ss") >= 0) {
                column["ZDGS"] = column["ZDGS"].replace("mm", "ii");
                options["minView"] = 0;
            }
            options["format"] = column["ZDGS"];
        }
        if (column["ZDLX"] == 3) {
            options["decimalPlaces"] = column["ZDGS"];
        }
        var columnJson = {
            field: column.ZDM,
            title: column.XSMC,
            align: 'center',
            editor: DynamicTable.ColumnMap[column.ZDLX](options)
        };
        columnsJson.push(columnJson);
    });
    return columnsJson;
};
DynamicTable.loadData = function () {
    var _this = this;
    this._api.getData(this._bm).then(function (list) {
        _this.$table.dtable("load", list);
        _this.$table.dtable("initAll");
    });
};
DynamicTable.init = function (id, bm, options) {
    this._id = id;
    this._bm = bm;
    this._options = $.extend({isPreview: false, tableHeight: $("html").height() - $("h3").height() - 30}, options);
    var _this = this;
    this._api.getColumnsDefine(bm).then(function (columns) {
        _this._columns = columns;
        var tableHeight = _this._options.tableHeight;
        _this._options = {
            toolbar: _this._getToolBar(),
            columns: _this._generateColumnsJson(columns),
            height: tableHeight,
            pageNumber: 1,//起始页
            pageSize: calcTablePageSize(tableHeight),//页面大小
            pagination: true,
            paginationHAlign: 'left',//分页按钮位置  left/right
            sidePagination: 'client',
            clickToSelect: true,//点击选中
            showRefresh: false
            // onPageChange: function () {
            //     setTimeout(function () {
            //         _this.$table.dtable("initAll");
            //     }, 0);
            // }
        };
        _this.$table = $(_this.getDomId()).dtable(_this._options);
        _this._setToolBarBtnClickEvent();
        _this._readyCallback();
    });
    return this;
};
DynamicTable.onReady = function (callback) {
    this._readyCallback = callback;
    return this;
};

DynamicTable.reloadColumns = function (bm) {
    this._bm = bm;
    var _this = this;
    this._api.getColumnsDefine(bm).then(function (columns) {
        _this._columns = columns;
        _this._options = $.extend({}, _this._options, {
            columns: _this._generateColumnsJson(columns),
            toolBar: _this._getToolBar()
        });
        _this.$table.dtable("load", []);
        _this.$table.dtable("refreshOptions", _this._options);
        _this._setToolBarBtnClickEvent();
        _this._readyCallback();
    });
    return this;
};


DynamicTable.setExtraData = function (data) {
    this._extraData = data;
};





