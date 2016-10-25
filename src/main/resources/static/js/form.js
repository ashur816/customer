function add_form() {
    var form = document.getElementsByClassName("valid-form");
}
//表单验证 
$(function () {
    $(".valid-form").Validform({
        tiptype: 2,
        callback: function (form) {
            form[0].submit();
            var index = parent.layer.getFrameIndex(window.name);
            parent.$('.btn-refresh').click();
            parent.layer.close(index);
        }
    });
});
