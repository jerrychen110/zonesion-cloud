(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('MajorDetailController', MajorDetailController);

    MajorDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Major'];

    function MajorDetailController($scope, $rootScope, $stateParams, previousState, entity, Major) {
        var vm = this;

        vm.major = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('zonesionCloudApplicationApp:majorUpdate', function(event, result) {
            vm.major = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
