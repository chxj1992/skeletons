define([], function () {

    var DataService = function DataService($q, $http) {

        this.getList = function(page) {
            var deferred = $q.defer();

            $http.get('/server/data-'+page+'.json').success(function (res) {
                deferred.resolve(res.data);
            });
            return deferred.promise;
        }

    };

    DataService.$inject = ['$q', '$http'];

    return DataService;
});