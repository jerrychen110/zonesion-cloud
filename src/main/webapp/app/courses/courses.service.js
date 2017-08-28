(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .factory('CoursesService', CoursesService);

    CoursesService.$inject = ['$resource'];
    
    function CoursesService ($resource) {
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
            }
        });
    }
})();
