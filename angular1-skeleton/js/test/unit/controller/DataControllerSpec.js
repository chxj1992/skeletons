define(['angular-mocks', 'app'], function () {

    describe("app module", function () {

        beforeEach(module("myApp"));

        describe("DataController", function () {
            var scope,
                DataService;

            beforeEach(inject(function ($rootScope, $controller) {
                scope = $rootScope.$new();

                DataService = {
                    getList: {}
                };
                spyOn(DataService, 'getList').and.returnValue(jasmine.createSpyObj(['then']));

                $controller("DataController", {$scope: scope, DataService: DataService});
            }));

            it("should call getList in DataService", function () {
                scope.getList();
                expect(DataService.getList).toHaveBeenCalled();
            });

        });
    });

});

