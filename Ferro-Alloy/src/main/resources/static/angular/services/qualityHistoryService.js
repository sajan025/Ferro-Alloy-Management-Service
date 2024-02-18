'use strict';
app.factory('qualityHistoryService', ['$http', 'ngAuthSettings', function ($http, ngAuthSettings) {

    var serviceBase = ngAuthSettings.apiServiceBaseUri;
    var qualityHistoryServiceFactory = {};
  
    var _getHistoryData= function (id) {

        return $http.get(serviceBase + 'api/v1/qualityHistoryData',{ params: { id: id} }).then(function (results) {
            return results;
        });
    };

  qualityHistoryServiceFactory.getHistoryData=_getHistoryData;
  return qualityHistoryServiceFactory;

}]);