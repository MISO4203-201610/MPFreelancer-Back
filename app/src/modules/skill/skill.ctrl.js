(function (ng) {
    var mod = ng.module('skillModule');

    mod.controller('skillCtrl', ['CrudCreator', '$scope',
        'skillContext', 'skillModel',
        function (ngCrud, $scope, url, model) {
            ngCrud.extendController({
                name: 'skill',
                displayName: 'Skill',
                ctrl: this,
                scope: $scope,
                model: model,
                url: url
            });
            this.fetchRecords();
        }]);

    mod.controller('SkillsfreelancersCtrl', ['CrudCreator', '$scope',
        'freelancerModel', 'freelancerContext', 'skillContext',
        function (ngCrud, $scope, model, url, parentUrl) {
            ngCrud.extendAggChildCtrl({
                name: 'freelancers',
                displayName: 'Freelancers',
                parentUrl: parentUrl,
                listUrl: url,
                ctrl: this,
                scope: $scope,
                model: model
            });
        }]);

    mod.controller('ExpectedskillsprojectsCtrl', ['CrudCreator', '$scope',
        'projectModel', 'projectContext', 'skillContext',
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
})(window.angular);
