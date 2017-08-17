(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .factory('HomeRecommendedService', HomeRecommendedService);

    HomeRecommendedService.$inject = ['$resource'];
       
    function HomeRecommendedService ($resource) {
        var resourceUrl =  'api/courses/recommended/:id';

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
