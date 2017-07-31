(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('course-lesson-note-like', {
            parent: 'entity',
            url: '/course-lesson-note-like?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'zonesionCloudApplicationApp.courseLessonNoteLike.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/course-lesson-note-like/course-lesson-note-likes.html',
                    controller: 'CourseLessonNoteLikeController',
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
                    $translatePartialLoader.addPart('courseLessonNoteLike');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('course-lesson-note-like-detail', {
            parent: 'course-lesson-note-like',
            url: '/course-lesson-note-like/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'zonesionCloudApplicationApp.courseLessonNoteLike.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/course-lesson-note-like/course-lesson-note-like-detail.html',
                    controller: 'CourseLessonNoteLikeDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('courseLessonNoteLike');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'CourseLessonNoteLike', function($stateParams, CourseLessonNoteLike) {
                    return CourseLessonNoteLike.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'course-lesson-note-like',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('course-lesson-note-like-detail.edit', {
            parent: 'course-lesson-note-like-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/course-lesson-note-like/course-lesson-note-like-dialog.html',
                    controller: 'CourseLessonNoteLikeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['CourseLessonNoteLike', function(CourseLessonNoteLike) {
                            return CourseLessonNoteLike.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('course-lesson-note-like.new', {
            parent: 'course-lesson-note-like',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/course-lesson-note-like/course-lesson-note-like-dialog.html',
                    controller: 'CourseLessonNoteLikeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                userId: null,
                                createdTime: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('course-lesson-note-like', null, { reload: 'course-lesson-note-like' });
                }, function() {
                    $state.go('course-lesson-note-like');
                });
            }]
        })
        .state('course-lesson-note-like.edit', {
            parent: 'course-lesson-note-like',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/course-lesson-note-like/course-lesson-note-like-dialog.html',
                    controller: 'CourseLessonNoteLikeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['CourseLessonNoteLike', function(CourseLessonNoteLike) {
                            return CourseLessonNoteLike.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('course-lesson-note-like', null, { reload: 'course-lesson-note-like' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('course-lesson-note-like.delete', {
            parent: 'course-lesson-note-like',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/course-lesson-note-like/course-lesson-note-like-delete-dialog.html',
                    controller: 'CourseLessonNoteLikeDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['CourseLessonNoteLike', function(CourseLessonNoteLike) {
                            return CourseLessonNoteLike.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('course-lesson-note-like', null, { reload: 'course-lesson-note-like' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
