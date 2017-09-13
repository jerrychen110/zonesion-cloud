(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider','$urlRouterProvider'];

    function stateConfig($stateProvider,$urlRouterProvider) {
    	$urlRouterProvider.when("", "/courses");
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
                entity: ['$stateParams', 'Course', function($stateParams, Course) {
                    return Course.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'course',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state("courses.reviews", {
            parent: 'courses',
            url:"/reviews",
            data: {
            	authorities: []
            },
            templateUrl: "app/views/courses/reviews/reviews.html",
            controller: 'ReviewsController',
            controllerAs: 'vm'
        })
        .state("courses.notes", {
            parent: 'courses',
            url:"/notes",
            data: {
            	authorities: []
            },
            templateUrl: "app/views/courses/notes/notes.html",
            controller: 'NotesController',
            controllerAs: 'vm'
        });
        $urlRouterProvider.otherwise('/');
    }
})();
