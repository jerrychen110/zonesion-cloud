(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('CourseLessonNoteDialogController', CourseLessonNoteDialogController);

    CourseLessonNoteDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'CourseLessonNote', 'CourseLesson', 'CourseLessonNoteLike'];

    function CourseLessonNoteDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, CourseLessonNote, CourseLesson, CourseLessonNoteLike) {
        var vm = this;

        vm.courseLessonNote = entity;
        vm.clear = clear;
        vm.save = save;
        vm.courselessons = CourseLesson.query();
        vm.courselessonnotelikes = CourseLessonNoteLike.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.courseLessonNote.id !== null) {
                CourseLessonNote.update(vm.courseLessonNote, onSaveSuccess, onSaveError);
            } else {
                CourseLessonNote.save(vm.courseLessonNote, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('zonesionCloudApplicationApp:courseLessonNoteUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
