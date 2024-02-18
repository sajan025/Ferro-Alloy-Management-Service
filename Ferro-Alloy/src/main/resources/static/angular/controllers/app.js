
var app = angular.module('app', ['ngRoute', 'LocalStorageModule', 'angular-loading-bar','angularUtils.directives.dirPagination','toastr']);

app.config(function ($routeProvider) {

    /*$routeProvider.when("login", {
        controller: "loginController",
        templateUrl: "templates/login.html"
    });*/


    //$routeProvider.otherwise({ redirectTo: "/login" });
	//$locationProvider.html5Mode(true);
});
//Prod Env.
var serviceBase = 'http://10.36.0.41:8080/Ferro-Alloy/';
//Test Env.
//var serviceBase = 'http://xxx:8081/Ferro-Alloy/';
//Local Env.
//var serviceBase = 'http://localhost:8087/Ferro-Alloy/';
app.constant('ngAuthSettings', {
    apiServiceBaseUri: serviceBase,
    clientId: 'ngAuthApp'
});

app.config(function ($httpProvider) {
    $httpProvider.interceptors.push('authInterceptorService');
});
app.config(['cfpLoadingBarProvider', function(cfpLoadingBarProvider) {
    cfpLoadingBarProvider.includeSpinner = true; 
    cfpLoadingBarProvider.includeBar = true; 
}]);

app.run(['authService', function (authService) {
    authService.fillAuthData();
}]);

app.config(function(toastrConfig) {
  angular.extend(toastrConfig, {
    autoDismiss: true,
    containerId: 'toast-container',
    maxOpened: 1,    
    newestOnTop: true,
    positionClass: 'toast-top-right',
    preventDuplicates: false,
    preventOpenDuplicates: false,
    target: 'body'
  });
});

