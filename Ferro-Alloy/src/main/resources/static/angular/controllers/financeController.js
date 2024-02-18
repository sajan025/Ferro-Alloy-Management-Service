'use strict';
app.controller('financeController', ['$scope', 'financeService','authService', function ($scope, financeService,authService) {



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
		/*var qualitypkid = document.querySelector('[data-qualitypkid]').getAttribute('data-qualitypkid');
		if(qualitypkid!=null || qualitypkid!=undefined){
			$scope.getfinanceHistorydata(qualitypkid);
		}*/
		
	}
	
	/*$scope.calculateValue1 = function (saprates, ratesLcncBasis,index) {
 		  var saprates = parseFloat(saprates);
 		  var ratesLcncBasis=parseFloat(ratesLcncBasis)
		  var result1=  saprates > ratesLcncBasis ? saprates: ratesLcncBasis;
 		  $scope.costRequest[index].baseRates=result1;
          var result = result1 < 100000 ? result1 * 1.03 : result1 * 1.02;
          $scope.costRequest[index].rates=result;
          return result.toFixed(2); // Example: Round to 2 decimal places
    };*/

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
	//qualityRequestFkId
		$scope.saveFinance=function(status){
			if(status==1){
				$scope.financeRequest.status="Submit";
				}
			else{
				$scope.financeRequest.status="Save as Draft";
				}
			$scope.financeRequest.qualityRequestFkId=$scope.qualityRequestFkId;
   	 		financeService.saveFinance($scope.financeRequest).then(function (results) {
			$scope.financeRequest = results;
        	location.reload();
     		}, function (error) {
      
     					});
    		}

$scope.setQualityId=function(qualityPkid){
	$scope.qualityRequestFkId=qualityPkid;
	$scope.getCostingRates(qualityPkid);
}
$scope.getCostingRates=function(qualityPkid){
	financeService.getCostingRates(qualityPkid).then(function (results) {
				//console.log("fin---> "+JSON.stringify(results));
				if(results.data.data.length!=0){
					$scope.costRequest = results.data.data;
				}
				
     		}, function (error) {
				 alert("somthing went wrong")
			});
}
    		
		$scope.saveCost=function(status){
			
				for(var i=0;i<$scope.costRequest.length;i++){
					if(status==1){
						$scope.costRequest[i].status="Submit";
					}
					else{
					$scope.costRequest[i].status="Save as Draft";
				}
					$scope.costRequest[i].qualityRequestFkId=$scope.qualityRequestFkId;
				}
				financeService.saveCost($scope.costRequest).then(function (results) {
				 location.reload();
				$scope.costRequest = [{}];
     		}, function (error) {
				 alert("somthing went wrong")
			});
   			 }
    
		$scope.getfinancedata=function(id){
			$scope.getCostingRates(id)
			financeService.financeData(id).then(function (results) {
				console.log(id+"o ---> "+JSON.stringify(results))
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

	/*$scope.getfinanceHistorydata=function(id){
			financeService.financeHistoryData(id).then(function (results) {
 			console.log(id+" ---> "+JSON.stringify(results))
		$scope.financeHistoryData = results.data;
 			 }, function (error) {
      
    			 });
    		}*/

				$scope.$watchGroup(['financeRequest.alloyExtra', 'financeRequest.qcRejectionImpactPer', 'financeRequest.qcRejectionImpactRs', 'financeRequest.smsProcessExtra','financeRequest.plateMillYieldImpact', 'financeRequest.plateMillNco', 'financeRequest.plateMillNcoImpact', 'financeRequest.plateMillRejection','financeRequest.plateMillRejectionImpact', 'financeRequest.processCharges'], function () {
                $scope.convertToRowValues();
				$scope.resultSum = $scope.sumProduct($scope.rowValues); 
				
                $scope.financeRequest.totalRevisedCost=$scope.resultSum;
 				}, true); 
			
			$scope.rowValues = [];

            $scope.convertToRowValues = function () {
                $scope.rowValues = [];

                for (var key in $scope.financeRequest) {
					if(key ==="segment"){}
					else if(key ==="subSegment") {}
					else if(key ==="remarks") {}
					else if(key ==="status") {}
					else if(key ==="totalRevisedCost") {}
					else if(key ==="id") {}
					else if(key ==="qualityRequestFkId") {}
					else if(key ==="qualityRequestFkId") {}
					else if(key ==="plateMillNco") {}
					else if(key ==="qcRejectionImpactPer") {}
					
					else if(key ==="plateMillRejection") {}
					else{
						 if ($scope.financeRequest.hasOwnProperty(key)) {
                        //$scope.rowValues.push($scope.financeRequest[key]);
						var value = $scope.financeRequest[key];

                // Check if the value is not null and not undefined
                if (value !== null && value !== undefined && typeof value !== 'object') {
                    $scope.rowValues.push(parseInt(value, 10));
                }
                    }
				}
                }
			//console.log("fin---> "+JSON.stringify($scope.rowValues));
            };

	$scope.sumProduct = function (array1) {
			

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