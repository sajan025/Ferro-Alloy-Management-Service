'use strict';
app.factory('adminDashboardService', ['$http', 'ngAuthSettings', function ($http, ngAuthSettings) {

    var serviceBase = ngAuthSettings.apiServiceBaseUri;

    var adminDashboardServiceFactory = {};

    var _getUsers = function () {

        return $http.get(serviceBase + 'api/v1/users').then(function (results) {
            return results;
        });
    };

    adminDashboardServiceFactory.getUsers = _getUsers;

    return adminDashboardServiceFactory;

}]);