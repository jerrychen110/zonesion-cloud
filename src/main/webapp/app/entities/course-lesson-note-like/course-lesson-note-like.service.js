(function() {
    'use strict';
    angular
        .module('zonesionCloudApplicationApp')
        .factory('CourseLessonNoteLike', CourseLessonNoteLike);

    CourseLessonNoteLike.$inject = ['$resource', 'DateUtils'];

    function CourseLessonNoteLike ($resource, DateUtils) {
        var resourceUrl =  'api/course-lesson-note-likes/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.createdTime = DateUtils.convertDateTimeFromServer(data.createdTime);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
