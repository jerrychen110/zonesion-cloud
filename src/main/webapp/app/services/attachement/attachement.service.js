(function() {
    'use strict';
    angular
        .module('zonesionCloudApplicationApp')
        .factory('AttachementService', AttachementService);

    AttachementService.$inject = ['$resource'];

    function AttachementService ($resource) {
        var resourceUrl =  'api/course-lesson-attachments/:id';

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
            'downloadAttachement':{method:'GET',isArray:false,params: {id:'@id'},url:'/api/course-lesson-attachments/:id/download'}
        });
    }
})();
