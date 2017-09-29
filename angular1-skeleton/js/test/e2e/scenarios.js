'use strict';

describe('Angular Skeleton App', function () {

    describe('Home view', function () {

        beforeEach(function () {
            browser.get('/');
        });

        it('should display "Angular Skeleton" title', function () {
            expect(element(by.css('.jumbotron h1')).getText()).toBe('Angular Skeleton');
        });

        it('should only highlight the "Home" link in navbar', function () {
            expect(element(by.css('.nav li.active')).getText()).toBe('Home');
        });

        it('should redirect to data view when click the "Show Data" button', function () {
            element(by.css('.jumbotron .btn')).click();
            browser.getLocationAbsUrl().then(function (url) {
                expect(url).toBe('/data/');
            });
        });

    });

    describe('Data view', function () {

        beforeEach(function () {
            browser.get('/#/data/');
        });

        it('should only highlight the "Data" link in navbar', function () {
            expect(element(by.css('.nav li.active')).getText()).toBe('Data');
        });

        it('should display 5 items per page', function () {
            var data = element.all(by.repeater('row in data'));
            expect(data.count()).toBe(5);
        });

        it('should display different data when goes to next page', function () {
            var data1 = element.all(by.repeater('row in data'));

            var nextPage = element(by.css('.pagination li:last-child a'));
            nextPage.click();
            var data2 = element.all(by.repeater('row in data'));

            expect(data1).not.toEqual(data2);
        });

        it('should display start with ID 6 on the 2nd page', function () {
            var page2 = element(by.css('.pagination li:nth-child(3) a'));
            page2.click();
            var firstId = element.all(by.repeater('row in data')).first()
                .$$("td").first();

            expect(firstId.getText()).toEqual('6');
        });

    });

});
