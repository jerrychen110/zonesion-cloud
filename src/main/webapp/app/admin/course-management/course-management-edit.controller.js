(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('CourseManagementEditController', CourseManagementEditController);

    CourseManagementEditController.$inject = ['Principal', 'Course', 'entity', 'AlertService', '$state', 'JhiLanguageService'];

    function CourseManagementEditController(Principal, Course, entity, AlertService, $state, JhiLanguageService) {
        var vm = this;

        vm.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        vm.currentAccount = null;
        vm.languages = null;
        vm.clear = clear;
        vm.save = save;
        vm.course = entity;
        
        JhiLanguageService.getAll().then(function (languages) {
            vm.languages = languages;
        });
        
        Principal.identity().then(function(account) {
            vm.currentAccount = account;
            vm.course.userId = account.id;
        });
        
        function clear() {
        	vm.course = {
                    id: null, userId: null, title: null, subTitle: null,
                    status: "0", courseType: null, courseSource: null, lessonNum: 0,
                    credit: "0", coverPicture: "", introduction: null, goals: null,
                    recommended: "0", recommendedSort: "0"
                };
        }
        
        function save () {
            vm.isSaving = true;
            console.log(vm.course);
            Course.save(vm.course, onSaveSuccess, onSaveError);
        }
        
        function onSaveError () {
            vm.isSaving = false;
        }
        
        function onSaveSuccess (result) {
            vm.isSaving = false;
            $state.go('course-management-self-list', null, { reload: true });
        }
    }
})();