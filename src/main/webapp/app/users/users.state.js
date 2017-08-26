(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('user', {
            parent: 'app',
            url: '/user/{id}',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'app/users/users.html',
                    controller: 'UsersController',
                    controllerAs: 'vm'
                }
            }
        });
        
    }
})();
