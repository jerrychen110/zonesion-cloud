(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('course-lesson-learn', {
            parent: 'entity',
            url: '/course-lesson-learn?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'zonesionCloudApplicationApp.courseLessonLearn.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/course-lesson-learn/course-lesson-learns.html',
                    controller: 'CourseLessonLearnController',
                    controllerAs: 'vm'
                }
            },
            params: {
                page: {
                    value: '1',
                    squash: true
                },
                sort: {
                    value: 'id,asc',
                    squash: true
                },
                search: null
            },
            resolve: {
                pagingParams: ['$stateParams', 'PaginationUtil', function ($stateParams, PaginationUtil) {
                    return {
                        page: PaginationUtil.parsePage($stateParams.page),
                        sort: $stateParams.sort,
                        predicate: PaginationUtil.parsePredicate($stateParams.sort),
                        ascending: PaginationUtil.parseAscending($stateParams.sort),
                        search: $stateParams.search
                    };
                }],
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('courseLessonLearn');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('course-lesson-learn-detail', {
            parent: 'course-lesson-learn',
            url: '/course-lesson-learn/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'zonesionCloudApplicationApp.courseLessonLearn.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/course-lesson-learn/course-lesson-learn-detail.html',
                    controller: 'CourseLessonLearnDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('courseLessonLearn');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'CourseLessonLearn', function($stateParams, CourseLessonLearn) {
                    return CourseLessonLearn.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'course-lesson-learn',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('course-lesson-learn-detail.edit', {
            parent: 'course-lesson-learn-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/course-lesson-learn/course-lesson-learn-dialog.html',
                    controller: 'CourseLessonLearnDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['CourseLessonLearn', function(CourseLessonLearn) {
                            return CourseLessonLearn.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('course-lesson-learn.new', {
            parent: 'course-lesson-learn',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/course-lesson-learn/course-lesson-learn-dialog.html',
                    controller: 'CourseLessonLearnDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                courseId: null,
                                userId: null,
                                durationId: null,
                                isComplete: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('course-lesson-learn', null, { reload: 'course-lesson-learn' });
                }, function() {
                    $state.go('course-lesson-learn');
                });
            }]
        })
        .state('course-lesson-learn.edit', {
            parent: 'course-lesson-learn',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/course-lesson-learn/course-lesson-learn-dialog.html',
                    controller: 'CourseLessonLearnDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['CourseLessonLearn', function(CourseLessonLearn) {
                            return CourseLessonLearn.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('course-lesson-learn', null, { reload: 'course-lesson-learn' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('course-lesson-learn.delete', {
            parent: 'course-lesson-learn',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/course-lesson-learn/course-lesson-learn-delete-dialog.html',
                    controller: 'CourseLessonLearnDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['CourseLessonLearn', function(CourseLessonLearn) {
                            return CourseLessonLearn.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('course-lesson-learn', null, { reload: 'course-lesson-learn' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
