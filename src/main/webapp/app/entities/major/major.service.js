(function() {
    'use strict';
    angular
        .module('zonesionCloudApplicationApp')
        .factory('Major', Major);

    Major.$inject = ['$resource'];

    function Major ($resource) {
        var resourceUrl =  'api/majors/:id';

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
            },
            'update': { method:'PUT' }
        });
    }
})();
