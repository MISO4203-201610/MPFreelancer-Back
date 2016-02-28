describe('Project E2E Testing', function () {

	browser.driver.manage().window().maximize();

	var nameVarTest = 'Val' + Math.floor(Math.random() * 10000);
	var descriptionVarTest = 'Val' + Math.floor(Math.random() * 10000);
	var priceVarTest = Math.floor(Math.random() * 10000).toString();

    beforeEach(function () {
        browser.addMockModule('ngCrudMock', function () {
            var mod = angular.module('ngCrudMock');

            mod.run(['ngCrudMock.mockRecords', function(records){
                records['projects'] = [];

                records['statuss'] = [];
                records['statuss'].push({id: Math.floor(Math.random() * 10000), name: 'status'});

                records['categorys'] = [];
                records['categorys'].push({id: Math.floor(Math.random() * 10000), name: 'category'});

                records['projectSponsors'] = [];
                records['projectSponsors'].push({id: Math.floor(Math.random() * 10000), name: 'sponsor'});
            }]);
        });
    });

    it('should create one project', function () {
        browser.get('#/project');
        element(by.id('create-project')).click();
        element(by.id('name')).sendKeys(nameVarTest);
        element(by.id('description')).sendKeys(descriptionVarTest);
		element(by.id('price')).sendKeys(priceVarTest);
        element(by.id('status')).all(by.css('option')).last().click();
        element(by.id('category')).all(by.css('option')).last().click();
        element(by.id('sponsor')).all(by.css('option')).last().click();
        element(by.id('save-project')).click();
        expect(element.all(by.repeater('record in records')).count()).toEqual(1);
    });

    it('should read one project', function () {
        expect(element(by.id('0-name')).getText()).toBe(nameVarTest);
        expect(element(by.id('0-description')).getText()).toBe(descriptionVarTest);
		expect(element(by.id('0-price')).getText()).toBe(priceVarTest);
    });

    it('should edit one project', function () {
        element(by.id('0-edit-btn')).click();

        element(by.id('name')).clear().sendKeys('New' + nameVarTest);
        element(by.id('description')).clear().sendKeys('New' + descriptionVarTest);
		element(by.id('price')).clear().sendKeys(priceVarTest + 1);

        element(by.id('save-project')).click();

        expect(element(by.id('0-name')).getText()).toBe('New' + nameVarTest);
        expect(element(by.id('0-description')).getText()).toBe('New' + descriptionVarTest);
		expect(element(by.id('0-price')).getText()).toBe(priceVarTest + 1);
    });

    it('should delete the project', function () {
        element(by.id('0-delete-btn')).click();
        expect(element.all(by.id('0-name')).count()).toEqual(0);
        expect(element.all(by.id('0-description')).count()).toEqual(0);
        expect(element.all(by.id('0-price')).count()).toEqual(0);
        expect(element.all(by.id('0-deadLine')).count()).toEqual(0);
        expect(element.all(by.id('0-publicationDate')).count()).toEqual(0);
        expect(element.all(by.id('0-startDate')).count()).toEqual(0);
    });
});
