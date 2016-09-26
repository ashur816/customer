function getInfo() {
    var qid = new Array("第一题", "第二题", "第三题", "第四题", "第五题", "第六题", "第七题", "第八题", "第九题", "第十题");
    
    $.ajax({
        type: "post",
        url: baseUrl + "/getAnswerList?token=" + token,
        contentType: "application/json; cahrset=utf-8",
        dataType: "text",
        success: function (response) {
            $.each(JSON.parse(response), function (i, val) {
                $("<button type='button' class='btn btn-primary btn-default' name='content' id='" + val + "' value='" + val + "onclick='return getvalue(this.value)'>" + qid[i] + "</button><p></p><br/>").apendTo("#content");
            });
        }
    });
}
getInfo(); 