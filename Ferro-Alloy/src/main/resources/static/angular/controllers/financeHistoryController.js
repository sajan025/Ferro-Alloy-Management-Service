'use strict';
app.controller('financeHistoryController', ['$scope', 'financeService','authService', function ($scope, financeService,authService) {



    $scope.data = [];
	$scope.currentPage = 1;
	$scope.pageNo=null;
	$scope.sortBy=null;
	$scope.productName=null;
	$scope.gradeName=null;
	$scope.internalGradename=null;
	$scope.financeRequest={};
	
	$scope.init = function() {
		$scope.ShowSpinnerStatus = false;
		$scope.getQualityList();
		$scope.costRequest=[{}];
		var qualitypkid = document.querySelector('[data-qualitypkid]').getAttribute('data-qualitypkid');
		if(qualitypkid!=null || qualitypkid!=undefined){
			$scope.getfinanceHistorydata(qualitypkid);
			$scope.getCostingHistorydata(qualitypkid);
		}
		
	}
	
	$scope.calculateValue1 = function (saprates, ratesLcncBasis,index) {
 		  var saprates = parseFloat(saprates);
 		  var ratesLcncBasis=parseFloat(ratesLcncBasis)
		  var result1=  saprates > ratesLcncBasis ? saprates: ratesLcncBasis;
 		  $scope.costRequest[index].baseRates=result1;
          var result = result1 < 100000 ? result1 * 1.03 : result1 * 1.02;
          $scope.costRequest[index].rates=result;
          return result.toFixed(2); // Example: Round to 2 decimal places
    };

	$scope.getQualityList=function(pageNo,sortBy,productName,gradeName,internalGradename){
		if(pageNo!=null)$scope.pageNo=pageNo-1;
		if(sortBy!=null)$scope.sortBy=sortBy;
		if(productName!=null)$scope.productName=productName;
		if(gradeName!=null)$scope.gradeName=gradeName;
		if(internalGradename!=null)$scope.internalGradename=internalGradename;
  			financeService.getQualityList($scope.pageNo,$scope.sortBy,$scope.productName,$scope.gradeName,$scope.internalGradename).then(function (results) {
 		$scope.qualityRequest = results.data.content;
 		$scope.totalPages = results.data.totalElements;
        $scope.productName=null;
		$scope.gradeName=null;
		$scope.internalGradename=null;
    }, function (error) {
      
     });
    }
    
    $scope.addRow1 = function(index) {
		$scope.costRequest.push({});  
     }
     
	$scope.removeRow1 = function(index) {
		if(confirm("Do You Want to delete")){
		$scope.costRequest.splice(index,1);
	}
 }

	/*$scope.open=function(qr){
		$scope.qualityList=qr;
    }*/

		$scope.saveFinance=function(status){
			if(status==1){
				$scope.financeRequest.status="Submit";
				}
			else{
				$scope.financeRequest.status="Save as Draft";
				}

   	 		financeService.saveFinance($scope.financeRequest).then(function (results) {
			$scope.financeRequest = results;
        	location.reload();
     		}, function (error) {
      
     					});
    		}
    		
		$scope.saveCost=function(status){
			if(status==1){
				$scope.costRequest.status="Submit";
				}
			else{
				$scope.costRequest.status="Save as Draft";
				}
				financeService.saveCost($scope.costRequest).then(function (results) {
				 $scope.costRequest = results;
     		}, function (error) {
				 });
   			 }
    
		$scope.getfinancedata=function(id){
			financeService.financeData(id).then(function (results) {
 			$scope.financeRequest = results.data;
  			 }, function (error) {
      		});
    		}
    		
    $scope.getfinanceViewdata=function(id){
				financeService.financeData(id).then(function (results) {
 			//console.log(id+" ---> "+JSON.stringify(results))
		//$scope.financeHistory = results.data;
		$scope.financeHistory = results.data;
 			 }, function (error) {
      
    			 });
    		}

	$scope.getfinanceHistorydata=function(id){
			financeService.financeHistoryData(id).then(function (results) {
 			//console.log(id+" ---> "+JSON.stringify(results))
		$scope.financeHistoryData = results.data;
 			 }, function (error) {
      
    			 });
    		}
	
	$scope.getCostingHistorydata=function(id){
			financeService.getCostingHistorydata(id).then(function (results) {
 			//console.log(id+" ---> "+JSON.stringify(results))
		$scope.costingHistoryData = results.data;
 			 }, function (error) {
      
    			 });
    		}

				$scope.$watch('financeRequest', function () {
                $scope.convertToRowValues();
				var result = $scope.sumProduct($scope.rowValues); 
				
               // $scope.financeRequest.totalRevisedCost=$scope.roundToInteger(result);
 				}, true); 
			
			$scope.rowValues = [];

            $scope.convertToRowValues = function () {
                $scope.rowValues = [];

                for (var key in $scope.financeRequest) {
					if(key ==="segment"){}
					else if(key ==="subSegment") {}
					else if(key ==="remarks") {}
					else if(key ==="totalRevisedCost") {}
					else if(key ==="id") {}
					else if(key ==="qualityRequestFkId") {}
					else{
						 if ($scope.smsValues.hasOwnProperty(key)) {
                        $scope.rowValues.push($scope.financeRequest[key]);
                    }
				}
                }
			console.log("fin---> "+JSON.stringify($scope.rowValues));
            };

	$scope.sumProduct = function (array1) {
			
               /* if (array1.length !== array2.length) {
                    throw new Error('Arrays must have the same length');
                }*/

                var sum = 0;
                for (var i = 0; i < array1.length; i++) {
                    sum += array1[i];
                }

                return sum;
            };

	$scope.logout=function(){
		authService.logOut();
		location.href = '/Ferro-Alloy/login';
	}
}]);