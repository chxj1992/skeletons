define(['angular-mocks', 'app'], function () {

    describe("DataService", function () {

        beforeEach(module("myApp"));

        var dataService;

        beforeEach(function () {

            mockHttp = {
                get: {}
            };
            spyOn(mockHttp, 'get').and.returnValue(jasmine.createSpyObj(['success', 'error']));

            module(function ($provide) {
                $provide.value('$http', mockHttp);
            });

            inject(function ($injector) {
                dataService = $injector.get('DataService');
            });
        });

        it("should call http get when get list in DataService", function () {
            dataService.getList();
            expect(mockHttp.get).toHaveBeenCalled();
        });

    });
});

