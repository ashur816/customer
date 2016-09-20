function getvalue(value) {
    var obj = {
        "examId": value
    };
    $.ajax({
        type: "post",
        url: "http://192.168.30.224:8791/getAnswerList?token=",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(obj),
        success: function (Message) {
            console.dir(Message);
            $("#p4").html();
            if (Message.answer_content == null) {
                $("#p1").text(Message.examinationId + "、" + Message.examinationQuestion + "(" + Message.examinationScore + "分)");
                $("#p3").val(value);
                $("#p2").val('');
            } else {
                $("#p1").text(Message.examinationId + "、" + Message.examinationQuestion + "(" + Message.examinationScore + "分)");
                $("#p2").val(Message.answerContent);
                $("#p3").val(value);
            }
        },
        error: function () {}
    });
}
