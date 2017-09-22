(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .factory('UsersFavoritedService', UsersFavoritedService);

    UsersFavoritedService.$inject = ['$resource'];
    
    function UsersFavoritedService ($resource) {
        var resourceUrl =  'api/user/:id/favorited';

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
