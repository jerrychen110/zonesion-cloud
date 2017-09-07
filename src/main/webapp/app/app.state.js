(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider', 'ngJcropConfigProvider'];

    function stateConfig($stateProvider, ngJcropConfigProvider) {
        $stateProvider.state('app', {
            abstract: true,
            views: {
                'navbar@': {
                    templateUrl: 'app/layouts/navbar/navbar.html',
                    controller: 'NavbarController',
                    controllerAs: 'vm'
                },
                'sidenav@': {
                    templateUrl: 'app/layouts/sidenav/sidenav.html',
                    controller: 'SidenavController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                authorize: ['Auth',
                    function (Auth) {
                        return Auth.authorize();
                    }
                ],
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('global');
                }]
            }
        });
        
        // [optional] To change the jcrop configuration,All jcrop settings are in: http://deepliquid.com/content/Jcrop_Manual.html#Setting_Options
        ngJcropConfigProvider.setJcropConfig({
            bgColor: 'transparent',
            bgOpacity: 0.4,
            aspectRatio: 1,
            maxWidth:200,
            maxHeight:200
        });

        // [optional] To change the css style in the preview image
        ngJcropConfigProvider.setPreviewStyle({
            'width': '100px',
            'height': '100px',
            'overflow': 'hidden',
            'margin-left': '5px'
        });
    }
})();
