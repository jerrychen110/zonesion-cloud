(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('course-lesson', {
            parent: 'entity',
            url: '/course-lesson?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'zonesionCloudApplicationApp.courseLesson.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/course-lesson/course-lessons.html',
                    controller: 'CourseLessonController',
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
                    $translatePartialLoader.addPart('courseLesson');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('course-lesson-detail', {
            parent: 'course-lesson',
            url: '/course-lesson/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'zonesionCloudApplicationApp.courseLesson.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/course-lesson/course-lesson-detail.html',
                    controller: 'CourseLessonDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('courseLesson');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'CourseLesson', function($stateParams, CourseLesson) {
                    return CourseLesson.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'course-lesson',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('course-lesson-detail.edit', {
            parent: 'course-lesson-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/course-lesson/course-lesson-dialog.html',
                    controller: 'CourseLessonDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['CourseLesson', function(CourseLesson) {
                            return CourseLesson.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('course-lesson.new', {
            parent: 'course-lesson',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/course-lesson/course-lesson-dialog.html',
                    controller: 'CourseLessonDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                userId: null,
                                number: null,
                                seq: null,
                                title: null,
                                summary: null,
                                courseLessonType: null,
                                content: null,
                                credit: null,
                                mediaId: null,
                                mediaSource: null,
                                mediaName: null,
                                mediaUri: null,
                                learnedNum: null,
                                viewedNum: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('course-lesson', null, { reload: 'course-lesson' });
                }, function() {
                    $state.go('course-lesson');
                });
            }]
        })
        .state('course-lesson.edit', {
            parent: 'course-lesson',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/course-lesson/course-lesson-dialog.html',
                    controller: 'CourseLessonDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['CourseLesson', function(CourseLesson) {
                            return CourseLesson.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('course-lesson', null, { reload: 'course-lesson' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('course-lesson.delete', {
            parent: 'course-lesson',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/course-lesson/course-lesson-delete-dialog.html',
                    controller: 'CourseLessonDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['CourseLesson', function(CourseLesson) {
                            return CourseLesson.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('course-lesson', null, { reload: 'course-lesson' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
