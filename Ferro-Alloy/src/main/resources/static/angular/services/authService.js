'use strict';
app.factory('authService', ['$http', '$q', 'localStorageService', 'ngAuthSettings', function ($http, $q, localStorageService, ngAuthSettings) {

    var serviceBase = ngAuthSettings.apiServiceBaseUri;
    var authServiceFactory = {};

    var _authentication = {
        isAuth: false,
        username: "",
        useRefreshTokens: false
    };

    var _login = function (loginData) {
        var data = "grant_type=password&username=" + loginData.username + "&password=" + loginData.password;
        /*if (loginData.useRefreshTokens) {
            data = data + "&client_id=" + ngAuthSettings.clientId;
        }*/
        var deferred = $q.defer();
        $http.post(serviceBase + 'auth/signin', data, { headers: { 'Content-Type': 'application/x-www-form-urlencoded' } }).success(function (response) {

            if (loginData.useRefreshTokens) {
                localStorageService.set('authorizationData', { token: response.authToken, userName: loginData.username, refreshToken: response.refresh_token, useRefreshTokens: true });
            }
            else {
                localStorageService.set('authorizationData', { token: response.authToken, userName: loginData.username, refreshToken: "", useRefreshTokens: false });
            }
            _authentication.isAuth = true;
            _authentication.username = loginData.username;
          //  _authentication.useRefreshTokens = loginData.useRefreshTokens;

            deferred.resolve(response);

        }).error(function (err, status) {
            _logOut();
            deferred.reject(err);
        });

        return deferred.promise;

    };

    var _logOut = function () {

        localStorageService.remove('authorizationData');

        _authentication.isAuth = false;
        _authentication.username = "";
        _authentication.useRefreshTokens = false;

    };

    var _fillAuthData = function () {

        var authData = localStorageService.get('authorizationData');
        if (authData) {
            _authentication.isAuth = true;
            _authentication.username = authData.userName;
           // _authentication.useRefreshTokens = authData.useRefreshTokens;
        }

    };

    var _refreshToken = function () {
        var deferred = $q.defer();
        var authData = localStorageService.get('authorizationData');

                localStorageService.remove('authorizationData');
                $http.get(serviceBase + 'auth/refresh-token/'+ authData.userName, { headers: { 'Content-Type': 'application/x-www-form-urlencoded' } }).success(function (response) {
                    localStorageService.set('authorizationData', { token: response.authToken, userName: response.username, refreshToken: response.authToken, useRefreshTokens: false });
                    deferred.resolve(response);
                }).error(function (err, status) {
                    _logOut();
                    deferred.reject(err);
                });

        return deferred.promise;
    };

    var _obtainAccessToken = function (externalData) {
        var deferred = $q.defer();
        $http.get(serviceBase + 'api/account/ObtainLocalAccessToken', { params: { provider: externalData.provider, externalAccessToken: externalData.externalAccessToken } }).success(function (response) {
            localStorageService.set('authorizationData', { token: response.access_token, userName: response.userName, refreshToken: "", useRefreshTokens: false });
            _authentication.isAuth = true;
            _authentication.username = response.username;
            _authentication.useRefreshTokens = false;
            deferred.resolve(response);
        }).error(function (err, status) {
            _logOut();
            deferred.reject(err);
        });

        return deferred.promise;

    };

    authServiceFactory.login = _login;
    authServiceFactory.logOut = _logOut;
    authServiceFactory.fillAuthData = _fillAuthData;
    authServiceFactory.authentication = _authentication;
    authServiceFactory.refreshToken = _refreshToken;

    //authServiceFactory.obtainAccessToken = _obtainAccessToken;
   

    return authServiceFactory;
}]);