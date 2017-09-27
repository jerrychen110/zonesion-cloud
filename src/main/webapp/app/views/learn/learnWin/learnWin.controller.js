(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('learnWinController', learnWinController);

    learnWinController.$inject = ['$scope', 'Principal', 'LoginService', '$state','Course','$rootScope','$stateParams','CourseService','$log'];

    function learnWinController ($scope, Principal, LoginService, $state, Course,$rootScope,$stateParams,CourseService,$log) {
        var vm = this;
        vm.checkType = false;
        vm.checkLeant = checkLeant;
        vm.checkClass = 'glyphicon-unchecked';

        function checkLeant(){
            vm.checkType = !vm.checkType;
            if(vm.checkType){
                vm.checkClass = 'glyphicon-check';
            }else{
                vm.checkClass = 'glyphicon-unchecked';
            }
            console.log(vm.checkType);
            return vm.checkClass;
        }
    }
})();
