(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('CourseLessonLearnDialogController', CourseLessonLearnDialogController);

    CourseLessonLearnDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'CourseLessonLearn', 'CourseLesson'];

    function CourseLessonLearnDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, CourseLessonLearn, CourseLesson) {
        var vm = this;

        vm.courseLessonLearn = entity;
        vm.clear = clear;
        vm.save = save;
        vm.courselessons = CourseLesson.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.courseLessonLearn.id !== null) {
                CourseLessonLearn.update(vm.courseLessonLearn, onSaveSuccess, onSaveError);
            } else {
                CourseLessonLearn.save(vm.courseLessonLearn, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('zonesionCloudApplicationApp:courseLessonLearnUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
