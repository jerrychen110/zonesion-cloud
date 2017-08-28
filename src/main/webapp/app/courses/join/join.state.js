(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('join', {
            parent: 'app',
            url: '/join/{id}',
            data: {
                authorities: ['ROLE_USER']
            },
            views: {
                'content@': {
                    templateUrl: 'app/courses/join/join.html',
                    controller: 'JoinController',
                    controllerAs: 'vm'
                }
            }
        });
        
    }
})();
