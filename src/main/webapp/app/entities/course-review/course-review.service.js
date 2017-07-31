(function() {
    'use strict';
    angular
        .module('zonesionCloudApplicationApp')
        .factory('CourseReview', CourseReview);

    CourseReview.$inject = ['$resource'];

    function CourseReview ($resource) {
        var resourceUrl =  'api/course-reviews/:id';

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
