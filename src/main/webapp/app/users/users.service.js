(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .factory('UsersService', UsersService);

    UsersService.$inject = ['$resource'];

    function UsersService ($resource) {
        var resourceUrl =  'api/user/:id';

        return $resource(resourceUrl, {id:'@id'}, {
        	'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'getCourseLearning':{method:'GET',isArray:true,url:'/api/courses/my/learning-courses'},
            'getCourseFavorite':{method:'GET',isArray:true,url:'/api/courses/my/favorite-courses'},
            'getCourseAccount':{method:'GET',isArray:false,url:'/api/account'}
        });
    }
})();
