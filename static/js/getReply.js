function getReply() {
    $.get("url", function (data, status) {
        if (data != "") {
            ("#p1").append(); //获取题目
            ("#p2").append(); //获取答案 
            ("#p3").append(); //获取参考答案
        } else {

        }
    });
}
