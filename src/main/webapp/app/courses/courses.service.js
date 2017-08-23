(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .factory('CoursesService', CoursesService);

    CoursesService.$inject = ['$resource'];
    
    function CoursesService ($resource) {
        var resourceUrl =  'api/courses/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', params:{courseId:'1'},isArray: false},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            }
        });
    }
})();
