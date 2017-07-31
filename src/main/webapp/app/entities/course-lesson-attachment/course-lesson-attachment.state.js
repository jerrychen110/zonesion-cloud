(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('course-lesson-attachment', {
            parent: 'entity',
            url: '/course-lesson-attachment?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'zonesionCloudApplicationApp.courseLessonAttachment.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/course-lesson-attachment/course-lesson-attachments.html',
                    controller: 'CourseLessonAttachmentController',
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
                    $translatePartialLoader.addPart('courseLessonAttachment');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('course-lesson-attachment-detail', {
            parent: 'course-lesson-attachment',
            url: '/course-lesson-attachment/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'zonesionCloudApplicationApp.courseLessonAttachment.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/course-lesson-attachment/course-lesson-attachment-detail.html',
                    controller: 'CourseLessonAttachmentDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('courseLessonAttachment');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'CourseLessonAttachment', function($stateParams, CourseLessonAttachment) {
                    return CourseLessonAttachment.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'course-lesson-attachment',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('course-lesson-attachment-detail.edit', {
            parent: 'course-lesson-attachment-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/course-lesson-attachment/course-lesson-attachment-dialog.html',
                    controller: 'CourseLessonAttachmentDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['CourseLessonAttachment', function(CourseLessonAttachment) {
                            return CourseLessonAttachment.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('course-lesson-attachment.new', {
            parent: 'course-lesson-attachment',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/course-lesson-attachment/course-lesson-attachment-dialog.html',
                    controller: 'CourseLessonAttachmentDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                courseId: null,
                                userId: null,
                                title: null,
                                description: null,
                                link: null,
                                fileId: null,
                                fileUri: null,
                                fileMime: null,
                                fileSize: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('course-lesson-attachment', null, { reload: 'course-lesson-attachment' });
                }, function() {
                    $state.go('course-lesson-attachment');
                });
            }]
        })
        .state('course-lesson-attachment.edit', {
            parent: 'course-lesson-attachment',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/course-lesson-attachment/course-lesson-attachment-dialog.html',
                    controller: 'CourseLessonAttachmentDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['CourseLessonAttachment', function(CourseLessonAttachment) {
                            return CourseLessonAttachment.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('course-lesson-attachment', null, { reload: 'course-lesson-attachment' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('course-lesson-attachment.delete', {
            parent: 'course-lesson-attachment',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/course-lesson-attachment/course-lesson-attachment-delete-dialog.html',
                    controller: 'CourseLessonAttachmentDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['CourseLessonAttachment', function(CourseLessonAttachment) {
                            return CourseLessonAttachment.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('course-lesson-attachment', null, { reload: 'course-lesson-attachment' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
