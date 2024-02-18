'use strict';
app.controller('qualityController', ['$scope','qualityService','authService', function ($scope, qualityService,authService) {

	/*toastr.options = {
		extendedTimeOut: 1000,
		timeOut: 5000,
		positionClass: 'toast-top-center',
	}*/

    $scope.data = [];
	$scope.currentPage = 1;
	$scope.pageNo=null;
	$scope.sortBy=null;
	$scope.productName=null;
	$scope.gradeName=null;
	$scope.internalGradename=null;
	
	$scope.qualityRequestDto={};
	$scope.qualityRequestDto.compositionList=[{}];
	
	$scope.init = function() {
		$scope.ShowSpinnerStatus = false;
		$scope.getProductList();
	}
	console.log("--"+JSON.stringify($scope.qualityRequestDto));
	$scope.logout=function(){
	authService.logOut();
		
	location.href = '/Ferro-Alloy/login';

   
	}
	
	$scope.setStatusValue=function(i){
		if(i==1){
			$scope.qualityRequestDto.status='Draft'
			$scope.saveProduct();
		}else if(i==2){
			$scope.qualityRequestDto.status='Submit'
		}
	}
	$scope.saveProduct=function(){
		console.log(JSON.stringify($scope.qualityRequestDto))
		
			$scope.qualityRequestDto.compositionList[0].material='C';
			$scope.qualityRequestDto.compositionList[1].material= 'Mn';
			$scope.qualityRequestDto.compositionList[2].material ='Si';
			$scope.qualityRequestDto.compositionList[3].material= 'Cr';
			
			$scope.qualityRequestDto.compositionList[4].material ='V';
			$scope.qualityRequestDto.compositionList[5].material='Nb';
			$scope.qualityRequestDto.compositionList[6].material= 'Ni';
			$scope.qualityRequestDto.compositionList[7].material='Mo';
			
			$scope.qualityRequestDto.compositionList[8].material= 'Cu';
			$scope.qualityRequestDto.compositionList[9].material='Ti';
			$scope.qualityRequestDto.compositionList[10].material= 'Ca';
			$scope.qualityRequestDto.compositionList[11].material= 'Al';
			
			$scope.qualityRequestDto.compositionList[12].material= 'B';
			$scope.qualityRequestDto.compositionList[13].material= 'P';
			$scope.qualityRequestDto.compositionList[14].material= 'N';
	
		console.log(JSON.stringify($scope.qualityRequestDto))
		qualityService.saveProduct($scope.qualityRequestDto).then(function (results) {
		//console.log(JSON.stringify(results))
		//show success msg using toster
		// make it not dissappear
		//redirect to dashboard and set qualityRequestDto=null
		location.reload();
		$scope.qualityRequestDto={};
		$scope.qualityRequestDto.compositionList=[{}];
		
    }, function (error) {
        alert("Somthing went wrong. ")
    });
	}
	
	
	$scope.fatchRequestById=function(id){
		 qualityService.fatchRequestById(id).then(function (results) {
			$scope.qualityRequestDto=results.data;
	//console.log("fatchRequestById==>>"+JSON.stringify($scope.qualityRequestDto))
    }, function (error) {
        alert("Somthing went wrong. ")
    });
	}
	
	$scope.getProductList=function(pageNo,sortBy,productName,gradeName,internalGradename){
	
	if(pageNo!=null)$scope.pageNo=pageNo-1;
	if(sortBy!=null)$scope.sortBy=sortBy;
	if(productName!=null)$scope.productName=productName;
	if(gradeName!=null)$scope.gradeName=gradeName;
	if(internalGradename!=null)$scope.internalGradename=internalGradename;
    qualityService.getProductList($scope.pageNo,$scope.sortBy,$scope.productName,$scope.gradeName,$scope.internalGradename).then(function (results) {
	//console.log(JSON.stringify(results))
        $scope.data = results.data.content;
 		$scope.totalPages = results.data.totalElements;

		$scope.productName=null;
		$scope.gradeName=null;
		$scope.internalGradename=null;
    }, function (error) {
        alert("Somthing went wrong. ")
    });
}

$scope.clearFields=function(){
	$scope.qualityRequestDto={};
	$scope.qualityRequestDto.compositionList=[{}];
}
$scope.findProduct=function(grade){
		 qualityService.findProduct(grade).then(function (results) {
			if(results.data.content[0]!=null){
				$scope.qualityRequestDto=results.data.content[0];
				$scope.qualityRequestDto.id=null;
				for(var i=0;i<$scope.qualityRequestDto.compositionList.length;i++){
					$scope.qualityRequestDto.compositionList[i].id=null;
				}
				//console.log("In ==>>"+JSON.stringify($scope.qualityRequestDto))
			}
				
    }, function (error) {
       alert("Somthing went wrong. ")
    });
	}


$scope.searchResult=[];
 // Fetch data
   $scope.fetchMasterGrade = function(key,index){
     
	qualityService.fetchMasterGrade(key).then(function(result) {
			$scope.searchResult=result.data;
			if(result.data.length==0){
				alert(" Grade Does Not Exist.");
				 $scope.qualityRequestDto.externalGrade=null;
			}
			//console.log("list "+JSON.stringify($scope.searchResult));
				});
	 }

  $scope.setValue = function(index,$event){
      $scope.qualityRequestDto.externalGrade = $scope.searchResult[index].gradeFamily;
   	$scope.findProduct($scope.searchResult[index].gradeFamily);
	$scope.searchResult = {};
      $event.stopPropagation();
   }

   $scope.searchboxClicked = function($event){
      $event.stopPropagation();
   }

   $scope.containerClicked = function(){
      $scope.searchResult = [];
   }

	
	$scope.searchResult1=[];
 // Fetch data
   $scope.fetchMasterInternalGrade = function(key,index){
     
	qualityService.fetchMasterInternalGrade(key).then(function(result) {
			$scope.searchResult1=result.data;
			if(result.data.length==0){
				alert(" Grade Does Not Exist.");
				 $scope.qualityRequestDto.internalGrade=null;
			}
			console.log("list "+JSON.stringify($scope.searchResult1));
				});
	 }

  $scope.setValue1 = function(index,$event){
      $scope.qualityRequestDto.internalGrade = $scope.searchResult1[index].intGradeName;
   
	$scope.searchResult1 = {};
      $event.stopPropagation();
   }

   $scope.searchboxClicked1 = function($event){
      $event.stopPropagation();
   }

   $scope.containerClicked1 = function(){
      $scope.searchResult1 = [];
   }
  /*$scope.gethistoryId=function(id){
   localStorage.setItem("qualityPkid", id);
  }
  */

}]);
app.directive('decimalInput', function() {
      return {
        require: 'ngModel',
        link: function(scope, element, attrs, ngModelCtrl) {
          ngModelCtrl.$parsers.push(function(inputValue) {
            // This function will be called when the user types in the input field

            // Remove any non-numeric or non-decimal characters
            var transformedInput = inputValue.replace(/[^0-9.]/g, '');
			// Limit the input to 3 decimal places
	        var decimalParts = transformedInput.split('.');
	        if (decimalParts.length > 1) {
	          decimalParts[1] = decimalParts[1].slice(0, 3);
	          transformedInput = decimalParts.join('.');
	        }
            // Update the model value
            ngModelCtrl.$setViewValue(transformedInput);
            ngModelCtrl.$render();

            return transformedInput;
          });
        }
      };
    });