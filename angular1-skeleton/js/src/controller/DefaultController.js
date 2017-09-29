define([], function () {

    var DefaultController = function DefaultController($scope) {

        $scope.init = function () {
            angular.element("#navbar-links li").removeClass();
            angular.element("#navbar-link-home").addClass("active");
        };

    };

    DefaultController.$inject = ['$scope'];

    return DefaultController;

});