(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('CourseManagementEditController', CourseManagementEditController);

    CourseManagementEditController.$inject = ['Principal', 'Course', 'entity', 'AlertService', '$state', '$log',
     '$scope', '$rootScope', 'JhiLanguageService', 'Upload','CourseManagementService','$uibModal','EDITOROPTIONS'];

    function CourseManagementEditController(Principal, Course, entity, AlertService, $state, $log,
      $scope, $rootScope, JhiLanguageService, Upload,CourseManagementService,$uibModal,EDITOROPTIONS) {
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
        vm.stateGo = stateGo;
        vm.publishCourse = publishCourse;
        vm.getOrders();


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
              resizeToWidth:'480',
              resizeToHeight:'270'
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
        $scope.editorOptions = EDITOROPTIONS;

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

        //获得课程
        function getCourseInfo(){
          Course.get({id: $state.params.id},function(result){
              vm.course = result;
          },function(error) {

          })

        }

        //发布课程
        function  publishCourse() {
          CourseManagementService.publishCourse({id:$state.params.id},function(result){
            getCourseInfo();
          },function(error){

          })
        }

        //
        function stateGo(url){
          $state.go(url,{id:$state.params.id});
        }
    }
})();
