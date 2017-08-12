'use strict';

angular.module('zonesionCloudApplicationApp')
    .directive('userAvatar', ['Principal','$templateRequest','$compile', function(Principal,$templateRequest,$compile) {
        return {
            restrict: 'AE',
            scope: {
                avatarSize: '@',
                userAccount:'='
            },
            template:'<span></span>',
            repalce:true,
            link: function (scope, element, attrs) {
              scope.$watch('userAccount', function(newVal) {
                if(newVal){
                  scope.showName = newVal.name||newVal.login||newVal.email;
                  newVal.iconWord = !newVal.avatar?_.toUpper(_.head(scope.showName)):'';
                  scope.account = newVal;
                }
              },true);
              $templateRequest('app/components/common/useravatar/template.html')
              .then(function(tmplHtml){
                element.html($compile(tmplHtml)(scope));
              });
            }
        };
    }])
