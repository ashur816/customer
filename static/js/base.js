var baseUrl = "http://192.168.30.224:8080";
var token = getUrlParam("token");

function checkLogin(data){
    if(data){
        window.location.href = baseUrl + "/login.html";
    }
}

//获取url参数
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}