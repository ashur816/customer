$(document).ready(function () {

    var token = getUrlParam("token");

    var t = $("#table-list").dataTable({
        "bFilter": true, //过滤功能
        "aoColumnDefs": [
            {
                "sTitle": "题目id",
                "targets": [0],
                "data": "examinationId",
                "bVisible": false,
                "bSearchable": false,
                "bSortable": false
            },
            {
                "sTitle": "序号",
                "targets": [1],
                "sWidth": 25,
                "sClass": "center",
                "bSearchable": false,
                "data": null,
                "bSortable": false
            },
            {
                "sTitle": "题目",
                "sClass": "center",
                "bSearchable": false,
                "targets": [2],
                "data": "examinationQuestion",
                "bSortable": false
            },
            {
                "sTitle": "分数",
                "sClass": "center",
                "bSearchable": false,
                "sWidth": 30,
                "targets": [3],
                "data": "examinationScore",
                "bSortable": false
            },
            {
                "sTitle": "参考答案",
                "sClass": "center",
                "bSearchable": false,
                "targets": [4],
                "data": "referenceAnswer",
                "bSortable": false
            },
            {
                "sTitle": "级别",
                "sClass": "center",
                "sWidth": 25,
                "targets": [5],
                "bSearchable": true,
                "data": "examinationLevel",
                "bSortable": false
            },
            {
                "sTitle": "操作",
                "sClass": "center",
                "bSearchable": false,
                "targets": [6], //删除；修改
                "data": null,
                "bSortable": false,
                "defaultContent": "<button id='delrow' class='btn btn-primary' type='button'><i class='fa fa-trash-o'>删除</i></button>&nbsp;&nbsp;<button id='editrow' class='btn btn-primary' type='button'><i class='fa fa-edit'>修改</i></button>"
            }
        ],
        /*添加索引列*/
        "fnDrawCallback": function () {
            var api = this.api();
            var startIndex = api.context[0]._iDisplayStart;
            api.column(1).nodes().each(function (cell, i) {
                cell.innerHTML = startIndex + i + 1;
            });
        }
    });

    var url = "http://localhost:8791";

    refreshTable();

    function refreshTable() {
        $.get(url + "/getExamList/0?token=" + token, function (data, status) {
            if (data != "") {
                t.fnClearTable();
                t.fnAddData(data, true);
            } else {
                Huimodal_alert("暂时没有数据", 1000);
            }
        });
    }

    /*************************************新增*******************************/
    $("#btn2").click(function () {
        $.ajax({
            type: "POST",
            url: url + "/insertExam?token=" + token,
            data: $("#form-exam-add").serialize(),
            async: false,
            success: function (response) {
                Huimodal_alert(response.message, 1000);
                $("#exam-add-modal").modal("hide");
                refreshTable();
            },
            error: function () {
                Huimodal_alert("error", 1000);
            }
        });
    });

    /*************************************修改*******************************/
    $('#table-list').on('click', 'button#editrow', function () {
        var data = t.api().row($(this).parents('tr')).data(); //获取点击行的数据
        var examinationId = data["examinationId"];
        /*显示信息*/
        $.ajax({
            type: "get",
            url: url + "/getExamInfo/" + examinationId + "?token=" + token,
            success: function (response) {
                if (response == "" || response == null) {
                    Huimodal_alert("未查询到题目信息", 1000);
                }
                else {
                    setDataToForm("form-exam-update", response);
                    $("#exam-update-modal").modal("show");
                }
            },
            error: function () {
                Huimodal_alert("error", 1000);
            }
        });
    });

    /*保存修改*/
    $("#btn3").click(function () {
        var data = $('#form-exam-update').serialize();
        $.ajax({
            type: "POST",
            url: url + "/updateExam?token=" + token,
            data: data,
            success: function (response) {
                //1s自动关闭
                Huimodal_alert(response.message, 1000);
                $("#exam-update-modal").modal("hide");
                refreshTable();
            },
            error: function (a, b, c) {
                Huimodal_alert("error", 1000);
            }
        });
    });

    /*************************************删除*******************************/
    $('#table-list').on('click', 'button#delrow', function () {
        if (show() == true) {
            var data = t.api().row($(this).parents('tr')).data(); //获取点击行的数据
            var examinationId = data["examinationId"];
            $.ajax({
                url: url + "/deleteExam?token=" + token + "&examinationId=" + examinationId,
                type: 'POST',
                dataType: 'json',
                data: null
            }).done(function (response) {
                Huimodal_alert(response.message, 1000);
                refreshTable();
            }).fail(function () {
                Huimodal_alert("error", 1000);
            });
        } else {
            return false;
        }
    });

    //将列数据写入form
    function setDataToForm(formId, formData) {
        if (formData) {
            var form = $("#" + formId);
            $.each(form.serializeArray(), function (index) {
                $("#" + formId + " [name='" + this.name + "']").val(formData[this.name]);
            });
        }
        return formData;
    }

    //form 取值
    function getFormData(formId) {
        var form = $("#" + formId);
        var json = {};
        $.each(form.serializeArray(), function (index) {
            json[this.name] = this.value;
        });
        return JSON.stringify(json);
    }

    //获取url参数
    function getUrlParam(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]);
        return null;
    }
});
