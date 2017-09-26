(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('CourseManagementBaseController', CourseManagementBaseController);

    CourseManagementBaseController.$inject = ['Principal', 'Course', 'entity', 'AlertService', '$state', '$log',
     '$scope', '$rootScope', 'JhiLanguageService', 'Upload','CourseManagementService','$uibModal','EDITOROPTIONS'];

    function CourseManagementBaseController(Principal, Course, entity, AlertService, $state, $log,
      $scope, $rootScope, JhiLanguageService, Upload,CourseManagementService,$uibModal,EDITOROPTIONS) {
        var vm = this;

        vm.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        vm.currentAccount = null;
        vm.languages = null;
        vm.save = save;
        vm.course = {};
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
        vm.getCourseInfo = getCourseInfo;
        vm.getCourseInfo();


        Principal.identity().then(function(account) {
            vm.currentAccount = account;
            vm.course.userId = account.id;
        });

        //富文本插件ckeditor相关设置
        $scope.editorOptions = EDITOROPTIONS;

        function save () {
            vm.isSaving = true;
            vm.course.tags=''
            // vm.course.tags = vm.selectedMajors.join(', ');
            _.forEach(vm.selectedMajors,function(selectedMajor,index){
              vm.course.tags += (index==0?'':',')+ selectedMajor.major;
            })
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
              var findFlag=_.findIndex(vm.selectedMajors, function(selectedMajor) {
                  return selectedMajor.major == major.major;
                });
                if(findFlag>=0){
                  major.selected=true;
                }else{
                  major.selected = false;
                }
            })
          },function(error){

          })
        }

        //获得课程
        function getCourseInfo(){
          Course.get({id: $state.params.id},function(result){
              vm.course = result;
              if(vm.course.tags!=null){
            	  var selectedMajors = vm.course.tags.split(",");
            	  _.forEach(selectedMajors,function(major){
            		  vm.selectedMajors.push({major:major,selected:true});
            	  })
              }
              vm.getOrders();
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
