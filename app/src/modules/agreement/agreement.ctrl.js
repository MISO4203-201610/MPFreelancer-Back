(function (ng) {
    var mod = ng.module('agreementModule');

    mod.controller('agreementCtrl', ['CrudCreator', '$scope',
        'agreementContext', 'agreementModel',
        function (ngCrud, $scope, url, model) {
            ngCrud.extendController({
                name: 'agreement',
                displayName: 'Agreement',
                ctrl: this,
                scope: $scope,
                model: model,
                url: url
            });
            this.fetchRecords();
        }]);
})(window.angular);
