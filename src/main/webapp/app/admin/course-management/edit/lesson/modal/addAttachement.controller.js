(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('AddAttachementController', AddAttachementController);

    AddAttachementController.$inject = ['$uibModalInstance','CourseManagementService',
    '$log','$localStorage','options','CommonFactory'];

    function AddAttachementController($uibModalInstance,CourseManagementService,
      $log,$localStorage,options,CommonFactory) {
        var vm = this;

        vm.clear = clear;
        vm.save = save;
        vm.deleteAttachement = deleteAttachement;
        vm.options = angular.copy(options);
        vm.fileInfo={};
        vm.flowInitOptions = {
          target:'api/file-management/file-upload',
          forceChunkSize:true,
          singleFile:true,
          testChunks:false,
          uploadMethod:'POST',
          headers:CommonFactory.getHeaders(),
          generateUniqueIdentifier:UUID.generate,
          permanentErrors:[404, 415, 500, 501,401]
        };
        vm.attachements = [];
        vm.getAttachementList = getAttachementList;
        vm.fileAdded = fileAdded;
        vm.flowError = flowError;
        vm.flowFileSuccess = flowFileSuccess;
        vm.getAttachementList();

        //上传
        function fileAdded($file) {
          vm.uploadFileError = false;
          vm.uploadFileCodeError = false;
          vm.showChangeAlert = false;
          vm.extenstionError = false;
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
          vm.fileInfo.mediaUri = $message;
          vm.fileInfo.mediaName = $file.file.name;
          vm.fileInfo.mediaSize = $file.file.size;
          vm.uploadFileError = false;
          vm.save();
        };

        function getAttachementList(){
          CourseManagementService.getAttachementList({id:vm.options.lessonId},onSuccess,onError);
          function onSuccess(result){
            vm.attachements=result;
            _.forEach(vm.attachements,function(atta){
              atta.fileSize = CommonFactory.bytesToSize(atta.fileSize);
            })

          }
          function onError(error){
          }
        }


        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
          var params={
            description: "",
            fileLength: 0,
            fileMime: "",
            fileSize: vm.fileInfo.mediaSize,
            fileType: 0,
            fileUri: vm.fileInfo.mediaUri,
            link: "",
            targetId:vm.options.lessonId ,
            targetType: "lesson",
            title: vm.fileInfo.mediaName,
            userId: vm.options.userId
          }
          CourseManagementService.addAttachement(params,onSuccess,onError);
          function onSuccess(result){
            getAttachementList()

          }
          function onError(error){

          }
            // $uibModalInstance.dismiss('cancel');
        }

        function deleteAttachement(attachementId){
          CourseManagementService.deleteAttachement({id:attachementId},function(result){
            vm.getAttachementList()
          },function(error) {

          })
        }
    }
})();
