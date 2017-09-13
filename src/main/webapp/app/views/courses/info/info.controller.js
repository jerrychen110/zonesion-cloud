(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('InfoController', InfoController);

    InfoController.$inject = ['$scope', '$rootScope', '$stateParams', 'InfoService'];

    function InfoController($scope, $rootScope, $stateParams, InfoService) {
        var vm = this;
        loadInfos();
        
        function loadInfos () {
        	InfoService.query({
        		id: $stateParams.id
            }, onSuccess, onError);

            function onSuccess(data, headers) {

                vm.courses = data[0];
                vm.allinfos = data;               
                console.log(data);
 
            }
            function onError(error) {
                //AlertService.error(error.data.message);
            }
        }
        
    }
})();
