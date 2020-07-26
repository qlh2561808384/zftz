function uploadFile(fileType,id){
    uploadfile({server: 'file/uploader.do?filebstype=' + fileType}, function (rowData) {
        var $table = $(id),
            dataLen = $table.dtable("getData").length;
        $table.tableEditor("updateAll");
        for (var i = 0; i < rowData.length; i++) {
            var data = {
                FILENAME: rowData[i].name,
                FILESIZE: rowData[i].size,
                FILEID: rowData[i].fileId
            };
            /*alert(rowData[i].name);
            alert(rowData[i].size);
            alert(rowData[i].fileId);*/
            $table.dtable("insertRow", {index: dataLen + i, row: data});
        }
        $table.tableEditor("initAll");
    });
}