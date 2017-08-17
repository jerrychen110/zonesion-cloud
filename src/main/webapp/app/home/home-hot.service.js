(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .factory('HomeHotService', HomeHotService);

    HomeHotService.$inject = ['$resource'];
    
    function HomeHotService ($resource) {
        var resourceUrl =  'api/courses/hot/:id';

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
            }
        });
    }
    
})();
