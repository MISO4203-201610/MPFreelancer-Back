describe('ProjectSponsor E2E Testing', function () {

	browser.driver.manage().window().maximize();

	var nameVarTest = 'Val' + Math.floor(Math.random() * 10000);
	var companyVarTest = 'Val' + Math.floor(Math.random() * 10000);
	var pictureVarTest = 'Val' + Math.floor(Math.random() * 10000);

    beforeEach(function () {
        browser.addMockModule('ngCrudMock', function () {
            var mod = angular.module('ngCrudMock');

            mod.run(['ngCrudMock.mockRecords', function(records){
                records['projectSponsors'] = [];

            }]);
        });
    });

    it('should create one projectSponsor', function () {
        browser.get('#/projectSponsor');
        element(by.id('create-projectSponsor')).click();
        element(by.id('name')).sendKeys(nameVarTest);
        element(by.id('company')).sendKeys(companyVarTest);
        element(by.id('picture')).sendKeys(pictureVarTest);
        element(by.id('save-projectSponsor')).click();
        expect(element.all(by.repeater('record in records')).count()).toEqual(1);
    });

    it('should read one projectSponsor', function () {
        expect(element(by.id('0-name')).getText()).toBe(nameVarTest);
        expect(element(by.id('0-company')).getText()).toBe(companyVarTest);
        expect(element(by.id('0-picture')).getText()).toBe(pictureVarTest);
    });

    it('should edit one projectSponsor', function () {
        element(by.id('0-edit-btn')).click();

        element(by.id('name')).clear().sendKeys('New' + nameVarTest);
        element(by.id('company')).clear().sendKeys('New' + companyVarTest);
        element(by.id('picture')).clear().sendKeys('New' + pictureVarTest);

        element(by.id('save-projectSponsor')).click();

        expect(element(by.id('0-name')).getText()).toBe('New' + nameVarTest);
        expect(element(by.id('0-company')).getText()).toBe('New' + companyVarTest);
        expect(element(by.id('0-picture')).getText()).toBe('New' + pictureVarTest);
    });

    it('should delete the projectSponsor', function () {
        element(by.id('0-delete-btn')).click();
        expect(element.all(by.id('0-name')).count()).toEqual(0);
        expect(element.all(by.id('0-company')).count()).toEqual(0);
        expect(element.all(by.id('0-picture')).count()).toEqual(0);
    });
});
