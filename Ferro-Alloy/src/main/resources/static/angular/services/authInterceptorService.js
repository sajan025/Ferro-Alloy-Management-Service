'use strict';
app.factory('authInterceptorService', ['$q', '$injector','$location','$window','ngAuthSettings', 'localStorageService', function ($q, $injector,$location,$window,ngAuthSettings, localStorageService) {
var serviceBase = ngAuthSettings.apiServiceBaseUri;
    var authInterceptorServiceFactory = {};

    var _request = function (config) {

        config.headers = config.headers || {};
       
        var authData = localStorageService.get('authorizationData');
        if (authData) {
            config.headers.Authorization = 'Bearer ' + authData.token;
        }

        return config;
    }

    /*var _responseError = function (rejection) {
        if (rejection.status === 401 ) {
            var authService = $injector.get('authService');
            var authData = localStorageService.get('authorizationData');

            if (authData) {
                if (authData.useRefreshTokens) {
                    $location.path('/refresh');
                    return $q.reject(rejection);
                }
            }
            authService.logOut();
            $window.location.href = serviceBase+"/login";
        }
        return $q.reject(rejection);
    }*/
	var _responseError = function (rejection) {
        var deferred = $q.defer();
        if (rejection.status === 401) {
            var authService = $injector.get('authService');
            authService.refreshToken().then(function (response) {
                _retryHttpRequest(rejection.config, deferred);
            }, function () {
				//alert("Somthing went wrong!!!")
               authService.logOut();
               location.href = '/Ferro-Alloy/login';
                deferred.reject(rejection);
            });
        } else {
            deferred.reject(rejection);
        }
        return deferred.promise;
    }

    var _retryHttpRequest = function (config, deferred) {
        var http = $injector.get('$http');
        http(config).then(function (response) {
            deferred.resolve(response);
        }, function (response) {
            deferred.reject(response);
        });
    }

    authInterceptorServiceFactory.request = _request;
    authInterceptorServiceFactory.responseError = _responseError;

    return authInterceptorServiceFactory;
}]);