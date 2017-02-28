var app = angular.module('app', ['ngRoute','ngResource', 'ngCookies']);
app.config(function($routeProvider){
    $routeProvider
        .when('/users',{
            templateUrl: '/views/users.html',
            controller: 'usersController'
        })
        .when('/roles',{
            templateUrl: '/views/roles.html',
            controller: 'rolesController'
        })
        .when('/login',{
            templateUrl: '/views/login.html',
            controller: 'loginController'
        })
        .when('/register',{
            templateUrl: '/views/register.html',
            controller: 'registerController'
        })
        .when('/airlineOffer',{
            templateUrl: '/views/airlineOffer.html',
            controller: 'airlineOfferController'
        })
        .when('/accountCreate',{
            templateUrl: '/views/accountCreate.html',
            controller: 'accountCreateController'
        })
        .otherwise(
            { redirectTo: '/'}
        );
});

