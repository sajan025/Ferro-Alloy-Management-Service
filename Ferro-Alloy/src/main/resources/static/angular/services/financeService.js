'use strict';
app.factory('financeService', ['$http', 'ngAuthSettings', function ($http, ngAuthSettings) {

    var serviceBase = ngAuthSettings.apiServiceBaseUri;

    var financeServiceFactory = {};

var _getQualityList = function (page,sortBy,product,grade,internalGrade) {

        return $http.get(serviceBase + 'financeapi/v1/getQualityList',{ params: { page: page,sortBy:sortBy,product:product,grade:grade,internalGrade:internalGrade } }).then(function (results) {
            return results;
        });
    };
var _financeData= function (id) {

        return $http.get(serviceBase + 'financeapi/v1/financeData',{ params: { id: id} }).then(function (results) {
            return results;
        });
    };
var _financeHistoryData= function (id) {

        return $http.get(serviceBase + 'financeapi/v1/financeHistoryData',{ params: { id: id} }).then(function (results) {
            return results;
        });
    };

var _getCostingHistorydata= function (id) {

        return $http.get(serviceBase + 'financeapi/v1/costingHistoryData',{ params: { id: id} }).then(function (results) {
            return results;
        });
    };

var _getCostingRates= function (id) {

        return $http.get(serviceBase + 'financeapi/v1/costing-rates',{ params: { id: id} }).then(function (results) {
            return results;
        });
    };
var _saveFinance = function (financeRequest) {

        return $http.post(serviceBase + 'financeapi/saveFinance',financeRequest).then(function (results) {
            return results;
        });
    };
    
 var _saveCost = function (costRequest) {

        return $http.post(serviceBase + 'financeapi/saveCost',costRequest).then(function (results) {
            return results;
        });
    };
     financeServiceFactory.getQualityList=_getQualityList;
     financeServiceFactory.financeData=_financeData;
       financeServiceFactory.financeHistoryData=_financeHistoryData;
    financeServiceFactory.saveFinance = _saveFinance;
    financeServiceFactory.saveCost=_saveCost;
	financeServiceFactory.getCostingRates=_getCostingRates
	financeServiceFactory.getCostingHistorydata=_getCostingHistorydata;
	
   
    return financeServiceFactory;

}]);