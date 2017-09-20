(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('EditLessonModalController', EditLessonModalController);

    EditLessonModalController.$inject = ['$uibModalInstance','lessonType','courseId'];

    function EditLessonModalController($uibModalInstance,lessonType,courseId) {
      var vm = this;
      vm.clear = clear;
      vm.lessonType =angular.copy(lessonType);
      vm.courseId = angular.copy(courseId);
      function clear () {
          $uibModalInstance.dismiss('cancel');
      }

      function save () {
          vm.isSaving = true;
          if (vm.courseId!== null) {
              // Course.update(vm.course, onSaveSuccess, onSaveError);
          } else {
              // Course.save(vm.course, onSaveSuccess, onSaveError);
          }
      }

      function onSaveSuccess (result) {
          // $scope.$emit('zonesionCloudApplicationApp:courseUpdate', result);
          // $uibModalInstance.close(result);
          // vm.isSaving = false;
      }

      function onSaveError () {
          // vm.isSaving = false;
      }

    }
})();
