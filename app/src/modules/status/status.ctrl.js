(function (ng) {
    var mod = ng.module('statusModule');

    mod.controller('statusCtrl', ['CrudCreator', '$scope',
        'statusContext', 'statusModel',
        function (ngCrud, $scope, url, model) {
            ngCrud.extendController({
                name: 'status',
                displayName: 'Status',
                ctrl: this,
                scope: $scope,
                model: model,
                url: url
            });
            this.fetchRecords();
        }]);

    mod.controller('StatusprojectCtrl', ['CrudCreator', '$scope',
        'projectModel', 'projectContext', 'statusContext',
        function (ngCrud, $scope, model, url, parentUrl) {
            ngCrud.extendAggChildCtrl({
                name: 'project',
                displayName: 'Project',
                parentUrl: parentUrl,
                listUrl: url,
                ctrl: this,
                scope: $scope,
                model: model
            });
            this.loadRefOptions();
        }]);
})(window.angular);
