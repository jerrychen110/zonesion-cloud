(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('HomeHotController', HomeHotController);

    HomeHotController.$inject = ['$scope', 'Principal', 'LoginService', '$state', 'HomeHotService'];

    function HomeHotController ($scope, Principal, LoginService, $state, HomeHotService) {
        
        $(".swiper-container").luara({interval:3000,selected:"seleted",deriction:"left"});
        
        var vm = this;

        vm.account = null;
        vm.isAuthenticated = null;
        vm.login = LoginService.open;
        vm.register = register;
        $scope.$on('authenticationSuccess', function() {
            getAccount();
        });

        getAccount();
        loadAll();

        function loadAll () {
        	HomeHotService.query({
                //size: vm.itemsPerPage
                //sort: sort()
            }, onSuccess, onError);
            /*function sort() {
                var result = [vm.predicate + ',' + (vm.reverse ? 'asc' : 'desc')];
                if (vm.predicate !== 'id') {
                    result.push('id');
                }
                return result;
            }*/
            function onSuccess(data, headers) {
                //vm.links = ParseLinks.parse(headers('link'));
                vm.totalItems = headers('X-Total-Count');
                vm.queryCount = vm.totalItems;
                vm.courses = data;
            }
            function onError(error) {
                //AlertService.error(error.data.message);
            }
        }

        function getAccount() {
            Principal.identity().then(function(account) {
                vm.account = account;
                vm.isAuthenticated = Principal.isAuthenticated;
            });
        }
        function register () {
            $state.go('register');
        }

         /*$("#indicators li").click(function(){
             $(this).addClass("carouse_style");
         })*/
 
         $scope.course=[
             {url:"content/images/2.png",text:"EduSoho慕课版介绍"},
             {url:"content/images/3.jpg",text:"EduSoho慕课版介绍"},
             {url:"content/images/4.jpg",text:"EduSoho慕课版介绍"},
             {url:"content/images/5.jpg",text:"EduSoho慕课版介绍"},
             {url:"content/images/6.jpg",text:"EduSoho慕课版介绍"},
             {url:"content/images/7.jpg",text:"EduSoho慕课版介绍"}
         ]
    }
})();
