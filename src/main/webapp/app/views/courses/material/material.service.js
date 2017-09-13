(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .factory('MaterialService', MaterialService);

    MaterialService.$inject = ['$resource'];
    
    function MaterialService ($resource) {
        var resourceUrl =  'api/info/:id';

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
