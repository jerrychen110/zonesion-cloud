(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('course-review', {
            parent: 'entity',
            url: '/course-review?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'zonesionCloudApplicationApp.courseReview.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/course-review/course-reviews.html',
                    controller: 'CourseReviewController',
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
                    $translatePartialLoader.addPart('courseReview');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('course-review-detail', {
            parent: 'course-review',
            url: '/course-review/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'zonesionCloudApplicationApp.courseReview.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/course-review/course-review-detail.html',
                    controller: 'CourseReviewDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('courseReview');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'CourseReview', function($stateParams, CourseReview) {
                    return CourseReview.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'course-review',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('course-review-detail.edit', {
            parent: 'course-review-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/course-review/course-review-dialog.html',
                    controller: 'CourseReviewDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['CourseReview', function(CourseReview) {
                            return CourseReview.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('course-review.new', {
            parent: 'course-review',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/course-review/course-review-dialog.html',
                    controller: 'CourseReviewDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                userId: null,
                                title: null,
                                content: null,
                                rating: null,
                                privacy: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('course-review', null, { reload: 'course-review' });
                }, function() {
                    $state.go('course-review');
                });
            }]
        })
        .state('course-review.edit', {
            parent: 'course-review',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/course-review/course-review-dialog.html',
                    controller: 'CourseReviewDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['CourseReview', function(CourseReview) {
                            return CourseReview.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('course-review', null, { reload: 'course-review' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('course-review.delete', {
            parent: 'course-review',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/course-review/course-review-delete-dialog.html',
                    controller: 'CourseReviewDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['CourseReview', function(CourseReview) {
                            return CourseReview.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('course-review', null, { reload: 'course-review' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
