'use strict';

describe('Controller Tests', function() {

    describe('CourseLesson Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockCourseLesson, MockCourseLessonAttachment, MockCourseLessonLearn, MockCourseLessonNote, MockChapter;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockCourseLesson = jasmine.createSpy('MockCourseLesson');
            MockCourseLessonAttachment = jasmine.createSpy('MockCourseLessonAttachment');
            MockCourseLessonLearn = jasmine.createSpy('MockCourseLessonLearn');
            MockCourseLessonNote = jasmine.createSpy('MockCourseLessonNote');
            MockChapter = jasmine.createSpy('MockChapter');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'CourseLesson': MockCourseLesson,
                'CourseLessonAttachment': MockCourseLessonAttachment,
                'CourseLessonLearn': MockCourseLessonLearn,
                'CourseLessonNote': MockCourseLessonNote,
                'Chapter': MockChapter
            };
            createController = function() {
                $injector.get('$controller')("CourseLessonDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'zonesionCloudApplicationApp:courseLessonUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
