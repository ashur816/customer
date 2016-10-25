var baseUrl = window.location.origin;
var token = getUrlParam("token");

//获取url参数
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}

//将列数据写入form
function setDataToForm(formId, formData) {
    if (formData) {
        var nameList = $("#" + formId + " [name]");
        $.each(nameList, function (index) {
            var id = "#" + formId + " [name='" + this.name + "']";
            var tmpObj = $(id);
            var tagName = tmpObj[0].tagName;
            if (tagName == "SELECT") {
                tmpObj[0].value = formData[this.name];
            }
            else if (tagName == "INPUT") {
                tmpObj.val(formData[this.name]);
            }
            else {

            }
        });
    }
    return formData;
}