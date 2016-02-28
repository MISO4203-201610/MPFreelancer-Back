(function (ng) {
    var mod = ng.module('projectModule', ['ngCrud']);

    mod.constant('projectContext', 'projects');

    mod.constant('projectModel', {
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
                name: 'price',
                displayName: 'Price',
                type: 'Integer',
                required: true
            }, {
                name: 'deadLine',
                displayName: 'Dead Line',
                type: 'Date',
                required: true
            }, {
                name: 'publicationDate',
                displayName: 'Publication Date',
                type: 'Date',
                required: true
            }, {
                name: 'startDate',
                displayName: 'Start Date',
                type: 'Date',
                required: true
            }, {
                name: 'status',
                displayName: 'Status',
                type: 'Reference',
                url: 'statusContext',
                options: [],
                required: true
            }, {
                name: 'category',
                displayName: 'Category',
                type: 'Reference',
                url: 'categoryContext',
                options: [],
                required: true
            }],
        childs: [{
                name: 'expectedskills',
                displayName: 'Expectedskills',
                //template: '', //override generic template
                ctrl: 'ProjectsexpectedskillsCtrl',
                owned: false
            }]});
})(window.angular);
