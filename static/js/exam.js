var x = 40, interval;
var isBegin = false;
$(document).ready(function () {
    <!--    计时器设定,初始化题目信息和题的数目-->
    $("#btnBegin").click(function () {
        //是否已经开始答题
        if (!isBegin) {
            var d = new Date("1111/1/1,0:" + x + ":0");
            interval = setInterval(function () {
                var m = d.getMinutes();
                var s = d.getSeconds();
                m = m < 10 ? "0" + m : m;
                s = s < 10 ? "0" + s : s;
                clock.innerHTML = m + ":" + s;
                if (m == 0 && s == 0) {
                    clearInterval(interval);
                    alert("时间到已强制提交，答题结束");
                    $("#btnSubmit").click();
                    window.location.href = baseUrl + "/login.html";
                    return;
                }
                d.setSeconds(s - 1);
            }, 1000);
            isBegin = true;

            $.ajax({
                type: "post",
                url: baseUrl + "/getExamIdList?token=" + token,
                contentType: "application/json; charset=utf-8",
                dataType: "text",
                success: function (response) {
                    $.each(JSON.parse(response), function (i, val) {
                        var html = "<button type='button' class='btn btn-primary btn-size-80' id='" + val + "' value='" + val + "' onclick='return getValue(" + (i + 1) + ",this.value)'>第" + (i + 1) + "题</button>";
                        if ((i + 1) % 10 == 0) {
                            html += "<p/>";
                        }
                        $(html).appendTo("#num");
                    });
                },
                error: function () {
                }
            });
        }
    });

    <!--提交试题的答案并保存-->
    $("#btnSave").click(function () {
        var obj = {
            "examinationId": $("#btnSave").val(),
            "answerContent": CKEDITOR.instances.yourAnswer.getData()
        };
        if (obj.answerContent != "" && obj.examinationId != "") {
            $.ajax({
                type: "post",
                url: baseUrl + "/insertAnswer?token=" + token,
                contentType: "application/json; charset=utf-8",
                dataType: "text",
                data: JSON.stringify(obj),
                success: function (response) {
                    $("#" + obj.examinationId).css("background", "green");
                },
                error: function () {
                }
            });
        } else if (obj.answerContent == "" || obj.answerContent == null) {
            alert("答案不能为空");
        } else if (obj.examinationId == "" || obj.examinationId == null) {
            alert("请选择题目再提交保存");
        }
    });

    <!--   提交用户表明已做过试题 -->
    $("#btnSubmit").click(function () {
        if (confirm("请检查您是否已做完所有题目？您确认要交卷吗？")) {
            $.ajax({
                type: "post",
                url: baseUrl + "/commit?token=" + token,
                contentType: "application/json; charset=utf-8",
                dataType: "text",
                success: function (response) {
                    console.log(response);
                    alert(response);
                    window.location.href = baseUrl + "/login.html";
                },
                error: function () {
                }
            });
        }
    });
});

<!--点击题目得到试题信息-->
function getValue(num, value) {
    $.ajax({
        type: "post",
        url: baseUrl + "/getExamAndAnswer?token=" + token + "&examId=" + value,
        success: function (Message) {
            if (Message.answerContent == null) {
                $("#question").html("第" + num + "题： " + Message.examinationQuestion);
                $("#btnSave").val(value);
                CKEDITOR.instances.yourAnswer.setData("");
            } else {
                $("#question").html("第" + num + "题： " + Message.examinationQuestion);
                CKEDITOR.instances.yourAnswer.setData(Message.answerContent);
                $("#btnSave").val(value);
            }
        },
        error: function () {
        }
    });
};

window.onload = function () {
    $.ajax({
        type: "get",
        url: baseUrl + "/getUserById?token=" + token,
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function (Message) {
            console.dir(Message);
            $("#user").text(Message.fullname);
            $("#school").text(Message.graduateInstitution);
            $("#major").text(Message.major);
        },
        error: function () {
            alert("您是非法用户请重新登录进入");
            window.location.href = baseUrl + "/login.html";
        }
    });
}
