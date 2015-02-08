angular.module('DiskApp',[]).controller("DisksListCtrl", function($scope, $http, filterFilter) {
  $scope.disks = [];
  $scope.cart = [];

  $scope.isList = true;
  $scope.isCart = false;
  $scope.count = 10;
  $scope.totalPrice = 0.0;
  $scope.disk = {price:10.0, count:0}


  $scope.init = function(){
    getDisks();
  }


  $scope.goToCart = function() {
    $scope.isList = false;
    $scope.isCart = true;
    getCart();
  };

  $scope.goToList = function() {
      $scope.isList = true;
      $scope.isCart = false;
      getDisks();
  };


  $scope.addDisk = function() {
      $http({
        method: 'POST',
        url: '/disks/add',
        data: JSON.stringify($scope.disk),
        contentType: "application/json"
      }).success(function(){
        getDisks();
      }
      );
    };

  $scope.removeDisk = function(disk) {
    $http({
      method: 'DELETE',
      url: '/disks/remove/' + disk.name
    }).success(function(){
      getDisks();
    });

  };

  $scope.addToCart = function(disk) {
    disk.count = $scope.count;
    $http({
      method: 'POST',
      url: '/cart/add',
      data: JSON.stringify(_.omit(disk,"$$hashKey")),
      contentType: "application/json"
    }).success(function(){
    });
  };

  $scope.isAllChecked = function(){
    var checkBoxes = $(".check-self");
    var flag = 0;
    angular.forEach(checkBoxes, function(checkbox) {
      if(checkbox.checked) {
        flag++;
      }
    });

    if(flag == checkBoxes.length) {
      $('.check-all')[0].checked = true;
    }
    else {
      $('.check-all')[0].checked = false;
    }

    $scope.calculateTotalPrice();
  };

  $scope.calculateTotalPrice = function() {
    var checkBoxes = $(".check-self");

    $scope.totalPrice = 0.0;
    for(var i = 0; i < checkBoxes.length; i++)
      {
        if(checkBoxes[i].checked)
          {
            $scope.totalPrice += $scope.cart[i].count * $scope.cart[i].price;
          }
      }
    };

  function getIndexOfDisk(disk) {
    return $scope.disks.indexOf(disk);
  };

  function getDisks() {
    $http({
          method: 'GET',
          url: '/disks'
        }).success(function(data) {
          $scope.disks = data;
        }).error(function(error) {
    });
  };

  function getCart() {
    $http({
      method: 'GET',
      url: '/cart'
          }).success(function(data) {
            $scope.cart = data;
          }).error(function(error) {
    });
  };
});
