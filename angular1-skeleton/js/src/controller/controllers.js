define(
    [
        'src/controller/DefaultController',
        'src/controller/DataController'
    ],
    function (DefaultController, DataController) {

        var controller = {};

        controller.DefaultController = DefaultController;
        controller.DataController = DataController;

        return controller;
    }

);