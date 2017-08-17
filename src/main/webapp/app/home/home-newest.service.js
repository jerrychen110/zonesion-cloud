(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .factory('HomeNewestService', HomeNewestService);

    HomeNewestService.$inject = ['$resource'];
    
    function HomeNewestService ($resource) {
        var resourceUrl =  'api/courses/newest/:id';

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
