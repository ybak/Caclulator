var app = angular.module('calcApp', []);
app.controller('calcCtrl', function($scope, $http, $filter) {

    $scope.repayTypes = {
        '到期还本息' : 1,
        '每月还息，到期还本' : 2
/*        ,
        '按月还本息（等额本息）' : 3,
        '按日算息，每月付息，到期还本' : 4,
        '等额本金' : 5,*/
    };

    console.log($scope.params);
    /*使用filter格式化日期，提交http请求*/
    $scope.calculate = function () {
        $http({
            method: 'POST',
            url: '/calc',
            params: { 'loanDate': $filter('date')($scope.loanDate,'yyyy-MM-dd'),
                'loanAmount': $scope.loanAmount,
                'loanTerm': $scope.loanTerm,
                'loanTermType': $scope.loanTermType,
                'loanRate': $scope.loanRate,
                'loanRateType': $scope.loanRateType,
                'repayType': $scope.repayType
            }
        }).then(function successCallback(response) {
            $scope.scheduleList = response.data.scheduleList;
            $scope.Msg = response.data.msg;
            console.log("successCallback:");
            console.log(response);
        }, function errorCallback(response) {
            $scope.Msg = "请求无响应！";
            console.log("errorCallback:");
            console.log(response);
        });
    }

});