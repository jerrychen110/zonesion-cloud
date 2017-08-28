(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('course-management', {
            parent: 'admin',
            url: '/course-management?page&sort',
            data: {
                authorities: ['ROLE_ADMIN'],
                pageTitle: 'courseManagement.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/admin/course-management/course-management.html',
                    controller: 'CourseManagementController',
                    controllerAs: 'vm'
                }
            },            params: {
                page: {
                    value: '1',
                    squash: true
                },
                sort: {
                    value: 'id,asc',
                    squash: true
                }
            },
            resolve: {
                pagingParams: ['$stateParams', 'PaginationUtil', function ($stateParams, PaginationUtil) {
                    return {
                        page: PaginationUtil.parsePage($stateParams.page),
                        sort: $stateParams.sort,
                        predicate: PaginationUtil.parsePredicate($stateParams.sort),
                        ascending: PaginationUtil.parseAscending($stateParams.sort)
                    };
                }],
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('course-management');
                    return $translate.refresh();
                }]

            }        })
        .state('course-management.new', {
            url: '/new',
            data: {
                authorities: ['ROLE_ADMIN']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/admin/course-management/course-management-dialog.html',
                    controller: 'CourseManagementDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                id: null, login: null, name: null, email: null,
                                activated: true, createdBy: null, createdDate: null,
                                lastModifiedBy: null, lastModifiedDate: null,
                                authorities: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('course-management', null, { reload: true });
                }, function() {
                    $state.go('course-management');
                });
            }]
        })
        .state('course-management.edit', {
            url: '/{login}/edit',
            data: {
                authorities: ['ROLE_ADMIN']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/admin/course-management/course-management-dialog.html',
                    controller: 'CourseManagementDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['User', function(User) {
                            return User.get({login : $stateParams.login});
                        }]
                    }
                }).result.then(function() {
                    $state.go('course-management', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('course-management-detail', {
            parent: 'course-management',
            url: '/{login}',
            data: {
                authorities: ['ROLE_ADMIN'],
                pageTitle: 'course-management.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/admin/course-management/course-management-detail.html',
                    controller: 'CourseManagementDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('course-management');
                    return $translate.refresh();
                }]
            }
        })
        .state('course-management.delete', {
            url: '/{login}/delete',
            data: {
                authorities: ['ROLE_ADMIN']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/admin/course-management/course-management-delete-dialog.html',
                    controller: 'CourseManagementDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['User', function(User) {
                            return User.get({login : $stateParams.login});
                        }]
                    }
                }).result.then(function() {
                    $state.go('course-management', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }
})();
