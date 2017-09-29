define('router', [], function () {

    return function ($routeProvider) {
        $routeProvider

            .when('/data/', {
                templateUrl: 'template/data.html',
                controller: 'DataController'
            })
            .otherwise({
                templateUrl: 'template/home.html',
                controller: 'DefaultController'
            });
    }
});