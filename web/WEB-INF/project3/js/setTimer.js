var x = 30,
    interval;
window.onload = function () {
    var d = new Date("1111/1/1,0:" + x + ":0");
    interval = setInterval(function () {
        var m = d.getMinutes();
        var s = d.getSeconds();
        m = m < 10 ? "0" + m : m;
        s = s < 10 ? "0" + s : s;
        btn.innerHTML = m + ":" + s;
        if (m == 0 && s == 0) {
            clearInterval(interval);
            $("#p5").attr('disabled', 'true');
            alert("时间到您已不能提交即将退出");
            window.location.href = "index.html";
            return;
        }
        d.setSeconds(s - 1);
    }, 1000);
}
