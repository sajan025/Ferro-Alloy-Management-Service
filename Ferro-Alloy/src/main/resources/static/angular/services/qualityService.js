'use strict';
app.factory('qualityService', ['$http', 'ngAuthSettings', function ($http, ngAuthSettings) {

    var serviceBase = ngAuthSettings.apiServiceBaseUri;

    var qualityServiceFactory = {};

    var _getProductList = function (page,sortBy,product,grade,internalGrade) {
        return $http.get(serviceBase + 'api/v1/products',{ params: { page: page,sortBy:sortBy,product:product,grade:grade,internalGrade:internalGrade } }).then(function (results) {
            return results;
        });
    };
    
	var _fatchRequestById = function (id) {
        return $http.get(serviceBase + 'api/v1/products/'+id).then(function (results) {
            return results;
        });
    };





	var _findProduct = function (grade) {
        return $http.get(serviceBase + 'api/v1/find-products/'+grade).then(function (results) {
            return results;
        });
    };
	var _fetchMasterGrade = function (key) {
        return $http.get(serviceBase + 'api/v1/master-grade/'+key).then(function (results) {
            return results;
        });
    };

	var _fetchMasterInternalGrade = function (key) {
        return $http.get(serviceBase + 'api/v1/master-internalgrade/'+key).then(function (results) {
            return results;
        });
    };

    var _saveproduct=function(qualityRequestDto){
	 		return $http.post(serviceBase + 'api/v1/save-product',qualityRequestDto).then(function (results) {
            return results;
        });
	}

    qualityServiceFactory.getProductList = _getProductList;
	qualityServiceFactory.saveProduct = _saveproduct;
	qualityServiceFactory.fatchRequestById=_fatchRequestById;
	qualityServiceFactory.fetchMasterGrade=_fetchMasterGrade;
	qualityServiceFactory.fetchMasterInternalGrade=_fetchMasterInternalGrade;
	qualityServiceFactory.findProduct=_findProduct;

    return qualityServiceFactory;

}]);