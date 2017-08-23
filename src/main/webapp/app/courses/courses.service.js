(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .factory('CoursesService', CoursesService);

    CoursesService.$inject = ['$resource'];
    
    function CoursesService ($resource) {
        var resourceUrl =  'api/courses/:id';

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

    /*function HomeService ($hot) {
        var resourceUrl =  'api/courses/hot/:id';

        return $hot(resourceUrl, {}, {
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
    
    function HomeService ($recommended) {
        var resourceUrl =  'api/courses/recommended/:id';

        return $recommended(resourceUrl, {}, {
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
    }*/
})();
