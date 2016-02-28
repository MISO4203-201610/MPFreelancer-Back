(function (ng) {
    var mod = ng.module('freelancerModule');

    mod.controller('freelancerCtrl', ['CrudCreator', '$scope',
        'freelancerContext', 'freelancerModel',
        function (ngCrud, $scope, url, model) {
            ngCrud.extendController({
                name: 'freelancer',
                displayName: 'Freelancer',
                ctrl: this,
                scope: $scope,
                model: model,
                url: url
            });
            this.loadRefOptions();
            this.fetchRecords();
            this.globalActions.create = {
                show: function () {
                    return false;
                }
            };
        }]);

    mod.controller('FreelancertitlesCtrl', ['CrudCreator', '$scope', 'educationModel',
        function (ngCrud, $scope, model) {
            ngCrud.extendCompChildCtrl({
                name: 'titles',
                displayName: 'Titles',
                parent: 'freelancer',
                ctrl: this,
                scope: $scope,
                model: model
            });
        }]);

    mod.controller('FreelancersskillsCtrl', ['CrudCreator', '$scope',
        'skillModel', 'skillContext', 'freelancerContext',
        function (ngCrud, $scope, model, url, parentUrl) {
            ngCrud.extendAggChildCtrl({
                name: 'skills',
                displayName: 'Skills',
                parentUrl: parentUrl,
                listUrl: url,
                ctrl: this,
                scope: $scope,
                model: model
            });
        }]);

    mod.controller('freelancerProfileCtrl', ['$scope','Restangular', function ($scope,Restangular) {
            $scope.getCurrentFreelancer = function () {
            Restangular.all("freelancers").customGET('current').then(function (response) {
                $scope.freelancer = response;
            });

            Restangular.all("users").customGET('me').then(function (response) {
                $scope.user = response;
            });
        };

            $scope.getCurrentFreelancer();

        }]);
    mod.controller('FreelancersagreementsCtrl', ['CrudCreator', '$scope', 'freelancerModel',
        function (ngCrud, $scope, model) {
            ngCrud.extendCompChildCtrl({
                name: 'agreements',
                displayName: 'Agreements',
                parent: 'author',
                ctrl: this,
                scope: $scope,
                model: model
            });
        }]);

    mod.controller('FreelancerscurriculumsCtrl', ['CrudCreator', '$scope', 'curriculumModel',
        function (ngCrud, $scope, model) {
            ngCrud.extendCompChildCtrl({
                name: 'curriculums',
                displayName: 'Curriculums',
                parent: 'freelancer',
                ctrl: this,
                scope: $scope,
                model: model
            });
        }]);

})(window.angular);
