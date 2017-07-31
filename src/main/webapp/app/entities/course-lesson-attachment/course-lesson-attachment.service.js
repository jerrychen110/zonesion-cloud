(function() {
    'use strict';
    angular
        .module('zonesionCloudApplicationApp')
        .factory('CourseLessonAttachment', CourseLessonAttachment);

    CourseLessonAttachment.$inject = ['$resource'];

    function CourseLessonAttachment ($resource) {
        var resourceUrl =  'api/course-lesson-attachments/:id';

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
