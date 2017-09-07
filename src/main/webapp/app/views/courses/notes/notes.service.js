(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .factory('NotesService', NotesService);

    NotesService.$inject = ['$resource'];
    
    function NotesService ($resource) {
        var resourceUrl =  'api/coursesLessonNote/:id';

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
