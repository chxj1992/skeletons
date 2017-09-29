define([], function () {

    var DataController = function DataController($scope, DataService) {

        $scope.totalItems = 15;
        $scope.pageSize = 5;
        $scope.currentPage = 1;

        $scope.init = function() {
            angular.element("#navbar-links li").removeClass();
            angular.element("#navbar-link-data").addClass("active");
            $scope.getList();
        };

        $scope.getList = function () {
            DataService.getList($scope.currentPage).then(function (data) {
                $scope.data = data;
                console.log('This is page: '+$scope.currentPage);
                console.log(data);
            });
        };

    };

    DataController.$inject = ['$scope', 'DataService'];

    return DataController;

});