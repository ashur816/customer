var min = 40,sec = 0, interval;
var isBegin = false;
$(document).ready(function () {
    <!--    计时器设定,初始化题目信息和题的数目-->
    $("#btnBegin").click(function () {
        $("btnBegin").attr('disabled',true);
        //是否已经开始答题
        if (!isBegin) {
            isBegin = true;

            $.ajax({
                type: "post",
                url: baseUrl + "/getExamIdList?token=" + token,
                contentType: "application/json; charset=utf-8",
                dataType: "text",
                success: function (response) {
                    var obj = JSON.parse(response);
                    var startTime = obj["startTime"];
                    var curTime = new Date();
                    var leave = curTime.getTime() - startTime;

                    //计算出相差天数
                    var days = Math.floor(leave/(24*3600*1000));
                    //计算出小时数
                    var leave1 = leave%(24*3600*1000);   //计算天数后剩余的毫秒数
                    var hours = Math.floor(leave1/(3600*1000));
                    //计算出分钟数
                    var leave2 = leave1%(3600*1000);      //计算小时数后剩余的毫秒数
                    var minutes = Math.floor(leave2/(60*1000));
                    //计算相差秒数
                    var leave3 = leave2%(60*1000);     //计算分钟数后剩余的毫秒数
                    var seconds = Math.round(leave3/1000);

                    sec = seconds > 0 ? 60 - seconds : 0;
                    min = seconds > 0 ? (min - 1 - minutes) : min - minutes;
                    setClock();

                    $.each(obj["examList"], function (i, val) {
                        var html = "<button type='button' class='btn btn-size-25 radius' id='" + val + "' value='" + val + "' onclick='return getValue(" + (i + 1) + ",this.value)'>" + (i + 1) + "</button>";
                        if ((i + 1) % 6 == 0) {
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

    function setClock(){
        var d = new Date("1111/1/1,0:" + min + ":" + sec);
        interval = setInterval(function () {
            var m = d.getMinutes();
            var s = d.getSeconds();
            m = m < 10 ? "0" + m : m;
            s = s < 10 ? "0" + s : s;
            clock.innerHTML = m + ":" + s;
            if (m == 0 && s == 0) {
                clearInterval(interval);
                Huimodal_alert("时间到已强制提交,答题结束", 1000);
                $("#btnSubmit").click();
                window.location.href = baseUrl + "/login.html";
                return;
            }
            d.setSeconds(s - 1);
        }, 1000);
    }

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
                    $("#" + obj.examinationId).addClass("btn-success");
                    Huimodal_alert("保存成功，请完成下一题", 1000);
                },
                error: function () {
                }
            });
        } else if (obj.answerContent == "" || obj.answerContent == null) {
            Huimodal_alert("答案不能为空", 1000);
        } else if (obj.examinationId == "" || obj.examinationId == null) {
            Huimodal_alert("请选择题目再提交保存", 1000);
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
                    Huimodal_alert(response, 1000);
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
    showCKEDITOR(true);
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
}

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
            Huimodal_alert("您是非法用户请重新登录进入", 1000);
            window.location.href = baseUrl + "/login.html";
        }
    });
    showCKEDITOR(false);
};

function showCKEDITOR(flag){
    if(flag){
        $("#yourAnswer").css("visibility", "");
        $("#btnSave").css("visibility", "");
    }
    else{
        $("#yourAnswer").css("visibility", "hidden");
        $("#btnSave").css("visibility", "hidden");
    }
}
