function getvalue(value) {
    var obj = {
        "user_id": Request['user_id'],
        "exam_id": value
    };
    $.ajax({
        type: "post",
        url: "http://192.168.30.224:8791/getExamAndAnswer",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(obj),
        success: function (Message) {
            console.dir(Message);
            if (Message.answer_content == "") {
                $("#p1").text(Message.examination_id + "、" + Message.examination_question + "(" + Message.examination_score + "分)");
                $("#p2").text('');
            } else {
                $("#p1").text(Message.examination_id + "、" + Message.examination_question + "(" + Message.examination_score + "分)");
                $("#p2").text(Message.answer_content);
            }
        },
        error: function () {}
    });
}
