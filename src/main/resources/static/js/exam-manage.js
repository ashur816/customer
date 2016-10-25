$(document).ready(function () {

    //调整编辑器的大小
    CKEDITOR.replace('examQuestion',{
        height : 100,
        width : 765
    });
    CKEDITOR.replace('referAnswer',{
        height : 100,
        width : 765
    });

    // var mWidth = $('#exam-form-modal').width;
    // CKEDITOR.replace('question', {
    //     height: 100,
    //     width: mWidth * 8 / 12
    // });

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
    $('#exam-form-modal').on('show.bs.modal', function (e) {//调整modal的大小
        $(this).css({
            'left': function () {
                var modalWidth = $(this).width();
                var winWidth = $(window).width();
                var left = 0;
                if(winWidth > modalWidth){
                    left = (winWidth - modalWidth)/2 + 250;
                }
                else {
                    $(this).width(winWidth);
                    left = 250;
                }
                return left;
            }
        });
    });
    $("#btnAdd").click(function () {
        restForm();
        $(".modal-title").text("添加试题");
        showCKEDITOR(true);
        $("#exam-form-modal").modal("show");
    });

    /*************************************显示修改页面*******************************/
    $('#table-list').on('click', 'button#editrow', function () {
        restForm();
        $(".modal-title").text("修改试题");
        var data = t.api().row($(this).parents('tr')).data(); //获取点击行的数据
        var examinationId = data["examinationId"];
        /*显示信息*/
        $.ajax({
            type: "get",
            url: baseUrl + "/getExamInfo/" + examinationId + "?token=" + token,
            success: function (response) {
                if (response == "" || response == null) {
                    CKEDITOR.instances.examQuestion.setData("");
                    CKEDITOR.instances.referAnswer.setData("");
                    Huimodal_alert("未查询到题目信息", 1000);
                }
                else {
                    setDataToForm("form-exam", response);
                    $("#exam-form-modal").modal("show");
                    CKEDITOR.instances.examQuestion.setData(response.examinationQuestion);
                    CKEDITOR.instances.referAnswer.setData(response.referenceAnswer);
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
        var question = CKEDITOR.instances.examQuestion.getData().replace(/&nbsp;/ig, "").replace(/<[^>]+>/g,"").replace(/[\r\n]/g,"").replace(/[ ]/g,"");
        var answer = CKEDITOR.instances.referAnswer.getData().replace(/&nbsp;/ig, "").replace(/<[^>]+>/g,"").replace(/[\r\n]/g,"").replace(/[ ]/g,"");
        if(question == "" || question == null){
            $("#divQ")[0].innerHTML = '<br><br><br><span class="Validform_checktip Validform_wrong">题目不能为空</span>';
            return;
        }else {
            $("#divQ")[0].innerHTML = '<br><br><br><span class="Validform_checktip Validform_right"></span>';
        }
        if(answer == "" || answer == null){
            $("#divA")[0].innerHTML = '<br><br><br><span class="Validform_checktip Validform_wrong">参考答案不能为空</span>';
            return;
        }else {
            $("#divA")[0].innerHTML = '<br><br><br><span class="Validform_checktip Validform_right"></span>';
        }
        //form表单提价只有input框中的 将编辑器中的值赋值到隐藏的input框中
        $("#form-exam [name=examinationQuestion]").val(CKEDITOR.instances.examQuestion.getData());
        $("#form-exam [name=referenceAnswer]").val(CKEDITOR.instances.referAnswer.getData());
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
        CKEDITOR.instances.examQuestion.setData("");
        CKEDITOR.instances.referAnswer.setData("");
    }

    //显示编辑器
    function showCKEDITOR(flag){
        if(flag){
            $("#examQuestion").css("visibility", "");
            $("#referAnswer").css("visibility", "");
        }
        else{
            $("#examQuestion").css("visibility", "hidden");
            $("#referAnswer").css("visibility", "");
        }
    }
});