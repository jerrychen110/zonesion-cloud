(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('EditLessonModalController', EditLessonModalController);

    EditLessonModalController.$inject = ['$uibModalInstance','options','CourseManagementService','LOAD_TYPES',
    '$log','$localStorage','LESSONTYPES','EDITOROPTIONS'];

    function EditLessonModalController($uibModalInstance,options,CourseManagementService,LOAD_TYPES,
      $log,$localStorage,LESSONTYPES,EDITOROPTIONS) {
      var vm = this;
      vm.clear = clear;
      vm.save = save;
      vm.options =angular.copy(options);
      vm.title = vm.options.title
      vm.optionName=vm.options.operation==0?'添加':vm.options.operation==1?'修改':'删除';
      vm.optionType=vm.options.type==0?'章':vm.options.type==1?'节':'课时';
      vm.changeLessonType = changeLessonType;
      vm.editorOptions = EDITOROPTIONS;
      /*
      **文件上传
      */

      vm.lessonTypes = angular.copy(LESSONTYPES);

      vm.selectTypeInfo = _.find(vm.lessonTypes,{checked:true});
      var acceptExtensions = _.find(LOAD_TYPES,{type:vm.selectTypeInfo.type}).extensions;
      vm.acceptExtensions = acceptExtensions.join(', ');
      vm.flowInitOptions = {
        target:'api/file-management/file-upload',
        forceChunkSize:true,
        singleFile:true,
        testChunks:false,
        uploadMethod:'POST',
        headers:getHeaders(),
        generateUniqueIdentifier:UUID.generate,
        permanentErrors:[404, 415, 500, 501,401]
      };
      vm.fileAdded = fileAdded;
      vm.flowError = flowError;
      vm.flowFileSuccess = flowFileSuccess;

      //上传
      function fileAdded($file) {
        vm.uploadFileError = false;
        vm.uploadFileCodeError = false;
        vm.showChangeAlert = false;
        vm.extenstionError = acceptExtensions.indexOf($file.getExtension())===-1;
        vm.previewData = null;
        return !vm.extenstionError;
      };
      //上传失败
      function flowError($file, $message) {
        try {
          vm.uploadFileError = true;
        } catch (e) {
          $log.error(e,$message);
        }
      };

      function flowFileSuccess($file, $message) {
        if($message=='failure'){
          vm.uploadFileCodeError = true;
          return;
        }
        vm.lessonInfo.mediaUri = $message;
        vm.lessonInfo.mediaName = $file.file.name;
        vm.lessonInfo.mediaSize = $file.file.size;
        vm.uploadFileError = false;
      };


      function getHeaders(){
        var header = {};
        var token = $localStorage.authenticationToken;
        if (token) {
          header.Authorization = 'Bearer ' + token.access_token;
        }
        return header;
      }

      function clear () {
          $uibModalInstance.dismiss('cancel');
      }

      function save () {
          vm.isSaving = true;
          var params = getParams();
          switch (vm.options.operation) {
            case 0:
              if(vm.options.type==2){
                CourseManagementService.addLesson(params,onSaveSuccess,onSaveError);
              }else{
                CourseManagementService.addChapter(params,onSaveSuccess,onSaveError);
              }
              break;
            case 1:

              break;
            case 2:

              break;
            default:

          }
      }

      //get params function
      function getParams(){
        var params;
        if(vm.options.type!=2){
           params = {
            chapterType:vm.options.type,
            title:vm.title,
            courseId:vm.options.courseId,
            parentId:vm.options.parentId?vm.options.parentId:'0',
            userId:vm.options.userId
          }
        }else{
          var chapterId = vm.options.type==0?vm.options.parentId:vm.options.currentId;
          var mediaLength= vm.lessonInfo.min*60+vm.lessonInfo.sec;
          params = {
            chapterId: chapterId,
            content: vm.lessonInfo.content,
            courseId: vm.options.courseId,
            courseLessonType: vm.selectTypeInfo.LessonType,//
            credit: 0,
            mediaLength: mediaLength,
            mediaName: vm.lessonInfo.mediaName,
            mediaSize: vm.lessonInfo.mediaSize,
            mediaSource: 0,
            mediaUri: vm.lessonInfo.mediaUri,
            summary: vm.lessonInfo.summary,
            title: vm.lessonInfo.title,
            userId: vm.options.userId
          }
        }
        return params;
      }

      function onSaveSuccess (result) {
          // $scope.$emit('zonesionCloudApplicationApp:courseUpdate', result);
          $uibModalInstance.close(result);
          // vm.isSaving = false;
      }

      function onSaveError () {
          // vm.isSaving = false;
      }

      //切换不同的lesson 类型
      function  changeLessonType(selectedIndex) {

        _.forEach(vm.lessonTypes,function(typeInfo,index){
          if(index==selectedIndex){
            typeInfo.checked = true;
            vm.selectTypeInfo = typeInfo;
            acceptExtensions = _.find(LOAD_TYPES,{type:vm.selectTypeInfo.type}).extensions;
            vm.acceptExtensions = acceptExtensions.join(', ');
          }else{
            typeInfo.checked = false;
          }
        })
      }

    }
})();
