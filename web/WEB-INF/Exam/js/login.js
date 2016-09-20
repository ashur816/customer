function Check() {
            $("#message").text('')
            if (document.form1.userName.value == "") {
                console.log("用户名不能为空");
                $('#message').attr("style", "color:red").text('用户名不能为空')
                return false;
            } else if (document.form1.password.value == "") {
                console.log("密码不能为空");
                $('#message').attr("style", "color:red").text('密码不能为空')
                return false;
            }
        };

function saveReport() {

           
            $("#message").text(''); //刷新
    
            // jquery 表单提交 
            $("#showDataForm").ajaxSubmit(
                function(Message) {
                    console.log(Message.redirectUrl);
                    // 对于表单提交成功后处理，message为提交页面saveReport.htm的返回内容 
                    if (Message.token == "n") {
                        $("#message").attr("style", "color:red").text('您已提交试卷，不能重复登陆');
                    } else if (Message.token == "p") {
                        $("#message").attr("style", "color:red").text('用户名或密码错误');
                    } else 
                    {
                       
                        window.location.href=Message.redirectUrl + "?user_id=" + Message.token;
                        $("#message").attr("style", "color:red").text('登录成功正在跳转...');
                        


                    }
                }
            );
            return false; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转 
        };