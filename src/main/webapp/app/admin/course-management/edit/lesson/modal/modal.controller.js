(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('EditLessonModalController', EditLessonModalController);

    EditLessonModalController.$inject = ['$uibModalInstance','options','CourseManagementService'];

    function EditLessonModalController($uibModalInstance,options,CourseManagementService) {
      var vm = this;
      vm.clear = clear;
      vm.save = save;
      vm.options =angular.copy(options);
      vm.title = vm.options.title
      vm.optionName=vm.options.operation==0?'添加':vm.options.operation==1?'修改':'删除';
      vm.optionType=vm.options.type==0?'章':'节';
      function clear () {
          $uibModalInstance.dismiss('cancel');
      }

      function save () {
          vm.isSaving = true;
          var params = {
            chapterType:vm.options.type,
            title:vm.title,
            courseId:vm.options.courseId,
            parentId:vm.options.chapterId?vm.options.chapterId:0,
            userId:vm.options.userId
          }
          switch (vm.options.operation) {
            case 0:
              CourseManagementService.addChapter(params,onSaveSuccess,onSaveError)
              break;
            case 1:

              break;
            case 2:

              break;
            default:

          }
      }

      function onSaveSuccess (result) {
          // $scope.$emit('zonesionCloudApplicationApp:courseUpdate', result);
          $uibModalInstance.close(result);
          // vm.isSaving = false;
      }

      function onSaveError () {
          // vm.isSaving = false;
      }

    }
})();
