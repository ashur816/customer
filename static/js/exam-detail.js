function getvalue(value) {
    var obj = {
        "examId": value
    };
    $.ajax({
        type: "post",
        url: baseUrl + "/getAnswerList?token=" + token,
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(obj),
        success: function (Message) {
            console.dir(Message);
            $("#p4").html();
            if (Message.answer_content == null) {
                $("#p1").text(Message.examinationQuestion + "(" + Message.examinationScore + "分)");
                $("#p3").val(value);
                $("#p2").val('');
            } else {
                $("#p1").text(Message.examinationQuestion + "(" + Message.examinationScore + "分)");
                $("#p2").val(Message.answerContent);
                $("#p3").val(value);
            }
        },
        error: function () {}
    });
}

function getInfo() {
    var qid = new Array("第一题", "第二题", "第三题", "第四题", "第五题", "第六题", "第七题", "第八题", "第九题", "第十题");

    $.ajax({
        type: "post",
        url: baseUrl + "/getAnswerList?token=" + token + "&userId=" + getUrlParam("userId"),
        contentType: "application/json; cahrset=utf-8",
        dataType: "json",
        success: function (response) {
            $.each(response, function (i, val) {
//                var html = "<button type='button' class='btn btn-primary btn-default' name='content' id='" + val + "' value='" + val + "onclick='return getvalue(this.value)'>" + qid[i] + "</button><p></p><br/>";
                $("<p><button type='button' class='btn btn-primary btn-default' id='"+ val + "' value='"+ val + "' onclick='return getvalue(this.value)'>" + qid[i] + "</button></p><br/>").appendTo("#num");
            });
        }
    });
}

getInfo();

$(function () {
    $("[name='content']").on("click", "button", function () {
        $('#myModal .modal-header .modal-title').empty().text($(this).text());
        $('#myModal').modal("show");
    });
    $("#btn1").click(function () {
        var obj = {};
        var goalList = [];
        goalList.push({
            "answerId": 1,
            "goal": $("#score").val()
        });
        obj.userId = user_id;
        obj.goalList = goalList;
        console.log(obj.userId);
    });
    $("#btn").click(function () {
        window.location.href = baseUrl + "/member-list.html";
    });
});

