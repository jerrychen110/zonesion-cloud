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
            },
            params: {
                page: {
                    value: '1',
                    squash: true
                },
                sort: {
                    value: 'id,asc',
                    squash: true
                }
            }
        })
        .state('course-management-list', {
            parent: 'course-management',
            url: '/course-management-list?page&sort&courseType&courseSource',
            data: {
                authorities: ['ROLE_ADMIN'],
                pageTitle: 'courseManagement.home.title'
            },
            views: {
                'courseManagementSub': {
                    templateUrl: 'app/admin/course-management/course-management-list.html',
                    controller: 'CourseManagementListController',
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
                courseType: {
                	value: '0',
                	squash: true
                },
                courseSource: {
                	value: '0',
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
                custParams: ['$stateParams', function ($stateParams){
                	return {
                		courseType: $stateParams.courseType,
                		courseSource: $stateParams.courseSource
                	};
                }],
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('course-management');
                    return $translate.refresh();
                }]
            }
        })
        .state('course-management-create', {
        	parent: 'course-management',
            url: '/course-management-create?courseType&courseSource',
            data: {
                authorities: ['ROLE_ADMIN']
            },
            views: {
            	'content@': {
            		templateUrl: 'app/admin/course-management/course-management-create.html',
                    controller: 'CourseManagementCreateController',
                    controllerAs: 'vm'
            	}
            },
            params: {
            	courseType: {
                	value: '0',
                	squash: true
                },
                courseSource: {
                	value: '0',
                	squash: true
                }
            },
            resolve: {
                entity: function () {
                    return {
                    	id: null, userId: null, title: null, subTitle: null,
                        status: "0", courseType: null, courseSource: null, lessonNum: 0,
                        credit: "0", coverPicture: "", introduction: null, goals: null,
                        recommended: "0", recommendedSort: "0"
                    };
                },
                custParams: ['$stateParams', function ($stateParams){
                	return {
                		courseType: $stateParams.courseType,
                		courseSource: $stateParams.courseSource
                	};
                }]
            }
        })
        .state('course-management-edit', {
        	parent: 'course-management',
            url: '/{id}/course-management-edit',
            data: {
                authorities: ['ROLE_ADMIN']
            },
            views: {
            	'content@': {
            		templateUrl: 'app/admin/course-management/course-management-edit.html',
                    controller: 'CourseManagementEditController',
                    controllerAs: 'vm'
            	}
            },
            params: {
            	id: {
                	value: '1',
                	squash: true
                }
            },
            resolve: {
                entity: ['Course', '$stateParams', function (Course, $stateParams) {
                    return Course.get({id: $stateParams.id});
                }]
            }
        })
        .state('course-management-edit-base', {
        	parent: 'course-management-edit',
        	url: '/base',
        	data: {
        		authorities: ['ROLE_ADMIN']
        	},
        	views: {
        		'courseEdit': {
        			templateUrl: 'app/admin/course-management/course-management-edit-base.html',
                    controller: 'CourseManagementEditController',
                    controllerAs: 'vm'
        		}
        	},
            params: {
            	id: {
                	value: '1',
                	squash: true
                }
            },
            resolve: {
                entity: ['Course', '$stateParams', function (Course, $stateParams) {
                    return Course.get({id: $stateParams.id});
                }]
            }
        })
        .state('course-management-edit-detail', {
        	parent: 'course-management-edit',
        	url: '/detail',
        	data: {
        		authorities: ['ROLE_ADMIN']
        	},
        	views: {
        		'courseEdit': {
        			templateUrl: 'app/admin/course-management/course-management-edit-detail.html',
                    controller: 'CourseManagementEditController',
                    controllerAs: 'vm'
        		}
        	},
            params: {
            	id: {
                	value: '1',
                	squash: true
                }
            },
            resolve: {
                entity: ['Course', '$stateParams', function (Course, $stateParams) {
                    return Course.get({id: $stateParams.id});
                }]
            }
        })
        .state('course-management-edit-picture', {
        	parent: 'course-management-edit',
        	url: '/picture',
        	data: {
        		authorities: ['ROLE_ADMIN']
        	},
        	views: {
        		'courseEdit': {
        			templateUrl: 'app/admin/course-management/course-management-edit-picture.html',
                    controller: 'CourseManagementEditController',
                    controllerAs: 'vm'
        		}
        	},
            params: {
            	id: {
                	value: '1',
                	squash: true
                }
            },
            resolve: {
                entity: ['Course', '$stateParams', function (Course, $stateParams) {
                    return Course.get({id: $stateParams.id});
                }]
            }
        })
        .state('course-management-edit-lesson', {
        	parent: 'course-management-edit',
        	url: '/lesson',
        	data: {
        		authorities: ['ROLE_ADMIN']
        	},
        	views: {
        		'courseEdit': {
        			templateUrl: 'app/admin/course-management/course-management-edit-lesson.html',
                    controller: 'CourseManagementEditController',
                    controllerAs: 'vm'
        		}
        	},
            params: {
            	id: {
                	value: '1',
                	squash: true
                }
            },
            resolve: {
                entity: ['Course', '$stateParams', function (Course, $stateParams) {
                    return Course.get({id: $stateParams.id});
                }]
            }
        })
        .state('course-management-edit-files', {
        	parent: 'course-management-edit',
        	url: '/files',
        	data: {
        		authorities: ['ROLE_ADMIN']
        	},
        	views: {
        		'courseEdit': {
        			templateUrl: 'app/admin/course-management/course-management-edit-files.html',
                    controller: 'CourseManagementEditController',
                    controllerAs: 'vm'
        		}
        	},
            params: {
            	id: {
                	value: '1',
                	squash: true
                }
            },
            resolve: {
                entity: ['Course', '$stateParams', function (Course, $stateParams) {
                    return Course.get({id: $stateParams.id});
                }]
            }
        })
        
        /*
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
        });*/
    }
})();
