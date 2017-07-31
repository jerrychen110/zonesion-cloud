(function() {
    'use strict';
    angular
        .module('zonesionCloudApplicationApp')
        .factory('CourseLessonLearn', CourseLessonLearn);

    CourseLessonLearn.$inject = ['$resource'];

    function CourseLessonLearn ($resource) {
        var resourceUrl =  'api/course-lesson-learns/:id';

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
            'update': { method:'PUT' }
        });
    }
})();
