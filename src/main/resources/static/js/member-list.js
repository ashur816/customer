$(document).ready(function () {
    var t = $("#table").dataTable({
        "showRowNumber": true,
        "aoColumnDefs": [
            /*操作列定义*/
            {
                "targets": [0],
                "data": "userId",
                "bVisible": false
            },
            {
                "targets": [1],
                "data": null,
            },
            {
                "targets": [2],
                "data": "fullname",
                "bSortable": false
            },
            {
                "targets": [3],
                "data": "startTime",
                "bSortable": true
            },
            {
                "targets": [4],
                "data": "endTime",
                "bSortable": true
            },
            {
                "targets": [5], //删除；修改
                "data": null,
                "bSortable": false,
                "defaultContent": "<button id='delrow' class='btn btn-primary' type='button'><i class='fa fa-trash-o'>删除</i></button>&nbsp;&nbsp;<button id='editrow' class='btn btn-primary' type='button'><i class='fa fa-edit'>修改</i></button>&nbsp;&nbsp;<button id='showrow' class='btn btn-primary' type='button'><i class='fa fa-trash-o'>查看</i></button>&nbsp;&nbsp;<button id='mark' class='btn btn-primary' type='button'><i class='fa fa-trash-o'>打分</i></button>"
            },
            {
                "targets": [6],
                "data": "grade",
                "bSortable": true
            },
            {
                "targets": [7],
                "data": "examMarker",
                "bSortable": false
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
        $.get(baseUrl + "/getUserList/0?token=" + token, function (data, status) {
            if (data != "") {
                t.fnClearTable();
                t.fnAddData(newData(data), true);
            } else {
                Huimodal_alert("暂时没有数据", 1000);
            }
        });
    }

    function newData(data) {
        var a = []; //定义一个空数组存放显示的数据
        $.each(data, function (n, value) {
            var tempObject = {};
            tempObject.userId = value.userId || "";
            tempObject.fullname = value.fullname || "";
            tempObject.startTime = value.startTime || "";
            tempObject.endTime = value.endTime || "";
            tempObject.grade = value.totalGoal || "";
            tempObject.examMarker = value.examMarker || "";
            a.push(tempObject);
        });
        return a;
    }

    /*修改*/
    $('#table tbody').on('click', 'button#editrow', function () {
        var data = t.api().row($(this).parents('tr')).data(); //获取点击行的数据
        /*显示信息*/
        $.ajax({
            type: "get",
            url: baseUrl + "/getUserList/" + data["userId"] + "?token=" + token,
            success: function (response) {
                setDataToForm("form-member-add-2", response[0]);
            },
            error: function () {
                console.log("ERROR!");
            }
        });
        $("#myModal-2").modal("show");
    });
    /*保存修改*/
    $("#btn3").click(function () {
        // console.dir($("#form-member-add-2").serialize());
        $.ajax({
            type: "POST",
            url: baseUrl + "/updateUser?token=" + token,
            data: $("#form-member-add-2").serialize(),
            success: function (response) {
                $("#myModal-2").modal("hide");
                refreshTable();
                Huimodal_alert(response.message, 1000);
            },
            error: function (a,b) {
                console.log("error", 1000);
            }
        });
        console.dir($("#form-member-add-2").serialize());
    });
    /*删除*/
    $('#table tbody').on('click', 'button#delrow', function () {
        if (show() == true) {
            var data = t.api().row($(this).parents('tr')).data();
            var id = {
                "userId": data["userId"]
            };
            $.ajax({
                url: baseUrl + "/deleteUser?token=" + token,
                type: 'POST',
                dataType: 'json',
                data: id
            }).done(function (response) {
                Huimodal_alert(response.message, 1000);
                $("#myModal-2").modal("hide");
            }).fail(function () {
                Huimodal_alert("error", 1000);
            });
        } else {
            return false;
        }
        refreshTable();
    });
    /*查看*/
    $('#table tbody').on('click', 'button#showrow', function () {
        var data = t.api().row($(this).parents('tr')).data();
        $.ajax({
            type: "get",
            url: baseUrl + "/getUserList/" + data["userId"] + "?token=" + token,
            success: function (response) {
                setDataToForm("form-member-add-3", response[0]);
            },
            error: function () {
                console.log("error!");
            }
        });
        $("#myModal-3").modal("show");
    });
    /*打分*/
    $('#table tbody').on('click', 'button#mark', function () {
        var data = t.api().row($(this).parents('tr')).data();
        var data_end = {
            "endTime": data["endTime"],
            "userId": data["userId"],
            "grade": data["grade"],
            "examMarker":data["examMarker"]
        };
        if (data_end.endTime != "") {
            window.location.href = baseUrl + "/exam-detail.html?userId=" + data_end.userId + "&token=" + token;
        } else if (data_end.endTime == "") {
            Huimodal_alert("该人员还在答题中...", 1000);
        }
    });
    /*新增人员*/
    $("#btn2").click(function () {
        // var obj = [];
        $.ajax({
            type: "POST",
            url: baseUrl + "/register?token=" + token,
            data: $("#form-member-add-1").serialize(),
            async: false,
            success: function (response) {
                if (response.token != "") {
                    $("#myModal-1").modal("hide");
                    refreshTable();
                    Huimodal_alert(response.message, 1000);
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

    window.onload = function () {
        $.ajax({
            type: "get",
            url: baseUrl + "/getUserById?token=" + token,
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            success: function (Message) {
                console.dir(Message);
                $("#user").text(Message.fullname);
            },
            error: function () {
                Huimodal_alert("您是非法用户请重新登录进入", 1000);
                window.location.href = baseUrl + "/login.html";
            }
        });
    };
});
