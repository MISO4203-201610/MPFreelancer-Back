(function (ng) {
    var mod = ng.module('categoryModule');

    mod.controller('categoryCtrl', ['CrudCreator', '$scope',
        'categoryContext', 'categoryModel',
        function (ngCrud, $scope, url, model) {
            ngCrud.extendController({
                name: 'category',
                displayName: 'Category',
                ctrl: this,
                scope: $scope,
                model: model,
                url: url
            });
            this.fetchRecords();
        }]);

    mod.controller('CategoryprojectsCtrl', ['CrudCreator', '$scope',
        'projectModel', 'projectContext', 'categoryContext',
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
