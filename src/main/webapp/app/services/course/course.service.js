(function() {
    'use strict';
    angular
        .module('zonesionCloudApplicationApp')
        .factory('CourseService', CourseService);

    CourseService.$inject = ['$resource'];

    function CourseService ($resource) {
        var resourceUrl =  'api/courses/:id';

        return $resource(resourceUrl, {}, {
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
            'update': { method:'PUT' },
            'getNewCourse':{method: 'GET', isArray: true,url:'api/home/newest/:id'},
            'getHotCourse':{method: 'GET', isArray: true,url:'api/home/hot/:id'},
            'getRecommendCourse':{method: 'GET', isArray: true,url:'api/home/recommended/:id'},
            'getCourseLessons':{method:'GET',isArray:false,params: {id:'@id'},url:'api/courses/:id/course-lessons'}
        });
    }
})();
