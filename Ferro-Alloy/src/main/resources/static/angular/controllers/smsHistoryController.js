'use strict';
app.controller('smsHistoryController', ['$scope', 'smsService','authService','$parse', function ($scope, smsService,authService,$filter,$compile,$parse,$window,) {

$scope.smsData=[{}];

$scope.init = function() {
	var qualitypkid = document.querySelector('[data-qualitypkid]').getAttribute('data-qualitypkid');
	$scope.getSmsHistoryData(qualitypkid);
	}	
		
	$scope.logout=function(){
	authService.logOut();
	location.href = '/Ferro-Alloy/login';
	}
		$scope.getSmsHistoryData= function(id) {
 			smsService.getSmsHistoryData(id).then(function (results) {
 			console.log(JSON.stringify(results.data))
 			$scope.smsData = results.data.data;
 			
 			//console.log(JSON.stringify(results.data.compositionList))
 			 }, function (error) {
      			alert("somthing went worng.")
    			 });
    		}


}
]);
