(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('major', {
            parent: 'entity',
            url: '/major?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'zonesionCloudApplicationApp.major.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/major/majors.html',
                    controller: 'MajorController',
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
                    $translatePartialLoader.addPart('major');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('major-detail', {
            parent: 'major',
            url: '/major/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'zonesionCloudApplicationApp.major.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/major/major-detail.html',
                    controller: 'MajorDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('major');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Major', function($stateParams, Major) {
                    return Major.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'major',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('major-detail.edit', {
            parent: 'major-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/major/major-dialog.html',
                    controller: 'MajorDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Major', function(Major) {
                            return Major.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('major.new', {
            parent: 'major',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/major/major-dialog.html',
                    controller: 'MajorDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                major: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('major', null, { reload: 'major' });
                }, function() {
                    $state.go('major');
                });
            }]
        })
        .state('major.edit', {
            parent: 'major',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/major/major-dialog.html',
                    controller: 'MajorDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Major', function(Major) {
                            return Major.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('major', null, { reload: 'major' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('major.delete', {
            parent: 'major',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/major/major-delete-dialog.html',
                    controller: 'MajorDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Major', function(Major) {
                            return Major.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('major', null, { reload: 'major' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
