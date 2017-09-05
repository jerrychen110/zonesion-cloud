(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .factory('ReviewsService', ReviewsService);

    ReviewsService.$inject = ['$resource'];
    
    function ReviewsService ($resource) {
        var resourceUrl =  'api/coursesReview/:id';

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
