(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('FileManagementController', FileManagementController);

    FileManagementController.$inject = ['$scope', '$rootScope', '$stateParams', 'CourseService','Principal','CommonFactory','AttachementService','$uibModal','CourseManagementService'];

    function FileManagementController($scope, $rootScope, $stateParams, CourseService,Principal,CommonFactory,AttachementService,$uibModal,CourseManagementService) {
        var vm = this;
        vm.isAuthenticated = Principal.isAuthenticated;
        vm.currentAccount = $rootScope.accountInfo;
        vm.courseId = $stateParams.id;
        // 初始化分页数据
        vm.currentPage = 1;
        vm.pageSize = 10;
        vm.attachements = [];
        vm.fileInfo = {};
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
        vm.showLoading = false;
        vm.getAttachements=getAttachements;
        vm.fileAdded = fileAdded;
        vm.flowError = flowError;
        vm.flowFileSuccess = flowFileSuccess;
        vm.deleteAttachement = deleteAttachement;
        vm.getAttachements();

        $scope.$on('authenticationSuccess', function() {
          vm.currentAccount =  $rootScope.accountInfo;
        });

        //获取资料库列表数据
        function getAttachements() {
        	CourseService.getCourseAttachements({
        		id: $stateParams.id,
            page:vm.currentPage-1,
            size:vm.pageSize
            }, onSuccess, onError);

            function onSuccess(data, headers) {
                vm.attachements = data;
                vm.totalCount = parseInt(headers('X-Total-Count'));
                //数据处理（size和时间）
                angular.forEach(vm.attachements,function(att){
                  att.fileSize=CommonFactory.bytesToSize(att.fileSize);
                  att.createdDate=moment(att.createdDate).format('YYYY-MM-DD HH:mm:ss');
                })
                console.log(data);

            }
            function onError(error) {
                //AlertService.error(error.data.message);
            }
        }

        //上传
        function fileAdded($file) {
          vm.uploadFileError = false;
          vm.uploadFileCodeError = false;
          vm.showChangeAlert = false;
          vm.extenstionError = false;
          vm.previewData = null;
          vm.showLoading = true;
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
          save();
        };

        //保存上传文件信息
        function save () {
          var params={
            description: "",
            fileLength: 0,
            fileMime: "",
            fileSize: vm.fileInfo.mediaSize,
            fileType: 0,
            fileUri: vm.fileInfo.mediaUri,
            link: "",
            targetId:vm.courseId,
            targetType: "course",
            title: vm.fileInfo.mediaName,
            userId:vm.currentAccount.id
          }
          CourseManagementService.addAttachement(params,onSuccess,onError);
          function onSuccess(result){
            getAttachements();
            vm.showLoading = false;

          }
          function onError(error){

          }
            // $uibModalInstance.dismiss('cancel');
        }

        function deleteAttachement(fileId){
          CourseManagementService.deleteAttachement({id:fileId},function(result){
            vm.getAttachements();
          },function(error){

          })
        }
    }
})();
