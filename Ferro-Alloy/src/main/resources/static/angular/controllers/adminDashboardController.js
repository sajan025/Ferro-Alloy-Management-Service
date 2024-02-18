'use strict';
app.controller('adminDashboardController', ['$scope', 'adminDashboardService', function ($scope, adminDashboardService) {

    $scope.data = [];
	
	$scope.init = function() {
		$scope.ShowSpinnerStatus = false;
		//$scope.Logout();
		//alert("hii")
	}

    adminDashboardService.getUsers().then(function (results) {
	console.log(JSON.stringify(results))
        $scope.data = results;

    }, function (error) {
        //alert("error4 ")
    });

}]);