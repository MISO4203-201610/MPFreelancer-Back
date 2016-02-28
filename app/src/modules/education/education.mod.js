(function (ng) {
    var mod = ng.module('educationModule', ['ngCrud']);

    mod.constant('educationContext', 'educations');

    mod.constant('educationModel', {
        fields: [{
                name: 'name',
                displayName: 'Name',
                type: 'String',
                required: true
            }, {
                name: 'startDate',
                displayName: 'Start Date',
                type: 'Date',
                required: true
            }, {
                name: 'endDate',
                displayName: 'End Date',
                type: 'Date',
                required: true
            }, {
                name: 'institution',
                displayName: 'Institution',
                type: 'String',
                required: true
            }, {
                name: 'title',
                displayName: 'Title',
                type: 'String',
                required: true
            }, {
                name: 'description',
                displayName: 'Description',
                type: 'String',
                required: true
            }]});
})(window.angular);
