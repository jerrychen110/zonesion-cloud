(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .factory('ExploreService', ExploreService);

    ExploreService.$inject = ['$resource'];

    function ExploreService ($resource) {
        var resourceUrl =  'api/home/explore?pageNo=:pageNo&pageSize=:pageSize&filter=:filter';
        return $resource(resourceUrl, {}, {
        	'query': { method: 'GET', isArray: false},
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
