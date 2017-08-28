(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('explore', {
            parent: 'app',
            url: '/course/explore?pageNo={number}&pageSize={size}&filters={filter}', 
            /*params: {"number":null,"size":null,"filter":null},*/
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'app/courses/explore/explore.html',
                    controller: 'ExploreController',
                    controllerAs: 'vm'
                }
            }
        });
        
    }
})();
