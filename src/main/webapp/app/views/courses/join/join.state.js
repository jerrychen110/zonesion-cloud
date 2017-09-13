(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider', '$urlRouterProvider'];

    function stateConfig($stateProvider, $urlRouterProvider) {
    	$urlRouterProvider.when("", "/join");
    	$stateProvider
        .state('join', {
            parent: 'app',
            url: '/join/{id}',
            data: {
                authorities: ['ROLE_USER']
            },
            views: {
                'content@': {
                    templateUrl: 'app/views/courses/join/join.html',
                    controller: 'JoinController',
                    controllerAs: 'vm'
                }
            }
        })
        .state("join.material", {
            url:"/material",
            data: {
            	authorities: ['ROLE_USER']
            },
            templateUrl: "app/views/courses/material/material.html",
            controller: 'MaterialController',
            controllerAs: 'vm'
        })
        .state("join.reviews", {
            url:"/reviews",
            data: {
            	authorities: ['ROLE_USER']
            },
            templateUrl: "app/views/courses/reviews/reviews-edit.html",
            controller: 'ReviewsController',
            controllerAs: 'vm'
        })
        .state("join.notes", {
            url:"/notes",
            data: {
            	authorities: ['ROLE_USER']
            },
            templateUrl: "app/views/courses/notes/notes.html",
            controller: 'NotesController',
            controllerAs: 'vm'
        })
        .state("join.info", {
            url:"/info",
            data: {
            	authorities: ['ROLE_USER']
            },
            templateUrl: "app/views/courses/info/info.html",
            controller: 'InfoController',
            controllerAs: 'vm'
        })
        ;

    }
})();
