(function (ng) {
    var mod = ng.module('projectSponsorModule', ['ngCrud']);

    mod.constant('projectSponsorContext', 'projectSponsors');

    mod.constant('projectSponsorModel', {
        fields: [{
                name: 'name',
                displayName: 'Name',
                type: 'String',
                required: true
            }, {
                name: 'company',
                displayName: 'Company',
                type: 'String',
                required: true
            }, {
                name: 'picture',
                displayName: 'Picture',
                type: 'Image',
                required: true
            }],
        childs: [{
                name: 'projects',
                displayName: 'Projects',
                //template: '', //override generic template
                ctrl: 'SponsorprojectsCtrl',
                owned: false
            }]});
})(window.angular);
