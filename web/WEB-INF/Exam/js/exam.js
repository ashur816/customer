 <!--提交试题的答案并保存-->
     
     $(document).ready(function() {
            $("#p3").click(function() {
                var obj = {
                    
                    "examinationId": $("#p3").val(),
                    "answerContent": $("#p2").val()
                };
                console.log(obj.answer_content);
                console.log(obj.examination_id);
                if (obj.answerContent != "" && obj.examId != "") {
                    $.ajax({
                        type: "post",
                        url: "http://192.168.30.224:8791/insertAnswer" + token + "=" + user_id,
                        contentType: "application/json; charset=utf-8",
                        dataType: "text",
                        data: JSON.stringify(obj),
                        success: function(response) {
                            console.log(response);
                            alert(response);
                            $("#" + obj.examinationId).css("background","green");

                        },
                        error: function() {}
                    });
                } else if (obj.answerContent == "") {
                    console.log("答案不能为空");
                    alert("答案不能为空");
                } else if (obj.examId == "") {
                    console.log("请选择题目再提交");
                    alert("请选择题目再提交保存");
                }

            });
         
});
       

    

    <!--点击题目得到试题信息-->
   
        function getvalue(value) {
            var obj = {
                
                "examId": value
            };
            $.ajax({
                type: "post",
                url: "http://192.168.30.224:8791/getExamAndAnswer" + token + "=" + user_id,
                dataType: "json",
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(obj),
                success: function(Message) {
                    console.dir(Message);
                    //$("#p4").html();
                    
                    if (Message.answerContent == null) {
                       //$("#title").text($(this).text());
                       $("#p1").text("$(this).text()" + Message.examinationQuestion);

                        $("#p3").val(value);
                        $("#p2").val('');
                    } else {
                        $("#p1").text(Message.examinationQuestion);
                        $("#p2").val(Message.answerContent);
                        $("#p3").val(value);
                    }
                },
                error: function() {}
            });
        };

// $(".btn btn-primary btn-default").click(function(){
//    $('#title').empty().text($("this").text());
// });
// 
    <!--获取页面传过来的用户id-->
    
        function GetUrlRequest() {
            var url = location.search; //获取url中"?"符后的字串
            var theRequest = new Object();
            if (url.indexOf("?") != -1) {
                var str = url.substr(1);
                if (str.indexOf("&") != -1) {
                    strs = str.split("&");
                    for (var i = 0; i < strs.length; i++) {
                        theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
                    }
                } else {
                    theRequest[str.split("=")[0]] = unescape(str.split("=")[1]);
                }
            }
            return theRequest;
        }
        var Request = new Object();
        Request = GetUrlRequest();
        var user_id = Request['user_id'];
        var token = '?token';
        console.log(user_id);

    
    <!--    计时器设定,初始化题目信息和题的数目-->
    var qid=new Array("第一题","第二题","第三题","第四题","第五题","第六题","第七题","第八题","第九题","第十题");
console.dir(qid);
        var x = 40,
            interval;
       $(document).ready(function() {
            $("#begin").click(function() {
            var d = new Date("1111/1/1,0:" + x + ":0");
            interval = setInterval(function() {
                var m = d.getMinutes();
                var s = d.getSeconds();
                m = m < 10 ? "0" + m : m;
                s = s < 10 ? "0" + s : s;
                btn.innerHTML = m + ":" + s;
                if (m == 0 && s == 0) {
                    clearInterval(interval);
                    alert("时间到已强制提交，答题结束");
                     $("#p5").click();
                    window.location.href = "index.html";
                    return;
                }
                d.setSeconds(s - 1);
            }, 1000); 
                  $.ajax({
                    type: "post",
                    url: "http://192.168.30.224:8791/getExamIdList" + token + "=" + user_id,
                    contentType: "application/json; charset=utf-8",
                    dataType: "text",
                    success: function(response) {
                        console.dir(response);
               $.each(JSON.parse(response),function(i,val) {
                   console.dir(val);
           $("<p><button type='button' class='btn btn-primary btn-default' id='"+ val + "' value='"+ val + "' onclick='return getvalue(this.value)'>" + qid[i] + "</button></p><br/>").appendTo("#num");
        });
                        
                       
                        //$("button").val(response[i]);
                       console.dir($('button').val());
                    },
                    error: function() {}
                }); 
});
                          });
    
    <!--   提交用户表明已做过试题 -->
    
        $(document).ready(function() {
            $("#p5").click(function() {
                if (confirm("请检查您是否已做完所有题目？您确认要交卷吗？")){
                
                $.ajax({
                    type: "post",
                    url: "http://192.168.30.224:8791/commit" + token + "=" + user_id,
                    contentType: "application/json; charset=utf-8",
                    dataType: "text",
                    success: function(response) {
                        console.log(response);
                        alert(response);
                        window.location.href = "index.html";
                    },
                    error: function() {}
                });
            }
            });
        });

