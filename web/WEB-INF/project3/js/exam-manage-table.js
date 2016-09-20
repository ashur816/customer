$(document).ready(function () {
    var t = $("#table").dataTable({
        "showRowNumber": true,
        "aoColumnDefs": [
            /*操作列定义*/
            {
                "targets": [0],
                "data": "examQusetion",
                "bSortable": false
            },
            {
                "targets": [1],
                "data": "examScore",
                "bSortable": false
            },
            {
                "targets": [2],
                "data": "referenceAnswer",
                "bSortable": false
            },
            {
                "targets": [3],
                "data": "examLevel",
                "bSortable": false
            },
            {
                "targets": [4], //删除；修改
                "data": null,
                "bSortable": false,
                "defaultContent": "<button id='delrow' class='btn btn-primary' type='button'><i class='fa fa-trash-o'>删除</i></button>&nbsp;&nbsp;<button id='editrow' class='btn btn-primary' type='button'><i class='fa fa-edit'>修改</i></button>&nbsp;&nbsp;<button id='showrow' class='btn btn-primary' type='button'><i class='fa fa-trash-o'>查看</i></button>&nbsp;&nbsp;<button id='mark' class='btn btn-primary' type='button'><i class='fa fa-trash-o'>打分</i></button>"
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
        $.get("http://192.168.30.224:8791/getExamList/中级?token=" + token, function (data, status) {
            if (data != "") {
                t.fnClearTable();
                t.fnAddData(newData(data), true);
            } else {
                alert("暂时没有数据");
            }
        });
    }

    function newData(data) {
        var a = []; //定义一个空数组存放显示的数据
        $.each(data, function (n, value) {
            var tempObject = {};
            tempObject.examQusetion = value.examQusetion || "";
            tempObject.examScore = value.examScore || "";
            tempObject.referenceAnswer = value.referenceAnswer || "";
            tempObject.examLevel = value.examLevel || "";
            a.push(tempObject);
        });
        return a;
    }

    /*设置第一列隐藏*/
    t.fnSetColumnVis(0, false);
    /*修改*/
        $('#table tbody').on('click', 'button#editrow', function () {
            var data = t.api().row($(this).parents('tr')).data(); //获取点击行的数据
            //console.log(data["number"]); //取出记录id的值
            var data_id = {
                "examId": data["examId"]
            };
            console.log(data_id.userId);
            /*显示信息*/
            $.ajax({
                type: "get",
                url: "http://192.168.30.224:8791/getExamList/" + data_id.userId,
                success: function (response) {
                    console.log(response[0]["major"]);
                    $("#examQusetion2").val(response[0]["examQusetion"] || "");
                    $("#examScore2").val(response[0]["examScore"] || "");
                    $("#referenceAnswer2").val(response[0]["referenceAnswer"] || "");
                    $("#examLevel2").val(response[0]["examLevel"] || "");
                },
                error: function () {
                    console.log("ERROR!");
                }
            });
            $("#myModal-2").modal("show");
        });
    /*保存修改*/
        $("#btn3").click(function () {
            var object = $("#form-member-add-2").serializeArray();
            $.ajax({
                type: "POST",
                url: "http://192.168.30.224:8791/updateExam/" + data_id.userId,
                data: object,
                success: function (response) {
                    
                },
                error: function () {
                    console.log("error!");
                }
            });
        });
    /*删除*/
        $('#table tbody').on('click', 'button#delrow', function () {
            if (show() == true) {
                var data = t.api().row($(this).parents('tr')).data();
                var id = {
                    "examId": data["examId"]
                };
                $.ajax({
                        url: 'http://192.168.30.224:8791/deleteExam',
                        type: 'POST',
                        dataType: 'json',
                        data: id
                    })
                    .done(function (message) {
                        if (message == "删除成功") {
                            console.log(message);
                        };
                    })
                    .fail(function () {
                        alert("error");
                    });
            } else {
                return false;
            }
            refreshTable();
        });
    /*新增人员*/
    $("#btn2").click(function () {
        var obj = [];
        $.ajax({
            type: "POST",
            url: "http://192.168.30.224:8791/insertExam",
            data: $("#form-member-add-1").serialize(),
            async: false,
            success: function (response) {
                if (response.token != "") {
                    console.log(response);
                    obj.push({
                        "examQusetion": response.examQusetion,
                        "examScore": response.examScore,
                        "referenceAnswer": response.referenceAnswer,
                        "examLevel": response.examLevel
                    });
                    $("#myModal-1").modal("hide");
                    t.fnAddData(obj); //插入新的json对象或者数组
                    location.reload();
                } else {
                    console.log(response.message);
                }
            },
            error: function () {
                console.log("Connection error");
            }
        });
        console.dir($("#form-member-add-1").serialize());
    });
});
