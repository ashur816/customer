var ActiveMock = true;
if (ActiveMock) {
    $.mockJSON.data.DAXUE = ['南京信息工程大学', '南京大学', '东南大学', '南京邮电大学', '南京理工大学', '解放军理工大学', '南京电大', '南京人大', '南京厦大', '南京北大'];
    $.mockJSON.data.SEX = ['男','女'];
    
    $.mockjax({
        url: 'http://192.168.30.245/work',
        status: 200,
        responseTime: 750,
        response: function () {
            var data = $.mockJSON.generateFromTemplate([
                {
                    "token": null,
                    "userName": "aaaa",
                    "userType": 1,
                    "password": "aaa12",
                    "fullname": "微微",
                    "graduateInstitution": "@DAXUE",
                    "age": 25,
                    "sex": "女",
                    "major": "计算机科学与技术",
                    "workingLife": "1",
                    "message": null,
                    "startTime": 1473331759000,
                    "endTime": null
                },
                {
                    "token": null,
                    "userName": "aaa",
                    "userType": 2,
                    "password": "aa",
                    "fullname": "微",
                    "graduateInstitution": "南京信息技术工程大学",
                    "age": 21,
                    "sex": "男",
                    "major": "计算机科学",
                    "workingLife": "2",
                    "message": null,
                    "startTime": 1473331,
                    "endTime": null
                }
            ], "list|20-30");
            this.responseText = data;
        }
    });
}
