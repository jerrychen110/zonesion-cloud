(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .directive('pptContainer', pptContainer);
        pptContainer.$inject = ['$templateRequest','$compile'];
        function pptContainer ($templateRequest,$compile) {
            var directive = {
                restrict: 'AE',
                scope: {
                  content: '=?',
                },
                link: linkFunc
            };

            return directive;

            function linkFunc (scope, element, attrs, formCtrl) {
              // 重新compile 刷新数据显示
              function initTemp() {
                $templateRequest('app/components/learn-display/ppt/ppt.html')
                .then(function(tmplHtml){
                  element.html($compile(tmplHtml)(scope));
                });
              }
              initTemp();
            }
        }
})();
