(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('CourseManagementEditController', CourseManagementEditController);

    CourseManagementEditController.$inject = ['Principal', 'Course', 'entity', 'AlertService', '$state', '$log',
     '$scope', '$rootScope', 'JhiLanguageService', 'Upload','CourseManagementService'];

    function CourseManagementEditController(Principal, Course, entity, AlertService, $state, $log,
      $scope, $rootScope, JhiLanguageService, Upload,CourseManagementService) {
        var vm = this;

        vm.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        vm.currentAccount = null;
        vm.languages = null;
        vm.save = save;
        vm.course = entity;
        vm.majors = [];
        vm.selectedMajors = [];
        vm.localLang = {
          selectAll       : '全选',
          selectNone      : '全不选',
          reset           : '重置',
          search          : '搜索',
          nothingSelected : '没有选择的专业',
          multipleLabel   : '当前选中数量'
};

        vm.getOrders = getOrders;
        vm.getOrders();

        JhiLanguageService.getAll().then(function (languages) {
            vm.languages = languages;
        });

        Principal.identity().then(function(account) {
            vm.currentAccount = account;
            vm.course.userId = account.id;
        });

        //修改封面
        $scope.IMAGE_TYPES = {
      		  mime:'image/jpeg,image/png,image/bmp,image/x-jpeg,image/x-png,image/x-ms-bmp',
      		  description:'JPG,PNG,BMP',
      		  pattern:'.jpg,.jpeg,.png,.bmp'
      		};
      $scope.coverPicturePicked = false;
      $scope.coverPictureImgContent=null;
      $scope.saveCoverPictureState=0;//0:init 1:saving 2:success -1:fail
      $scope.coverPictureSelection = [0, 0, 100, 100, 100, 100];

      $scope.cancelCoverPicture = function() {
        $scope.coverPicturePicked=false;
      };

      $scope.saveCoverPicture = function() {
          $scope.saveCoverPictureState = 1;
          $log.debug($scope.coverPictureSelection);
          Upload.upload({
            url:'api/courses/'+vm.course.id+'/cover-picture',
            data:{
              file:$scope.coverPicturePicked,
              cropSelection:$scope.coverPictureSelection.join(','),
              resizeTo:'120'
            }
          }).then(function success(res) {
            $log.debug('success',res);
            $scope.saveCoverPictureState = 0;
            $scope.coverPicturePicked=false;
            $scope.coverPictureSelection = [0, 0, 100, 100, 100, 100];
            Principal.identity(true).then(function(account) {
                $scope.settingsAccount = angular.copy(account);
                $rootScope.account = angular.copy(account);
            });
          },function error(res){
            $log.debug('error',res);
            $scope.saveCoverPictureState = -1;
          },function progress(evt){
            var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
            $log.debug('progress:'+progressPercentage+'%',evt);
          });
        };

        $scope.clearCoverPicture = function() {
          $scope.coverPicturePicked=false;
          $scope.saveCoverPicture();
        };

        var coverPictureFileReader = new FileReader();
        coverPictureFileReader.onload = function(){
          $scope.$apply(function(){
            $scope.coverPictureImgContent=coverPictureFileReader.result;
          });
        };

        $scope.onCoverPicturePicked = function(file) {
          if (file) {
            $scope.coverPictureSelection = [0, 0, 100, 100, 100, 100];
            coverPictureFileReader.readAsDataURL(file);
            $scope.coverPicturePicked=file;
          }
        };

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
            Course.update(vm.course, onSaveSuccess, onSaveError);
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        function onSaveSuccess (result) {
            vm.isSaving = false;
//            $state.go('course-management-list', {courseType:result.courseType, courseSource: result.courseSource}, { reload: true });
        }

        //获取分类专业
        function getOrders(){
          CourseManagementService.getMajors(function(data){
            vm.majors = data;
            angular.forEach(vm.majors,function(major){
              major.selected = false;
            })
          },function(error){

          })
        }
    }
})();
