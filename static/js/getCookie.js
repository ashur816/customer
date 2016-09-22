function getCookie(c_name) {
    var strCookie = document.cookie;
    var arrCookie = strCookie.split(";");
    for (var i = 0; i < arrCookie.length; i++) {
        var arr = arrCookie[i].split("=");
        if (arr[0] == c_name)
            return arr[1];
    }
}

function setCookie(c_name, value, expireHours) {
    var cookiesString = c_name + "=" + escape(value);
    if (expireHours > 0) {
        var exdate = new Date();
        exdate.setTime(exdate.getTime() + expireHours * 3600 * 1000);
        cookieString = cookieString + ";expires=" + exdate.toGMtString() + ";path/";
    }
    document.cookie = cookieString;
}

function checkCookie() {
    userName = getCookie('userName')
    if (userName != null && userName != "") {
        alert('Welcome again ' + userName + '!')
    } else {
        userName = prompt('Please enter your Name:', "")
        if (userName != null && userName != "") {
            setCookie('userName', userName, 365)
        }
    }
}
