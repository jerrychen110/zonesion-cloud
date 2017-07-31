(function() {
    'use strict';
    angular
        .module('zonesionCloudApplicationApp')
        .factory('CourseLessonNote', CourseLessonNote);

    CourseLessonNote.$inject = ['$resource'];

    function CourseLessonNote ($resource) {
        var resourceUrl =  'api/course-lesson-notes/:id';

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
