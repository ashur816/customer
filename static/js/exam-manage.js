$(document).ready(function () {

    var t = $("#table-list").dataTable({
        "bFilter": false, //过滤功能
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
                "sWidth": 40,
                "targets": [5],
                "bSearchable": true,
                "data": "examinationLevel",
                "bSortable": false,
                "mRender": function (data, display, row) {
                    if (data == 1) {
                        return "初级";
                    }
                    else if (data == 2) {
                        return "中级";
                    }
                    else if (data == 3) {
                        return "高级";
                    }
                    else {
                        return "";
                    }
                }
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

    refreshTable();

    function refreshTable() {
        var examLevel = $("#selectLevel").val();
        $.get(baseUrl + "/getExamList/" + examLevel + "?token=" + token, function (data, status) {
            if (data != "") {
                t.fnClearTable();
                t.fnAddData(data, true);
            } else {
                Huimodal_alert("暂时没有数据", 1000);
            }
        });
    }

    $("#btnQry").click(function () {
        refreshTable();
    });

    /*************************************显示新增页面*******************************/
    $("#btnAdd").click(function () {
        restForm();
        $("#exam-form-modal").modal("show");
    });

    /*************************************显示修改页面*******************************/
    $('#table-list').on('click', 'button#editrow', function () {
        restForm();
        var data = t.api().row($(this).parents('tr')).data(); //获取点击行的数据
        var examinationId = data["examinationId"];
        /*显示信息*/
        $.ajax({
            type: "get",
            url: baseUrl + "/getExamInfo/" + examinationId + "?token=" + token,
            success: function (response) {
                if (response == "" || response == null) {
                    Huimodal_alert("未查询到题目信息", 1000);
                }
                else {
                    setDataToForm("form-exam", response);
                    $("#exam-form-modal").modal("show");
                }
            },
            error: function () {
                Huimodal_alert("error", 1000);
            }
        });
    });

    /*************************************新增 & 修改 提交数据*******************************/
    $("#btnSave").click(function () {
        var data = $("#form-exam").serialize();
        var examinationId = $("#form-exam [name=examinationId]").val();
        var postUrl = baseUrl + "/insertExam?token=" + token;
        if (examinationId != null && examinationId != "") {
            postUrl = baseUrl + "/updateExam?token=" + token;
        }
        validator.submitForm(false, postUrl);
    });

    /*************************************删除*******************************/
    $('#table-list').on('click', 'button#delrow', function () {
        if (show() == true) {
            var data = t.api().row($(this).parents('tr')).data(); //获取点击行的数据
            var examinationId = data["examinationId"];
            $.ajax({
                url: baseUrl + "/deleteExam?token=" + token + "&examinationId=" + examinationId,
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

    //表单验证
    var validator = $("#form-exam").Validform({
        tiptype: 2,
        ajaxPost: true,
        callback: function (response) {
            $("#Validform_msg").hide();
            $("#exam-form-modal").modal("hide");
            Huimodal_alert(response.message, 1000);
            refreshTable();
        }
    });

    //初始化表单
    function restForm() {
        $("#form-exam")[0].reset();
        validator.resetForm();
        $("#form-exam [name='examinationId']").val("");
        $("#form-exam span.Validform_checktip").empty();
    }
});