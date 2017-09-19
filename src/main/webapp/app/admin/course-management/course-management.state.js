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
            url: '/course-management-list?courseType&courseSource',
            data: {
                authorities: ['ROLE_ADMIN'],
                pageTitle: 'courseManagement.home.title'
            },
            views: {
                'courseManagementSub': {
                    templateUrl: 'app/admin/course-management/list/course-management-list.html',
                    controller: 'CourseManagementListController',
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
            		templateUrl: 'app/admin/course-management/create/course-management-create.html',
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
            		templateUrl: 'app/admin/course-management/edit/course-management-edit.html',
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
        			templateUrl: 'app/admin/course-management/edit/base/course-management-edit-base.html',
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
        			templateUrl: 'app/admin/course-management/edit/detail/course-management-edit-detail.html',
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
        			templateUrl: 'app/admin/course-management/edit/picture/course-management-edit-picture.html',
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
        			templateUrl: 'app/admin/course-management/edit/lesson/course-management-edit-lesson.html',
                    controller: 'EditLessonController',
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
        			templateUrl: 'app/admin/course-management/edit/file/course-management-edit-files.html',
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
    }
})();
