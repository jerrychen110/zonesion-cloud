(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('CourseLessonNoteLikeDialogController', CourseLessonNoteLikeDialogController);

    CourseLessonNoteLikeDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'CourseLessonNoteLike', 'CourseLessonNote'];

    function CourseLessonNoteLikeDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, CourseLessonNoteLike, CourseLessonNote) {
        var vm = this;

        vm.courseLessonNoteLike = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.courselessonnotes = CourseLessonNote.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.courseLessonNoteLike.id !== null) {
                CourseLessonNoteLike.update(vm.courseLessonNoteLike, onSaveSuccess, onSaveError);
            } else {
                CourseLessonNoteLike.save(vm.courseLessonNoteLike, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('zonesionCloudApplicationApp:courseLessonNoteLikeUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.createdTime = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
