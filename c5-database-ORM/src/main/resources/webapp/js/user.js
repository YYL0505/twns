angular.module('DiskApp',[]).controller("UserCtrl", function($scope, $http, filterFilter) {

  $scope.errorMessage = "";
  $scope.isError = false;

  $scope.init = function() {
    $http({
        method: 'GET',
        url: '/user',
        }).success(function(data) {
                $scope.users = data
            }).error(function(error) {
             });
  };

  $scope.userNameValid = function() {

    if($scope.user.user_name == '') {
        alert('用户名不能为空!');
    } else {
        for(var i = 0; i < $scope.users.length; i++) {
            if($scope.user.user_name == $scope.users[i].user_name) {
                alert('用户已存在!');
                $scope.user.user_name = null
                break;
            }
        }
    }
  };

   $scope.rePasswordValid = function() {
      $scope.userNameValid()
      if($scope.user.password.length == 0) {
        alert('密码不能为空!');
      } else {
          if($scope.user.password.length < 6) {
            $scope.user.password = null;
            alert('密码长度小于6!');
          }
      }
    };

  $scope.rePasswordValid = function() {
    $scope.rePasswordValid()
    if ($scope.user.password != $scope.password) {
        $scope.password = null
        alert('两次输入的密码不一致!')
    }
  };

  $scope.login = function() {
    $http({
        method: 'POST',
        url: '/user/login',
        data: JSON.stringify($scope.user),
        contentType: "application/json"
    }).success(function(data) {
        if(data.status == 'ok'){
            sessionStorage.setItem("user_name", data.user_name);
            sessionStorage.setItem("type", data.type);
            window.location.href = "../html/home.html";
        }
        else{
            $scope.isError = true
            $scope.errorMessage = data.errorMsg;
        }
      }).error(function(error) {
        });
  };

  $scope.register = function() {
    $scope.user.type = "client"
      $http({
         method: 'POST',
         url: '/user/register',
         data: JSON.stringify($scope.user),
         contentType: "application/json"
      }).success(function(data) {
           window.location.href = "../html/login.html";
        }).error(function(error) {
          });
    }
});
