(function (ng) {
    var mod = ng.module('projectSponsorModule');

    mod.controller('projectSponsorCtrl', ['CrudCreator', '$scope',
        'projectSponsorContext', 'projectSponsorModel',
        function (ngCrud, $scope, url, model) {
            ngCrud.extendController({
                name: 'projectSponsor',
                displayName: 'Project Sponsor',
                ctrl: this,
                scope: $scope,
                model: model,
                url: url
            });
            this.fetchRecords();
            this.globalActions.create = {
                show: function () {
                    return false;
                }
            };
        }]);

    mod.controller('SponsorprojectsCtrl', ['CrudCreator', '$scope',
        'projectModel', 'projectContext', 'projectSponsorContext',
        function (ngCrud, $scope, model, url, parentUrl) {
            ngCrud.extendAggChildCtrl({
                name: 'projects',
                displayName: 'Projects',
                parentUrl: parentUrl,
                listUrl: url,
                ctrl: this,
                scope: $scope,
                model: model
            });
            this.loadRefOptions();
        }]);

    mod.controller('projectSponsorProfileCtrl', ['$scope','Restangular', function ($scope,Restangular) {
            $scope.getCurrentProjectSponsor = function () {
            Restangular.all("projectSponsors").customGET('current').then(function (response) {
                $scope.projectSponsor = response;
            });

            Restangular.all("users").customGET('me').then(function (response) {
                $scope.user = response;
            });
        };

            $scope.getCurrentProjectSponsor();

        }]);
})(window.angular);
