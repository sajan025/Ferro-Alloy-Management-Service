var tdcViewApp = angular.module('tdcViewApp',['ngSanitize','angularUtils.directives.dirPagination']);
tdcViewApp.controller ('ApproveRejectHodPpcController', function($scope,$filter,$compile,$parse,$http,$window,ApproveRejectHodPpcService,TdcInitiatorViewService){
	
		$scope.tdcObj={};
		$scope.fileList=[];
		$scope.fileList.push({});
		$scope.comments='';
		$scope.files=[];
		  toastr.options.timeOut = 1000;
			toastr.options.fadeOut = 1000;
			toastr.options.onHidden = function(){
			  // this will be executed after fadeout, i.e. 2secs after notification has been show
			 /* window.location.reload();*/
				$window.location.href = '../tdcApproverHodPpc/dashboard';
			};
	$scope.inIt = function() {
		//alert("dss");
		
		//alert($window.localStorage.getItem("tdcObj"))
		//for edit by help of local storage
		if($window.localStorage.getItem("tdcObj")!=null){
			
			$scope.tdcObj=angular.fromJson($window.localStorage.getItem("tdcObj"));
			//alert($scope.tdcObj);
			
		//$window.localStorage.removeItem("tdcObj");
		
		////////////
		var approverObj={};
		approverObj['tdcId']=$scope.tdcObj.tdcPkId;
		$scope.tdcPkId=null;		
		$scope.tdcPkId=angular.fromJson($window.localStorage["approverObj.tdcId"])
		$scope.getTdcData(approverObj.tdcId);
		$scope.getTdcLadleData(approverObj.tdcId);
		$scope.getTdcMechanicalDtoData(approverObj.tdcId);
		$scope.getTdcOtherDetailsData(approverObj.tdcId);
		/////////////
		
		$scope.getTdcApprovers(approverObj.tdcId);
		}

	}
	
	///////////////////
	
	
	  //////////////////////////////////
	  $scope.getTdcApprovers= function(tdcId) {
		//alert("sadadweqweqw"+tdcId)
		  
		  $scope.level1={};
			$scope.level2={};
			$scope.level3={};
			$scope.level4={};
			//alert(tdcId);
			ApproveRejectHodPpcService.getTdcApprovers(tdcId).then(function(result) {
				//alert(JSON.stringify(result));
				$scope.tdcApprovalList = result;
				for(var i in $scope.tdcApprovalList){
					if($scope.tdcApprovalList[i].approverLevel==2){
						$scope.level2=$scope.tdcApprovalList[i];
						
					}
					if($scope.tdcApprovalList[i].approverLevel==3){
						//alert("hello")
						$scope.level3=$scope.tdcApprovalList[i];
						
					}
					if($scope.tdcApprovalList[i].approverLevel==4){
						$scope.level4=$scope.tdcApprovalList[i];
					}
				}
			});
		}
	///////////////
	$scope.getTdcData = function(tdcPkId) {
		TdcInitiatorViewService.getTdcData(tdcPkId).then(function(result) {
		$scope.tdc = result;
	  });
	}
	$scope.masterChemicalList=[];
	$scope.masterProductList=[];
	$scope.getTdcLadleData= function(tdcPkId) {
		TdcInitiatorViewService.getTdcLadleData(tdcPkId).then(function(result) {
			$scope.obj={};
			$scope.obj=result;			
			for(var index in $scope.obj.tdcChemistryList){
				var newObj={};
				
				newObj['elements']=$scope.obj.tdcChemistryList[index].masterElementChemistryFkId.elements;
				newObj['masterChemicalPkId']=$scope.obj.tdcChemistryList[index].masterElementChemistryFkId.masterChemicalPkId;
				$scope.masterChemicalList.push(newObj);
			}
			console.log(JSON.stringify($scope.masterChemicalList));
			for(var index in $scope.obj.tdcChemistryProductList){
				var proObj={};				
				proObj['elements']=$scope.obj.tdcChemistryProductList[index].masterElementChemistryFkId.elements;
				proObj['masterChemicalPkId']=$scope.obj.tdcChemistryProductList[index].masterElementChemistryFkId.masterChemicalPkId;
		        $scope.masterProductList.push(proObj);
			}					
			$scope.max=$scope.tdcLadleChemistry.masterLadleChemistryFkId;
			$scope.min=$scope.tdcLadleChemistry.masterLadleChemistryFkId;
	    });
	}
	$scope.getTdcMechanicalDtoData= function(tdcPkId) {
		TdcInitiatorViewService.getTdcMechanicalDtoData(tdcPkId).then(function(result) {
			$scope.tdcMechanicalDto=result;
	    });
	}
	$scope.getTdcOtherDetailsData= function(tdcPkId) {
		TdcInitiatorViewService.getTdcOtherDetailsData(tdcPkId).then(function(result) {
			$scope.tdcOtherDetails=result;
			console.log(JSON.stringify($scope.tdcOtherDetails))
	    });
	}
////////////////////////////
	$scope.$on("seletedFile", function (event, args) { 
	//	alert("dir")
	    $scope.$apply(function () {  
	        //add the file object to the scope's files collection  
	    	$scope.files.push(args.file);  
	    });  
	});
	
	
	$scope.addRowFile = function() {
		if($scope.fileList.length<=15){
		$scope.fileList.push({});
		}
		else{
			alert("Limit reached");
		}
	}
	
	$scope.removeRowFile=function(index){
		if($scope.fileList.length >1){
			$scope.fileList.splice(index, 1);
		}
		
		else{
			alert("Atleast one row is required");
		}
		$scope.fileList.splice(index,1)
	}
	
	
	$scope.approve = function(requestString) {
		var approverObj={};
		approverObj['approverComments']=$scope.comments;
		approverObj['status']=requestString;
		approverObj['tdcId']=$scope.tdcObj.tdcPkId;
		//alert($scope.tdcObj.tdcPkId)
		console.log($scope.tdcObj)
		ApproveRejectHodPpcService.saveApproveReject(approverObj,$scope.files).then(function(result) {
 		   if(result!=null&&result!=undefined&&result!=''){
 			  toastr.success("Action Taken successfully.<br /><br />Thank You!");	
				$uibModalInstance.dismiss();
				}
 		   else{
				toastr["success"]("Not successfull.<br /><br />Thank You!");
				$uibModalInstance.dismiss();
 		   }
				//$scope.message = 'CostCenter Added Successfully';
			});
	}
	
	
	
	
});
	

tdcViewApp.directive('uploadFiles', function () {  
	    return {  
	        scope: true,        //create a new scope  
	        link: function (scope, el, attrs) {  
	            el.bind('change', function (event) {  
	                var files = event.target.files;  
	                //iterate files since 'multiple' may be specified on the element  
	                for (var i = 0; i < files.length; i++) {  
	                    //emit event upward  
	                    scope.$emit("seletedFile", { file: files[i] });  
	                }  
	            });  
	        }  
	    };  
	})

