// RequireJs configuration file
require.config({
    baseUrl: '/js/',
    paths: {
        'jquery': 'bower_components/jquery/dist/jquery.min',
        'bootstrap': 'bower_components/bootstrap/dist/js/bootstrap.min',
        'angularjs': 'bower_components/angular/angular',
        'angular-route': 'bower_components/angular-route/angular-route',
        'angular-bootstrap-tpls': 'bower_components/angular-bootstrap/ui-bootstrap-tpls'
    },

    shim: {
        'bootstrap': {
            deps: ['jquery']
        },
        'angularjs': {
            deps: ['jquery'],
            exports: "angular"
        },
        'angular-route': {
            deps: ['angularjs']
        },
        'angular-bootstrap-tpls': {
            deps: ['angularjs', 'bootstrap']
        }
    },

    deps: ['init']
});


