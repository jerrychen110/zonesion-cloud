(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .factory('InfoService', InfoService);

    InfoService.$inject = ['$resource'];
    
    function InfoService ($resource) {
        var resourceUrl =  'api/courses/:id';

        return $resource(resourceUrl, {id:'@id'}, {
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
