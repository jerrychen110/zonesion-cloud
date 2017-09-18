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
            'getCourseList':{method: 'GET', isArray: true,url:'/api/home/course-list'},
            'getCourseExplore':{method:'GET',isArray:false,url:'api/home/explore'},
            'getCourseBase':{method:'GET',isArray:false,params: {id:'@id'},url:'api/courses/:id/course-base'},
            'getCourseLessons':{method:'GET',isArray:false,params: {id:'@id'},url:'api/courses/:id/course-lessons'},
            'getCourseReviews':{method:'GET',isArray:true,params: {id:'@id'},url:'/api/courses/:id/course-reviews'},
            'getCourseNotes':{method:'GET',isArray:true,params: {id:'@id'},url:'api/coursesLessonNote/:id'},
            'getCourseAttachements':{method:'GET',isArray:true,params: {id:'@id'},url:'/api/courses/:id/course-attachments'},
            'saveReview':{method:'POST',isArray:false,params: {id:'@id'},url:'api/courses/:id/course-reviews'}
        });
    }
})();
