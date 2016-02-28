(function (ng) {
    var mod = ng.module('statusModule', ['ngCrud']);

    mod.constant('statusContext', 'statuss');

    mod.constant('statusModel', {
        fields: [{
                name: 'name',
                displayName: 'Name',
                type: 'String',
                required: true
            }],
        childs: [{
                name: 'project',
                displayName: 'Project',
                //template: '', //override generic template
                ctrl: 'StatusprojectCtrl',
                owned: false
            }]});
})(window.angular);
