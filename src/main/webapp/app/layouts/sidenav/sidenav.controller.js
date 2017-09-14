(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('SidenavController', SidenavController);

    SidenavController.$inject = ['$state', '$scope', 'Auth', 'Principal', 'ProfileService', 'LoginService','$sce','SERVICEHTML'];

    function SidenavController ($state, $scope, Auth, Principal, ProfileService, LoginService,$sce,SERVICEHTML) {
        var vm = this;
        vm.stateName = $state.$current.name;
        vm.htmlTooltip = $sce.trustAsHtml(SERVICEHTML);
        vm.login = LoginService.open;
        
        function isLogin(){
        	Principal.identity().then(function(account) {
                vm.account = account;
                vm.isAuthenticated = Principal.isAuthenticated;
                if(vm.account == null || vm.isAuthenticated == null){
                	vm.login();
                	$scope.mystyle={right: '-230px'};
                }               
            });
        }
        $scope.isActiveUC = false;
        $scope.isActiveEH = false;
        $scope.isActiveCM = false;
        $scope.userCenter = function(){
        	isLogin();
        	$scope.isActiveUC = true;
        	if($scope.isActiveUC == true){
        		$scope.isActiveEH = false;
        		$scope.isActiveCM = false;
        	}
        	
        	$scope.mystyle={right: '0px'};
        	
        }
        
        $scope.examHomework = function(){
        	isLogin();
        	$scope.isActiveEH = true;
        	if($scope.isActiveEH == true){
        		$scope.isActiveUC = false;
                $scope.isActiveCM = false;
        	}
        	$scope.mystyle={right: '0px'};
        }
        
        $scope.courseMajor = function(){
        	isLogin();
        	$scope.isActiveCM = true;
        	if($scope.isActiveCM == true){
        		$scope.isActiveUC = false;
                $scope.isActiveEH = false;
        	}
        	$scope.mystyle={right: '0px'};
        }

        $scope.navhide = function(){
        	$scope.mystyle={right: '-230px'};
        }
        

    }
})();
