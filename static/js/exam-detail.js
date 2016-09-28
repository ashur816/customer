var userId = getUrlParam("userId");

$(function () {
    getInfo();
    function getInfo() {
        $.ajax({
            type: "post",
            url: baseUrl + "/getAnswerList?token=" + token + "&userId=" + userId,
            contentType: "application/json; cahrset=utf-8",
            success: function (response) {
                if (response != null && response.length > 0) {
                    $.each(response, function (i, val) {
                        var btnId = "btnAnswer" + val.examinationId;
                        var html = "<input type='button' evalue='" + val.examinationId + "' class='btn btn-default btn-size-80' id='" + btnId + "' value='第" + (i + 1) + "题'" + "</input>";
                        if ((i + 1) % 6 == 0) {
                            html += "<p/>";
                        }
                        $(html).appendTo("#content");
                        addBtnEvent(btnId);
                    });
                }
                else {
                    alert("未获取到任何答卷信息");
                }
            }
        });
    }

    function addBtnEvent(btnId) {
        $("#" + btnId).bind("click", function () {
            $('#myModal .modal-header .modal-title').empty().text($(this).val());
            $('#myModal').modal("show");
            $.ajax({
                type: "get",
                url: baseUrl + "/getUserAnswerInfo?userId=" + userId + "&examId=" + this.getAttribute("evalue") + "&token=" + token,
                dataType: "json",
                contentType: "application/json; charset=utf-8",
                data: null,
                success: function (Message) {
                    console.log(Message);
                    $("#question").text(Message.examinationQuestion + "(" + Message.examinationScore + "分)");
                    $("#userAnswer").val(Message.answerContent);
                    $("#referAnswer").text(Message.referenceAnswer);
                    $("#answerId").val(Message.answerId);
                },
                error: function () {
                }
            });
        });
    }

    $("#btnGrade").click(function () {
        $.ajax({
            type: "post",
            url: baseUrl + "/gradeSingle?answerId=" + $("#answerId").val() + "&goal=" + $("#score").val() + "&token=" + token,
            success: function (data) {
                alert(data.message);
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

