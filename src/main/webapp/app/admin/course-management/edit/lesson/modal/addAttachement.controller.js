(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('AddAttachementController', AddAttachementController);

    AddAttachementController.$inject = ['$uibModalInstance','CourseManagementService',
    '$log','$localStorage','options'];

    function AddAttachementController($uibModalInstance,CourseManagementService,
      $log,$localStorage,options) {
        var vm = this;

        vm.clear = clear;
        vm.save = save;
        vm.options = angular.copy(options);
        vm.getAttachementList = getAttachementList;



        function getAttachementList(){
          CourseManagementService.getAttachementList({id:vm.options.lessonId},onSuccess,onError);
        }

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            $uibModalInstance.dismiss('cancel');
        }
    }
})();
