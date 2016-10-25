var userId = getUrlParam("userId");
var examId = 0;
var answerId = 0;
var examinationScore = 0;
var tempGoal = 0;

$(function () {
    CKEDITOR.replace('yourAnswer', {toolbarCanCollapse: true, toolbarStartupExpanded: false, toolbar: [['Smiley']]});
    CKEDITOR.config.readOnly = true;
    $.ajax({
        type: "post",
        url: baseUrl + "/getAnswerList?token=" + token + "&userId=" + userId,
        contentType: "application/json; cahrset=utf-8",
        success: function (response) {
            if (response != null && response.length > 0) {
                $.each(response, function (i, val) {
                    var btnId = "btnAnswer" + val.examinationId;
                    var goal = val.goal;
                    var btnClass = "btn btn-size-25 radius";
                    if(goal != "" && goal != null){
                        btnClass = "btn btn-size-25 radius btn-success";
                    }
                    var html = "<input type='button' evalue='" + val.examinationId + "' class='" + btnClass + "' id='" + btnId + "' value='"+(i + 1)+"'" + "</input>";
                    $(html).appendTo("#btnNum");
                    if ((i + 1) % 6 == 0) {
                        html += "<p/>";
                    }
                    addBtnEvent(btnId, i + 1);
                });
            }
            else {
                Huimodal_alert("未获取到任何答卷信息", 1000);
            }
        }
    });

    function addBtnEvent(btnId, num) {
        $("#" + btnId).bind("click", function () {
            examId = this.getAttribute("evalue");
            tempGoal = document.getElementById("goal");
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
                    tempGoal.max = examinationScore;
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
            Huimodal_alert("请选中一道题进行评分", 1000);
            return;
        }
        $.ajax({
            type: "post",
            url: baseUrl + "/gradeSingle?answerId=" + answerId + "&goal=" + goal + "&token=" + token,
            success: function (data) {
                $("#btnAnswer" + examId).addClass("btn-success").removeClass("btn-primary");

            },
            error: function () {
                Huimodal_alert("error", 1000);
            }
        });
    });

    $("#btnBack").click(function () {
        window.location.href = baseUrl + "/member-list.html?token=" + token;
    });
});

