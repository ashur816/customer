$(function () {
    $("[name='content']").on("click", "button", function () {
        $('#myModal .modal-header .modal-title').empty().text($(this).text());
        $('#myModal').modal("show");
    });
    $("#btn1").click(function () {
        var obj = {};
        var goalList = [];
        goalList.push({
            "answerId": 1,
            "goal": $("#score").val()
        });
        obj.userId = user_id;
        obj.goalList = goalList;
        console.log(obj.userId);
    });
    $("#btn").click(function () {
        window.location.href = baseUrl + "/member-list.html";
    });
});
