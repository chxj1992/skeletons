define('app', ['src/controller/controllers', 'src/service/services', 'router', 'angularjs', 'angular-route', 'angular-bootstrap-tpls'], function (controllers, services, router) {

    var myApp = angular.module('myApp', ['ngRoute', 'ui.bootstrap'], function ($interpolateProvider, $httpProvider) {

        // avoid the conflict of '{{..}}' with some backend template engine, like Twig
        $interpolateProvider.startSymbol('[[');
        $interpolateProvider.endSymbol(']]');

        // post data with form content type
        $httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
        $httpProvider.defaults.headers.put['Content-Type'] = 'application/x-www-form-urlencoded';
    });

    myApp.config(router);

    // Dependency Injections
    myApp.controller(controllers);
    myApp.service(services);

    angular.bootstrap(document, ['myApp']);

    return myApp;
});

