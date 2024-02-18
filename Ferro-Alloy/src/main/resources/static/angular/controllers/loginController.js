'use strict';
app.controller('loginController', ['$scope','cfpLoadingBar', '$location','$window', 'authService', 'ngAuthSettings', function ($scope, cfpLoadingBar,$location,$window, authService, ngAuthSettings) {
	var serviceBase = ngAuthSettings.apiServiceBaseUri;
    $scope.loginData = {
        username: "",
        password: "",
    };
	$scope.init = function() {
		$scope.ShowSpinnerStatus = false;
		//$scope.Logout();
		//alert("hii")
	}

    $scope.message = "";

    $scope.login = function () {
		$scope.ShowSpinner();
        authService.login($scope.loginData).then(function (result) {
		console.log(JSON.stringify(result))
		$scope.HideSpinner();
        if (result.status == '200') {
				
				if(result.userType== 'USER'){
					$window.location.href = serviceBase+"sms";
				}else if(result.userType== 'ADMIN'){
					$window.location.href = serviceBase+"admin";
				}else if(result.userType== 'SMS'){
					$window.location.href = serviceBase+"sms";
				}else if(result.userType== 'QUALITY'){
					$window.location.href = serviceBase+"quality";
				}else if(result.userType== 'FINANCE'){
					$window.location.href = serviceBase+"finance";
				}
					
                   
                } else {
                   $scope.error = 'Username or password is incorrect';
                   $window.location.href = serviceBase+"login";
                }

        },
         function (err) {
             //$scope.message = err.error_description;
			$scope.HideSpinner();
         });
    };

 	$scope.ShowSpinnerStatus = false;
	$scope.ShowSpinner = function(){
	$scope.ShowSpinnerStatus = true;
	 cfpLoadingBar.start(); 
	};			
	$scope.HideSpinner = function(){
	cfpLoadingBar.complete();
	$scope.ShowSpinnerStatus = false;
	
	};			
				

    

}]);
