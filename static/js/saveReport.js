function saveReport() {
    $("#nmessage").text('');
    $("#pmessage").text('');
    $("#fmessage").text(''); //刷新
    // jquery 表单提交 
    $("#showDataForm").ajaxSubmit(
        function (Message) {
            console.log(Message);
            // 对于表单提交成功后处理，message为提交页面saveReport.htm的返回内容 
            if (Message.id == "u") {
                $("#umessage").attr("style", "color:red").text(Message.umessage);
                setTimeout("window.location.href='login.html'", 3000);
            } else if (Message.id == "n")
                $("#nmessage").attr("style", "color:red").text(Message.nmessage);
            else if (Message.id == "p")
                $("#pmessage").attr("style", "color:red").text(Message.pmessage);
            else if (Message.id == "f")
                $("#fmessage").attr("style", "color:red").text(Message.fmessage);

        }
    );
    return false; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转 
}
