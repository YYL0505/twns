angular.module('DiskApp',[]).controller("DisksListCtrl", function($scope, $http, filterFilter) {

  $scope.init = function(){
    $scope.disks = [];
    $scope.cart = [];

    $scope.isManager = false;
    $scope.user = {};
    $scope.isList = true;
    $scope.isCart = false;
    $scope.totalPrice = 0.0;
    $scope.totalCount = 0;
    $scope.editing = false;
    $scope.addDisking = false;
    $scope.count = 10;

    getDisks();
    $scope.user = sessionStorage.getItem("user_name");
    if("manager" == sessionStorage.getItem("type"))
      $scope.isManager = true;
    else
      $scope.isManager = false;
  }


  $scope.goToCart = function() {
    $scope.isList = false;
    $scope.isCart = true;
    getCart()

  };

  $scope.goToList = function() {
    $scope.isList = true;
    $scope.isCart = false;
};

  $scope.login = function() {
    $http({
      method: 'GET',
      url: ''
        }).success(function(data) {
        }).error(function(error) {
    });
  };


  $scope.addDisk = function() {
      $http({
        method: 'POST',
        url: '/disks/add',
        data: JSON.stringify($scope.disk),
        contentType: "application/json"
      }).success(function(){
        getDisks();
        $scope.addDisking = false;
      }
      );
    };

  $scope.removeDisk = function(disk) {
    $http({
      method: 'DELETE',
      url: '/disks/remove/' + getIndexOfDisk(disk)
    }).success(function(){
      getDisks();
    });

  };

  $scope.addToCart = function(disk) {
    if (null == $scope.user) {
      window.location.href = "../html/login.html";
    } else {
    var tempCount = disk.count - $scope.count

    if (tempCount < 0) {
      alert('库存数量不足!')
    } else {
      disk.count = $scope.count;
      $http({
        method: 'POST',
        url: '/cart/add/' + $scope.user,
        data: JSON.stringify(_.omit(disk,"$$hashKey")),
        contentType: "application/json"
      }).success(function(){
        setDiskCount(disk.id, tempCount)
      });
      }
    }
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
    console.log(checkBoxes.length)

    $scope.totalPrice = 0.0;
    $scope.totalCount = 0;
    for(var i = 0; i < checkBoxes.length; i++)
      {
        if(checkBoxes[i].checked)
          {
            $scope.totalCount++;
            $scope.totalPrice += $scope.cart[i].count * $scope.cart[i].price;
          }
      }
  };

  $scope.removeDiskFromCart = function(id) {
    removeDiskFromCart(id);
    getCart();
    $scope.calculateTotalPrice();
  };


  $scope.removeDisksFromCart = function() {
    var checkBoxes = $(".check-self");

    for(var i = 0; i < checkBoxes.length; i++) {
      if(checkBoxes[i].checked)
        removeDiskFromCart($scope.cart[i].id)
    }
    getCart();
    $scope.calculateTotalPrice();
  };

  $scope.goToEditing = function() {
    $scope.editing = true;
  };

  $scope.goToEdited = function() {
      $scope.editing = false;
  };

  $scope.goToAddDisk = function() {
    $scope.addDisking = true;
  };

  $scope.goToEdit = function() {
    $scope.addDisking = false;
  }

  function setDiskCount(id, count) {
    $http({
           method: 'POST',
           url: '/disks/setCount/' + id,
           data: count
          }).success(function(data) {
              getDisks();
            }).error(function(error) {
    });
  };

  $scope.updateDisk = function(disk) {
    $http({
              method: 'POST',
              url: '/disks/update',
              data: disk
            }).success(function(data) {
              $scope.disks = data;
            }).error(function(error) {
        });
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
          url: '/cart/' + $scope.user
        }).success(function(data) {
            $scope.cart = data;
           }).error(function(error) {
              });
  }

  function removeDiskFromCart(id) {
      $http({
        method: 'DELETE',
        url: '/cart/remove/' + id
      }).success(function(data) {
        }).error(function(error) {
          });
   };
});
