(function (ng) {
    var mod = ng.module('skillModule', ['ngCrud']);

    mod.constant('skillContext', 'skills');

    mod.constant('skillModel', {
        fields: [{
                name: 'name',
                displayName: 'Name',
                type: 'String',
                required: true
            }, {
                name: 'description',
                displayName: 'Description',
                type: 'String',
                required: true
            }, {
                name: 'level',
                displayName: 'Level',
                type: 'String',
                required: true
            }],
        childs: [{
                name: 'freelancers',
                displayName: 'Freelancers',
                //template: '', //override generic template
                ctrl: 'SkillsfreelancersCtrl',
                owned: false
            }, {
                name: 'projects',
                displayName: 'Projects',
                //template: '', //override generic template
                ctrl: 'ExpectedskillsprojectsCtrl',
                owned: false
            }]});
})(window.angular);
