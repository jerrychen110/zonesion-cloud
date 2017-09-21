(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .factory('CourseManagementService', CourseManagementService);

    CourseManagementService.$inject = ['$resource'];

    function CourseManagementService ($resource) {
        var resourceUrl =  'api/coursesdto/:id';

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
            'getMajors':{method:'GET',isArray:true,url:'api/majors/order'},
            'addChapter':{method:'POST',isArray:false,params:{courseId:'@courseId'},url:'/api/courses/:courseId/chapters'},
            'addLesson':{method:'POST',isArray:false,params:{courseId:'@courseId'},url:'/api/courses/:courseId/lessons'}
        });
    }
})();
