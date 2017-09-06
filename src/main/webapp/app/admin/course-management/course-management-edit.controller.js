(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('CourseManagementEditController', CourseManagementEditController);

    CourseManagementEditController.$inject = ['Principal', 'Course', 'entity', 'AlertService', '$state', '$scope', 'JhiLanguageService'];

    function CourseManagementEditController(Principal, Course, entity, AlertService, $state, $scope, JhiLanguageService) {
        var vm = this;

        vm.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        vm.currentAccount = null;
        vm.languages = null;
        vm.save = save;
        vm.course = entity;
        
        JhiLanguageService.getAll().then(function (languages) {
            vm.languages = languages;
        });
        
        Principal.identity().then(function(account) {
            vm.currentAccount = account;
            vm.course.userId = account.id;
        });
        
        //富文本插件ckeditor相关设置
        $scope.editorOptions = {
	      language: 'zh-cn',
	      // allowedContent:{
	      //     $1: {
	      //         elements: CKEDITOR.dtd,
	      //         attributes: true,
	      //         styles: true,
	      //         classes: true
	      //     }
	      // },
	      image_previewText:' ',
	      allowedContent: true,
	      extraPlugins : 'widget,filetools,notification,lineutils,notificationaggregator,uploadwidget,uploadimage',
	      filebrowserImageUploadUrl: '/api/file/image-upload?type=Images',
	      filebrowserWindowHeight: '240',
	      disallowedContent:'script; *[on*];*{*javascript*}',
	      toolbar :[
	        { name: 'tools', items: [ 'Maximize'] },
    		{ name: 'clipboard', items: [ 'Cut', 'Copy', 'Paste', 'PasteText', 'PasteFromWord', '-', 'Undo', 'Redo' ] },
    		{ name: 'basicstyles', items: [ 'Bold', 'Italic', 'Underline', 'Strike', 'Subscript', 'Superscript', '-', 'RemoveFormat' ] },
    		{ name: 'paragraph', items: [ 'NumberedList', 'BulletedList', '-', 'Outdent', 'Indent',  '-', 'JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock'] },
    		{ name: 'links', items: [ 'Link', 'Unlink' ] },
    		{ name: 'insert', items: [ 'Image', 'Table', 'HorizontalRule', 'SpecialChar', 'PageBreak'] },
    		{ name: 'colors', items: [  'TextColor', 'BGColor', 'Format'] },
    		{ name: 'styles', items: [ 'Styles', 'Font', 'FontSize' ] },
	        { name: 'document', items: [ 'Source'] }
	      ],
	      on: {
	        instanceReady: function () {
	          setTimeout(function () {
	            $('.cke_reset iframe').contents().find('body').css('background-color','##fff');
	            $('.cke_reset iframe').contents().find('body').css('margin','0px');
	          }, 100);
	        }
	      }
	    };
        
        function save () {
            vm.isSaving = true;
            Course.save(vm.course, onSaveSuccess, onSaveError);
        }
        
        function onSaveError () {
            vm.isSaving = false;
        }
        
        function onSaveSuccess (result) {
            vm.isSaving = false;
            $state.go('course-management-list', {courseType:result.courseType, courseSource: result.courseSource}, { reload: true });
        }
    }
})();