(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider','$urlRouterProvider'];

    function stateConfig($stateProvider,$urlRouterProvider) {
    	// $urlRouterProvider.when("", "/courses");
        $stateProvider
        .state('courses', {
            parent: 'app',
            url: '/courses/{id}',
            data: {
            	authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'app/views/courses/courses.html',
                    controller: 'CoursesController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('course');
                    return $translate.refresh();
                }],
            }
        })
        .state("courses.reviews", {
            parent: 'courses',
            url:"/reviews",
            data: {
            	authorities: []
            }
        })
        .state("courses.notes", {
            parent: 'courses',
            url:"/notes",
            data: {
            	authorities: []
            }
        })
        .state("courses.database", {
            parent: 'courses',
            url:"/database",
            data: {
              authorities: []
            }
        });
        // $urlRouterProvider.otherwise('/');
    }
})();
