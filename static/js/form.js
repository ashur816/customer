 function add_form() {
     var form = document.getElementById("form-member-add");
 }
 //表单验证 
 $(function () {
     $("#form-member-add").Validform({
         tiptype: 2,
         callback: function (form) {
             form[0].submit();
             var index = parent.layer.getFrameIndex(window.name);
             parent.$('.btn-refresh').click();
             parent.layer.close(index);
         }
     });
 });
