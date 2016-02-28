(function (ng) {
    var mod = ng.module('mainApp', [
        //'ngCrudMock',
        'categoryModule',
        'educationModule',
        'freelancerModule',
        'projectModule',
        'projectSponsorModule',
        'skillModule',
        'statusModule',
        'authModule',
        'ui.router',
        'ngCrud',
        'roleModule',
        'agreementModule',
        'curriculumModule'
    ]);

    mod.config(['$logProvider', function ($logProvider) {
            $logProvider.debugEnabled(true);
        }]);

    mod.config(['RestangularProvider', function (rp) {
            rp.setBaseUrl('http://localhost:8080/m-p-freelancer-api/api/');
        }]);

    mod.config(['$stateProvider', '$urlRouterProvider', 'CrudTemplateURL', 'CrudCtrlAlias', function ($stateProvider, $urlRouterProvider, tplUrl, alias) {
            $stateProvider
                .state('category', {
                    url: '/category',
                    templateUrl: tplUrl,
                    controller: 'categoryCtrl',
                    controllerAs: alias
                })
                .state('freelancer', {
                    url: '/freelancer',
                    templateUrl: tplUrl,
                    controller: 'freelancerCtrl',
                    controllerAs: alias
                })
                .state('project', {
                    url: '/project',
                    templateUrl: tplUrl,
                    controller: 'projectCtrl',
                    controllerAs: alias
                })
                .state('projectSponsor', {
                    url: '/projectSponsor',
                    templateUrl: tplUrl,
                    controller: 'projectSponsorCtrl',
                    controllerAs: alias
                })
                .state('skill', {
                    url: '/skill',
                    templateUrl: tplUrl,
                    controller: 'skillCtrl',
                    controllerAs: alias
                })
                .state('curriculum', {
                    url: '/curriculum',
                    templateUrl: tplUrl,
                    controller: 'curriculumCtrl',
                    controllerAs: alias
                })
                .state('status', {
                    url: '/status',
                    templateUrl: tplUrl,
                    controller: 'statusCtrl',
                    controllerAs: alias
                })
                .state('freelancerProfile', {
                    url: '/freelancerProfile',
                    templateUrl: "src/modules/freelancer/freelancerProfile.tpl.html",
                    controller: 'freelancerProfileCtrl',
                    controllerAs: alias
                })
                .state('projectSponsorProfile', {
                    url: '/projectSponsorProfile',
                    templateUrl: "src/modules/projectSponsor/projectSponsorProfile.tpl.html",
                    controller: 'projectSponsorProfileCtrl',
                    controllerAs: alias
                })
                .state('projectList', {
                    url: '/projectList',
                    templateUrl: "src/modules/project/projectList.tpl.html",
                    controller: 'projectListCtrl',
                    controllerAs: alias
                })
                .state('agreement', {
                    url: '/agreement',
                    templateUrl: tplUrl,
                    controller: 'agreementCtrl',
                    controllerAs: alias
                });
            $urlRouterProvider.otherwise('/');
        }]);

    mod.config(['authServiceProvider', function (auth) {
            auth.setValues({
                apiUrl: 'http://localhost:8080/m-p-freelancer-api/api/users/',
                successState: 'projectList'
            });

            auth.setRoles({
                'freelancer': [{
                        id: 'freelancer',
                        label: 'Freelancer',
                        icon: 'list-alt',
                        state: 'freelancer'
                    }, {
                        id: 'freelancerProfile',
                        label: 'Freelancer Profile',
                        icon: 'list-alt',
                        state: 'freelancerProfile'
                    }, {
                        id: 'skill',
                        label: 'Skill',
                        icon: 'list-alt',
                        state: 'skill'
                    }, {
                        id: 'curriculum',
                        label: 'Curriculum',
                        icon: 'list-alt',
                        state: 'curriculum'
                    }],
                'projectSponsor': [{
                        id: 'category',
                        label: 'Category',
                        icon: 'list-alt',
                        state: 'category'
                    }, {
                        id: 'project',
                        label: 'Project',
                        icon: 'list-alt',
                        state: 'project'
                    }, {
                        id: 'projectSponsor',
                        label: 'Project Sponsor',
                        icon: 'list-alt',
                        state: 'projectSponsor'
                    }, {
                        id: 'projectSponsorProfile',
                        label: 'Project Sponsor Profile',
                        icon: 'list-alt',
                        state: 'projectSponsorProfile'
                    }, {
                        id: 'skill',
                        label: 'Skill',
                        icon: 'list-alt',
                        state: 'skill'
                    }],
                'admin': [{
                        id: 'freelancer',
                        label: 'Freelancer',
                        icon: 'list-alt',
                        state: 'freelancer'
                    }, {
                        id: 'skill',
                        label: 'Skill',
                        icon: 'list-alt',
                        state: 'skill'
                    }]
            });
        }]);
})(window.angular);
