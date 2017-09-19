(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('EditLessonController', EditLessonController);

    EditLessonController.$inject = ['Principal', 'Course', 'entity', 'AlertService', '$state', '$log',
     '$scope', '$rootScope', 'JhiLanguageService', 'Upload','CourseManagementService','$uibModal'];

    function EditLessonController(Principal, Course, entity, AlertService, $state, $log,
      $scope, $rootScope, JhiLanguageService, Upload,CourseManagementService,$uibModal) {
        var vm = this;
        vm.currentAccount = null;
        vm.languages = null;
        vm.save = save;
        vm.course = entity;
        vm.majors = [];
        vm.selectedMajors = [];
        vm.testData=[{name:'第一章',unit:[{name:'第一节',lesson:[{name:'第一课时'}]}]},{name:'第2章',unit:[{name:'第2节',lesson:[{name:'第2课时'}]}]}]

        vm.stateGo = stateGo;
        vm.openModal = openModal;

        JhiLanguageService.getAll().then(function (languages) {
            vm.languages = languages;
        });

        Principal.identity().then(function(account) {
            vm.currentAccount = account;
            vm.course.userId = account.id;
        });



        function save () {
            vm.isSaving = true;
            Course.update(vm.course, onSaveSuccess, onSaveError);
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        function onSaveSuccess (result) {
            vm.isSaving = false;
//            $state.go('course-management-list', {courseType:result.courseType, courseSource: result.courseSource}, { reload: true });
        }

        //
        function openModal(type){
          $uibModal.open({
              templateUrl: 'app/admin/course-management/edit/lesson/modal/modal.html',
              controller: 'EditLessonModalController',
              controllerAs: 'vm',
              backdrop: 'static',
              resolve: {
                  lessonType:  function(){
                    return type;
                  },
                  courseId:  function(){
                    return $state.params.id;
                  }
              }
          }).result.then(function(result) {
              // $state.go('^', {}, { reload: false });
          }, function() {
              // $state.go('^');
          });
        }

        //
        function stateGo(url){
          $state.go(url,{id:$state.params.id});
        }
    }
})();
