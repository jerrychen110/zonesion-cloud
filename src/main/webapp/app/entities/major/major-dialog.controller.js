(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('MajorDialogController', MajorDialogController);

    MajorDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Major'];

    function MajorDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Major) {
        var vm = this;

        vm.major = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.major.id !== null) {
                Major.update(vm.major, onSaveSuccess, onSaveError);
            } else {
                Major.save(vm.major, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('zonesionCloudApplicationApp:majorUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
