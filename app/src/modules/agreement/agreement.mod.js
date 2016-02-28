(function (ng) {
    var mod = ng.module('agreementModule', ['ngCrud']);

    mod.constant('agreementContext', 'agreements');

    mod.constant('agreementModel', {
        fields: [{
                name: 'name',
                displayName: 'Name',
                type: 'String',
                required: true
            }, {
                name: 'price',
                displayName: 'Price',
                type: 'Integer',
                required: true
            }, {
                name: 'rate',
                displayName: 'Rate',
                type: 'Double',
                required: false
            }, {
                name: 'selected',
                displayName: 'Selected',
                type: 'Boolean',
                required: false
            }, {
                name: 'freelancer',
                displayName: 'Freelancer',
                type: 'Reference',
                url: 'freelancerContext',
                options: [],
                required: true
            }, {
                name: 'project',
                displayName: 'Project',
                type: 'Reference',
                url: 'projectstatusContext',
                options: [],
                required: true
            }]});
})(window.angular);
