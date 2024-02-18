'use strict';
app.controller('smsController', ['$scope', 'smsService','qualityService','authService', function ($scope, smsService,qualityService,authService) {

    $scope.data = [];
	$scope.qualityRequestDto={};
	$scope.currentPage = 1;
	$scope.pageNo=null;
	$scope.sortBy=null;
	$scope.productName=null;
	$scope.gradeName=null;
	$scope.internalGradename=null;
	//$scope.ferroAlloySpecification={};
	/*var convertedValuesMap = new Map();*/
	
	$scope.init = function() {
		$scope.ShowSpinnerStatus = false;
		$scope.getProductList();
		$scope.getFerroAlloySpecificationList();
		
	}
	
	 $scope.smsValues = {
				id:null,
                segment: "",
                subSegment: "",
				processRoute:"",
				smsRemark:"",
				cokePetroleumCalcined:null,
				graphiteFines:null,
				silicoManganeseHP:null,
				silicoManganesExtraLowC:null,
				silicoManganeseLowPhos:null,
				manganeseMetalBriqutes:null,
				ferroManganeseHC:null,
				ferroMangansesLC:null,
				ferroSilicon:null,
				aluminiumDross:null,
				ingotAL:null,
				wireAL:null,
				wire16MM:null,
				caWirePure:null,
				copperCathode:null,
				nickelMetal:null,
				ferroChromeLC:null,
				ferroChromeCR:null,
				ferroNiobium:null,
				wireFETICored:null,
				ferroVanadium:null,
				nitrovan:null,
				ferroMolybdenum:null,
				ferroBoron:null,
				shotsAluminium:null,
				ferroAluminium:null,
				ferroPhosphorous:null,
				ferroTitaninum:null,
				synSlagPreMelted:null,
				slagFluidiser:null,
				wire13MM:null,
				alCubes:null,
				calcinedLime:null,
				manganeseCoreWire:null,
				totalAlloyCostPerTon:null
			
            };
			$scope.constants = [88065,	112200,	86110,	70513,	66363,	217665,	84460,	146925,	137190,	7725,	249900,	253470,	200940,	435540,	773160,	2636680,	275400,	115770,	2739720,	354692,	1712580,	1469820,	3828825,	366180,	204346,	125371,	41311,	428590,	22557,	23278,	158768,	221493,	6695,208287];
            $scope.divisor1 = 1000;
            $scope.divisor2 = 0.975;

            $scope.rowValues = [];

            $scope.convertToRowValues = function () {
                $scope.rowValues = [];

                for (var key in $scope.smsValues) {
					if(key ==="segment"){}
					else if(key ==="subSegment") {}
					else if(key ==="processRoute") {}
					else if(key ==="smsRemark") {}
					else if(key ==="totalAlloyCostPerTon") {}
					else if(key ==="id") {}
					else if(key ==="qualityRequestFkId") {}
					else{
						 if ($scope.smsValues.hasOwnProperty(key)) {
                        $scope.rowValues.push($scope.smsValues[key]);
                    }
				}
                }
			//console.log(JSON.stringify($scope.rowValues));
            };
            $scope.$watch('smsValues', function (newValue, oldValue) {
				$scope.calculateValueForC();
                $scope.convertToRowValues();
				var result = $scope.sumProduct($scope.rowValues, $scope.constants) / $scope.divisor1; 
				$scope.finalResult= result/ $scope.divisor2;
                $scope.smsValues.totalAlloyCostPerTon=$scope.roundToInteger($scope.finalResult);
 				}, true); 
	
		$scope.sumProduct = function (array1, array2) {
			//alert("array1"+array1.length+" array2 "+array2.length)
                if (array1.length !== array2.length) {
                    throw new Error('Arrays must have the same length');
                }

                var sum = 0;
                for (var i = 0; i < array1.length; i++) {
                    sum += array1[i] * array2[i];
                }

                return sum;
            };
     // Function to round a number to an integer
            $scope.roundToInteger = function (number) {
                return Math.round(number);
            };  

$scope.fatchRequestById=function(id){
		 $scope.getSmsData(id);
		 qualityService.fatchRequestById(id).then(function (results) {
			$scope.qualityRequestDto=results.data;
	//console.log("fatchRequestById==>>"+JSON.stringify($scope.qualityRequestDto))
    }, function (error) {
        alert("Somthing went wrong. ")
    });
	}

	$scope.saveSmsData=function(){
		$scope.smsValues.qualityRequestFkId=$scope.qualityRequestPkId;
		//console.log(JSON.stringify($scope.smsValues))
		smsService.saveSmsData($scope.smsValues).then(function (results) {
		//console.log(JSON.stringify(results))
		
		location.reload();
		$scope.smsValues = {};
		
    }, function (error) {
        alert("Somthing went wrong. ")
    });
	}
	
	$scope.fatchSmsData=function(qualityRequestPkId){
		smsService.fatchSmsData(qualityRequestPkId).then(function (results) {
		//console.log(JSON.stringify(results))
		if(results.data.data!==null){
			$scope.smsValues = results.data.data;
		}
		
		
    }, function (error) {
        //alert(error.data.message)
    });
	}
	
	$scope.setQualityData=function(pkId,product,grade,internalGrade){
		//alert(product)
		$scope.qualityRequestPkId=pkId;
		$scope.smsProduct1=product;
		$scope.smsGrade1=grade;
		$scope.smsInternalGrade1=internalGrade;
		$scope.fatchSmsData($scope.qualityRequestPkId);
		$scope.fatchRequestById($scope.qualityRequestPkId)
	}
	
	$scope.getSmsData=function(pkId,product,grade,internalGrade){
		$scope.smsProduct=product;
		$scope.smsGrade=grade;
		$scope.smsInternalGrade=internalGrade;
		$scope.fatchSmsData(pkId);
	}
	
	$scope.reSetSmsData=function(){
		location.reload();
	}
	$scope.getProductList=function(pageNo,sortBy,productName,gradeName,internalGradename){
	if(pageNo!=null)$scope.pageNo=pageNo-1;
	if(sortBy!=null)$scope.sortBy=sortBy;
	if(productName!=null)$scope.productName=productName;
	if(gradeName!=null)$scope.gradeName=gradeName;
	if(internalGradename!=null)$scope.internalGradename=internalGradename;
    smsService.getProductList1($scope.pageNo,$scope.sortBy,$scope.productName,$scope.gradeName,$scope.internalGradename).then(function (results) {
	//console.log("---->>"+JSON.stringify(results))
        $scope.data = results.data.content;
 		$scope.totalPages = results.data.totalElements;

		$scope.productName=null;
		$scope.gradeName=null;
		$scope.internalGradename=null;
    }, function (error) {
        alert("Somthing went wrong. ")
    });
}


$scope.materialMap={};
$scope.getFerroAlloySpecificationList=function(){
		smsService.getFerroAlloySpecificationList().then(function (results) {
		//console.log("getFerroAlloySpecificationList "+JSON.stringify(results.data))
		$scope.materialMap = results.data;
		//console.log("materialMap "+JSON.stringify($scope.materialMap))
// Sample data for ferroAlloySpecification and calculationSheet
$scope.ferroAlloySpecification = {
    C19: getMaterialCodeValue("3000000954"),
    C36: getMaterialCodeValue("3000000902"),
    C2: getMaterialCodeValue("2000000083"),
    C4: getMaterialCodeValue("2000000084"),
    C5: getMaterialCodeValue("2000000044"),
    C6: getMaterialCodeValue("2000002000"),
    C7: getMaterialCodeValue("2000000045"),
    C9: getMaterialCodeValue("2000001280"),
    C10: getMaterialCodeValue("2000000042"),
    C11: getMaterialCodeValue("2000000050"),
    C12: getMaterialCodeValue("2000001580"),
    C14: getMaterialCodeValue("2000000047"),
    C15: getMaterialCodeValue("2000000052"),
    C16: getMaterialCodeValue("2000000405"),
    C17: getMaterialCodeValue("2000000041"),
    C20: getMaterialCodeValue("2000000049"),
    C23: getMaterialCodeValue("2000000048"),
    C24: getMaterialCodeValue("2000001949"),
    C27: getMaterialCodeValue("2000001831"),
    C28: getMaterialCodeValue("2000000051"),
    C30: getMaterialCodeValue("2000000040"),
};

$scope.ferroAlloySpecificationForMn = {
    D2: getMaterialCodeValueByMn("2000000083"),
    D4: getMaterialCodeValueByMn("2000000084"),
    D6: getMaterialCodeValueByMn("2000002000"),
    D8: getMaterialCodeValueByMn("2000000001"),
    D5: getMaterialCodeValueByMn("2000000044"),
    D7: getMaterialCodeValueByMn("2000000045"),
    D27: getMaterialCodeValueByMn("2000001831")
};

$scope.ferroAlloySpecificationForSi = {
    G2: getMaterialCodeValueBySi("2000000083"),
    G4: getMaterialCodeValueBySi("2000000084"),
    G6: getMaterialCodeValueBySi("2000002000"),
    G8: getMaterialCodeValueBySi("2000000001"),
    G5: getMaterialCodeValueBySi("2000000044"),
    G7: getMaterialCodeValueBySi("2000000045"),
    G11: getMaterialCodeValueBySi("2000000050"),
    G12: getMaterialCodeValueBySi("2000001580"),
    G10: getMaterialCodeValueBySi("2000000042"),
    G9: getMaterialCodeValueBySi("2000001280"),
    G23: getMaterialCodeValueBySi("2000000048"),
    G16: getMaterialCodeValueBySi("2000000405"),
    G15: getMaterialCodeValueBySi("2000000052"),
    G24: getMaterialCodeValueBySi("2000001949"),
    G14: getMaterialCodeValueBySi("2000000047"),
    G17: getMaterialCodeValueBySi("2000000041"),
    G27: getMaterialCodeValueBySi("2000001831"),
    G29: getMaterialCodeValueBySi("2000000019"),
    G28: getMaterialCodeValueBySi("2000000051")
};

$scope.ferroAlloySpecificationForCr = {
    L9: getMaterialCodeValueByCr("2000001280"),
    L10: getMaterialCodeValueByCr("2000000042")
};

$scope.ferroAlloySpecificationForV = {
    N15: getMaterialCodeValueByV("2000000052")
};

$scope.ferroAlloySpecificationForNb = {
    R23: getMaterialCodeValueByNb("2000000048")
};

$scope.ferroAlloySpecificationForNi = {
    K21: getMaterialCodeValueByNi("2000000069")
};

$scope.ferroAlloySpecificationForMo = {
    M14: getMaterialCodeValueByMo("2000000047")
};

$scope.ferroAlloySpecificationForCu = {
    I22: getMaterialCodeValueByCu("2000000103")
};

$scope.ferroAlloySpecificationForB = {
    T17: getMaterialCodeValueByB("2000000041")
};

$scope.ferroAlloySpecificationForN = {
    U27: getMaterialCodeValueByN("2000001831")
};

$scope.ferroAlloySpecificationForTi = {
    Q16: getMaterialCodeValueByTi("2000000405"),
    Q28: getMaterialCodeValueByTi("2000000051")
};

$scope.ferroAlloySpecificationForCa = {
    J12: getMaterialCodeValueByCa("2000001580"),
    J26: getMaterialCodeValueByCa("2000001561"),
    J31: getMaterialCodeValueByCa("2000000096")
};

$scope.ferroAlloySpecificationForAi = {
    H25: getMaterialCodeValueByAi("2000000055"),
    H18: getMaterialCodeValueByAi("2000000099"),
    H16: getMaterialCodeValueByAi("2000000405"),
    H11: getMaterialCodeValueByAi("2000000050"),
    H12: getMaterialCodeValueByAi("2000001580"),
    H13: getMaterialCodeValueByAi("2000000096"),
    H28: getMaterialCodeValueByAi("2000000051"),
    H23: getMaterialCodeValueByAi("2000000048"),
    H32: getMaterialCodeValueByAi("2000000104"),
    H30: getMaterialCodeValueByAi("2000000040"),
    H29: getMaterialCodeValueByAi("2000000019"),
    G27: getMaterialCodeValueByAi("2000001831")
};

$scope.ferroAlloySpecificationForP = {
    F19: getMaterialCodeValueByP("3000000954"),
    F2: getMaterialCodeValueByP("2000000083"),
    F6: getMaterialCodeValueByP("2000002000"),
    F4: getMaterialCodeValueByP("2000000084"),
    F8: getMaterialCodeValueByP("2000000001"),
    F5: getMaterialCodeValueByP("2000000044"),
    F7: getMaterialCodeValueByP("2000000045"),
    F11: getMaterialCodeValueByP("2000000050"),
    F18: getMaterialCodeValueByP("2000000099"),
    F12: getMaterialCodeValueByP("2000001580"),
    F10: getMaterialCodeValueByP("2000000042"),
    F9: getMaterialCodeValueByP("2000001280"),
    F23: getMaterialCodeValueByP("2000000048"),
    F15: getMaterialCodeValueByP("2000000052"),
    F14: getMaterialCodeValueByP("2000000047"),
    F17: getMaterialCodeValueByP("2000000041"),
    F20: getMaterialCodeValueByP("2000000049"),
    F28: getMaterialCodeValueByP("2000000051"),
    F27: getMaterialCodeValueByP("2000001831")
};



$scope.calculationSheet = {
    L6: $scope.smsValues.silicoManganeseHP,
    M6: $scope.smsValues.silicoManganesExtraLowC,
    N6: $scope.smsValues.silicoManganeseLowPhos,
    P6: $scope.smsValues.ferroManganeseHC,
    Q6: $scope.smsValues.ferroMangansesLC,
    R6: $scope.smsValues.ferroSilicon,
    V6: $scope.smsValues.wire16MM,
    Z6: $scope.smsValues.ferroChromeLC,
    AA6: $scope.smsValues.ferroChromeCR,
    AB6: $scope.smsValues.ferroNiobium,
    AC6: $scope.smsValues.wireFETICored,
    AD6: $scope.smsValues.ferroVanadium,
    AE6: $scope.smsValues.nitrovan,
    AF6: $scope.smsValues.ferroMolybdenum,
    AG6: $scope.smsValues.ferroBoron,
    AJ6: $scope.smsValues.ferroPhosphorous,
    AK6: $scope.smsValues.ferroTitaninum,
    AQ6: $scope.smsValues.manganeseCoreWire,
    AI6: $scope.smsValues.ferroAluminium,
    BL6: 0.06,
    BM2: 0.90,
};


		
    }, function (error) {
        //alert(error.data.message)
    });
	}
	
	// Function to convert percent string to numerical value
function convertPercentToValue(percentString) {
    if (percentString != null && percentString.endsWith("%")) {
        var valueStr = percentString.substring(0, percentString.length - 1);
        return parseFloat(valueStr) / 100.0;
    }
    return 0.0; 
}

	
	function getMaterialCodeValue(materialCode){
	$scope.valuesForMaterialCode0 = getValuesByMaterialCode(materialCode);
	return convertPercentToValue($scope.valuesForMaterialCode0[0].c);
	}
	function getMaterialCodeValueByMn(materialCode){
	$scope.valuesForMaterialCode0 = getValuesByMaterialCode(materialCode);
	return convertPercentToValue($scope.valuesForMaterialCode0[0].mn);
	}
	
	function getMaterialCodeValueBySi(materialCode){
	$scope.valuesForMaterialCode0 = getValuesByMaterialCode(materialCode);
	return convertPercentToValue($scope.valuesForMaterialCode0[0].si);
	}
	
	function getMaterialCodeValueByCr(materialCode){
	$scope.valuesForMaterialCode0 = getValuesByMaterialCode(materialCode);
	return convertPercentToValue($scope.valuesForMaterialCode0[0].cr);
	}
	
	function getMaterialCodeValueByV(materialCode){
	$scope.valuesForMaterialCode0 = getValuesByMaterialCode(materialCode);
	return convertPercentToValue($scope.valuesForMaterialCode0[0].v);
	}
	
	function getMaterialCodeValueByNb(materialCode){
	$scope.valuesForMaterialCode0 = getValuesByMaterialCode(materialCode);
	return convertPercentToValue($scope.valuesForMaterialCode0[0].nb);
	}
	
	function getMaterialCodeValueByNi(materialCode){
	$scope.valuesForMaterialCode0 = getValuesByMaterialCode(materialCode);
	return convertPercentToValue($scope.valuesForMaterialCode0[0].ni);
	}
	
	function getMaterialCodeValueByMo(materialCode){
	$scope.valuesForMaterialCode0 = getValuesByMaterialCode(materialCode);
	return convertPercentToValue($scope.valuesForMaterialCode0[0].mo);
	}
	
	function getMaterialCodeValueByCu(materialCode){
	$scope.valuesForMaterialCode0 = getValuesByMaterialCode(materialCode);
	return convertPercentToValue($scope.valuesForMaterialCode0[0].cu);
	}
	
	function getMaterialCodeValueByB(materialCode){
	$scope.valuesForMaterialCode0 = getValuesByMaterialCode(materialCode);
	return convertPercentToValue($scope.valuesForMaterialCode0[0].b);
	}
	
	function getMaterialCodeValueByN(materialCode){
	$scope.valuesForMaterialCode0 = getValuesByMaterialCode(materialCode);
	return convertPercentToValue($scope.valuesForMaterialCode0[0].n);
	}
	
	function getMaterialCodeValueByTi(materialCode){
	$scope.valuesForMaterialCode0 = getValuesByMaterialCode(materialCode);
	return convertPercentToValue($scope.valuesForMaterialCode0[0].ti);
	}
	
	function getMaterialCodeValueByCa(materialCode){
	$scope.valuesForMaterialCode0 = getValuesByMaterialCode(materialCode);
	return convertPercentToValue($scope.valuesForMaterialCode0[0].ca);
	}
	
	
	function getMaterialCodeValueByAi(materialCode){
	$scope.valuesForMaterialCode0 = getValuesByMaterialCode(materialCode);
	return convertPercentToValue($scope.valuesForMaterialCode0[0].al);
	}
	
	function getMaterialCodeValueByP(materialCode){
	$scope.valuesForMaterialCode0 = getValuesByMaterialCode(materialCode);
	return convertPercentToValue($scope.valuesForMaterialCode0[0].p);
	}
	
	function getValuesByMaterialCode(materialCode) {
    return $scope.materialMap[materialCode] || [];
}
	
	$scope.logout=function(){
	authService.logOut();
	location.href = '/Ferro-Alloy/login';
	}
	
	// calculation for P
	$scope.calculateResultForP = function(J7, L7, M7, N7, O7, P7, Q7, R7, U7, V7, Z7, AA7, AB7, AD7, AF7, AG7, AJ7, AK7, AQ7, BZ2) {
	    var numerator = (
	        J7 * $scope.ferroAlloySpecificationForP.F19 +
	        L7 * $scope.ferroAlloySpecificationForP.F2 +
	        M7 * $scope.ferroAlloySpecificationForP.F6 +
	        N7 * $scope.ferroAlloySpecificationForP.F4 +
	        O7 * $scope.ferroAlloySpecificationForP.F8 +
	        P7 * $scope.ferroAlloySpecificationForP.F5 +
	        Q7 * $scope.ferroAlloySpecificationForP.F7 +
	        R7 * $scope.ferroAlloySpecificationForP.F11 +
	        U7 * $scope.ferroAlloySpecificationForP.F18 +
	        V7 * $scope.ferroAlloySpecificationForP.F12 +
	        Z7 * $scope.ferroAlloySpecificationForP.F10 +
	        AA7 * $scope.ferroAlloySpecificationForP.F9 +
	        AB7 * $scope.ferroAlloySpecificationForP.F23 +
	        AD7 * $scope.ferroAlloySpecificationForP.F15 +
	        AF7 * $scope.ferroAlloySpecificationForP.F14 +
	        AG7 * $scope.ferroAlloySpecificationForP.F17 +
	        AJ7 * $scope.ferroAlloySpecificationForP.F20 +
	        AK7 * $scope.ferroAlloySpecificationForP.F28 +
	        AQ7 * $scope.ferroAlloySpecificationForP.F27
	    ) / 10;
	
	    var result = (numerator + 0.007) * BZ2;
	
	    return result;
	};
	
	
	// calculation for Ai
	$scope.calculateResultForAi = function(T7, U7, AC7, R7, V7, AN7, AK7, AB7, AO7, AI7, AH7, AQ7, BX2) {
	    var numerator = (
	        T7 * $scope.ferroAlloySpecificationForAi.H25 +
	        U7 * $scope.ferroAlloySpecificationForAi.H18 +
	        AC7 * $scope.ferroAlloySpecificationForAi.H16 +
	        R7 * $scope.ferroAlloySpecificationForAi.H11 +
	        V7 * $scope.ferroAlloySpecificationForAi.H12 +
	        AN7 * $scope.ferroAlloySpecificationForAi.H13 +
	        AK7 * $scope.ferroAlloySpecificationForAi.H28 +
	        AB7 * $scope.ferroAlloySpecificationForAi.H23 +
	        AO7 * $scope.ferroAlloySpecificationForAi.H32 +
	        AI7 * $scope.ferroAlloySpecificationForAi.H30 +
	        AH7 * $scope.ferroAlloySpecificationForAi.H29 +
	        AQ7 * $scope.ferroAlloySpecificationForAi.G27
	    ) / 10;
	
	    var result = numerator * BX2;
	
	    if (result === 0) {
	        return 0; 
	    } else {
	        return result; // Return the calculated result
	    }
	};
	
	// calculation for Ca
	$scope.calculateResultForCa = function(V7, W7, AN7, BW2) {
	    var numerator = (V7 * $scope.ferroAlloySpecificationForCa.J12 + W7 * $scope.ferroAlloySpecificationForCa.J26 + AN7 * $scope.ferroAlloySpecificationForCa.J31) / 10;
	    var result = numerator * BW2;
	
	    if (result === 0) {
	        return 0; 
	    } else {
	        return result; // Return the calculated result
	    }
	};
	// calculation for Ti
	$scope.calculateResultForTi = function(AC7, AK7, BV2) {
	    var numerator = (AC7 * $scope.ferroAlloySpecificationForTi.Q16 + AK7 * $scope.ferroAlloySpecificationForTi.Q28) / 10;
	    var result = numerator * BV2;
	
	    if (result === 0) {
	        return 0; 
	    } else {
	        return result; // Return the calculated result
	    }
	};
	// calculation for N
	$scope.calculateResultForN = function(AQ7, CA2) {
	    var result = (AQ7 * $scope.ferroAlloySpecificationForN.U27) / 10 * CA2 + 0.0045;
	    return result;
	};
	
	// calculation for B
	$scope.calculateResultForB = function(AG7, BY2) {
	    var result = (AG7 * $scope.ferroAlloySpecificationForB.T17) / 10 * BY2;
	
	    if (result === 0) {
	        return 0; // Return empty string if the result is 0
	    } else {
	        return result; // Return the calculated result
	    }
	};
	
	// calculation for Cu
	$scope.calculateResultForCu = function(X7, BU2) {
	    var result = (X7 * $scope.ferroAlloySpecificationForCu.I22) / 10 * BU2;
	//console.log(X7+ "- BU2 "+ BU2+" - "+$scope.ferroAlloySpecificationForCu.I22)
	//console.log((X7 * $scope.ferroAlloySpecificationForCu.I22) / 10 * BU2)
	    if (result === 0) {
	        return 0; 
	    } else {
	        return result; // Return the calculated result
	    }
	};
	
	// calculation for Mo
	$scope.calculateResultForMo = function(AF7, BT2) {
	    var result = (AF7 * $scope.ferroAlloySpecificationForMo.M14) / 10 * BT2;
	
	    if (result === 0) {
	        return 0; 
	    } else {
	        return result; // Return the calculated result
	    }
	};
	
	// calculation for Ni
	$scope.calculateResultForNi = function(Y7, BS2) {
	    var result = (Y7 * $scope.ferroAlloySpecificationForNi.K21) / 10 * BS2;
	
	    if (result === 0) {
	        return 0; 
	    } else {
	        return result; // Return the calculated result
	    }
	};
	
	// calculation for Nb
	$scope.calculateResultForNb = function(AB7, BR2) {
    var result = (AB7 * $scope.ferroAlloySpecificationForNb.R23) / 10 * BR2;

    if (result === 0) {
        return 0; 
    } else {
        return result; // Return the calculated result
    }
};
	
	// calculation for V
	$scope.calculateResultForV = function(AD7, BQ2) {
	    var result = (AD7 * $scope.ferroAlloySpecificationForV.N15) / 10 * BQ2;
	
	    if (result === 0) {
	        return 0; 
	    } else {
	        return result; 
	    }
	};
	
	// calculation for Cr
	$scope.calculateResultForCr = function(Z6, AA6, BP2) {
    var result = (Z6 * $scope.ferroAlloySpecificationForCr.L10 + AA6 * $scope.ferroAlloySpecificationForCr.L9) / 10 * BP2;
		//console.log(Z6+ "- AA6 "+AA6+" - BP2 "+ BP2+" - "+$scope.ferroAlloySpecificationForCr.L10+" l9 "+$scope.ferroAlloySpecificationForCr.L9)
	//console.log((Z6 * $scope.ferroAlloySpecificationForCr.L10 + AA6 * $scope.ferroAlloySpecificationForCr.L9) / 10 * BP2)
		//(200 * 65% + 200* 60%)/10 * 98%
    if (result === 0) {
        return 0; 
    } else {
        return result; 
    }
};
	
	// calculation for Si
	$scope.calculateResultForSi = function(L6, M6, N6, P6, Q6, R6, V6, Z6, AA6, AB6, AC6, AD6, AE6, AF6, AG6, AQ6, AI6, AH6, AK6, BO2) {
    /*console.log(BO2+"h "+$scope.ferroAlloySpecificationForSi.G10+"Z6->"+Z6+" AA6->"+AA6+" AB6->"+AB6+" AC6-> "+AC6+" AD6->"+AD6+" AE6->"+AE6+" AF6->"+AF6 +" AG6->"+AG6);
	console.log((Z6 * $scope.ferroAlloySpecificationForSi.G10 +
        AA6 * $scope.ferroAlloySpecificationForSi.G9 +
        AB6 * $scope.ferroAlloySpecificationForSi.G23 +
        AC6 * $scope.ferroAlloySpecificationForSi.G16 +
        AD6 * $scope.ferroAlloySpecificationForSi.G15 +
        AE6 * $scope.ferroAlloySpecificationForSi.G24 +
        AF6 * $scope.ferroAlloySpecificationForSi.G14 +
        AG6 * $scope.ferroAlloySpecificationForSi.G17 )/ 10 * BO2)*/
/*
console.log("L6 term:", L6 );
console.log("$scope.ferroAlloySpecificationForSi.G2: ",$scope.ferroAlloySpecificationForSi.G2);

console.log("M6 term:", M6 );
console.log("$scope.ferroAlloySpecificationForSi.G6: ",$scope.ferroAlloySpecificationForSi.G6);


console.log("N6 term:", N6 );
console.log("$scope.ferroAlloySpecificationForSi.G4: ",$scope.ferroAlloySpecificationForSi.G4);

console.log("P6 term:", P6 );
console.log("$scope.ferroAlloySpecificationForSi.G5: ",$scope.ferroAlloySpecificationForSi.G5);


console.log("Q6 term:", Q6 );
console.log("$scope.ferroAlloySpecificationForSi.G7: ",$scope.ferroAlloySpecificationForSi.G7);


console.log("R6 term:", R6 );
console.log("$scope.ferroAlloySpecificationForSi.G11: ",$scope.ferroAlloySpecificationForSi.G11);


console.log("V6 term:", V6 );
console.log("$scope.ferroAlloySpecificationForSi.G12: ",$scope.ferroAlloySpecificationForSi.G12);


console.log("Z6 term:", Z6);
console.log("$scope.ferroAlloySpecificationForSi.G10: ",$scope.ferroAlloySpecificationForSi.G10);


console.log("AA6 term:", AA6 );
console.log("$scope.ferroAlloySpecificationForSi.G9: ",$scope.ferroAlloySpecificationForSi.G9);


console.log("AB6 term:", AB6 );
console.log("$scope.ferroAlloySpecificationForSi.G23: ",$scope.ferroAlloySpecificationForSi.G23);


console.log("AC6 term:", AC6 );
console.log("$scope.ferroAlloySpecificationForSi.G16: ",$scope.ferroAlloySpecificationForSi.G16);


console.log("AD6 term:", AD6 );
console.log("$scope.ferroAlloySpecificationForSi.G15: ",$scope.ferroAlloySpecificationForSi.G15);


console.log("AE6 term:", AE6 );
console.log("$scope.ferroAlloySpecificationForSi.G24: ",$scope.ferroAlloySpecificationForSi.G24);


console.log("AF6 term:", AF6 );
console.log("$scope.ferroAlloySpecificationForSi.G14: ",$scope.ferroAlloySpecificationForSi.G14);


console.log("AG6 term:", AG6 );
console.log("$scope.ferroAlloySpecificationForSi.G17: ",$scope.ferroAlloySpecificationForSi.G17);

console.log("AQ6 term:", AQ6 );
console.log("$scope.ferroAlloySpecificationForSi.G27: ",$scope.ferroAlloySpecificationForSi.G27);

console.log("AI6 term:", AI6 );
console.log("$scope.ferroAlloySpecificationForSi.G29: ",$scope.ferroAlloySpecificationForSi.G29);


console.log("AH6 term:", AH6 );
console.log("$scope.ferroAlloySpecificationForSi.G29: ",$scope.ferroAlloySpecificationForSi.G29);

console.log("AK6 term:", AK6 );
console.log("$scope.ferroAlloySpecificationForSi.G28: ",$scope.ferroAlloySpecificationForSi.G28);
*/

	var result = (
        L6 * $scope.ferroAlloySpecificationForSi.G2 +
        M6 * $scope.ferroAlloySpecificationForSi.G6 +
        N6 * $scope.ferroAlloySpecificationForSi.G4 +
        P6 * $scope.ferroAlloySpecificationForSi.G5 +
        Q6 * $scope.ferroAlloySpecificationForSi.G7 +
        R6 * $scope.ferroAlloySpecificationForSi.G11 +
        V6 * $scope.ferroAlloySpecificationForSi.G12 +
        Z6 * $scope.ferroAlloySpecificationForSi.G10 +
        AA6 * $scope.ferroAlloySpecificationForSi.G9 +
        AB6 * $scope.ferroAlloySpecificationForSi.G23 +
        AC6 * $scope.ferroAlloySpecificationForSi.G16 +
        AD6 * $scope.ferroAlloySpecificationForSi.G15 +
        AE6 * $scope.ferroAlloySpecificationForSi.G24 +
        AF6 * $scope.ferroAlloySpecificationForSi.G14 +
        AG6 * $scope.ferroAlloySpecificationForSi.G17 +
        AQ6 * $scope.ferroAlloySpecificationForSi.G27 +
        AI6 * $scope.ferroAlloySpecificationForSi.G29 +
        AH6 * $scope.ferroAlloySpecificationForSi.G29 +
        AK6 * $scope.ferroAlloySpecificationForSi.G28
    ) / 10 * BO2;

    return result;
};
	
	
	// calculation for Mn
	$scope.calculateResultForMn = function(L6, N6, M6, O6, P6, Q6, AQ6, BN2) {
    var result = (
        L6 * $scope.ferroAlloySpecificationForMn.D2 +
        N6 * $scope.ferroAlloySpecificationForMn.D4 +
        M6 * $scope.ferroAlloySpecificationForMn.D6 +
        O6 * $scope.ferroAlloySpecificationForMn.D8 +
        P6 * $scope.ferroAlloySpecificationForMn.D5 +
        Q6 * $scope.ferroAlloySpecificationForMn.D7 +
        AQ6 * $scope.ferroAlloySpecificationForMn.D27
    ) / 10 * BN2;

    return result;
};
	
	// calculation for C 
	 $scope.calculateResult = function (J6, K6, L6, M6, N6, P6, Q6, R6, V6, Z6, AA6, AB6, AC6, AD6, AE6, AF6, AG6, AJ6, AK6, AQ6, AI6, BL6) {
 
	return (J6 * $scope.ferroAlloySpecification.C19 +
            K6 * $scope.ferroAlloySpecification.C36 +
            L6 * $scope.ferroAlloySpecification.C2 *
            M6 * $scope.ferroAlloySpecification.C6 +
            N6 * $scope.ferroAlloySpecification.C4 +
            P6 * $scope.ferroAlloySpecification.C5 +
            Q6 * $scope.ferroAlloySpecification.C7 +
            R6 * $scope.ferroAlloySpecification.C11 +
            V6 * $scope.ferroAlloySpecification.C12 +
            Z6 * $scope.ferroAlloySpecification.C10 +
            AA6 *  $scope.ferroAlloySpecification.C9 +
            AB6 *  $scope.ferroAlloySpecification.C23 +
            AC6 *  $scope.ferroAlloySpecification.C16 +
            AD6 *  $scope.ferroAlloySpecification.C15 +
            AE6 *  $scope.ferroAlloySpecification.C24 +
            AF6 *  $scope.ferroAlloySpecification.C14 +
            AG6 *  $scope.ferroAlloySpecification.C17 +
            AJ6 *  $scope.ferroAlloySpecification.C20 +
            AK6 *  $scope.ferroAlloySpecification.C28 +
            AQ6 *  $scope.ferroAlloySpecification.C27 +
            AI6 * $scope.ferroAlloySpecification.C30) / 10 * $scope.calculationSheet.BM2 + BL6; 
 
    };



// Call the calculateResult function when needed
$scope.calculateValueForC=function(){
	// Initial values for your input variables
$scope.J6 = $scope.smsValues.cokePetroleumCalcined;
$scope.K6 = $scope.smsValues.graphiteFines;
$scope.L6 = $scope.smsValues.silicoManganeseHP;
$scope.M6 = $scope.smsValues.silicoManganesExtraLowC;
$scope.N6 = $scope.smsValues.silicoManganeseLowPhos;
$scope.P6 = $scope.smsValues.ferroManganeseHC;
$scope.Q6 = $scope.smsValues.ferroMangansesLC;
$scope.R6 = $scope.smsValues.ferroSilicon;
$scope.V6 = $scope.smsValues.wire16MM;  
$scope.Y6 = $scope.smsValues.nickelMetal;
$scope.X6 = $scope.smsValues.copperCathode;
$scope.W6 = $scope.smsValues.caWirePure;
$scope.T6 = $scope.smsValues.ingotAL;
$scope.U6 = $scope.smsValues.wireAL;

$scope.O6 = $scope.smsValues.manganeseMetalBriqutes;
$scope.AH6=$scope.smsValues.shotsAluminium;
$scope.AK6=$scope.smsValues.ferroTitaninum;
$scope.AN6=$scope.smsValues.wire13MM;
$scope.AO6=$scope.smsValues.alCubes;

$scope.Z6 = $scope.smsValues.ferroChromeLC;
$scope.AA6 = $scope.smsValues.ferroChromeCR;
$scope.AB6 = $scope.smsValues.ferroNiobium;
$scope.AC6 = $scope.smsValues.wireFETICored;
$scope.AD6 = $scope.smsValues.ferroVanadium;
$scope.AE6 = $scope.smsValues.nitrovan;
$scope.AF6 = $scope.smsValues.ferroMolybdenum;
$scope.AG6 = $scope.smsValues.ferroBoron;

$scope.AJ6 = $scope.smsValues.ferroPhosphorous;
$scope.AK6 = $scope.smsValues.ferroTitaninum;
$scope.AQ6 = $scope.smsValues.manganeseCoreWire;
$scope.AI6 = $scope.smsValues.ferroAluminium;
$scope.BL6 = 0.06;
var rawResultC = $scope.calculateResult($scope.J6, $scope.K6, $scope.L6, $scope.M6, $scope.N6, $scope.P6, $scope.Q6, $scope.R6, $scope.V6, $scope.Z6, $scope.AA6, $scope.AB6, $scope.AC6, $scope.AD6, $scope.AE6, $scope.AF6, $scope.AG6, $scope.AJ6, $scope.AK6, $scope.AQ6, $scope.AI6, $scope.BL6);
$scope.resultForC = parseFloat(rawResultC.toFixed(2));

$scope.BN2 =0.90;
var rawResultMn = $scope.calculateResultForMn($scope.L6, $scope.N6,$scope.M6, $scope.O6,$scope.P6, $scope.Q6, $scope.AQ6, $scope.BN2);
$scope.resultForMn = parseFloat(rawResultMn.toFixed(2));

//for si
$scope.BO2 =0.70; 
var rawResultSi = $scope.calculateResultForSi($scope.L6, $scope.M6, $scope.N6, $scope.P6, $scope.Q6, $scope.R6, $scope.V6,
 $scope.Z6, $scope.AA6, $scope.AB6, $scope.AC6, $scope.AD6, $scope.AE6, $scope.AF6, $scope.AG6,
 $scope.AQ6, $scope.AI6, $scope.AH6, $scope.AK6, $scope.BO2);
$scope.resultForSi = parseFloat(rawResultSi.toFixed(2));

//for Cr
$scope.BP2 =0.98;
var rawresultForCr = $scope.calculateResultForCr($scope.Z6, $scope.AA6, $scope.BP2);
$scope.resultForCr = parseFloat(rawresultForCr.toFixed(2));

//for V
$scope.BQ2 =0.98;
var result = $scope.calculateResultForV($scope.AD6, $scope.BQ2);
$scope.resultForV = parseFloat(result.toFixed(2));

//For Nb
$scope.BR2 =0.98;
var rawResultForNb = $scope.calculateResultForNb($scope.AB6, $scope.BR2);
$scope.resultForNb = parseFloat(rawResultForNb.toFixed(2));

// for Ni
$scope.BS2 =0.99;
var rawResultforNi = $scope.calculateResultForNi($scope.Y6, $scope.BS2);
$scope.resultForNi = parseFloat(rawResultforNi.toFixed(2));

//for Mo
$scope.BT2 =0.98;
var rawResultForMo = $scope.calculateResultForMo($scope.AF6, $scope.BT2 );
$scope.resultForMo = parseFloat(rawResultForMo.toFixed(2));

//For Cu
$scope.BU2 =0.995;
var rawResultForCu = $scope.calculateResultForCu($scope.X6, $scope.BU2 );
$scope.resultForCu = parseFloat(rawResultForCu.toFixed(2));

 //For Ti
$scope.BV2 =0.65;
var rawResultForTi = $scope.calculateResultForTi($scope.AC6, $scope.AK6, $scope.BV2 );
$scope.resultForTi = parseFloat(rawResultForTi.toFixed(2));

 //For Ca
$scope.BW2 =0.17;
var rawResultForCa = $scope.calculateResultForCa($scope.V6, $scope.W6, $scope.AN6, $scope.BW2);
$scope.resultForCa = parseFloat(rawResultForCa.toFixed(2));

 //For Ai
$scope.BX2 =0.12;
var rawResultForAi = $scope.calculateResultForAi($scope.T6, $scope.U6, $scope.AC6, $scope.R6, $scope.V6, $scope.AN6, $scope.AK6, $scope.AB6, $scope.AO6, $scope.AI6, $scope.AH6, $scope.AQ6, $scope.BX2);
$scope.resultForAi = parseFloat(rawResultForAi.toFixed(2));

 //For B
$scope.BY2 =0.60;
var rawResultForB = $scope.calculateResultForB($scope.AG6, $scope.BY2);
$scope.resultForB = parseFloat(rawResultForB.toFixed(2));

 //For P
$scope.BZ2 =0.98;
var rawResultForP = $scope.calculateResultForP($scope.J6, $scope.L6, $scope.M6, $scope.N6, $scope.O6, $scope.P6, $scope.Q6, $scope.R6, $scope.U6, $scope.V6, $scope.Z6, $scope.AA6, $scope.AB6, $scope.AD6, $scope.AF6, $scope.AG6, $scope.AJ6, $scope.AK6, $scope.AQ6, $scope.BZ2);
$scope.resultForP = parseFloat(rawResultForP.toFixed(2));

 //For N
$scope.CA2 =0.50;
var rawResultForN = $scope.calculateResultForN($scope.AQ6, $scope.CA2);
$scope.resultForN = parseFloat(rawResultForN.toFixed(2));



}





}]);