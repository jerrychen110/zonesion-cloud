(function(){
  angular
      .module('zonesionCloudApplicationApp')
      .constant('LEFTTOOL',[{
        name:'目录',
        icon:'glyphicon glyphicon-th-list',
        active:true
      },
      // {
      //   name:'问答',
      //   icon:'glyphicon glyphicon-question-sign',
      //   active:false
      // },
      {
        name:'笔记',
        icon:'glyphicon glyphicon-edit',
        active:false
      },
      {
        name:'资料',
        icon:'glyphicon glyphicon-download-alt',
        active:false
      }])
})();
