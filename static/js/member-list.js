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
                alert("暂时没有数据");
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
            url: baseUrl + "/getUserList/" + data["userId"],
            success: function (response) {
                $("#userId2").val(response[0]["userId"] || "");
                $("#userName2").val(response[0]["userName"] || "");
                $("#password2").val(response[0]["password"] || "");
                $("#fullname2").val(response[0]["fullname"] || "");
                $("#age2").val(response[0]["age"] || "");
                $("#sex2").val(response[0]["sex"] || "");
                $("#graduateInstitution2").val(response[0]["graduateInstitution"] || "");
                $("#major2").val(response[0]["major"] || "");
                $("#workingLife2").val(response[0]["workingLife"] || "");
            },
            error: function () {
                console.log("ERROR!");
            }
        });
        $("#myModal-2").modal("show");
    });
    /*保存修改*/
    $("#btn3").click(function () {
        var data = $("#form-member-add-2").serialize();
        $.ajax({
            type: "POST",
            url: baseUrl + "/updateUser?token=" + token,
            data: data,
            success: function (response) {
                alert(response.message);
                $("#myModal-2").modal("hide");
            },
            error: function () {
                console.log("error!");
                alert("error");
            }
        });
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
            }).done(function (message) {
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
            url: baseUrl + "/getUserList/" + data["userId"],
            success: function (response) {
                $("#userName3").val(response[0]["userName"] || "");
                $("#password3").val(response[0]["password"] || "");
                $("#fullname3").val(response[0]["fullname"] || "");
                $("#age3").val(response[0]["age"] || "");
                $("#sex3").val(response[0]["sex"] || "");
                $("#graduateInstitution3").val(response[0]["graduateInstitution"] || "");
                $("#major3").val(response[0]["major"] || "");
                $("#workingLife3").val(response[0]["workingLife"] || "");
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
            "grade": data["grade"]
        };
        console.log(data_end.grade);
        if (data_end.endTime != "" && data_end.grade == "") {
            window.location.href = baseUrl + "/exam-detail.html?userId=" + data_end.userId + "&token=" + token;
        } else if (data_end.endTime == "") {
            alert("该人员还在答题中...");
        } else if (data_end.grade != "") {
            alert("已阅卷")
        }
    });
    /*新增人员*/
    $("#btn2").click(function () {
        var obj = [];
        $.ajax({
            type: "POST",
            url: baseUrl + "/register?token=" + token,
            data: $("#form-member-add-1").serialize(),
            async: false,
            success: function (response) {
                if (response.token != "") {
                   $("#myModal-1").modal("hide");
                   refreshTable();
                   location.reload();
                   alert(response.message);
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
