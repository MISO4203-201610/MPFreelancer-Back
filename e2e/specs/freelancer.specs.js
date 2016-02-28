describe('Freelancer E2E Testing', function () {

	browser.driver.manage().window().maximize();

	var nameVarTest = 'Val' + Math.floor(Math.random() * 10000);
	var rateVarTest = Math.floor(Math.random() * 10000).toString();
	var pictureVarTest = 'Val' + Math.floor(Math.random() * 10000);

    beforeEach(function () {
        browser.addMockModule('ngCrudMock', function () {
            var mod = angular.module('ngCrudMock');

            mod.run(['ngCrudMock.mockRecords', function(records){
                records['freelancers'] = [];

            }]);
        });
    });

    it('should create one freelancer', function () {
        browser.get('#/freelancer');
        element(by.id('create-freelancer')).click();
        element(by.id('name')).sendKeys(nameVarTest);
		element(by.id('rate')).sendKeys(rateVarTest);
        element(by.id('picture')).sendKeys(pictureVarTest);
        element(by.id('save-freelancer')).click();
        expect(element.all(by.repeater('record in records')).count()).toEqual(1);
    });

    it('should read one freelancer', function () {
        expect(element(by.id('0-name')).getText()).toBe(nameVarTest);
		expect(element(by.id('0-rate')).getText()).toBe(rateVarTest);
        expect(element(by.id('0-picture')).getText()).toBe(pictureVarTest);
    });

    it('should edit one freelancer', function () {
        element(by.id('0-edit-btn')).click();

        element(by.id('name')).clear().sendKeys('New' + nameVarTest);
		element(by.id('rate')).clear().sendKeys(rateVarTest + 1);
        element(by.id('picture')).clear().sendKeys('New' + pictureVarTest);

        element(by.id('save-freelancer')).click();

        expect(element(by.id('0-name')).getText()).toBe('New' + nameVarTest);
		expect(element(by.id('0-rate')).getText()).toBe(rateVarTest + 1);
        expect(element(by.id('0-picture')).getText()).toBe('New' + pictureVarTest);
    });

    it('should delete the freelancer', function () {
        element(by.id('0-delete-btn')).click();
        expect(element.all(by.id('0-name')).count()).toEqual(0);
        expect(element.all(by.id('0-rate')).count()).toEqual(0);
        expect(element.all(by.id('0-bithday')).count()).toEqual(0);
        expect(element.all(by.id('0-picture')).count()).toEqual(0);
    });
});
