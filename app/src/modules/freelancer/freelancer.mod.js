(function (ng) {
    var mod = ng.module('freelancerModule', ['ngCrud']);

    mod.constant('freelancerContext', 'freelancers');

    mod.constant('freelancerModel', {
        fields: [{
                name: 'name',
                displayName: 'Name',
                type: 'String',
                required: true
            }, {
                name: 'rate',
                displayName: 'Rate',
                type: 'Integer',
                required: true
            }, {
                name: 'bithday',
                displayName: 'Bithday',
                type: 'Date',
                required: true
            }, {
                name: 'picture',
                displayName: 'Picture',
                type: 'Image',
                required: true
            }],
        childs: [{
                name: 'titles',
                displayName: 'Titles',
                //template: '', //override generic template
                ctrl: 'FreelancertitlesCtrl',
                owned: true
            }, {
                name: 'skills',
                displayName: 'Skills',
                //template: '', //override generic template
                ctrl: 'FreelancersskillsCtrl',
                owned: false
            }, {
                name: 'agreements',
                displayName: 'Agreements',
                //template: '', //override generic template
                ctrl: 'FreelancersagreementsCtrl',
                owned: false
            },{
                name: 'curriculums',
                displayName: 'Curriculums',
                //template: '', //override generic template
                ctrl: 'FreelancerscurriculumsCtrl',
                owned: true
            }]
    });
})(window.angular);
