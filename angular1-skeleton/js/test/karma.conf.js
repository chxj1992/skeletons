// Karma configuration
// Generated on Tue Apr 28 2015 11:54:15 GMT+0800 (CST)

module.exports = function (config) {
    config.set({

        // base path that will be used to resolve all patterns (eg. files, exclude)
        basePath: '../',

        // frameworks to use
        // available frameworks: https://npmjs.org/browse/keyword/karma-adapter
        frameworks: ['jasmine', 'requirejs'],

        // list of files / patterns to load in the browser
        files: [
            {pattern: 'bower_components/**/*.js', included: false},
            {pattern: 'src/**/*.js', included: false},
            {pattern: 'app.js', included: false},
            {pattern: 'router.js', included: false},
            {pattern: 'test/unit/**/*Spec.js', included: false},
            {pattern: 'test-main.js', included: true}
        ],

        // list of files to exclude
        exclude: [
            '**/*.swp'
        ],

        junitReporter : {
            outputFile: 'test_out/unit.xml',
            suite: 'unit'
        },

        preprocessors: {},

        // test results reporter to use
        // possible values: 'dots', 'progress'
        reporters: ['progress'],

        port: 9876,

        colors: true,

        // level of logging
        // possible values: config.LOG_DISABLE || config.LOG_ERROR || config.LOG_WARN || config.LOG_INFO || config.LOG_DEBUG
        logLevel: config.LOG_INFO,

        autoWatch: true,

        browsers: ['Chrome'],

        singleRun: false
    });
};
