(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('CourseFavoriteDialogController', CourseFavoriteDialogController);

    CourseFavoriteDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'CourseFavorite', 'Course'];

    function CourseFavoriteDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, CourseFavorite, Course) {
        var vm = this;

        vm.courseFavorite = entity;
        vm.clear = clear;
        vm.save = save;
        vm.courses = Course.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.courseFavorite.id !== null) {
                CourseFavorite.update(vm.courseFavorite, onSaveSuccess, onSaveError);
            } else {
                CourseFavorite.save(vm.courseFavorite, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('zonesionCloudApplicationApp:courseFavoriteUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
