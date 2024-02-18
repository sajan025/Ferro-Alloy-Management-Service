'use strict';
app.controller('qualityHistoryController', ['$scope', 'qualityHistoryService','authService','$parse', function ($scope, qualityHistoryService,authService,$filter,$compile,$parse,$window,) {

$scope.init = function() {
	var qualitypkid = document.querySelector('[data-qualitypkid]').getAttribute('data-qualitypkid');
	//localStorage.setItem("qualitypkid", qualitypkid);
	$scope.getHistoryData(qualitypkid);
	}	
		
	$scope.logout=function(){
	authService.logOut();
	location.href = '/Ferro-Alloy/login';
	}
		$scope.getHistoryData= function(id) {
 			qualityHistoryService.getHistoryData(id).then(function (results) {
 			//console.log(JSON.stringify(results.data))
 			$scope.qualityHistory = results.data;
 			//$scope.chemical=$scope.qualityHistory.compositionList;
 			//console.log(JSON.stringify(results.data.compositionList))
 			 }, function (error) {
      
    			 });
    		}


}
]);
