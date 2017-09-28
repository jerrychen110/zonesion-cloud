(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .factory('LearnService', LearnService);

    LearnService.$inject = ['$resource'];

    function LearnService ($resource) {
        var resourceUrl =  'api/course-lessons';

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
            },
            'doLearn':{method:'POST',isArray:false,params:{id:'@id',userId:'@userId',courseId:'@courseId'},url:'/api/course-lessons/:id/do-learn'},
            'overLearn':{method:'PUT',isArray:false,params:{id:'@id',userId:'@userId',courseId:'@courseId'},url:'/api//course-lessons/:id/learn-over'}


        });
    }
})();
