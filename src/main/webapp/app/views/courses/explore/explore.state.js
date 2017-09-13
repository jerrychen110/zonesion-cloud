(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('explore', {
            parent: 'app',
            url: '/courses/explore?pageNo={number}&pageSize={size}&filters={filter}',
            /*params: {"number":null,"size":null,"filter":null},*/
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'app/views/courses/explore/explore.html',
                    controller: 'ExploreController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                    $translatePartialLoader.addPart('course');
                    return $translate.refresh();
                }]
            }
        });

    }
})();
