'use strict';
app.factory('smsService', ['$http', 'ngAuthSettings', function ($http, ngAuthSettings) {

    var serviceBase = ngAuthSettings.apiServiceBaseUri;

    var smsServiceFactory = {};

    var _getProductList = function (page,sortBy,product,grade,internalGrade) {
        return $http.get(serviceBase + 'api/v1/products-list',{ params: { page: page,sortBy:sortBy,product:product,grade:grade,internalGrade:internalGrade } }).then(function (results) {
            return results;
        });
    };
	
	var _fatchSmsData = function (qualityFkid) {
        return $http.get(serviceBase + 'api/v1/sms-data',{ params: { qualityFkid: qualityFkid} }).then(function (results) {
            return results;
        });
    };
	
	var _getSmsHistoryData = function (qualityFkid) {
        return $http.get(serviceBase + 'api/v1/sms-history-data',{ params: { qualityFkid: qualityFkid} }).then(function (results) {
            return results;
        });
    };

	var _getFerroAlloySpecificationList = function () {
        return $http.get(serviceBase + 'api/v1/ferro-alloy-specification').then(function (results) {
            return results;
        });
    };
	var _saveSmsData=function(smsValues){
	 		return $http.post(serviceBase + 'api/v1/save-sms-data',smsValues).then(function (results) {
            return results;
        });
	}

   smsServiceFactory.getProductList1 = _getProductList;
   smsServiceFactory.saveSmsData = _saveSmsData;
   smsServiceFactory.fatchSmsData = _fatchSmsData;
   smsServiceFactory.getSmsHistoryData = _getSmsHistoryData;
   smsServiceFactory.getFerroAlloySpecificationList = _getFerroAlloySpecificationList;


    return smsServiceFactory;

}]);