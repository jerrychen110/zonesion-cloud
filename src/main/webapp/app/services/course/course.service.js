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
            'getLessons':{method:'GET',isArray:true,params: {id:'@id'},url:'/api/course-lessons/info/:id'},
            'getCourseReviews':{method:'GET',isArray:true,params: {id:'@id'},url:'/api/courses/:id/course-reviews'},
            'getCourseNotes':{method:'GET',isArray:true,params: {id:'@id'},url:'/api/courses/:id/course-notes'},
            'getCourseAttachements':{method:'GET',isArray:true,params: {id:'@id'},url:'/api/courses/:id/course-attachments'},
            'getLessonAttachments':{method:'GET',isArray:true,params: {id:'@id'},url:'/api/course-lessons/:id/lesson-attachments'},
            'saveReview':{method:'POST',isArray:false,params: {id:'@id',content:'@content',
            privacy:'@privacy',rating:'@rating',title:'@title',userId:'@userId'},url:'api/courses/:id/course-reviews'},
            'joinCourse':{method:'POST',isArray:false,params: {id:'@id'},url:'api/courses/:id/join'},
            'latestLearnLesson':{method:'GET',isArray:false,params: {id:'@id'},url:'api/courses/:id/latest-learn-lesson'}
        });
    }
})();
