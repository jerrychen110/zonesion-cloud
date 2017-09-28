(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('SettingsController', SettingsController);

    SettingsController.$inject = ['Principal', 'Auth', 'JhiLanguageService', '$translate', '$log', '$rootScope', '$scope', 'Upload','CourseManagementService'];

    function SettingsController (Principal, Auth, JhiLanguageService, $translate, $log, $rootScope, $scope, Upload,CourseManagementService) {
        var vm = this;

        vm.error = null;
        vm.save = save;
        vm.settingsAccount = null;
        vm.success = null;


        vm.settingsAccount = $rootScope.accountInfo;

        vm.getOrders = getOrders;
        vm.getOrders();
        /**
         * Store the "settings account" in a separate variable, and not in the shared "account" variable.
         */
        var copyAccount = function (account) {
            return {
                activated: account.activated,
                email: account.email,
                name: account.name,
                login: account.login,
                avatar: account.avatar,
                mobile: account.mobile,
                sex: account.sex,
                staffNo: account.staffNo,
                major: account.major
            };
        };

        //  刷新信息
        $scope.$on('authenticationSuccess', function() {
          vm.settingsAccount =  $rootScope.accountInfo;
        });
        function save () {
            Auth.updateAccount(vm.settingsAccount).then(function() {
                vm.error = null;
                vm.success = 'OK';
                Principal.identity(true).then(function(account) {
                    vm.settingsAccount = copyAccount(account);
                });
            }).catch(function() {
                vm.success = null;
                vm.error = 'ERROR';
            });
        }

        //获取分类专业
        function getOrders(){
          CourseManagementService.getMajors(function(data){
            vm.majors = data;
          },function(error){

          })
        }

        $scope.IMAGE_TYPES = {
        		  mime:'image/jpeg,image/png,image/bmp,image/x-jpeg,image/x-png,image/x-ms-bmp',
        		  description:'JPG,PNG,BMP',
        		  pattern:'.jpg,.jpeg,.png,.bmp'
        		};
        $scope.avatarPicked = false;
        $scope.avatarImgContent=null;
        $scope.saveAvatarState=0;//0:init 1:saving 2:success -1:fail
        $scope.avatarSelection = [0, 0, 100, 100, 100, 100];

        $scope.cancelAvatar = function() {
          $scope.avatarPicked=false;
        };

        $scope.saveAvatar = function() {
            $scope.saveAvatarState = 1;
            $log.debug($scope.avatarSelection);
            Upload.upload({
              url:'api/users/user-avatar',
              data:{
                file:$scope.avatarPicked,
                cropSelection:$scope.avatarSelection.join(','),
                resizeToWidth:'120',
                resizeToHeight:'120'
              }
            }).then(function success(res) {
              $log.debug('success',res);
              $scope.saveAvatarState = 0;
              $scope.avatarPicked=false;
              $scope.avatarSelection = [0, 0, 100, 100, 100, 100];
              Principal.identity(true).then(function(account) {
                  vm.settingsAccount = angular.copy(account);
                  $rootScope.account = angular.copy(account);
              });
            },function error(res){
              $log.debug('error',res);
              $scope.saveAvatarState = -1;
            },function progress(evt){
              var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
              $log.debug('progress:'+progressPercentage+'%',evt);
            });
          };

          $scope.clearAvatar = function() {
            $scope.avatarPicked=false;
            $scope.saveAvatar();
          };

          var avatarFileReader = new FileReader();
          avatarFileReader.onload = function(){
            $scope.$apply(function(){
              $scope.avatarImgContent=avatarFileReader.result;
            });
          };

          $scope.onAvatarPicked = function(file) {
            if (file) {
              $scope.avatarSelection = [0, 0, 100, 100, 100, 100];
              avatarFileReader.readAsDataURL(file);
              $scope.avatarPicked=file;
            }
          };


    }
})();
