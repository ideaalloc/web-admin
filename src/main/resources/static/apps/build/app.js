(function () {
    'use strict';

    var app = angular.module('app', ['ngRoute', 'wj']);

    app.config(function ($interpolateProvider) {
        $interpolateProvider.startSymbol('{[').endSymbol(']}');
    });

    app.config(['$routeProvider', function ($routeProvider) {
        $routeProvider.
            when('/kick-starter', {templateUrl: '/admin/kick-starter'}).
            when('/user', {templateUrl: '/admin/user'}).
            when('/', {templateUrl: '/admin/home'}).
            otherwise({redirectTo: '/admin'});
    }]);

})();
