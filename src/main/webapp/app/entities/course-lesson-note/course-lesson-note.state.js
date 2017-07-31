(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('course-lesson-note', {
            parent: 'entity',
            url: '/course-lesson-note?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'zonesionCloudApplicationApp.courseLessonNote.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/course-lesson-note/course-lesson-notes.html',
                    controller: 'CourseLessonNoteController',
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
                    $translatePartialLoader.addPart('courseLessonNote');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('course-lesson-note-detail', {
            parent: 'course-lesson-note',
            url: '/course-lesson-note/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'zonesionCloudApplicationApp.courseLessonNote.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/course-lesson-note/course-lesson-note-detail.html',
                    controller: 'CourseLessonNoteDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('courseLessonNote');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'CourseLessonNote', function($stateParams, CourseLessonNote) {
                    return CourseLessonNote.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'course-lesson-note',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('course-lesson-note-detail.edit', {
            parent: 'course-lesson-note-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/course-lesson-note/course-lesson-note-dialog.html',
                    controller: 'CourseLessonNoteDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['CourseLessonNote', function(CourseLessonNote) {
                            return CourseLessonNote.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('course-lesson-note.new', {
            parent: 'course-lesson-note',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/course-lesson-note/course-lesson-note-dialog.html',
                    controller: 'CourseLessonNoteDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                courseId: null,
                                userId: null,
                                content: null,
                                length: null,
                                likeNum: null,
                                isPrivate: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('course-lesson-note', null, { reload: 'course-lesson-note' });
                }, function() {
                    $state.go('course-lesson-note');
                });
            }]
        })
        .state('course-lesson-note.edit', {
            parent: 'course-lesson-note',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/course-lesson-note/course-lesson-note-dialog.html',
                    controller: 'CourseLessonNoteDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['CourseLessonNote', function(CourseLessonNote) {
                            return CourseLessonNote.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('course-lesson-note', null, { reload: 'course-lesson-note' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('course-lesson-note.delete', {
            parent: 'course-lesson-note',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/course-lesson-note/course-lesson-note-delete-dialog.html',
                    controller: 'CourseLessonNoteDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['CourseLessonNote', function(CourseLessonNote) {
                            return CourseLessonNote.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('course-lesson-note', null, { reload: 'course-lesson-note' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
