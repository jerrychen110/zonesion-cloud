(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .factory('ExploreService', ExploreService);

    ExploreService.$inject = ['$resource'];
    
    function ExploreService ($resource) {
        var resourceUrl =  'api/home/explore?pageNo=:pageNo&pageSize=:pageSize&filter=:filter';
        /*if ($stateParams.page ==null && $stateParams.size ==null && $stateParams.filter ==null){
        	$stateParams.page = 1;
        	$stateParams.size = 10;
        	$stateParams.filters = "newest";
        }*/
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
