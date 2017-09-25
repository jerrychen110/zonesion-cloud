(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('learn', {
            parent: 'courses',
            url: '/learn/:id',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'app/views/learn/learn.html',
                    controller: 'LearnController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                    $translatePartialLoader.addPart('home');
                    return $translate.refresh();
                }]
            }
        })
        .state('learntest', {
            url: '/learntest/:id',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'app/views/learn/learntest.html',
                    controller: 'LearnTestController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                    $translatePartialLoader.addPart('home');
                    return $translate.refresh();
                }]
            }
        })
    }
})();
