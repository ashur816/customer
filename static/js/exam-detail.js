var userId = getUrlParam("userId");
var examId = 0;
var answerId = 0;
var examinationScore = 0;

$(function () {
    CKEDITOR.replace('yourAnswer', {toolbarCanCollapse: true, toolbarStartupExpanded: false, toolbar: [['Smiley']]});
    $.ajax({
        type: "post",
        url: baseUrl + "/getAnswerList?token=" + token + "&userId=" + userId,
        contentType: "application/json; cahrset=utf-8",
        success: function (response) {
            if (response != null && response.length > 0) {
                $.each(response, function (i, val) {
                    var btnId = "btnAnswer" + val.examinationId;
                    var html = "<input type='button' evalue='" + val.examinationId + "' class='btn btn-primary btn-size-80' id='" + btnId + "' value='第" + (i + 1) + "题'" + "</input>";
                    if ((i + 1) % 10 == 0) {
                        html += "<p/>";
                    }
                    $(html).appendTo("#btnNum");
                    addBtnEvent(btnId, i + 1);
                });
            }
            else {
                alert("未获取到任何答卷信息");
            }
        }
    });

    function addBtnEvent(btnId, num) {
        $("#" + btnId).bind("click", function () {
            examId = this.getAttribute("evalue");
            $.ajax({
                type: "get",
                url: baseUrl + "/getUserAnswerInfo?userId=" + userId + "&examId=" + examId + "&token=" + token,
                success: function (Message) {
                    examinationScore = Message.examinationScore;
                    answerId = Message.answerId;
                    $("#question").html("第" + num + "题： " + Message.examinationQuestion + "(" + examinationScore + "分)");
                    $("#referenceAnswer").html("参考答案： " + Message.referenceAnswer);
                    $("#answerContent").removeClass("hidden");
                    $("#divGrade").removeClass("hidden");
                    $("#goal").val(Message.goal == null ? 0 : Message.goal);
                    if (Message.answerContent == null) {
                        CKEDITOR.instances.yourAnswer.setData("");
                    } else {
                        CKEDITOR.instances.yourAnswer.setData(Message.answerContent);
                    }
                },
                error: function () {
                }
            });
        });
    }

    $("#btnGrade").click(function () {
        var goal = $("#goal").val();
        if (examId == 0) {
            alert("请选中一道题进行评分");
            return;
        }
        if(parseInt(goal) > parseInt(examinationScore)){
            alert("不能超过本题最高得分");
            return;
        }
        $.ajax({
            type: "post",
            url: baseUrl + "/gradeSingle?answerId=" + answerId + "&goal=" + goal + "&token=" + token,
            success: function (data) {
                $("#btnAnswer" + examId).css("background", "green");
            },
            error: function () {
                alert("error");
            }
        });
    });

    $("#btnBack").click(function () {
        window.location.href = baseUrl + "/member-list.html?token=" + token;
    });
});

