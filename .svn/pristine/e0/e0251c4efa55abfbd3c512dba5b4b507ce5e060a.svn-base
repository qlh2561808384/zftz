function delFile(id){
    var $table = $(id);
    var selectData = $(id).dtable("getSelections");
    if(selectData.length==0){
        $.dalert({text:'请选择您要删除的附件',icon:7});
        return;
    }else{
        layer.confirm('确定删除吗？', {
            btn: ['确定', '取消'] //按钮
        }, function () {
            $.dajax({
                type: 'post',
                url: 'file/deleteById.do',
                data: {
                    id: selectData[0].FILEID
                },
                dataType: 'json',
                success: function (data) {
                    var tableData = $table.dtable("getData");
                    $table.tableEditor("updateAll");
                    $.each(selectData, function (i) {
                        var dataIndex = tableData.indexOf(selectData[i]);
                        $table.dtable("removeByRowIndex", dataIndex);
                    });
                    $table.tableEditor("updateAll");
                    $(id).dtable('refresh');
                    /*var dataLen = $table.dtable("getData").length;

                    $table.dtable("refreshOptions", {height: 250 + 30 * dataLen});
                    $table.tableEditor("initAll");*/
                    layer.msg('删除成功', {icon: 1});
                }
            });
        });
    }
}