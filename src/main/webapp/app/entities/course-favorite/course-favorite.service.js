(function() {
    'use strict';
    angular
        .module('zonesionCloudApplicationApp')
        .factory('CourseFavorite', CourseFavorite);

    CourseFavorite.$inject = ['$resource'];

    function CourseFavorite ($resource) {
        var resourceUrl =  'api/course-favorites/:id';

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
