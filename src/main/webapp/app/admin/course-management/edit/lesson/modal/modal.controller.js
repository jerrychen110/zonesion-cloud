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
      vm.options =angular.copy(options);
      vm.title = vm.options.selectedInfo?vm.options.selectedInfo.title:'';
      vm.optionName=vm.options.operation==0?'添加':vm.options.operation==1?'修改':'删除';
      vm.optionType=vm.options.type==0?'章':vm.options.type==1?'节':'课时';
      vm.editorOptions = EDITOROPTIONS;
      /*
      **文件上传
      */

      vm.lessonInfo = {
        id:vm.options.selectedInfo?vm.options.selectedInfo.id:null,
        title: vm.options.selectedInfo?vm.options.selectedInfo.title:'',
        summary: vm.options.selectedInfo?vm.options.selectedInfo.summary:'',
        content: vm.options.selectedInfo?vm.options.selectedInfo.content:'',
        mediaUri: vm.options.selectedInfo?vm.options.selectedInfo.mediaUri:'',
        mediaSize: vm.options.selectedInfo?vm.options.selectedInfo.mediaSize:'',
        mediaName: vm.options.selectedInfo?vm.options.selectedInfo.mediaName:'',
        min:vm.options.selectedInfo?parseInt(vm.options.selectedInfo.mediaLength/60):0,
        sec:vm.options.selectedInfo?parseInt(vm.options.selectedInfo.mediaLength%60):0,
        number: vm.options.selectedInfo?vm.options.selectedInfo.number:null,
        seq:vm.options.selectedInfo?vm.options.selectedInfo.seq:null
      }
      var acceptExtensions;
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


      vm.clear = clear;
      vm.save = save;
      vm.changeLessonType = changeLessonType;
      vm.fileAdded = fileAdded;
      vm.flowError = flowError;
      vm.flowFileSuccess = flowFileSuccess;
      vm.showUpload = showUpload;
      vm.showUpload();
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
            if(vm.options.type==2){
              CourseManagementService.updateLesson(params,onSaveSuccess,onSaveError);
            }else{
              CourseManagementService.updateChapter(params,onSaveSuccess,onSaveError);
            }

              break;
            case 2:

              break;
            default:

          }
      }

      //get params function
      function getParams(){
        var params;
        var id = vm.options.operation==0?null:vm.options.currentId;
        if(vm.options.type!=2){
           params = {
            id:id,
            chapterType:vm.options.type,
            title:vm.title,
            courseId:vm.options.courseId,
            parentId:vm.options.parentId?vm.options.parentId:'0',
            userId:vm.options.userId  ,
            number: vm.options.selectedInfo?vm.options.selectedInfo.number:null,
            seq: vm.options.selectedInfo?vm.options.selectedInfo.seq:null
          }
        }else{
          var chapterId = vm.options.operation==0?vm.options.currentId:vm.options.parentId;
          var mediaLength= vm.lessonInfo.min*60+vm.lessonInfo.sec;
          params = {
            chapterId: chapterId,
            content: vm.lessonInfo.content,
            courseId: vm.options.courseId,
            courseLessonType: vm.selectTypeInfo.lessonType,//
            credit: 0,
            mediaLength: mediaLength,
            mediaName: vm.lessonInfo.mediaName,
            mediaSize: vm.lessonInfo.mediaSize,
            mediaSource: 0,
            mediaUri: vm.lessonInfo.mediaUri,
            summary: vm.lessonInfo.summary,
            title: vm.lessonInfo.title,
            userId: vm.options.userId,
            id:vm.lessonInfo.id,
            number: vm.lessonInfo.number,
            seq: vm.lessonInfo.seq
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

      function showUpload(){
        vm.lessonTypes = angular.copy(LESSONTYPES);
        if(vm.lessonInfo.title){
          vm.showUploadFile = false;
          vm.selectTypeInfo =_.find(vm.lessonTypes,{lessonType:vm.options.selectedInfo.courseLessonType});
          _.forEach(vm.lessonTypes,function(lessonType){
            if(vm.selectTypeInfo.lessonType==lessonType.lessonType){
                lessonType.checked = true;
            }else{
              lessonType.checked = false;
            }
          })
        }else{
          vm.showUploadFile = true;
          vm.selectTypeInfo = _.find(vm.lessonTypes,{checked:true});
        }
        if(vm.selectTypeInfo.type!='text'){
          acceptExtensions = _.find(LOAD_TYPES,{type:vm.selectTypeInfo.type}).extensions;
          vm.acceptExtensions = acceptExtensions.join(', ');
        }

      }

    }
})();
